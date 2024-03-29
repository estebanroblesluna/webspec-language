package org.webspeclanguage.plugin.webspecmodel.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 */
public class WebspecmodelCreationWizard extends Wizard implements INewWizard {

  /**
   * @generated
   */
  private IWorkbench workbench;

  /**
   * @generated
   */
  protected IStructuredSelection selection;

  /**
   * @generated
   */
  protected WebspecmodelCreationWizardPage diagramModelFilePage;

  /**
   * @generated
   */
  protected Resource diagram;

  /**
   * @generated
   */
  private boolean openNewlyCreatedDiagramEditor = true;

  /**
   * @generated
   */
  public IWorkbench getWorkbench() {
    return workbench;
  }

  /**
   * @generated
   */
  public IStructuredSelection getSelection() {
    return selection;
  }

  /**
   * @generated
   */
  public final Resource getDiagram() {
    return diagram;
  }

  /**
   * @generated
   */
  public final boolean isOpenNewlyCreatedDiagramEditor() {
    return openNewlyCreatedDiagramEditor;
  }

  /**
   * @generated
   */
  public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
    this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
  }

  /**
   * @generated
   */
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    this.workbench = workbench;
    this.selection = selection;
    setWindowTitle(Messages.WebspecmodelCreationWizardTitle);
    setDefaultPageImageDescriptor(WebspecmodelDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewWebspecmodelWizard.gif")); //$NON-NLS-1$
    setNeedsProgressMonitor(true);
  }

  /**
   * @generated
   */
  public void addPages() {
    diagramModelFilePage = new WebspecmodelCreationWizardPage("DiagramModelFile", getSelection(), "webspec"); //$NON-NLS-1$ //$NON-NLS-2$
    diagramModelFilePage.setTitle(Messages.WebspecmodelCreationWizard_DiagramModelFilePageTitle);
    diagramModelFilePage.setDescription(Messages.WebspecmodelCreationWizard_DiagramModelFilePageDescription);
    addPage(diagramModelFilePage);
  }

  /**
   * @generated
   */
  public boolean performFinish() {
    IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

      protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
        diagram = WebspecmodelDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(), monitor);
        if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
          try {
            WebspecmodelDiagramEditorUtil.openDiagram(diagram);
          } catch (PartInitException e) {
            ErrorDialog.openError(getContainer().getShell(), Messages.WebspecmodelCreationWizardOpenEditorError, null, e.getStatus());
          }
        }
      }
    };
    try {
      getContainer().run(false, true, op);
    } catch (InterruptedException e) {
      return false;
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof CoreException) {
        ErrorDialog.openError(getContainer().getShell(), Messages.WebspecmodelCreationWizardCreationError, null, ((CoreException) e.getTargetException())
                .getStatus());
      } else {
        WebspecmodelDiagramEditorPlugin.getInstance().logError("Error creating diagram", e.getTargetException()); //$NON-NLS-1$
      }
      return false;
    }
    return diagram != null;
  }
}
