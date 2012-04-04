package org.webspeclanguage.mockupdd.transformations.specs2webml;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.NavigationSpec2NavigationPageToPage;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

	/**
	 * @author José Matías Rivero
	 */
	public class MultiPageTags2WebMLIntegrationTest1 extends SuiTestCase {

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
	  
	  public void testLinkToPageWebMLGeneration() throws TagApplicationException {
	    Page page1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
	    Page page2 = this.getFactory().createPage("page2", 0, 0, 0, 0, "page2", "page2");
	    Button b = this.getFactory().createButton("button1", 0, 0, 0, 0, "Button");
	    page2.addChild(b);

	    SuiModel model = this.getFactory().createSuiModel();
	    model.addPage(page1);
	    model.addPage(page2);

	    this.getSuiConfig().createTagApplication(page1, "Nav", "Node", "Page1");
	    this.getSuiConfig().createTagApplication(b, "Nav", "Link", "Page1");

	    SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
	    this.processor.process(specs);
	    assertEquals(0, specs.getErrors().size());
	    
		DataSpecs2WebMLDataModel d = this.dmTransformationFacade.transformData(specs);
	    
	    HypertextSpecs2WebMLWebModel h = this.wmTransformationFacade.transformHypertext(specs, d);
	    
	    //Page number
	    assertEquals(2, h.getSuiPage2Pages().size());
	    
	    //Links Page to Page number
	    assertEquals(1, h.getNavigationSpec2NavigationPageToPageList().size());

	    NavigationSpec2NavigationPageToPage nav = h.getNavigationSpec2NavigationPageToPageList().get(0);
	    
	    //Link From page1 to page 2
	    assertEquals("page1", nav.getLink().getFrom().getId());
	    assertEquals("page2", nav.getLink().getTo().getId());
	    
	    
	    /*
	     * There should be
	     * - 2 WebML Pages called "Page1" and "Page2"
	     * - A link from Page2 to Page1
	     */
	    
	  }
}
