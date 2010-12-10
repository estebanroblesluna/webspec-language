package webspecplugin.editor.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import webspecplugin.editor.refactoring.MergeInteractions;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;

public class MergeInteractionsHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		List<InteractionEditPart> editParts = this.getSelectedInteractions(event);
		
		if (editParts != null && editParts.size() >= 2) {
			try {
				MergeInteractions ref = new MergeInteractions(editParts);
				ref.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<InteractionEditPart> getSelectedInteractions(ExecutionEvent event) {
		try {
			List<InteractionEditPart> interactionEditParts = new ArrayList<InteractionEditPart>();
			StructuredSelection selections = ((StructuredSelection) HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection());
			for (Iterator iterator = selections.iterator(); iterator.hasNext();) {
				Object o = iterator.next();
				InteractionEditPart interactionEditPart = (InteractionEditPart) o; 
				interactionEditParts.add(interactionEditPart);
			}
			return interactionEditParts;
		} catch (ClassCastException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
