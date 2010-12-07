package webspecplugin.webspecmodel.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;
import webspecplugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;

/**
 * @generated
 */
public class ListOfContainerItemSemanticEditPolicy extends WebspecmodelBaseItemSemanticEditPolicy {

  /**
   * @generated
   */
  public ListOfContainerItemSemanticEditPolicy() {
    super(WebspecmodelElementTypes.ListOfContainer_3011);
  }

  /**
   * @generated
   */
  protected Command getDestroyElementCommand(DestroyElementRequest req) {
    View view = (View) getHost().getModel();
    CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
    cmd.setTransactionNestingEnabled(false);
    EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
    if (annotation == null) {
      // there are indirectly referenced children, need extra commands: false
      addDestroyChildNodesCommand(cmd);
      addDestroyShortcutsCommand(cmd, view);
      // delete host element
      cmd.add(new DestroyElementCommand(req));
    } else {
      cmd.add(new DeleteCommand(getEditingDomain(), view));
    }
    return getGEFWrapper(cmd.reduce());
  }

  /**
   * @generated
   */
  private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
    View view = (View) getHost().getModel();
    for (Iterator nit = view.getChildren().iterator(); nit.hasNext();) {
      Node node = (Node) nit.next();
      switch (WebspecmodelVisualIDRegistry.getVisualID(node)) {
      case ListOfContainerListOfCompartmentEditPart.VISUAL_ID:
        for (Iterator cit = node.getChildren().iterator(); cit.hasNext();) {
          Node cnode = (Node) cit.next();
          switch (WebspecmodelVisualIDRegistry.getVisualID(cnode)) {
          case ButtonEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case CheckBoxEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case ComboBoxEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case LabelEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case LinkEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case ListBoxEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case RadioButtonEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case TextFieldEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case PanelContainer2EditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          case ListOfContainerEditPart.VISUAL_ID:
            cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
            // don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
            // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
            break;
          }
        }
        break;
      }
    }
  }

}
