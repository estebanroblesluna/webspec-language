package webspecplugin.editor.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import webspecplugin.editor.refactoring.SplitInteraction;
import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.Widget;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;

public class SplitInteractionHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		InteractionEditPart editPart = this.getSelectedInteraction(event);
		Interaction interaction = (Interaction) ((NodeImpl)editPart.getModel()).getElement();
		
		List<List<Widget>> widgetsToSteps = new ArrayList<List<Widget>>();
		for (Object o : interaction.getRoot().getWidgets()) {
			List<Widget> widgets = new ArrayList<Widget>();
			widgets.add((Widget) o);
			widgetsToSteps.add(widgets);
		}
		
		if (editPart != null) {
			try {
				SplitInteraction ref = new SplitInteraction(editPart, interaction.getRoot().getWidgets().size(), widgetsToSteps);
				ref.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private InteractionEditPart getSelectedInteraction(ExecutionEvent event) {
		try {
			return (InteractionEditPart) ((StructuredSelection) HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection()).getFirstElement();
		} catch (ClassCastException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
