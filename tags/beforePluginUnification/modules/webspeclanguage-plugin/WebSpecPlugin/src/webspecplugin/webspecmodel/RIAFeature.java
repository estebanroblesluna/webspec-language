/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RIA Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.RIAFeature#getName <em>Name</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.RIAFeature#getInteractions <em>Interactions</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.RIAFeature#isVisible <em>Visible</em>}</li>
 * </ul>
 * </p>
 *
 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getRIAFeature()
 * @model
 * @generated
 */
public interface RIAFeature extends EObject {
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
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getRIAFeature_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link webspecplugin.webspecmodel.RIAFeature#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Interactions</b></em>' reference list.
	 * The list contents are of type {@link webspecplugin.webspecmodel.Interaction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interactions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interactions</em>' reference list.
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getRIAFeature_Interactions()
	 * @model type="webspecplugin.webspecmodel.Interaction"
	 * @generated
	 */
	EList getInteractions();

	/**
	 * Returns the value of the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible</em>' attribute.
	 * @see #setVisible(boolean)
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getRIAFeature_Visible()
	 * @model
	 * @generated
	 */
	boolean isVisible();

	/**
	 * Sets the value of the '{@link webspecplugin.webspecmodel.RIAFeature#isVisible <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visible</em>' attribute.
	 * @see #isVisible()
	 * @generated
	 */
	void setVisible(boolean value);

} // RIAFeature
