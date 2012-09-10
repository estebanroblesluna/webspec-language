package org.webspeclanguage.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

public class Sprint {

  private long id;
  private Date initDate;
  private Date endDate;
  private String title;
  private Long number;
  private Project project;
  private List<Mockup> mockups;
  private List<UserStory> userStories;

  public Sprint() {
    this.mockups = new ArrayList<Mockup>();
    this.userStories = new ArrayList<UserStory>();
  }

  public Sprint(String title, Date initDate, Date endDate, Long number) {
    this();
    
    Validate.notNull(title);
    Validate.notNull(initDate);
    Validate.notNull(endDate);
    Validate.notNull(number);
    
    this.title = title;
    this.number = number++;
    this.initDate = initDate;
    this.endDate = endDate;
  }

  public List<Mockup> getMockups() {
    return Collections.unmodifiableList(mockups);
  }

  public void addMockup(Mockup mockup) {
    Validate.notNull(mockup);

    this.mockups.add(mockup);
  }

  public List<UserStory> getUserStories() {
    return Collections.unmodifiableList(userStories);
  }

  public void addUserStory(UserStory userStory) {
    Validate.notNull(userStory);

    this.userStories.add(userStory);
    userStory.setSprint(this);
  }

  public long getId() {
    return id;
  }

  public Date getInitDate() {
    return initDate;
  }

  public void setInitDate(Date initDate) {
    this.initDate = initDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public Project getProject() {
    return project;
  }
  
  public void setProject(Project project) {
    this.project = project;
  }
}