package org.webspeclanguage.plugin.editor.refactoring;

import org.eclipse.gmf.runtime.notation.impl.EdgeImpl;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.Navigation;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationEditPart;


public class AddProcessingInteraction extends AbstractRefactoring {

	private NavigationEditPart navigationEditPart;
	private Interaction intermediateInteraction;
	private Navigation intermediateNavigation1;
	
	public AddProcessingInteraction(NavigationEditPart navigationEditPart) {
		this.initializeModelCommandBuilder(navigationEditPart.getEditingDomain());
		this.initializeUICommandBuilder(
				navigationEditPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(navigationEditPart), 
				navigationEditPart.getDiagramEditDomain());

		this.navigationEditPart = navigationEditPart;
	}

	@Override
	protected void createModel() {
		Diagram diagram = this.getDiagramOf(this.getNavigation());
		Navigation navigation = this.getNavigation();
		
		intermediateInteraction = this.getModelCommandBuilder().createInteraction(diagram);
		intermediateNavigation1 = this.getModelCommandBuilder().createNavigation(diagram, this.getNavigation().getSourceInteraction(), intermediateInteraction);
		this.getModelCommandBuilder().createNavigation(diagram, intermediateInteraction, navigation.getTargetInteraction());
		
		this.getModelCommandBuilder()
			.copyTransitionAttributes(navigation, intermediateNavigation1)
			.delete(navigation)
			.execute();
	}

	@Override
	protected void createUI() {
//		this.getUiCommandBuilder()
//			.createInteraction(this.intermediateInteraction, new Point(0,0))
//			.createTransition(this.intermediateNavigation1, new Point(0,0))
//			.createTransition(this.intermediateNavigation2, new Point(0,0))
//			.delete(this.navigationEditPart)
//			.execute();		
	}
	
	private Navigation getNavigation() {
		return (Navigation) ((EdgeImpl)this.navigationEditPart.getModel()).getElement();
	}
}
