package webspecplugin.webspecmodel.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramEditorPlugin;

/**
 * @generated
 */
public class WebspecmodelElementTypes extends ElementInitializers {

  /**
   * @generated
   */
  private WebspecmodelElementTypes() {
  }

  /**
   * @generated
   */
  private static Map elements;

  /**
   * @generated
   */
  private static ImageRegistry imageRegistry;

  /**
   * @generated
   */
  private static Set KNOWN_ELEMENT_TYPES;

  /**
   * @generated
   */
  public static final IElementType Diagram_1000 = getElementType("WebSpecPlugin.diagram.Diagram_1000"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType Interaction_2001 = getElementType("WebSpecPlugin.diagram.Interaction_2001"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType OneOfNumbers_2002 = getElementType("WebSpecPlugin.diagram.OneOfNumbers_2002"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType OneOfStrings_2003 = getElementType("WebSpecPlugin.diagram.OneOfStrings_2003"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType UniformNumberDistribution_2004 = getElementType("WebSpecPlugin.diagram.UniformNumberDistribution_2004"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType OneOfArray_2005 = getElementType("WebSpecPlugin.diagram.OneOfArray_2005"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType StringGenerator_2006 = getElementType("WebSpecPlugin.diagram.StringGenerator_2006"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType RIAFeature_2007 = getElementType("WebSpecPlugin.diagram.RIAFeature_2007"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType PanelContainer_3001 = getElementType("WebSpecPlugin.diagram.PanelContainer_3001"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType Button_3002 = getElementType("WebSpecPlugin.diagram.Button_3002"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType CheckBox_3003 = getElementType("WebSpecPlugin.diagram.CheckBox_3003"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType ComboBox_3004 = getElementType("WebSpecPlugin.diagram.ComboBox_3004"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType Label_3005 = getElementType("WebSpecPlugin.diagram.Label_3005"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType Link_3006 = getElementType("WebSpecPlugin.diagram.Link_3006"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType ListBox_3007 = getElementType("WebSpecPlugin.diagram.ListBox_3007"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType RadioButton_3008 = getElementType("WebSpecPlugin.diagram.RadioButton_3008"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType TextField_3009 = getElementType("WebSpecPlugin.diagram.TextField_3009"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType PanelContainer_3010 = getElementType("WebSpecPlugin.diagram.PanelContainer_3010"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType ListOfContainer_3011 = getElementType("WebSpecPlugin.diagram.ListOfContainer_3011"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType Navigation_4001 = getElementType("WebSpecPlugin.diagram.Navigation_4001"); //$NON-NLS-1$

  /**
   * @generated
   */
  public static final IElementType RichBehavior_4002 = getElementType("WebSpecPlugin.diagram.RichBehavior_4002"); //$NON-NLS-1$

  /**
   * @generated
   */
  private static ImageRegistry getImageRegistry() {
    if (imageRegistry == null) {
      imageRegistry = new ImageRegistry();
    }
    return imageRegistry;
  }

  /**
   * @generated
   */
  private static String getImageRegistryKey(ENamedElement element) {
    return element.getName();
  }

  /**
   * @generated
   */
  private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
    if (element instanceof EStructuralFeature) {
      EStructuralFeature feature = ((EStructuralFeature) element);
      EClass eContainingClass = feature.getEContainingClass();
      EClassifier eType = feature.getEType();
      if (eContainingClass != null && !eContainingClass.isAbstract()) {
        element = eContainingClass;
      } else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
        element = eType;
      }
    }
    if (element instanceof EClass) {
      EClass eClass = (EClass) element;
      if (!eClass.isAbstract()) {
        return WebspecmodelDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
      }
    }
    // TODO : support structural features
    return null;
  }

  /**
   * @generated
   */
  public static ImageDescriptor getImageDescriptor(ENamedElement element) {
    String key = getImageRegistryKey(element);
    ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
    if (imageDescriptor == null) {
      imageDescriptor = getProvidedImageDescriptor(element);
      if (imageDescriptor == null) {
        imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
      }
      getImageRegistry().put(key, imageDescriptor);
    }
    return imageDescriptor;
  }

  /**
   * @generated
   */
  public static Image getImage(ENamedElement element) {
    String key = getImageRegistryKey(element);
    Image image = getImageRegistry().get(key);
    if (image == null) {
      ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
      if (imageDescriptor == null) {
        imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
      }
      getImageRegistry().put(key, imageDescriptor);
      image = getImageRegistry().get(key);
    }
    return image;
  }

  /**
   * @generated
   */
  public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
    ENamedElement element = getElement(hint);
    if (element == null) {
      return null;
    }
    return getImageDescriptor(element);
  }

  /**
   * @generated
   */
  public static Image getImage(IAdaptable hint) {
    ENamedElement element = getElement(hint);
    if (element == null) {
      return null;
    }
    return getImage(element);
  }

  /**
   * Returns 'type' of the ecore object associated with the hint.
   * 
   * @generated
   */
  public static ENamedElement getElement(IAdaptable hint) {
    Object type = hint.getAdapter(IElementType.class);
    if (elements == null) {
      elements = new IdentityHashMap();

      elements.put(Diagram_1000, WebspecmodelPackage.eINSTANCE.getDiagram());

      elements.put(Interaction_2001, WebspecmodelPackage.eINSTANCE.getInteraction());

      elements.put(OneOfNumbers_2002, WebspecmodelPackage.eINSTANCE.getOneOfNumbers());

      elements.put(OneOfStrings_2003, WebspecmodelPackage.eINSTANCE.getOneOfStrings());

      elements.put(UniformNumberDistribution_2004, WebspecmodelPackage.eINSTANCE.getUniformNumberDistribution());

      elements.put(OneOfArray_2005, WebspecmodelPackage.eINSTANCE.getOneOfArray());

      elements.put(StringGenerator_2006, WebspecmodelPackage.eINSTANCE.getStringGenerator());

      elements.put(RIAFeature_2007, WebspecmodelPackage.eINSTANCE.getRIAFeature());

      elements.put(PanelContainer_3001, WebspecmodelPackage.eINSTANCE.getPanelContainer());

      elements.put(Button_3002, WebspecmodelPackage.eINSTANCE.getButton());

      elements.put(CheckBox_3003, WebspecmodelPackage.eINSTANCE.getCheckBox());

      elements.put(ComboBox_3004, WebspecmodelPackage.eINSTANCE.getComboBox());

      elements.put(Label_3005, WebspecmodelPackage.eINSTANCE.getLabel());

      elements.put(Link_3006, WebspecmodelPackage.eINSTANCE.getLink());

      elements.put(ListBox_3007, WebspecmodelPackage.eINSTANCE.getListBox());

      elements.put(RadioButton_3008, WebspecmodelPackage.eINSTANCE.getRadioButton());

      elements.put(TextField_3009, WebspecmodelPackage.eINSTANCE.getTextField());

      elements.put(PanelContainer_3010, WebspecmodelPackage.eINSTANCE.getPanelContainer());

      elements.put(ListOfContainer_3011, WebspecmodelPackage.eINSTANCE.getListOfContainer());

      elements.put(Navigation_4001, WebspecmodelPackage.eINSTANCE.getNavigation());

      elements.put(RichBehavior_4002, WebspecmodelPackage.eINSTANCE.getRichBehavior());
    }
    return (ENamedElement) elements.get(type);
  }

  /**
   * @generated
   */
  private static IElementType getElementType(String id) {
    return ElementTypeRegistry.getInstance().getType(id);
  }

  /**
   * @generated
   */
  public static boolean isKnownElementType(IElementType elementType) {
    if (KNOWN_ELEMENT_TYPES == null) {
      KNOWN_ELEMENT_TYPES = new HashSet();
      KNOWN_ELEMENT_TYPES.add(Diagram_1000);
      KNOWN_ELEMENT_TYPES.add(Interaction_2001);
      KNOWN_ELEMENT_TYPES.add(OneOfNumbers_2002);
      KNOWN_ELEMENT_TYPES.add(OneOfStrings_2003);
      KNOWN_ELEMENT_TYPES.add(UniformNumberDistribution_2004);
      KNOWN_ELEMENT_TYPES.add(OneOfArray_2005);
      KNOWN_ELEMENT_TYPES.add(StringGenerator_2006);
      KNOWN_ELEMENT_TYPES.add(RIAFeature_2007);
      KNOWN_ELEMENT_TYPES.add(PanelContainer_3001);
      KNOWN_ELEMENT_TYPES.add(Button_3002);
      KNOWN_ELEMENT_TYPES.add(CheckBox_3003);
      KNOWN_ELEMENT_TYPES.add(ComboBox_3004);
      KNOWN_ELEMENT_TYPES.add(Label_3005);
      KNOWN_ELEMENT_TYPES.add(Link_3006);
      KNOWN_ELEMENT_TYPES.add(ListBox_3007);
      KNOWN_ELEMENT_TYPES.add(RadioButton_3008);
      KNOWN_ELEMENT_TYPES.add(TextField_3009);
      KNOWN_ELEMENT_TYPES.add(PanelContainer_3010);
      KNOWN_ELEMENT_TYPES.add(ListOfContainer_3011);
      KNOWN_ELEMENT_TYPES.add(Navigation_4001);
      KNOWN_ELEMENT_TYPES.add(RichBehavior_4002);
    }
    return KNOWN_ELEMENT_TYPES.contains(elementType);
  }

  /**
   * @generated
   */
  public static IElementType getElementType(int visualID) {
    switch (visualID) {
    case DiagramEditPart.VISUAL_ID:
      return Diagram_1000;
    case InteractionEditPart.VISUAL_ID:
      return Interaction_2001;
    case OneOfNumbersEditPart.VISUAL_ID:
      return OneOfNumbers_2002;
    case OneOfStringsEditPart.VISUAL_ID:
      return OneOfStrings_2003;
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return UniformNumberDistribution_2004;
    case OneOfArrayEditPart.VISUAL_ID:
      return OneOfArray_2005;
    case StringGeneratorEditPart.VISUAL_ID:
      return StringGenerator_2006;
    case RIAFeatureEditPart.VISUAL_ID:
      return RIAFeature_2007;
    case PanelContainerEditPart.VISUAL_ID:
      return PanelContainer_3001;
    case ButtonEditPart.VISUAL_ID:
      return Button_3002;
    case CheckBoxEditPart.VISUAL_ID:
      return CheckBox_3003;
    case ComboBoxEditPart.VISUAL_ID:
      return ComboBox_3004;
    case LabelEditPart.VISUAL_ID:
      return Label_3005;
    case LinkEditPart.VISUAL_ID:
      return Link_3006;
    case ListBoxEditPart.VISUAL_ID:
      return ListBox_3007;
    case RadioButtonEditPart.VISUAL_ID:
      return RadioButton_3008;
    case TextFieldEditPart.VISUAL_ID:
      return TextField_3009;
    case PanelContainer2EditPart.VISUAL_ID:
      return PanelContainer_3010;
    case ListOfContainerEditPart.VISUAL_ID:
      return ListOfContainer_3011;
    case NavigationEditPart.VISUAL_ID:
      return Navigation_4001;
    case RichBehaviorEditPart.VISUAL_ID:
      return RichBehavior_4002;
    }
    return null;
  }

}
