package org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.RichBehavior;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies.WebspecmodelBaseItemSemanticEditPolicy;


/**
 * @generated
 */
public class RichBehaviorReorientCommand extends EditElementCommand {

  /**
   * @generated
   */
  private final int reorientDirection;

  /**
   * @generated
   */
  private final EObject oldEnd;

  /**
   * @generated
   */
  private final EObject newEnd;

  /**
   * @generated
   */
  public RichBehaviorReorientCommand(ReorientRelationshipRequest request) {
    super(request.getLabel(), request.getRelationship(), request);
    reorientDirection = request.getDirection();
    oldEnd = request.getOldRelationshipEnd();
    newEnd = request.getNewRelationshipEnd();
  }

  /**
   * @generated
   */
  public boolean canExecute() {
    if (false == getElementToEdit() instanceof RichBehavior) {
      return false;
    }
    if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
      return canReorientSource();
    }
    if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
      return canReorientTarget();
    }
    return false;
  }

  /**
   * @generated
   */
  protected boolean canReorientSource() {
    if (!(oldEnd instanceof Interaction && newEnd instanceof Interaction)) {
      return false;
    }
    Interaction target = getLink().getTargetInteraction();
    if (!(getLink().eContainer() instanceof Diagram)) {
      return false;
    }
    Diagram container = (Diagram) getLink().eContainer();
    return WebspecmodelBaseItemSemanticEditPolicy.LinkConstraints.canExistRichBehavior_4002(container, getNewSource(), target);
  }

  /**
   * @generated
   */
  protected boolean canReorientTarget() {
    if (!(oldEnd instanceof Interaction && newEnd instanceof Interaction)) {
      return false;
    }
    Interaction source = getLink().getSourceInteraction();
    if (!(getLink().eContainer() instanceof Diagram)) {
      return false;
    }
    Diagram container = (Diagram) getLink().eContainer();
    return WebspecmodelBaseItemSemanticEditPolicy.LinkConstraints.canExistRichBehavior_4002(container, source, getNewTarget());
  }

  /**
   * @generated
   */
  protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
    if (!canExecute()) {
      throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
    }
    if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
      return reorientSource();
    }
    if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
      return reorientTarget();
    }
    throw new IllegalStateException();
  }

  /**
   * @generated
   */
  protected CommandResult reorientSource() throws ExecutionException {
    getLink().setSourceInteraction(getNewSource());
    return CommandResult.newOKCommandResult(getLink());
  }

  /**
   * @generated
   */
  protected CommandResult reorientTarget() throws ExecutionException {
    getLink().setTargetInteraction(getNewTarget());
    return CommandResult.newOKCommandResult(getLink());
  }

  /**
   * @generated
   */
  protected RichBehavior getLink() {
    return (RichBehavior) getElementToEdit();
  }

  /**
   * @generated
   */
  protected Interaction getOldSource() {
    return (Interaction) oldEnd;
  }

  /**
   * @generated
   */
  protected Interaction getNewSource() {
    return (Interaction) newEnd;
  }

  /**
   * @generated
   */
  protected Interaction getOldTarget() {
    return (Interaction) oldEnd;
  }

  /**
   * @generated
   */
  protected Interaction getNewTarget() {
    return (Interaction) newEnd;
  }
}
