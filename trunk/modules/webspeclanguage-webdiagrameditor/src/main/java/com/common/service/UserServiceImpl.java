package com.common.service;

import org.apache.commons.lang.Validate;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.common.model.User;
import com.common.repository.Repository;

public class UserServiceImpl implements UserDetailsService, UserService {

  private Repository repository;
  
  protected UserServiceImpl() { }
  
  public UserServiceImpl(Repository repository) {
    Validate.notNull(repository);
    this.repository = repository;
  }
  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    User user = this.repository.getUser(username);
    if (user != null) {
      GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") };
      return new org.springframework.security.core.userdetails.User(username, "", true, true, true, true, authorities);
    } else {
      return null;
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Transactional
  public void addUser(User user) {
    this.repository.save(user);
  }
}
