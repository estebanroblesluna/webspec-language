package org.webspeclanguage.model;

import org.apache.commons.lang.Validate;

public class Mockup {

  private Long id;
  private String name;

  protected Mockup() {}

  public Mockup(String name) {
    this();
    Validate.notNull(name);
    
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
