package org.webspeclanguage.web.servlet;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/projects")
public class ProjectRestService {

  public ProjectRestService() {
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
}
