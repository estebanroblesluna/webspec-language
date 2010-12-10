package webspecplugin.editor.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.webspec2test.TestGenerationResult;
import org.webspeclanguage.webspec2test.WebSpec2WebTestTransformation;
import org.webspeclanguage.webtest.base.WebTestSuite;
import org.webspeclanguage.webtest.generator.selenium.java.SeleniumJavaWebTestGenerator;
import org.webspeclanguage.webtest.generator.selenium.java.SeleniumWaitGenerationPolicy;

import webspecplugin.editor.util.WebSpecPluginUtil;
import webspecplugin.emf2core.SuccessTransformation;
import webspecplugin.emf2core.TransformationErrors;
import webspecplugin.emf2core.TransformationResult;
import webspecplugin.emf2core.TransformationResultVisitor;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class GenerateTestsHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public GenerateTestsHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
	  webspecplugin.webspecmodel.Diagram diagram = WebSpecPluginUtil.getCurrentDiagram(event);
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
				generateTests(successTransformation.getWebSpecDiagram(), event);
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

	private void generateTests(Diagram webSpecDiagram, ExecutionEvent event) {
		WebSpec2WebTestTransformation transformation = new WebSpec2WebTestTransformation();
		
		try {
			TestGenerationResult result = (TestGenerationResult) transformation.transform(webSpecDiagram);
			WebTestSuite testSuite = result.getTestSuite();
			SeleniumJavaWebTestGenerator webTestGenerator = new SeleniumJavaWebTestGenerator();
			
//			webTestGenerator.setBaseClass("com.twitter.web.AbstractTwitterTestCase");
			webTestGenerator.setStopPolicy(new SeleniumWaitGenerationPolicy());
			
			IResource resource = WebSpecPluginUtil.getResource(event);
			String filename = resource.getRawLocationURI().getPath().replace(".webspec", "TestCase.java");
			IJavaElement packageResource = JavaCore.create(resource.getParent());
			if (!packageResource.getElementName().equals("")) {
				webTestGenerator.setPackageName(packageResource.getElementName());
			}
			testSuite.setName(resource.getName().replace(".webspec", ""));
			webTestGenerator.generateTestFor(testSuite, filename);
			resource.getParent().refreshLocal(IResource.DEPTH_ONE, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
