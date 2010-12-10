package org.webspeclanguage.plugin.webspecmodel.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.Messages;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelDiagramEditorPlugin;


/**
 * @generated
 */
public class WebspecmodelModelingAssistantProvider extends ModelingAssistantProvider {

  /**
   * @generated
   */
  public List getTypesForPopupBar(IAdaptable host) {
    IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
    if (editPart instanceof InteractionEditPart) {
      ArrayList types = new ArrayList(1);
      types.add(WebspecmodelElementTypes.PanelContainer_3001);
      return types;
    }
    if (editPart instanceof PanelContainerPanelCompartmentEditPart) {
      ArrayList types = new ArrayList(10);
      types.add(WebspecmodelElementTypes.Button_3002);
      types.add(WebspecmodelElementTypes.CheckBox_3003);
      types.add(WebspecmodelElementTypes.ComboBox_3004);
      types.add(WebspecmodelElementTypes.Label_3005);
      types.add(WebspecmodelElementTypes.Link_3006);
      types.add(WebspecmodelElementTypes.ListBox_3007);
      types.add(WebspecmodelElementTypes.RadioButton_3008);
      types.add(WebspecmodelElementTypes.TextField_3009);
      types.add(WebspecmodelElementTypes.PanelContainer_3010);
      types.add(WebspecmodelElementTypes.ListOfContainer_3011);
      return types;
    }
    if (editPart instanceof PanelContainerPanelCompartment2EditPart) {
      ArrayList types = new ArrayList(10);
      types.add(WebspecmodelElementTypes.Button_3002);
      types.add(WebspecmodelElementTypes.CheckBox_3003);
      types.add(WebspecmodelElementTypes.ComboBox_3004);
      types.add(WebspecmodelElementTypes.Label_3005);
      types.add(WebspecmodelElementTypes.Link_3006);
      types.add(WebspecmodelElementTypes.ListBox_3007);
      types.add(WebspecmodelElementTypes.RadioButton_3008);
      types.add(WebspecmodelElementTypes.TextField_3009);
      types.add(WebspecmodelElementTypes.PanelContainer_3010);
      types.add(WebspecmodelElementTypes.ListOfContainer_3011);
      return types;
    }
    if (editPart instanceof ListOfContainerListOfCompartmentEditPart) {
      ArrayList types = new ArrayList(10);
      types.add(WebspecmodelElementTypes.Button_3002);
      types.add(WebspecmodelElementTypes.CheckBox_3003);
      types.add(WebspecmodelElementTypes.ComboBox_3004);
      types.add(WebspecmodelElementTypes.Label_3005);
      types.add(WebspecmodelElementTypes.Link_3006);
      types.add(WebspecmodelElementTypes.ListBox_3007);
      types.add(WebspecmodelElementTypes.RadioButton_3008);
      types.add(WebspecmodelElementTypes.TextField_3009);
      types.add(WebspecmodelElementTypes.PanelContainer_3010);
      types.add(WebspecmodelElementTypes.ListOfContainer_3011);
      return types;
    }
    if (editPart instanceof DiagramEditPart) {
      ArrayList types = new ArrayList(7);
      types.add(WebspecmodelElementTypes.Interaction_2001);
      types.add(WebspecmodelElementTypes.OneOfNumbers_2002);
      types.add(WebspecmodelElementTypes.OneOfStrings_2003);
      types.add(WebspecmodelElementTypes.UniformNumberDistribution_2004);
      types.add(WebspecmodelElementTypes.OneOfArray_2005);
      types.add(WebspecmodelElementTypes.StringGenerator_2006);
      types.add(WebspecmodelElementTypes.RIAFeature_2007);
      return types;
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public List getRelTypesOnSource(IAdaptable source) {
    IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
    if (sourceEditPart instanceof InteractionEditPart) {
      return ((InteractionEditPart) sourceEditPart).getMARelTypesOnSource();
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public List getRelTypesOnTarget(IAdaptable target) {
    IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
    if (targetEditPart instanceof InteractionEditPart) {
      return ((InteractionEditPart) targetEditPart).getMARelTypesOnTarget();
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
    IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
    IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
    if (sourceEditPart instanceof InteractionEditPart) {
      return ((InteractionEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
    IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
    if (targetEditPart instanceof InteractionEditPart) {
      return ((InteractionEditPart) targetEditPart).getMATypesForSource(relationshipType);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
    IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
    if (sourceEditPart instanceof InteractionEditPart) {
      return ((InteractionEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * @generated
   */
  public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
    return selectExistingElement(target, getTypesForSource(target, relationshipType));
  }

  /**
   * @generated
   */
  public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
    return selectExistingElement(source, getTypesForTarget(source, relationshipType));
  }

  /**
   * @generated
   */
  protected EObject selectExistingElement(IAdaptable host, Collection types) {
    if (types.isEmpty()) {
      return null;
    }
    IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
    if (editPart == null) {
      return null;
    }
    Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
    Collection elements = new HashSet();
    for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
      EObject element = (EObject) it.next();
      if (isApplicableElement(element, types)) {
        elements.add(element);
      }
    }
    if (elements.isEmpty()) {
      return null;
    }
    return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
  }

  /**
   * @generated
   */
  protected boolean isApplicableElement(EObject element, Collection types) {
    IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
    return types.contains(type);
  }

  /**
   * @generated
   */
  protected EObject selectElement(EObject[] elements) {
    Shell shell = Display.getCurrent().getActiveShell();
    ILabelProvider labelProvider = new AdapterFactoryLabelProvider(WebspecmodelDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
    ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
    dialog.setMessage(Messages.WebspecmodelModelingAssistantProviderMessage);
    dialog.setTitle(Messages.WebspecmodelModelingAssistantProviderTitle);
    dialog.setMultipleSelection(false);
    dialog.setElements(elements);
    EObject selected = null;
    if (dialog.open() == Window.OK) {
      selected = (EObject) dialog.getFirstResult();
    }
    return selected;
  }
}
