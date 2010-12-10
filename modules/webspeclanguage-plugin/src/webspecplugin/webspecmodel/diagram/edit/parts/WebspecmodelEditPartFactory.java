package webspecplugin.webspecmodel.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class WebspecmodelEditPartFactory implements EditPartFactory {

  /**
   * @generated
   */
  public EditPart createEditPart(EditPart context, Object model) {
    if (model instanceof View) {
      View view = (View) model;
      switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {

      case DiagramEditPart.VISUAL_ID:
        return new DiagramEditPart(view);

      case InteractionEditPart.VISUAL_ID:
        return new InteractionEditPart(view);

      case InteractionNameEditPart.VISUAL_ID:
        return new InteractionNameEditPart(view);

      case OneOfNumbersEditPart.VISUAL_ID:
        return new OneOfNumbersEditPart(view);

      case OneOfNumbersNameEditPart.VISUAL_ID:
        return new OneOfNumbersNameEditPart(view);

      case OneOfStringsEditPart.VISUAL_ID:
        return new OneOfStringsEditPart(view);

      case OneOfStringsNameEditPart.VISUAL_ID:
        return new OneOfStringsNameEditPart(view);

      case UniformNumberDistributionEditPart.VISUAL_ID:
        return new UniformNumberDistributionEditPart(view);

      case UniformNumberDistributionNameEditPart.VISUAL_ID:
        return new UniformNumberDistributionNameEditPart(view);

      case OneOfArrayEditPart.VISUAL_ID:
        return new OneOfArrayEditPart(view);

      case OneOfArrayNameEditPart.VISUAL_ID:
        return new OneOfArrayNameEditPart(view);

      case StringGeneratorEditPart.VISUAL_ID:
        return new StringGeneratorEditPart(view);

      case StringGeneratorNameEditPart.VISUAL_ID:
        return new StringGeneratorNameEditPart(view);

      case RIAFeatureEditPart.VISUAL_ID:
        return new RIAFeatureEditPart(view);

      case RIAFeatureNameEditPart.VISUAL_ID:
        return new RIAFeatureNameEditPart(view);

      case PanelContainerEditPart.VISUAL_ID:
        return new PanelContainerEditPart(view);

      case ButtonEditPart.VISUAL_ID:
        return new ButtonEditPart(view);

      case ButtonNameEditPart.VISUAL_ID:
        return new ButtonNameEditPart(view);

      case CheckBoxEditPart.VISUAL_ID:
        return new CheckBoxEditPart(view);

      case CheckBoxNameEditPart.VISUAL_ID:
        return new CheckBoxNameEditPart(view);

      case ComboBoxEditPart.VISUAL_ID:
        return new ComboBoxEditPart(view);

      case ComboBoxNameEditPart.VISUAL_ID:
        return new ComboBoxNameEditPart(view);

      case LabelEditPart.VISUAL_ID:
        return new LabelEditPart(view);

      case LabelNameEditPart.VISUAL_ID:
        return new LabelNameEditPart(view);

      case LinkEditPart.VISUAL_ID:
        return new LinkEditPart(view);

      case LinkNameEditPart.VISUAL_ID:
        return new LinkNameEditPart(view);

      case ListBoxEditPart.VISUAL_ID:
        return new ListBoxEditPart(view);

      case ListBoxNameEditPart.VISUAL_ID:
        return new ListBoxNameEditPart(view);

      case RadioButtonEditPart.VISUAL_ID:
        return new RadioButtonEditPart(view);

      case RadioButtonNameEditPart.VISUAL_ID:
        return new RadioButtonNameEditPart(view);

      case TextFieldEditPart.VISUAL_ID:
        return new TextFieldEditPart(view);

      case TextFieldNameEditPart.VISUAL_ID:
        return new TextFieldNameEditPart(view);

      case PanelContainer2EditPart.VISUAL_ID:
        return new PanelContainer2EditPart(view);

      case ListOfContainerEditPart.VISUAL_ID:
        return new ListOfContainerEditPart(view);

      case PanelContainerPanelCompartmentEditPart.VISUAL_ID:
        return new PanelContainerPanelCompartmentEditPart(view);

      case PanelContainerPanelCompartment2EditPart.VISUAL_ID:
        return new PanelContainerPanelCompartment2EditPart(view);

      case ListOfContainerListOfCompartmentEditPart.VISUAL_ID:
        return new ListOfContainerListOfCompartmentEditPart(view);

      case NavigationEditPart.VISUAL_ID:
        return new NavigationEditPart(view);

      case NavigationNamePreconditionActionsEditPart.VISUAL_ID:
        return new NavigationNamePreconditionActionsEditPart(view);

      case RichBehaviorEditPart.VISUAL_ID:
        return new RichBehaviorEditPart(view);

      case RichBehaviorNamePreconditionActionsEditPart.VISUAL_ID:
        return new RichBehaviorNamePreconditionActionsEditPart(view);

      }
    }
    return createUnrecognizedEditPart(context, model);
  }

  /**
   * @generated
   */
  private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
    // Handle creation of unrecognized child node EditParts here
    return null;
  }

  /**
   * @generated
   */
  public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
    if (source.getFigure() instanceof WrappingLabel)
      return new TextCellEditorLocator((WrappingLabel) source.getFigure());
    else {
      return new LabelCellEditorLocator((Label) source.getFigure());
    }
  }

  /**
   * @generated
   */
  static private class TextCellEditorLocator implements CellEditorLocator {

    /**
     * @generated
     */
    private WrappingLabel wrapLabel;

    /**
     * @generated
     */
    public TextCellEditorLocator(WrappingLabel wrapLabel) {
      this.wrapLabel = wrapLabel;
    }

    /**
     * @generated
     */
    public WrappingLabel getWrapLabel() {
      return wrapLabel;
    }

    /**
     * @generated
     */
    public void relocate(CellEditor celleditor) {
      Text text = (Text) celleditor.getControl();
      Rectangle rect = getWrapLabel().getTextBounds().getCopy();
      getWrapLabel().translateToAbsolute(rect);
      if (getWrapLabel().isTextWrapOn() && getWrapLabel().getText().length() > 0) {
        rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
      } else {
        int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
        rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
      }
      if (!rect.equals(new Rectangle(text.getBounds()))) {
        text.setBounds(rect.x, rect.y, rect.width, rect.height);
      }
    }
  }

  /**
   * @generated
   */
  private static class LabelCellEditorLocator implements CellEditorLocator {

    /**
     * @generated
     */
    private Label label;

    /**
     * @generated
     */
    public LabelCellEditorLocator(Label label) {
      this.label = label;
    }

    /**
     * @generated
     */
    public Label getLabel() {
      return label;
    }

    /**
     * @generated
     */
    public void relocate(CellEditor celleditor) {
      Text text = (Text) celleditor.getControl();
      Rectangle rect = getLabel().getTextBounds().getCopy();
      getLabel().translateToAbsolute(rect);
      int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
      rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
      if (!rect.equals(new Rectangle(text.getBounds()))) {
        text.setBounds(rect.x, rect.y, rect.width, rect.height);
      }
    }
  }
}
