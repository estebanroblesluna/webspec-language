package org.webspeclanguage.plugin.editor.ria;

import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.webspeclanguage.plugin.editor.refactoring.AbstractRefactoring;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.Label;
import org.webspeclanguage.plugin.webspecmodel.RIAFeature;
import org.webspeclanguage.plugin.webspecmodel.RichBehavior;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelEditPart;


public class HoverDetail extends AbstractRefactoring {

	private LabelEditPart labelEditPart;
	
	public HoverDetail(LabelEditPart labelPart) {
		this.initializeModelCommandBuilder(labelPart.getEditingDomain());
		this.initializeUICommandBuilder(
				labelPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(labelPart), 
				labelPart.getDiagramEditDomain());

		this.labelEditPart = labelPart;
	}
	
	@Override
	protected void createModel() {
		Label label = this.getLabel();
		Diagram diagram = this.getDiagramOf(label);
		Interaction sourceInteraction = this.getInteractionOf(label);		
		
		String hoverInteractionName = sourceInteraction.getName() + label.getName() + "Hover";

		Interaction hoverInteraction = this.getModelCommandBuilder().createInteraction(diagram);
		this.getModelCommandBuilder().setName(hoverInteraction, hoverInteractionName);
		
		RichBehavior hoverRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, sourceInteraction, hoverInteraction);
		this.getModelCommandBuilder().setActions(hoverRichBehavior, 
				"mouseOver(" + sourceInteraction.getName() + "." + label.getName() + ");\n" +
				"wait(300)");
		
		RichBehavior hoverOutRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, hoverInteraction, sourceInteraction);
		this.getModelCommandBuilder().setActions(hoverOutRichBehavior, 
				"mouseOut(" +hoverInteractionName + "." + label.getName() + ");\n" +
				"wait(300)");
		
		RIAFeature riaFeature = this.getModelCommandBuilder().createRIAFeature(diagram);

		this.getModelCommandBuilder()
			.setName(riaFeature, sourceInteraction.getName() + " " + label.getName() + " Hover detail")
			.addInteraction(riaFeature, hoverInteraction)
			.setVisible(riaFeature, true)
			.execute();
	}

	@Override
	protected void createUI() {
		
	}
	
	private Label getLabel() {
		return (Label) ((NodeImpl)this.labelEditPart.getModel()).getElement();
	}
}
