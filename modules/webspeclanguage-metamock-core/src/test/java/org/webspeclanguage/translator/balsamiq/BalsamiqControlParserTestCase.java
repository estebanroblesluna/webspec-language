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

import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.translator.MetaMockProcessingEngine;
import org.webspeclanguage.metamock.translator.MetaMockTranslationException;
import org.webspeclanguage.metamock.translator.MetaMockTranslator;
import org.webspeclanguage.metamock.translator.MockupContainerInfo;
import org.webspeclanguage.metamock.translator.annotation.MetaMockJsonAnnotationParser;
import org.webspeclanguage.metamock.translator.balsamiq.BalsamiqControlParser;
import org.webspeclanguage.translator.common.ControlParserTestCase;

/**
 * @author Jose Matias Rivero
 */
public class BalsamiqControlParserTestCase extends ControlParserTestCase {

  private MetaMockTranslator<File> balsamiqTranslator;

  public void setUp() throws Exception {
    super.setUp();
    this.setBalsamiqTranslator(
      new MetaMockProcessingEngine<File>(
        new BalsamiqControlParser(
          this.getFactory()), 
          new MetaMockJsonAnnotationParser(this.getFactory()), 
        this.getFactory()));
  }

  public void testModelTranslation() throws MetaMockTranslationException {
    MetaMockModel m = this.getBalsamiqTranslator().translateModelFrom(
            new File("src/test/resources/org/webspeclanguage/metamock/translator/balsamiq/model.bmml"), new MockupContainerInfo("balsamiqModel"));
    for (Page page : m.getPages()) {
      if (page.getTitle().equals("New event"))
        this.assertOnNewEventPage(page);
      else if (page.getTitle().equals("Invite friends to event"))
        this.assertConditionsOnInviteFriendsPage(page);
      else if (page.getTitle().equals("Task Manager"))
        this.assertConditionsOnTaskManagerPage(page);
      else
        fail("Invalid page name");
    }
    this.assertModelFeatures(m);
  }

  void setBalsamiqTranslator(MetaMockTranslator<File> translator) {
    this.balsamiqTranslator = translator;
  }

  MetaMockTranslator<File> getBalsamiqTranslator() {
    return balsamiqTranslator;
  }

}
