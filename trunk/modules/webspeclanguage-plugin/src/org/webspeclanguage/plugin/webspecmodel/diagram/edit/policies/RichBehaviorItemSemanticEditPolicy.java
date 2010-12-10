package org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.webspeclanguage.plugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;


/**
 * @generated
 */
public class RichBehaviorItemSemanticEditPolicy extends WebspecmodelBaseItemSemanticEditPolicy {

  /**
   * @generated
   */
  public RichBehaviorItemSemanticEditPolicy() {
    super(WebspecmodelElementTypes.RichBehavior_4002);
  }

  /**
   * @generated
   */
  protected Command getDestroyElementCommand(DestroyElementRequest req) {
    return getGEFWrapper(new DestroyElementCommand(req));
  }

}
