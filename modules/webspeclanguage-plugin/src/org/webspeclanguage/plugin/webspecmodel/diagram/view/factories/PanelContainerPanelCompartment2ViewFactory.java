package org.webspeclanguage.plugin.webspecmodel.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class PanelContainerPanelCompartment2ViewFactory extends
    BasicNodeViewFactory {

  /**
   * @generated
   */
  protected List createStyles(View view) {
    List styles = new ArrayList();
    styles.add(NotationFactory.eINSTANCE.createDrawerStyle());
    styles.add(NotationFactory.eINSTANCE.createSortingStyle());
    styles.add(NotationFactory.eINSTANCE.createFilteringStyle());
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
          .getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID);
      view.setType(semanticHint);
    }
    super.decorateView(containerView, view, semanticAdapter, semanticHint,
        index, persisted);
    setupCompartmentTitle(view);
    setupCompartmentCollapsed(view);
  }

  /**
   * @generated
   */
  protected void setupCompartmentTitle(View view) {
    TitleStyle titleStyle = (TitleStyle) view
        .getStyle(NotationPackage.eINSTANCE.getTitleStyle());
    if (titleStyle != null) {
      titleStyle.setShowTitle(true);
    }
  }

  /**
   * @generated
   */
  protected void setupCompartmentCollapsed(View view) {
    DrawerStyle drawerStyle = (DrawerStyle) view
        .getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
    if (drawerStyle != null) {
      drawerStyle.setCollapsed(false);
    }
  }
}