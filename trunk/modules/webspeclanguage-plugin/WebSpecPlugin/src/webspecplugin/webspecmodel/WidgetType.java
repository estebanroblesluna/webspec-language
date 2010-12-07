/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Widget Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see webspecplugin.webspecmodel.WebspecmodelPackage#getWidgetType()
 * @model
 * @generated
 */
public final class WidgetType extends AbstractEnumerator {
	/**
	 * The '<em><b>Button</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Button</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BUTTON_LITERAL
	 * @model name="Button"
	 * @generated
	 * @ordered
	 */
	public static final int BUTTON = 0;

	/**
	 * The '<em><b>Text Field</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Text Field</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEXT_FIELD_LITERAL
	 * @model name="TextField"
	 * @generated
	 * @ordered
	 */
	public static final int TEXT_FIELD = 0;

	/**
	 * The '<em><b>Button</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUTTON
	 * @generated
	 * @ordered
	 */
	public static final WidgetType BUTTON_LITERAL = new WidgetType(BUTTON, "Button", "Button");

	/**
	 * The '<em><b>Text Field</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEXT_FIELD
	 * @generated
	 * @ordered
	 */
	public static final WidgetType TEXT_FIELD_LITERAL = new WidgetType(TEXT_FIELD, "TextField", "TextField");

	/**
	 * An array of all the '<em><b>Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WidgetType[] VALUES_ARRAY =
		new WidgetType[] {
			BUTTON_LITERAL,
			TEXT_FIELD_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Widget Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Widget Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WidgetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WidgetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Widget Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WidgetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WidgetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Widget Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WidgetType get(int value) {
		switch (value) {
			case BUTTON: return BUTTON_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private WidgetType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //WidgetType
