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
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;


/**
 * @author José Matías Rivero
 */
public class ClassAndAttributeSpecInfererTestCase extends SuiTestCase {

  private ClassAndAttributeSpecInferer inferer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.inferer = new ClassAndAttributeSpecInferer();
  }

  public void testClassInference() throws TagApplicationException {
    Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Page p2 = this.getFactory().createPage("page2", 0, 0, 0, 0, "page2", "page2");
    Panel panel1 = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "panel1");
    Panel panel2 = this.getFactory().createPanel("panel2", 0, 0, 0, 0, "panel2");
    TextBox tb = this.getFactory().createTextBox("tb1", 0, 0, 0, 0);
    
    p1.addChild(panel1);
    p2.addChild(panel2);
    panel1.addChild(tb);
     

    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);
    model.addPage(p2);

    this.getSuiConfig().createTagApplication(panel1, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(panel2, "Data", "Data", "w1:Class2.attr -> Class3");
    this.getSuiConfig().createTagApplication(tb, "Data", "Data", "Class1.attrib1");

    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);

    this.inferer.process(suiSpecs);
    
    assertEquals(3, suiSpecs.getClassSpecs().size());
    
    ClassSpec class1 = suiSpecs.getClassSpecByName("Class1");
    assertEquals("Class1", class1.getName());
    assertEquals(1, class1.getAttributes().size());
    assertEquals("attrib1", class1.getAttributes().iterator().next().getName());
    
    ClassSpec class3 = suiSpecs.getClassSpecByName("Class3");
    assertEquals("Class3", class3.getName());
    
    ClassSpec class2 = suiSpecs.getClassSpecByName("Class2");
    assertEquals("Class2", class2.getName());
    assertEquals(1, class2.getAssociations().size());
    assertEquals("attr", class2.getAssociations().iterator().next().getAssociationName());
    assertSame(class3, class2.getAssociations().iterator().next().getDestinationClass());
  }

}
