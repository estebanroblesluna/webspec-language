package org.webspeclanguage.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.common.model.User;

public class Project {

  private long id;
  private List<User> users;
  private List<Sprint> sprints;
  private List<Mockup> mockups;
  private String name;
  
  protected Project() {
    this.users = new ArrayList<User>();
    this.sprints = new ArrayList<Sprint>();
    this.mockups = new ArrayList<Mockup>();
  }
  
  public Project(String name, User user) {
    this();
    Validate.notNull(name);
    Validate.notNull(user);
    
    this.name = name;
    this.addUser(user);
  }

  public List<User> getUsers() {
    return Collections.unmodifiableList(users);
  }

  public void addUser(User user) {
    Validate.notNull(user);

    this.users.add(user);
  }

  public long getId() {
    return id;
  }

  public List<Sprint> getSprints() {
    return Collections.unmodifiableList(sprints);
  }

  public void addSprint(Sprint sprint) {
    Validate.notNull(sprint);

    this.sprints.add(sprint);
    sprint.setProject(this);
  }

  public List<Mockup> getMockups() {
    return Collections.unmodifiableList(mockups);
  }

  public void addMockUp(Mockup mockup) {
    Validate.notNull(mockup);

    this.mockups.add(mockup);
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
