/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Generator Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage#getGeneratorType()
 * @model
 * @generated
 */
public final class GeneratorType extends AbstractEnumerator {
	/**
	 * The '<em><b>One Of Number</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One Of Number</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_OF_NUMBER_LITERAL
	 * @model name="OneOfNumber"
	 * @generated
	 * @ordered
	 */
	public static final int ONE_OF_NUMBER = 0;

	/**
	 * The '<em><b>One Of String</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One Of String</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_OF_STRING_LITERAL
	 * @model name="OneOfString"
	 * @generated
	 * @ordered
	 */
	public static final int ONE_OF_STRING = 0;

	/**
	 * The '<em><b>Uniform Number</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Uniform Number</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIFORM_NUMBER_LITERAL
	 * @model name="UniformNumber"
	 * @generated
	 * @ordered
	 */
	public static final int UNIFORM_NUMBER = 0;

	/**
	 * The '<em><b>One Of Number</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE_OF_NUMBER
	 * @generated
	 * @ordered
	 */
	public static final GeneratorType ONE_OF_NUMBER_LITERAL = new GeneratorType(ONE_OF_NUMBER, "OneOfNumber", "OneOfNumber");

	/**
	 * The '<em><b>One Of String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE_OF_STRING
	 * @generated
	 * @ordered
	 */
	public static final GeneratorType ONE_OF_STRING_LITERAL = new GeneratorType(ONE_OF_STRING, "OneOfString", "OneOfString");

	/**
	 * The '<em><b>Uniform Number</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIFORM_NUMBER
	 * @generated
	 * @ordered
	 */
	public static final GeneratorType UNIFORM_NUMBER_LITERAL = new GeneratorType(UNIFORM_NUMBER, "UniformNumber", "UniformNumber");

	/**
	 * An array of all the '<em><b>Generator Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GeneratorType[] VALUES_ARRAY =
		new GeneratorType[] {
			ONE_OF_NUMBER_LITERAL,
			ONE_OF_STRING_LITERAL,
			UNIFORM_NUMBER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Generator Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Generator Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GeneratorType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GeneratorType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generator Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GeneratorType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GeneratorType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generator Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GeneratorType get(int value) {
		switch (value) {
			case ONE_OF_NUMBER: return ONE_OF_NUMBER_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private GeneratorType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //GeneratorType
