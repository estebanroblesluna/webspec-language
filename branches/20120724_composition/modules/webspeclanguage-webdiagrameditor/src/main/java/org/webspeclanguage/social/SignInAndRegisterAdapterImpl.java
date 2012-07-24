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

import com.common.model.User;
import com.common.repository.Repository;
import com.common.service.UserService;

public class SignInAndRegisterAdapterImpl implements SignInAdapter {

  private Repository repository;
  private UserService userService;
  private String appUrl;

  public SignInAndRegisterAdapterImpl(Repository repository, UserService userService, String appUrl) {
    Validate.notNull(repository);
    Validate.notNull(userService);
    Validate.notNull(appUrl);
    
    this.repository = repository;
    this.userService = userService;
    this.appUrl = appUrl;
  }

  public String signIn(String userId, Connection< ? > connection, NativeWebRequest request) {
    UserProfile profile = connection.fetchUserProfile();
    String username = profile.getUsername();
    User user = this.repository.getUser(username);
    
    if (user == null) {
      String firstName = profile.getFirstName();
      String lastName = profile.getLastName();
      String email = profile.getUsername();
      Object nativeRequest = request.getNativeRequest();
      String ip = "unknown";
      
      if (nativeRequest instanceof HttpServletRequest) {
        HttpServletRequest req = (HttpServletRequest) nativeRequest;
        ip = req.getRemoteAddr();
      }
      
      User newUser = new User(firstName, lastName, username, email);
      this.userService.addUser(newUser);
    }

    GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") };
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, "", authorities));
    return null;
  }

}
