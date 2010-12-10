package org.webspeclanguage.plugin.editor.handlers;

import org.webspeclanguage.plugin.editor.change.ChangeRecorder;

public class ChangeRecorderHolder {

  private static ChangeRecorder instance;
  
  public static synchronized ChangeRecorder getInstance() {
    if (instance == null) {
      instance = new ChangeRecorder();
    }
    return instance;
  }
}
