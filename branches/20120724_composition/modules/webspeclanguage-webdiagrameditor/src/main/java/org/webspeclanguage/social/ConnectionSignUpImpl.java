package org.webspeclanguage.social;

import org.apache.commons.lang.Validate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.common.model.User;
import com.common.repository.Repository;

public class ConnectionSignUpImpl implements ConnectionSignUp {

  private Repository repository;
  
  public ConnectionSignUpImpl(Repository repository) {
    Validate.notNull(repository);
    this.repository = repository;
  }
  
  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    String username = profile.getUsername();
    User user = this.repository.getUser(username);
    if (user != null) {
      return username;
    } else {
      return "";
    }
  }
}
