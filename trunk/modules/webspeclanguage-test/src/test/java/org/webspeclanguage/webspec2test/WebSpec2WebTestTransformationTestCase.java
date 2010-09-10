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
package org.webspeclanguage.webspec2test;

import junit.framework.TestCase;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecNavigation;
import org.webspeclanguage.base.WebSpecRichBehavior;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.utils.ExpressionUtils;
import org.webspeclanguage.generator.OneOfStrings;
import org.webspeclanguage.webspec2test.UnsatisfiedInvariantException;
import org.webspeclanguage.webspec2test.UnsatisfiedPreconditionException;
import org.webspeclanguage.webspec2test.TestGenerationResult;
import org.webspeclanguage.webspec2test.WebSpec2WebTestTransformation;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertExpression;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestSuite;
import org.webspeclanguage.widget.Label;

/**
 * @author Esteban Robles Luna
 */
public class WebSpec2WebTestTransformationTestCase extends TestCase {

  private WebSpec2WebTestTransformation transformation;
  private WebSpecDiagram diagram;
  private SimpleWebTest test;
  private WebSpecInteraction starting;

  public void setUp() throws Exception {
    super.setUp();

    this.transformation = new WebSpec2WebTestTransformation();

    this.diagram = new WebSpecDiagram("d");
    this.diagram.addGenerator("validUsernames", new OneOfStrings("carlos2"));
    this.diagram.addGenerator("invalidUsernames", new OneOfStrings("carlos"));

    this.starting = new WebSpecInteraction("start", this.diagram);
    this.starting.setTitle("\"The title\"");
    this.starting.setLocation("http://www.google.com");
    this.diagram.setStartingInteraction(this.starting);
    this.diagram.addInteraction(this.starting);

    Label l = this.starting.createLabelWithLocation("id=a");
    l.setName("label");
    this.starting.addWidget(l);

    this.test = new SimpleWebTest("name");
  }

  public void testGenerateFirstInteraction() {
    this.starting.setInvariant("start.label = \"Home\"");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(this.starting, this.test);

    assertEquals(1, this.test.getSetUpItems().size());
    assertEquals(WebOpenUrl.class, this.test.getSetUpItems().get(0).getClass());
    assertEquals("http://www.google.com", ((WebOpenUrl) this.test
        .getSetUpItems().get(0)).getUrl());

    assertEquals(2, this.test.getLength());
    assertEquals(WebAssertTitle.class, this.test.getItem(0).getClass());
    assertEquals("The title", ((StringConstant) ((WebAssertTitle) this.test
        .getItem(0)).getTitle()).getConstant());
    assertEquals(WebAssertExpression.class, this.test.getItem(1).getClass());
    assertEquals(this.starting.getInvariant(), ((WebAssertExpression) this.test
        .getItem(1)).getExpression());
  }

  public void testGenerateInsatisfiedInvariant() {
    this.starting.setInvariant("true -> false");

    this.transformation.setCurrentDiagram(this.diagram);

    try {
      this.transformation.generateItemsFor(this.starting, this.test);
      fail("Should have thrown an exception");
    } catch (UnsatisfiedInvariantException e) {
      assertEquals(e.getInteraction(), this.starting);
    }
  }

  public void testGenerateInteractionWithTitleAndInvariant() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    interaction.setTitle("\"The title\"");
    interaction.setLocation("http://www.google.com");
    this.diagram.addInteraction(interaction);

    Label l = interaction.createLabelWithLocation("id=a");
    l.setName("label");
    interaction.addWidget(l);

    interaction.setInvariant("otherInteraction.label = \"Home\"");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(interaction, this.test);

    assertEquals(0, this.test.getSetUpItems().size());

    assertEquals(2, this.test.getLength());
    assertEquals(WebAssertTitle.class, this.test.getItem(0).getClass());
    assertEquals("The title", ((StringConstant) ((WebAssertTitle) this.test
        .getItem(0)).getTitle()).getConstant());
    assertEquals(WebAssertExpression.class, this.test.getItem(1).getClass());
    assertEquals(interaction.getInvariant(), ((WebAssertExpression) this.test
        .getItem(1)).getExpression());
  }

  public void testGenerateInteractionWithoutTitleAndInvariant() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    Label l = interaction.createLabelWithLocation("id=a");
    l.setName("label");
    interaction.addWidget(l);

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(interaction, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(0, this.test.getLength());
  }

  public void testGenerateNavigation() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecNavigation navigation = starting.navigateTo(interaction);

    navigation.setActions("click(start.label)");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(navigation, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(2, this.test.getLength());

    assertEquals(WebExpression.class, this.test.getItem(0).getClass());
    assertEquals(WebWaitPageToLoad.class, this.test.getItem(1).getClass());
  }

  public void testGenerateRichBehavior() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);

    richBehavior.setActions("click(start.label)");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(richBehavior, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(1, this.test.getLength());

    assertEquals(WebExpression.class, this.test.getItem(0).getClass());
    assertEquals(ExpressionUtils.getExpression("click(start.label)", diagram),
        ((WebExpression) this.test.getItem(0)).getExpression());
  }

  public void testGenerateConstantTransition() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);

    richBehavior.setActions("Number var := 1");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(richBehavior, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(0, this.test.getLength());
  }

  public void testGenerateConstantThenDynamicTransition() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);

    richBehavior.setActions("String var := \"a\";var := start.label");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(richBehavior, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(1, this.test.getLength());

    assertEquals(WebCreateVariableFromExpression.class, this.test.getItem(0)
        .getClass());
    assertEquals("var",
        ((WebCreateVariableFromExpression) this.test.getItem(0))
            .getVariableName());
    assertEquals(ExpressionUtils.getExpression("start.label", diagram),
        ((WebCreateVariableFromExpression) this.test.getItem(0))
            .getExpression());

  }

  public void testGenerateInsatisfiedRichBehavior() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);

    richBehavior.setActions("click(start.label)");
    richBehavior.setPrecondition("false");

    this.transformation.setCurrentDiagram(this.diagram);
    try {
      this.transformation.generateItemsFor(richBehavior, this.test);
      fail("Should have thrown an exception");
    } catch (UnsatisfiedPreconditionException e) {

    }
  }

  public void testGenerateTransitionWithPrecondition() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);

    richBehavior.setActions("click(start.label)");
    richBehavior.setPrecondition("start.label = \"a\"");

    this.transformation.setCurrentDiagram(this.diagram);
    this.transformation.generateItemsFor(richBehavior, this.test);

    assertEquals(0, this.test.getSetUpItems().size());
    assertEquals(2, this.test.getLength());

    assertEquals(WebAssertExpression.class, this.test.getItem(0).getClass());
  }

  public void testTransform() {
    WebSpecInteraction interaction = new WebSpecInteraction("otherInteraction",
        this.diagram);
    this.diagram.addInteraction(interaction);

    WebSpecRichBehavior richBehavior = starting.richBehaviorTo(interaction);
    richBehavior.setActions("click(start.label)");

    WebSpecInteraction interaction2 = new WebSpecInteraction(
        "otherInteraction2", this.diagram);
    this.diagram.addInteraction(interaction2);

    WebSpecNavigation navigation = starting.navigateTo(interaction2);
    navigation.setActions("click(start.label)");

    TestGenerationResult result = this.transformation.transform(this.diagram);
    WebTestSuite suite = result.getTestSuite();

    assertEquals(2, result.getSize());
    assertEquals(2, result.getTestSuite().getSize());
    assertEquals(result.getTest(0), suite.getTest(0));
    assertEquals(result.getTest(1), suite.getTest(1));
  }
}
