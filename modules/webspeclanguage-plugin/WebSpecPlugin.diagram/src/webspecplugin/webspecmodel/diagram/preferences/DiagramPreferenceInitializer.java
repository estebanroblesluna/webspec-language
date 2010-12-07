package webspecplugin.webspecmodel.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

  /**
   * @generated
   */
  public void initializeDefaultPreferences() {
    IPreferenceStore store = getPreferenceStore();
    DiagramGeneralPreferencePage.initDefaults(store);
    DiagramAppearancePreferencePage.initDefaults(store);
    DiagramConnectionsPreferencePage.initDefaults(store);
    DiagramPrintingPreferencePage.initDefaults(store);
    DiagramRulersAndGridPreferencePage.initDefaults(store);

  }

  /**
   * @generated
   */
  protected IPreferenceStore getPreferenceStore() {
    return WebspecmodelDiagramEditorPlugin.getInstance().getPreferenceStore();
  }
}
