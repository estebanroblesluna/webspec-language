package webspecplugin.editor.change;

public class NewWidget implements ChangeItem {

  private String name;
  private String containerName;
  private String type;
  private String interactionName;
  
  public NewWidget(String name, String containerName, String type, String interactionName) {
    this.name = name;
    this.containerName = containerName;
    this.type = type;
    this.interactionName = interactionName;
  }

  public String getInteractionName() {
    return interactionName;
  }
  
  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }

  public String getContainerName() {
    return containerName;
  }
  
  @Override
  public void accept(ChangeItemVisitor visitor) {
    visitor.visitNewWidget(this);
  }
}