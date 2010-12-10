/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.webspeclanguage.plugin.webspecmodel.Button;
import org.webspeclanguage.plugin.webspecmodel.CheckBox;
import org.webspeclanguage.plugin.webspecmodel.ComboBox;
import org.webspeclanguage.plugin.webspecmodel.Container;
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
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;
import org.webspeclanguage.plugin.webspecmodel.Widget;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage
 * @generated
 */
public class WebspecmodelAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static WebspecmodelPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = WebspecmodelPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected WebspecmodelSwitch modelSwitch =
		new WebspecmodelSwitch() {
      public Object caseDiagram(Diagram object) {
        return createDiagramAdapter();
      }
      public Object caseInteraction(Interaction object) {
        return createInteractionAdapter();
      }
      public Object caseTransition(Transition object) {
        return createTransitionAdapter();
      }
      public Object caseWidget(Widget object) {
        return createWidgetAdapter();
      }
      public Object caseGenerator(Generator object) {
        return createGeneratorAdapter();
      }
      public Object caseButton(Button object) {
        return createButtonAdapter();
      }
      public Object caseTextField(TextField object) {
        return createTextFieldAdapter();
      }
      public Object caseRadioButton(RadioButton object) {
        return createRadioButtonAdapter();
      }
      public Object caseListBox(ListBox object) {
        return createListBoxAdapter();
      }
      public Object caseComboBox(ComboBox object) {
        return createComboBoxAdapter();
      }
      public Object caseCheckBox(CheckBox object) {
        return createCheckBoxAdapter();
      }
      public Object caseContainer(Container object) {
        return createContainerAdapter();
      }
      public Object caseOneOfStrings(OneOfStrings object) {
        return createOneOfStringsAdapter();
      }
      public Object caseOneOfNumbers(OneOfNumbers object) {
        return createOneOfNumbersAdapter();
      }
      public Object caseUniformNumberDistribution(UniformNumberDistribution object) {
        return createUniformNumberDistributionAdapter();
      }
      public Object caseLabel(Label object) {
        return createLabelAdapter();
      }
      public Object caseOneOfArray(OneOfArray object) {
        return createOneOfArrayAdapter();
      }
      public Object caseStringGenerator(StringGenerator object) {
        return createStringGeneratorAdapter();
      }
      public Object caseLink(Link object) {
        return createLinkAdapter();
      }
      public Object caseListOfContainer(ListOfContainer object) {
        return createListOfContainerAdapter();
      }
      public Object casePanelContainer(PanelContainer object) {
        return createPanelContainerAdapter();
      }
      public Object caseNavigation(Navigation object) {
        return createNavigationAdapter();
      }
      public Object caseRichBehavior(RichBehavior object) {
        return createRichBehaviorAdapter();
      }
      public Object caseRIAFeature(RIAFeature object) {
        return createRIAFeatureAdapter();
      }
      public Object defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	public Adapter createAdapter(Notifier target) {
    return (Adapter)modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Diagram <em>Diagram</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Diagram
   * @generated
   */
	public Adapter createDiagramAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Interaction <em>Interaction</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Interaction
   * @generated
   */
	public Adapter createInteractionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Transition <em>Transition</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Transition
   * @generated
   */
	public Adapter createTransitionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Navigation <em>Navigation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Navigation
   * @generated
   */
	public Adapter createNavigationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.RichBehavior <em>Rich Behavior</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.RichBehavior
   * @generated
   */
	public Adapter createRichBehaviorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.RIAFeature <em>RIA Feature</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.RIAFeature
   * @generated
   */
	public Adapter createRIAFeatureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Widget <em>Widget</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Widget
   * @generated
   */
	public Adapter createWidgetAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Generator <em>Generator</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Generator
   * @generated
   */
	public Adapter createGeneratorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Button <em>Button</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Button
   * @generated
   */
	public Adapter createButtonAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.TextField <em>Text Field</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.TextField
   * @generated
   */
	public Adapter createTextFieldAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.RadioButton <em>Radio Button</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.RadioButton
   * @generated
   */
	public Adapter createRadioButtonAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.ListBox <em>List Box</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.ListBox
   * @generated
   */
	public Adapter createListBoxAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.ComboBox <em>Combo Box</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.ComboBox
   * @generated
   */
	public Adapter createComboBoxAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.CheckBox <em>Check Box</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.CheckBox
   * @generated
   */
	public Adapter createCheckBoxAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Container <em>Container</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Container
   * @generated
   */
	public Adapter createContainerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.OneOfStrings <em>One Of Strings</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.OneOfStrings
   * @generated
   */
	public Adapter createOneOfStringsAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.OneOfNumbers <em>One Of Numbers</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.OneOfNumbers
   * @generated
   */
	public Adapter createOneOfNumbersAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution <em>Uniform Number Distribution</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution
   * @generated
   */
	public Adapter createUniformNumberDistributionAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Label <em>Label</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Label
   * @generated
   */
	public Adapter createLabelAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.OneOfArray <em>One Of Array</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.OneOfArray
   * @generated
   */
	public Adapter createOneOfArrayAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.StringGenerator <em>String Generator</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.StringGenerator
   * @generated
   */
	public Adapter createStringGeneratorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.Link <em>Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.Link
   * @generated
   */
	public Adapter createLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.ListOfContainer <em>List Of Container</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.ListOfContainer
   * @generated
   */
	public Adapter createListOfContainerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.webspeclanguage.plugin.webspecmodel.PanelContainer <em>Panel Container</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.webspeclanguage.plugin.webspecmodel.PanelContainer
   * @generated
   */
	public Adapter createPanelContainerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //WebspecmodelAdapterFactory
