package org.webspeclanguage.plugin.editor.change;

public interface ChangeItem {

  void accept(ChangeItemVisitor visitor);

}
