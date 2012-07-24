package org.webspeclanguage.plugin.editor.util;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.emf2core.Emf2CoreTransformation;
import org.webspeclanguage.plugin.emf2core.TransformationResult;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.DiagramEditPart;


public class WebSpecPluginUtil {

	public static Diagram getCurrentDiagram(ExecutionEvent event)
			throws ExecutionException {
		return getCurrentDiagram(HandlerUtil
				.getActiveWorkbenchWindowChecked(event));
	}

  public static Diagram getCurrentDiagram(IWorkbenchWindow window) {
    try {
      return simplifiedGetCurrentDiagram(window);
    } catch (Exception e) {
      return complexGetCurrentDiagram(window);
    }
  }
  
  public static Diagram complexGetCurrentDiagram(IWorkbenchWindow window) {
    Diagram diagram = null;

    IEditorPart editor = window.getActivePage().getActiveEditor();
    URI uri = null;
    if (editor.getEditorInput().exists()
        && editor.getEditorInput() instanceof URIEditorInput) {
      URIEditorInput editorInput = (URIEditorInput) editor
          .getEditorInput();
      uri = editorInput.getURI();
    } else if (editor.getEditorInput().exists()) {
      IFileEditorInput editorInput = (IFileEditorInput) editor
          .getEditorInput();
      if (editorInput.getFile() instanceof File) {
        File file = (File) editorInput.getFile();
        uri = URI.createFileURI(file.getLocation().toPortableString());
      }
    }

    TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE
        .createEditingDomain();
    Resource resource = domain.getResourceSet().getResource(uri, true);

    EList<EObject> objects = resource.getContents();
    for (EObject object : objects) {
      if (object instanceof Diagram) {
        diagram = (Diagram) object;
        break;
      }
    }

    return diagram;
  }

	public static Diagram simplifiedGetCurrentDiagram(IWorkbenchWindow window) {
		StructuredSelection structSelection = (StructuredSelection) window.getActivePage().getSelection(); 
    EditPart editPart = (EditPart) structSelection.getFirstElement();
    while (!(editPart instanceof DiagramEditPart)) {
      editPart = editPart.getParent();
    }
		DiagramEditPart diagramEditPart = (DiagramEditPart) editPart;
		Diagram diagram = (Diagram) ((DiagramImpl) diagramEditPart.getModel()).getElement();
		return diagram;
	}

	public static TransformationResult toWebSpecDiagram(Diagram diagram) {
		Emf2CoreTransformation transformation = new Emf2CoreTransformation();
		return transformation.transform(diagram);
	}

	@SuppressWarnings("restriction")
	public static IResource getResource(ExecutionEvent event)
			throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		IEditorPart editor = window.getActivePage().getActiveEditor();

		if (editor.getEditorInput().exists()
				&& editor.getEditorInput() instanceof URIEditorInput) {
			URIEditorInput editorInput = (URIEditorInput) editor
					.getEditorInput();
			Path path = new Path(editorInput.getURI().toPlatformString(true));
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		} else if (editor.getEditorInput().exists()
				&& editor.getEditorInput() instanceof IFileEditorInput) {
			IFileEditorInput editorInput = (IFileEditorInput) editor
					.getEditorInput();
			if (editorInput.getFile() instanceof File) {
				return (File) editorInput.getFile();
			}
		}
		return null;
	}

	public static void showErrors(List<String> errors, ExecutionEvent event) {
		try {
			IResource resource = getResource(event);
			for (String error : errors) {
				IMarker marker = resource.createMarker(IMarker.PROBLEM);
				marker.setAttribute(IMarker.MESSAGE, error);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void showWarnings(List<String> warnings, ExecutionEvent event) {
		try {
			IResource resource = getResource(event);
			for (String warning : warnings) {
				IMarker marker = resource.createMarker(IMarker.PROBLEM);
				marker.setAttribute(IMarker.MESSAGE, warning);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void clearErrors(ExecutionEvent event) {
		try {
			IResource resource = getResource(event);
			resource.deleteMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
