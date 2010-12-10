/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see webspecplugin.webspecmodel.WebspecmodelPackage
 * @generated
 */
public interface WebspecmodelFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	WebspecmodelFactory eINSTANCE = webspecplugin.webspecmodel.impl.WebspecmodelFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Diagram</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Diagram</em>'.
   * @generated
   */
	Diagram createDiagram();

	/**
   * Returns a new object of class '<em>Interaction</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Interaction</em>'.
   * @generated
   */
	Interaction createInteraction();

	/**
   * Returns a new object of class '<em>Transition</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Transition</em>'.
   * @generated
   */
	Transition createTransition();

	/**
   * Returns a new object of class '<em>Navigation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Navigation</em>'.
   * @generated
   */
	Navigation createNavigation();

	/**
   * Returns a new object of class '<em>Rich Behavior</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Rich Behavior</em>'.
   * @generated
   */
	RichBehavior createRichBehavior();

	/**
   * Returns a new object of class '<em>RIA Feature</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>RIA Feature</em>'.
   * @generated
   */
	RIAFeature createRIAFeature();

	/**
   * Returns a new object of class '<em>Widget</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Widget</em>'.
   * @generated
   */
	Widget createWidget();

	/**
   * Returns a new object of class '<em>Generator</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Generator</em>'.
   * @generated
   */
	Generator createGenerator();

	/**
   * Returns a new object of class '<em>Button</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Button</em>'.
   * @generated
   */
	Button createButton();

	/**
   * Returns a new object of class '<em>Text Field</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Text Field</em>'.
   * @generated
   */
	TextField createTextField();

	/**
   * Returns a new object of class '<em>Radio Button</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Radio Button</em>'.
   * @generated
   */
	RadioButton createRadioButton();

	/**
   * Returns a new object of class '<em>List Box</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>List Box</em>'.
   * @generated
   */
	ListBox createListBox();

	/**
   * Returns a new object of class '<em>Combo Box</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Combo Box</em>'.
   * @generated
   */
	ComboBox createComboBox();

	/**
   * Returns a new object of class '<em>Check Box</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Check Box</em>'.
   * @generated
   */
	CheckBox createCheckBox();

	/**
   * Returns a new object of class '<em>Container</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Container</em>'.
   * @generated
   */
	Container createContainer();

	/**
   * Returns a new object of class '<em>One Of Strings</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>One Of Strings</em>'.
   * @generated
   */
	OneOfStrings createOneOfStrings();

	/**
   * Returns a new object of class '<em>One Of Numbers</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>One Of Numbers</em>'.
   * @generated
   */
	OneOfNumbers createOneOfNumbers();

	/**
   * Returns a new object of class '<em>Uniform Number Distribution</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Uniform Number Distribution</em>'.
   * @generated
   */
	UniformNumberDistribution createUniformNumberDistribution();

	/**
   * Returns a new object of class '<em>Label</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Label</em>'.
   * @generated
   */
	Label createLabel();

	/**
   * Returns a new object of class '<em>One Of Array</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>One Of Array</em>'.
   * @generated
   */
	OneOfArray createOneOfArray();

	/**
   * Returns a new object of class '<em>String Generator</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>String Generator</em>'.
   * @generated
   */
	StringGenerator createStringGenerator();

	/**
   * Returns a new object of class '<em>Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Link</em>'.
   * @generated
   */
	Link createLink();

	/**
   * Returns a new object of class '<em>List Of Container</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>List Of Container</em>'.
   * @generated
   */
	ListOfContainer createListOfContainer();

	/**
   * Returns a new object of class '<em>Panel Container</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Panel Container</em>'.
   * @generated
   */
	PanelContainer createPanelContainer();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	WebspecmodelPackage getWebspecmodelPackage();

} //WebspecmodelFactory
