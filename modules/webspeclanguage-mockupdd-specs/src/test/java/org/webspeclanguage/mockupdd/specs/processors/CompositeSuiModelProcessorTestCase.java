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
package org.webspeclanguage.mockupdd.specs.processors;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * @author Jose Matias Rivero
 */
public class CompositeSuiModelProcessorTestCase extends SuiTestCase {

  CompositeSuiModelProcessor processor;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.processor = new CompositeSuiModelProcessor(new NavigationSpecInferer(), new ClassAndAttributeSpecInferer());
  }
  
  public void testModelProcessing() throws TagApplicationException {
    Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Page p2 = this.getFactory().createPage("page2", 0, 0, 0, 0, "page2", "page2");
    Panel panel11 = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "panel1");
    Panel panel22 = this.getFactory().createPanel("panel2", 0, 0, 0, 0, "panel2");
    Panel panel21 = this.getFactory().createPanel("panel3", 0, 0, 0, 0, "panel3");
    Panel panel12 = this.getFactory().createPanel("panel4", 0, 0, 0, 0, "panel4");
    Label l = this.getFactory().createLabel("tb1", 0, 0, 0, 0, "label1");
    Button b = this.getFactory().createButton("b", 0, 0, 0, 0, "label1");
    
    p1.addChild(panel11);
    p1.addChild(panel12);
    p2.addChild(panel21);
    p2.addChild(panel22);
    panel11.addChild(l);
    panel11.addChild(b);
     
    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);
    model.addPage(p2);

    this.getSuiConfig().createTagApplication(panel11, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(panel22, "Data", "Data", "panel3:Class2.attr -> Class3");
    this.getSuiConfig().createTagApplication(l, "Data", "Data", "panel4:Class1.attrib1");
    this.getSuiConfig().createTagApplication(b, "Nav", "Link", "Page2");
    this.getSuiConfig().createTagApplication(p2, "Nav", "Node", "Page2");
    
    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);
    
    this.processor.process(suiSpecs);
    
    assertEquals(0, suiSpecs.getErrors().size());
    assertEquals(1, suiSpecs.getNavigationSpecs().size());
    assertEquals(3, suiSpecs.getClassSpecs().size());
  }
  
}
