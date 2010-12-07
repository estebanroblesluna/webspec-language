package webspecplugin.editor.handlers;

import webspecplugin.editor.change.ChangeRecorder;

public class ChangeRecorderHolder {

  private static ChangeRecorder instance;
  
  public static synchronized ChangeRecorder getInstance() {
    if (instance == null) {
      instance = new ChangeRecorder();
    }
    return instance;
  }
}
