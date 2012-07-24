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
package org.webspeclanguage.webtest.generator.webdriver.java;

import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.core.NumberConstant;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.utils.ExpressionUtils;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.TextField;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestGenerator;
import org.webspeclanguage.webtest.base.WebTestSuite;
import org.webspeclanguage.webtest.test.WebTestGeneration;
import org.webspeclanguage.webtest.test.WebTestGenerationTestCase;

/**
 * Integration Test Case for WebDriver Test Geneator Class
 * 
 * @author Gonzalo G. Testa
 */
public class WebdriverJavaWebTestGeneratorIntegrationTestCase extends WebTestGenerationTestCase {

  private WebdriverJavaWebTestGenerator testGenerator;

  private DiagramImpl diagram;

  private Button searchButton;

  private InteractionImpl homeInteraction;

  private TextField searchField;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.testGenerator = new WebdriverJavaWebTestGenerator(new WebdriverFirefox());
    this.testGenerator.setStopPolicy(new ThreadSleepGenerationPolicy());
    this.diagram = new DiagramImpl("the diagram");

    homeInteraction = new InteractionImpl("Home");
    this.diagram.addInteraction(homeInteraction);

    searchButton = new Button();
    searchButton.setId("searchButton");
    searchButton.setName("searchButton");
    homeInteraction.addWidget(searchButton);

    searchField = new TextField();
    searchField.setId("searchField");
    searchField.setName("searchField");
    homeInteraction.addWidget(searchField);
  }

  @Override
  protected WebTestGenerator getTestGenerator() {
    return this.testGenerator;
  }

  @WebTestGeneration
  public void testEmpty() {
    this.testGenerator.setPackageName("org.webspeclanguage.tests");
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");
    this.checkTestGeneration(webTest);
  }

  @WebTestGeneration
  public void testSetup() {
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");
    webTest.addSetUpItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi\""), ExpressionType.STRING));
    this.checkTestGeneration(webTest);
  }

  @WebTestGeneration
  public void testExpressions() {
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");

    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi\""), ExpressionType.STRING));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("12305"), ExpressionType.NUMBER));

    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 + 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 - 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 * 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 / 2"), ExpressionType.NUMBER));

    webTest.addItem(new WebCreateVariableFromExpression("money", new AddExpression(new NumberConstant("1"), new VariableValue("money")), ExpressionType.NUMBER));

    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("!true"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true && false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true || false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true -> false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true && false && false"), ExpressionType.BOOLEAN));

    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi \" # \"esteban\""), ExpressionType.STRING));
    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("(\"hi \" # \"esteban\") # \" how are you\""), ExpressionType.STRING));

    this.checkTestGeneration(webTest);
  }

  @WebTestGeneration
  public void testInteractive() {
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");

    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("Home.searchField", this.diagram), ExpressionType.STRING));
    webTest.addItem(new WebExpression(ExpressionUtils.getExpression("click(Home.searchButton)", this.diagram)));
    this.checkTestGeneration(webTest);
  }

  @WebTestGeneration
  public void testWebTestSuiteGeneration() {
    WebTestSuite webTest = new WebTestSuite("HomeNavigation");

    SimpleWebTest test1 = new SimpleWebTest("HomeNavigation");
    test1.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("Home.searchField", this.diagram), ExpressionType.STRING));
    test1.addItem(new WebExpression(ExpressionUtils.getExpression("click(Home.searchButton)", this.diagram)));
    webTest.addTest(test1);

    SimpleWebTest test2 = new SimpleWebTest("HomeNavigation2");
    test2.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi\""), ExpressionType.STRING));
    webTest.addTest(test2);

    webTest.addSetUpItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1"), ExpressionType.NUMBER));

    this.checkTestGeneration(webTest);
  }

  @WebTestGeneration
  public void testWebTestIOExampleGeneration() {
    this.checkTestGenerationFromResource();
  }
}
