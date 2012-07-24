/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.Transition;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.TransitionImpl#getTargetInteraction <em>Target Interaction</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.TransitionImpl#getSourceInteraction <em>Source Interaction</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.TransitionImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.TransitionImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.TransitionImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionImpl extends EObjectImpl implements Transition {
	/**
   * The cached value of the '{@link #getTargetInteraction() <em>Target Interaction</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTargetInteraction()
   * @generated
   * @ordered
   */
	protected Interaction targetInteraction;

	/**
   * The cached value of the '{@link #getSourceInteraction() <em>Source Interaction</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSourceInteraction()
   * @generated
   * @ordered
   */
	protected Interaction sourceInteraction;

	/**
   * The default value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPrecondition()
   * @generated
   * @ordered
   */
	protected static final String PRECONDITION_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPrecondition()
   * @generated
   * @ordered
   */
	protected String precondition = PRECONDITION_EDEFAULT;

	/**
   * The default value of the '{@link #getActions() <em>Actions</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getActions()
   * @generated
   * @ordered
   */
	protected static final String ACTIONS_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getActions() <em>Actions</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getActions()
   * @generated
   * @ordered
   */
	protected String actions = ACTIONS_EDEFAULT;

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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TransitionImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EClass eStaticClass() {
    return WebspecmodelPackage.Literals.TRANSITION;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Interaction getTargetInteraction() {
    if (targetInteraction != null && targetInteraction.eIsProxy()) {
      InternalEObject oldTargetInteraction = (InternalEObject)targetInteraction;
      targetInteraction = (Interaction)eResolveProxy(oldTargetInteraction);
      if (targetInteraction != oldTargetInteraction) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebspecmodelPackage.TRANSITION__TARGET_INTERACTION, oldTargetInteraction, targetInteraction));
      }
    }
    return targetInteraction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Interaction basicGetTargetInteraction() {
    return targetInteraction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTargetInteraction(Interaction newTargetInteraction) {
    Interaction oldTargetInteraction = targetInteraction;
    targetInteraction = newTargetInteraction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.TRANSITION__TARGET_INTERACTION, oldTargetInteraction, targetInteraction));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Interaction getSourceInteraction() {
    if (sourceInteraction != null && sourceInteraction.eIsProxy()) {
      InternalEObject oldSourceInteraction = (InternalEObject)sourceInteraction;
      sourceInteraction = (Interaction)eResolveProxy(oldSourceInteraction);
      if (sourceInteraction != oldSourceInteraction) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION, oldSourceInteraction, sourceInteraction));
      }
    }
    return sourceInteraction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Interaction basicGetSourceInteraction() {
    return sourceInteraction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSourceInteraction(Interaction newSourceInteraction) {
    Interaction oldSourceInteraction = sourceInteraction;
    sourceInteraction = newSourceInteraction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION, oldSourceInteraction, sourceInteraction));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getPrecondition() {
    return precondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setPrecondition(String newPrecondition) {
    String oldPrecondition = precondition;
    precondition = newPrecondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.TRANSITION__PRECONDITION, oldPrecondition, precondition));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getActions() {
    return actions;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setActions(String newActions) {
    String oldActions = actions;
    actions = newActions;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.TRANSITION__ACTIONS, oldActions, actions));
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
      eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.TRANSITION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case WebspecmodelPackage.TRANSITION__TARGET_INTERACTION:
        if (resolve) return getTargetInteraction();
        return basicGetTargetInteraction();
      case WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION:
        if (resolve) return getSourceInteraction();
        return basicGetSourceInteraction();
      case WebspecmodelPackage.TRANSITION__PRECONDITION:
        return getPrecondition();
      case WebspecmodelPackage.TRANSITION__ACTIONS:
        return getActions();
      case WebspecmodelPackage.TRANSITION__NAME:
        return getName();
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
      case WebspecmodelPackage.TRANSITION__TARGET_INTERACTION:
        setTargetInteraction((Interaction)newValue);
        return;
      case WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION:
        setSourceInteraction((Interaction)newValue);
        return;
      case WebspecmodelPackage.TRANSITION__PRECONDITION:
        setPrecondition((String)newValue);
        return;
      case WebspecmodelPackage.TRANSITION__ACTIONS:
        setActions((String)newValue);
        return;
      case WebspecmodelPackage.TRANSITION__NAME:
        setName((String)newValue);
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
      case WebspecmodelPackage.TRANSITION__TARGET_INTERACTION:
        setTargetInteraction((Interaction)null);
        return;
      case WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION:
        setSourceInteraction((Interaction)null);
        return;
      case WebspecmodelPackage.TRANSITION__PRECONDITION:
        setPrecondition(PRECONDITION_EDEFAULT);
        return;
      case WebspecmodelPackage.TRANSITION__ACTIONS:
        setActions(ACTIONS_EDEFAULT);
        return;
      case WebspecmodelPackage.TRANSITION__NAME:
        setName(NAME_EDEFAULT);
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
      case WebspecmodelPackage.TRANSITION__TARGET_INTERACTION:
        return targetInteraction != null;
      case WebspecmodelPackage.TRANSITION__SOURCE_INTERACTION:
        return sourceInteraction != null;
      case WebspecmodelPackage.TRANSITION__PRECONDITION:
        return PRECONDITION_EDEFAULT == null ? precondition != null : !PRECONDITION_EDEFAULT.equals(precondition);
      case WebspecmodelPackage.TRANSITION__ACTIONS:
        return ACTIONS_EDEFAULT == null ? actions != null : !ACTIONS_EDEFAULT.equals(actions);
      case WebspecmodelPackage.TRANSITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
    result.append(" (precondition: ");
    result.append(precondition);
    result.append(", actions: ");
    result.append(actions);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //TransitionImpl
