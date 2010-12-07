package webspecplugin.webspecmodel.diagram.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramUpdater;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelLinkDescriptor;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelNodeDescriptor;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class DiagramCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

  /**
   * @generated
   */
  Set myFeaturesToSynchronize;

  /**
   * @generated
   */
  protected List getSemanticChildrenList() {
    View viewObject = (View) getHost().getModel();
    List result = new LinkedList();
    for (Iterator it = WebspecmodelDiagramUpdater.getDiagram_1000SemanticChildren(viewObject).iterator(); it.hasNext();) {
      result.add(((WebspecmodelNodeDescriptor) it.next()).getModelElement());
    }
    return result;
  }

  /**
   * @generated
   */
  protected boolean shouldDeleteView(View view) {
    return true;
  }

  /**
   * @generated
   */
  protected boolean isOrphaned(Collection semanticChildren, final View view) {
    int visualID = WebspecmodelVisualIDRegistry.getVisualID(view);
    switch (visualID) {
    case InteractionEditPart.VISUAL_ID:
    case OneOfNumbersEditPart.VISUAL_ID:
    case OneOfStringsEditPart.VISUAL_ID:
    case UniformNumberDistributionEditPart.VISUAL_ID:
    case OneOfArrayEditPart.VISUAL_ID:
    case StringGeneratorEditPart.VISUAL_ID:
    case RIAFeatureEditPart.VISUAL_ID:
      if (!semanticChildren.contains(view.getElement())) {
        return true;
      }
    }
    return false;
  }

  /**
   * @generated
   */
  protected String getDefaultFactoryHint() {
    return null;
  }

  /**
   * @generated
   */
  protected Set getFeaturesToSynchronize() {
    if (myFeaturesToSynchronize == null) {
      myFeaturesToSynchronize = new HashSet();
      myFeaturesToSynchronize.add(WebspecmodelPackage.eINSTANCE.getDiagram_Interactions());
      myFeaturesToSynchronize.add(WebspecmodelPackage.eINSTANCE.getDiagram_Generators());
      myFeaturesToSynchronize.add(WebspecmodelPackage.eINSTANCE.getDiagram_RiaFeatures());
    }
    return myFeaturesToSynchronize;
  }

  /**
   * @generated
   */
  protected List getSemanticConnectionsList() {
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  protected EObject getSourceElement(EObject relationship) {
    return null;
  }

  /**
   * @generated
   */
  protected EObject getTargetElement(EObject relationship) {
    return null;
  }

  /**
   * @generated
   */
  protected boolean shouldIncludeConnection(Edge connector, Collection children) {
    return false;
  }

  /**
   * @generated
   */
  protected void refreshSemantic() {
    List createdViews = new LinkedList();
    createdViews.addAll(refreshSemanticChildren());
    List createdConnectionViews = new LinkedList();
    createdConnectionViews.addAll(refreshSemanticConnections());
    createdConnectionViews.addAll(refreshConnections());

    if (createdViews.size() > 1) {
      // perform a layout of the container
      DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host().getEditingDomain(), createdViews, host());
      executeCommand(new ICommandProxy(layoutCmd));
    }

    createdViews.addAll(createdConnectionViews);
    makeViewsImmutable(createdViews);
  }

  /**
   * @generated
   */
  private Diagram getDiagram() {
    return ((View) getHost().getModel()).getDiagram();
  }

  /**
   * @generated
   */
  private Collection refreshConnections() {
    Map domain2NotationMap = new HashMap();
    Collection linkDescriptors = collectAllLinks(getDiagram(), domain2NotationMap);
    Collection existingLinks = new LinkedList(getDiagram().getEdges());
    for (Iterator linksIterator = existingLinks.iterator(); linksIterator.hasNext();) {
      Edge nextDiagramLink = (Edge) linksIterator.next();
      int diagramLinkVisualID = WebspecmodelVisualIDRegistry.getVisualID(nextDiagramLink);
      if (diagramLinkVisualID == -1) {
        if (nextDiagramLink.getSource() != null && nextDiagramLink.getTarget() != null) {
          linksIterator.remove();
        }
        continue;
      }
      EObject diagramLinkObject = nextDiagramLink.getElement();
      EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
      EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
      for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator.hasNext();) {
        WebspecmodelLinkDescriptor nextLinkDescriptor = (WebspecmodelLinkDescriptor) linkDescriptorsIterator.next();
        if (diagramLinkObject == nextLinkDescriptor.getModelElement() && diagramLinkSrc == nextLinkDescriptor.getSource()
                && diagramLinkDst == nextLinkDescriptor.getDestination() && diagramLinkVisualID == nextLinkDescriptor.getVisualID()) {
          linksIterator.remove();
          linkDescriptorsIterator.remove();
          break;
        }
      }
    }
    deleteViews(existingLinks.iterator());
    return createConnections(linkDescriptors, domain2NotationMap);
  }

  /**
   * @generated
   */
  private Collection collectAllLinks(View view, Map domain2NotationMap) {
    if (!DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry.getModelID(view))) {
      return Collections.EMPTY_LIST;
    }
    Collection result = new LinkedList();
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case DiagramEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getDiagram_1000ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case InteractionEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getInteraction_2001ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case OneOfNumbersEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getOneOfNumbers_2002ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case OneOfStringsEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getOneOfStrings_2003ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case UniformNumberDistributionEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getUniformNumberDistribution_2004ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case OneOfArrayEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getOneOfArray_2005ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case StringGeneratorEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getStringGenerator_2006ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case RIAFeatureEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getRIAFeature_2007ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case PanelContainerEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getPanelContainer_3001ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case ButtonEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getButton_3002ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case CheckBoxEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getCheckBox_3003ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case ComboBoxEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getComboBox_3004ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case LabelEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getLabel_3005ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case LinkEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getLink_3006ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case ListBoxEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getListBox_3007ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case RadioButtonEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getRadioButton_3008ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case TextFieldEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getTextField_3009ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case PanelContainer2EditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getPanelContainer_3010ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case ListOfContainerEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getListOfContainer_3011ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case NavigationEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getNavigation_4001ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    case RichBehaviorEditPart.VISUAL_ID: {
      if (!domain2NotationMap.containsKey(view.getElement())) {
        result.addAll(WebspecmodelDiagramUpdater.getRichBehavior_4002ContainedLinks(view));
      }
      if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
        domain2NotationMap.put(view.getElement(), view);
      }
      break;
    }
    }
    for (Iterator children = view.getChildren().iterator(); children.hasNext();) {
      result.addAll(collectAllLinks((View) children.next(), domain2NotationMap));
    }
    for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
      result.addAll(collectAllLinks((View) edges.next(), domain2NotationMap));
    }
    return result;
  }

  /**
   * @generated
   */
  private Collection createConnections(Collection linkDescriptors, Map domain2NotationMap) {
    List adapters = new LinkedList();
    for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator.hasNext();) {
      final WebspecmodelLinkDescriptor nextLinkDescriptor = (WebspecmodelLinkDescriptor) linkDescriptorsIterator.next();
      EditPart sourceEditPart = getEditPart(nextLinkDescriptor.getSource(), domain2NotationMap);
      EditPart targetEditPart = getEditPart(nextLinkDescriptor.getDestination(), domain2NotationMap);
      if (sourceEditPart == null || targetEditPart == null) {
        continue;
      }
      CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(nextLinkDescriptor
              .getSemanticAdapter(), String.valueOf(nextLinkDescriptor.getVisualID()), ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost())
              .getDiagramPreferencesHint());
      CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(descriptor);
      ccr.setType(RequestConstants.REQ_CONNECTION_START);
      ccr.setSourceEditPart(sourceEditPart);
      sourceEditPart.getCommand(ccr);
      ccr.setTargetEditPart(targetEditPart);
      ccr.setType(RequestConstants.REQ_CONNECTION_END);
      Command cmd = targetEditPart.getCommand(ccr);
      if (cmd != null && cmd.canExecute()) {
        executeCommand(cmd);
        IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
        if (viewAdapter != null) {
          adapters.add(viewAdapter);
        }
      }
    }
    return adapters;
  }

  /**
   * @generated
   */
  private EditPart getEditPart(EObject domainModelElement, Map domain2NotationMap) {
    View view = (View) domain2NotationMap.get(domainModelElement);
    if (view != null) {
      return (EditPart) getHost().getViewer().getEditPartRegistry().get(view);
    }
    return null;
  }
}
