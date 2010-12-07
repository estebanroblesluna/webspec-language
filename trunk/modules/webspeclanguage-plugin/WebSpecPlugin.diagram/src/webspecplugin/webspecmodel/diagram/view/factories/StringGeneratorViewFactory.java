package webspecplugin.webspecmodel.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorNameEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;

/**
 * @generated
 */
public class StringGeneratorViewFactory extends AbstractShapeViewFactory {

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
          .getType(StringGeneratorEditPart.VISUAL_ID);
      view.setType(semanticHint);
    }
    super.decorateView(containerView, view, semanticAdapter, semanticHint,
        index, persisted);
    if (!DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry
        .getModelID(containerView))) {
      EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
          .createEAnnotation();
      shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
      shortcutAnnotation.getDetails().put("modelID", DiagramEditPart.MODEL_ID); //$NON-NLS-1$
      view.getEAnnotations().add(shortcutAnnotation);
    }
    IAdaptable eObjectAdapter = null;
    EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
    if (eObject != null) {
      eObjectAdapter = new EObjectAdapter(eObject);
    }
    getViewService().createNode(
        eObjectAdapter,
        view,
        WebspecmodelVisualIDRegistry
            .getType(StringGeneratorNameEditPart.VISUAL_ID), ViewUtil.APPEND,
        true, getPreferencesHint());
  }
}
