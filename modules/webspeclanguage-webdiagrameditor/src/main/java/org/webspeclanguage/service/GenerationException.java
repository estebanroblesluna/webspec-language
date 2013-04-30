package org.webspeclanguage.service;

/**
 * An exception to signal that a generation error has occured.
 * 
 * @author Esteban Robles Luna
 */
public class GenerationException extends Exception {

  private static final long serialVersionUID = 7173655958281393936L;

  public GenerationException(Exception e) {
    super(e);
  }
}
