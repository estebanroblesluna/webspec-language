/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import webspecplugin.webspecmodel.OneOfStrings;
import webspecplugin.webspecmodel.WebspecmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>One Of Strings</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.impl.OneOfStringsImpl#getStrings <em>Strings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OneOfStringsImpl extends GeneratorImpl implements OneOfStrings {
	/**
	 * The default value of the '{@link #getStrings() <em>Strings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrings()
	 * @generated
	 * @ordered
	 */
	protected static final String STRINGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStrings() <em>Strings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrings()
	 * @generated
	 * @ordered
	 */
	protected String strings = STRINGS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OneOfStringsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.ONE_OF_STRINGS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStrings() {
		return strings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrings(String newStrings) {
		String oldStrings = strings;
		strings = newStrings;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.ONE_OF_STRINGS__STRINGS, oldStrings, strings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebspecmodelPackage.ONE_OF_STRINGS__STRINGS:
				return getStrings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WebspecmodelPackage.ONE_OF_STRINGS__STRINGS:
				setStrings((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case WebspecmodelPackage.ONE_OF_STRINGS__STRINGS:
				setStrings(STRINGS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case WebspecmodelPackage.ONE_OF_STRINGS__STRINGS:
				return STRINGS_EDEFAULT == null ? strings != null : !STRINGS_EDEFAULT.equals(strings);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (strings: ");
		result.append(strings);
		result.append(')');
		return result.toString();
	}

} //OneOfStringsImpl
