package org.webspeclanguage.plugin.editor.change;

public class SetValue implements ChangeItem {

  private String objectPath;
  private String feature;
  private String newValue;
  
  public SetValue(String objectPath, String feature, String newValue) {
    this.objectPath = objectPath;
    this.feature = feature;
    this.newValue = newValue;
  }

  public String getObjectPath() {
    return objectPath;
  }

  public String getFeature() {
    return feature;
  }

  public String getNewValue() {
    return newValue;
  }
  
  @Override
  public void accept(ChangeItemVisitor visitor) {
    visitor.visitSetValue(this);
  }
}