package org.webspeclanguage.plugin.webspecmodel.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class PanelContainerViewFactory extends AbstractShapeViewFactory {

  /**
   * @generated
   */
  protected List createStyles(View view) {
    List styles = new ArrayList();
    styles.add(NotationFactory.eINSTANCE.createShapeStyle());
    return styles;
  }

  /**
   * @generated
   */
  protected void decorateView(View containerView, View view,
      IAdaptable semanticAdapter, String semanticHint, int index,
      boolean persisted) {
    if (semanticHint == null) {
      semanticHint = WebspecmodelVisualIDRegistry
          .getType(PanelContainerEditPart.VISUAL_ID);
      view.setType(semanticHint);
    }
    super.decorateView(containerView, view, semanticAdapter, semanticHint,
        index, persisted);
    IAdaptable eObjectAdapter = null;
    EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
    if (eObject != null) {
      eObjectAdapter = new EObjectAdapter(eObject);
    }
    getViewService().createNode(
        eObjectAdapter,
        view,
        WebspecmodelVisualIDRegistry
            .getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID),
        ViewUtil.APPEND, true, getPreferencesHint());
  }
}
