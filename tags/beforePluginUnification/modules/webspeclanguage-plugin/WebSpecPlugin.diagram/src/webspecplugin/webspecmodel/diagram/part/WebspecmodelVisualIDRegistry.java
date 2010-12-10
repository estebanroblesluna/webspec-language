package webspecplugin.webspecmodel.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationNamePreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationPreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorNamePreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorPreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.WrappingLabelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class WebspecmodelVisualIDRegistry {

  /**
   * @generated
   */
  private static final String DEBUG_KEY = "WebSpecPlugin.diagram/debug/visualID"; //$NON-NLS-1$

  /**
   * @generated
   */
  public static int getVisualID(View view) {
    if (view instanceof Diagram) {
      if (DiagramEditPart.MODEL_ID.equals(view.getType())) {
        return DiagramEditPart.VISUAL_ID;
      } else {
        return -1;
      }
    }
    return webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry.getVisualID(view.getType());
  }

  /**
   * @generated
   */
  public static String getModelID(View view) {
    View diagram = view.getDiagram();
    while (view != diagram) {
      EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
      if (annotation != null) {
        return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
      }
      view = (View) view.eContainer();
    }
    return diagram != null ? diagram.getType() : null;
  }

  /**
   * @generated
   */
  public static int getVisualID(String type) {
    try {
      return Integer.parseInt(type);
    } catch (NumberFormatException e) {
      if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
        WebspecmodelDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
      }
    }
    return -1;
  }

  /**
   * @generated
   */
  public static String getType(int visualID) {
    return String.valueOf(visualID);
  }

  /**
   * @generated
   */
  public static int getDiagramVisualID(EObject domainElement) {
    if (domainElement == null) {
      return -1;
    }
    if (WebspecmodelPackage.eINSTANCE.getDiagram().isSuperTypeOf(domainElement.eClass()) && isDiagram((webspecplugin.webspecmodel.Diagram) domainElement)) {
      return DiagramEditPart.VISUAL_ID;
    }
    return -1;
  }

  /**
   * @generated
   */
  public static int getNodeVisualID(View containerView, EObject domainElement) {
    if (domainElement == null) {
      return -1;
    }
    String containerModelID = webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry.getModelID(containerView);
    if (!DiagramEditPart.MODEL_ID.equals(containerModelID)) {
      return -1;
    }
    int containerVisualID;
    if (DiagramEditPart.MODEL_ID.equals(containerModelID)) {
      containerVisualID = webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry.getVisualID(containerView);
    } else {
      if (containerView instanceof Diagram) {
        containerVisualID = DiagramEditPart.VISUAL_ID;
      } else {
        return -1;
      }
    }
    switch (containerVisualID) {
    case InteractionEditPart.VISUAL_ID:
      if (WebspecmodelPackage.eINSTANCE.getPanelContainer().isSuperTypeOf(domainElement.eClass())) {
        return PanelContainerEditPart.VISUAL_ID;
      }
      break;
    case PanelContainerPanelCompartmentEditPart.VISUAL_ID:
      if (WebspecmodelPackage.eINSTANCE.getButton().isSuperTypeOf(domainElement.eClass())) {
        return ButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getCheckBox().isSuperTypeOf(domainElement.eClass())) {
        return CheckBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getComboBox().isSuperTypeOf(domainElement.eClass())) {
        return ComboBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLabel().isSuperTypeOf(domainElement.eClass())) {
        return LabelEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLink().isSuperTypeOf(domainElement.eClass())) {
        return LinkEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListBox().isSuperTypeOf(domainElement.eClass())) {
        return ListBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getRadioButton().isSuperTypeOf(domainElement.eClass())) {
        return RadioButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getTextField().isSuperTypeOf(domainElement.eClass())) {
        return TextFieldEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getPanelContainer().isSuperTypeOf(domainElement.eClass())) {
        return PanelContainer2EditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListOfContainer().isSuperTypeOf(domainElement.eClass())) {
        return ListOfContainerEditPart.VISUAL_ID;
      }
      break;
    case PanelContainerPanelCompartment2EditPart.VISUAL_ID:
      if (WebspecmodelPackage.eINSTANCE.getButton().isSuperTypeOf(domainElement.eClass())) {
        return ButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getCheckBox().isSuperTypeOf(domainElement.eClass())) {
        return CheckBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getComboBox().isSuperTypeOf(domainElement.eClass())) {
        return ComboBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLabel().isSuperTypeOf(domainElement.eClass())) {
        return LabelEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLink().isSuperTypeOf(domainElement.eClass())) {
        return LinkEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListBox().isSuperTypeOf(domainElement.eClass())) {
        return ListBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getRadioButton().isSuperTypeOf(domainElement.eClass())) {
        return RadioButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getTextField().isSuperTypeOf(domainElement.eClass())) {
        return TextFieldEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getPanelContainer().isSuperTypeOf(domainElement.eClass())) {
        return PanelContainer2EditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListOfContainer().isSuperTypeOf(domainElement.eClass())) {
        return ListOfContainerEditPart.VISUAL_ID;
      }
      break;
    case ListOfContainerListOfCompartmentEditPart.VISUAL_ID:
      if (WebspecmodelPackage.eINSTANCE.getButton().isSuperTypeOf(domainElement.eClass())) {
        return ButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getCheckBox().isSuperTypeOf(domainElement.eClass())) {
        return CheckBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getComboBox().isSuperTypeOf(domainElement.eClass())) {
        return ComboBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLabel().isSuperTypeOf(domainElement.eClass())) {
        return LabelEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getLink().isSuperTypeOf(domainElement.eClass())) {
        return LinkEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListBox().isSuperTypeOf(domainElement.eClass())) {
        return ListBoxEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getRadioButton().isSuperTypeOf(domainElement.eClass())) {
        return RadioButtonEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getTextField().isSuperTypeOf(domainElement.eClass())) {
        return TextFieldEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getPanelContainer().isSuperTypeOf(domainElement.eClass())) {
        return PanelContainer2EditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getListOfContainer().isSuperTypeOf(domainElement.eClass())) {
        return ListOfContainerEditPart.VISUAL_ID;
      }
      break;
    case DiagramEditPart.VISUAL_ID:
      if (WebspecmodelPackage.eINSTANCE.getInteraction().isSuperTypeOf(domainElement.eClass())) {
        return InteractionEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getOneOfNumbers().isSuperTypeOf(domainElement.eClass())) {
        return OneOfNumbersEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getOneOfStrings().isSuperTypeOf(domainElement.eClass())) {
        return OneOfStringsEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getUniformNumberDistribution().isSuperTypeOf(domainElement.eClass())) {
        return UniformNumberDistributionEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getOneOfArray().isSuperTypeOf(domainElement.eClass())) {
        return OneOfArrayEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getStringGenerator().isSuperTypeOf(domainElement.eClass())) {
        return StringGeneratorEditPart.VISUAL_ID;
      }
      if (WebspecmodelPackage.eINSTANCE.getRIAFeature().isSuperTypeOf(domainElement.eClass())) {
        return RIAFeatureEditPart.VISUAL_ID;
      }
      break;
    }
    return -1;
  }

  /**
   * @generated
   */
  public static boolean canCreateNode(View containerView, int nodeVisualID) {
    String containerModelID = webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry.getModelID(containerView);
    if (!DiagramEditPart.MODEL_ID.equals(containerModelID)) {
      return false;
    }
    int containerVisualID;
    if (DiagramEditPart.MODEL_ID.equals(containerModelID)) {
      containerVisualID = webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry.getVisualID(containerView);
    } else {
      if (containerView instanceof Diagram) {
        containerVisualID = DiagramEditPart.VISUAL_ID;
      } else {
        return false;
      }
    }
    switch (containerVisualID) {
    case InteractionEditPart.VISUAL_ID:
      if (InteractionNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (PanelContainerEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case OneOfNumbersEditPart.VISUAL_ID:
      if (OneOfNumbersNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case OneOfStringsEditPart.VISUAL_ID:
      if (OneOfStringsNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case UniformNumberDistributionEditPart.VISUAL_ID:
      if (UniformNumberDistributionNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case OneOfArrayEditPart.VISUAL_ID:
      if (OneOfArrayNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case StringGeneratorEditPart.VISUAL_ID:
      if (StringGeneratorNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case RIAFeatureEditPart.VISUAL_ID:
      if (RIAFeatureNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case PanelContainerEditPart.VISUAL_ID:
      if (PanelContainerPanelCompartmentEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case ButtonEditPart.VISUAL_ID:
      if (ButtonNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case CheckBoxEditPart.VISUAL_ID:
      if (CheckBoxNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case ComboBoxEditPart.VISUAL_ID:
      if (ComboBoxNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case LabelEditPart.VISUAL_ID:
      if (LabelNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case LinkEditPart.VISUAL_ID:
      if (LinkNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case ListBoxEditPart.VISUAL_ID:
      if (ListBoxNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case RadioButtonEditPart.VISUAL_ID:
      if (RadioButtonNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case TextFieldEditPart.VISUAL_ID:
      if (TextFieldNameEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case PanelContainer2EditPart.VISUAL_ID:
      if (PanelContainerPanelCompartment2EditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case ListOfContainerEditPart.VISUAL_ID:
      if (ListOfContainerListOfCompartmentEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case PanelContainerPanelCompartmentEditPart.VISUAL_ID:
      if (ButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (CheckBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ComboBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LabelEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LinkEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (RadioButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (TextFieldEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (PanelContainer2EditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListOfContainerEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case PanelContainerPanelCompartment2EditPart.VISUAL_ID:
      if (ButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (CheckBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ComboBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LabelEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LinkEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (RadioButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (TextFieldEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (PanelContainer2EditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListOfContainerEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case ListOfContainerListOfCompartmentEditPart.VISUAL_ID:
      if (ButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (CheckBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ComboBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LabelEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (LinkEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListBoxEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (RadioButtonEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (TextFieldEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (PanelContainer2EditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (ListOfContainerEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case DiagramEditPart.VISUAL_ID:
      if (InteractionEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (OneOfNumbersEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (OneOfStringsEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (UniformNumberDistributionEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (OneOfArrayEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (StringGeneratorEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      if (RIAFeatureEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case NavigationEditPart.VISUAL_ID:
      if (NavigationNamePreconditionActionsEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    case RichBehaviorEditPart.VISUAL_ID:
      if (RichBehaviorNamePreconditionActionsEditPart.VISUAL_ID == nodeVisualID) {
        return true;
      }
      break;
    }
    return false;
  }

  /**
   * @generated
   */
  public static int getLinkWithClassVisualID(EObject domainElement) {
    if (domainElement == null) {
      return -1;
    }
    if (WebspecmodelPackage.eINSTANCE.getNavigation().isSuperTypeOf(domainElement.eClass())) {
      return NavigationEditPart.VISUAL_ID;
    }
    if (WebspecmodelPackage.eINSTANCE.getRichBehavior().isSuperTypeOf(domainElement.eClass())) {
      return RichBehaviorEditPart.VISUAL_ID;
    }
    return -1;
  }

  /**
   * User can change implementation of this method to handle some specific
   * situations not covered by default logic.
   * 
   * @generated
   */
  private static boolean isDiagram(webspecplugin.webspecmodel.Diagram element) {
    return true;
  }

}
