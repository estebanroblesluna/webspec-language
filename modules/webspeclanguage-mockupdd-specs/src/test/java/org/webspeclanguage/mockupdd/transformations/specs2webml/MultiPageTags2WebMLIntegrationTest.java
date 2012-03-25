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

import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
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

/**
 * @author Jos� Mat�as Rivero
 */
public class MultiPageTags2WebMLIntegrationTest extends SuiTestCase {

  private CompositeSuiModelProcessor processor;
  private DMTransformationFacade dmTransformationFacade;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.processor = new CompositeSuiModelProcessor(new NavigationSpecInferer(), new ClassAndAttributeSpecInferer(), new SaveAndDeleteActionSpecInferer());
    this.dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
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

    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);
    this.processor.process(suiSpecs);
    assertEquals(0, suiSpecs.getErrors().size());
    
    /*
     * There should be
     * - 2 WebML Pages called "Page1" and "Page2"
     * - A link from Page2 to Page1
     */
    
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

    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);
    this.processor.process(suiSpecs);
    assertEquals(0, suiSpecs.getErrors().size());
    
    /*
     * There should be
     * - 2 WebML Pages called "Page1" and "Page2"
     * - 1 DataUnit in each page referencing the "Class1" entity
     * - A link from DataUnit in Page2 to the one in Page1
     */
    
  }
  
}
