package com.common.service;

import com.common.model.User;

public interface UserService {

  void addUser(User user);
  
  User getUser(String username);

}