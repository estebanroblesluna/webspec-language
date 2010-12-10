/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.Container#getWidgets <em>Widgets</em>}</li>
 * </ul>
 * </p>
 *
 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends Widget {
	/**
	 * Returns the value of the '<em><b>Widgets</b></em>' containment reference list.
	 * The list contents are of type {@link webspecplugin.webspecmodel.Widget}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widgets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widgets</em>' containment reference list.
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getContainer_Widgets()
	 * @model type="webspecplugin.webspecmodel.Widget" containment="true"
	 * @generated
	 */
	EList getWidgets();

} // Container
