package webspecplugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import webspecplugin.editor.util.WebSpecPluginUtil;
import webspecplugin.emf2core.SuccessTransformation;
import webspecplugin.emf2core.TransformationErrors;
import webspecplugin.emf2core.TransformationResult;
import webspecplugin.emf2core.TransformationResultVisitor;
import webspecplugin.webspecmodel.Diagram;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ValidateHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ValidateHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		Diagram diagram = WebSpecPluginUtil.getCurrentDiagram(event);
		TransformationResult result = WebSpecPluginUtil.toWebSpecDiagram(diagram);
		result.accept(new TransformationResultVisitor(){
			public void visitSuccessTransformation(SuccessTransformation successTransformation) {
				WebSpecPluginUtil.clearErrors(event);
				WebSpecPluginUtil.showWarnings(successTransformation.getWarnings(), event);
			}

			public void visitTransformationErrors(TransformationErrors transformationErrors) {
				WebSpecPluginUtil.clearErrors(event);
				WebSpecPluginUtil.showErrors(transformationErrors.getErrors(), event);
				WebSpecPluginUtil.showWarnings(transformationErrors.getWarnings(), event);
			}
		});
		return null;
	}
}
