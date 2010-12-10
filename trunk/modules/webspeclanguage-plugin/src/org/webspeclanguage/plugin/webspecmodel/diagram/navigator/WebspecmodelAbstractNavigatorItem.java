package org.webspeclanguage.plugin.webspecmodel.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @generated
 */
public abstract class WebspecmodelAbstractNavigatorItem extends PlatformObject {

  /**
   * @generated
   */
  static {
    final Class[] supportedTypes = new Class[] { ITabbedPropertySheetPageContributor.class };
    final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {

      public String getContributorId() {
        return "WebSpecPlugin.diagram"; //$NON-NLS-1$
      }
    };
    Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

      public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adaptableObject instanceof org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelAbstractNavigatorItem
                && adapterType == ITabbedPropertySheetPageContributor.class) {
          return propertySheetPageContributor;
        }
        return null;
      }

      public Class[] getAdapterList() {
        return supportedTypes;
      }
    }, org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelAbstractNavigatorItem.class);
  }

  /**
   * @generated
   */
  private Object myParent;

  /**
   * @generated
   */
  protected WebspecmodelAbstractNavigatorItem(Object parent) {
    myParent = parent;
  }

  /**
   * @generated
   */
  public Object getParent() {
    return myParent;
  }

}
