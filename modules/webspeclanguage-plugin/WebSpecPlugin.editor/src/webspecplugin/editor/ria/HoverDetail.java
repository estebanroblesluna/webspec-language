package webspecplugin.editor.ria;

import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import webspecplugin.editor.refactoring.AbstractRefactoring;
import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Label;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.RichBehavior;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;

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
