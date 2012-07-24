package org.webspeclanguage.plugin.editor.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ChangeObjectMarshaller {

  private XStream xStream;
  
  public ChangeObjectMarshaller() {
    this.xStream = new XStream(new DomDriver());
    this.xStream.alias("changeObject", ChangeObject.class);
    this.xStream.alias("newInteraction", NewInteraction.class);
    this.xStream.alias("newTransition", NewTransition.class);
    this.xStream.alias("newGenerator", NewGenerator.class);
    this.xStream.alias("newWidget", NewWidget.class);
    this.xStream.alias("changeValue", SetValue.class);
  }
  
  public void save(String filename, ChangeObject changeObject) throws IOException {
    File file = new File(filename);
    int i = 0;
    while (file.exists()) {
      file = new File(filename + i);
      i++;
    }
    String string = xStream.toXML(changeObject);
    FileOutputStream out = new FileOutputStream(file);
    out.write(string.getBytes());
    out.close();
  }
  
  public ChangeObject read(String filename) {
    try {
      return (ChangeObject) this.xStream.fromXML(new FileReader(filename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
