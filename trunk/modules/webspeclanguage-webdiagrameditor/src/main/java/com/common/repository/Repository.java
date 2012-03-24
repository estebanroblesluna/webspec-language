package com.common.repository;

import org.apache.commons.lang.Validate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.model.Registration;
import com.common.model.User;

@org.springframework.stereotype.Repository
public class Repository {

  private SessionFactory sessionFactory;

  @Autowired
  public Repository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.sessionFactory = sessionFactory;
  }

  public Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }

  public void save(Object object) {
    this.getSession().saveOrUpdate(object);
  }

  public void delete(Object object) {
    this.getSession().delete(object);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(Class<T> theClass, Long id) {
    return (T) this.getSession()
      .createCriteria(theClass)
      .add(Restrictions.eq("id", id))
      .uniqueResult();
  }

  public void update(Object object) {
    this.getSession().update(object);
  }

  public User getUser(String username) {
    return (User) this.getSession()
             .createCriteria(User.class)
             .add(Restrictions.eq("username", username))
             .uniqueResult();
  }

  public Registration findRegistrationByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }
}
