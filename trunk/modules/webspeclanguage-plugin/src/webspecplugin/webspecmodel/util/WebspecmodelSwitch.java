/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import webspecplugin.webspecmodel.Button;
import webspecplugin.webspecmodel.CheckBox;
import webspecplugin.webspecmodel.ComboBox;
import webspecplugin.webspecmodel.Container;
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
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.Widget;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see webspecplugin.webspecmodel.WebspecmodelPackage
 * @generated
 */
public class WebspecmodelSwitch {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static WebspecmodelPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelSwitch() {
    if (modelPackage == null) {
      modelPackage = WebspecmodelPackage.eINSTANCE;
    }
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	public Object doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else {
      List eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch((EClass)eSuperTypes.get(0), theEObject);
    }
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	protected Object doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case WebspecmodelPackage.DIAGRAM: {
        Diagram diagram = (Diagram)theEObject;
        Object result = caseDiagram(diagram);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.INTERACTION: {
        Interaction interaction = (Interaction)theEObject;
        Object result = caseInteraction(interaction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.TRANSITION: {
        Transition transition = (Transition)theEObject;
        Object result = caseTransition(transition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.WIDGET: {
        Widget widget = (Widget)theEObject;
        Object result = caseWidget(widget);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.GENERATOR: {
        Generator generator = (Generator)theEObject;
        Object result = caseGenerator(generator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.BUTTON: {
        Button button = (Button)theEObject;
        Object result = caseButton(button);
        if (result == null) result = caseWidget(button);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.TEXT_FIELD: {
        TextField textField = (TextField)theEObject;
        Object result = caseTextField(textField);
        if (result == null) result = caseWidget(textField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.RADIO_BUTTON: {
        RadioButton radioButton = (RadioButton)theEObject;
        Object result = caseRadioButton(radioButton);
        if (result == null) result = caseWidget(radioButton);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.LIST_BOX: {
        ListBox listBox = (ListBox)theEObject;
        Object result = caseListBox(listBox);
        if (result == null) result = caseWidget(listBox);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.COMBO_BOX: {
        ComboBox comboBox = (ComboBox)theEObject;
        Object result = caseComboBox(comboBox);
        if (result == null) result = caseWidget(comboBox);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.CHECK_BOX: {
        CheckBox checkBox = (CheckBox)theEObject;
        Object result = caseCheckBox(checkBox);
        if (result == null) result = caseWidget(checkBox);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.CONTAINER: {
        Container container = (Container)theEObject;
        Object result = caseContainer(container);
        if (result == null) result = caseWidget(container);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.ONE_OF_STRINGS: {
        OneOfStrings oneOfStrings = (OneOfStrings)theEObject;
        Object result = caseOneOfStrings(oneOfStrings);
        if (result == null) result = caseGenerator(oneOfStrings);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.ONE_OF_NUMBERS: {
        OneOfNumbers oneOfNumbers = (OneOfNumbers)theEObject;
        Object result = caseOneOfNumbers(oneOfNumbers);
        if (result == null) result = caseGenerator(oneOfNumbers);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION: {
        UniformNumberDistribution uniformNumberDistribution = (UniformNumberDistribution)theEObject;
        Object result = caseUniformNumberDistribution(uniformNumberDistribution);
        if (result == null) result = caseGenerator(uniformNumberDistribution);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.LABEL: {
        Label label = (Label)theEObject;
        Object result = caseLabel(label);
        if (result == null) result = caseWidget(label);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.ONE_OF_ARRAY: {
        OneOfArray oneOfArray = (OneOfArray)theEObject;
        Object result = caseOneOfArray(oneOfArray);
        if (result == null) result = caseGenerator(oneOfArray);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.STRING_GENERATOR: {
        StringGenerator stringGenerator = (StringGenerator)theEObject;
        Object result = caseStringGenerator(stringGenerator);
        if (result == null) result = caseGenerator(stringGenerator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.LINK: {
        Link link = (Link)theEObject;
        Object result = caseLink(link);
        if (result == null) result = caseWidget(link);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.LIST_OF_CONTAINER: {
        ListOfContainer listOfContainer = (ListOfContainer)theEObject;
        Object result = caseListOfContainer(listOfContainer);
        if (result == null) result = caseContainer(listOfContainer);
        if (result == null) result = caseWidget(listOfContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.PANEL_CONTAINER: {
        PanelContainer panelContainer = (PanelContainer)theEObject;
        Object result = casePanelContainer(panelContainer);
        if (result == null) result = caseContainer(panelContainer);
        if (result == null) result = caseWidget(panelContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.NAVIGATION: {
        Navigation navigation = (Navigation)theEObject;
        Object result = caseNavigation(navigation);
        if (result == null) result = caseTransition(navigation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.RICH_BEHAVIOR: {
        RichBehavior richBehavior = (RichBehavior)theEObject;
        Object result = caseRichBehavior(richBehavior);
        if (result == null) result = caseTransition(richBehavior);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WebspecmodelPackage.RIA_FEATURE: {
        RIAFeature riaFeature = (RIAFeature)theEObject;
        Object result = caseRIAFeature(riaFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseDiagram(Diagram object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interaction</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interaction</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseInteraction(Interaction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseTransition(Transition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Navigation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Navigation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseNavigation(Navigation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Rich Behavior</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Rich Behavior</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseRichBehavior(RichBehavior object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>RIA Feature</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>RIA Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseRIAFeature(RIAFeature object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Widget</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Widget</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseWidget(Widget object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Generator</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseGenerator(Generator object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Button</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Button</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseButton(Button object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Text Field</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseTextField(TextField object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Radio Button</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Radio Button</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseRadioButton(RadioButton object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>List Box</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>List Box</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseListBox(ListBox object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Combo Box</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Combo Box</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseComboBox(ComboBox object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Check Box</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Check Box</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseCheckBox(CheckBox object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseContainer(Container object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>One Of Strings</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Of Strings</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseOneOfStrings(OneOfStrings object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>One Of Numbers</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Of Numbers</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseOneOfNumbers(OneOfNumbers object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Uniform Number Distribution</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Uniform Number Distribution</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseUniformNumberDistribution(UniformNumberDistribution object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Label</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseLabel(Label object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>One Of Array</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Of Array</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseOneOfArray(OneOfArray object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>String Generator</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Generator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseStringGenerator(StringGenerator object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseLink(Link object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>List Of Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>List Of Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object caseListOfContainer(ListOfContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Panel Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Panel Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public Object casePanelContainer(PanelContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	public Object defaultCase(EObject object) {
    return null;
  }

} //WebspecmodelSwitch
