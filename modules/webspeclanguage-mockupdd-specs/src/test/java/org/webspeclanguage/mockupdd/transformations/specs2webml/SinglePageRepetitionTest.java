package org.webspeclanguage.mockupdd.transformations.specs2webml;

import java.util.ArrayList;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.IndexUnit;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.SUIPage2Page;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

public class SinglePageRepetitionTest extends SuiTestCase{

	private CompositeSuiModelProcessor processor;
	private DMTransformationFacade dmTransformationFacade;
	private WMTransformationFacade wmTransformationFacade;


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.processor = new CompositeSuiModelProcessor(new NavigationSpecInferer(), new ClassAndAttributeSpecInferer(), new SaveAndDeleteActionSpecInferer());
		this.dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
		this.wmTransformationFacade = WMTransformationFacade.getWMTransformationFacade();
	}

	public void testLinkSpecInference() throws TagApplicationException {
		
		Page page1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");

		Repetition repetition1 = this.getFactory().createRepetition("repetition1", 0, 0, 0, 0, new ArrayList<Widget>(), 0, 0, "page1");
		page1.addChild(repetition1);
		
		SuiModel model = this.getFactory().createSuiModel();
		model.addPage(page1);

		this.getSuiConfig().createTagApplication(page1, "Nav", "Node", "Page1");
		this.getSuiConfig().createTagApplication(repetition1, "Data", "Data", "Class1");

		SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
		this.processor.process(specs);
		assertEquals(0, specs.getErrors().size());

		DataSpecs2WebMLDataModel d = this.dmTransformationFacade.transformData(specs);

		assertEquals(1, d.getDataModel().getEntitys().keySet().size());

		Entity ent1 = (Entity)d.getDataModel().getEntitys().values().toArray()[0];

		assertEquals("Class1", ent1.getName()); 

		HypertextSpecs2WebMLWebModel h = this.wmTransformationFacade.transformHypertext(specs, d);
		
		//Page number
	    assertEquals(1, h.getSuiPage2Pages().size());
	    
	    //Unit number
	    SUIPage2Page suiPage2Page = (SUIPage2Page) h.getSuiPage2Pages().toArray()[0];    
	    assertEquals(1, suiPage2Page.getWebmlPage().getContentUnits().size());

	    //Repetition to Index Unit
	    
	    IndexUnit indexUnit = (IndexUnit) suiPage2Page.getWebmlPage().getContentUnits().values().toArray()[0];
	    
	    assertEquals("repetition1", indexUnit.getName());
	    
	    assertEquals("Class1", indexUnit.getEntity().getName());

		
	}
}
