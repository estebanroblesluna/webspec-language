package webspecplugin.editor.util;

import java.util.ArrayList;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import webspecplugin.webspecmodel.Button;
import webspecplugin.webspecmodel.Container;
import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Label;
import webspecplugin.webspecmodel.Link;
import webspecplugin.webspecmodel.ListOfContainer;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.RichBehavior;
import webspecplugin.webspecmodel.TextField;
import webspecplugin.webspecmodel.Transition;
import webspecplugin.webspecmodel.WebspecmodelFactory;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.Widget;

@SuppressWarnings("unchecked")
public class EMFCommandBuilder {

	private CompoundCommand command;
	private EditingDomain editingDomain;

	public EMFCommandBuilder(EditingDomain editingDomain) {
		this.setCommand(new CompoundCommand());
		this.setEditingDomain(editingDomain);
	}
	
	public EMFCommandBuilder set(EObject object, Object attribute, Object value) {
		Command setCommand = SetCommand.create(
				this.getEditingDomain(), 
				object, 
				attribute, 
				value);

		this.getCommand().append(setCommand);
		return this;
	}
	
	public EObject copy(EObject object) {
		Command command = CopyCommand.create(
				this.getEditingDomain(), 
				object);

		this.getEditingDomain().getCommandStack().execute(command);

		EObject newObject = (EObject) command.getResult().iterator().next();
		return newObject;
	}
	
	public EMFCommandBuilder add(EObject object, Object attribute, Object value) {
		Command setCommand = AddCommand.create(
				this.getEditingDomain(), 
				object, 
				attribute, 
				value);

		this.getCommand().append(setCommand);
		return this;
	}
	
	public EMFCommandBuilder delete(EObject object) {
		Command setCommand = DeleteCommand.create(
				this.getEditingDomain(), 
				object);

		this.getCommand().append(setCommand);
		return this;
	}
	
	public Button createButton(Container container) {
		Button button = WebspecmodelFactory.eINSTANCE.createButton();
		this.add(	container, 
					WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
					button);
		return button;
	}
	
	public Label createLabel(Container container) {
		Label label = WebspecmodelFactory.eINSTANCE.createLabel();
		this.add(	container, 
					WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
					label);
		return label;
	}
	
	public Link createLink(Container container) {
		Link link = WebspecmodelFactory.eINSTANCE.createLink();
		this.add(	container, 
					WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
					link);
		return link;
	}

	public ListOfContainer createListOfContainer(Container container) {
		ListOfContainer listOfContainer = WebspecmodelFactory.eINSTANCE.createListOfContainer();
		this.add(	container, 
					WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
					listOfContainer);
		return listOfContainer;
	}

	public TextField createTextField(Container container) {
		return WebspecmodelFactory.eINSTANCE.createTextField();
	}

	public RIAFeature createRIAFeature(Diagram diagram) {
		RIAFeature riaFeature = WebspecmodelFactory.eINSTANCE.createRIAFeature();
		this.add(	diagram, 
					WebspecmodelPackage.eINSTANCE.getDiagram_RiaFeatures(), 
					riaFeature);
		return riaFeature;
	}
	
	public Interaction createInteraction(Diagram diagram) {
		Interaction interaction = WebspecmodelFactory.eINSTANCE.createInteraction();
		this.add(	diagram, 
					WebspecmodelPackage.eINSTANCE.getDiagram_Interactions(), 
					interaction);
		interaction.setRoot(WebspecmodelFactory.eINSTANCE.createPanelContainer()); 
		return interaction;
	}
	
	public Navigation createNavigation(Diagram diagram, Interaction from, Interaction to) {
		Navigation transition = WebspecmodelFactory.eINSTANCE.createNavigation();
		this.createTransition(transition, diagram, from, to);
		return transition;
	}
	
	public RichBehavior createRichBehavior(Diagram diagram, Interaction from, Interaction to) {
		RichBehavior transition = WebspecmodelFactory.eINSTANCE.createRichBehavior();
		this.createTransition(transition, diagram, from, to);
		return transition;
	}
	
	private void createTransition(Transition transition, Diagram diagram, Interaction from, Interaction to) {
		this.set(	transition, 
					WebspecmodelPackage.eINSTANCE.getTransition_SourceInteraction(), 
					from);
		this.set(	transition, 
					WebspecmodelPackage.eINSTANCE.getTransition_TargetInteraction(), 
					to);
		this.add(	diagram, 
					WebspecmodelPackage.eINSTANCE.getDiagram_Transitions(), 
					transition);
	}
	
	public EMFCommandBuilder copyTransitionAttributes(Transition from, Transition to) {
		this.set(	to, 
					WebspecmodelPackage.eINSTANCE.getTransition_Actions(), 
					from.getActions());
		this.set(	to, 
					WebspecmodelPackage.eINSTANCE.getTransition_Precondition(), 
					from.getPrecondition());
		return this;
	}
	
	public EMFCommandBuilder setName(Interaction interaction, String name) {
		this.set(	interaction, 
					WebspecmodelPackage.eINSTANCE.getInteraction_Name(), 
					name);
		return this;
	}
	
	public EMFCommandBuilder setRoot(Interaction interaction, PanelContainer root) {
		this.set(	interaction, 
					WebspecmodelPackage.eINSTANCE.getInteraction_Root(), 
					root);
		return this;
	}

	
	public EMFCommandBuilder setName(RIAFeature riaFeature, String name) {
		this.set(	riaFeature, 
					WebspecmodelPackage.eINSTANCE.getRIAFeature_Name(), 
					name);
		return this;
	}
	
	public EMFCommandBuilder setInvariant(Interaction interaction, String invariant) {
		this.set(	interaction, 
					WebspecmodelPackage.eINSTANCE.getInteraction_Invariant(), 
					invariant);
		return this;
	}
	
	public EMFCommandBuilder setName(Widget widget, String name) {
		this.set(	widget, 
					WebspecmodelPackage.eINSTANCE.getWidget_Name(), 
					name);
		return this;
	}

	public EMFCommandBuilder setActions(Transition transition, String actions) {
		this.set(	transition, 
					WebspecmodelPackage.eINSTANCE.getTransition_Actions(), 
					actions);
		return this;
	}
	
	public EMFCommandBuilder setPrecondition(Transition transition, String precondition) {
		this.set(	transition, 
					WebspecmodelPackage.eINSTANCE.getTransition_Precondition(), 
					precondition);
		return this;
	}
	
	public EMFCommandBuilder addInteraction(RIAFeature riaFeature, Interaction interaction) {
		ArrayList l = new ArrayList();
		for (Object o : riaFeature.getInteractions()) {
			l.add(o);
		}
		l.add(interaction);
		this.set(	riaFeature, 
					WebspecmodelPackage.eINSTANCE.getRIAFeature_Interactions(), 
					l);
		return this;
	}
	
	public EMFCommandBuilder setVisible(RIAFeature riaFeature, boolean visible) {
		this.set(	riaFeature, 
					WebspecmodelPackage.eINSTANCE.getRIAFeature_Visible(), 
					visible);
		return this;
	}
	
	public void execute() {
		this.getEditingDomain().getCommandStack().execute(this.build());
		this.reset();
	}

	private void reset() {
		this.setCommand(new CompoundCommand());
	}

	public CompoundCommand build() {
		return this.getCommand();
	}
	
	private CompoundCommand getCommand() {
		return command;
	}

	private void setCommand(CompoundCommand command) {
		this.command = command;
	}

	private EditingDomain getEditingDomain() {
		return editingDomain;
	}

	private void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
}
