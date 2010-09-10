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
package org.webspeclanguage.widget;

import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.CheckBox;
import org.webspeclanguage.widget.ComboBox;
import org.webspeclanguage.widget.Label;
import org.webspeclanguage.widget.Link;
import org.webspeclanguage.widget.ListBox;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.Panel;
import org.webspeclanguage.widget.RadioButton;
import org.webspeclanguage.widget.TextField;
import org.webspeclanguage.widget.Widget;

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class WidgetTestCase extends TestCase {

  public void testWidgetCreation() {
    assertNotNull(new Button());
    assertNotNull(new CheckBox());
    assertNotNull(new ComboBox());
    assertNotNull(new Panel());
    assertNotNull(new ListOfContainer());
    assertNotNull(new Label());
    assertNotNull(new Link());
    assertNotNull(new ListBox());
    assertNotNull(new RadioButton());
    assertNotNull(new TextField());
  }

  public void testLocation() {
    Widget button = new Button();
    button.setId("b");
    assertEquals("id=b", button.getPreferedLocation());
    button.setLocation("loc");
    assertEquals("id=b", button.getPreferedLocation());
    button.setId(null);
    assertEquals("loc", button.getPreferedLocation());
  }
}
