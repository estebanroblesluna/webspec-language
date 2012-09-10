package org.webspeclanguage.service;

import java.util.List;

import org.webspeclanguage.model.Mockup;
import org.webspeclanguage.model.Project;
import org.webspeclanguage.model.Sprint;
import org.webspeclanguage.model.UserStory;

import com.common.model.User;


public interface ProjectService {

  void save(Project project);
  
  Project getProject(Long projectId);

  List<Project> getProjectsFor(User user);

  Project getProjectForUser(Long project, User user);

  void updateProject(Project project, String name);

  
  Sprint getSprint(Long sprintId);

  void addSprint(Project project, String title, String initDate, String endDate);

  void updateSprint(Sprint sprint, String title, String initDate, String endDate);


  void addUserStory(Sprint sprint, String title);

  UserStory getUserStory(Long userStoryId);

  void updateUserStory(UserStory user, String title);

  
  void addMockup(Project p, String name);

  void addMockup(Sprint s, String name);

  void addMockup(UserStory us, String name);

  Mockup getMockup(Long mockupId);

  void updateMockupForProject(Mockup m, String name);
}
