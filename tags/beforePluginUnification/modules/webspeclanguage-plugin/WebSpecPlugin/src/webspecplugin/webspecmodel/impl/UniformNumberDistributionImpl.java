/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.impl;

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import webspecplugin.webspecmodel.UniformNumberDistribution;
import webspecplugin.webspecmodel.WebspecmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uniform Number Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl#getMin <em>Min</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.UniformNumberDistributionImpl#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniformNumberDistributionImpl extends GeneratorImpl implements UniformNumberDistribution {
	/**
	 * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal MIN_EDEFAULT = new BigDecimal("0");

	/**
	 * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal min = MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal MAX_EDEFAULT = new BigDecimal("9999");

	/**
	 * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal max = MAX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniformNumberDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.UNIFORM_NUMBER_DISTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getMin() {
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMin(BigDecimal newMin) {
		BigDecimal oldMin = min;
		min = newMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MIN, oldMin, min));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getMax() {
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMax(BigDecimal newMax) {
		BigDecimal oldMax = max;
		max = newMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MAX, oldMax, max));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MIN:
				return getMin();
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MAX:
				return getMax();
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
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MIN:
				setMin((BigDecimal)newValue);
				return;
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MAX:
				setMax((BigDecimal)newValue);
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
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MIN:
				setMin(MIN_EDEFAULT);
				return;
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MAX:
				setMax(MAX_EDEFAULT);
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
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MIN:
				return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
			case WebspecmodelPackage.UNIFORM_NUMBER_DISTRIBUTION__MAX:
				return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
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
		result.append(" (min: ");
		result.append(min);
		result.append(", max: ");
		result.append(max);
		result.append(')');
		return result.toString();
	}

} //UniformNumberDistributionImpl
