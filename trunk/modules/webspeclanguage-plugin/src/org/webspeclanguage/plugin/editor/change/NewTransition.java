package org.webspeclanguage.plugin.editor.change;

public class NewTransition implements ChangeItem {

  private String name;
  
  public NewTransition(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  @Override
  public void accept(ChangeItemVisitor visitor) {
    visitor.visitNewTransition(this);
  }
}
