package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.webspeclanguage.plugin.editor.change.ChangeObject;
import org.webspeclanguage.plugin.editor.change.ChangeObjectMarshaller;
import org.webspeclanguage.plugin.editor.change.GWTEffect;


public class GWTEffectHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof ITreeSelection) {
      ITreeSelection treeSelection = (ITreeSelection) selection;
      IResource resource = (IResource) treeSelection.getFirstElement();
      ChangeObject changeObject = new ChangeObjectMarshaller().read(resource.getLocation().toOSString());
      try {
        IJavaProject project = JavaModelManager.getJavaModelManager().getJavaModel().getJavaProject(resource);
        IPackageFragment packageFragment = project.findPackageFragment(Path.fromOSString("/" + project.getElementName() + "/").append(resource.getProjectRelativePath().removeLastSegments(1)));
        GWTEffect effect = new GWTEffect(packageFragment);
        effect.handle(changeObject);
      } catch (JavaModelException e) {
        e.printStackTrace();
      }
    }

    return null;
  }
}
