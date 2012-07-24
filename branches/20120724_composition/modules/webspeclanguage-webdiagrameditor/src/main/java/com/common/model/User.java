package com.common.model;

import org.apache.commons.lang.Validate;

public class User {

  private long id;

  private String firstName;
  private String lastName;
  private String username;
  private String email;

  protected User() { }
  
  public User(String firstName, String lastName, String username, String email) {
    Validate.notNull(username);

    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
