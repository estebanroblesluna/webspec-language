/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.webspeclanguage.mockupdd.transformations.specs2webml;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DataUnit;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.NavigationSpec2NavigationPUnitToPUnit;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

/**
 * @author José Matías Rivero
 */
public class MultiPageTags2WebMLIntegrationTest2 extends SuiTestCase {

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
    Page page2 = this.getFactory().createPage("page2", 0, 0, 0, 0, "page2", "page2");
    Button b = this.getFactory().createButton("button1", 0, 0, 0, 0, "Button");
    page2.addChild(b);
    Panel panel1 = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
    page1.addChild(panel1);
    Panel panel2 = this.getFactory().createPanel("panel2", 0, 0, 0, 0, "page2");
    page2.addChild(panel2);

    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(page1);
    model.addPage(page2);

    this.getSuiConfig().createTagApplication(page1, "Nav", "Node", "Page1");
    this.getSuiConfig().createTagApplication(panel1, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(panel2, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(b, "Nav", "Link", "Page1");
    this.getSuiConfig().createTagApplication(b, "Nav", "Transfer", "panel2", "panel1");

    SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
    this.processor.process(specs);
    assertEquals(0, specs.getErrors().size());
    
    DataSpecs2WebMLDataModel d = this.dmTransformationFacade.transformData(specs);
    
    assertEquals(1, d.getDataModel().getEntitys().keySet().size());
	
	Entity ent1 = (Entity)d.getDataModel().getEntitys().values().toArray()[0];
    
    assertEquals("Class1", ent1.getName()); 
    
    
    HypertextSpecs2WebMLWebModel h = this.wmTransformationFacade.transformHypertext(specs, d);
    
    //Page number
    assertEquals(2, h.getSuiPage2Pages().size());
    
    //Link Content Unit to Content Unit number
    assertEquals(1, h.getNavigationSpec2NavigationPUnitToPUnitList().size());

    NavigationSpec2NavigationPUnitToPUnit nav = h.getNavigationSpec2NavigationPUnitToPUnitList().get(0);
    
    //Link From content unit1 to content unit2 
    assertEquals("panel2", nav.getLink().getFrom().getName());
    assertEquals("panel1", nav.getLink().getTo().getName());
    
    
    //DataUnits point to Class1
    DataUnit du1 = (DataUnit) nav.getLink().getFrom();
    DataUnit du2 = (DataUnit) nav.getLink().getTo();
    
    assertEquals("Class1", du1.getEntity().getName());
    assertEquals("Class1", du2.getEntity().getName());
    
    
    /*
     * There should be
     * - 2 WebML Pages called "Page1" and "Page2"
     * - 1 DataUnit in each page referencing the "Class1" entity
     * - A link from DataUnit in Page2 to the one in Page1
     */
    
  }
  
}
