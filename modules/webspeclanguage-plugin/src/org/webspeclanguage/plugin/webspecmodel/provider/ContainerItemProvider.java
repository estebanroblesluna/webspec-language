/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.webspeclanguage.plugin.webspecmodel.Container;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelFactory;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * This is the item provider adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Container} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainerItemProvider
	extends WidgetItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(WebspecmodelPackage.Literals.CONTAINER__WIDGETS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Container.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Container"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((Container)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Container_type") :
			getString("_UI_Container_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Container.class)) {
			case WebspecmodelPackage.CONTAINER__WIDGETS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createWidget()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createButton()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createTextField()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createRadioButton()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createListBox()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createComboBox()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createCheckBox()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createContainer()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createLabel()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createLink()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createListOfContainer()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.CONTAINER__WIDGETS,
				 WebspecmodelFactory.eINSTANCE.createPanelContainer()));
	}

}
