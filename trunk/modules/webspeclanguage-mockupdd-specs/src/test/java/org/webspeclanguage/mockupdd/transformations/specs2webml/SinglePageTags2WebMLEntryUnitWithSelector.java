package org.webspeclanguage.mockupdd.transformations.specs2webml;

import org.junit.Test;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.CreateUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.EntryUnit;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessor;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.SUIPage2Page;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.SaveActionSpec2CreateUnit;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

public class SinglePageTags2WebMLEntryUnitWithSelector  extends SuiTestCase {
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
		/*Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
		Panel panel = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
		p1.addChild(panel);
		TextBox textBox = this.getFactory().createTextBox("tb1", 0, 0, 0 , 0);
		ComboBox comboBox = this.getFactory().createComboBox("cb1", 0, 0, 0,0);
		
		panel.addChild(textBox);
		panel.addChild(comboBox);
		Button button = this.getFactory().createButton("button", 0, 0, 0, 0, "Save");
		panel.addChild(button);
		SuiModel model = this.getFactory().createSuiModel();
		model.addPage(p1);
		this.getSuiConfig().createTagApplication(panel, "Data", "Data", "Class1");
		this.getSuiConfig().createTagApplication(textBox, "Data", "Data", "Class1.attribute");
		this.getSuiConfig().createTagApplication(button, "Data", "Save", "Class1");
		this.getSuiConfig().createTagApplication(comboBox, "Data", "Data", "Class2");

		SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
		this.processor.process(specs);
		assertEquals(0, specs.getErrors().size());

		DataSpecs2WebMLDataModel d = this.dmTransformationFacade.transformData(specs);
		assertEquals(1, d.getDataModel().getEntitys().keySet().size());
		
		Entity ent1 = (Entity)d.getDataModel().getEntitys().values().toArray()[0];
	    
	    assertEquals("Class1", ent1.getName()); 
	    		
		assertEquals(2, ent1.getAttributes().size());
	    
	    HypertextSpecs2WebMLWebModel h = this.wmTransformationFacade.transformHypertext(specs, d);
	      
	    assertEquals(1, h.getSuiPage2Pages().size());
	    
	    assertEquals(1, h.getInputPanelSpec2EntryUnits().size());
	    
	    assertEquals(1, h.getSaveActionSpec2CreateUnits().size());
	    
	    SUIPage2Page suiPage2Page = (SUIPage2Page) h.getSuiPage2Pages().toArray()[0];
	    
	    //Unit number
	    assertEquals(1, suiPage2Page.getWebmlPage().getContentUnits().size());

	    
	    assertEquals("page1" , suiPage2Page.getWebmlPage().getName());
	    
	    EntryUnit entryUnit = (EntryUnit) suiPage2Page.getWebmlPage().getContentUnits().values().toArray()[0];
	    
	    assertEquals("panel1", entryUnit.getName());
	    
	    assertEquals("Class1", entryUnit.getEntity().getName());
	    
	    //Fields	    
	    assertEquals(2, entryUnit.getFields().size());
	    
	    //Create Unit
	    assertEquals(1, suiPage2Page.getHypertextSpec2WebMLWebModel().getSaveActionSpec2CreateUnits().size());
	    
	    SaveActionSpec2CreateUnit saveActionSpec2CreateUnit = (SaveActionSpec2CreateUnit)suiPage2Page.getHypertextSpec2WebMLWebModel().getSaveActionSpec2CreateUnits().toArray()[0];
	    
	    CreateUnit createUnit = saveActionSpec2CreateUnit.getCreateUnit();
	    
	    //Link
	    assertEquals(1, entryUnit.getLinks().size());
	    
	    NormalLink normalLink = (NormalLink)entryUnit.getLinks().values().toArray()[0];
	    
	    assertEquals(createUnit, normalLink.getTo());
	    
	    
	    

		 * There should be
		 * - 1 WebML Entity called "Class1", with an attribute called "attribute"
		 * - 1 WebML Page
		 * - 1 WebML EntryUnit associated to the "Class1" entity
		 * - 1 WebML CreateUnit associated to the "Class1" entity
		 * - 1 WebML Link from the EntryUnit to the CreateUnit
		 
*/
	}
}


