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
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;


/**
 * @author Jose Matias Rivero
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
    Panel panel3 = this.getFactory().createPanel("panel3", 0, 0, 0, 0, "panel3");
    Panel panel4 = this.getFactory().createPanel("panel4", 0, 0, 0, 0, "panel4");
    Label l = this.getFactory().createLabel("tb1", 0, 0, 0, 0, "label1");
    
    p1.addChild(panel1);
    p1.addChild(panel4);
    p2.addChild(panel3);
    p2.addChild(panel2);
    panel1.addChild(l);
     
    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);
    model.addPage(p2);

    this.getSuiConfig().createTagApplication(panel1, "Data", "Data", "Class1");
    this.getSuiConfig().createTagApplication(panel2, "Data", "Data", "panel3:Class2.attr -> Class3");
    this.getSuiConfig().createTagApplication(l, "Data", "Data", "panel4:Class1.attrib1");
    
    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);

    this.inferer.process(suiSpecs);
    
    assertEquals(0, suiSpecs.getErrors().size());
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
    
    assertNotNull(suiSpecs.getCWClassMappingSpecForWidget(panel1));
    assertSame(class1, suiSpecs.getCWClassMappingSpecForWidget(panel1).getClassSpec());
    assertSame(panel1, suiSpecs.getCWClassMappingSpecForWidget(panel1).getWidget());
    assertNotNull(suiSpecs.getCWClassMappingSpecForWidget(panel2));
    assertSame(class3, suiSpecs.getCWClassMappingSpecForWidget(panel2).getClassSpec());
    assertSame(panel2, suiSpecs.getCWClassMappingSpecForWidget(panel2).getWidget());
    
    assertNotNull(suiSpecs.getAttributeMappingSpecForWidget(l));
    assertEquals("attrib1", suiSpecs.getAttributeMappingSpecForWidget(l).getAttributeSpec().getName());
    assertSame(l, suiSpecs.getAttributeMappingSpecForWidget(l).getWidget());
    assertSame(panel4, suiSpecs.getAttributeMappingSpecForWidget(l).getDataSource());
    
  }

}
