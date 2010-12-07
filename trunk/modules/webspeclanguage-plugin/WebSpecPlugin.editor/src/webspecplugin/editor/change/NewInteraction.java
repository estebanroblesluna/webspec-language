package webspecplugin.editor.change;

public class NewInteraction implements ChangeItem {

  private String name;
  
  public NewInteraction(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  @Override
  public void accept(ChangeItemVisitor visitor) {
    visitor.visitNewInteraction(this);
  }
}
