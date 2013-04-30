package org.webspeclanguage.model;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.Validate;

import com.common.model.User;

public class Diagram {

  private long id;
  private DiagramType type;
  private Date saveTime;
  private User owner;
  private String name;
  private String jsonRepresentation;
  private byte[] imageBytes;

  protected Diagram() {}
  
  public Diagram(DiagramType type, String name, User owner) {
    Validate.notNull(type);
    Validate.notNull(name);
    Validate.notNull(owner);

    this.type = type;
    this.name = name;
    this.owner = owner;
    this.saveTime = Calendar.getInstance().getTime();
  }

  public long getId() {
    return id;
  }

  public DiagramType getType() {
    return type;
  }

  public Date getSaveTime() {
    return saveTime;
  }

  public User getOwner() {
    return owner;
  }

  public String getName() {
    return name;
  }

  public String getJsonRepresentation() {
    return jsonRepresentation;
  }

  public void setSaveTime(Date saveTime) {
    this.saveTime = saveTime;
  }

  public void setJsonRepresentation(String jsonRepresentation) {
    this.jsonRepresentation = jsonRepresentation;
  }

  public byte[] getImageBytes() {
    return imageBytes;
  }

  public void setImageBytes(byte[] imageBytes) {
    this.imageBytes = imageBytes;
  }
}
