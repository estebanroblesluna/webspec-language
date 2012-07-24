/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.impl;

import static org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage.CONTAINER;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.webspeclanguage.plugin.webspecmodel.Button;
import org.webspeclanguage.plugin.webspecmodel.CheckBox;
import org.webspeclanguage.plugin.webspecmodel.ComboBox;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Generator;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.Label;
import org.webspeclanguage.plugin.webspecmodel.Link;
import org.webspeclanguage.plugin.webspecmodel.ListBox;
import org.webspeclanguage.plugin.webspecmodel.ListOfContainer;
import org.webspeclanguage.plugin.webspecmodel.Navigation;
import org.webspeclanguage.plugin.webspecmodel.OneOfArray;
import org.webspeclanguage.plugin.webspecmodel.OneOfNumbers;
import org.webspeclanguage.plugin.webspecmodel.OneOfStrings;
import org.webspeclanguage.plugin.webspecmodel.PanelContainer;
import org.webspeclanguage.plugin.webspecmodel.RIAFeature;
import org.webspeclanguage.plugin.webspecmodel.RadioButton;
import org.webspeclanguage.plugin.webspecmodel.RichBehavior;
import org.webspeclanguage.plugin.webspecmodel.StringGenerator;
import org.webspeclanguage.plugin.webspecmodel.TextField;
import org.webspeclanguage.plugin.webspecmodel.Transition;
import org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelFactory;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;
import org.webspeclanguage.plugin.webspecmodel.Widget;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebspecmodelPackageImpl extends EPackageImpl implements WebspecmodelPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass diagramEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interactionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass transitionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass navigationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass richBehaviorEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass riaFeatureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass widgetEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass generatorEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass buttonEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass textFieldEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass radioButtonEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass listBoxEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass comboBoxEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass checkBoxEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass containerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass oneOfStringsEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass oneOfNumbersEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass uniformNumberDistributionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass labelEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass oneOfArrayEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass stringGeneratorEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass linkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass listOfContainerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass panelContainerEClass = null;

	/**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private WebspecmodelPackageImpl() {
    super(eNS_URI, WebspecmodelFactory.eINSTANCE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static boolean isInited = false;

	/**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static WebspecmodelPackage init() {
    if (isInited) return (WebspecmodelPackage)EPackage.Registry.INSTANCE.getEPackage(WebspecmodelPackage.eNS_URI);

    // Obtain or create and register package
    WebspecmodelPackageImpl theWebspecmodelPackage = (WebspecmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof WebspecmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new WebspecmodelPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theWebspecmodelPackage.createPackageContents();

    // Initialize created meta-data
    theWebspecmodelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theWebspecmodelPackage.freeze();

    return theWebspecmodelPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDiagram() {
    return diagramEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDiagram_Transitions() {
    return (EReference)diagramEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDiagram_Interactions() {
    return (EReference)diagramEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDiagram_Name() {
    return (EAttribute)diagramEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDiagram_Generators() {
    return (EReference)diagramEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDiagram_CycleAllowed() {
    return (EAttribute)diagramEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDiagram_ActionsSetup() {
    return (EAttribute)diagramEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDiagram_RiaFeatures() {
    return (EReference)diagramEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getInteraction() {
    return interactionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_Name() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getInteraction_ForwardTransitions() {
    return (EReference)interactionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getInteraction_Root() {
    return (EReference)interactionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_Location() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_Invariant() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_Title() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_Starting() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getInteraction_MockupFile() {
    return (EAttribute)interactionEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTransition() {
    return transitionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTransition_TargetInteraction() {
    return (EReference)transitionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getTransition_SourceInteraction() {
    return (EReference)transitionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getTransition_Precondition() {
    return (EAttribute)transitionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getTransition_Actions() {
    return (EAttribute)transitionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTransition_Name() {
    return (EAttribute)transitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNavigation() {
    return navigationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRichBehavior() {
    return richBehaviorEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRIAFeature() {
    return riaFeatureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRIAFeature_Name() {
    return (EAttribute)riaFeatureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getRIAFeature_Interactions() {
    return (EReference)riaFeatureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRIAFeature_Visible() {
    return (EAttribute)riaFeatureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getWidget() {
    return widgetEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getWidget_Name() {
    return (EAttribute)widgetEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getWidget_Location() {
    return (EAttribute)widgetEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getGenerator() {
    return generatorEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getGenerator_Name() {
    return (EAttribute)generatorEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getButton() {
    return buttonEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getTextField() {
    return textFieldEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRadioButton() {
    return radioButtonEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getListBox() {
    return listBoxEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getComboBox() {
    return comboBoxEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getCheckBox() {
    return checkBoxEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getContainer() {
    return containerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getContainer_Widgets() {
    return (EReference)containerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOneOfStrings() {
    return oneOfStringsEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOneOfStrings_Strings() {
    return (EAttribute)oneOfStringsEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOneOfNumbers() {
    return oneOfNumbersEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOneOfNumbers_Numbers() {
    return (EAttribute)oneOfNumbersEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getUniformNumberDistribution() {
    return uniformNumberDistributionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUniformNumberDistribution_Min() {
    return (EAttribute)uniformNumberDistributionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUniformNumberDistribution_Max() {
    return (EAttribute)uniformNumberDistributionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLabel() {
    return labelEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getOneOfArray() {
    return oneOfArrayEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getOneOfArray_Arrays() {
    return (EAttribute)oneOfArrayEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getStringGenerator() {
    return stringGeneratorEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getStringGenerator_MaxLength() {
    return (EAttribute)stringGeneratorEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getStringGenerator_MinLength() {
    return (EAttribute)stringGeneratorEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getLink() {
    return linkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getListOfContainer() {
    return listOfContainerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getListOfContainer_IndexVariable() {
    return (EAttribute)listOfContainerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPanelContainer() {
    return panelContainerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelFactory getWebspecmodelFactory() {
    return (WebspecmodelFactory)getEFactoryInstance();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isCreated = false;

	/**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    diagramEClass = createEClass(DIAGRAM);
    createEReference(diagramEClass, DIAGRAM__TRANSITIONS);
    createEReference(diagramEClass, DIAGRAM__INTERACTIONS);
    createEAttribute(diagramEClass, DIAGRAM__NAME);
    createEReference(diagramEClass, DIAGRAM__GENERATORS);
    createEAttribute(diagramEClass, DIAGRAM__CYCLE_ALLOWED);
    createEAttribute(diagramEClass, DIAGRAM__ACTIONS_SETUP);
    createEReference(diagramEClass, DIAGRAM__RIA_FEATURES);

    interactionEClass = createEClass(INTERACTION);
    createEAttribute(interactionEClass, INTERACTION__NAME);
    createEReference(interactionEClass, INTERACTION__FORWARD_TRANSITIONS);
    createEReference(interactionEClass, INTERACTION__ROOT);
    createEAttribute(interactionEClass, INTERACTION__LOCATION);
    createEAttribute(interactionEClass, INTERACTION__INVARIANT);
    createEAttribute(interactionEClass, INTERACTION__TITLE);
    createEAttribute(interactionEClass, INTERACTION__STARTING);
    createEAttribute(interactionEClass, INTERACTION__MOCKUP_FILE);

    transitionEClass = createEClass(TRANSITION);
    createEReference(transitionEClass, TRANSITION__TARGET_INTERACTION);
    createEReference(transitionEClass, TRANSITION__SOURCE_INTERACTION);
    createEAttribute(transitionEClass, TRANSITION__PRECONDITION);
    createEAttribute(transitionEClass, TRANSITION__ACTIONS);
    createEAttribute(transitionEClass, TRANSITION__NAME);

    widgetEClass = createEClass(WIDGET);
    createEAttribute(widgetEClass, WIDGET__NAME);
    createEAttribute(widgetEClass, WIDGET__LOCATION);

    generatorEClass = createEClass(GENERATOR);
    createEAttribute(generatorEClass, GENERATOR__NAME);

    buttonEClass = createEClass(BUTTON);

    textFieldEClass = createEClass(TEXT_FIELD);

    radioButtonEClass = createEClass(RADIO_BUTTON);

    listBoxEClass = createEClass(LIST_BOX);

    comboBoxEClass = createEClass(COMBO_BOX);

    checkBoxEClass = createEClass(CHECK_BOX);

    containerEClass = createEClass(CONTAINER);
    createEReference(containerEClass, CONTAINER__WIDGETS);

    oneOfStringsEClass = createEClass(ONE_OF_STRINGS);
    createEAttribute(oneOfStringsEClass, ONE_OF_STRINGS__STRINGS);

    oneOfNumbersEClass = createEClass(ONE_OF_NUMBERS);
    createEAttribute(oneOfNumbersEClass, ONE_OF_NUMBERS__NUMBERS);

    uniformNumberDistributionEClass = createEClass(UNIFORM_NUMBER_DISTRIBUTION);
    createEAttribute(uniformNumberDistributionEClass, UNIFORM_NUMBER_DISTRIBUTION__MIN);
    createEAttribute(uniformNumberDistributionEClass, UNIFORM_NUMBER_DISTRIBUTION__MAX);

    labelEClass = createEClass(LABEL);

    oneOfArrayEClass = createEClass(ONE_OF_ARRAY);
    createEAttribute(oneOfArrayEClass, ONE_OF_ARRAY__ARRAYS);

    stringGeneratorEClass = createEClass(STRING_GENERATOR);
    createEAttribute(stringGeneratorEClass, STRING_GENERATOR__MAX_LENGTH);
    createEAttribute(stringGeneratorEClass, STRING_GENERATOR__MIN_LENGTH);

    linkEClass = createEClass(LINK);

    listOfContainerEClass = createEClass(LIST_OF_CONTAINER);
    createEAttribute(listOfContainerEClass, LIST_OF_CONTAINER__INDEX_VARIABLE);

    panelContainerEClass = createEClass(PANEL_CONTAINER);

    navigationEClass = createEClass(NAVIGATION);

    richBehaviorEClass = createEClass(RICH_BEHAVIOR);

    riaFeatureEClass = createEClass(RIA_FEATURE);
    createEAttribute(riaFeatureEClass, RIA_FEATURE__NAME);
    createEReference(riaFeatureEClass, RIA_FEATURE__INTERACTIONS);
    createEAttribute(riaFeatureEClass, RIA_FEATURE__VISIBLE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isInitialized = false;

	/**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Add supertypes to classes
    buttonEClass.getESuperTypes().add(this.getWidget());
    textFieldEClass.getESuperTypes().add(this.getWidget());
    radioButtonEClass.getESuperTypes().add(this.getWidget());
    listBoxEClass.getESuperTypes().add(this.getWidget());
    comboBoxEClass.getESuperTypes().add(this.getWidget());
    checkBoxEClass.getESuperTypes().add(this.getWidget());
    containerEClass.getESuperTypes().add(this.getWidget());
    oneOfStringsEClass.getESuperTypes().add(this.getGenerator());
    oneOfNumbersEClass.getESuperTypes().add(this.getGenerator());
    uniformNumberDistributionEClass.getESuperTypes().add(this.getGenerator());
    labelEClass.getESuperTypes().add(this.getWidget());
    oneOfArrayEClass.getESuperTypes().add(this.getGenerator());
    stringGeneratorEClass.getESuperTypes().add(this.getGenerator());
    linkEClass.getESuperTypes().add(this.getWidget());
    listOfContainerEClass.getESuperTypes().add(this.getContainer());
    panelContainerEClass.getESuperTypes().add(this.getContainer());
    navigationEClass.getESuperTypes().add(this.getTransition());
    richBehaviorEClass.getESuperTypes().add(this.getTransition());

    // Initialize classes and features; add operations and parameters
    initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDiagram_Transitions(), this.getTransition(), null, "transitions", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagram_Interactions(), this.getInteraction(), null, "interactions", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagram_Name(), ecorePackage.getEString(), "name", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagram_Generators(), this.getGenerator(), null, "generators", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagram_CycleAllowed(), ecorePackage.getEInt(), "cycleAllowed", "0", 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagram_ActionsSetup(), ecorePackage.getEString(), "actionsSetup", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagram_RiaFeatures(), this.getRIAFeature(), null, "riaFeatures", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInteraction_Name(), ecorePackage.getEString(), "name", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInteraction_ForwardTransitions(), this.getTransition(), null, "forwardTransitions", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInteraction_Root(), this.getPanelContainer(), null, "root", null, 1, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInteraction_Location(), ecorePackage.getEString(), "location", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInteraction_Invariant(), ecorePackage.getEString(), "invariant", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInteraction_Title(), ecorePackage.getEString(), "title", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInteraction_Starting(), ecorePackage.getEBoolean(), "starting", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInteraction_MockupFile(), ecorePackage.getEString(), "mockupFile", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTransition_TargetInteraction(), this.getInteraction(), null, "targetInteraction", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTransition_SourceInteraction(), this.getInteraction(), null, "sourceInteraction", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransition_Precondition(), ecorePackage.getEString(), "precondition", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransition_Actions(), ecorePackage.getEString(), "actions", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransition_Name(), ecorePackage.getEString(), "name", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(widgetEClass, Widget.class, "Widget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWidget_Name(), ecorePackage.getEString(), "name", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWidget_Location(), ecorePackage.getEString(), "location", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(generatorEClass, Generator.class, "Generator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenerator_Name(), ecorePackage.getEString(), "name", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(buttonEClass, Button.class, "Button", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(textFieldEClass, TextField.class, "TextField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(radioButtonEClass, RadioButton.class, "RadioButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(listBoxEClass, ListBox.class, "ListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(comboBoxEClass, ComboBox.class, "ComboBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(checkBoxEClass, CheckBox.class, "CheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(containerEClass, Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getContainer_Widgets(), this.getWidget(), null, "widgets", null, 0, -1, Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(oneOfStringsEClass, OneOfStrings.class, "OneOfStrings", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOneOfStrings_Strings(), ecorePackage.getEString(), "strings", null, 0, 1, OneOfStrings.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(oneOfNumbersEClass, OneOfNumbers.class, "OneOfNumbers", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOneOfNumbers_Numbers(), ecorePackage.getEString(), "numbers", null, 0, 1, OneOfNumbers.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(uniformNumberDistributionEClass, UniformNumberDistribution.class, "UniformNumberDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUniformNumberDistribution_Min(), ecorePackage.getEBigDecimal(), "min", "0", 0, 1, UniformNumberDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getUniformNumberDistribution_Max(), ecorePackage.getEBigDecimal(), "max", "9999", 0, 1, UniformNumberDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(oneOfArrayEClass, OneOfArray.class, "OneOfArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOneOfArray_Arrays(), ecorePackage.getEString(), "arrays", null, 0, 1, OneOfArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(stringGeneratorEClass, StringGenerator.class, "StringGenerator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStringGenerator_MaxLength(), ecorePackage.getEInt(), "maxLength", "25", 0, 1, StringGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getStringGenerator_MinLength(), ecorePackage.getEInt(), "minLength", "1", 0, 1, StringGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(listOfContainerEClass, ListOfContainer.class, "ListOfContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getListOfContainer_IndexVariable(), ecorePackage.getEString(), "indexVariable", null, 1, 1, ListOfContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(panelContainerEClass, PanelContainer.class, "PanelContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(navigationEClass, Navigation.class, "Navigation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(richBehaviorEClass, RichBehavior.class, "RichBehavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(riaFeatureEClass, RIAFeature.class, "RIAFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRIAFeature_Name(), ecorePackage.getEString(), "name", null, 0, 1, RIAFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRIAFeature_Interactions(), this.getInteraction(), null, "interactions", null, 0, -1, RIAFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRIAFeature_Visible(), ecorePackage.getEBoolean(), "visible", null, 0, 1, RIAFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //WebspecmodelPackageImpl
