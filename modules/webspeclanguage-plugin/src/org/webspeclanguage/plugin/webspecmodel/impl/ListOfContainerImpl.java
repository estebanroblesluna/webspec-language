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
import org.webspeclanguage.plugin.webspecmodel.ListOfContainer;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Of Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.ListOfContainerImpl#getIndexVariable <em>Index Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ListOfContainerImpl extends ContainerImpl implements ListOfContainer {
	/**
	 * The default value of the '{@link #getIndexVariable() <em>Index Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String INDEX_VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIndexVariable() <em>Index Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexVariable()
	 * @generated
	 * @ordered
	 */
	protected String indexVariable = INDEX_VARIABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListOfContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.LIST_OF_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIndexVariable() {
		return indexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexVariable(String newIndexVariable) {
		String oldIndexVariable = indexVariable;
		indexVariable = newIndexVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.LIST_OF_CONTAINER__INDEX_VARIABLE, oldIndexVariable, indexVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebspecmodelPackage.LIST_OF_CONTAINER__INDEX_VARIABLE:
				return getIndexVariable();
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
			case WebspecmodelPackage.LIST_OF_CONTAINER__INDEX_VARIABLE:
				setIndexVariable((String)newValue);
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
			case WebspecmodelPackage.LIST_OF_CONTAINER__INDEX_VARIABLE:
				setIndexVariable(INDEX_VARIABLE_EDEFAULT);
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
			case WebspecmodelPackage.LIST_OF_CONTAINER__INDEX_VARIABLE:
				return INDEX_VARIABLE_EDEFAULT == null ? indexVariable != null : !INDEX_VARIABLE_EDEFAULT.equals(indexVariable);
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
		result.append(" (indexVariable: ");
		result.append(indexVariable);
		result.append(')');
		return result.toString();
	}

} //ListOfContainerImpl
