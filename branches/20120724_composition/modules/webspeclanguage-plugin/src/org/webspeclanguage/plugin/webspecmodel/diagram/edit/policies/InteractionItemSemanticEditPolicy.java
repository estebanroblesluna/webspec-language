package org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.NavigationCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.NavigationReorientCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.PanelContainerCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.RichBehaviorCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.RichBehaviorReorientCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;
import org.webspeclanguage.plugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;


/**
 * @generated
 */
public class InteractionItemSemanticEditPolicy extends WebspecmodelBaseItemSemanticEditPolicy {

  /**
   * @generated
   */
  public InteractionItemSemanticEditPolicy() {
    super(WebspecmodelElementTypes.Interaction_2001);
  }

  /**
   * @generated
   */
  protected Command getCreateCommand(CreateElementRequest req) {
    if (WebspecmodelElementTypes.PanelContainer_3001 == req.getElementType()) {
      return getGEFWrapper(new PanelContainerCreateCommand(req));
    }
    return super.getCreateCommand(req);
  }

  /**
   * @generated
   */
  protected Command getDestroyElementCommand(DestroyElementRequest req) {
    View view = (View) getHost().getModel();
    CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
    cmd.setTransactionNestingEnabled(false);
    for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
      Edge incomingLink = (Edge) it.next();
      if (WebspecmodelVisualIDRegistry.getVisualID(incomingLink) == NavigationEditPart.VISUAL_ID) {
        DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
        cmd.add(new DestroyElementCommand(r));
        cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
        continue;
      }
      if (WebspecmodelVisualIDRegistry.getVisualID(incomingLink) == RichBehaviorEditPart.VISUAL_ID) {
        DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
        cmd.add(new DestroyElementCommand(r));
        cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
        continue;
      }
    }
    for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
      Edge outgoingLink = (Edge) it.next();
      if (WebspecmodelVisualIDRegistry.getVisualID(outgoingLink) == NavigationEditPart.VISUAL_ID) {
        DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
        cmd.add(new DestroyElementCommand(r));
        cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
        continue;
      }
      if (WebspecmodelVisualIDRegistry.getVisualID(outgoingLink) == RichBehaviorEditPart.VISUAL_ID) {
        DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
        cmd.add(new DestroyElementCommand(r));
        cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
        continue;
      }
    }
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
      case PanelContainerEditPart.VISUAL_ID:
        cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
        // don't need explicit deletion of node as parent's view deletion would clean child views as well 
        // cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
        break;
      }
    }
  }

  /**
   * @generated
   */
  protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
    Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
    return command != null ? command : super.getCreateRelationshipCommand(req);
  }

  /**
   * @generated
   */
  protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
    if (WebspecmodelElementTypes.Navigation_4001 == req.getElementType()) {
      return getGEFWrapper(new NavigationCreateCommand(req, req.getSource(), req.getTarget()));
    }
    if (WebspecmodelElementTypes.RichBehavior_4002 == req.getElementType()) {
      return getGEFWrapper(new RichBehaviorCreateCommand(req, req.getSource(), req.getTarget()));
    }
    return null;
  }

  /**
   * @generated
   */
  protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
    if (WebspecmodelElementTypes.Navigation_4001 == req.getElementType()) {
      return getGEFWrapper(new NavigationCreateCommand(req, req.getSource(), req.getTarget()));
    }
    if (WebspecmodelElementTypes.RichBehavior_4002 == req.getElementType()) {
      return getGEFWrapper(new RichBehaviorCreateCommand(req, req.getSource(), req.getTarget()));
    }
    return null;
  }

  /**
   * Returns command to reorient EClass based link. New link target or source
   * should be the domain model element associated with this node.
   * 
   * @generated
   */
  protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
    switch (getVisualID(req)) {
    case NavigationEditPart.VISUAL_ID:
      return getGEFWrapper(new NavigationReorientCommand(req));
    case RichBehaviorEditPart.VISUAL_ID:
      return getGEFWrapper(new RichBehaviorReorientCommand(req));
    }
    return super.getReorientRelationshipCommand(req);
  }

}
