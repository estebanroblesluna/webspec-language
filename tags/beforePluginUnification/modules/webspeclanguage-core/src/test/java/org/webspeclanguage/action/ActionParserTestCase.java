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
package org.webspeclanguage.action;

import java.util.List;

import junit.framework.TestCase;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.impl.action.ActionParser;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.expression.parser.ParsingException;
import org.webspeclanguage.impl.widget.TextField;

/**
 * @author Esteban Robles Luna
 */
public class ActionParserTestCase extends TestCase {

  private DiagramImpl diagram;

  public void setUp() throws Exception {
    super.setUp();
    this.diagram = new DiagramImpl("diagram");
    InteractionImpl i1 = new InteractionImpl("home");
    this.diagram.addInteraction(i1);
    TextField tf = i1.createTextFieldWithId("searchTF");
    tf.setName("searchTF");
    i1.addWidget(tf);
  }

  public void testParsingExpression() throws ParsingException {
    List<Action> actions1 = this.basicParse("1+2");
    assertEquals(1, actions1.size());
    assertEquals(ExpressionAction.class, actions1.get(0).getClass());
  }
  
  public void testParsingActions() throws ParsingException {
    List<Action> actions2 = this
        .basicParse("click(home.searchTF); Number aaa := 1 + 2");
    assertEquals(2, actions2.size());
    assertEquals(ExpressionAction.class, actions2.get(0).getClass());
    assertEquals(LetVariable.class, actions2.get(1).getClass());
  }
  
  public void testParsingNull() throws ParsingException {
    assertNull(this.basicParse(null));
  }

  private List<Action> basicParse(String string) throws ParsingException {
    return ActionParser.getActions(string, diagram);
  }
}
