package webspecplugin.webspecmodel.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Generator;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.ListOfContainer;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.RichBehavior;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.Widget;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;

/**
 * @generated
 */
public class WebspecmodelDiagramUpdater {

  /**
   * @generated
   */
  public static List getSemanticChildren(View view) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case InteractionEditPart.VISUAL_ID:
      return getInteraction_2001SemanticChildren(view);
    case PanelContainerPanelCompartmentEditPart.VISUAL_ID:
      return getPanelContainerPanelCompartment_7001SemanticChildren(view);
    case PanelContainerPanelCompartment2EditPart.VISUAL_ID:
      return getPanelContainerPanelCompartment_7002SemanticChildren(view);
    case ListOfContainerListOfCompartmentEditPart.VISUAL_ID:
      return getListOfContainerListOfCompartment_7003SemanticChildren(view);
    case DiagramEditPart.VISUAL_ID:
      return getDiagram_1000SemanticChildren(view);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getInteraction_2001SemanticChildren(View view) {
    if (!view.isSetElement()) {
      return Collections.EMPTY_LIST;
    }
    Interaction modelElement = (Interaction) view.getElement();
    List result = new LinkedList();
    {
      PanelContainer childElement = modelElement.getRoot();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == PanelContainerEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
      }
    }
    return result;
  }

  /**
   * @generated
   */
  public static List getPanelContainerPanelCompartment_7001SemanticChildren(View view) {
    if (false == view.eContainer() instanceof View) {
      return Collections.EMPTY_LIST;
    }
    View containerView = (View) view.eContainer();
    if (!containerView.isSetElement()) {
      return Collections.EMPTY_LIST;
    }
    PanelContainer modelElement = (PanelContainer) containerView.getElement();
    List result = new LinkedList();
    for (Iterator it = modelElement.getWidgets().iterator(); it.hasNext();) {
      Widget childElement = (Widget) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == ButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == CheckBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ComboBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LabelEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LinkEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == RadioButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == TextFieldEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == PanelContainer2EditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListOfContainerEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    return result;
  }

  /**
   * @generated
   */
  public static List getPanelContainerPanelCompartment_7002SemanticChildren(View view) {
    if (false == view.eContainer() instanceof View) {
      return Collections.EMPTY_LIST;
    }
    View containerView = (View) view.eContainer();
    if (!containerView.isSetElement()) {
      return Collections.EMPTY_LIST;
    }
    PanelContainer modelElement = (PanelContainer) containerView.getElement();
    List result = new LinkedList();
    for (Iterator it = modelElement.getWidgets().iterator(); it.hasNext();) {
      Widget childElement = (Widget) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == ButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == CheckBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ComboBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LabelEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LinkEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == RadioButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == TextFieldEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == PanelContainer2EditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListOfContainerEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    return result;
  }

  /**
   * @generated
   */
  public static List getListOfContainerListOfCompartment_7003SemanticChildren(View view) {
    if (false == view.eContainer() instanceof View) {
      return Collections.EMPTY_LIST;
    }
    View containerView = (View) view.eContainer();
    if (!containerView.isSetElement()) {
      return Collections.EMPTY_LIST;
    }
    ListOfContainer modelElement = (ListOfContainer) containerView.getElement();
    List result = new LinkedList();
    for (Iterator it = modelElement.getWidgets().iterator(); it.hasNext();) {
      Widget childElement = (Widget) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == ButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == CheckBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ComboBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LabelEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == LinkEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListBoxEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == RadioButtonEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == TextFieldEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == PanelContainer2EditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == ListOfContainerEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    return result;
  }

  /**
   * @generated
   */
  public static List getDiagram_1000SemanticChildren(View view) {
    if (!view.isSetElement()) {
      return Collections.EMPTY_LIST;
    }
    Diagram modelElement = (Diagram) view.getElement();
    List result = new LinkedList();
    for (Iterator it = modelElement.getInteractions().iterator(); it.hasNext();) {
      Interaction childElement = (Interaction) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == InteractionEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    for (Iterator it = modelElement.getGenerators().iterator(); it.hasNext();) {
      Generator childElement = (Generator) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == OneOfNumbersEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == OneOfStringsEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == UniformNumberDistributionEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == OneOfArrayEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
      if (visualID == StringGeneratorEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    for (Iterator it = modelElement.getRiaFeatures().iterator(); it.hasNext();) {
      RIAFeature childElement = (RIAFeature) it.next();
      int visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(view, childElement);
      if (visualID == RIAFeatureEditPart.VISUAL_ID) {
        result.add(new WebspecmodelNodeDescriptor(childElement, visualID));
        continue;
      }
    }
    return result;
  }

  /**
   * @generated
   */
  public static List getContainedLinks(View view) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case DiagramEditPart.VISUAL_ID:
      return getDiagram_1000ContainedLinks(view);
    case InteractionEditPart.VISUAL_ID:
      return getInteraction_2001ContainedLinks(view);
    case OneOfNumbersEditPart.VISUAL_ID:
      return getOneOfNumbers_2002ContainedLinks(view);
    case OneOfStringsEditPart.VISUAL_ID:
      return getOneOfStrings_2003ContainedLinks(view);
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return getUniformNumberDistribution_2004ContainedLinks(view);
    case OneOfArrayEditPart.VISUAL_ID:
      return getOneOfArray_2005ContainedLinks(view);
    case StringGeneratorEditPart.VISUAL_ID:
      return getStringGenerator_2006ContainedLinks(view);
    case RIAFeatureEditPart.VISUAL_ID:
      return getRIAFeature_2007ContainedLinks(view);
    case PanelContainerEditPart.VISUAL_ID:
      return getPanelContainer_3001ContainedLinks(view);
    case ButtonEditPart.VISUAL_ID:
      return getButton_3002ContainedLinks(view);
    case CheckBoxEditPart.VISUAL_ID:
      return getCheckBox_3003ContainedLinks(view);
    case ComboBoxEditPart.VISUAL_ID:
      return getComboBox_3004ContainedLinks(view);
    case LabelEditPart.VISUAL_ID:
      return getLabel_3005ContainedLinks(view);
    case LinkEditPart.VISUAL_ID:
      return getLink_3006ContainedLinks(view);
    case ListBoxEditPart.VISUAL_ID:
      return getListBox_3007ContainedLinks(view);
    case RadioButtonEditPart.VISUAL_ID:
      return getRadioButton_3008ContainedLinks(view);
    case TextFieldEditPart.VISUAL_ID:
      return getTextField_3009ContainedLinks(view);
    case PanelContainer2EditPart.VISUAL_ID:
      return getPanelContainer_3010ContainedLinks(view);
    case ListOfContainerEditPart.VISUAL_ID:
      return getListOfContainer_3011ContainedLinks(view);
    case NavigationEditPart.VISUAL_ID:
      return getNavigation_4001ContainedLinks(view);
    case RichBehaviorEditPart.VISUAL_ID:
      return getRichBehavior_4002ContainedLinks(view);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getIncomingLinks(View view) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case InteractionEditPart.VISUAL_ID:
      return getInteraction_2001IncomingLinks(view);
    case OneOfNumbersEditPart.VISUAL_ID:
      return getOneOfNumbers_2002IncomingLinks(view);
    case OneOfStringsEditPart.VISUAL_ID:
      return getOneOfStrings_2003IncomingLinks(view);
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return getUniformNumberDistribution_2004IncomingLinks(view);
    case OneOfArrayEditPart.VISUAL_ID:
      return getOneOfArray_2005IncomingLinks(view);
    case StringGeneratorEditPart.VISUAL_ID:
      return getStringGenerator_2006IncomingLinks(view);
    case RIAFeatureEditPart.VISUAL_ID:
      return getRIAFeature_2007IncomingLinks(view);
    case PanelContainerEditPart.VISUAL_ID:
      return getPanelContainer_3001IncomingLinks(view);
    case ButtonEditPart.VISUAL_ID:
      return getButton_3002IncomingLinks(view);
    case CheckBoxEditPart.VISUAL_ID:
      return getCheckBox_3003IncomingLinks(view);
    case ComboBoxEditPart.VISUAL_ID:
      return getComboBox_3004IncomingLinks(view);
    case LabelEditPart.VISUAL_ID:
      return getLabel_3005IncomingLinks(view);
    case LinkEditPart.VISUAL_ID:
      return getLink_3006IncomingLinks(view);
    case ListBoxEditPart.VISUAL_ID:
      return getListBox_3007IncomingLinks(view);
    case RadioButtonEditPart.VISUAL_ID:
      return getRadioButton_3008IncomingLinks(view);
    case TextFieldEditPart.VISUAL_ID:
      return getTextField_3009IncomingLinks(view);
    case PanelContainer2EditPart.VISUAL_ID:
      return getPanelContainer_3010IncomingLinks(view);
    case ListOfContainerEditPart.VISUAL_ID:
      return getListOfContainer_3011IncomingLinks(view);
    case NavigationEditPart.VISUAL_ID:
      return getNavigation_4001IncomingLinks(view);
    case RichBehaviorEditPart.VISUAL_ID:
      return getRichBehavior_4002IncomingLinks(view);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOutgoingLinks(View view) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case InteractionEditPart.VISUAL_ID:
      return getInteraction_2001OutgoingLinks(view);
    case OneOfNumbersEditPart.VISUAL_ID:
      return getOneOfNumbers_2002OutgoingLinks(view);
    case OneOfStringsEditPart.VISUAL_ID:
      return getOneOfStrings_2003OutgoingLinks(view);
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return getUniformNumberDistribution_2004OutgoingLinks(view);
    case OneOfArrayEditPart.VISUAL_ID:
      return getOneOfArray_2005OutgoingLinks(view);
    case StringGeneratorEditPart.VISUAL_ID:
      return getStringGenerator_2006OutgoingLinks(view);
    case RIAFeatureEditPart.VISUAL_ID:
      return getRIAFeature_2007OutgoingLinks(view);
    case PanelContainerEditPart.VISUAL_ID:
      return getPanelContainer_3001OutgoingLinks(view);
    case ButtonEditPart.VISUAL_ID:
      return getButton_3002OutgoingLinks(view);
    case CheckBoxEditPart.VISUAL_ID:
      return getCheckBox_3003OutgoingLinks(view);
    case ComboBoxEditPart.VISUAL_ID:
      return getComboBox_3004OutgoingLinks(view);
    case LabelEditPart.VISUAL_ID:
      return getLabel_3005OutgoingLinks(view);
    case LinkEditPart.VISUAL_ID:
      return getLink_3006OutgoingLinks(view);
    case ListBoxEditPart.VISUAL_ID:
      return getListBox_3007OutgoingLinks(view);
    case RadioButtonEditPart.VISUAL_ID:
      return getRadioButton_3008OutgoingLinks(view);
    case TextFieldEditPart.VISUAL_ID:
      return getTextField_3009OutgoingLinks(view);
    case PanelContainer2EditPart.VISUAL_ID:
      return getPanelContainer_3010OutgoingLinks(view);
    case ListOfContainerEditPart.VISUAL_ID:
      return getListOfContainer_3011OutgoingLinks(view);
    case NavigationEditPart.VISUAL_ID:
      return getNavigation_4001OutgoingLinks(view);
    case RichBehaviorEditPart.VISUAL_ID:
      return getRichBehavior_4002OutgoingLinks(view);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getDiagram_1000ContainedLinks(View view) {
    Diagram modelElement = (Diagram) view.getElement();
    List result = new LinkedList();
    result.addAll(getContainedTypeModelFacetLinks_Navigation_4001(modelElement));
    result.addAll(getContainedTypeModelFacetLinks_RichBehavior_4002(modelElement));
    return result;
  }

  /**
   * @generated
   */
  public static List getInteraction_2001ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfNumbers_2002ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfStrings_2003ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getUniformNumberDistribution_2004ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfArray_2005ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getStringGenerator_2006ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRIAFeature_2007ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3001ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getButton_3002ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getCheckBox_3003ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getComboBox_3004ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLabel_3005ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLink_3006ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListBox_3007ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRadioButton_3008ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getTextField_3009ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3010ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListOfContainer_3011ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getNavigation_4001ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRichBehavior_4002ContainedLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getInteraction_2001IncomingLinks(View view) {
    Interaction modelElement = (Interaction) view.getElement();
    Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
    List result = new LinkedList();
    result.addAll(getIncomingTypeModelFacetLinks_Navigation_4001(modelElement, crossReferences));
    result.addAll(getIncomingTypeModelFacetLinks_RichBehavior_4002(modelElement, crossReferences));
    return result;
  }

  /**
   * @generated
   */
  public static List getOneOfNumbers_2002IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfStrings_2003IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getUniformNumberDistribution_2004IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfArray_2005IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getStringGenerator_2006IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRIAFeature_2007IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3001IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getButton_3002IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getCheckBox_3003IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getComboBox_3004IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLabel_3005IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLink_3006IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListBox_3007IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRadioButton_3008IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getTextField_3009IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3010IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListOfContainer_3011IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getNavigation_4001IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRichBehavior_4002IncomingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getInteraction_2001OutgoingLinks(View view) {
    Interaction modelElement = (Interaction) view.getElement();
    List result = new LinkedList();
    result.addAll(getOutgoingTypeModelFacetLinks_Navigation_4001(modelElement));
    result.addAll(getOutgoingTypeModelFacetLinks_RichBehavior_4002(modelElement));
    return result;
  }

  /**
   * @generated
   */
  public static List getOneOfNumbers_2002OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfStrings_2003OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getUniformNumberDistribution_2004OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getOneOfArray_2005OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getStringGenerator_2006OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRIAFeature_2007OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3001OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getButton_3002OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getCheckBox_3003OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getComboBox_3004OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLabel_3005OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getLink_3006OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListBox_3007OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRadioButton_3008OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getTextField_3009OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getPanelContainer_3010OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getListOfContainer_3011OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getNavigation_4001OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public static List getRichBehavior_4002OutgoingLinks(View view) {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  private static Collection getContainedTypeModelFacetLinks_Navigation_4001(Diagram container) {
    Collection result = new LinkedList();
    for (Iterator links = container.getTransitions().iterator(); links.hasNext();) {
      EObject linkObject = (EObject) links.next();
      if (false == linkObject instanceof Navigation) {
        continue;
      }
      Navigation link = (Navigation) linkObject;
      if (NavigationEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction dst = link.getTargetInteraction();
      Interaction src = link.getSourceInteraction();
      result.add(new WebspecmodelLinkDescriptor(src, dst, link, WebspecmodelElementTypes.Navigation_4001, NavigationEditPart.VISUAL_ID));
    }
    return result;
  }

  /**
   * @generated
   */
  private static Collection getContainedTypeModelFacetLinks_RichBehavior_4002(Diagram container) {
    Collection result = new LinkedList();
    for (Iterator links = container.getTransitions().iterator(); links.hasNext();) {
      EObject linkObject = (EObject) links.next();
      if (false == linkObject instanceof RichBehavior) {
        continue;
      }
      RichBehavior link = (RichBehavior) linkObject;
      if (RichBehaviorEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction dst = link.getTargetInteraction();
      Interaction src = link.getSourceInteraction();
      result.add(new WebspecmodelLinkDescriptor(src, dst, link, WebspecmodelElementTypes.RichBehavior_4002, RichBehaviorEditPart.VISUAL_ID));
    }
    return result;
  }

  /**
   * @generated
   */
  private static Collection getIncomingTypeModelFacetLinks_Navigation_4001(Interaction target, Map crossReferences) {
    Collection result = new LinkedList();
    Collection settings = (Collection) crossReferences.get(target);
    for (Iterator it = settings.iterator(); it.hasNext();) {
      EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
      if (setting.getEStructuralFeature() != WebspecmodelPackage.eINSTANCE.getTransition_TargetInteraction()
              || false == setting.getEObject() instanceof Navigation) {
        continue;
      }
      Navigation link = (Navigation) setting.getEObject();
      if (NavigationEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction src = link.getSourceInteraction();
      result.add(new WebspecmodelLinkDescriptor(src, target, link, WebspecmodelElementTypes.Navigation_4001, NavigationEditPart.VISUAL_ID));
    }
    return result;
  }

  /**
   * @generated
   */
  private static Collection getIncomingTypeModelFacetLinks_RichBehavior_4002(Interaction target, Map crossReferences) {
    Collection result = new LinkedList();
    Collection settings = (Collection) crossReferences.get(target);
    for (Iterator it = settings.iterator(); it.hasNext();) {
      EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
      if (setting.getEStructuralFeature() != WebspecmodelPackage.eINSTANCE.getTransition_TargetInteraction()
              || false == setting.getEObject() instanceof RichBehavior) {
        continue;
      }
      RichBehavior link = (RichBehavior) setting.getEObject();
      if (RichBehaviorEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction src = link.getSourceInteraction();
      result.add(new WebspecmodelLinkDescriptor(src, target, link, WebspecmodelElementTypes.RichBehavior_4002, RichBehaviorEditPart.VISUAL_ID));
    }
    return result;
  }

  /**
   * @generated
   */
  private static Collection getOutgoingTypeModelFacetLinks_Navigation_4001(Interaction source) {
    Diagram container = null;
    // Find container element for the link.
    // Climb up by containment hierarchy starting from the source
    // and return the first element that is instance of the container class.
    for (EObject element = source; element != null && container == null; element = element.eContainer()) {
      if (element instanceof Diagram) {
        container = (Diagram) element;
      }
    }
    if (container == null) {
      return Collections.EMPTY_LIST;
    }
    Collection result = new LinkedList();
    for (Iterator links = container.getTransitions().iterator(); links.hasNext();) {
      EObject linkObject = (EObject) links.next();
      if (false == linkObject instanceof Navigation) {
        continue;
      }
      Navigation link = (Navigation) linkObject;
      if (NavigationEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction dst = link.getTargetInteraction();
      Interaction src = link.getSourceInteraction();
      if (src != source) {
        continue;
      }
      result.add(new WebspecmodelLinkDescriptor(src, dst, link, WebspecmodelElementTypes.Navigation_4001, NavigationEditPart.VISUAL_ID));
    }
    return result;
  }

  /**
   * @generated
   */
  private static Collection getOutgoingTypeModelFacetLinks_RichBehavior_4002(Interaction source) {
    Diagram container = null;
    // Find container element for the link.
    // Climb up by containment hierarchy starting from the source
    // and return the first element that is instance of the container class.
    for (EObject element = source; element != null && container == null; element = element.eContainer()) {
      if (element instanceof Diagram) {
        container = (Diagram) element;
      }
    }
    if (container == null) {
      return Collections.EMPTY_LIST;
    }
    Collection result = new LinkedList();
    for (Iterator links = container.getTransitions().iterator(); links.hasNext();) {
      EObject linkObject = (EObject) links.next();
      if (false == linkObject instanceof RichBehavior) {
        continue;
      }
      RichBehavior link = (RichBehavior) linkObject;
      if (RichBehaviorEditPart.VISUAL_ID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(link)) {
        continue;
      }
      Interaction dst = link.getTargetInteraction();
      Interaction src = link.getSourceInteraction();
      if (src != source) {
        continue;
      }
      result.add(new WebspecmodelLinkDescriptor(src, dst, link, WebspecmodelElementTypes.RichBehavior_4002, RichBehaviorEditPart.VISUAL_ID));
    }
    return result;
  }

}
