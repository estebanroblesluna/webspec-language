/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Of Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.ListOfContainer#getIndexVariable <em>Index Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getListOfContainer()
 * @model
 * @generated
 */
public interface ListOfContainer extends Container {
	/**
	 * Returns the value of the '<em><b>Index Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Variable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Variable</em>' attribute.
	 * @see #setIndexVariable(String)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getListOfContainer_IndexVariable()
	 * @model required="true"
	 * @generated
	 */
	String getIndexVariable();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.ListOfContainer#getIndexVariable <em>Index Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Variable</em>' attribute.
	 * @see #getIndexVariable()
	 * @generated
	 */
	void setIndexVariable(String value);

} // ListOfContainer
