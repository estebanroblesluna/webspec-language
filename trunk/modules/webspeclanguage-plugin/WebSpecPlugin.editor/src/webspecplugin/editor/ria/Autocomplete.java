package webspecplugin.editor.ria;

import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import webspecplugin.editor.refactoring.AbstractRefactoring;
import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Label;
import webspecplugin.webspecmodel.ListOfContainer;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.RIAFeature;
import webspecplugin.webspecmodel.RichBehavior;
import webspecplugin.webspecmodel.TextField;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;

public class Autocomplete extends AbstractRefactoring {

	private TextFieldEditPart textFieldEditPart;
	
	private int maxIndex;
	private String itemName;
	private String cssSelected;
	
	public Autocomplete(TextFieldEditPart textFieldEditPart, int maxIndex, String itemName, String cssSelected) {
		this.initializeModelCommandBuilder(textFieldEditPart.getEditingDomain());
		this.initializeUICommandBuilder(
				textFieldEditPart.getDiagramPreferencesHint(), 
				getDiagramEditPartOf(textFieldEditPart), 
				textFieldEditPart.getDiagramEditDomain());

		this.textFieldEditPart = textFieldEditPart;
		this.maxIndex = maxIndex;
		this.itemName = itemName;
		this.cssSelected = cssSelected;
	}
	
	@Override
	protected void createModel() {
		TextField textField = this.getTextField();
		Diagram diagram = this.getDiagramOf(textField);
		Interaction sourceInteraction = this.getInteractionOf(textField);

		String newInteractionName = sourceInteraction.getName() + textField.getName() + "AutoComplete";
		String newInteractionWithValueName = sourceInteraction.getName() + textField.getName() + "AutoCompleteSelected";
		String autoCompleteIndexVar = sourceInteraction.getName() + textField.getName() + "SelectionIndex";

		PanelContainer newInteractionPanelContainer = (PanelContainer) this.getModelCommandBuilder().copy(sourceInteraction.getRoot());
		Interaction newInteraction = this.getModelCommandBuilder().createInteraction(diagram);
		this.getModelCommandBuilder().setName(newInteraction, newInteractionName);
		this.getModelCommandBuilder().setInvariant(newInteraction, "style(" + newInteractionWithValueName + "." + this.itemName + "[" + this.asVar(autoCompleteIndexVar)+ "]) = \"" + this.cssSelected + "\"");
		this.getModelCommandBuilder().setRoot(newInteraction, newInteractionPanelContainer);
		
		PanelContainer newInteractionWithValuePanelContainer = (PanelContainer) this.getModelCommandBuilder().copy(sourceInteraction.getRoot());
		Interaction newInteractionWithValue = this.getModelCommandBuilder().createInteraction(diagram);
		this.getModelCommandBuilder().setName(newInteractionWithValue, newInteractionWithValueName);
		this.getModelCommandBuilder().setInvariant(newInteractionWithValue, newInteractionName + "." + textField.getName() + " != \"\"");
		this.getModelCommandBuilder().setRoot(newInteractionWithValue, newInteractionWithValuePanelContainer);
		
		ListOfContainer listOfContainer = this.getModelCommandBuilder().createListOfContainer(newInteractionPanelContainer);
		Label label = this.getModelCommandBuilder().createLabel(listOfContainer);
		this.getModelCommandBuilder().setName(label, this.itemName);
		
		RichBehavior initialRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, sourceInteraction, newInteraction);
		this.getModelCommandBuilder().setActions(initialRichBehavior, 
				this.asVar(autoCompleteIndexVar) + ":= 0;\n" +
				"wait(300)");
		
		RichBehavior moveUpRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, newInteraction, newInteraction);
		this.getModelCommandBuilder()
			.setPrecondition(moveUpRichBehavior, this.asVar(autoCompleteIndexVar) + " > 0")
			.setActions(moveUpRichBehavior, 
				autoCompleteIndexVar + " := " + this.asVar(autoCompleteIndexVar) + " - 1;\n" +
				"sendKey(UP_ARROW)");

		
		RichBehavior moveDownRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, newInteraction, newInteraction);
		this.getModelCommandBuilder()
			.setPrecondition(moveDownRichBehavior, this.asVar(autoCompleteIndexVar) + " < " + Integer.valueOf(this.maxIndex).toString())
			.setActions(moveDownRichBehavior, 
				autoCompleteIndexVar + " := " + this.asVar(autoCompleteIndexVar) + " + 1;\n" +
				"sendKey(DOWN_ARROW)");

		RichBehavior clickOutsideRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, newInteraction, sourceInteraction);
		this.getModelCommandBuilder()
			.setActions(clickOutsideRichBehavior, 
				"sendKey(ESC)");
		
		RichBehavior selectItemRichBehavior = this.getModelCommandBuilder().createRichBehavior(diagram, newInteraction, newInteractionWithValue);
		this.getModelCommandBuilder()
			.setActions(selectItemRichBehavior,
				"sendKey(ENTER)");

		RIAFeature riaFeature = this.getModelCommandBuilder().createRIAFeature(diagram);

		this.getModelCommandBuilder()
			.addInteraction(riaFeature, newInteraction)
			.addInteraction(riaFeature, newInteractionWithValue)
			.setVisible(riaFeature, true)
			.setName(riaFeature, sourceInteraction.getName() + " " + textField.getName() + " Autocomplete")
			.execute();
	}

	@Override
	protected void createUI() {
		
	}
	
	private TextField getTextField() {
		return (TextField) ((NodeImpl)this.textFieldEditPart.getModel()).getElement();
	}
}
