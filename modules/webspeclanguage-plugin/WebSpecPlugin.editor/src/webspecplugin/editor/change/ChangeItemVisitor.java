package webspecplugin.editor.change;

public interface ChangeItemVisitor {

  void visitNewGenerator(NewGenerator newGenerator);

  void visitNewInteraction(NewInteraction newInteraction);

  void visitNewTransition(NewTransition newTransition);

  void visitNewWidget(NewWidget newWidget);

  void visitSetValue(SetValue setValue);

}
