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
package org.webspeclanguage.translator.balsamiq;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.webspeclanguage.mockupdd.codegen.generator.Mockup;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.SuiTranslator;
import org.webspeclanguage.mockupdd.translator.annotation.JsonAnnotationParser;
import org.webspeclanguage.mockupdd.translator.balsamiq.BalsamiqWidgetParser;
import org.webspeclanguage.translator.common.WidgetParserTestCase;

/**
 * @author Jose Matias Rivero
 */
public class BalsamiqWidgetParserTestCase extends WidgetParserTestCase {

  private SuiTranslator<String> balsamiqTranslator;

  public void setUp() throws Exception {
    super.setUp();
    this.setBalsamiqTranslator(
      new MockupProcessingEngine<String>(
        new BalsamiqWidgetParser(
          this.getFactory()), 
          new JsonAnnotationParser(this.getFactory()), 
        this.getFactory()));
  }

  public void testModelTranslation() throws Exception{
    SuiModel m = this.getBalsamiqTranslator().translateModelFrom(
      Arrays.asList(
      (Mockup<String>)
      new Mockup<String>(
        FileUtils.readFileToString(new File("src/test/resources/org/webspeclanguage/metamock/translator/balsamiq/model.bmml")),
        new MockupContainerInfo("balsamiqModel", "file://src/test/resources/org/webspeclanguage/metamock/translator/balsamiq/model.bmml/"))));
    for (Page page : m.getPages()) {
      if (page.getTitle().equals("New event")) {
        this.assertOnNewEventPage(page);
      } else if (page.getTitle().equals("Invite friends to event")) {
        this.assertConditionsOnInviteFriendsPage(page);
      } else if (page.getTitle().equals("Task Manager")) {
        this.assertConditionsOnTaskManagerPage(page);
      } else {
        fail("Invalid page name");
      }
    }
    this.assertModelFeatures(m);
  }

  void setBalsamiqTranslator(SuiTranslator<String> translator) {
    this.balsamiqTranslator = translator;
  }

  SuiTranslator<String> getBalsamiqTranslator() {
    return balsamiqTranslator;
  }

}
