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
 * A representation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getName <em>Name</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getForwardTransitions <em>Forward Transitions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getRoot <em>Root</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getLocation <em>Location</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getInvariant <em>Invariant</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getTitle <em>Title</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#isStarting <em>Starting</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getMockupFile <em>Mockup File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction()
 * @model
 * @generated
 */
public interface Interaction extends EObject {
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
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Forward Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.webspeclanguage.plugin.webspecmodel.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Transitions</em>' containment reference list.
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_ForwardTransitions()
	 * @model type="org.webspeclanguage.plugin.webspecmodel.Transition" containment="true"
	 * @generated
	 */
	EList getForwardTransitions();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(PanelContainer)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Root()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PanelContainer getRoot();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(PanelContainer value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Invariant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invariant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invariant</em>' attribute.
	 * @see #setInvariant(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Invariant()
	 * @model
	 * @generated
	 */
	String getInvariant();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getInvariant <em>Invariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invariant</em>' attribute.
	 * @see #getInvariant()
	 * @generated
	 */
	void setInvariant(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Starting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting</em>' attribute.
	 * @see #setStarting(boolean)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_Starting()
	 * @model
	 * @generated
	 */
	boolean isStarting();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#isStarting <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting</em>' attribute.
	 * @see #isStarting()
	 * @generated
	 */
	void setStarting(boolean value);

	/**
	 * Returns the value of the '<em><b>Mockup File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mockup File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mockup File</em>' attribute.
	 * @see #setMockupFile(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getInteraction_MockupFile()
	 * @model
	 * @generated
	 */
	String getMockupFile();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.Interaction#getMockupFile <em>Mockup File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mockup File</em>' attribute.
	 * @see #getMockupFile()
	 * @generated
	 */
	void setMockupFile(String value);

} // Interaction
