package webspecplugin.webspecmodel;

import junit.framework.TestCase;

public class WebspecmodelFactoryTestCase extends TestCase {

	public void testAddProperty() {
		Button button = WebspecmodelFactory.eINSTANCE.createButton();
		button.setLocation("");
		
		PanelContainer panel = WebspecmodelFactory.eINSTANCE.createPanelContainer();
    assertFalse(panel.getWidgets().contains(button));
		panel.getWidgets().add(button);
		assertTrue(panel.getWidgets().contains(button));
	}
}
