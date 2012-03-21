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

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.processors.ClassAndAttributeSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.CompositeSuiModelProcessor;
import org.webspeclanguage.mockupdd.specs.processors.NavigationSpecInferer;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessor;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;

/**
 * @author Jose Matias Rivero
 */
public class SinglePageTags2WebMLIntegrationTest extends SuiTestCase {

  SuiModelProcessor processor;
  DMTransformationFacade dmTransformationFacade;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.processor = new CompositeSuiModelProcessor(new NavigationSpecInferer(), new ClassAndAttributeSpecInferer());
    this.dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
  }
  
  public void testDataUnitAndClassGeneration() throws TagApplicationException {
    Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Panel panel = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "page1");
    p1.addChild(panel);
    
    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);
    this.getSuiConfig().createTagApplication(panel, "Data", "Data", "Class1");
    
    SuiSpecsInferenceState specs = new SuiSpecsInferenceState(model);
    this.processor.process(specs);
    
    DataSpecs2WebMLDataModel d = this.dmTransformationFacade.transformData(specs);
    assertEquals(1, d.getDataModel().getEntitys().keySet().size());
    
    /*
     * There should be
     * - 1 WebML Entity called "Class1"
     * - 1 WebML Page
     * - 1 WebML DataUnit associated to "Class1" entity
     */
    
    
  }
  
}
