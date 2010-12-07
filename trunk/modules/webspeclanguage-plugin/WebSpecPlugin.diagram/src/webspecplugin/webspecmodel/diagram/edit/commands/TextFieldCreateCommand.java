package webspecplugin.webspecmodel.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.Container;
import webspecplugin.webspecmodel.TextField;
import webspecplugin.webspecmodel.WebspecmodelFactory;
import webspecplugin.webspecmodel.WebspecmodelPackage;

/**
 * @generated
 */
public class TextFieldCreateCommand extends EditElementCommand {

  /**
   * @generated
   */
  public TextFieldCreateCommand(CreateElementRequest req) {
    super(req.getLabel(), null, req);
  }

  /**
   * @generated
   */
  protected EObject getElementToEdit() {
    EObject container = ((CreateElementRequest) getRequest()).getContainer();
    if (container instanceof View) {
      container = ((View) container).getElement();
    }
    return container;
  }

  /**
   * @generated
   */
  public boolean canExecute() {
    return true;

  }

  /**
   * @generated
   */
  protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
    TextField newElement = WebspecmodelFactory.eINSTANCE.createTextField();

    Container owner = (Container) getElementToEdit();
    owner.getWidgets().add(newElement);

    doConfigure(newElement, monitor, info);

    ((CreateElementRequest) getRequest()).setNewElement(newElement);
    return CommandResult.newOKCommandResult(newElement);
  }

  /**
   * @generated
   */
  protected void doConfigure(TextField newElement, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
    IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
    ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
    configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
    configureRequest.addParameters(getRequest().getParameters());
    ICommand configureCommand = elementType.getEditCommand(configureRequest);
    if (configureCommand != null && configureCommand.canExecute()) {
      configureCommand.execute(monitor, info);
    }
  }

}
