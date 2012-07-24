package org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelDiagramUpdater;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelNodeDescriptor;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class InteractionCanonicalEditPolicy extends CanonicalEditPolicy {

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
    for (Iterator it = WebspecmodelDiagramUpdater.getInteraction_2001SemanticChildren(viewObject).iterator(); it.hasNext();) {
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
    case PanelContainerEditPart.VISUAL_ID:
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
      myFeaturesToSynchronize.add(WebspecmodelPackage.eINSTANCE.getInteraction_Root());
    }
    return myFeaturesToSynchronize;
  }

}
