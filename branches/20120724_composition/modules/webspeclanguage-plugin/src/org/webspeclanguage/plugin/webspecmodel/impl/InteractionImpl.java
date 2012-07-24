/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.impl;

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
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.PanelContainer;
import org.webspeclanguage.plugin.webspecmodel.Transition;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getForwardTransitions <em>Forward Transitions</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getInvariant <em>Invariant</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#isStarting <em>Starting</em>}</li>
 *   <li>{@link org.webspeclanguage.plugin.webspecmodel.impl.InteractionImpl#getMockupFile <em>Mockup File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InteractionImpl extends EObjectImpl implements Interaction {
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
	 * The cached value of the '{@link #getForwardTransitions() <em>Forward Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList forwardTransitions;

	/**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
	protected PanelContainer root;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getInvariant() <em>Invariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariant()
	 * @generated
	 * @ordered
	 */
	protected static final String INVARIANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInvariant() <em>Invariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariant()
	 * @generated
	 * @ordered
	 */
	protected String invariant = INVARIANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isStarting() <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStarting()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STARTING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStarting() <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStarting()
	 * @generated
	 * @ordered
	 */
	protected boolean starting = STARTING_EDEFAULT;

	/**
	 * The default value of the '{@link #getMockupFile() <em>Mockup File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMockupFile()
	 * @generated
	 * @ordered
	 */
	protected static final String MOCKUP_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMockupFile() <em>Mockup File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMockupFile()
	 * @generated
	 * @ordered
	 */
	protected String mockupFile = MOCKUP_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InteractionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.INTERACTION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getForwardTransitions() {
		if (forwardTransitions == null) {
			forwardTransitions = new EObjectContainmentEList(Transition.class, this, WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS);
		}
		return forwardTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PanelContainer getRoot() {
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(PanelContainer newRoot, NotificationChain msgs) {
		PanelContainer oldRoot = root;
		root = newRoot;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__ROOT, oldRoot, newRoot);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(PanelContainer newRoot) {
		if (newRoot != root) {
//		  this.setRootName(newRoot);
			NotificationChain msgs = null;
			if (root != null)
				msgs = ((InternalEObject)root).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebspecmodelPackage.INTERACTION__ROOT, null, msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebspecmodelPackage.INTERACTION__ROOT, null, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__ROOT, newRoot, newRoot));
	}

	private void setRootName(PanelContainer newRoot) {
	  newRoot.setRootName();
  }

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInvariant() {
		return invariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvariant(String newInvariant) {
		String oldInvariant = invariant;
		invariant = newInvariant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__INVARIANT, oldInvariant, invariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStarting() {
		return starting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStarting(boolean newStarting) {
		boolean oldStarting = starting;
		starting = newStarting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__STARTING, oldStarting, starting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMockupFile() {
		return mockupFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMockupFile(String newMockupFile) {
		String oldMockupFile = mockupFile;
		mockupFile = newMockupFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebspecmodelPackage.INTERACTION__MOCKUP_FILE, oldMockupFile, mockupFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS:
				return ((InternalEList)getForwardTransitions()).basicRemove(otherEnd, msgs);
			case WebspecmodelPackage.INTERACTION__ROOT:
				return basicSetRoot(null, msgs);
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
			case WebspecmodelPackage.INTERACTION__NAME:
				return getName();
			case WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS:
				return getForwardTransitions();
			case WebspecmodelPackage.INTERACTION__ROOT:
				return getRoot();
			case WebspecmodelPackage.INTERACTION__LOCATION:
				return getLocation();
			case WebspecmodelPackage.INTERACTION__INVARIANT:
				return getInvariant();
			case WebspecmodelPackage.INTERACTION__TITLE:
				return getTitle();
			case WebspecmodelPackage.INTERACTION__STARTING:
				return isStarting() ? Boolean.TRUE : Boolean.FALSE;
			case WebspecmodelPackage.INTERACTION__MOCKUP_FILE:
				return getMockupFile();
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
			case WebspecmodelPackage.INTERACTION__NAME:
				setName((String)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS:
				getForwardTransitions().clear();
				getForwardTransitions().addAll((Collection)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__ROOT:
				setRoot((PanelContainer)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__LOCATION:
				setLocation((String)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__INVARIANT:
				setInvariant((String)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__TITLE:
				setTitle((String)newValue);
				return;
			case WebspecmodelPackage.INTERACTION__STARTING:
				setStarting(((Boolean)newValue).booleanValue());
				return;
			case WebspecmodelPackage.INTERACTION__MOCKUP_FILE:
				setMockupFile((String)newValue);
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
			case WebspecmodelPackage.INTERACTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS:
				getForwardTransitions().clear();
				return;
			case WebspecmodelPackage.INTERACTION__ROOT:
				setRoot((PanelContainer)null);
				return;
			case WebspecmodelPackage.INTERACTION__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case WebspecmodelPackage.INTERACTION__INVARIANT:
				setInvariant(INVARIANT_EDEFAULT);
				return;
			case WebspecmodelPackage.INTERACTION__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case WebspecmodelPackage.INTERACTION__STARTING:
				setStarting(STARTING_EDEFAULT);
				return;
			case WebspecmodelPackage.INTERACTION__MOCKUP_FILE:
				setMockupFile(MOCKUP_FILE_EDEFAULT);
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
			case WebspecmodelPackage.INTERACTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case WebspecmodelPackage.INTERACTION__FORWARD_TRANSITIONS:
				return forwardTransitions != null && !forwardTransitions.isEmpty();
			case WebspecmodelPackage.INTERACTION__ROOT:
				return root != null;
			case WebspecmodelPackage.INTERACTION__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case WebspecmodelPackage.INTERACTION__INVARIANT:
				return INVARIANT_EDEFAULT == null ? invariant != null : !INVARIANT_EDEFAULT.equals(invariant);
			case WebspecmodelPackage.INTERACTION__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case WebspecmodelPackage.INTERACTION__STARTING:
				return starting != STARTING_EDEFAULT;
			case WebspecmodelPackage.INTERACTION__MOCKUP_FILE:
				return MOCKUP_FILE_EDEFAULT == null ? mockupFile != null : !MOCKUP_FILE_EDEFAULT.equals(mockupFile);
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
		result.append(", location: ");
		result.append(location);
		result.append(", invariant: ");
		result.append(invariant);
		result.append(", title: ");
		result.append(title);
		result.append(", starting: ");
		result.append(starting);
		result.append(", mockupFile: ");
		result.append(mockupFile);
		result.append(')');
		return result.toString();
	}

} //InteractionImpl
