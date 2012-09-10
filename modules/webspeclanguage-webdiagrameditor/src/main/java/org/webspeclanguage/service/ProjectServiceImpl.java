package org.webspeclanguage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webspeclanguage.model.Mockup;
import org.webspeclanguage.model.Project;
import org.webspeclanguage.model.Sprint;
import org.webspeclanguage.model.UserStory;
import org.webspeclanguage.repository.DiagramRepository;

import com.common.model.User;

public class ProjectServiceImpl implements ProjectService {

  private static Log log = LogFactory.getLog(ProjectServiceImpl.class);
  
  private DiagramRepository repository;

  protected ProjectServiceImpl() { }
  
  public ProjectServiceImpl(DiagramRepository repository) {
    Validate.notNull(repository);
    this.repository = repository;
  }

  public DiagramRepository getRepository() {
    return repository;
  }

  @Override
  public void save(Project project) {
    this.getRepository().save(project);
  }

  @Override
  public Project getProject(Long projectId) {
    return this.getRepository().getProject(projectId);
  }

  @Override
  public List<Project> getProjectsFor(User user) {
    return this.getRepository().getProjectsFor(user);
  }

  @Override
  public Sprint getSprint(Long sprintId) {
    return this.getRepository().getSprint(sprintId);
  }

  @Override
  public Project getProjectForUser(Long projectId, User user) {
    return this.getRepository().getProjectForUser(projectId, user);
  }

  @Override
  public void updateProject(Project project, String name) {
    project.setName(name);
    this.getRepository().save(project);
  }

  @Override
  public void addUserStory(Sprint sprint, String title) {
    UserStory user = new UserStory(title);
    sprint.addUserStory(user);
    this.getRepository().save(sprint);
  }

  @Override
  public UserStory getUserStory(Long userStoryId) {
    return this.getRepository().getUserStory(userStoryId);
  }

  @Override
  public void updateUserStory(UserStory user, String title) {
    user.setTitle(title);
    this.getRepository().save(user);
  }

  @Override
  public void addMockup(Project p, String name) {
    Mockup m = new Mockup(name);
    p.addMockUp(m);
    this.getRepository().save(p);
  }

  @Override
  public void addMockup(Sprint sprint, String name) {
    Mockup mockup = new Mockup(name);
    sprint.addMockup(mockup);
    this.getRepository().save(mockup);
    this.getRepository().save(sprint);
  }

  @Override
  public void addMockup(UserStory userStory, String name) {
    Mockup mockup = new Mockup(name);
    userStory.addMockUp(mockup);
    this.getRepository().save(userStory);
  }

  @Override
  public Mockup getMockup(Long mockupId) {
    return this.getRepository().getMockup(mockupId);
  }

  @Override
  public void updateMockupForProject(Mockup mockup, String name) {
    mockup.setName(name);
    this.getRepository().save(mockup);
  }

  @Override
  public void addSprint(Project project, String title, String initDate, String endDate) {
    Date init = null;
    Date end = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
       init = dateFormat.parse(initDate);
    } catch (ParseException e) {
      log.error("Error parsing date", e);
    }
    
    try {
      end = dateFormat.parse(endDate);
    } catch (ParseException e) {
      log.error("Error parsing date", e);
    }
   
    Sprint sprint = new Sprint(title, init, end, Long.valueOf(project.getSprints().size()+1));
    project.addSprint(sprint);
    this.getRepository().save(project);
  }

  @Override
  public void updateSprint(Sprint sprint, String title, String initDate, String endDate) {
    Date init = null;
    Date end = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
      init = dateFormat.parse(initDate);
    } catch (ParseException e) {
      log.error("Error parsing date", e);
    }
    
    try {
      end = dateFormat.parse(endDate);
    } catch (ParseException e) {
      log.error("Error parsing date", e);
    }

    if (init != null && end != null) {
      sprint.setEndDate(end);
      sprint.setInitDate(init);
      sprint.setTitle(title);
      this.getRepository().save(sprint);
    }
  }
}
