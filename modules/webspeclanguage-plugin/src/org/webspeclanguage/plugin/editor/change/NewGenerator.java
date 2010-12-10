package org.webspeclanguage.plugin.editor.change;

public class NewGenerator implements ChangeItem {

  private String name;
  
  public NewGenerator(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public void accept(ChangeItemVisitor visitor) {
    visitor.visitNewGenerator(this);
  }
}
