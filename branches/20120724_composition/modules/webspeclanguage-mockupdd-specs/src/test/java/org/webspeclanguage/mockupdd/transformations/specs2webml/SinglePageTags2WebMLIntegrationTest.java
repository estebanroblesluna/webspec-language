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

import org.junit.Test;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DataUnit;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SaveAndDeleteActionSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessor;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.HypertextSpecs2WebMLWebModel;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.SUIPage2Page;
import org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel.WMTransformationFacade;

/**
 * @author Jose Matias Rivero
 */
public class SinglePageTags2WebMLIntegrationTest extends SuiTestCase {

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
  public void testDataUnitAndEntityGeneration() throws TagApplicationException {
    Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Panel panel = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
    p1.addChild(panel);
    Label label1 = this.getFactory().createLabel("label1", 0, 0, 0, 0, "lab3l");
    panel.addChild(label1);
    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);
    this.getSuiConfig().createTagApplication(panel, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(label1, "Data", "Data", "Class1.attribute");
    

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
    
    assertEquals(1, h.getPanelClassMapping2DataUnits().size());
    
    SUIPage2Page suiPage2Page = (SUIPage2Page) h.getSuiPage2Pages().toArray()[0];

    assertEquals("page1" , suiPage2Page.getWebmlPage().getName());
    
    DataUnit dataUnit = (DataUnit) suiPage2Page.getWebmlPage().getContentUnits().values().toArray()[0];
    
    assertEquals("panel1", dataUnit.getName());
    
    
    
    /*
     * There should be
     * - 1 WebML Entity called "Class1", with an attribute called "attribute"
     * - 1 WebML Page
     * - 1 WebML DataUnit associated to "Class1" entity
     */
    
  }
  
  
  
}
