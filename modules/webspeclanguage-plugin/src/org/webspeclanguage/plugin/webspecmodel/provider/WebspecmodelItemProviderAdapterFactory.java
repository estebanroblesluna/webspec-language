/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.webspeclanguage.plugin.webspecmodel.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.webspeclanguage.plugin.webspecmodel.util.WebspecmodelAdapterFactory;


/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WebspecmodelItemProviderAdapterFactory extends WebspecmodelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection supportedTypes = new ArrayList();

	/**
   * This constructs an instance.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public WebspecmodelItemProviderAdapterFactory() {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Diagram} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected DiagramItemProvider diagramItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Diagram}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createDiagramAdapter() {
    if (diagramItemProvider == null) {
      diagramItemProvider = new DiagramItemProvider(this);
    }

    return diagramItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Interaction} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InteractionItemProvider interactionItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Interaction}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createInteractionAdapter() {
    if (interactionItemProvider == null) {
      interactionItemProvider = new InteractionItemProvider(this);
    }

    return interactionItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Transition} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TransitionItemProvider transitionItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Transition}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createTransitionAdapter() {
    if (transitionItemProvider == null) {
      transitionItemProvider = new TransitionItemProvider(this);
    }

    return transitionItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Navigation} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected NavigationItemProvider navigationItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Navigation}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createNavigationAdapter() {
    if (navigationItemProvider == null) {
      navigationItemProvider = new NavigationItemProvider(this);
    }

    return navigationItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.RichBehavior} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RichBehaviorItemProvider richBehaviorItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.RichBehavior}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createRichBehaviorAdapter() {
    if (richBehaviorItemProvider == null) {
      richBehaviorItemProvider = new RichBehaviorItemProvider(this);
    }

    return richBehaviorItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.RIAFeature} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RIAFeatureItemProvider riaFeatureItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.RIAFeature}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createRIAFeatureAdapter() {
    if (riaFeatureItemProvider == null) {
      riaFeatureItemProvider = new RIAFeatureItemProvider(this);
    }

    return riaFeatureItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Widget} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected WidgetItemProvider widgetItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Widget}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createWidgetAdapter() {
    if (widgetItemProvider == null) {
      widgetItemProvider = new WidgetItemProvider(this);
    }

    return widgetItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Generator} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected GeneratorItemProvider generatorItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Generator}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createGeneratorAdapter() {
    if (generatorItemProvider == null) {
      generatorItemProvider = new GeneratorItemProvider(this);
    }

    return generatorItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Button} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ButtonItemProvider buttonItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Button}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createButtonAdapter() {
    if (buttonItemProvider == null) {
      buttonItemProvider = new ButtonItemProvider(this);
    }

    return buttonItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.TextField} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TextFieldItemProvider textFieldItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.TextField}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createTextFieldAdapter() {
    if (textFieldItemProvider == null) {
      textFieldItemProvider = new TextFieldItemProvider(this);
    }

    return textFieldItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.RadioButton} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected RadioButtonItemProvider radioButtonItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.RadioButton}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createRadioButtonAdapter() {
    if (radioButtonItemProvider == null) {
      radioButtonItemProvider = new RadioButtonItemProvider(this);
    }

    return radioButtonItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.ListBox} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ListBoxItemProvider listBoxItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.ListBox}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createListBoxAdapter() {
    if (listBoxItemProvider == null) {
      listBoxItemProvider = new ListBoxItemProvider(this);
    }

    return listBoxItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.ComboBox} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComboBoxItemProvider comboBoxItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.ComboBox}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createComboBoxAdapter() {
    if (comboBoxItemProvider == null) {
      comboBoxItemProvider = new ComboBoxItemProvider(this);
    }

    return comboBoxItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.CheckBox} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CheckBoxItemProvider checkBoxItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.CheckBox}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createCheckBoxAdapter() {
    if (checkBoxItemProvider == null) {
      checkBoxItemProvider = new CheckBoxItemProvider(this);
    }

    return checkBoxItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Container} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ContainerItemProvider containerItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Container}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createContainerAdapter() {
    if (containerItemProvider == null) {
      containerItemProvider = new ContainerItemProvider(this);
    }

    return containerItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.OneOfStrings} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OneOfStringsItemProvider oneOfStringsItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.OneOfStrings}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createOneOfStringsAdapter() {
    if (oneOfStringsItemProvider == null) {
      oneOfStringsItemProvider = new OneOfStringsItemProvider(this);
    }

    return oneOfStringsItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.OneOfNumbers} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OneOfNumbersItemProvider oneOfNumbersItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.OneOfNumbers}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createOneOfNumbersAdapter() {
    if (oneOfNumbersItemProvider == null) {
      oneOfNumbersItemProvider = new OneOfNumbersItemProvider(this);
    }

    return oneOfNumbersItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected UniformNumberDistributionItemProvider uniformNumberDistributionItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.UniformNumberDistribution}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createUniformNumberDistributionAdapter() {
    if (uniformNumberDistributionItemProvider == null) {
      uniformNumberDistributionItemProvider = new UniformNumberDistributionItemProvider(this);
    }

    return uniformNumberDistributionItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Label} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LabelItemProvider labelItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Label}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createLabelAdapter() {
    if (labelItemProvider == null) {
      labelItemProvider = new LabelItemProvider(this);
    }

    return labelItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.OneOfArray} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OneOfArrayItemProvider oneOfArrayItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.OneOfArray}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createOneOfArrayAdapter() {
    if (oneOfArrayItemProvider == null) {
      oneOfArrayItemProvider = new OneOfArrayItemProvider(this);
    }

    return oneOfArrayItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.StringGenerator} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StringGeneratorItemProvider stringGeneratorItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.StringGenerator}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createStringGeneratorAdapter() {
    if (stringGeneratorItemProvider == null) {
      stringGeneratorItemProvider = new StringGeneratorItemProvider(this);
    }

    return stringGeneratorItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.Link} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LinkItemProvider linkItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.Link}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createLinkAdapter() {
    if (linkItemProvider == null) {
      linkItemProvider = new LinkItemProvider(this);
    }

    return linkItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.ListOfContainer} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ListOfContainerItemProvider listOfContainerItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.ListOfContainer}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createListOfContainerAdapter() {
    if (listOfContainerItemProvider == null) {
      listOfContainerItemProvider = new ListOfContainerItemProvider(this);
    }

    return listOfContainerItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link org.webspeclanguage.plugin.webspecmodel.PanelContainer} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PanelContainerItemProvider panelContainerItemProvider;

	/**
   * This creates an adapter for a {@link org.webspeclanguage.plugin.webspecmodel.PanelContainer}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Adapter createPanelContainerAdapter() {
    if (panelContainerItemProvider == null) {
      panelContainerItemProvider = new PanelContainerItemProvider(this);
    }

    return panelContainerItemProvider;
  }

	/**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ComposeableAdapterFactory getRootAdapterFactory() {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

	/**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
    this.parentAdapterFactory = parentAdapterFactory;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object type) {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

	/**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
    return super.adapt(notifier, this);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object adapt(Object object, Object type) {
    if (isFactoryForType(type)) {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class) || (((Class)type).isInstance(adapter))) {
        return adapter;
      }
    }

    return null;
  }

	/**
   * This adds a listener.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void addListener(INotifyChangedListener notifyChangedListener) {
    changeNotifier.addListener(notifyChangedListener);
  }

	/**
   * This removes a listener.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
    changeNotifier.removeListener(notifyChangedListener);
  }

	/**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void fireNotifyChanged(Notification notification) {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null) {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

	/**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void dispose() {
    if (diagramItemProvider != null) diagramItemProvider.dispose();
    if (interactionItemProvider != null) interactionItemProvider.dispose();
    if (transitionItemProvider != null) transitionItemProvider.dispose();
    if (widgetItemProvider != null) widgetItemProvider.dispose();
    if (generatorItemProvider != null) generatorItemProvider.dispose();
    if (buttonItemProvider != null) buttonItemProvider.dispose();
    if (textFieldItemProvider != null) textFieldItemProvider.dispose();
    if (radioButtonItemProvider != null) radioButtonItemProvider.dispose();
    if (listBoxItemProvider != null) listBoxItemProvider.dispose();
    if (comboBoxItemProvider != null) comboBoxItemProvider.dispose();
    if (checkBoxItemProvider != null) checkBoxItemProvider.dispose();
    if (containerItemProvider != null) containerItemProvider.dispose();
    if (oneOfStringsItemProvider != null) oneOfStringsItemProvider.dispose();
    if (oneOfNumbersItemProvider != null) oneOfNumbersItemProvider.dispose();
    if (uniformNumberDistributionItemProvider != null) uniformNumberDistributionItemProvider.dispose();
    if (labelItemProvider != null) labelItemProvider.dispose();
    if (oneOfArrayItemProvider != null) oneOfArrayItemProvider.dispose();
    if (stringGeneratorItemProvider != null) stringGeneratorItemProvider.dispose();
    if (linkItemProvider != null) linkItemProvider.dispose();
    if (listOfContainerItemProvider != null) listOfContainerItemProvider.dispose();
    if (panelContainerItemProvider != null) panelContainerItemProvider.dispose();
    if (navigationItemProvider != null) navigationItemProvider.dispose();
    if (richBehaviorItemProvider != null) richBehaviorItemProvider.dispose();
    if (riaFeatureItemProvider != null) riaFeatureItemProvider.dispose();
  }

}
