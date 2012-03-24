package org.webspeclanguage.social;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.Validate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.common.model.Registration;
import com.common.model.User;
import com.common.repository.Repository;
import com.common.service.AlreadyRegisterException;
import com.common.service.RegistrationService;

public class SignInAdapterImpl implements SignInAdapter {

  private Repository repository;
  private RegistrationService registrationService;
  private String appUrl;


  public SignInAdapterImpl(Repository repository, RegistrationService registrationService, String appUrl) {
    Validate.notNull(repository);
    Validate.notNull(registrationService);
    Validate.notNull(appUrl);
    
    this.repository = repository;
    this.registrationService = registrationService;
    this.appUrl = appUrl;
  }

  public String signIn(String userId, Connection< ? > connection, NativeWebRequest request) {
    User user = this.repository.getUser(userId);
    
    if (user == null) {
      UserProfile profile = connection.fetchUserProfile();
      String firstName = profile.getFirstName();
      String lastName = profile.getLastName();
      String email = profile.getUsername();
      Object nativeRequest = request.getNativeRequest();
      String ip = "unknown";
      
      if (nativeRequest instanceof HttpServletRequest) {
        HttpServletRequest req = (HttpServletRequest) nativeRequest;
        ip = req.getRemoteAddr();
      }
      
      Registration registration = new Registration(firstName, lastName, email, ip);
      try {
        this.registrationService.save(registration);
        return this.appUrl + "registerSuccess.htm";
      } catch (AlreadyRegisterException e) {
        return this.appUrl + "registerError.htm";
      }
    } else {
      GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") };
      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, "", authorities));
      return null;
    }
  }

}
