package org.webspeclanguage.plugin.editor.change;

import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.plugin.webspecmodel.Diagram;


public class ChangeRecorder {

  private Map<Diagram, DiagramChangeRecorder> recorders;
  
  public ChangeRecorder() {
    this.recorders = new HashMap<Diagram, DiagramChangeRecorder>();
  }
  
  public void startRecording(Diagram diagram) {
    if (!this.isRecording(diagram)) {
      DiagramChangeRecorder diagramChangeRecorder = this.createDiagramChangeRecorder(diagram);
      this.recorders.put(diagram, diagramChangeRecorder);
      diagramChangeRecorder.startRecording();
    }
  }
  
  public ChangeObject stopRecording(Diagram diagram) {
    if (this.isRecording(diagram)) {
      DiagramChangeRecorder diagramChangeRecorder = this.getChangeRecorderFor(diagram);
      ChangeObject changeObject = diagramChangeRecorder.stopRecording();
      this.recorders.remove(diagram);
      return changeObject;
    } else {
      throw new NotRecordingException(diagram);
    }
  }

  private DiagramChangeRecorder getChangeRecorderFor(Diagram diagram) {
    return this.recorders.get(diagram);
  }

  public boolean isRecording(Diagram diagram) {
    return this.recorders.containsKey(diagram);
  }

  private DiagramChangeRecorder createDiagramChangeRecorder(Diagram diagram) {
    DiagramChangeRecorder recorder = new DiagramChangeRecorder(diagram);
    return recorder;
  }
}
