package org.webspeclanguage.service;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

/**
 * Represents the result of parsing the json representation which includes
 * the diagram and the bounding boxes of the elements.
 * 
 * @author Esteban Robles Luna
 */
public class ParsingResult {

  private Diagram diagram;
  private Map<String, CroppingInfo> croppingMap;
  
  public ParsingResult(Diagram diagram, Map<String, CroppingInfo> croppingMap) {
    Validate.notNull(diagram);
    Validate.notNull(croppingMap);
    
    this.diagram = diagram;
    this.croppingMap = croppingMap;
  }

  public Diagram getDiagram() {
    return this.diagram;
  }

  public Map<String, CroppingInfo> getBoundingBoxes() {
    return this.croppingMap;
  }
}
