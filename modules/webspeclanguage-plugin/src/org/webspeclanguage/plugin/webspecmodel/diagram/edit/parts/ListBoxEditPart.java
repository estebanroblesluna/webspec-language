package org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies.ListBoxItemSemanticEditPolicy;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class ListBoxEditPart extends ShapeNodeEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 3007;

  /**
   * @generated
   */
  protected IFigure contentPane;

  /**
   * @generated
   */
  protected IFigure primaryShape;

  /**
   * @generated
   */
  public ListBoxEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ListBoxItemSemanticEditPolicy());
    installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
    // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
    // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
  }

  /**
   * @generated
   */
  protected LayoutEditPolicy createLayoutEditPolicy() {
    LayoutEditPolicy lep = new LayoutEditPolicy() {

      protected EditPolicy createChildEditPolicy(EditPart child) {
        EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (result == null) {
          result = new NonResizableEditPolicy();
        }
        return result;
      }

      protected Command getMoveChildrenCommand(Request request) {
        return null;
      }

      protected Command getCreateCommand(CreateRequest request) {
        return null;
      }
    };
    return lep;
  }

  /**
   * @generated
   */
  protected IFigure createNodeShape() {
    WidgetFigure figure = new WidgetFigure();
    return primaryShape = figure;
  }

  /**
   * @generated
   */
  public WidgetFigure getPrimaryShape() {
    return (WidgetFigure) primaryShape;
  }

  /**
   * @generated
   */
  protected boolean addFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof ListBoxNameEditPart) {
      ((ListBoxNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureWidgetName());
      return true;
    }
    return false;
  }

  /**
   * @generated
   */
  protected boolean removeFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof ListBoxNameEditPart) {
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
  protected void removeChildVisual(EditPart childEditPart) {
    if (removeFixedChild(childEditPart)) {
      return;
    }
    super.removeChildVisual(childEditPart);
  }

  /**
   * @generated
   */
  protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
    return getContentPane();
  }

  /**
   * @generated
   */
  protected NodeFigure createNodePlate() {
    DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(5, 5);
    return result;
  }

  /**
   * Creates figure for this edit part.
   * 
   * Body of this method does not depend on settings in generation model
   * so you may safely remove <i>generated</i> tag and modify it.
   * 
   * @generated
   */
  protected NodeFigure createNodeFigure() {
    NodeFigure figure = createNodePlate();
    figure.setLayoutManager(new StackLayout());
    IFigure shape = createNodeShape();
    figure.add(shape);
    contentPane = setupContentPane(shape);
    return figure;
  }

  /**
   * Default implementation treats passed figure as content pane.
   * Respects layout one may have set for generated figure.
   * @param nodeShape instance of generated figure class
   * @generated
   */
  protected IFigure setupContentPane(IFigure nodeShape) {
    if (nodeShape.getLayoutManager() == null) {
      ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
      layout.setSpacing(5);
      nodeShape.setLayoutManager(layout);
    }
    return nodeShape; // use nodeShape itself as contentPane
  }

  /**
   * @generated
   */
  public IFigure getContentPane() {
    if (contentPane != null) {
      return contentPane;
    }
    return super.getContentPane();
  }

  /**
   * @generated
   */
  protected void setForegroundColor(Color color) {
    if (primaryShape != null) {
      primaryShape.setForegroundColor(color);
    }
  }

  /**
   * @generated
   */
  protected void setBackgroundColor(Color color) {
    if (primaryShape != null) {
      primaryShape.setBackgroundColor(color);
    }
  }

  /**
   * @generated
   */
  protected void setLineWidth(int width) {
    if (primaryShape instanceof Shape) {
      ((Shape) primaryShape).setLineWidth(width);
    }
  }

  /**
   * @generated
   */
  protected void setLineType(int style) {
    if (primaryShape instanceof Shape) {
      ((Shape) primaryShape).setLineStyle(style);
    }
  }

  /**
   * @generated
   */
  public EditPart getPrimaryChildEditPart() {
    return getChildBySemanticHint(WebspecmodelVisualIDRegistry.getType(ListBoxNameEditPart.VISUAL_ID));
  }

  /**
   * @generated
   */
  public class WidgetFigure extends RectangleFigure {

    /**
     * @generated
     */
    private WrappingLabel fFigureWidgetName;

    /**
     * @generated
     */
    public WidgetFigure() {

      GridLayout layoutThis = new GridLayout();
      layoutThis.numColumns = 1;
      layoutThis.makeColumnsEqualWidth = true;
      layoutThis.horizontalSpacing = 0;
      layoutThis.verticalSpacing = 0;
      layoutThis.marginWidth = 0;
      layoutThis.marginHeight = 0;
      this.setLayoutManager(layoutThis);

      this.setFill(false);
      this.setOutline(false);
      this.setLineWidth(0);
      createContents();
    }

    /**
     * @generated
     */
    private void createContents() {

      fFigureWidgetName = new WrappingLabel();
      fFigureWidgetName.setText("");

      this.add(fFigureWidgetName);

    }

    /**
     * @generated
     */
    private boolean myUseLocalCoordinates = false;

    /**
     * @generated
     */
    protected boolean useLocalCoordinates() {
      return myUseLocalCoordinates;
    }

    /**
     * @generated
     */
    protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
      myUseLocalCoordinates = useLocalCoordinates;
    }

    /**
     * @generated
     */
    public WrappingLabel getFigureWidgetName() {
      return fFigureWidgetName;
    }

  }

}
