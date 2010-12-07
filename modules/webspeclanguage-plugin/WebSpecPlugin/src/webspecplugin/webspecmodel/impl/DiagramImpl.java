/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Generator;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.Transition;
import webspecplugin.webspecmodel.WebspecmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getInteractions <em>Interactions</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getName <em>Name</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getGenerators <em>Generators</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getCycleAllowed <em>Cycle Allowed</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getActionsSetup <em>Actions Setup</em>}</li>
 *   <li>{@link webspecplugin.webspecmodel.impl.DiagramImpl#getRiaFeatures <em>Ria Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramImpl extends EObjectImpl implements Diagram {
	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList transitions;

	/**
	 * The cached value of the '{@link #getInteractions() <em>Interactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractions()
	 * @generated
	 * @ordered
	 */
	protected EList interactions;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGenerators() <em>Generators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList generators;

	/**
	 * The default value of the '{@link #getCycleAllowed() <em>Cycle Allowed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCycleAllowed()
	 * @generated
	 * @ordered
	 */
	protected static final int CYCLE_ALLOWED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCycleAllowed() <em>Cycle Allowed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCycleAllowed()
	 * @generated
	 * @ordered
	 */
	protected int cycleAllowed = CYCLE_ALLOWED_EDEFAULT;

	/**
	 * The default value of the '{@link #getActionsSetup() <em>Actions Setup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionsSetup()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIONS_SETUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActionsSetup() <em>Actions Setup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionsSetup()
	 * @generated
	 * @ordered
	 */
	protected String actionsSetup = ACTIONS_SETUP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRiaFeatures() <em>Ria Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRiaFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList riaFeatures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTransitions() {
		if (transitions == null) {
			transitions = new EObjectContainmentEList(Transition.class, this, WebspecmodelPackage.DIAGRAM__TRANSITIONS);
		}
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInteractions() {
		if (interactions == null) {
			interactions = new EObjectContainmentEList(Interaction.class, this, WebspecmodelPackage.DIAGRAM__INTERACTIONS);
		}
		return interactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.DIAGRAM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGenerators() {
		if (generators == null) {
			generators = new EObjectContainmentEList(Generator.class, this, WebspecmodelPackage.DIAGRAM__GENERATORS);
		}
		return generators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCycleAllowed() {
		return cycleAllowed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCycleAllowed(int newCycleAllowed) {
		int oldCycleAllowed = cycleAllowed;
		cycleAllowed = newCycleAllowed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED, oldCycleAllowed, cycleAllowed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActionsSetup() {
		return actionsSetup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionsSetup(String newActionsSetup) {
		String oldActionsSetup = actionsSetup;
		actionsSetup = newActionsSetup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP, oldActionsSetup, actionsSetup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRiaFeatures() {
		if (riaFeatures == null) {
			riaFeatures = new EObjectContainmentEList(RIAFeature.class, this, WebspecmodelPackage.DIAGRAM__RIA_FEATURES);
		}
		return riaFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
				return ((InternalEList)getTransitions()).basicRemove(otherEnd, msgs);
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
				return ((InternalEList)getInteractions()).basicRemove(otherEnd, msgs);
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
				return ((InternalEList)getGenerators()).basicRemove(otherEnd, msgs);
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
				return ((InternalEList)getRiaFeatures()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
				return getTransitions();
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
				return getInteractions();
			case WebspecmodelPackage.DIAGRAM__NAME:
				return getName();
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
				return getGenerators();
			case WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED:
				return new Integer(getCycleAllowed());
			case WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP:
				return getActionsSetup();
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
				return getRiaFeatures();
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
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection)newValue);
				return;
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
				getInteractions().clear();
				getInteractions().addAll((Collection)newValue);
				return;
			case WebspecmodelPackage.DIAGRAM__NAME:
				setName((String)newValue);
				return;
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
				getGenerators().clear();
				getGenerators().addAll((Collection)newValue);
				return;
			case WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED:
				setCycleAllowed(((Integer)newValue).intValue());
				return;
			case WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP:
				setActionsSetup((String)newValue);
				return;
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
				getRiaFeatures().clear();
				getRiaFeatures().addAll((Collection)newValue);
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
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
				getTransitions().clear();
				return;
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
				getInteractions().clear();
				return;
			case WebspecmodelPackage.DIAGRAM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
				getGenerators().clear();
				return;
			case WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED:
				setCycleAllowed(CYCLE_ALLOWED_EDEFAULT);
				return;
			case WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP:
				setActionsSetup(ACTIONS_SETUP_EDEFAULT);
				return;
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
				getRiaFeatures().clear();
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
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
				return interactions != null && !interactions.isEmpty();
			case WebspecmodelPackage.DIAGRAM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
				return generators != null && !generators.isEmpty();
			case WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED:
				return cycleAllowed != CYCLE_ALLOWED_EDEFAULT;
			case WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP:
				return ACTIONS_SETUP_EDEFAULT == null ? actionsSetup != null : !ACTIONS_SETUP_EDEFAULT.equals(actionsSetup);
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
				return riaFeatures != null && !riaFeatures.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", cycleAllowed: ");
		result.append(cycleAllowed);
		result.append(", actionsSetup: ");
		result.append(actionsSetup);
		result.append(')');
		return result.toString();
	}

} //DiagramImpl
