package webspecplugin.webspecmodel.diagram.edit.parts;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.diagram.edit.policies.DiagramCanonicalEditPolicy;
import webspecplugin.webspecmodel.diagram.edit.policies.DiagramItemSemanticEditPolicy;

/**
 * @generated
 */
public class DiagramEditPart extends org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart {

  /**
   * @generated
   */
  public final static String MODEL_ID = "Webspecmodel"; //$NON-NLS-1$

  /**
   * @generated
   */
  public static final int VISUAL_ID = 1000;

  /**
   * @generated
   */
  public DiagramEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DiagramItemSemanticEditPolicy());
    installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DiagramCanonicalEditPolicy());
    // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
  }

  @Override
  public void activate() {
    super.activate();
    for (Object o : this.getChildren()) {
      AbstractEditPart editPart = (AbstractEditPart) o;
      editPart.refresh();
    }
  }

}
