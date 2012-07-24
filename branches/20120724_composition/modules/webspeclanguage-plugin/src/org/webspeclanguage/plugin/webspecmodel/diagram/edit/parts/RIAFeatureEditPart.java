package org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.swt.graphics.Color;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.RIAFeature;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies.RIAFeatureItemSemanticEditPolicy;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class RIAFeatureEditPart extends ShapeNodeEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 2007;

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
  public RIAFeatureEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RIAFeatureItemSemanticEditPolicy());
    installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
    // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
    // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
  }

  /**
   * @generated
   */
  protected LayoutEditPolicy createLayoutEditPolicy() {

    FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

      protected Command createAddCommand(EditPart child, EditPart after) {
        return null;
      }

      protected Command createMoveChildCommand(EditPart child, EditPart after) {
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
    RIAFeatureFigure figure = new RIAFeatureFigure();
    return primaryShape = figure;
  }

  /**
   * @generated
   */
  public RIAFeatureFigure getPrimaryShape() {
    return (RIAFeatureFigure) primaryShape;
  }

  /**
   * @generated
   */
  protected boolean addFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof RIAFeatureNameEditPart) {
      ((RIAFeatureNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureRIAFeatureNameFigure());
      return true;
    }
    return false;
  }

  /**
   * @generated
   */
  protected boolean removeFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof RIAFeatureNameEditPart) {
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
    DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
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
    return getChildBySemanticHint(WebspecmodelVisualIDRegistry.getType(RIAFeatureNameEditPart.VISUAL_ID));
  }

  private boolean installed = false;

  private void installIfNecessary(RIAFeature model) {
    if (!installed) {
      Adapter adapter = new AdapterImpl() {

        public void notifyChanged(Notification notification) {
          refreshVisuals();
        }
      };
      model.eAdapters().add(adapter);
      installed = true;
    }
  }

  @Override
  protected void refreshVisuals() {
    RIAFeature model = (RIAFeature) ((NodeImpl) this.getModel()).getElement();
    this.installIfNecessary(model);
    if (this.getParent() != null) {
      for (Object i : model.getInteractions()) {
        Interaction interaction = (Interaction) i;
        InteractionEditPart interactionEditPart = (InteractionEditPart) this.findEditPart(this.getParent(), interaction);
        if (interactionEditPart != null) {
          if (model.isVisible()) {
            interactionEditPart.activate();
          } else {
            interactionEditPart.deactivate();
          }
          interactionEditPart.getPrimaryShape().setVisible(model.isVisible());
          for (Object o : interactionEditPart.getSourceConnections()) {
            ConnectionEditPart connection = (ConnectionEditPart) o;
            connection.getFigure().setVisible(model.isVisible());
          }
          for (Object o : interactionEditPart.getTargetConnections()) {
            ConnectionEditPart connection = (ConnectionEditPart) o;
            connection.getFigure().setVisible(model.isVisible());
          }
        }
      }
    }
    super.refreshVisuals();
  }

  /**
   * @generated
   */
  public class RIAFeatureFigure extends RoundedRectangle {

    /**
     * @generated
     */
    private WrappingLabel fFigureRIAFeatureNameFigure;

    /**
     * @generated
     */
    public RIAFeatureFigure() {

      FlowLayout layoutThis = new FlowLayout();
      layoutThis.setStretchMinorAxis(false);
      layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);

      layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
      layoutThis.setMajorSpacing(5);
      layoutThis.setMinorSpacing(5);
      layoutThis.setHorizontal(true);

      this.setLayoutManager(layoutThis);

      this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(15), getMapMode().DPtoLP(15)));
      this.setFill(false);
      this.setOutline(false);
      this.setLineWidth(1);
      createContents();
    }

    /**
     * @generated
     */
    private void createContents() {

      fFigureRIAFeatureNameFigure = new WrappingLabel();
      fFigureRIAFeatureNameFigure.setText("new RIA feature");

      this.add(fFigureRIAFeatureNameFigure);

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
    public WrappingLabel getFigureRIAFeatureNameFigure() {
      return fFigureRIAFeatureNameFigure;
    }

  }

}
