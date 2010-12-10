package webspecplugin.editor.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Link;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.Transition;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.Widget;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;

public class SplitInteraction extends AbstractRefactoring {

	private InteractionEditPart interactionEditPart;
	private int steps;
	private List<List<Widget>> widgetsToSteps;
	
	private String interactionNamePattern;
	private String linkForwardNamePattern;
	private String linkPreviousNamePattern;
	
	public SplitInteraction(InteractionEditPart interactionEditPart, int steps, List<List<Widget>> widgetsToSteps) {
		this.initializeModelCommandBuilder(interactionEditPart.getEditingDomain());
		this.initializeUICommandBuilder(
				interactionEditPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(interactionEditPart), 
				interactionEditPart.getDiagramEditDomain());
		
		this.interactionEditPart = interactionEditPart;
		this.widgetsToSteps = widgetsToSteps;
		this.steps = steps;
	}
	
	@Override
	protected void createModel() {
		Diagram diagram = this.getDiagramOf(this.getInteraction());
		Interaction interaction = this.getInteraction();
		
		List<Interaction> newInteractions = new ArrayList<Interaction>();
		
		//create all interactions
		for (int i = 0; i < this.steps; i++) {
			Interaction newInteraction = this.getModelCommandBuilder().createInteraction(diagram);
			newInteractions.add(newInteraction);
			
			String name = this.getInteractionNamePattern().replaceAll("\\$\\{step\\}", Integer.valueOf(i).toString());
			this.getModelCommandBuilder().setName(newInteraction, name);
		}
		
		//add bidirectional links
		for (int i = 0; i < this.steps - 1; i++) {
			Interaction from = newInteractions.get(i);
			Interaction to = newInteractions.get(i + 1);
			Navigation fromTo = this.getModelCommandBuilder().createNavigation(diagram, from, to);
			Navigation toFrom = this.getModelCommandBuilder().createNavigation(diagram, to, from);
			
			Link linkFromTo = this.getModelCommandBuilder().createLink(from.getRoot());
			Link linkToFrom = this.getModelCommandBuilder().createLink(to.getRoot());
			
			String nameFromTo = this.getLinkForwardNamePattern().replaceAll("\\$\\{step\\}", Integer.valueOf(i).toString());
			String nameToFrom = this.getLinkPreviousNamePattern().replaceAll("\\$\\{step\\}", Integer.valueOf(i).toString());

			this.getModelCommandBuilder().setName(linkFromTo, nameFromTo);
			this.getModelCommandBuilder().setName(linkToFrom, nameToFrom);

			String nameFrom = this.getInteractionNamePattern().replaceAll("\\$\\{step\\}", Integer.valueOf(i).toString());
			String nameTo = this.getInteractionNamePattern().replaceAll("\\$\\{step\\}", Integer.valueOf(i+1).toString());

			this.getModelCommandBuilder().setActions(fromTo, "click(" + nameFrom + "." + nameFromTo + ")");
			this.getModelCommandBuilder().setActions(toFrom, "click(" + nameTo   + "." + nameToFrom + ")");
		}
		
		//add all target navigations to the first interaction
		Collection<EStructuralFeature.Setting> references = EcoreUtil.UsageCrossReferencer.find(interaction, diagram);
		Interaction firstInteraction = newInteractions.get(0);
		for (EStructuralFeature.Setting setting : references) {
			Transition transition = (Transition) setting.getEObject();
			Transition copy = null;
			if (transition instanceof Navigation) {
				copy = this.getModelCommandBuilder().createNavigation(
						diagram, 
						transition.getSourceInteraction(), 
						firstInteraction);
			} else {
				copy = this.getModelCommandBuilder().createRichBehavior(
						diagram, 
						transition.getSourceInteraction(), 
						firstInteraction);
			}
			this.getModelCommandBuilder().copyTransitionAttributes(transition, copy);
		}
		
		//add all source navigations to the last interaction
		Interaction lastInteraction = newInteractions.get(newInteractions.size() - 1);
		for (Object o : interaction.getForwardTransitions()) {
			Transition transition = (Transition) o;
			Transition copy = null;
			if (transition instanceof Navigation) {
				copy = this.getModelCommandBuilder().createNavigation(
						diagram, 
						lastInteraction, 
						transition.getTargetInteraction());
			} else {
				copy = this.getModelCommandBuilder().createRichBehavior(
						diagram, 
						lastInteraction, 
						transition.getTargetInteraction());
			}
			this.getModelCommandBuilder().copyTransitionAttributes(transition, copy);
		}
		
		//move all widgets to its corresponding interaction
		int index = 0;
		for (List<Widget> widgets: this.widgetsToSteps) {
			Interaction currentInteraction = newInteractions.get(index);
			
			for (Widget widget : widgets) {
				this.getModelCommandBuilder()
					.add(	currentInteraction.getRoot(), 
							WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
							widget);
			}
			
			index++;
		}
		
		//delete old interaction and execute
		this.getModelCommandBuilder()
			.delete(interaction)
			.execute();
	}

	@Override
	protected void createUI() {
		
	}
	
	private Interaction getInteraction() {
		return (Interaction) ((NodeImpl)this.interactionEditPart.getModel()).getElement();
	}

	public String getInteractionNamePattern() {
		if (this.interactionNamePattern == null) {
			return "Step${step}";
		}
		return interactionNamePattern;
	}

	public void setInteractionNamePattern(String interactionNamePattern) {
		this.interactionNamePattern = interactionNamePattern;
	}

	public String getLinkForwardNamePattern() {
		if (this.linkPreviousNamePattern == null) {
			return "forward";
		}
		return linkForwardNamePattern;
	}

	public void setLinkForwardNamePattern(String linkForwardNamePattern) {
		this.linkForwardNamePattern = linkForwardNamePattern;
	}

	public String getLinkPreviousNamePattern() {
		if (this.linkPreviousNamePattern == null) {
			return "previous";
		}
		return linkPreviousNamePattern;
	}

	public void setLinkPreviousNamePattern(String linkPreviousNamePattern) {
		this.linkPreviousNamePattern = linkPreviousNamePattern;
	}
}
