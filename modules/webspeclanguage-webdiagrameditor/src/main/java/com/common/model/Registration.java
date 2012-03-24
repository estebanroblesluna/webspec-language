package com.common.model;

import org.apache.commons.lang.Validate;

public class Registration {
  
  @SuppressWarnings("unused")
  private long id;
  
  private String firstName;
  private String lastName;
  private String email;
  private String ip;

  protected Registration() { }

  public Registration(String firstName, String lastName, String email, String ip) {
    Validate.notNull(firstName);
    Validate.notNull(lastName);
    Validate.notNull(email);
    Validate.notNull(ip);

    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.ip = ip;
  }
  
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getIp() {
    return ip;
  }
}
