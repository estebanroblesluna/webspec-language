package webspecplugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.diagram.edit.policies.ListOfContainerListOfCompartmentCanonicalEditPolicy;
import webspecplugin.webspecmodel.diagram.edit.policies.ListOfContainerListOfCompartmentItemSemanticEditPolicy;
import webspecplugin.webspecmodel.diagram.part.Messages;

/**
 * @generated
 */
public class ListOfContainerListOfCompartmentEditPart extends ShapeCompartmentEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 7003;

  /**
   * @generated
   */
  public ListOfContainerListOfCompartmentEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  public String getCompartmentName() {
    return Messages.ListOfContainerListOfCompartmentEditPart_title;
  }

  public IFigure createFigure() {
    ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
    result.setTitleVisibility(false);
    this.hideScrollbars(result);
    return result;
  }

  private void hideScrollbars(ResizableCompartmentFigure result) {
    ScrollPane pane = (ScrollPane) result.getChildren().get(1);
    pane.setHorizontalScrollBar(null);
    pane.setVerticalScrollBar(null);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableCompartmentEditPolicy());
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ListOfContainerListOfCompartmentItemSemanticEditPolicy());
    installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
    installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
    installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new ListOfContainerListOfCompartmentCanonicalEditPolicy());
  }

  /**
   * @generated
   */
  protected void setRatio(Double ratio) {
    // nothing to do -- parent layout does not accept Double constraints as ratio
    // super.setRatio(ratio); 
  }

}