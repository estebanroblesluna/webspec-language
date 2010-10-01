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

import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.ExistingWidgetException;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.ListOfContainer;

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class ContainerTestCase extends TestCase {

  public void testIndexVariable() {
    ListOfContainer container = new ListOfContainer();
    container.setIndexVariable("i");
    assertEquals("i", container.getIndexVariable());
  }

  public void testAdd() {
    Container container = new ListOfContainer();

    Label label = new Label();
    label.setName("l");
    container.addWidget(label);

    assertEquals(label, container.getWidgetNamed("l"));
    assertEquals(container, label.getContainer());

    Button b = new Button();
    b.setName("l");
    try {
      container.addWidget(b);
    } catch (ExistingWidgetException e) {
      assertEquals("l", e.getName());
    }
  }
}
