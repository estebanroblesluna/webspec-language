package webspecplugin.editor.change;

import java.util.ArrayList;
import java.util.List;

public class ChangeObject {

  private List<ChangeItem> items;
  
  public ChangeObject() {
    this.items = new ArrayList<ChangeItem>();
  }
  
  public void add(ChangeItem changeItem) {
    this.items.add(changeItem);
  }
  
  public List<ChangeItem> getItems() {
    return this.items;
  }
}
