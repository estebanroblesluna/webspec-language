package org.webspeclanguage.plugin.webspecmodel.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class WebspecmodelIconProvider extends AbstractProvider implements IIconProvider {

  /**
   * @generated
   */
  public Image getIcon(IAdaptable hint, int flags) {
    return WebspecmodelElementTypes.getImage(hint);
  }

  /**
   * @generated
   */
  public boolean provides(IOperation operation) {
    if (operation instanceof GetIconOperation) {
      return ((GetIconOperation) operation).execute(this) != null;
    }
    return false;
  }
}
