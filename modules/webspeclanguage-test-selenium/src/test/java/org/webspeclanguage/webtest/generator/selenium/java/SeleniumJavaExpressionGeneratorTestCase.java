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
package org.webspeclanguage.webtest.generator.selenium.java;

import junit.framework.TestCase;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.parser.ExpressionParser;
import org.webspeclanguage.widget.Button;

/**
 * @author Esteban Robles Luna
 */
public class SeleniumJavaExpressionGeneratorTestCase extends TestCase {

  private SeleniumJavaExpressionGenerator expressionGenerator;
  private SeleniumJavaWebTestGenerator webTestGenerator;
  private ExpressionParser parser;
  private WebSpecDiagram diagram;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    this.webTestGenerator = new SeleniumJavaWebTestGenerator();
    this.expressionGenerator = new SeleniumJavaExpressionGenerator(this.webTestGenerator);

    this.parser = new ExpressionParser();
    this.diagram = new WebSpecDiagram("a");

    WebSpecInteraction interaction = new WebSpecInteraction("Home", this.diagram);
    this.diagram.addInteraction(interaction);
    Button button = interaction.createButtonWithLocation("loc");
    button.setName("register");
    interaction.addWidget(button);
  }

  public void testExpressions() {
    this.basicTestExpression("new BigDecimal(\"1\")", "1");
    this.basicTestExpression("true", "true");
    this.basicTestExpression("\"aaa\"", "\"aaa\"");

    this.basicTestExpression("new BigDecimal(\"1\").add(new BigDecimal(\"2\"))", "1 + 2");
    this.basicTestExpression("new BigDecimal(\"1\").subtract(new BigDecimal(\"2\"))", "1 - 2");
    this.basicTestExpression("new BigDecimal(\"1\").multiply(new BigDecimal(\"2\"))", "1 * 2");
    this.basicTestExpression("new BigDecimal(\"1\").divide(new BigDecimal(\"2\"))", "1 / 2");

    this.basicTestExpression("true && false", "true && false");
    this.basicTestExpression("true || false", "true || false");
    this.basicTestExpression("!true", "!true");

    this.basicTestExpression("\"a\" + \"b\"", "\"a\" & \"b\"");

    this.basicTestExpression("new BigDecimal(\"1\").equals(new BigDecimal(\"2\"))", "1 = 2");
    this.basicTestExpression("!(new BigDecimal(\"1\").equals(new BigDecimal(\"2\")))", "1 != 2");
    this.basicTestExpression("new BigDecimal(\"1\").compareTo(new BigDecimal(\"2\")) >= 0", "1 >= 2");
    this.basicTestExpression("new BigDecimal(\"1\").compareTo(new BigDecimal(\"2\")) > 0", "1 > 2");
    this.basicTestExpression("new BigDecimal(\"1\").compareTo(new BigDecimal(\"2\")) < 0", "1 < 2");
    this.basicTestExpression("new BigDecimal(\"1\").compareTo(new BigDecimal(\"2\")) <= 0", "1 <= 2");

    this.basicTestExpression("selenium.click(\"loc\")", "click(Home.register)");

    this.basicTestExpression("selenium.getText(\"loc\")", "Home.register");
    this.basicTestExpression("selenium.getAttribute(\"loc@value\")", "Home.register.value");
  }

  private void basicTestExpression(String javaCode, String expressionString) {
    Expression expression = this.parser.parseFor(expressionString, this.diagram);
    assertEquals(javaCode, this.expressionGenerator.generate(expression));
  }
}