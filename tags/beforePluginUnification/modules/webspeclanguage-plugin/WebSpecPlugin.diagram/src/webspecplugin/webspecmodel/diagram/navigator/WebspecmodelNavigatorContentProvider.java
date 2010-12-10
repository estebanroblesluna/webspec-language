package webspecplugin.webspecmodel.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.part.Messages;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class WebspecmodelNavigatorContentProvider implements ICommonContentProvider {

  /**
   * @generated
   */
  private static final Object[] EMPTY_ARRAY = new Object[0];

  /**
   * @generated
   */
  private Viewer myViewer;

  /**
   * @generated
   */
  private AdapterFactoryEditingDomain myEditingDomain;

  /**
   * @generated
   */
  private WorkspaceSynchronizer myWorkspaceSynchronizer;

  /**
   * @generated
   */
  private Runnable myViewerRefreshRunnable;

  /**
   * @generated
   */
  public WebspecmodelNavigatorContentProvider() {
    TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
    myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
    myEditingDomain.setResourceToReadOnlyMap(new HashMap() {

      public Object get(Object key) {
        if (!containsKey(key)) {
          put(key, Boolean.TRUE);
        }
        return super.get(key);
      }
    });
    myViewerRefreshRunnable = new Runnable() {

      public void run() {
        if (myViewer != null) {
          myViewer.refresh();
        }
      }
    };
    myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {

      public void dispose() {
      }

      public boolean handleResourceChanged(final Resource resource) {
        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it.hasNext();) {
          Resource nextResource = (Resource) it.next();
          nextResource.unload();
        }
        if (myViewer != null) {
          myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
        }
        return true;
      }

      public boolean handleResourceDeleted(Resource resource) {
        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it.hasNext();) {
          Resource nextResource = (Resource) it.next();
          nextResource.unload();
        }
        if (myViewer != null) {
          myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
        }
        return true;
      }

      public boolean handleResourceMoved(Resource resource, final URI newURI) {
        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it.hasNext();) {
          Resource nextResource = (Resource) it.next();
          nextResource.unload();
        }
        if (myViewer != null) {
          myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
        }
        return true;
      }
    });
  }

  /**
   * @generated
   */
  public void dispose() {
    myWorkspaceSynchronizer.dispose();
    myWorkspaceSynchronizer = null;
    myViewerRefreshRunnable = null;
    for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it.hasNext();) {
      Resource resource = (Resource) it.next();
      resource.unload();
    }
    ((TransactionalEditingDomain) myEditingDomain).dispose();
    myEditingDomain = null;
  }

  /**
   * @generated
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    myViewer = viewer;
  }

  /**
   * @generated
   */
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  /**
   * @generated
   */
  public void restoreState(IMemento aMemento) {
  }

  /**
   * @generated
   */
  public void saveState(IMemento aMemento) {
  }

  /**
   * @generated
   */
  public void init(ICommonContentExtensionSite aConfig) {
  }

  /**
   * @generated
   */
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IFile) {
      IFile file = (IFile) parentElement;
      URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
      Resource resource = myEditingDomain.getResourceSet().getResource(fileURI, true);
      Collection result = new ArrayList();
      result.addAll(createNavigatorItems(selectViewsByType(resource.getContents(), DiagramEditPart.MODEL_ID), file, false));
      return result.toArray();
    }

    if (parentElement instanceof WebspecmodelNavigatorGroup) {
      WebspecmodelNavigatorGroup group = (WebspecmodelNavigatorGroup) parentElement;
      return group.getChildren();
    }

    if (parentElement instanceof WebspecmodelNavigatorItem) {
      WebspecmodelNavigatorItem navigatorItem = (WebspecmodelNavigatorItem) parentElement;
      if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
        return EMPTY_ARRAY;
      }
      return getViewChildren(navigatorItem.getView(), parentElement);
    }

    return EMPTY_ARRAY;
  }

  /**
   * @generated
   */
  private Object[] getViewChildren(View view, Object parentElement) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {

    case DiagramEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      WebspecmodelNavigatorGroup links = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_Diagram_1000_links,
              "icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      Collection connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(OneOfNumbersEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(OneOfStringsEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(UniformNumberDistributionEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(OneOfArrayEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(StringGeneratorEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(RIAFeatureEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getDiagramLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(NavigationEditPart.VISUAL_ID));
      links.addChildren(createNavigatorItems(connectedViews, links, false));
      connectedViews = getDiagramLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(RichBehaviorEditPart.VISUAL_ID));
      links.addChildren(createNavigatorItems(connectedViews, links, false));
      if (!links.isEmpty()) {
        result.add(links);
      }
      return result.toArray();
    }

    case InteractionEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      WebspecmodelNavigatorGroup incominglinks = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_Interaction_2001_incominglinks,
              "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      WebspecmodelNavigatorGroup outgoinglinks = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_Interaction_2001_outgoinglinks,
              "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      Collection connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getIncomingLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(NavigationEditPart.VISUAL_ID));
      incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
      connectedViews = getOutgoingLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(NavigationEditPart.VISUAL_ID));
      outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
      connectedViews = getIncomingLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(RichBehaviorEditPart.VISUAL_ID));
      incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
      connectedViews = getOutgoingLinksByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(RichBehaviorEditPart.VISUAL_ID));
      outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
      if (!incominglinks.isEmpty()) {
        result.add(incominglinks);
      }
      if (!outgoinglinks.isEmpty()) {
        result.add(outgoinglinks);
      }
      return result.toArray();
    }

    case PanelContainerEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      Collection connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry
              .getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(CheckBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ComboBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LabelEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(RadioButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(TextFieldEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(PanelContainer2EditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListOfContainerEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      return result.toArray();
    }

    case PanelContainer2EditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      Collection connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry
              .getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(CheckBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ComboBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LabelEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(RadioButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(TextFieldEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(PanelContainer2EditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListOfContainerEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      return result.toArray();
    }

    case ListOfContainerEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      Collection connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry
              .getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(CheckBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ComboBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LabelEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListBoxEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(RadioButtonEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(TextFieldEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(PanelContainer2EditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      connectedViews = getChildrenByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID));
      connectedViews = getChildrenByType(connectedViews, WebspecmodelVisualIDRegistry.getType(ListOfContainerEditPart.VISUAL_ID));
      result.addAll(createNavigatorItems(connectedViews, parentElement, false));
      return result.toArray();
    }

    case NavigationEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      WebspecmodelNavigatorGroup target = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_Navigation_4001_target,
              "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      WebspecmodelNavigatorGroup source = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_Navigation_4001_source,
              "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      Collection connectedViews = getLinksTargetByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
      target.addChildren(createNavigatorItems(connectedViews, target, true));
      connectedViews = getLinksSourceByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
      source.addChildren(createNavigatorItems(connectedViews, source, true));
      if (!target.isEmpty()) {
        result.add(target);
      }
      if (!source.isEmpty()) {
        result.add(source);
      }
      return result.toArray();
    }

    case RichBehaviorEditPart.VISUAL_ID: {
      Collection result = new ArrayList();
      WebspecmodelNavigatorGroup target = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_RichBehavior_4002_target,
              "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      WebspecmodelNavigatorGroup source = new WebspecmodelNavigatorGroup(Messages.NavigatorGroupName_RichBehavior_4002_source,
              "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
      Collection connectedViews = getLinksTargetByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
      target.addChildren(createNavigatorItems(connectedViews, target, true));
      connectedViews = getLinksSourceByType(Collections.singleton(view), WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
      source.addChildren(createNavigatorItems(connectedViews, source, true));
      if (!target.isEmpty()) {
        result.add(target);
      }
      if (!source.isEmpty()) {
        result.add(source);
      }
      return result.toArray();
    }
    }
    return EMPTY_ARRAY;
  }

  /**
   * @generated
   */
  private Collection getLinksSourceByType(Collection edges, String type) {
    Collection result = new ArrayList();
    for (Iterator it = edges.iterator(); it.hasNext();) {
      Edge nextEdge = (Edge) it.next();
      View nextEdgeSource = nextEdge.getSource();
      if (type.equals(nextEdgeSource.getType()) && isOwnView(nextEdgeSource)) {
        result.add(nextEdgeSource);
      }
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection getLinksTargetByType(Collection edges, String type) {
    Collection result = new ArrayList();
    for (Iterator it = edges.iterator(); it.hasNext();) {
      Edge nextEdge = (Edge) it.next();
      View nextEdgeTarget = nextEdge.getTarget();
      if (type.equals(nextEdgeTarget.getType()) && isOwnView(nextEdgeTarget)) {
        result.add(nextEdgeTarget);
      }
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection getOutgoingLinksByType(Collection nodes, String type) {
    Collection result = new ArrayList();
    for (Iterator it = nodes.iterator(); it.hasNext();) {
      View nextNode = (View) it.next();
      result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection getIncomingLinksByType(Collection nodes, String type) {
    Collection result = new ArrayList();
    for (Iterator it = nodes.iterator(); it.hasNext();) {
      View nextNode = (View) it.next();
      result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection getChildrenByType(Collection nodes, String type) {
    Collection result = new ArrayList();
    for (Iterator it = nodes.iterator(); it.hasNext();) {
      View nextNode = (View) it.next();
      result.addAll(selectViewsByType(nextNode.getChildren(), type));
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection getDiagramLinksByType(Collection diagrams, String type) {
    Collection result = new ArrayList();
    for (Iterator it = diagrams.iterator(); it.hasNext();) {
      Diagram nextDiagram = (Diagram) it.next();
      result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection selectViewsByType(Collection views, String type) {
    Collection result = new ArrayList();
    for (Iterator it = views.iterator(); it.hasNext();) {
      View nextView = (View) it.next();
      if (type.equals(nextView.getType()) && isOwnView(nextView)) {
        result.add(nextView);
      }
    }
    return result;
  }

  /**
   * @generated
   */
  private boolean isOwnView(View view) {
    return DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry.getModelID(view));
  }

  /**
   * @generated
   */
  private Collection createNavigatorItems(Collection views, Object parent, boolean isLeafs) {
    Collection result = new ArrayList();
    for (Iterator it = views.iterator(); it.hasNext();) {
      result.add(new WebspecmodelNavigatorItem((View) it.next(), parent, isLeafs));
    }
    return result;
  }

  /**
   * @generated
   */
  public Object getParent(Object element) {
    if (element instanceof WebspecmodelAbstractNavigatorItem) {
      WebspecmodelAbstractNavigatorItem abstractNavigatorItem = (WebspecmodelAbstractNavigatorItem) element;
      return abstractNavigatorItem.getParent();
    }
    return null;
  }

  /**
   * @generated
   */
  public boolean hasChildren(Object element) {
    return element instanceof IFile || getChildren(element).length > 0;
  }

}
