package webspecplugin.editor.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Link;
import webspecplugin.webspecmodel.Transition;

public class GMFCommandBuilder {

	private PreferencesHint hint;
	private List<Command> commands;
	private IGraphicalEditPart parent;
	private IDiagramEditDomain diagramEditDomain;
	private EditPartViewer currentViewer;

	public GMFCommandBuilder(PreferencesHint hint, IGraphicalEditPart parent,
			IDiagramEditDomain diagramEditDomain) {
		this.setHint(hint);
		this.parent = parent;
		this.diagramEditDomain = diagramEditDomain;
		this.setCommands(new ArrayList<Command>());
	}

	public Command build() {
		CompoundCommand cc = new CompoundCommand();
		for (Command command : this.getCommands()) {
			cc.add(command);
		}
		return cc;
	}

	public GMFCommandBuilder delete(EditPart part) {
		GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
		this.getCommands().add(part.getCommand(deleteReq));
		return this;
	}

	public GMFCommandBuilder createInteraction(Interaction interaction) {
		return createViewFromEObject(interaction);
	}

	public GMFCommandBuilder createInteraction(Interaction interaction,
			Point location) {
		this.createViewFromEObject(interaction, location);
		return this.createViewFromEObject(interaction.getRoot());
	}

	public GMFCommandBuilder createTransition(Transition transition) {
		return createConnectionFromEObject(transition);
	}

	public GMFCommandBuilder createTransition(Transition transition,
			Point location) {
		return createConnectionFromEObject(transition, location);
	}

	public GMFCommandBuilder createWidget(Link link, Point location) {
		return createViewFromEObject(link, location);
	}

	private GMFCommandBuilder createConnectionFromEObject(
			Transition transition, Point location) {
		CreateConnectionViewRequest request = new CreateConnectionViewRequest(
				transition, this.getHint());

		request.setLocation(location);
		request.setSourceEditPart(parent.findEditPart(parent, transition
				.getSourceInteraction()));
		request.setTargetEditPart(parent.findEditPart(parent, transition
				.getTargetInteraction()));
		Command createCmd = parent.getCommand(request);
		this.getCommands().add(createCmd);
		return this;
	}

	private GMFCommandBuilder createConnectionFromEObject(Transition transition) {
		DeferredCreateConnectionViewCommand command = new DeferredCreateConnectionViewCommand(
				(TransactionalEditingDomain) this.diagramEditDomain, 
				transition,
				new EObjectAdapter(transition.getSourceInteraction()), 
				new EObjectAdapter(transition.getTargetInteraction()),
				this.currentViewer, 
				this.getHint());

		this.getCommands().add(new ICommandProxy(command));

		return this;
	}

	private GMFCommandBuilder createViewFromEObject(EObject eobject,
			Point location) {
		CreateViewRequest request = new CreateViewRequest(eobject, this
				.getHint());

		request.setLocation(location);
		Command createCmd = parent.getCommand(request);
		this.getCommands().add(createCmd);
		return this;
	}

	private GMFCommandBuilder createViewFromEObject(EObject eobject) {
		CreateViewRequest request = new CreateViewRequest(eobject, this
				.getHint());

		Command createCmd = parent.getCommand(request);
		this.getCommands().add(createCmd);
		return this;
	}

	public void execute() {
		this.diagramEditDomain.getDiagramCommandStack().execute(this.build());
		this.reset();
	}

	private void reset() {
		this.getCommands().clear();
	}

	// @SuppressWarnings("unchecked")
	// public EObject getEObjectOfRequest(String name) {
	// if (this.getRequests().containsKey(name)) {
	// CreateViewRequest request = this.getRequests().get(name);
	// CreateViewAndElementRequest.ViewAndElementDescriptor descriptor =
	// (CreateViewAndElementRequest.ViewAndElementDescriptor) ((List)
	// request.getNewObject()).get(0);
	// return descriptor.getCreateElementRequestAdapter().resolve();
	//			
	// } else {
	// CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor
	// request =
	// (CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor
	// )
	// this.getConnectionRequests().get(name).getCommandResult().getReturnValue
	// ();
	// return request.getCreateElementRequestAdapter().resolve();
	// }
	// }
	// public GMFCommandBuilder createInteraction(String name, EditPart parent)
	// {
	// CreateViewRequest request =
	// org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory
	// .getCreateShapeRequest(
	// WebspecmodelElementTypes.Interaction_1001,
	// this.getHint());
	//
	// this.getRequests().put(name, request);
	// Command createCmd = parent.getCommand(request);
	// this.getCommands().add(createCmd);
	// return this;
	// }
	//	
	// public EditPart getViewResult(String name) {
	// return (EditPart)
	// this.getViewer().getEditPartRegistry().get(this.getAdaptableFromRequest
	// (name).getAdapter(View.class));
	// }
	//	
	// public GMFCommandBuilder createButton(String name, EditPart parent, Point
	// point) {
	// return this.createWidget(
	// name,
	// parent,
	// point,
	// WebspecmodelElementTypes.Button_2002);
	// }
	//
	// public GMFCommandBuilder createLink(String name, EditPart parent, Point
	// point) {
	// return this.createWidget(
	// name,
	// parent,
	// point,
	// WebspecmodelElementTypes.Link_2006);
	// }
	//
	// public GMFCommandBuilder createWidget(String name, EditPart parent, Point
	// point, IElementType element) {
	// CreateViewRequest createButtonRequest =
	// org.eclipse.gmf.runtime.diagram.ui
	// .requests.CreateViewRequestFactory.getCreateShapeRequest(
	// element,
	// this.getHint());
	// createButtonRequest.setLocation(point);
	// Command createCmd = parent.getCommand(createButtonRequest);
	// this.getRequests().put(name, createButtonRequest);
	// this.getCommands().add(createCmd);
	// return this;
	// }
	//
	// public GMFCommandBuilder createNavigation(String name, String from,
	// String to) {
	// return this.createNavigation(
	// name,
	// this.getAdaptableFromRequest(from),
	// this.getAdaptableFromRequest(to));
	// }
	//	
	// public GMFCommandBuilder createNavigation(String name, IAdaptable from,
	// String to) {
	// return this.createNavigation(
	// name,
	// from,
	// this.getAdaptableFromRequest(to));
	// }
	//
	// public GMFCommandBuilder createNavigation(String name, IAdaptable from,
	// IAdaptable to) {
	// DeferredCreateConnectionViewAndElementCommand command = new
	// DeferredCreateConnectionViewAndElementCommand(
	// new CreateConnectionViewAndElementRequest(
	// WebspecmodelElementTypes.Navigation_3001,
	// ((IHintedType)
	// WebspecmodelElementTypes.Navigation_3001).getSemanticHint(),
	// this.getHint()),
	// from,
	// to,
	// this.getViewer());
	//		
	// this.getCommands().add(new ICommandProxy(command));
	//		
	// this.getConnectionRequests().put(name, command);
	// return this;
	// }

	// @SuppressWarnings("unchecked")
	// private IAdaptable getAdaptableFromRequest(String req) {
	// List list = (List) this.getRequests().get(req).getNewObject();
	// return (IAdaptable) list.get(0);
	// }

	private PreferencesHint getHint() {
		return hint;
	}

	private void setHint(PreferencesHint hint) {
		this.hint = hint;
	}

	private List<Command> getCommands() {
		return commands;
	}

	private void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
