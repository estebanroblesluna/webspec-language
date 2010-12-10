package webspecplugin.editor.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.Transition;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;

public class MergeInteractions extends AbstractRefactoring {

	private List<InteractionEditPart> interactionEditParts;
	private Interaction newInteraction;
	private List<Interaction> interactions;
	private List<Transition> newTransitions;
	
	public MergeInteractions(List<InteractionEditPart> interactionEditParts) {
		InteractionEditPart interactionEditPart = interactionEditParts.get(0);
		
		this.initializeModelCommandBuilder(interactionEditPart.getEditingDomain());
		this.initializeUICommandBuilder(
				interactionEditPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(interactionEditPart), 
				interactionEditPart.getDiagramEditDomain());

		this.interactionEditParts = interactionEditParts;
		this.newTransitions = new ArrayList<Transition>();
	}
	
	@Override
	protected void createModel() {
		interactions = this.getInteractions();
		Diagram diagram = this.getDiagramOf(interactions.get(0));
		
		newInteraction = this.getModelCommandBuilder().createInteraction(diagram);

		Map<EObject, Collection<EStructuralFeature.Setting>> map = EcoreUtil.UsageCrossReferencer.findAll(interactions, diagram);
		
		//move all target transitions
		for (Interaction interaction : interactions) {
			for (EStructuralFeature.Setting setting : map.get(interaction)) {
				Transition transition = (Transition) setting.getEObject();
				if (!interactions.contains(transition.getSourceInteraction()) && interactions.contains(transition.getTargetInteraction())) {
					Transition copy = null;
					if (transition instanceof Navigation) {
						copy = this.getModelCommandBuilder().createNavigation(
								diagram, 
								transition.getSourceInteraction(), 
								newInteraction);
					} else {
						copy = this.getModelCommandBuilder().createRichBehavior(
								diagram, 
								transition.getSourceInteraction(), 
								newInteraction);
					}
					
					this.getModelCommandBuilder().copyTransitionAttributes(transition, copy);
					newTransitions.add(copy);
				}
			}
		}
		
		//move all source transitions
		for (Interaction interaction : interactions) {
			for (Object t : interaction.getForwardTransitions()) {
				Transition transition = (Transition) t;
				if (interactions.contains(transition.getSourceInteraction()) && !interactions.contains(transition.getTargetInteraction())) {
					Transition copy = null;
					if (transition instanceof Navigation) {
						copy = this.getModelCommandBuilder().createNavigation(
								diagram, 
								newInteraction, 
								transition.getTargetInteraction());
					} else {
						copy = this.getModelCommandBuilder().createRichBehavior(
								diagram, 
								newInteraction, 
								transition.getTargetInteraction());
					}
					this.getModelCommandBuilder().copyTransitionAttributes(transition, copy);
					newTransitions.add(copy);
				}
			}
		}
		
		// move all root panels for each interaction
		for (Interaction interaction : interactions) {
			this.getModelCommandBuilder()
				.add(	newInteraction.getRoot(), 
						WebspecmodelPackage.eINSTANCE.getContainer_Widgets(), 
						interaction.getRoot());
		}
		
		// remove all interactions
		for (Interaction interaction : interactions) {
			this.getModelCommandBuilder()
				.delete(interaction);
		}

		this.getModelCommandBuilder().execute();
	}

	@Override
	protected void createUI() {
//		this.getUiCommandBuilder()
//			.createInteraction(this.newInteraction, new Point(0,0));
//		
//		for (InteractionEditPart interactionEditPart : this.interactionEditParts) {
//			this.getUiCommandBuilder().delete(interactionEditPart);
//		}
//		
//		for (Transition transition : this.newTransitions) {
//			this.getUiCommandBuilder().createTransition(transition);
//		}
//		this.getUiCommandBuilder().execute();
	}
	
	private List<Interaction> getInteractions() {
		List<Interaction> interactions = new ArrayList<Interaction>();
		for (InteractionEditPart interactionEditPart : this.interactionEditParts) {
			interactions.add(this.getInteractionOf(interactionEditPart));
		}
		return interactions;
	}

	private Interaction getInteractionOf(InteractionEditPart interactionEditPart) {
		return (Interaction) ((NodeImpl)interactionEditPart.getModel()).getElement();
	}
}
