package webspecplugin.editor.handlers;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolItem;

import webspecplugin.editor.change.ChangeObject;
import webspecplugin.editor.change.ChangeObjectMarshaller;
import webspecplugin.editor.change.ChangeRecorder;
import webspecplugin.editor.change.NotRecordingException;
import webspecplugin.editor.util.WebSpecPluginUtil;
import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.presentation.WebspecmodelEditorPlugin;

public class StartStopRecordingHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Event plainEvent = (Event) event.getTrigger();
    ToolItem item = (ToolItem) plainEvent.widget;
    
    ChangeRecorder changeRecorder = ChangeRecorderHolder.getInstance();
    try {
      Diagram diagram = WebSpecPluginUtil.getCurrentDiagram(event);
      if (changeRecorder.isRecording(diagram)) {
        ChangeObject changeObject = changeRecorder.stopRecording(diagram);
        saveChangeObject(changeObject, diagram, event);
        Image image = ImageDescriptor.createFromURL(new URL(WebspecmodelEditorPlugin.INSTANCE.getImage("full/obj16/startRecording.gif").toString())).createImage();
        item.setImage(image);
      } else {
        changeRecorder.startRecording(diagram);
        Image image = ImageDescriptor.createFromURL(new URL(WebspecmodelEditorPlugin.INSTANCE.getImage("full/obj16/stopRecording.gif").toString())).createImage();
        item.setImage(image);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  private void saveChangeObject(ChangeObject changeObject, Diagram diagram, ExecutionEvent event) throws ExecutionException {
    try {
      ChangeObjectMarshaller marshaller = new ChangeObjectMarshaller();
      IResource resource = WebSpecPluginUtil.getResource(event);
      String filename = resource.getRawLocationURI().getPath().replace(".webspec", ".webspecChange");
      marshaller.save(filename, changeObject);
      resource.getParent().refreshLocal(IResource.DEPTH_ONE, null);
    } catch (NotRecordingException e) {
      e.printStackTrace();
    } catch (CoreException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
