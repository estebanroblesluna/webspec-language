package webspecplugin.webspecmodel.diagram.providers;

import webspecplugin.webspecmodel.Interaction;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.WebspecmodelFactory;
import webspecplugin.webspecmodel.WebspecmodelPackage;
import webspecplugin.webspecmodel.diagram.expressions.WebspecmodelOCLFactory;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

  /**
   * @generated
   */
  public static void init_Interaction_2001(Interaction instance) {
    try {
      PanelContainer newInstance_0_0 = WebspecmodelFactory.eINSTANCE.createPanelContainer();
      instance.setRoot(newInstance_0_0);
      Object value_0_0_0 = name_root_Interaction_2001(newInstance_0_0);
      newInstance_0_0.setName((String) value_0_0_0);

    } catch (RuntimeException e) {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
    }
  }

  /**
   * @generated
   */
  private static String name_root_Interaction_2001(PanelContainer self) {
    // TODO: implement this method to return value  
    // for webspecplugin.webspecmodel.WebspecmodelPackage.eINSTANCE.getWidget_Name()
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException("No user java implementation provided in 'name_root_Interaction_2001' operation"); //$NON-NLS-1$
  }

}
