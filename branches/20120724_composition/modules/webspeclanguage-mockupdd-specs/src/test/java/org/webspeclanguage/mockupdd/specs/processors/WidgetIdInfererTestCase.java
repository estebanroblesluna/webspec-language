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

import java.util.ArrayList;

import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * @author Jose Matias Rivero
 */
public class WidgetIdInfererTestCase extends SuiTestCase {

  private WidgetIdInferer widgetIdInferer;
  private SuiDefaultConfig suiConfig;
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.widgetIdInferer = new WidgetIdInferer();
    this.suiConfig = SuiDefaultConfig.getInstance();
  }

  public void testWidgetIdInference() throws TagApplicationException {
    Panel panel = this.getFactory().createPanel("panel1", 0, 0, 0, 0, "panel1");
    Repetition rep = this.getFactory().createRepetition("repetition1", 0, 0, 0, 0, new ArrayList<Widget>(), 1, 1, "rep1");
    Page p = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    
    SuiModel model = this.suiConfig.getFactory().createSuiModel();
    model.addPage(p);
    
    this.suiConfig.createTagApplication(panel, "Data", "Data", "Class1");
    this.suiConfig.createTagApplication(rep, "Data", "Data", "Class1");
    p.addChild(panel);
    p.addChild(rep);
    p.addChild(this.getFactory().createPanel("panel2", 0, 0, 0, 0, "panel2"));
    
//    this.widgetIdInferer.process(model);
  }
  
}
