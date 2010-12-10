/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getInteractions <em>Interactions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getName <em>Name</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getCycleAllowed <em>Cycle Allowed</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getActionsSetup <em>Actions Setup</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getRiaFeatures <em>Ria Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.webspeclanguage.plugin.webspecmodel.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_Transitions()
	 * @model type="org.webspeclanguage.plugin.webspecmodel.Transition" containment="true"
	 * @generated
	 */
	EList getTransitions();

	/**
	 * Returns the value of the '<em><b>Interactions</b></em>' containment reference list.
	 * The list contents are of type {@link org.webspeclanguage.plugin.webspecmodel.Interaction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interactions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interactions</em>' containment reference list.
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_Interactions()
	 * @model type="org.webspeclanguage.plugin.webspecmodel.Interaction" containment="true"
	 * @generated
	 */
	EList getInteractions();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Generators</b></em>' containment reference list.
	 * The list contents are of type {@link org.webspeclanguage.plugin.webspecmodel.Generator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generators</em>' containment reference list.
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_Generators()
	 * @model type="org.webspeclanguage.plugin.webspecmodel.Generator" containment="true"
	 * @generated
	 */
	EList getGenerators();

	/**
	 * Returns the value of the '<em><b>Cycle Allowed</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cycle Allowed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cycle Allowed</em>' attribute.
	 * @see #setCycleAllowed(int)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_CycleAllowed()
	 * @model default="0"
	 * @generated
	 */
	int getCycleAllowed();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getCycleAllowed <em>Cycle Allowed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cycle Allowed</em>' attribute.
	 * @see #getCycleAllowed()
	 * @generated
	 */
	void setCycleAllowed(int value);

	/**
	 * Returns the value of the '<em><b>Actions Setup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions Setup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions Setup</em>' attribute.
	 * @see #setActionsSetup(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_ActionsSetup()
	 * @model
	 * @generated
	 */
	String getActionsSetup();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Diagram#getActionsSetup <em>Actions Setup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actions Setup</em>' attribute.
	 * @see #getActionsSetup()
	 * @generated
	 */
	void setActionsSetup(String value);

	/**
	 * Returns the value of the '<em><b>Ria Features</b></em>' containment reference list.
	 * The list contents are of type {@link org.webspeclanguage.plugin.webspecmodel.RIAFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ria Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ria Features</em>' containment reference list.
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getDiagram_RiaFeatures()
	 * @model type="org.webspeclanguage.plugin.webspecmodel.RIAFeature" containment="true"
	 * @generated
	 */
	EList getRiaFeatures();

} // Diagram
