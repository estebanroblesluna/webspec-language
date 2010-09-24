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

import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.webspeclanguage.action.Action;
import org.webspeclanguage.action.LetVariable;
import org.webspeclanguage.base.DuplicatedGeneratorException;
import org.webspeclanguage.base.ExistingInteractionException;
import org.webspeclanguage.base.InvalidStartingInteractionException;
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecNavigation;
import org.webspeclanguage.base.WebSpecPath;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.generator.Generator;
import org.webspeclanguage.generator.OneOfStrings;
import org.webspeclanguage.widget.Button;

/**
 * @author Esteban Robles Luna
 */
public class WebSpecDiagramTestCase extends TestCase {

  private WebSpecDiagram diagram;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.diagram = new WebSpecDiagram("diagram");
  }

  public void testActionSetup() {
    assertEquals(0, this.diagram.getActionsSetup().size());

    Action action = new LetVariable("varName", new StringConstant("aaa"),
        ExpressionType.STRING);
    this.diagram.addActionSetup(action);
    assertEquals(1, this.diagram.getActionsSetup().size());
    assertEquals(action, this.diagram.getActionsSetup().get(0));
  }

  public void testAddGenerator() {
    assertTrue(this.diagram.getGeneratorsNames().isEmpty());
    Generator generator = new OneOfStrings("aaa", "aaa", "aaab");
    Generator generator2 = new OneOfStrings("aaa", "aaddda", "aaab");
    this.diagram.addGenerator(generator);
    try {
      this.diagram.addGenerator(generator2);
      fail("Exception should be thrown");
    } catch (DuplicatedGeneratorException e) {
      assertEquals("aaa", e.getName());
    }

    assertEquals(generator, this.diagram.getGeneratorNamed("aaa"));
    assertEquals(1, this.diagram.getGeneratorsNames().size());
    assertEquals("aaa", this.diagram.getGeneratorsNames().toArray()[0]);
  }

  public void testAddInteraction() {
    WebSpecInteraction interaction1 = new WebSpecInteraction("i1");
    WebSpecInteraction interaction2 = new WebSpecInteraction("i1");
    this.diagram.addInteraction(interaction1);
    try {
      this.diagram.addInteraction(interaction2);
      fail("Exception should be thrown");
    } catch (ExistingInteractionException e) {
      assertEquals("i1", e.getName());
    }
  }

  @SuppressWarnings("unchecked")
  public void testGetUndeclaredVariables() {
    WebSpecInteraction interaction1 = new WebSpecInteraction("i1");
    WebSpecInteraction interaction2 = new WebSpecInteraction("i2");
    WebSpecInteraction interaction3 = new WebSpecInteraction("i3");

    this.diagram.addInteraction(interaction1);
    this.diagram.addInteraction(interaction2);
    this.diagram.addInteraction(interaction3);

    this.diagram.setActionsSetup("Boolean cond := true");

    interaction1.setLocation("http://www.google.com");
    this.diagram.setStartingInteraction(interaction1);

    WebSpecNavigation navigation1 = interaction1.navigateTo(interaction2);
    interaction1.navigateTo(interaction3);

    navigation1.setActions("typeK(1,2,3); Number username := 3");
    navigation1.setPrecondition("${cond}");

    interaction2.setInvariant("${username} = 1");
    interaction3.setInvariant("${username} = 2");

    Map<WebSpecPath, Set<String>> undeclaredVariables = this.diagram
        .getUndeclaredVariables();
    Set<String> variables = (Set<String>) undeclaredVariables.values()
        .toArray()[0];
    assertEquals(1, undeclaredVariables.size());
    assertEquals("username", variables.toArray()[0]);
  }

  public void testSetStartingInteraction() {
    WebSpecInteraction interaction1 = new WebSpecInteraction("i1");

    this.diagram.addInteraction(interaction1);

    try {
      this.diagram.setStartingInteraction(interaction1);
      fail("Exception should be thrown");
    } catch (InvalidStartingInteractionException e) {
      assertEquals(interaction1, e.getStartingInteraction());
    }
  }

  public void testGetWidget() {
    WebSpecInteraction interaction1 = new WebSpecInteraction("i1");
    Button button = new Button();
    button.setName("aaa");
    interaction1.addWidget(button);
    this.diagram.addInteraction(interaction1);

    assertNotNull(this.diagram.getWidget("i1", "aaa"));
    assertNull(this.diagram.getWidget("i1", "bbb"));
  }

}
