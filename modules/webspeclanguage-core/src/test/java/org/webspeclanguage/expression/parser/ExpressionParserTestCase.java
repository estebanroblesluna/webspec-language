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

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.expression.parser.ExpressionParser;
import org.webspeclanguage.expression.parser.ParsingException;
import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.Panel;
import org.webspeclanguage.widget.TextField;

/**
 * @author Esteban Robles Luna
 */
public class ExpressionParserTestCase extends TestCase {

  private ExpressionParser parser;
  private WebSpecDiagram diagram;

  public void setUp() throws Exception {
    super.setUp();
    this.parser = new ExpressionParser();
    this.diagram = new WebSpecDiagram("diagram");
    
    WebSpecInteraction interaction = new WebSpecInteraction("Interaction", this.diagram);

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
    this.basicParse("Interaction.property");
    this.basicParse("Interaction.widget.property");
    this.basicParse("Interaction.widgetList.property");
    this.basicParse("Interaction.widgetList[1].widgetList.property");
    this.basicParse("Interaction.widgetList[1].panel.button.property");
  }

  private void basicParse(String string) throws ParsingException {
    this.parser.parseFor(string, this.diagram);
  }
}
