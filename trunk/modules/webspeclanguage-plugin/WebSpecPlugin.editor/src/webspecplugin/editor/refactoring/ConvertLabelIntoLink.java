package webspecplugin.editor.refactoring;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Label;
import webspecplugin.webspecmodel.Link;
import webspecplugin.webspecmodel.Navigation;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;

public class ConvertLabelIntoLink extends AbstractRefactoring {

	private LabelEditPart labelPart;
	private Point newLinkLocation;
	
	private Link link;
	private Navigation navigation;
	private Interaction sourceInteraction;
	private Interaction destinationInteraction;
	
	public ConvertLabelIntoLink(LabelEditPart labelPart) {
		this.initializeModelCommandBuilder(labelPart.getEditingDomain());
		this.initializeUICommandBuilder(
				labelPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(labelPart), 
				labelPart.getDiagramEditDomain());

		this.labelPart = labelPart;
	}
	
	protected void prepare() {
		newLinkLocation = labelPart.getFigure().getBounds().getTopLeft().getCopy();
		labelPart.getFigure().translateToAbsolute(newLinkLocation);
	}

	@Override
	protected void createModel() {
		Label label = this.getLabel();
		Diagram diagram = this.getDiagramOf(label);
		
		sourceInteraction = this.getInteractionOf(label);
		destinationInteraction = this.getModelCommandBuilder().createInteraction(diagram);
		navigation = this.getModelCommandBuilder().createNavigation(diagram, sourceInteraction, destinationInteraction);
		link = this.getModelCommandBuilder().createLink(sourceInteraction.getRoot());

		this.getModelCommandBuilder()
			.set(	link, 
					WebspecmodelPackage.eINSTANCE.getWidget_Name(), 
					label.getName())
			.set(	link, 
					WebspecmodelPackage.eINSTANCE.getWidget_Location(), 
					label.getLocation())
			.delete(label)
			.set(	navigation, 
					WebspecmodelPackage.eINSTANCE.getTransition_Actions(), 
					"click(" + sourceInteraction.getName() + "." + label.getName() + ")")
			.execute();
	}
	
	@Override
	protected void createUI() {
//		this.getUiCommandBuilder()
//			.createInteraction(this.destinationInteraction, new Point(0,0))
//			.createWidget(this.link, newLinkLocation)
//			.delete(this.labelPart)
//			.createTransition(this.navigation, new Point(0,0))
//			.execute();
	}

	private Label getLabel() {
		return (Label) ((NodeImpl)this.labelPart.getModel()).getElement();
	}
}
