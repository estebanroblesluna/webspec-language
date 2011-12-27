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

import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * @author José Matías Rivero
 */
public class LinkSpecInfererTestCase extends SuiTestCase {

  private LinkSpecInferer linkSpecInferer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.linkSpecInferer = new LinkSpecInferer();
  }

  public void testLinkSpecInference() throws TagApplicationException {
    Page p = this.getFactory().createPage("page1", 0, 0, 0, 0, "page1", "page1");
    Page p2 = this.getFactory().createPage("page2", 0, 0, 0, 0, "page2", "page2");
    Button b = this.getFactory().createButton("button1", 0, 0, 0, 0, "Button");
    p2.addChild(b);

    SuiModel model = this.getFactory().createSuiModel();
    model.addPage(p);
    model.addPage(p2);

    this.getSuiConfig().createTagApplication(p, "Nav", "Node", "Page1");
    this.getSuiConfig().createTagApplication(b, "Nav", "Link", "Page1");

    SuiSpecsInferenceState suiSpecs = new SuiSpecsInferenceState(model);
    this.linkSpecInferer.process(suiSpecs);

    List<NavigationSpec> navSpecs = suiSpecs.getNavigationSpecsForPage(p2);
    assertEquals(1, navSpecs.size());
    NavigationSpec navSpec = navSpecs.iterator().next();
    assertSame(b, navSpec.getTrigger());
    assertSame(p, navSpec.getTo());
    assertEquals(0, suiSpecs.getErrors().size());
  }

}
