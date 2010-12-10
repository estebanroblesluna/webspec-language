/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel;

import java.math.BigDecimal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uniform Number Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution#getMin <em>Min</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getUniformNumberDistribution()
 * @model
 * @generated
 */
public interface UniformNumberDistribution extends Generator {
	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(BigDecimal)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getUniformNumberDistribution_Min()
	 * @model default="0"
	 * @generated
	 */
	BigDecimal getMin();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * The default value is <code>"9999"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(BigDecimal)
	 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getUniformNumberDistribution_Max()
	 * @model default="9999"
	 * @generated
	 */
	BigDecimal getMax();

	/**
	 * Sets the value of the '{@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(BigDecimal value);

} // UniformNumberDistribution
