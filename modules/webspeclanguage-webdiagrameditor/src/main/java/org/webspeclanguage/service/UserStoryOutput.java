package org.webspeclanguage.service;

/**
 * Represents the output of the generation process
 * 
 * @author Esteban Robles Luna
 */
public enum UserStoryOutput {
  HTML("html", "text/html"), 
  WORD_TABULAR("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"), 
  WORD_ENUMERATION("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
  
  private String extension;
  private String contentType;
  
  private UserStoryOutput(String extension, String contentType) {
    this.extension = extension;
    this.contentType = contentType;
  }
  
  public String getContentType() {
    return contentType;
  }

  public String getExtension() {
    return extension;
  }
}
