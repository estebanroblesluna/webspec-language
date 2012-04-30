package org.webspeclanguage.web.servlet;

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

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.json.JSONException;
import org.springframework.security.oauth2.common.json.JSONObject;
import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;
import org.webspeclanguage.service.DiagramService;

import com.common.model.User;
import com.common.service.UserService;

@Path("/projects")
public class ProjectRestService {

  private DiagramService diagramService;
  private UserService userService;
  
  public ProjectRestService(DiagramService diagramService, UserService userService) {
    Validate.notNull(diagramService);
    Validate.notNull(userService);
    
    this.diagramService = diagramService;
    this.userService = userService;
  }

  /**
   * Returns the list of projects
   */
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String getProjects() {
    return "";
  }
  
  /**
   * Add a new project
   */
  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String addProject() {
    return "";
  }
  
  /**
   * Returns the project info
   */
  @GET
  @Path("/{projectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getProjectInfo(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Updates the project info
   */
  @POST
  @Path("/{projectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateProject(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Returns the list of user stories
   */
  @GET
  @Path("/{projectId}/user-stories")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUserStories(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Adds a new user story
   */
  @POST
  @Path("/{projectId}/user-stories")
  @Produces(MediaType.APPLICATION_JSON)
  public String addUserStory(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Returns the user story
   */
  @GET
  @Path("/{projectId}/user-stories/{userStoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUserStory(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("userStoryId") long userStoryId) {
    return "";
  }
  
  /**
   * Updates the user story
   */
  @POST
  @Path("/{projectId}/user-stories/{userStoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateUserStory(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("userStoryId") long userStoryId) {
    return "";
  }
  
  /**
   * Returns the list of mockups
   */
  @GET
  @Path("/{projectId}/mockups")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockups(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Adds a new mockup
   */
  @POST
  @Path("/{projectId}/mockups")
  @Produces(MediaType.APPLICATION_JSON)
  public String addMockup(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Gets the mockup
   */
  @GET
  @Path("/{projectId}/mockups/{mockupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getMockup(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("mockupId") long mockupId) {
    return "";
  }
  
  /**
   * Updates the mockup
   */
  @POST
  @Path("/{projectId}/mockups/{mockupId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateMockup(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("mockupId") long mockupId) {
    return "";
  }
  
  /**
   * Returns the list of webspec diagrams
   */
  @GET
  @Path("/{projectId}/webspec-diagrams")
  @Produces(MediaType.APPLICATION_JSON)
  public String getWebspecDiagrams(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Adds a new webspec diagram
   */
  @POST
  @Path("/{projectId}/webspec-diagrams")
  @Produces(MediaType.APPLICATION_JSON)
  public String addWebspecDiagram(
		  @DefaultValue("") @PathParam("projectId") long projectId) {
    return "";
  }
  
  /**
   * Gets the webspec diagram
   */
  @GET
  @Path("/{projectId}/webspec-diagrams/{webspecDiagramId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getWebspecDiagram(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("webspecDiagramId") long webspecDiagramId) {
    return "";
  }
  
  /**
   * Updates the webspec diagram
   */
  @POST
  @Path("/{projectId}/webspec-diagrams/{webspecDiagramId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateWebspecDiagram(
		  @DefaultValue("") @PathParam("projectId") long projectId,
		  @DefaultValue("") @PathParam("webspecDiagramId") long webspecDiagramId) {
    return "";
  }
  
  private static Log log = LogFactory.getLog(ProjectRestService.class);
  
  @POST
  @Path("/saveDiagram")
  @Produces(MediaType.APPLICATION_JSON)
  public String saveDiagram(
                        @FormParam("diagramId") long diagramId,
      @DefaultValue("") @FormParam("diagram") String diagramAsJSON) {
    
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userService.getUser(username);
    String name = "name";
    
    Diagram diagram = null;
    if (diagramId == -1) {
      diagram = new Diagram(DiagramType.WEBSPEC, name, user);
    } else {
      diagram = this.diagramService.getDiagram(user, diagramId);
    }

    diagram.setJsonRepresentation(diagramAsJSON);
    this.diagramService.save(diagram);

    return "OK";
  }
  
  @GET
  @Path("/diagram/{diagramId}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getDiagram(
      @DefaultValue("") @PathParam("diagramId") long diagramId) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userService.getUser(username);
    
    return this.diagramService.getDiagram(user, diagramId).getJsonRepresentation();
  }
  
  @GET
  @Path("/diagrams")
  @Produces(MediaType.APPLICATION_JSON)
  public String getDiagrams() {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userService.getUser(username);
    
    List objects = this.diagramService.getIdsAndNamesOfDiagrams(user, DiagramType.WEBSPEC);
    
    try {
      JSONObject result = new JSONObject();
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
      
      result.put("status", "OK");
      result.put("diagrams", diagrams);
      
      return result.toString();
    } catch (JSONException e) {
      return "{ \"status\" : \"Failed\" }";
    }
  }
}
