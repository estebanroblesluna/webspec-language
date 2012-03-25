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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * @author Jose Matias Rivero
 */
public class SaveAndDeleteActionSpecInfererTestCase extends SuiSpecsTestCase {

  SaveAndDeleteActionSpecInferer inferer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.inferer = new SaveAndDeleteActionSpecInferer();
  }

  @Test
  public void testActionSpecInference() throws TagApplicationException {
    Page p1 = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Panel panel1 = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "panel1");
    Panel panel2 = this.getFactory().createPanel("panel2", 0, 0, 0, 0, "panel2");
    Button b1 = this.getFactory().createButton("b1", 0, 0, 0, 0, "Save");
    Button b2 = this.getFactory().createButton("b2", 0, 0, 0, 0, "Save2");
    
    p1.addChild(panel1);
    p1.addChild(panel2);
    panel1.addChild(b1);
    p1.addChild(b2);
    
    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p1);

    this.getSuiConfig().createTagApplication(b1, "Data", "Save", "Class1");
    this.getSuiConfig().createTagApplication(b2, "Data", "Save", "panel2:Class2");
    this.getSuiConfig().createTagApplication(b1, "Data", "Delete", "Class1");
    this.getSuiConfig().createTagApplication(b2, "Data", "Delete", "panel2:Class2");
    
    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);

    ClassSpec class1Spec = this.dataSpecFactory.createClassSpec("Class1");
    suiSpecs.addClassSpec(class1Spec);
    PanelClassMappingSpec panel1ClassMapping = this.hypertextFactory.createPanelClassMappingSpec(panel1, class1Spec);
    suiSpecs.addClassMappingSpec(panel1ClassMapping);
    
    ClassSpec class2Spec = this.dataSpecFactory.createClassSpec("Class2");
    suiSpecs.addClassSpec(class2Spec);
    PanelClassMappingSpec panel2ClassMapping = this.hypertextFactory.createPanelClassMappingSpec(panel2, class2Spec);
    suiSpecs.addClassMappingSpec(panel2ClassMapping);

    this.inferer.process(suiSpecs);
    
    assertEquals(0, suiSpecs.getErrors().size());
    assertEquals(2, suiSpecs.getSaveActionSpecs().size());
    Collection<ClassMappingSpec> originalSpecs = new HashSet<ClassMappingSpec>(Arrays.asList(panel1ClassMapping, panel2ClassMapping));
    for (SaveActionSpec saveActionSpec : suiSpecs.getSaveActionSpecs()) {
      originalSpecs.remove(saveActionSpec.getSpec());
    }
    assertEquals(0, originalSpecs.size());
    
    assertEquals(0, suiSpecs.getErrors().size());
    assertEquals(2, suiSpecs.getDeleteActionSpecs().size());
    originalSpecs = new HashSet<ClassMappingSpec>(Arrays.asList(panel1ClassMapping, panel2ClassMapping));
    for (DeleteActionSpec deleteActionSpec : suiSpecs.getDeleteActionSpecs()) {
      originalSpecs.remove(deleteActionSpec.getSpec());
    }
    assertEquals(0, originalSpecs.size());
  }

}
