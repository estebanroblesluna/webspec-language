package webspecplugin.editor.change;

public interface ChangeItem {

  void accept(ChangeItemVisitor visitor);

}
