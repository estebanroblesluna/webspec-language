package webspecplugin.webspecmodel.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class WebspecmodelNavigatorSorter extends ViewerSorter {

  /**
   * @generated
   */
  private static final int GROUP_CATEGORY = 7005;

  /**
   * @generated
   */
  public int category(Object element) {
    if (element instanceof WebspecmodelNavigatorItem) {
      WebspecmodelNavigatorItem item = (WebspecmodelNavigatorItem) element;
      return WebspecmodelVisualIDRegistry.getVisualID(item.getView());
    }
    return GROUP_CATEGORY;
  }

}
