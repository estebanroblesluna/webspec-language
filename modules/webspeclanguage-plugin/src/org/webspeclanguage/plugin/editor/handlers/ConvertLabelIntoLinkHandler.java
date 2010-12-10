package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.editor.refactoring.ConvertLabelIntoLink;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelEditPart;


public class ConvertLabelIntoLinkHandler extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		LabelEditPart labelPart = this.getSelectedLabel(event);
		
		if (labelPart != null) {
			try {
				ConvertLabelIntoLink ref = new ConvertLabelIntoLink(labelPart);
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
