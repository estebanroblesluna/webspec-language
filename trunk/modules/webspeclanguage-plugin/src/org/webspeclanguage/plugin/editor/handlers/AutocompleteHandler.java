package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.editor.ria.Autocomplete;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;


public class AutocompleteHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		TextFieldEditPart editPart = this.getSelectedTextField(event);
		
		if (editPart != null) {
			try {
				Autocomplete ref = new Autocomplete(editPart, 3, "autocompleteItem", "selected");
				ref.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private TextFieldEditPart getSelectedTextField(ExecutionEvent event) {
		try {
			return (TextFieldEditPart) ((StructuredSelection) HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection()).getFirstElement();
		} catch (ClassCastException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
