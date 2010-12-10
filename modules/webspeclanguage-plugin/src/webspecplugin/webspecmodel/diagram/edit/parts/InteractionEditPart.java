package webspecplugin.webspecmodel.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.swt.graphics.Color;

import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.diagram.edit.policies.InteractionCanonicalEditPolicy;
import webspecplugin.webspecmodel.diagram.edit.policies.InteractionItemSemanticEditPolicy;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;
import webspecplugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;

/**
 * @generated
 */
public class InteractionEditPart extends ShapeNodeEditPart {

  /**
   * @generated
   */
  public static final int VISUAL_ID = 2001;

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
  public InteractionEditPart(View view) {
    super(view);
  }

  /**
   * @generated
   */
  protected void createDefaultEditPolicies() {
    installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new InteractionItemSemanticEditPolicy());
    installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
    installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new InteractionCanonicalEditPolicy());
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
    InteractionFigure figure = new InteractionFigure();
    return primaryShape = figure;
  }

  /**
   * @generated
   */
  public InteractionFigure getPrimaryShape() {
    return (InteractionFigure) primaryShape;
  }

  /**
   * @generated
   */
  protected boolean addFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof InteractionNameEditPart) {
      ((InteractionNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureInteractionNameFigure());
      return true;
    }
    return false;
  }

  /**
   * @generated
   */
  protected boolean removeFixedChild(EditPart childEditPart) {
    if (childEditPart instanceof InteractionNameEditPart) {
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

  private boolean installed = false;

  private void installIfNecessary(Interaction interaction) {
    if (!installed) {
      Adapter adapter = new AdapterImpl() {

        public void notifyChanged(Notification notification) {
          refreshVisuals();
        }
      };
      interaction.eAdapters().add(adapter);
      installed = true;
    }
  }

  @Override
  protected void refreshVisuals() {
    Interaction interaction = (Interaction) ((NodeImpl) this.getModel()).getElement();
    this.installIfNecessary(interaction);
    if (interaction.isStarting()) {
      ((InteractionFigure) primaryShape).setLineStyle(Graphics.LINE_DOT);
      ((InteractionFigure) primaryShape).setForegroundColor(ColorConstants.black);
      ((InteractionFigure) primaryShape).setLineWidth(2);
    } else {
      ((InteractionFigure) primaryShape).setLineStyle(Graphics.LINE_SOLID);
      ((InteractionFigure) primaryShape).setForegroundColor(ColorConstants.lightGray);
      ((InteractionFigure) primaryShape).setLineWidth(1);
    }
    super.refreshVisuals();
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
    return getChildBySemanticHint(WebspecmodelVisualIDRegistry.getType(InteractionNameEditPart.VISUAL_ID));
  }

  /**
   * @generated
   */
  public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
    List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
    types.add(WebspecmodelElementTypes.Navigation_4001);
    types.add(WebspecmodelElementTypes.RichBehavior_4002);
    return types;
  }

  /**
   * @generated
   */
  public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
    List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
    if (targetEditPart instanceof webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart) {
      types.add(WebspecmodelElementTypes.Navigation_4001);
    }
    if (targetEditPart instanceof webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart) {
      types.add(WebspecmodelElementTypes.RichBehavior_4002);
    }
    return types;
  }

  /**
   * @generated
   */
  public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(IElementType relationshipType) {
    List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
    if (relationshipType == WebspecmodelElementTypes.Navigation_4001) {
      types.add(WebspecmodelElementTypes.Interaction_2001);
    }
    if (relationshipType == WebspecmodelElementTypes.RichBehavior_4002) {
      types.add(WebspecmodelElementTypes.Interaction_2001);
    }
    return types;
  }

  /**
   * @generated
   */
  public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
    List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
    types.add(WebspecmodelElementTypes.Navigation_4001);
    types.add(WebspecmodelElementTypes.RichBehavior_4002);
    return types;
  }

  /**
   * @generated
   */
  public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(IElementType relationshipType) {
    List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
    if (relationshipType == WebspecmodelElementTypes.Navigation_4001) {
      types.add(WebspecmodelElementTypes.Interaction_2001);
    }
    if (relationshipType == WebspecmodelElementTypes.RichBehavior_4002) {
      types.add(WebspecmodelElementTypes.Interaction_2001);
    }
    return types;
  }

  /**
   * @generated
   */
  public class InteractionFigure extends RoundedRectangle {

    /**
     * @generated
     */
    private WrappingLabel fFigureInteractionNameFigure;

    /**
     * @generated
     */
    public InteractionFigure() {

      FlowLayout layoutThis = new FlowLayout();
      layoutThis.setStretchMinorAxis(false);
      layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

      layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
      layoutThis.setMajorSpacing(5);
      layoutThis.setMinorSpacing(5);
      layoutThis.setHorizontal(true);

      this.setLayoutManager(layoutThis);

      this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(15), getMapMode().DPtoLP(15)));
      this.setLineWidth(1);
      createContents();
    }

    /**
     * @generated
     */
    private void createContents() {

      RectangleFigure rect0 = new RectangleFigure();
      rect0.setFill(false);
      rect0.setOutline(false);
      rect0.setLineWidth(0);
      rect0.setLineStyle(Graphics.LINE_CUSTOM);

      rect0.setBorder(new MarginBorder(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

      this.add(rect0);

      FlowLayout layoutRect0 = new FlowLayout();
      layoutRect0.setStretchMinorAxis(false);
      layoutRect0.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

      layoutRect0.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
      layoutRect0.setMajorSpacing(5);
      layoutRect0.setMinorSpacing(5);
      layoutRect0.setHorizontal(true);

      rect0.setLayoutManager(layoutRect0);

      fFigureInteractionNameFigure = new WrappingLabel();
      fFigureInteractionNameFigure.setText("new interaction");

      rect0.add(fFigureInteractionNameFigure);

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
    public WrappingLabel getFigureInteractionNameFigure() {
      return fFigureInteractionNameFigure;
    }

  }

}
