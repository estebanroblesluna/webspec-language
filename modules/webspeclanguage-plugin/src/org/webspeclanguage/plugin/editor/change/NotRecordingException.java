package org.webspeclanguage.plugin.editor.change;

import org.webspeclanguage.plugin.webspecmodel.Diagram;

public class NotRecordingException extends RuntimeException {

  private static final long serialVersionUID = -1052272289268952652L;

  private Diagram diagram;
  
  public NotRecordingException(Diagram diagram) {
    this.diagram = diagram;
  }

  public Diagram getDiagram() {
    return diagram;
  }
}