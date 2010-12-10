package org.webspeclanguage.plugin.editor.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.selenium.ExtendedSelenium;
import org.selenium.SeleniumEvaluator;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.plugin.editor.util.WebSpecPluginUtil;
import org.webspeclanguage.plugin.emf2core.SuccessTransformation;
import org.webspeclanguage.plugin.emf2core.TransformationErrors;
import org.webspeclanguage.plugin.emf2core.TransformationResult;
import org.webspeclanguage.plugin.emf2core.TransformationResultVisitor;
import org.webspeclanguage.simulation.Simulator;
import org.webspeclanguage.webspec2simulation.SeleniumSimulatorFactory;


/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SimulateHandler extends AbstractHandler {
	
	/**
	 * The constructor.
	 */
	public SimulateHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
	  org.webspeclanguage.plugin.webspecmodel.Diagram diagram = WebSpecPluginUtil.getCurrentDiagram(event);
		TransformationResult result = null;
		try {
			result = WebSpecPluginUtil.toWebSpecDiagram(diagram);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.accept(new TransformationResultVisitor(){
			public void visitSuccessTransformation(SuccessTransformation successTransformation) {
				WebSpecPluginUtil.clearErrors(event);
				WebSpecPluginUtil.showWarnings(successTransformation.getWarnings(), event);
				generateSimulations(successTransformation.getWebSpecDiagram(), event);
			}

			public void visitTransformationErrors(TransformationErrors transformationErrors) {
				WebSpecPluginUtil.clearErrors(event);
				WebSpecPluginUtil.showErrors(transformationErrors.getErrors(), event);
				WebSpecPluginUtil.showWarnings(transformationErrors.getWarnings(), event);
				IWorkbenchWindow window;
				try {
					window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
					MessageDialog.openInformation(
							window.getShell(),
							"WebSpec Editor",
							"Errors found!");
				} catch (ExecutionException e) {
				}
			}
		});
		
		return null;
	}
	
	private void generateSimulations(Diagram webSpecDiagram, ExecutionEvent event) {
		try {
			ExtendedSelenium extendedSelenium = new ExtendedSelenium(
					"localhost", 
					4444,
					"*chrome", 
					"http://www.google.com");
			
			extendedSelenium.safeStart();
			
			IResource resource = WebSpecPluginUtil.getResource(event);
			
			SeleniumEvaluator evaluator = new SeleniumEvaluator(extendedSelenium);
			SeleniumSimulatorFactory factory = new SeleniumSimulatorFactory(evaluator);
			List<Simulator> simulators = factory.getSimulators(webSpecDiagram, resource.getParent().getLocation().toOSString() + "/");
			simulators.get(0).simulate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
