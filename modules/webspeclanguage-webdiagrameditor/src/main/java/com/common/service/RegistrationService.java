package com.common.service;

import org.hibernate.exception.ConstraintViolationException;

import com.common.model.Registration;
import com.common.repository.Repository;

public class RegistrationService {

  private Repository repository;
  
  protected RegistrationService() {}
  
  public RegistrationService(Repository theRepository) {
    this.repository = theRepository;
  }
  
  public Repository getRepository() {
    return repository;
  }

  public Registration findRecordByEmail(String email) {
    return this.getRepository().findRegistrationByEmail(email);
  }

  public void save(Registration record) {
    try {
      this.getRepository().save(record);
    } catch (ConstraintViolationException e) {
      throw new AlreadyRegisterException(e);
    }
  }
}
