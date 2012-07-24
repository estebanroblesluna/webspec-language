package org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.InteractionCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.OneOfArrayCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.OneOfNumbersCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.OneOfStringsCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.RIAFeatureCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.StringGeneratorCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.UniformNumberDistributionCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;


/**
 * @generated
 */
public class DiagramItemSemanticEditPolicy extends WebspecmodelBaseItemSemanticEditPolicy {

  /**
   * @generated
   */
  public DiagramItemSemanticEditPolicy() {
    super(WebspecmodelElementTypes.Diagram_1000);
  }

  /**
   * @generated
   */
  protected Command getCreateCommand(CreateElementRequest req) {
    if (WebspecmodelElementTypes.Interaction_2001 == req.getElementType()) {
      return getGEFWrapper(new InteractionCreateCommand(req));
    }
    if (WebspecmodelElementTypes.OneOfNumbers_2002 == req.getElementType()) {
      return getGEFWrapper(new OneOfNumbersCreateCommand(req));
    }
    if (WebspecmodelElementTypes.OneOfStrings_2003 == req.getElementType()) {
      return getGEFWrapper(new OneOfStringsCreateCommand(req));
    }
    if (WebspecmodelElementTypes.UniformNumberDistribution_2004 == req.getElementType()) {
      return getGEFWrapper(new UniformNumberDistributionCreateCommand(req));
    }
    if (WebspecmodelElementTypes.OneOfArray_2005 == req.getElementType()) {
      return getGEFWrapper(new OneOfArrayCreateCommand(req));
    }
    if (WebspecmodelElementTypes.StringGenerator_2006 == req.getElementType()) {
      return getGEFWrapper(new StringGeneratorCreateCommand(req));
    }
    if (WebspecmodelElementTypes.RIAFeature_2007 == req.getElementType()) {
      return getGEFWrapper(new RIAFeatureCreateCommand(req));
    }
    return super.getCreateCommand(req);
  }

  /**
   * @generated
   */
  protected Command getDuplicateCommand(DuplicateElementsRequest req) {
    TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
    return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
  }

  /**
   * @generated
   */
  private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

    /**
     * @generated
     */
    public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
      super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
    }

  }

}
