package org.webspeclanguage.plugin.webspecmodel.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class WebspecmodelNavigatorItem extends WebspecmodelAbstractNavigatorItem {

  /**
   * @generated
   */
  static {
    final Class[] supportedTypes = new Class[] { View.class, EObject.class };
    Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

      public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adaptableObject instanceof org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelNavigatorItem
                && (adapterType == View.class || adapterType == EObject.class)) {
          return ((org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelNavigatorItem) adaptableObject).getView();
        }
        return null;
      }

      public Class[] getAdapterList() {
        return supportedTypes;
      }
    }, org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelNavigatorItem.class);
  }

  /**
   * @generated
   */
  private View myView;

  /**
   * @generated
   */
  private boolean myLeaf = false;

  /**
   * @generated
   */
  public WebspecmodelNavigatorItem(View view, Object parent, boolean isLeaf) {
    super(parent);
    myView = view;
    myLeaf = isLeaf;
  }

  /**
   * @generated
   */
  public View getView() {
    return myView;
  }

  /**
   * @generated
   */
  public boolean isLeaf() {
    return myLeaf;
  }

  /**
   * @generated
   */
  public boolean equals(Object obj) {
    if (obj instanceof org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelNavigatorItem) {
      return EcoreUtil.getURI(getView()).equals(EcoreUtil.getURI(((org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelNavigatorItem) obj).getView()));
    }
    return super.equals(obj);
  }

  /**
   * @generated
   */
  public int hashCode() {
    return EcoreUtil.getURI(getView()).hashCode();
  }

}
