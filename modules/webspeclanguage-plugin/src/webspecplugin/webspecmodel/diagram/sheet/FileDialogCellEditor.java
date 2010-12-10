package webspecplugin.webspecmodel.diagram.sheet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.IPropertyAction;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.MultiButtonCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;

import webspecplugin.util.RelativePath;

public class FileDialogCellEditor extends MultiButtonCellEditor {

	public FileDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	public FileDialogCellEditor(Composite parent) {
		super(parent);
	}

	@Override
	protected void initButtons() {
		this.addButton("...", new IPropertyAction() {
			@Override
			public Object execute(Control owner) {
				return openDialogBox(owner);
			}
		});
		
		this.addButton("Open", new IPropertyAction() {
			@Override
			public Object execute(Control owner) {
				return openFileOnBrowser(owner);
			}
		});
	}

	protected Object openDialogBox(Control cellEditorWindow) {
		FileDialog dialog = new FileDialog(cellEditorWindow.getShell(),
				SWT.OPEN);
		String[] filterNames = new String[] { "All Files (*)" };
		String[] filterExtensions = new String[] { "*" };
		String filterPath = "/";

		dialog.setFilterNames(filterNames);
		dialog.setFilterExtensions(filterExtensions);
		dialog.setFilterPath(filterPath);

		Object value = getValue();
		if (value != null) {
			dialog.setFileName((String) value);
		}
		value = dialog.open();

		try {
			String relativePath = RelativePath.getRelativePath(
					getHome(), 
					new File((String) value));
			return relativePath;
		} catch (Exception e) {
			return value;
		}
	}

	private Object openFileOnBrowser(Control owner) {
		IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench()
				.getBrowserSupport();
		IWebBrowser browser;
		try {
			browser = browserSupport.createBrowser(
					WorkbenchBrowserSupport.AS_EDITOR
							| WorkbenchBrowserSupport.LOCATION_BAR
							| IWorkbenchBrowserSupport.NAVIGATION_BAR, null,
					null, null);
			URL findMorePluginsURL = new URL("file://" + getAbsoluteFile().getCanonicalPath());
			browser.openURL(findMorePluginsURL);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private File getAbsoluteFile() {
		return new File(getHome().getParent().toString() + "/" + getValue().toString());
	}

	private File getHome() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IEditorPart editor = window.getActivePage().getActiveEditor();
		URI uri = null;
		org.eclipse.core.internal.resources.File file = null;
		if (editor.getEditorInput().exists()
				&& editor.getEditorInput() instanceof URIEditorInput) {
			URIEditorInput editorInput = (URIEditorInput) editor
					.getEditorInput();
			uri = editorInput.getURI();
			return new File(uri.toFileString());
		} else if (editor.getEditorInput().exists()) {
			IFileEditorInput editorInput = (IFileEditorInput) editor
					.getEditorInput();
			if (editorInput.getFile() instanceof org.eclipse.core.internal.resources.File) {
				file = (org.eclipse.core.internal.resources.File) editorInput.getFile();
				return new File(file.getLocationURI().getPath());
			}
		}
		return null;
	}
}
