package org.webspeclanguage.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;

public class UserStory {

  private long id;
  private String title;
  private Sprint sprint;
  private List<Mockup> mockups;

  protected UserStory() {
    this.mockups = new ArrayList<Mockup>();
  }

  public UserStory(String title) {
    this();

    Validate.notNull(title);
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public List<Mockup> getMockups() {
    return Collections.unmodifiableList(mockups);
  }

  public void addMockUp(Mockup mockup) {
    Validate.notNull(mockup);

    this.mockups.add(mockup);
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  public Sprint getSprint() {
    return sprint;
  }

  public void setSprint(Sprint sprint) {
    this.sprint = sprint;
  }
}