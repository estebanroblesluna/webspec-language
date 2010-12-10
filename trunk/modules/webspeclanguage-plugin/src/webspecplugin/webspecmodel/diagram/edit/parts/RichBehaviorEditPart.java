package webspecplugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
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

import webspecplugin.webspecmodel.diagram.edit.policies.RichBehaviorItemSemanticEditPolicy;

/**
 * @generated
 */
public class RichBehaviorEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 4002;

  /**
   * @generated
   */
  public RichBehaviorEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RichBehaviorItemSemanticEditPolicy());
  }

  /**
   * @generated
   */
  protected boolean addFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof RichBehaviorNamePreconditionActionsEditPart) {
      ((RichBehaviorNamePreconditionActionsEditPart) childEditPart).setLabel(getPrimaryShape().getFigureRichBehaviorActionsAndPreconditionsFigure());
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
    if (childEditPart instanceof RichBehaviorNamePreconditionActionsEditPart) {
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
    return new RichBehaviorFigure();
  }

  /**
   * @generated
   */
  public RichBehaviorFigure getPrimaryShape() {
    return (RichBehaviorFigure) getFigure();
  }

  /**
   * @generated
   */
  public class RichBehaviorFigure extends PolylineConnectionEx {

    /**
     * @generated
     */
    private WrappingLabel fFigureRichBehaviorActionsAndPreconditionsFigure;

    /**
     * @generated
     */
    public RichBehaviorFigure() {
      this.setLineWidth(2);
      this.setLineStyle(Graphics.LINE_DOT);
      this.setForegroundColor(ColorConstants.red);
      this.setBackgroundColor(ColorConstants.red);

      createContents();
      setTargetDecoration(createTargetDecoration());
    }

    private void createContents() {

      fFigureRichBehaviorActionsAndPreconditionsFigure = new WrapLabel();
      fFigureRichBehaviorActionsAndPreconditionsFigure.setText("");

      this.add(fFigureRichBehaviorActionsAndPreconditionsFigure);

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
    public WrappingLabel getFigureRichBehaviorActionsAndPreconditionsFigure() {
      return fFigureRichBehaviorActionsAndPreconditionsFigure;
    }

  }

}
