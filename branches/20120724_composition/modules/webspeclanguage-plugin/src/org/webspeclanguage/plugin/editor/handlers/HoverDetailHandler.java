package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.editor.ria.HoverDetail;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelEditPart;


public class HoverDetailHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		LabelEditPart editPart = this.getSelectedLabel(event);
		
		if (editPart != null) {
			try {
				HoverDetail ref = new HoverDetail(editPart);
				ref.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private LabelEditPart getSelectedLabel(ExecutionEvent event) {
		try {
			return (LabelEditPart) ((StructuredSelection) HandlerUtil.getActiveWorkbenchWindowChecked(event).getSelectionService().getSelection()).getFirstElement();
		} catch (ClassCastException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
