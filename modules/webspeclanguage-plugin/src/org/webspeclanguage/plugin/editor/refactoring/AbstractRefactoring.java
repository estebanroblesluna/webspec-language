package org.webspeclanguage.plugin.editor.refactoring;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.webspeclanguage.plugin.editor.util.EMFCommandBuilder;
import org.webspeclanguage.plugin.editor.util.GMFCommandBuilder;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.DiagramEditPart;


public abstract class AbstractRefactoring {

	private EMFCommandBuilder modelCommandBuilder;
	private GMFCommandBuilder uiCommandBuilder;
	
	protected AbstractRefactoring() {
		
	}
	
	protected void initializeModelCommandBuilder(EditingDomain editingDomain) {
		this.setModelCommandBuilder(new EMFCommandBuilder(editingDomain));
	}
	
	protected void initializeUICommandBuilder(
			PreferencesHint hint, 
			IGraphicalEditPart parent,
			IDiagramEditDomain diagramEditDomain) {
		this.setUiCommandBuilder(new GMFCommandBuilder(hint, parent, diagramEditDomain));
	}

	public void execute() {
		this.prepare();
		this.createModel();
		this.createUI();
		this.postExecute();
	}
	
	protected void prepare() {
		
	}
	
	protected void postExecute() {
		
	}
	
	protected abstract void createModel();

	protected abstract void createUI();

	protected Interaction getInteractionOf(EObject eObject) {
		EObject current = eObject;
		while (current != null && !(current instanceof Interaction)) {
			current = current.eContainer();
		}
		return (Interaction) current;
	}
	
	protected Diagram getDiagramOf(EObject eObject) {
		EObject current = eObject;
		while (current != null && !(current instanceof Diagram)) {
			current = current.eContainer();
		}
		return (Diagram) current;
	}
	
	protected DiagramEditPart getDiagramEditPartOf(EditPart part) {
		EditPart current = part;
		while (current != null && !(current instanceof DiagramEditPart)) {
			current = current.getParent();
		}
		return (DiagramEditPart) current;
	}
	
	protected String asVar(String variableName) {
		return "${" + variableName + "}";
	}

	protected EMFCommandBuilder getModelCommandBuilder() {
		return modelCommandBuilder;
	}

	private void setModelCommandBuilder(EMFCommandBuilder modelCommandBuilder) {
		this.modelCommandBuilder = modelCommandBuilder;
	}

	protected GMFCommandBuilder getUiCommandBuilder() {
		return uiCommandBuilder;
	}

	private void setUiCommandBuilder(GMFCommandBuilder uiCommandBuilder) {
		this.uiCommandBuilder = uiCommandBuilder;
	}
}
