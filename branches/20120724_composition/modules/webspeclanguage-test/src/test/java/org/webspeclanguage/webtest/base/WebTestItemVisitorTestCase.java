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
package org.webspeclanguage.webtest.base;

import junit.framework.TestCase;

import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertExpression;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;
import org.webspeclanguage.webtest.base.WebTestItem;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;

/**
 * @author Esteban Robles Luna
 */
public class WebTestItemVisitorTestCase extends TestCase implements
    WebTestItemVisitor {

  private WebTestItem expectedObject;
  private String expectedMethod;

  public void testVisiting() {
    this.basicVisit(new WebCreateVariableFromExpression("var",
        BooleanConstant.TRUE, ExpressionType.BOOLEAN));
    this.basicVisit(new WebExpression(BooleanConstant.TRUE));
    this.basicVisit(new WebOpenUrl("http://www.google.com"));
    this.basicVisit(new WebWaitPageToLoad());

    this.basicVisit(new WebAssertExpression(BooleanConstant.TRUE));
    this.basicVisit(new WebAssertTitle(new StringConstant(
        "http://www.google.com")));
  }

  private void basicVisit(WebTestItem item) {
    this.expectedObject = item;
    this.expectedMethod = "visit" + item.getClass().getSimpleName();
    item.accept(this);
  }

  private void doAssert(WebTestItem item, String methodName) {
    assertEquals(this.expectedObject, item);
    assertEquals(this.expectedMethod, methodName);
  }

  public Object visitWebAssertExpression(WebAssertExpression webAssertExpression) {
    this.doAssert(webAssertExpression, "visitWebAssertExpression");
    return null;
  }

  public Object visitWebAssertTitle(WebAssertTitle assertTitle) {
    this.doAssert(assertTitle, "visitWebAssertTitle");
    return null;
  }

  public Object visitWebCreateVariableFromExpression(
      WebCreateVariableFromExpression webCreateVariableFromExpression) {
    this.doAssert(webCreateVariableFromExpression,
        "visitWebCreateVariableFromExpression");
    return null;
  }

  public Object visitWebExpression(WebExpression webExpression) {
    this.doAssert(webExpression, "visitWebExpression");
    return null;
  }

  public Object visitWebOpenUrl(WebOpenUrl webOpenUrl) {
    this.doAssert(webOpenUrl, "visitWebOpenUrl");
    return null;
  }

  public Object visitWebWaitPageToLoad(WebWaitPageToLoad webWaitPageToLoad) {
    this.doAssert(webWaitPageToLoad, "visitWebWaitPageToLoad");
    return null;
  }
}
