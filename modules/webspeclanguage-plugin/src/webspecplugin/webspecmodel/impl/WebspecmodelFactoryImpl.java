/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import webspecplugin.webspecmodel.Button;
import webspecplugin.webspecmodel.CheckBox;
import webspecplugin.webspecmodel.ComboBox;
import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Generator;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Label;
import webspecplugin.webspecmodel.Link;
import webspecplugin.webspecmodel.ListBox;
import webspecplugin.webspecmodel.ListOfContainer;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.OneOfArray;
import webspecplugin.webspecmodel.OneOfNumbers;
import webspecplugin.webspecmodel.OneOfStrings;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.RadioButton;
import webspecplugin.webspecmodel.RichBehavior;
import webspecplugin.webspecmodel.StringGenerator;
import webspecplugin.webspecmodel.TextField;
import webspecplugin.webspecmodel.Transition;
import webspecplugin.webspecmodel.UniformNumberDistribution;
import webspecplugin.webspecmodel.WebspecmodelFactory;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.Widget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebspecmodelFactoryImpl extends EFactoryImpl implements WebspecmodelFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static WebspecmodelFactory init() {
    try {
      WebspecmodelFactory theWebspecmodelFactory = (WebspecmodelFactory)EPackage.Registry.INSTANCE.getEFactory("ar.edu.unlp.info.lifia.webspec"); 
      if (theWebspecmodelFactory != null) {
        return theWebspecmodelFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new WebspecmodelFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case WebspecmodelPackage.DIAGRAM: return createDiagram();
      case WebspecmodelPackage.INTERACTION: return createInteraction();
      case WebspecmodelPackage.TRANSITION: return createTransition();
      case WebspecmodelPackage.WIDGET: return createWidget();
      case WebspecmodelPackage.GENERATOR: return createGenerator();
      case WebspecmodelPackage.BUTTON: return createButton();
      case WebspecmodelPackage.TEXT_FIELD: return createTextField();
      case WebspecmodelPackage.RADIO_BUTTON: return createRadioButton();
      case WebspecmodelPackage.LIST_BOX: return createListBox();
      case WebspecmodelPackage.COMBO_BOX: return createComboBox();
      case WebspecmodelPackage.CHECK_BOX: return createCheckBox();
      case WebspecmodelPackage.CONTAINER: return createContainer();
      case WebspecmodelPackage.ONE_OF_STRINGS: return createOneOfStrings();
      case WebspecmodelPackage.ONE_OF_NUMBERS: return createOneOfNumbers();
      case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION: return createUniformNumberDistribution();
      case WebspecmodelPackage.LABEL: return createLabel();
      case WebspecmodelPackage.ONE_OF_ARRAY: return createOneOfArray();
      case WebspecmodelPackage.STRING_GENERATOR: return createStringGenerator();
      case WebspecmodelPackage.LINK: return createLink();
      case WebspecmodelPackage.LIST_OF_CONTAINER: return createListOfContainer();
      case WebspecmodelPackage.PANEL_CONTAINER: return createPanelContainer();
      case WebspecmodelPackage.NAVIGATION: return createNavigation();
      case WebspecmodelPackage.RICH_BEHAVIOR: return createRichBehavior();
      case WebspecmodelPackage.RIA_FEATURE: return createRIAFeature();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Diagram createDiagram() {
    DiagramImpl diagram = new DiagramImpl();
    return diagram;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Interaction createInteraction() {
    InteractionImpl interaction = new InteractionImpl();
    return interaction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Transition createTransition() {
    TransitionImpl transition = new TransitionImpl();
    return transition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Navigation createNavigation() {
    NavigationImpl navigation = new NavigationImpl();
    return navigation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RichBehavior createRichBehavior() {
    RichBehaviorImpl richBehavior = new RichBehaviorImpl();
    return richBehavior;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RIAFeature createRIAFeature() {
    RIAFeatureImpl riaFeature = new RIAFeatureImpl();
    return riaFeature;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Widget createWidget() {
    WidgetImpl widget = new WidgetImpl();
    return widget;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Generator createGenerator() {
    GeneratorImpl generator = new GeneratorImpl();
    return generator;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Button createButton() {
    ButtonImpl button = new ButtonImpl();
    return button;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TextField createTextField() {
    TextFieldImpl textField = new TextFieldImpl();
    return textField;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RadioButton createRadioButton() {
    RadioButtonImpl radioButton = new RadioButtonImpl();
    return radioButton;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ListBox createListBox() {
    ListBoxImpl listBox = new ListBoxImpl();
    return listBox;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ComboBox createComboBox() {
    ComboBoxImpl comboBox = new ComboBoxImpl();
    return comboBox;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CheckBox createCheckBox() {
    CheckBoxImpl checkBox = new CheckBoxImpl();
    return checkBox;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public webspecplugin.webspecmodel.Container createContainer() {
    ContainerImpl container = new ContainerImpl();
    return container;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OneOfStrings createOneOfStrings() {
    OneOfStringsImpl oneOfStrings = new OneOfStringsImpl();
    return oneOfStrings;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OneOfNumbers createOneOfNumbers() {
    OneOfNumbersImpl oneOfNumbers = new OneOfNumbersImpl();
    return oneOfNumbers;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public UniformNumberDistribution createUniformNumberDistribution() {
    UniformNumberDistributionImpl uniformNumberDistribution = new UniformNumberDistributionImpl();
    return uniformNumberDistribution;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Label createLabel() {
    LabelImpl label = new LabelImpl();
    return label;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OneOfArray createOneOfArray() {
    OneOfArrayImpl oneOfArray = new OneOfArrayImpl();
    return oneOfArray;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StringGenerator createStringGenerator() {
    StringGeneratorImpl stringGenerator = new StringGeneratorImpl();
    return stringGenerator;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Link createLink() {
    LinkImpl link = new LinkImpl();
    return link;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ListOfContainer createListOfContainer() {
    ListOfContainerImpl listOfContainer = new ListOfContainerImpl();
    return listOfContainer;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PanelContainer createPanelContainer() {
    PanelContainerImpl panelContainer = new PanelContainerImpl();
    return panelContainer;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelPackage getWebspecmodelPackage() {
    return (WebspecmodelPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	public static WebspecmodelPackage getPackage() {
    return WebspecmodelPackage.eINSTANCE;
  }

} //WebspecmodelFactoryImpl
