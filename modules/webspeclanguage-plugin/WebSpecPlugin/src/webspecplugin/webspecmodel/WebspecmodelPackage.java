/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see webspecplugin.webspecmodel.WebspecmodelFactory
 * @model kind="package"
 * @generated
 */
public interface WebspecmodelPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "webspecmodel";

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "ar.edu.unlp.info.lifia.webspec";

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "ar.edu.unlp.info.lifia.webspec";

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	WebspecmodelPackage eINSTANCE = webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl.init();

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.DiagramImpl <em>Diagram</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.DiagramImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getDiagram()
   * @generated
   */
	int DIAGRAM = 0;

	/**
   * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__TRANSITIONS = 0;

	/**
   * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__INTERACTIONS = 1;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__NAME = 2;

	/**
   * The feature id for the '<em><b>Generators</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__GENERATORS = 3;

	/**
   * The feature id for the '<em><b>Cycle Allowed</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__CYCLE_ALLOWED = 4;

	/**
   * The feature id for the '<em><b>Actions Setup</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__ACTIONS_SETUP = 5;

	/**
   * The feature id for the '<em><b>Ria Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM__RIA_FEATURES = 6;

	/**
   * The number of structural features of the '<em>Diagram</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DIAGRAM_FEATURE_COUNT = 7;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.InteractionImpl <em>Interaction</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.InteractionImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getInteraction()
   * @generated
   */
	int INTERACTION = 1;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__NAME = 0;

	/**
   * The feature id for the '<em><b>Forward Transitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__FORWARD_TRANSITIONS = 1;

	/**
   * The feature id for the '<em><b>Root</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__ROOT = 2;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__LOCATION = 3;

	/**
   * The feature id for the '<em><b>Invariant</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__INVARIANT = 4;

	/**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__TITLE = 5;

	/**
   * The feature id for the '<em><b>Starting</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__STARTING = 6;

	/**
   * The feature id for the '<em><b>Mockup File</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION__MOCKUP_FILE = 7;

	/**
   * The number of structural features of the '<em>Interaction</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FEATURE_COUNT = 8;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.TransitionImpl <em>Transition</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.TransitionImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getTransition()
   * @generated
   */
	int TRANSITION = 2;

	/**
   * The feature id for the '<em><b>Target Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSITION__TARGET_INTERACTION = 0;

	/**
   * The feature id for the '<em><b>Source Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSITION__SOURCE_INTERACTION = 1;

	/**
   * The feature id for the '<em><b>Precondition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSITION__PRECONDITION = 2;

	/**
   * The feature id for the '<em><b>Actions</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSITION__ACTIONS = 3;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__NAME = 4;

  /**
   * The number of structural features of the '<em>Transition</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSITION_FEATURE_COUNT = 5;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.NavigationImpl <em>Navigation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.NavigationImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getNavigation()
   * @generated
   */
	int NAVIGATION = 21;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.RichBehaviorImpl <em>Rich Behavior</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.RichBehaviorImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRichBehavior()
   * @generated
   */
	int RICH_BEHAVIOR = 22;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.RIAFeatureImpl <em>RIA Feature</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.RIAFeatureImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRIAFeature()
   * @generated
   */
	int RIA_FEATURE = 23;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.WidgetImpl <em>Widget</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.WidgetImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getWidget()
   * @generated
   */
	int WIDGET = 3;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int WIDGET__NAME = 0;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int WIDGET__LOCATION = 1;

	/**
   * The number of structural features of the '<em>Widget</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int WIDGET_FEATURE_COUNT = 2;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.GeneratorImpl <em>Generator</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.GeneratorImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getGenerator()
   * @generated
   */
	int GENERATOR = 4;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERATOR__NAME = 0;

	/**
   * The number of structural features of the '<em>Generator</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERATOR_FEATURE_COUNT = 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.ButtonImpl <em>Button</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.ButtonImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getButton()
   * @generated
   */
	int BUTTON = 5;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BUTTON__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BUTTON__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Button</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BUTTON_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.TextFieldImpl <em>Text Field</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.TextFieldImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getTextField()
   * @generated
   */
	int TEXT_FIELD = 6;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TEXT_FIELD__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TEXT_FIELD__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Text Field</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TEXT_FIELD_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.RadioButtonImpl <em>Radio Button</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.RadioButtonImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRadioButton()
   * @generated
   */
	int RADIO_BUTTON = 7;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RADIO_BUTTON__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RADIO_BUTTON__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Radio Button</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RADIO_BUTTON_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.ListBoxImpl <em>List Box</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.ListBoxImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getListBox()
   * @generated
   */
	int LIST_BOX = 8;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_BOX__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_BOX__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>List Box</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_BOX_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.ComboBoxImpl <em>Combo Box</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.ComboBoxImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getComboBox()
   * @generated
   */
	int COMBO_BOX = 9;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBO_BOX__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBO_BOX__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Combo Box</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBO_BOX_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.CheckBoxImpl <em>Check Box</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.CheckBoxImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getCheckBox()
   * @generated
   */
	int CHECK_BOX = 10;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHECK_BOX__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHECK_BOX__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Check Box</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CHECK_BOX_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.ContainerImpl <em>Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.ContainerImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getContainer()
   * @generated
   */
	int CONTAINER = 11;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTAINER__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTAINER__LOCATION = WIDGET__LOCATION;

	/**
   * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTAINER__WIDGETS = WIDGET_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTAINER_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.OneOfStringsImpl <em>One Of Strings</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.OneOfStringsImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfStrings()
   * @generated
   */
	int ONE_OF_STRINGS = 12;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_STRINGS__NAME = GENERATOR__NAME;

	/**
   * The feature id for the '<em><b>Strings</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_STRINGS__STRINGS = GENERATOR_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>One Of Strings</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_STRINGS_FEATURE_COUNT = GENERATOR_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.OneOfNumbersImpl <em>One Of Numbers</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.OneOfNumbersImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfNumbers()
   * @generated
   */
	int ONE_OF_NUMBERS = 13;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_NUMBERS__NAME = GENERATOR__NAME;

	/**
   * The feature id for the '<em><b>Numbers</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_NUMBERS__NUMBERS = GENERATOR_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>One Of Numbers</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_NUMBERS_FEATURE_COUNT = GENERATOR_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl <em>Uniform Number Distribution</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getUniformNumberDistribution()
   * @generated
   */
	int UNIFORM_NUMBER_DISTRIBUTION = 14;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIFORM_NUMBER_DISTRIBUTION__NAME = GENERATOR__NAME;

	/**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIFORM_NUMBER_DISTRIBUTION__MIN = GENERATOR_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIFORM_NUMBER_DISTRIBUTION__MAX = GENERATOR_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Uniform Number Distribution</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIFORM_NUMBER_DISTRIBUTION_FEATURE_COUNT = GENERATOR_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.LabelImpl <em>Label</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.LabelImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getLabel()
   * @generated
   */
	int LABEL = 15;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LABEL__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LABEL__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Label</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LABEL_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.OneOfArrayImpl <em>One Of Array</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.OneOfArrayImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfArray()
   * @generated
   */
	int ONE_OF_ARRAY = 16;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_ARRAY__NAME = GENERATOR__NAME;

	/**
   * The feature id for the '<em><b>Arrays</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_ARRAY__ARRAYS = GENERATOR_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>One Of Array</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ONE_OF_ARRAY_FEATURE_COUNT = GENERATOR_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.StringGeneratorImpl <em>String Generator</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.StringGeneratorImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getStringGenerator()
   * @generated
   */
	int STRING_GENERATOR = 17;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_GENERATOR__NAME = GENERATOR__NAME;

	/**
   * The feature id for the '<em><b>Max Length</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_GENERATOR__MAX_LENGTH = GENERATOR_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Min Length</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_GENERATOR__MIN_LENGTH = GENERATOR_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>String Generator</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_GENERATOR_FEATURE_COUNT = GENERATOR_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.LinkImpl <em>Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.LinkImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getLink()
   * @generated
   */
	int LINK = 18;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LINK__NAME = WIDGET__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LINK__LOCATION = WIDGET__LOCATION;

	/**
   * The number of structural features of the '<em>Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LINK_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.ListOfContainerImpl <em>List Of Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.ListOfContainerImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getListOfContainer()
   * @generated
   */
	int LIST_OF_CONTAINER = 19;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_OF_CONTAINER__NAME = CONTAINER__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_OF_CONTAINER__LOCATION = CONTAINER__LOCATION;

	/**
   * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_OF_CONTAINER__WIDGETS = CONTAINER__WIDGETS;

	/**
   * The feature id for the '<em><b>Index Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_OF_CONTAINER__INDEX_VARIABLE = CONTAINER_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>List Of Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LIST_OF_CONTAINER_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link webspecplugin.webspecmodel.impl.PanelContainerImpl <em>Panel Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see webspecplugin.webspecmodel.impl.PanelContainerImpl
   * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getPanelContainer()
   * @generated
   */
	int PANEL_CONTAINER = 20;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PANEL_CONTAINER__NAME = CONTAINER__NAME;

	/**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PANEL_CONTAINER__LOCATION = CONTAINER__LOCATION;

	/**
   * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PANEL_CONTAINER__WIDGETS = CONTAINER__WIDGETS;

	/**
   * The number of structural features of the '<em>Panel Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PANEL_CONTAINER_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION__TARGET_INTERACTION = TRANSITION__TARGET_INTERACTION;

	/**
   * The feature id for the '<em><b>Source Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION__SOURCE_INTERACTION = TRANSITION__SOURCE_INTERACTION;

	/**
   * The feature id for the '<em><b>Precondition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION__PRECONDITION = TRANSITION__PRECONDITION;

	/**
   * The feature id for the '<em><b>Actions</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION__ACTIONS = TRANSITION__ACTIONS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAVIGATION__NAME = TRANSITION__NAME;

  /**
   * The number of structural features of the '<em>Navigation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAVIGATION_FEATURE_COUNT = TRANSITION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RICH_BEHAVIOR__TARGET_INTERACTION = TRANSITION__TARGET_INTERACTION;

	/**
   * The feature id for the '<em><b>Source Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RICH_BEHAVIOR__SOURCE_INTERACTION = TRANSITION__SOURCE_INTERACTION;

	/**
   * The feature id for the '<em><b>Precondition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RICH_BEHAVIOR__PRECONDITION = TRANSITION__PRECONDITION;

	/**
   * The feature id for the '<em><b>Actions</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RICH_BEHAVIOR__ACTIONS = TRANSITION__ACTIONS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RICH_BEHAVIOR__NAME = TRANSITION__NAME;

  /**
   * The number of structural features of the '<em>Rich Behavior</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RICH_BEHAVIOR_FEATURE_COUNT = TRANSITION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RIA_FEATURE__NAME = 0;

	/**
   * The feature id for the '<em><b>Interactions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RIA_FEATURE__INTERACTIONS = 1;

	/**
   * The feature id for the '<em><b>Visible</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RIA_FEATURE__VISIBLE = 2;

	/**
   * The number of structural features of the '<em>RIA Feature</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RIA_FEATURE_FEATURE_COUNT = 3;

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Diagram <em>Diagram</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Diagram</em>'.
   * @see webspecplugin.webspecmodel.Diagram
   * @generated
   */
	EClass getDiagram();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Diagram#getTransitions <em>Transitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Transitions</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getTransitions()
   * @see #getDiagram()
   * @generated
   */
	EReference getDiagram_Transitions();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Diagram#getInteractions <em>Interactions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Interactions</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getInteractions()
   * @see #getDiagram()
   * @generated
   */
	EReference getDiagram_Interactions();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Diagram#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getName()
   * @see #getDiagram()
   * @generated
   */
	EAttribute getDiagram_Name();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Diagram#getGenerators <em>Generators</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Generators</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getGenerators()
   * @see #getDiagram()
   * @generated
   */
	EReference getDiagram_Generators();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Diagram#getCycleAllowed <em>Cycle Allowed</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cycle Allowed</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getCycleAllowed()
   * @see #getDiagram()
   * @generated
   */
	EAttribute getDiagram_CycleAllowed();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Diagram#getActionsSetup <em>Actions Setup</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Actions Setup</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getActionsSetup()
   * @see #getDiagram()
   * @generated
   */
	EAttribute getDiagram_ActionsSetup();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Diagram#getRiaFeatures <em>Ria Features</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ria Features</em>'.
   * @see webspecplugin.webspecmodel.Diagram#getRiaFeatures()
   * @see #getDiagram()
   * @generated
   */
	EReference getDiagram_RiaFeatures();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Interaction <em>Interaction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interaction</em>'.
   * @see webspecplugin.webspecmodel.Interaction
   * @generated
   */
	EClass getInteraction();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getName()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_Name();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Interaction#getForwardTransitions <em>Forward Transitions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Forward Transitions</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getForwardTransitions()
   * @see #getInteraction()
   * @generated
   */
	EReference getInteraction_ForwardTransitions();

	/**
   * Returns the meta object for the containment reference '{@link webspecplugin.webspecmodel.Interaction#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Root</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getRoot()
   * @see #getInteraction()
   * @generated
   */
	EReference getInteraction_Root();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getLocation()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_Location();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#getInvariant <em>Invariant</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Invariant</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getInvariant()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_Invariant();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getTitle()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_Title();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#isStarting <em>Starting</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Starting</em>'.
   * @see webspecplugin.webspecmodel.Interaction#isStarting()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_Starting();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Interaction#getMockupFile <em>Mockup File</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mockup File</em>'.
   * @see webspecplugin.webspecmodel.Interaction#getMockupFile()
   * @see #getInteraction()
   * @generated
   */
	EAttribute getInteraction_MockupFile();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Transition <em>Transition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Transition</em>'.
   * @see webspecplugin.webspecmodel.Transition
   * @generated
   */
	EClass getTransition();

	/**
   * Returns the meta object for the reference '{@link webspecplugin.webspecmodel.Transition#getTargetInteraction <em>Target Interaction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Interaction</em>'.
   * @see webspecplugin.webspecmodel.Transition#getTargetInteraction()
   * @see #getTransition()
   * @generated
   */
	EReference getTransition_TargetInteraction();

	/**
   * Returns the meta object for the reference '{@link webspecplugin.webspecmodel.Transition#getSourceInteraction <em>Source Interaction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Interaction</em>'.
   * @see webspecplugin.webspecmodel.Transition#getSourceInteraction()
   * @see #getTransition()
   * @generated
   */
	EReference getTransition_SourceInteraction();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Transition#getPrecondition <em>Precondition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Precondition</em>'.
   * @see webspecplugin.webspecmodel.Transition#getPrecondition()
   * @see #getTransition()
   * @generated
   */
	EAttribute getTransition_Precondition();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Transition#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Actions</em>'.
   * @see webspecplugin.webspecmodel.Transition#getActions()
   * @see #getTransition()
   * @generated
   */
	EAttribute getTransition_Actions();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Transition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.Transition#getName()
   * @see #getTransition()
   * @generated
   */
  EAttribute getTransition_Name();

  /**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Navigation <em>Navigation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Navigation</em>'.
   * @see webspecplugin.webspecmodel.Navigation
   * @generated
   */
	EClass getNavigation();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.RichBehavior <em>Rich Behavior</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rich Behavior</em>'.
   * @see webspecplugin.webspecmodel.RichBehavior
   * @generated
   */
	EClass getRichBehavior();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.RIAFeature <em>RIA Feature</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>RIA Feature</em>'.
   * @see webspecplugin.webspecmodel.RIAFeature
   * @generated
   */
	EClass getRIAFeature();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.RIAFeature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.RIAFeature#getName()
   * @see #getRIAFeature()
   * @generated
   */
	EAttribute getRIAFeature_Name();

	/**
   * Returns the meta object for the reference list '{@link webspecplugin.webspecmodel.RIAFeature#getInteractions <em>Interactions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Interactions</em>'.
   * @see webspecplugin.webspecmodel.RIAFeature#getInteractions()
   * @see #getRIAFeature()
   * @generated
   */
	EReference getRIAFeature_Interactions();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.RIAFeature#isVisible <em>Visible</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visible</em>'.
   * @see webspecplugin.webspecmodel.RIAFeature#isVisible()
   * @see #getRIAFeature()
   * @generated
   */
	EAttribute getRIAFeature_Visible();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Widget <em>Widget</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Widget</em>'.
   * @see webspecplugin.webspecmodel.Widget
   * @generated
   */
	EClass getWidget();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Widget#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.Widget#getName()
   * @see #getWidget()
   * @generated
   */
	EAttribute getWidget_Name();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Widget#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see webspecplugin.webspecmodel.Widget#getLocation()
   * @see #getWidget()
   * @generated
   */
	EAttribute getWidget_Location();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Generator <em>Generator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generator</em>'.
   * @see webspecplugin.webspecmodel.Generator
   * @generated
   */
	EClass getGenerator();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.Generator#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see webspecplugin.webspecmodel.Generator#getName()
   * @see #getGenerator()
   * @generated
   */
	EAttribute getGenerator_Name();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Button <em>Button</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Button</em>'.
   * @see webspecplugin.webspecmodel.Button
   * @generated
   */
	EClass getButton();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.TextField <em>Text Field</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Text Field</em>'.
   * @see webspecplugin.webspecmodel.TextField
   * @generated
   */
	EClass getTextField();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.RadioButton <em>Radio Button</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Radio Button</em>'.
   * @see webspecplugin.webspecmodel.RadioButton
   * @generated
   */
	EClass getRadioButton();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.ListBox <em>List Box</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>List Box</em>'.
   * @see webspecplugin.webspecmodel.ListBox
   * @generated
   */
	EClass getListBox();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.ComboBox <em>Combo Box</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Combo Box</em>'.
   * @see webspecplugin.webspecmodel.ComboBox
   * @generated
   */
	EClass getComboBox();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.CheckBox <em>Check Box</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Check Box</em>'.
   * @see webspecplugin.webspecmodel.CheckBox
   * @generated
   */
	EClass getCheckBox();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Container <em>Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Container</em>'.
   * @see webspecplugin.webspecmodel.Container
   * @generated
   */
	EClass getContainer();

	/**
   * Returns the meta object for the containment reference list '{@link webspecplugin.webspecmodel.Container#getWidgets <em>Widgets</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Widgets</em>'.
   * @see webspecplugin.webspecmodel.Container#getWidgets()
   * @see #getContainer()
   * @generated
   */
	EReference getContainer_Widgets();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.OneOfStrings <em>One Of Strings</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Of Strings</em>'.
   * @see webspecplugin.webspecmodel.OneOfStrings
   * @generated
   */
	EClass getOneOfStrings();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.OneOfStrings#getStrings <em>Strings</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Strings</em>'.
   * @see webspecplugin.webspecmodel.OneOfStrings#getStrings()
   * @see #getOneOfStrings()
   * @generated
   */
	EAttribute getOneOfStrings_Strings();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.OneOfNumbers <em>One Of Numbers</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Of Numbers</em>'.
   * @see webspecplugin.webspecmodel.OneOfNumbers
   * @generated
   */
	EClass getOneOfNumbers();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.OneOfNumbers#getNumbers <em>Numbers</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Numbers</em>'.
   * @see webspecplugin.webspecmodel.OneOfNumbers#getNumbers()
   * @see #getOneOfNumbers()
   * @generated
   */
	EAttribute getOneOfNumbers_Numbers();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.UniformNumberDistribution <em>Uniform Number Distribution</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Uniform Number Distribution</em>'.
   * @see webspecplugin.webspecmodel.UniformNumberDistribution
   * @generated
   */
	EClass getUniformNumberDistribution();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.UniformNumberDistribution#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see webspecplugin.webspecmodel.UniformNumberDistribution#getMin()
   * @see #getUniformNumberDistribution()
   * @generated
   */
	EAttribute getUniformNumberDistribution_Min();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.UniformNumberDistribution#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see webspecplugin.webspecmodel.UniformNumberDistribution#getMax()
   * @see #getUniformNumberDistribution()
   * @generated
   */
	EAttribute getUniformNumberDistribution_Max();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Label <em>Label</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Label</em>'.
   * @see webspecplugin.webspecmodel.Label
   * @generated
   */
	EClass getLabel();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.OneOfArray <em>One Of Array</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Of Array</em>'.
   * @see webspecplugin.webspecmodel.OneOfArray
   * @generated
   */
	EClass getOneOfArray();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.OneOfArray#getArrays <em>Arrays</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arrays</em>'.
   * @see webspecplugin.webspecmodel.OneOfArray#getArrays()
   * @see #getOneOfArray()
   * @generated
   */
	EAttribute getOneOfArray_Arrays();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.StringGenerator <em>String Generator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Generator</em>'.
   * @see webspecplugin.webspecmodel.StringGenerator
   * @generated
   */
	EClass getStringGenerator();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.StringGenerator#getMaxLength <em>Max Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Length</em>'.
   * @see webspecplugin.webspecmodel.StringGenerator#getMaxLength()
   * @see #getStringGenerator()
   * @generated
   */
	EAttribute getStringGenerator_MaxLength();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.StringGenerator#getMinLength <em>Min Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Length</em>'.
   * @see webspecplugin.webspecmodel.StringGenerator#getMinLength()
   * @see #getStringGenerator()
   * @generated
   */
	EAttribute getStringGenerator_MinLength();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.Link <em>Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link</em>'.
   * @see webspecplugin.webspecmodel.Link
   * @generated
   */
	EClass getLink();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.ListOfContainer <em>List Of Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>List Of Container</em>'.
   * @see webspecplugin.webspecmodel.ListOfContainer
   * @generated
   */
	EClass getListOfContainer();

	/**
   * Returns the meta object for the attribute '{@link webspecplugin.webspecmodel.ListOfContainer#getIndexVariable <em>Index Variable</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index Variable</em>'.
   * @see webspecplugin.webspecmodel.ListOfContainer#getIndexVariable()
   * @see #getListOfContainer()
   * @generated
   */
	EAttribute getListOfContainer_IndexVariable();

	/**
   * Returns the meta object for class '{@link webspecplugin.webspecmodel.PanelContainer <em>Panel Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Panel Container</em>'.
   * @see webspecplugin.webspecmodel.PanelContainer
   * @generated
   */
	EClass getPanelContainer();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	WebspecmodelFactory getWebspecmodelFactory();

	/**
   * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
   * @generated
   */
	interface Literals {
		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.DiagramImpl <em>Diagram</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.DiagramImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getDiagram()
     * @generated
     */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
     * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DIAGRAM__TRANSITIONS = eINSTANCE.getDiagram_Transitions();

		/**
     * The meta object literal for the '<em><b>Interactions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DIAGRAM__INTERACTIONS = eINSTANCE.getDiagram_Interactions();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

		/**
     * The meta object literal for the '<em><b>Generators</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DIAGRAM__GENERATORS = eINSTANCE.getDiagram_Generators();

		/**
     * The meta object literal for the '<em><b>Cycle Allowed</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DIAGRAM__CYCLE_ALLOWED = eINSTANCE.getDiagram_CycleAllowed();

		/**
     * The meta object literal for the '<em><b>Actions Setup</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DIAGRAM__ACTIONS_SETUP = eINSTANCE.getDiagram_ActionsSetup();

		/**
     * The meta object literal for the '<em><b>Ria Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DIAGRAM__RIA_FEATURES = eINSTANCE.getDiagram_RiaFeatures();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.InteractionImpl <em>Interaction</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.InteractionImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getInteraction()
     * @generated
     */
		EClass INTERACTION = eINSTANCE.getInteraction();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__NAME = eINSTANCE.getInteraction_Name();

		/**
     * The meta object literal for the '<em><b>Forward Transitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION__FORWARD_TRANSITIONS = eINSTANCE.getInteraction_ForwardTransitions();

		/**
     * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION__ROOT = eINSTANCE.getInteraction_Root();

		/**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__LOCATION = eINSTANCE.getInteraction_Location();

		/**
     * The meta object literal for the '<em><b>Invariant</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__INVARIANT = eINSTANCE.getInteraction_Invariant();

		/**
     * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__TITLE = eINSTANCE.getInteraction_Title();

		/**
     * The meta object literal for the '<em><b>Starting</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__STARTING = eINSTANCE.getInteraction_Starting();

		/**
     * The meta object literal for the '<em><b>Mockup File</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERACTION__MOCKUP_FILE = eINSTANCE.getInteraction_MockupFile();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.TransitionImpl <em>Transition</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.TransitionImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getTransition()
     * @generated
     */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
     * The meta object literal for the '<em><b>Target Interaction</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TRANSITION__TARGET_INTERACTION = eINSTANCE.getTransition_TargetInteraction();

		/**
     * The meta object literal for the '<em><b>Source Interaction</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TRANSITION__SOURCE_INTERACTION = eINSTANCE.getTransition_SourceInteraction();

		/**
     * The meta object literal for the '<em><b>Precondition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSITION__PRECONDITION = eINSTANCE.getTransition_Precondition();

		/**
     * The meta object literal for the '<em><b>Actions</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSITION__ACTIONS = eINSTANCE.getTransition_Actions();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSITION__NAME = eINSTANCE.getTransition_Name();

    /**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.NavigationImpl <em>Navigation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.NavigationImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getNavigation()
     * @generated
     */
		EClass NAVIGATION = eINSTANCE.getNavigation();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.RichBehaviorImpl <em>Rich Behavior</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.RichBehaviorImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRichBehavior()
     * @generated
     */
		EClass RICH_BEHAVIOR = eINSTANCE.getRichBehavior();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.RIAFeatureImpl <em>RIA Feature</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.RIAFeatureImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRIAFeature()
     * @generated
     */
		EClass RIA_FEATURE = eINSTANCE.getRIAFeature();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute RIA_FEATURE__NAME = eINSTANCE.getRIAFeature_Name();

		/**
     * The meta object literal for the '<em><b>Interactions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference RIA_FEATURE__INTERACTIONS = eINSTANCE.getRIAFeature_Interactions();

		/**
     * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute RIA_FEATURE__VISIBLE = eINSTANCE.getRIAFeature_Visible();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.WidgetImpl <em>Widget</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.WidgetImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getWidget()
     * @generated
     */
		EClass WIDGET = eINSTANCE.getWidget();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute WIDGET__NAME = eINSTANCE.getWidget_Name();

		/**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute WIDGET__LOCATION = eINSTANCE.getWidget_Location();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.GeneratorImpl <em>Generator</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.GeneratorImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getGenerator()
     * @generated
     */
		EClass GENERATOR = eINSTANCE.getGenerator();

		/**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute GENERATOR__NAME = eINSTANCE.getGenerator_Name();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.ButtonImpl <em>Button</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.ButtonImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getButton()
     * @generated
     */
		EClass BUTTON = eINSTANCE.getButton();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.TextFieldImpl <em>Text Field</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.TextFieldImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getTextField()
     * @generated
     */
		EClass TEXT_FIELD = eINSTANCE.getTextField();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.RadioButtonImpl <em>Radio Button</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.RadioButtonImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getRadioButton()
     * @generated
     */
		EClass RADIO_BUTTON = eINSTANCE.getRadioButton();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.ListBoxImpl <em>List Box</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.ListBoxImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getListBox()
     * @generated
     */
		EClass LIST_BOX = eINSTANCE.getListBox();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.ComboBoxImpl <em>Combo Box</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.ComboBoxImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getComboBox()
     * @generated
     */
		EClass COMBO_BOX = eINSTANCE.getComboBox();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.CheckBoxImpl <em>Check Box</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.CheckBoxImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getCheckBox()
     * @generated
     */
		EClass CHECK_BOX = eINSTANCE.getCheckBox();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.ContainerImpl <em>Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.ContainerImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getContainer()
     * @generated
     */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
     * The meta object literal for the '<em><b>Widgets</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONTAINER__WIDGETS = eINSTANCE.getContainer_Widgets();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.OneOfStringsImpl <em>One Of Strings</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.OneOfStringsImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfStrings()
     * @generated
     */
		EClass ONE_OF_STRINGS = eINSTANCE.getOneOfStrings();

		/**
     * The meta object literal for the '<em><b>Strings</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ONE_OF_STRINGS__STRINGS = eINSTANCE.getOneOfStrings_Strings();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.OneOfNumbersImpl <em>One Of Numbers</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.OneOfNumbersImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfNumbers()
     * @generated
     */
		EClass ONE_OF_NUMBERS = eINSTANCE.getOneOfNumbers();

		/**
     * The meta object literal for the '<em><b>Numbers</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ONE_OF_NUMBERS__NUMBERS = eINSTANCE.getOneOfNumbers_Numbers();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl <em>Uniform Number Distribution</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getUniformNumberDistribution()
     * @generated
     */
		EClass UNIFORM_NUMBER_DISTRIBUTION = eINSTANCE.getUniformNumberDistribution();

		/**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute UNIFORM_NUMBER_DISTRIBUTION__MIN = eINSTANCE.getUniformNumberDistribution_Min();

		/**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute UNIFORM_NUMBER_DISTRIBUTION__MAX = eINSTANCE.getUniformNumberDistribution_Max();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.LabelImpl <em>Label</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.LabelImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getLabel()
     * @generated
     */
		EClass LABEL = eINSTANCE.getLabel();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.OneOfArrayImpl <em>One Of Array</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.OneOfArrayImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getOneOfArray()
     * @generated
     */
		EClass ONE_OF_ARRAY = eINSTANCE.getOneOfArray();

		/**
     * The meta object literal for the '<em><b>Arrays</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ONE_OF_ARRAY__ARRAYS = eINSTANCE.getOneOfArray_Arrays();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.StringGeneratorImpl <em>String Generator</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.StringGeneratorImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getStringGenerator()
     * @generated
     */
		EClass STRING_GENERATOR = eINSTANCE.getStringGenerator();

		/**
     * The meta object literal for the '<em><b>Max Length</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STRING_GENERATOR__MAX_LENGTH = eINSTANCE.getStringGenerator_MaxLength();

		/**
     * The meta object literal for the '<em><b>Min Length</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STRING_GENERATOR__MIN_LENGTH = eINSTANCE.getStringGenerator_MinLength();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.LinkImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getLink()
     * @generated
     */
		EClass LINK = eINSTANCE.getLink();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.ListOfContainerImpl <em>List Of Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.ListOfContainerImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getListOfContainer()
     * @generated
     */
		EClass LIST_OF_CONTAINER = eINSTANCE.getListOfContainer();

		/**
     * The meta object literal for the '<em><b>Index Variable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LIST_OF_CONTAINER__INDEX_VARIABLE = eINSTANCE.getListOfContainer_IndexVariable();

		/**
     * The meta object literal for the '{@link webspecplugin.webspecmodel.impl.PanelContainerImpl <em>Panel Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see webspecplugin.webspecmodel.impl.PanelContainerImpl
     * @see webspecplugin.webspecmodel.impl.WebspecmodelPackageImpl#getPanelContainer()
     * @generated
     */
		EClass PANEL_CONTAINER = eINSTANCE.getPanelContainer();

	}

} //WebspecmodelPackage
