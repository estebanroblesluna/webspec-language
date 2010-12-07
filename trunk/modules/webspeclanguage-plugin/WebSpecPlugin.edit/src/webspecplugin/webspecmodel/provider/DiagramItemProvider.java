/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package webspecplugin.webspecmodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.WebspecmodelFactory;
import webspecplugin.webspecmodel.WebspecmodelPackage;

/**
 * This is the item provider adapter for a {@link webspecplugin.webspecmodel.Diagram} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagramItemProvider
	extends ItemProviderAdapter
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
	public DiagramItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addCycleAllowedPropertyDescriptor(object);
			addActionsSetupPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_name_feature", "_UI_Diagram_type"),
				 WebspecmodelPackage.Literals.DIAGRAM__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cycle Allowed feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCycleAllowedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_cycleAllowed_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_cycleAllowed_feature", "_UI_Diagram_type"),
				 WebspecmodelPackage.Literals.DIAGRAM__CYCLE_ALLOWED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actions Setup feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActionsSetupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_actionsSetup_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_actionsSetup_feature", "_UI_Diagram_type"),
				 WebspecmodelPackage.Literals.DIAGRAM__ACTIONS_SETUP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(WebspecmodelPackage.Literals.DIAGRAM__TRANSITIONS);
			childrenFeatures.add(WebspecmodelPackage.Literals.DIAGRAM__INTERACTIONS);
			childrenFeatures.add(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS);
			childrenFeatures.add(WebspecmodelPackage.Literals.DIAGRAM__RIA_FEATURES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Diagram.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Diagram"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Diagram)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Diagram_type") :
			getString("_UI_Diagram_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Diagram.class)) {
			case WebspecmodelPackage.DIAGRAM__NAME:
			case WebspecmodelPackage.DIAGRAM__CYCLE_ALLOWED:
			case WebspecmodelPackage.DIAGRAM__ACTIONS_SETUP:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case WebspecmodelPackage.DIAGRAM__TRANSITIONS:
			case WebspecmodelPackage.DIAGRAM__INTERACTIONS:
			case WebspecmodelPackage.DIAGRAM__GENERATORS:
			case WebspecmodelPackage.DIAGRAM__RIA_FEATURES:
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
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__TRANSITIONS,
				 WebspecmodelFactory.eINSTANCE.createTransition()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__TRANSITIONS,
				 WebspecmodelFactory.eINSTANCE.createNavigation()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__TRANSITIONS,
				 WebspecmodelFactory.eINSTANCE.createRichBehavior()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__INTERACTIONS,
				 WebspecmodelFactory.eINSTANCE.createInteraction()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createGenerator()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createOneOfStrings()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createOneOfNumbers()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createUniformNumberDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createOneOfArray()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__GENERATORS,
				 WebspecmodelFactory.eINSTANCE.createStringGenerator()));

		newChildDescriptors.add
			(createChildParameter
				(WebspecmodelPackage.Literals.DIAGRAM__RIA_FEATURES,
				 WebspecmodelFactory.eINSTANCE.createRIAFeature()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return WebspecmodelEditPlugin.INSTANCE;
	}

}
