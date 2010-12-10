package org.webspeclanguage.plugin.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.webspeclanguage.plugin.editor.util.WebSpecPluginUtil;
import org.webspeclanguage.plugin.webspecmodel.Diagram;


public class ValidateAction implements IObjectActionDelegate {

	private IWorkbenchWindow window;
	
	/**
	 * Constructor for Action1.
	 */
	public ValidateAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.window = targetPart.getSite().getWorkbenchWindow();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Diagram diagram = WebSpecPluginUtil.getCurrentDiagram(this.window);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object selectedResource = structuredSelection.getFirstElement();
		}
	}
}

