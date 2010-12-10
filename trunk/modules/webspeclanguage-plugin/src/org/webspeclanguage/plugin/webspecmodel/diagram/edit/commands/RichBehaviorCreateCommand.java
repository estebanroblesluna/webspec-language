package org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.RichBehavior;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelFactory;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies.WebspecmodelBaseItemSemanticEditPolicy;


/**
 * @generated
 */
public class RichBehaviorCreateCommand extends EditElementCommand {

  /**
   * @generated
   */
  private final EObject source;

  /**
   * @generated
   */
  private final EObject target;

  /**
   * @generated
   */
  private final Diagram container;

  /**
   * @generated
   */
  public RichBehaviorCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
    super(request.getLabel(), null, request);
    this.source = source;
    this.target = target;
    container = deduceContainer(source, target);
  }

  /**
   * @generated
   */
  public boolean canExecute() {
    if (source == null && target == null) {
      return false;
    }
    if (source != null && false == source instanceof Interaction) {
      return false;
    }
    if (target != null && false == target instanceof Interaction) {
      return false;
    }
    if (getSource() == null) {
      return true; // link creation is in progress; source is not defined yet
    }
    // target may be null here but it's possible to check constraint
    if (getContainer() == null) {
      return false;
    }
    return WebspecmodelBaseItemSemanticEditPolicy.LinkConstraints.canCreateRichBehavior_4002(getContainer(), getSource(), getTarget());
  }

  /**
   * @generated
   */
  protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
    if (!canExecute()) {
      throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
    }

    RichBehavior newElement = WebspecmodelFactory.eINSTANCE.createRichBehavior();
    getContainer().getTransitions().add(newElement);
    newElement.setSourceInteraction(getSource());
    newElement.setTargetInteraction(getTarget());
    doConfigure(newElement, monitor, info);
    ((CreateElementRequest) getRequest()).setNewElement(newElement);
    return CommandResult.newOKCommandResult(newElement);

  }

  /**
   * @generated
   */
  protected void doConfigure(RichBehavior newElement, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
    IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
    ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
    configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
    configureRequest.addParameters(getRequest().getParameters());
    configureRequest.setParameter(CreateRelationshipRequest.SOURCE, getSource());
    configureRequest.setParameter(CreateRelationshipRequest.TARGET, getTarget());
    ICommand configureCommand = elementType.getEditCommand(configureRequest);
    if (configureCommand != null && configureCommand.canExecute()) {
      configureCommand.execute(monitor, info);
    }
  }

  /**
   * @generated
   */
  protected void setElementToEdit(EObject element) {
    throw new UnsupportedOperationException();
  }

  /**
   * @generated
   */
  protected Interaction getSource() {
    return (Interaction) source;
  }

  /**
   * @generated
   */
  protected Interaction getTarget() {
    return (Interaction) target;
  }

  /**
   * @generated
   */
  public Diagram getContainer() {
    return container;
  }

  /**
   * Default approach is to traverse ancestors of the source to find instance of container.
   * Modify with appropriate logic.
   * @generated
   */
  private static Diagram deduceContainer(EObject source, EObject target) {
    // Find container element for the new link.
    // Climb up by containment hierarchy starting from the source
    // and return the first element that is instance of the container class.
    for (EObject element = source; element != null; element = element.eContainer()) {
      if (element instanceof Diagram) {
        return (Diagram) element;
      }
    }
    return null;
  }
}
