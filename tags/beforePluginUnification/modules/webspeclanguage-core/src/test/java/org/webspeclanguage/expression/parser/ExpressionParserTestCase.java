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
package org.webspeclanguage.expression.parser;

import junit.framework.TestCase;

import org.webspeclanguage.api.WidgetProvider;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.expression.core.InteractionPropertyExpression;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.expression.core.WidgetReference;
import org.webspeclanguage.impl.expression.parser.ExpressionParser;
import org.webspeclanguage.impl.expression.parser.ParsingException;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Panel;
import org.webspeclanguage.impl.widget.TextField;

/**
 * @author Esteban Robles Luna
 */
public class ExpressionParserTestCase extends TestCase {

  private ExpressionParser parser;
  private DiagramImpl diagram;
  private InteractionImpl interaction;

  public void setUp() throws Exception {
    super.setUp();
    this.parser = new ExpressionParser();
    this.diagram = new DiagramImpl("diagram");
    
    interaction = new InteractionImpl("Interaction");

    TextField textField = new TextField();
    textField.setName("widget");
    interaction.addWidget(textField);
    
    ListOfContainer listContainer = new ListOfContainer();
    listContainer.setName("widgetList");
    interaction.addWidget(listContainer);
    
    Panel panel = new Panel();
    panel.setName("panel");
    listContainer.addWidget(panel);
    
    Button button = new Button();
    button.setName("button");
    panel.addWidget(button);

    ListOfContainer listContainer2 = new ListOfContainer();
    listContainer2.setName("widgetList");
    listContainer.addWidget(listContainer2);

    this.diagram.addInteraction(interaction);
  }

  public void testParsing() throws ParsingException {
    this.basicParse("102");
    this.basicParse("102.54");
    this.basicParse("true");
    this.basicParse("false");
    this.basicParse("${aaa}");
    this.basicParse("${aAAAaa}");
    this.basicParse("\"hola como estas\"");
    this.basicParse("\"\"");
    this.basicParse("\"33como seguimos\"");
    this.basicParse("\"http://www.gmail.com\"");
    this.basicParse("\"esteban@aa\"");

    this.basicParse("${aaa} && ${bbb}");
    this.basicParse("${aaa} || ${b}");

    this.basicParse("! ${aaa}");

    this.basicParse("1 + 2");
    this.basicParse("1 - 2");
    this.basicParse("1 * 3");
    this.basicParse("1 / 3");

    this.basicParse("1 > 2");
    this.basicParse("1 >= 2");
    this.basicParse("1 != 2");
    this.basicParse("1 = 2");
    this.basicParse("1 < 2");
    this.basicParse("1 <= 2");

    this.basicParse("1 && 2");
    this.basicParse("1 || 2");
    this.basicParse("1 -> 2");
    this.basicParse("%primitiveCall(\"a\")");

    this.basicParse("5.3 / 6 + (true > false)");
    this.basicParse("%native(true)");
    this.basicParse("%native()");
    this.basicParse("wait()");

    this.basicParse("textExists(\"Hola\") && true");
    this.basicParse("false \n && true");
    this.basicParse("[1, [1, 2], 3, \"this is a test\", true]");
    this.basicParse("${userAndPass}->${aaa}");
    this.basicParse("${userAndPass}[123]");
    
    this.basicParse("${var}[1 + 2]");
    this.basicParse("Interaction.title", this.interaction, InteractionPropertyExpression.class);
    this.basicParse("Interaction.widget.property", this.interaction, WidgetPropertyReference.class);
    this.basicParse("Interaction.widget", this.interaction, WidgetReference.class);
    this.basicParse("Interaction.widgetList.property", this.interaction, WidgetPropertyReference.class);
    this.basicParse("Interaction.widgetList[1].widgetList.property", this.interaction, WidgetPropertyReference.class);
    this.basicParse("Interaction.widgetList[1].widgetList", this.interaction, WidgetReference.class);
    this.basicParse("Interaction.widgetList[1].panel.button.property", this.interaction, WidgetPropertyReference.class);
  }

  private void basicParse(String string) throws ParsingException {
    this.parser.parseFor(string, this.diagram);
  }

  private void basicParse(String string, WidgetProvider provider, Class<?> aClass) throws ParsingException {
    assertTrue(aClass.isInstance(this.parser.parseFor(string, provider)));
  }
}
