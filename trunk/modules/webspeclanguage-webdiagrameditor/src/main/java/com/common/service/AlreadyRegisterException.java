package com.common.service;


public class AlreadyRegisterException extends RuntimeException {

  private static final long serialVersionUID = -5880001420085521072L;
  
  public AlreadyRegisterException(Exception e) {
    super(e);
  }
}
