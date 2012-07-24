package org.webspeclanguage.mockupdd.transformations.specs2webml;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.NavigationSpec2IntraNavigationUnitToUnit;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.SUIPage2Page;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

public class SinglePageIntraNavigationTest extends SuiTestCase{


	private CompositeSuiModelProcessor processor;
	private DMTransformationFacade dmTransformationFacade;
	private WMTransformationFacade wmTransformationFacade;


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.processor = new CompositeSuiModelProcessor(new ClassAndAttributeSpecInferer(), new NavigationSpecInferer(), new SaveAndDeleteActionSpecInferer());
		this.dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
		this.wmTransformationFacade = WMTransformationFacade.getWMTransformationFacade();
	}

	public void testLinkSpecInference() throws TagApplicationException {
		
		Page page1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");

		Panel panel1 = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
		page1.addChild(panel1);
		Panel panel2 = this.getFactory().createPanel("panel2", 0, 0, 0, 0, "page2");
		page1.addChild(panel2);

		SuiModel model = this.getFactory().createSuiModel();
		model.addPage(page1);

		this.getSuiConfig().createTagApplication(panel1, "Data", "Data", "Class1");
		this.getSuiConfig().createTagApplication(panel2, "Data", "Data", "panel1:Class1");

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
	    
	    //Units number
	    SUIPage2Page suipage2pages = (SUIPage2Page) h.getSuiPage2Pages().toArray()[0];
	    
	    assertEquals(2, suipage2pages.getWebmlPage().getContentUnits().size());
	    
	    //Links Page to Page number
	   	    
	    assertEquals(1, h.getNavigationSpec2IntraNavigationUnitToUnitList().size());

	    NavigationSpec2IntraNavigationUnitToUnit nav = (NavigationSpec2IntraNavigationUnitToUnit) h.getNavigationSpec2IntraNavigationUnitToUnitList().toArray()[0];
	    
	    assertEquals(nav.getLink().getFrom().getName(), "panel1");
	    assertEquals(nav.getLink().getTo().getName(), "panel2");
		
	}
}























