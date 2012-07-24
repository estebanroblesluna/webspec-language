/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.webspeclanguage.plugin.webspecmodel.OneOfNumbers;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>One Of Numbers</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.OneOfNumbersImpl#getNumbers <em>Numbers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OneOfNumbersImpl extends GeneratorImpl implements OneOfNumbers {
	/**
	 * The default value of the '{@link #getNumbers() <em>Numbers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumbers()
	 * @generated
	 * @ordered
	 */
	protected static final String NUMBERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumbers() <em>Numbers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumbers()
	 * @generated
	 * @ordered
	 */
	protected String numbers = NUMBERS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OneOfNumbersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.ONE_OF_NUMBERS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNumbers() {
		return numbers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumbers(String newNumbers) {
		String oldNumbers = numbers;
		numbers = newNumbers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.ONE_OF_NUMBERS__NUMBERS, oldNumbers, numbers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebspecmodelPackage.ONE_OF_NUMBERS__NUMBERS:
				return getNumbers();
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
			case WebspecmodelPackage.ONE_OF_NUMBERS__NUMBERS:
				setNumbers((String)newValue);
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
			case WebspecmodelPackage.ONE_OF_NUMBERS__NUMBERS:
				setNumbers(NUMBERS_EDEFAULT);
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
			case WebspecmodelPackage.ONE_OF_NUMBERS__NUMBERS:
				return NUMBERS_EDEFAULT == null ? numbers != null : !NUMBERS_EDEFAULT.equals(numbers);
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
		result.append(" (numbers: ");
		result.append(numbers);
		result.append(')');
		return result.toString();
	}

} //OneOfNumbersImpl
