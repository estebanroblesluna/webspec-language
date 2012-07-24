/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.webspeclanguage.plugin.webspecmodel.PanelContainer;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Panel Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class PanelContainerImpl extends ContainerImpl implements PanelContainer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PanelContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebspecmodelPackage.Literals.PANEL_CONTAINER;
	}

	public void setRootName() {
	  this.name = "root";
	}
} //PanelContainerImpl
