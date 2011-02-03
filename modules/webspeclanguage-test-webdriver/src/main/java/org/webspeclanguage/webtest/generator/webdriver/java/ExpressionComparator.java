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

/**
 * Expression comparator class, used to process the string received from
 * elements. It also implements the classes needed to generate the final
 * locator.
 * 
 * @author Gonzalo G. Testa
 * 
 */
public class ExpressionComparator {

  String locator;

  ElementExpression how;

  public ExpressionComparator(String expr) {
    if (expr.contains("//")) {
      how = new ExpressionXpath(expr);
    } else {
      how = new ExpressionHow(expr);
    }
  }

  public String locators() {
    return "By." + how.element() + "(\"" + how.locator() + "\")";
  }
}

abstract class ElementExpression {

  String how;
  Locator locator;
  String expression;

  public ElementExpression(String expr) {
    how = new String();
    locator = new Locator(expr);
    expression = new String(expr);
  }

  abstract String locator();

  public String element() {
    return this.how;
  }

}

final class ExpressionXpath extends ElementExpression {

  public ExpressionXpath(String expr) {
    super(expr);
    this.how = new String("xpath");
  }

  public String locator() {
    return this.locator.locatorWithXpath();
  }

}

final class ExpressionHow extends ElementExpression {

  public ExpressionHow(String expr) {
    super(expr);
    this.how = new String(expr.substring(0, expr.indexOf("=")));

  }

  String locator() {
    return this.locator.locatorWithHow();
  }
}

class Locator {

  String expression;

  Locator(String expr) {
    expression = new String(expr);
  }

  public String locatorWithXpath() {
    if (expression.contains("[@") || (expression.contains("//") && !expression.contains("=")))
      return expression;

    return "//*[@" + expression.substring(0, expression.indexOf("=")) + "='" + expression.substring(expression.indexOf("=") + 1, expression.length()) + "']";
  }

  public String locatorWithHow() {
    if (expression.contains("]"))
      return expression.substring(expression.indexOf("=") + 2, expression.indexOf("]") - 1);
    else
      return expression.substring(expression.indexOf("=") + 1);
  }
}
