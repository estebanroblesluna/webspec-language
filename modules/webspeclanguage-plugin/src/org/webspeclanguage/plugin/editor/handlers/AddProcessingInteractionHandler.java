package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.editor.refactoring.AddProcessingInteraction;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationEditPart;


public class AddProcessingInteractionHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		NavigationEditPart editPart = this.getSelectedNavigation(event);
		
		if (editPart != null) {
			try {
				AddProcessingInteraction ref = new AddProcessingInteraction(editPart);
				ref.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private NavigationEditPart getSelectedNavigation(ExecutionEvent event) {
		try {
			return (NavigationEditPart) ((StructuredSelection) HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection()).getFirstElement();
		} catch (ClassCastException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
