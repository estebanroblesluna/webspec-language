/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Transition#getTargetInteraction <em>Target Interaction</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Transition#getSourceInteraction <em>Source Interaction</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Transition#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Transition#getActions <em>Actions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Transition#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
	/**
   * Returns the value of the '<em><b>Target Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Interaction</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Interaction</em>' reference.
   * @see #setTargetInteraction(Interaction)
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition_TargetInteraction()
   * @model required="true"
   * @generated
   */
	Interaction getTargetInteraction();

	/**
   * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Transition#getTargetInteraction <em>Target Interaction</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Interaction</em>' reference.
   * @see #getTargetInteraction()
   * @generated
   */
	void setTargetInteraction(Interaction value);

	/**
   * Returns the value of the '<em><b>Source Interaction</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Interaction</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Interaction</em>' reference.
   * @see #setSourceInteraction(Interaction)
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition_SourceInteraction()
   * @model required="true"
   * @generated
   */
	Interaction getSourceInteraction();

	/**
   * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Transition#getSourceInteraction <em>Source Interaction</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Interaction</em>' reference.
   * @see #getSourceInteraction()
   * @generated
   */
	void setSourceInteraction(Interaction value);

	/**
   * Returns the value of the '<em><b>Precondition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Precondition</em>' attribute.
   * @see #setPrecondition(String)
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition_Precondition()
   * @model
   * @generated
   */
	String getPrecondition();

	/**
   * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Transition#getPrecondition <em>Precondition</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Precondition</em>' attribute.
   * @see #getPrecondition()
   * @generated
   */
	void setPrecondition(String value);

	/**
   * Returns the value of the '<em><b>Actions</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Actions</em>' attribute.
   * @see #setActions(String)
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition_Actions()
   * @model
   * @generated
   */
	String getActions();

	/**
   * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Transition#getActions <em>Actions</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actions</em>' attribute.
   * @see #getActions()
   * @generated
   */
	void setActions(String value);

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
   * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getTransition_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Transition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Transition
