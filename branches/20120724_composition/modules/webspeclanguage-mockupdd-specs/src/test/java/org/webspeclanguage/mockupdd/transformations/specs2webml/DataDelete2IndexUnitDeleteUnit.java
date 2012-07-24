package org.webspeclanguage.mockupdd.transformations.specs2webml;

import java.util.ArrayList;

import org.junit.Test;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessor;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

public class DataDelete2IndexUnitDeleteUnit extends SuiTestCase {
	SuiModelProcessor processor;
	DMTransformationFacade dmTransformationFacade;
	WMTransformationFacade wmTransformationFacade;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.processor = new CompositeSuiModelProcessor(new ClassAndAttributeSpecInferer(), new NavigationSpecInferer(), new SaveAndDeleteActionSpecInferer());
		this.dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
		this.wmTransformationFacade = WMTransformationFacade.getWMTransformationFacade();
	}

	@Test
	public void testEntryUnitAndEntityGeneration() throws TagApplicationException {
		Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
		Panel panel = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
		p1.addChild(panel);
		
		Button button = this.getFactory().createButton("button", 0, 0, 0, 0, "Save");
		panel.addChild(button);
		SuiModel model = this.getFactory().createSuiModel();
		model.addPage(p1);
		this.getSuiConfig().createTagApplication(panel, "Data", "Data", "Class1");
		this.getSuiConfig().createTagApplication(button, "Data", "Delete", "Class1");

		SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
		this.processor.process(specs);
		assertEquals(0, specs.getErrors().size());
	
	}
}
