package org.webspeclanguage.service;

import org.webspeclanguage.userstories.UserStoryGenerationParameters;
/**
 * Represents the output of the generation process
 * 
 * @author Esteban Robles Luna
 */
public enum UserStoryOutput {
  HTML("html", "text/html") {
  	public UserStoryGenerationParameters getParametersForOutput(UserStoryGenerationParametersFactory userStoryGenerationParameterFactory) {
  		return userStoryGenerationParameterFactory.buildHTMLParameters();
  	}

    @Override
    public boolean isAttachment() {
      return false;
    }
  }, 
  WORD_TABULAR("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"){
  	public UserStoryGenerationParameters getParametersForOutput(UserStoryGenerationParametersFactory userStoryGenerationParameterFactory) {
  		return userStoryGenerationParameterFactory.buildWordParameters();
  	}

    @Override
    public boolean isAttachment() {
      return true;
    }
  }, 
  WORD_ENUMERATION("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"){
  	public UserStoryGenerationParameters getParametersForOutput(UserStoryGenerationParametersFactory userStoryGenerationParameterFactory) {
  		return userStoryGenerationParameterFactory.buildWordParameters();
  	}

    @Override
    public boolean isAttachment() {
      return true;
    }
  };
  
  private String extension;
  private String contentType;
  
  private UserStoryOutput(String extension, String contentType) {
    this.extension = extension;
    this.contentType = contentType;
  }

	public abstract UserStoryGenerationParameters getParametersForOutput(UserStoryGenerationParametersFactory userStoryGenerationParameterFactory);

  public String getContentType() {
    return contentType;
  }

  public String getExtension() {
    return extension;
  }

  public abstract boolean isAttachment();
}
