/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.StringGenerator#getMaxLength <em>Max Length</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.StringGenerator#getMinLength <em>Min Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getStringGenerator()
 * @model
 * @generated
 */
public interface StringGenerator extends Generator {
	/**
	 * Returns the value of the '<em><b>Max Length</b></em>' attribute.
	 * The default value is <code>"25"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Length</em>' attribute.
	 * @see #setMaxLength(int)
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getStringGenerator_MaxLength()
	 * @model default="25"
	 * @generated
	 */
	int getMaxLength();

	/**
	 * Sets the value of the '{@link webspecplugin.webspecmodel.StringGenerator#getMaxLength <em>Max Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Length</em>' attribute.
	 * @see #getMaxLength()
	 * @generated
	 */
	void setMaxLength(int value);

	/**
	 * Returns the value of the '<em><b>Min Length</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Length</em>' attribute.
	 * @see #setMinLength(int)
	 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getStringGenerator_MinLength()
	 * @model default="1"
	 * @generated
	 */
	int getMinLength();

	/**
	 * Sets the value of the '{@link webspecplugin.webspecmodel.StringGenerator#getMinLength <em>Min Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Length</em>' attribute.
	 * @see #getMinLength()
	 * @generated
	 */
	void setMinLength(int value);

} // StringGenerator
