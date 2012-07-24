package org.webspeclanguage.plugin.webspecmodel.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DiagramViewFactory extends
    org.eclipse.gmf.runtime.diagram.ui.view.factories.DiagramViewFactory {

  /**
   * @generated
   */
  protected List createStyles(View view) {
    List styles = new ArrayList();
    styles.add(NotationFactory.eINSTANCE.createDiagramStyle());
    return styles;
  }

  /**
   * @generated
   */
  protected MeasurementUnit getMeasurementUnit() {
    return MeasurementUnit.PIXEL_LITERAL;
  }
}
