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
package org.webspeclanguage.base;

import junit.framework.TestCase;

import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.Label;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.TextField;

/**
 * @author Esteban Robles Luna
 */
public class InteractionTestCase extends TestCase {

  private Interaction interaction;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    this.interaction = new Interaction("i");
  }

  public void testAccessors() {
    assertEquals("i", this.interaction.toString());
    assertEquals("i", this.interaction.getName());

    this.interaction.setMockupFile("mock");
    assertEquals("mock", this.interaction.getMockupFile());
  }

  public void testCreations() {
    TextField textField = this.interaction.createTextFieldWithLocation("loc");
    assertEquals("loc", textField.getLocation());

    Button button = this.interaction.createButtonWithLocation("bloc");
    assertEquals("bloc", button.getLocation());

    Label label = this.interaction.createLabelWithLocation("lloc");
    assertEquals("lloc", label.getLocation());

    ListOfContainer widgetList = this.interaction
        .createListOfContainer("index");
    assertEquals("index", widgetList.getIndexVariable());
  }
}
