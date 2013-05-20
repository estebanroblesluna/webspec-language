package org.webspeclanguage.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.json.JSONException;
import org.springframework.security.oauth2.common.json.JSONObject;
import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;
import org.webspeclanguage.model.Mockup;
import org.webspeclanguage.model.Project;
import org.webspeclanguage.model.Sprint;
import org.webspeclanguage.model.UserStory;
import org.webspeclanguage.service.DiagramService;
import org.webspeclanguage.service.ProjectService;
import org.webspeclanguage.service.UserStoryOutput;
import org.webspeclanguage.service.UserStoryServiceImpl;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

import com.common.model.User;
import com.common.service.UserService;

@Path("/projects")
public class ProjectRestService {

  private static Log log = LogFactory.getLog(ProjectRestService.class);

  private DiagramService diagramService;
  private UserService userService;
  private ProjectService projectService;
  private UserStoryServiceImpl userStoryService;
  private SimpleDateFormat formatter;

  public ProjectRestService(DiagramService diagramService, UserService userService, ProjectService projectService, UserStoryServiceImpl userStoryService) {
    Validate.notNull(diagramService);
    Validate.notNull(userService);
    Validate.notNull(projectService);
    Validate.notNull(userStoryService);

    this.diagramService = diagramService;
    this.userService = userService;
    this.projectService = projectService;
    this.userStoryService = userStoryService;
    this.formatter = new SimpleDateFormat("dd/MM/yyyy");
  }

  /**
   * Returns the list of projects
   */
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String getProjects() {
    User user = this.getUser();
    List<Project> projectsResult = this.projectService.getProjectsFor(user);

    try {
      JSONObject result = this.getJSON();
      List<JSONObject> projects = new ArrayList<JSONObject>();

      for (Project p : projectsResult) {
        JSONObject project = new JSONObject();
        project.put("id", p.getId());
        project.put("name", p.getName());
        projects.add(project);
      }
      result.put("projects", projects);

      return result.toString();
    } catch (JSONException e) {
      return this.failed();
    }
  }

  /**
   * Add a new project
   */
  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String addProject(@DefaultValue("") @FormParam("name") String name) {
    User user = this.getUser();
    Project project = new Project(name, user);
    this.projectService.save(project);
    return this.getJSON().toString();
  }

  /**
   * Updates the project info
   */
  @POST
  @Path("/{projectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateProject(@DefaultValue("") @PathParam("projectId") long projectId, @DefaultValue("") @FormParam("name") String name) {
    Project project = this.getProjectForUser(projectId);
    
    if (project == null) {
      return failed();
    } else {
      this.projectService.updateProject(project, name);
      return this.getJSON().toString();
    }
  }

  /**
   * Get the project info
   */
  @GET
  @Path("/{projectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getProject(@DefaultValue("") @PathParam("projectId") long projectId) {
    Project project = this.getProjectForUser(projectId);
    
    if (project == null) {
      return failed();
    } else {
      try {
        JSONObject result = this.getJSON();
        result.put("id", project.getId());
        result.put("name", project.getName());
        return result.toString();
      } catch (JSONException e) {
        return failed();
      }
    }
  }

  @GET
  @Path("/sprint/{sprintId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSprint(@DefaultValue("") @PathParam("sprintId") long sprintId) {
    Sprint sprint = this.getSprintForUser(sprintId);
    
    if (sprint == null) {
      return failed();
    } else {
      try {
        JSONObject result = this.getJSON();
        result.put("id", sprint.getId());
        result.put("initDate", this.formatter.format(sprint.getInitDate()));
        result.put("endDate", this.formatter.format(sprint.getEndDate()));
        result.put("title", sprint.getTitle());
        result.put("number", sprint.getNumber());
        result.put("projectId", sprint.getProject().getId());

        return result.toString();
      } catch (JSONException e) {
        return failed();
      }
    }
  }
  /**
   * Returns the list of user stories
   */
  @GET
  @Path("/{projectId}/sprints")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSprints(@DefaultValue("") @PathParam("projectId") long projectId) {
    Project p = this.getProjectForUser(projectId);
    
    if (p == null) {
      return this.failed();
    } else {
      List<Sprint> s = p.getSprints();
      
      try {
        JSONObject result = this.getJSON();
        List<JSONObject> sprints = new ArrayList<JSONObject>();

        for (Sprint sprint : s) {
          JSONObject sp = new JSONObject();
          sp.put("id", sprint.getId());
          sp.put("initDate", this.formatter.format(sprint.getInitDate()));
          sp.put("endDate", this.formatter.format(sprint.getEndDate()));
          sp.put("title", sprint.getTitle());
          sp.put("number", sprint.getNumber());
          sprints.add(sp);
        }

        result.put("sprints", sprints);
        result.put("projectId", p.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  /**
   * Returns the list of user stories
   */
  @GET
  @Path("/{sprintId}/user-stories")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUserStories(@DefaultValue("") @PathParam("sprintId") long sprintId) {
    Sprint sprint = this.getSprintForUser(sprintId);
    
    if (sprint == null) {
      return this.failed();
    } else {
      try {
        JSONObject result = this.getJSON();
        
        List<JSONObject> userStories = new ArrayList<JSONObject>();
        
        for (UserStory us : sprint.getUserStories()) {
          JSONObject userS = new JSONObject();
          userS.put("id", us.getId());
          userS.put("title", us.getTitle());
          userStories.add(userS);
        }
        
        result.put("userStories", userStories);
        result.put("projectId", sprint.getProject().getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  /**
   * Adds a new user story
   */
  @POST
  @Path("/sprint/{sprintId}/user-stories")
  @Produces(MediaType.APPLICATION_JSON)
  public String addUserStory(@DefaultValue("") @PathParam("sprintId") long sprintId, @DefaultValue("") @FormParam("title") String title) {
    Sprint sprint = this.getSprintForUser(sprintId);
    
    if (sprint == null) {
      return this.failed();
    } else {
      try {
        this.projectService.addUserStory(sprint, title);

        JSONObject result = this.getJSON();
        result.put("sprintId", sprint.getId());
        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }
  
  /**
   * Returns the user story
   */
  @GET
  @Path("/user-stories/{userStoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUserStory(@DefaultValue("") @PathParam("userStoryId") long userStoryId) {
    UserStory user = this.getUserStoryForUser(userStoryId);
    
    if (user != null && user.getSprint() != null) {
      Project p = this.validateProjectForUser(user.getSprint().getProject());
      if (p == null) {
        return this.failed();
      } else {
        try {
          JSONObject userS = this.getJSON();
          userS.put("id", user.getId());
          userS.put("title", user.getTitle());
          userS.put("sprintId", user.getSprint().getId());

          return userS.toString();
        } catch (JSONException e) {
          return this.failed();
        }
      }
    } else {
      return this.failed();
    }
  }

  private UserStory getUserStoryForUser(long userStoryId) {
    User user = this.getUser();
    Project project = null;
    UserStory userStory = this.projectService.getUserStory(userStoryId);
    
    if (userStory != null && userStory.getSprint() != null && userStory.getSprint().getProject() != null) {
      project = this.projectService.getProjectForUser(userStory.getSprint().getProject().getId(), user);
    }
    return (project != null) ? userStory : null;
  }

  /**
   * Updates the user story
   */
  @POST
  @Path("/user-story/{userStoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateUserStory(@DefaultValue("") @PathParam("userStoryId") long userStoryId, @DefaultValue("") @FormParam("title") String title) {

    UserStory user = this.projectService.getUserStory(userStoryId);
    if (user != null && user.getSprint() != null) {

      Project project = this.validateProjectForUser(user.getSprint().getProject());
      if (project == null) {
        return this.failed();
      } else {
        try {
          this.projectService.updateUserStory(user, title);
          JSONObject result = this.getJSON();
          result.put("sprintId", user.getSprint().getId());
          return result.toString();
        } catch (JSONException e) {
          return this.failed();
        }
      }
    } else {
      return this.failed();
    }
  }

  /**
   * Adds a new sprint
   */
  @POST
  @Path("/project/{projectId}/sprint")
  @Produces(MediaType.APPLICATION_JSON)
  public String addSprint(@DefaultValue("") @PathParam("projectId") long projectId, @DefaultValue("") @FormParam("title") String title,
          @DefaultValue("") @FormParam("initDate") String initDate, @DefaultValue("") @FormParam("endDate") String endDate) {

    Project project = this.getProjectForUser(projectId);
    if (project == null) {
      return this.failed();
    } else {
      try {
        this.projectService.addSprint(project, title, initDate, endDate);
        JSONObject result = this.getJSON();
        result.put("projectId", project.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  /**
   * Update a sprint
   */
  @POST
  @Path("/{sprintId}/sprint")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateSprint(@DefaultValue("") @PathParam("sprintId") long sprintId, @DefaultValue("") @FormParam("title") String title,
          @DefaultValue("") @FormParam("initDate") String initDate, @DefaultValue("") @FormParam("endDate") String endDate) {

    Sprint sprint = this.getSprintForUser(sprintId);
    if (sprint == null) {
      return this.failed();
    } else {
      try {
        this.projectService.updateSprint(sprint, title, initDate, endDate);
        JSONObject result = this.getJSON();
        result.put("projectId", sprint.getProject().getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  /**
   * Returns the list of mockups
   */
  @GET
  @Path("/{projectId}/mockupsForProject")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupsForProject(@DefaultValue("") @PathParam("projectId") long projectId) {
    Project project = this.getProjectForUser(projectId);
    
    if (project == null) {
      return this.failed();
    } else {
      try {
        JSONObject result = this.getJSON();
        
        List<JSONObject> mockups = new ArrayList<JSONObject>();
        for (Mockup m : project.getMockups()) {
          JSONObject mock = new JSONObject();
          mock.put("id", m.getId());
          mock.put("title", m.getName());
          mock.put("projectId", project.getId());
          mockups.add(mock);
        }

        result.put("mockups", mockups);
        result.put("projectId", projectId);

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  @GET
  @Path("/{sprintId}/mockupsForSprint")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupsForSprint(@DefaultValue("") @PathParam("sprintId") long sprintId) {
    Sprint sprint = this.projectService.getSprint(sprintId);
    Project project = this.validateProjectForUser(sprint.getProject());

    if (project == null) {
      return this.failed();
    } else {
      try {
        JSONObject result = this.getJSON();
        
        List<JSONObject> mockups = new ArrayList<JSONObject>();
        for (Mockup m : sprint.getMockups()) {
          JSONObject mock = new JSONObject();
          mock.put("id", m.getId());
          mock.put("title", m.getName());
          mock.put("sprintId", sprint.getId());
          mockups.add(mock);
        }

        result.put("mockups", mockups);
        result.put("sprintId", sprint.getId());
        result.put("projectId", sprint.getProject().getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  @GET
  @Path("/{userStoryId}/mockupsForUserStory")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupsForUserStory(@DefaultValue("") @PathParam("userStoryId") long userStoryId) {
    UserStory userStory = this.projectService.getUserStory(userStoryId);
    
    if (userStory != null && userStory.getSprint() != null) {
      Project project = this.validateProjectForUser(userStory.getSprint().getProject());
    
      if (project == null) {
        return this.failed();
      } else {
        try {
          JSONObject result = this.getJSON();
          
          List<JSONObject> mockups = new ArrayList<JSONObject>();
          for (Mockup mockup : userStory.getMockups()) {
            JSONObject mock = new JSONObject();
            mock.put("id", mockup.getId());
            mock.put("title", mockup.getName());
            mock.put("userStoryId", userStory.getId());
            mockups.add(mock);
          }

          result.put("mockups", mockups);
          result.put("userStoryId", userStory.getId());
          result.put("sprintId", userStory.getSprint().getId());

          return result.toString();
        } catch (JSONException e) {
          return this.failed();
        }
      }
    } else {
      return this.failed();
    }
  }

  /**
   * Adds a new mockup
   */
  @POST
  @Path("/project/{projectId}/mockups")
  @Produces(MediaType.APPLICATION_JSON)
  public String addMockupToProject(@DefaultValue("") @PathParam("projectId") long projectId, @DefaultValue("") @FormParam("name") String name) {
    Project project = this.getProjectForUser(projectId);
    
    if (project == null) {
      return this.failed();
    } else {
      try {
        this.projectService.addMockup(project, name);
        JSONObject result = this.getJSON();
        result.put("projectId", project.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }
 
  /**
   * Adds a new mockup
   */
  @POST
  @Path("/sprint/{sprintId}/mockups")
  @Produces(MediaType.APPLICATION_JSON)
  public String addMockupToSprint(@DefaultValue("") @PathParam("sprintId") long sprintId, @DefaultValue("") @FormParam("name") String name) {
    Sprint sprint = this.getSprintForUser(sprintId);

    if (sprint == null) {
      return this.failed();
    } else {
      try {
        this.projectService.addMockup(sprint, name);
        JSONObject result = this.getJSON();
        result.put("sprintId", sprint.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  private Sprint getSprintForUser(Long sprintId) {
    User user = this.getUser();
    Project project = null;
    Sprint sprint = this.projectService.getSprint(sprintId);
    if (sprint != null && sprint.getProject() != null) {
      project = this.projectService.getProjectForUser(sprint.getProject().getId(), user);
    }
    return (project != null) ? sprint : null;
  }

  /**
   * Adds a new mockup
   */
  @POST
  @Path("/userStory/{userStoryId}/mockups")
  @Produces(MediaType.APPLICATION_JSON)
  public String addMockupToUserStory(@DefaultValue("") @PathParam("userStoryId") long userStoryId, @DefaultValue("") @FormParam("name") String name) {
    UserStory userStory = this.getUserStoryForUser(userStoryId);

    if (userStory == null) {
      return this.failed();
    } else {
      try {
        this.projectService.addMockup(userStory, name);
        JSONObject result = this.getJSON();
        result.put("userStoryId", userStory.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    }
  }

  /**
   * Gets the mockup
   */
  @GET
  @Path("/mockups/{mockupId}/userStory/{userStoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupForUserStory(@DefaultValue("") @PathParam("mockupId") long mockupId, @DefaultValue("") @PathParam("userStoryId") long userStoryId) {
    UserStory userStory = this.getUserStoryForUser(userStoryId);
    
    if (userStory != null && this.containsMockup(userStory.getMockups(), mockupId)) {
      Mockup mockup = this.projectService.getMockup(mockupId);
      try {
        JSONObject mock = this.generateJSONMockup(mockup);
        mock.put("userStoryId", userStory.getId());

        return mock.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    } else {
      return this.failed();
    }
  }

  private JSONObject generateJSONMockup(Mockup m) throws JSONException {
    JSONObject mock = this.getJSON();
    mock.put("id", m.getId());
    mock.put("title", m.getName());
    return mock;
  }

  /**
   * Gets the mockup
   */
  @GET
  @Path("/mockups/{mockupId}/sprint/{sprintId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupForSprint(@DefaultValue("") @PathParam("mockupId") long mockupId, @DefaultValue("") @PathParam("sprintId") long sprintId) {
    Sprint sprint = this.getSprintForUser(sprintId);
    
    if (sprint != null && this.containsMockup(sprint.getMockups(), mockupId)) {
      Mockup m = this.projectService.getMockup(mockupId);
      try {
        JSONObject mock = this.getJSON();
        mock.put("id", m.getId());
        mock.put("title", m.getName());
        mock.put("sprintId", sprint.getId());
        mock.put("projectId", sprint.getProject().getId());

        return mock.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    } else {
      return this.failed();
    }
  }

  /**
   * Gets the mockup
   */
  @GET
  @Path("/mockups/{mockupId}/project/{projectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockupForProject(@DefaultValue("") @PathParam("mockupId") long mockupId, @DefaultValue("") @PathParam("projectId") long projectId) {
    Project project = this.getProjectForUser(projectId);
    
    if (project != null && this.containsMockup(project.getMockups(), mockupId)) {
      Mockup m = this.projectService.getMockup(mockupId);
      try {
        JSONObject mock = this.generateJSONMockup(m);
        mock.put("projectId", project.getId());

        return mock.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    } else {
      return this.failed();
    }
  }

  private boolean containsMockup(List<Mockup> mockups, final Long mockupId) {
    return CollectionUtils.exists(mockups, new Predicate() {
      @Override
      public boolean evaluate(Object object) {
        Mockup mockup = (Mockup) object;
        return mockup.getId().equals(mockupId);
      }
    });
  }

  /**
   * Updates the mockup
   */
  @POST
  @Path("/project/{projectId}/mockups/{mockupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateMockupForProject(@DefaultValue("") @PathParam("projectId") long projectId, @DefaultValue("") @PathParam("mockupId") long mockupId,
          @DefaultValue("") @FormParam("name") String name) {

    Project project = this.getProjectForUser(projectId);
    
    if (project != null && this.containsMockup(project.getMockups(), mockupId)) {
      try {
        Mockup mockup = this.projectService.getMockup(mockupId);
        this.projectService.updateMockupForProject(mockup, name);
        JSONObject result = this.getJSON();
        result.put("projectId", project.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    } else {
      return this.failed();
    }
  }

  /**
   * Updates the mockup
   */
  @POST
  @Path("/sprint/{sprintId}/mockup/{mockupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateMockupForSprint(@DefaultValue("") @PathParam("sprintId") long sprintId, @DefaultValue("") @PathParam("mockupId") long mockupId,
          @DefaultValue("") @FormParam("name") String name) {
    Sprint sprint = this.getSprintForUser(sprintId);
    
    if (sprint != null && this.containsMockup(sprint.getMockups(), mockupId)) {
      try {
        Mockup mockup = this.projectService.getMockup(mockupId);
        this.projectService.updateMockupForProject(mockup, name);
        JSONObject result = this.getJSON();
        result.put("sprintId", sprint.getId());

        return result.toString();
      } catch (JSONException e) {
        return this.failed();
      }
    } else {
      return this.failed();
    }
  }

  /**
   * Updates the mockup
   */
  @POST
  @Path("/userStory/{userStoryId}/mockups/{mockupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateMockupForUserStory(@DefaultValue("") @PathParam("userStoryId") long userStoryId, @DefaultValue("") @PathParam("mockupId") long mockupId,
          @DefaultValue("") @FormParam("name") String name) {

    UserStory userStory = this.getUserStoryForUser(userStoryId);
    
    if (userStory != null && this.containsMockup(userStory.getMockups(), mockupId)) {
      try {
        Mockup mockup = this.projectService.getMockup(mockupId);
        this.projectService.updateMockupForProject(mockup, name);
        JSONObject result = this.getJSON();
        result.put("userStoryId", userStory.getId());
        return result.toString();

      } catch (JSONException e) {
        return this.failed();
      }

    } else {
      return this.failed();
    }
  }

  @POST
  @Path("/saveDiagram")
  @Produces(MediaType.APPLICATION_JSON)
  public String saveDiagram(@DefaultValue("-1") @FormParam("diagramId") String diagramIdAsString, @DefaultValue("") @FormParam("diagram") String diagramAsJSON) {
    long diagramId = -1;
    try {
      diagramId = Long.valueOf(diagramIdAsString);
    } catch (Exception e) {
      //ignore
    }
    
    User user = this.getUser();
    String name = "name";

    Diagram diagram = null;
    if (diagramId == -1) {
      diagram = new Diagram(DiagramType.WEBSPEC, name, user);
    } else {
      diagram = this.diagramService.getDiagram(user, diagramId);
    }

    diagram.setJsonRepresentation(diagramAsJSON);
    this.diagramService.save(diagram);

    return this.getJSON().toString();
  }

  @GET
  @Path("/diagram/{diagramId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getDiagram(@DefaultValue("") @PathParam("diagramId") long diagramId) {
    User user = this.getUser();

    return this.diagramService.getDiagram(user, diagramId).getJsonRepresentation();
  }
  
  @GET
  @Path("/diagram/{diagramId}/userStories/{output}")
  public Response convertTo(@DefaultValue("") @PathParam("diagramId") long diagramId, @PathParam("output") String output) {
    User user = this.getUser();
    
    try {
      UserStoryOutput userStoryOutput = UserStoryOutput.valueOf(output);
      byte[] result = this.userStoryService.generate(user, diagramId, userStoryOutput);
      String contentType = userStoryOutput.getContentType();
      return Response
              .ok(result, MediaType.valueOf(contentType))
              .header("Content-disposition","attachment; filename=conversion." + userStoryOutput.getExtension())
              .build();
    } catch (Exception e) {
      return Response.status(500).entity("Error converting to user story").build();
    }
  }
  
  @POST
  @Path("/diagram/{diagramId}/saveImage")
  public Response saveImage(@DefaultValue("") @PathParam("diagramId") long diagramId) {
    User user = this.getUser();
    
    try {
      File file = new File("/Users/estebanroblesluna/Documents/Editor.png");
      byte[] contents = IOUtils.toByteArray(new FileInputStream(file));
      
      Diagram diagram = this.diagramService.getDiagram(user, diagramId);
      diagram.setImageBytes(contents);
      
      this.diagramService.save(diagram);
      return Response
              .ok()
              .build();
    } catch (Exception e) {
      return Response.status(500).entity("Error saving image").build();
    }
  }
  
  @GET
  @Path("/diagram/{diagramId}/image")
  public Response getImage(@DefaultValue("") @PathParam("diagramId") long diagramId) {
	  try {
	      File file = new File("/home/sony/Development/repositorio/webspec-language/modules/webspeclanguage-user-stories/userStories-example-folder/img/scenarios/MyBookingListDiagram.png");
	      byte[] contents = IOUtils.toByteArray(new FileInputStream(file));
	      return Response.ok(contents, "image/png")
	              .build();
	    } catch (Exception e) {
	      return Response.status(500).entity("Error saving image").build();
	    }
  }
  
  @GET
  @Path("/diagram/{diagramId}/image/{x}/{y}/{width}/{height}")
  public Response getImageClip(
          @DefaultValue("") @PathParam("diagramId") long diagramId,
          @PathParam("x") int x,
          @PathParam("y") int y,
          @PathParam("width") int width,
          @PathParam("height") int height) {
	  try {
	      File file = new File("/home/sony/Development/repositorio/webspec-language/modules/webspeclanguage-user-stories/userStories-example-folder/img/scenarios/MyBookingListDiagram.png");
	      ByteArrayOutputStream byteArrayOutputStream = ImageCroppingUtil.cropImage(file, new CroppingInfo(x, y, width, height));
	      Response response = Response.ok(byteArrayOutputStream.toByteArray(), "image/png")
	              .build();
	      return response;
	    } catch (Exception e) {
	      return Response.status(500).entity("Error saving image").build();
	    }
  }
  

  @GET
  @Path("/diagrams")
  @Produces(MediaType.APPLICATION_JSON)
  public String getDiagrams() {
    User user = this.getUser();

    List objects = this.diagramService.getIdsAndNamesOfDiagrams(user, DiagramType.WEBSPEC);

    try {
      JSONObject result = this.getJSON();
      List<JSONObject> diagrams = new ArrayList<JSONObject>();

      for (Object object : objects) {
        Object[] array = (Object[]) object;
        long id = (Long) array[0];
        String name = (String) array[1];

        JSONObject diagram = new JSONObject();
        diagram.put("id", id);
        diagram.put("name", name);
        diagrams.add(diagram);
      }

      result.put("diagrams", diagrams);

      return result.toString();
    } catch (JSONException e) {
      return this.failed();
    }
  }
  
  private Project validateProjectForUser(Project project) {
    if (project != null) {
      return this.getProjectForUser(project.getId());
    } else {
      return null;
    }
  }

  private Project getProjectForUser(Long projectId) {
    User user = this.getUser();
    Project project = this.projectService.getProjectForUser(projectId, user);
    return project;
  }
  
  private User getUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userService.getUser(username);
    return user;
  }

  private JSONObject getJSON() {
    JSONObject object = new JSONObject();
    try {
      object.put("status", "OK");
    } catch (JSONException e) {
    }
    return object;
  }
  
  private String failed() {
    return "{ \"status\" : \"Failed\" }";
  }
}
