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

import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.utils.ExpressionUtils;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestGenerator;
import org.webspeclanguage.webtest.test.WebTestGeneration;
import org.webspeclanguage.webtest.test.WebTestGenerationTestCase;

/**
 * @author Esteban Robles Luna
 */
public class SeleniumJavaWebTestGeneratorIntegrationTestCase extends WebTestGenerationTestCase {

  private SeleniumJavaWebTestGenerator testGenerator;
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.testGenerator = new SeleniumJavaWebTestGenerator();
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
    this.testGenerator.setPackageName("org.webspeclanguage.tests");
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");
    webTest.addSetUpItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi\""), ExpressionType.STRING));
    this.checkTestGeneration(webTest);
  }
  
  @WebTestGeneration
  public void testExpressions() {
    this.testGenerator.setPackageName("org.webspeclanguage.tests");
    SimpleWebTest webTest = new SimpleWebTest("HomeNavigation");

    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi\""), ExpressionType.STRING));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("12305"), ExpressionType.NUMBER));
    
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 + 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 - 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 * 2"), ExpressionType.NUMBER));
    webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 / 2"), ExpressionType.NUMBER));

    //TODO add this test case
    //webTest.addItem(new WebCreateVariableFromExpression("money", ExpressionUtils.getExpression("1 + ${money}"), ExpressionType.NUMBER));

    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("!true"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true && false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true || false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true -> false"), ExpressionType.BOOLEAN));
    webTest.addItem(new WebCreateVariableFromExpression("isQuery", ExpressionUtils.getExpression("true && false && false"), ExpressionType.BOOLEAN));

    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("\"hi \" & \"esteban\""), ExpressionType.STRING));
    webTest.addItem(new WebCreateVariableFromExpression("message", ExpressionUtils.getExpression("(\"hi \" & \"esteban\") & \" how are you\""), ExpressionType.STRING));

    this.checkTestGeneration(webTest);
  }
}
