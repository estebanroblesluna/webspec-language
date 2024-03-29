package org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies.NavigationItemSemanticEditPolicy;


/**
 * @generated
 */
public class NavigationEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 4001;

  /**
   * @generated
   */
  public NavigationEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new NavigationItemSemanticEditPolicy());
  }

  /**
   * @generated
   */
  protected boolean addFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof NavigationNamePreconditionActionsEditPart) {
      ((NavigationNamePreconditionActionsEditPart) childEditPart).setLabel(getPrimaryShape().getFigureNavigationActionsAndPreconditionsFigure());
      return true;
    }
    return false;
  }

  /**
   * @generated
   */
  protected void addChildVisual(EditPart childEditPart, int index) {
    if (addFixedChild(childEditPart)) {
      return;
    }
    super.addChildVisual(childEditPart, -1);
  }

  /**
   * @generated
   */
  protected boolean removeFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof NavigationNamePreconditionActionsEditPart) {
      return true;
    }
    return false;
  }

  /**
   * @generated
   */
  protected void removeChildVisual(EditPart childEditPart) {
    if (removeFixedChild(childEditPart)) {
      return;
    }
    super.removeChildVisual(childEditPart);
  }

  /**
   * Creates figure for this edit part.
   * 
   * Body of this method does not depend on settings in generation model
   * so you may safely remove <i>generated</i> tag and modify it.
   * 
   * @generated
   */

  protected Connection createConnectionFigure() {
    return new NavigationFigure();
  }

  /**
   * @generated
   */
  public NavigationFigure getPrimaryShape() {
    return (NavigationFigure) getFigure();
  }

  /**
   * @generated
   */
  public class NavigationFigure extends PolylineConnectionEx {

    /**
     * @generated
     */
    private WrappingLabel fFigureNavigationActionsAndPreconditionsFigure;

    /**
     * @generated
     */
    public NavigationFigure() {
      this.setLineWidth(1);

      createContents();
      setTargetDecoration(createTargetDecoration());
    }

    private void createContents() {

      fFigureNavigationActionsAndPreconditionsFigure = new WrapLabel();
      fFigureNavigationActionsAndPreconditionsFigure.setTextWrap(true);
      fFigureNavigationActionsAndPreconditionsFigure.setText("");

      this.add(fFigureNavigationActionsAndPreconditionsFigure);
    }

    /**
     * @generated
     */
    private RotatableDecoration createTargetDecoration() {
      PolylineDecoration df = new PolylineDecoration();
      df.setLineWidth(1);
      return df;
    }

    /**
     * @generated
     */
    public WrappingLabel getFigureNavigationActionsAndPreconditionsFigure() {
      return fFigureNavigationActionsAndPreconditionsFigure;
    }

  }

}
