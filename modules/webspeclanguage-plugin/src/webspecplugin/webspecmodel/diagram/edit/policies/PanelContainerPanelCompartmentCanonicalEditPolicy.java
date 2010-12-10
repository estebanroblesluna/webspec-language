package webspecplugin.webspecmodel.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramUpdater;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelNodeDescriptor;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class PanelContainerPanelCompartmentCanonicalEditPolicy extends CanonicalEditPolicy {

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
    for (Iterator it = WebspecmodelDiagramUpdater.getPanelContainerPanelCompartment_7001SemanticChildren(viewObject).iterator(); it.hasNext();) {
      result.add(((WebspecmodelNodeDescriptor) it.next()).getModelElement());
    }
    return result;
  }

  /**
   * @generated
   */
  protected boolean isOrphaned(Collection semanticChildren, final View view) {
    int visualID = WebspecmodelVisualIDRegistry.getVisualID(view);
    switch (visualID) {
    case ButtonEditPart.VISUAL_ID:
    case CheckBoxEditPart.VISUAL_ID:
    case ComboBoxEditPart.VISUAL_ID:
    case LabelEditPart.VISUAL_ID:
    case LinkEditPart.VISUAL_ID:
    case ListBoxEditPart.VISUAL_ID:
    case RadioButtonEditPart.VISUAL_ID:
    case TextFieldEditPart.VISUAL_ID:
    case PanelContainer2EditPart.VISUAL_ID:
    case ListOfContainerEditPart.VISUAL_ID:
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
      myFeaturesToSynchronize.add(WebspecmodelPackage.eINSTANCE.getContainer_Widgets());
    }
    return myFeaturesToSynchronize;
  }

}
