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
package org.webspeclanguage.webtest.action.generator;

import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.utils.ExpressionUtils;
import org.webspeclanguage.webtest.action.WebClick;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestSuite;

/**
 * A sample test generator
 * 
 * @author Esteban Robles Luna
 */
public class WebTestGenerator {

  public static SimpleWebTest generateSimpleTest(String name) {
    SimpleWebTest test = new SimpleWebTest(name);
    
    //setup items
    test.addSetUpItem(new WebOpenUrl("http://www.google.com"));
    
    //items
    test.addItem(new WebCreateVariableFromExpression("count", ExpressionUtils.getExpression("1 + 20 * 30"), ExpressionType.NUMBER));
    test.addItem(new WebClick(ExpressionUtils.getExpression("${count} + 'button'")));
    test.addItem(new WebWaitPageToLoad());
    test.addItem(new WebAssertTitle(ExpressionUtils.getExpression("'Search results...'")));
    
    return test;
  }

  public static WebTestSuite generateWebTestSuite() {
    WebTestSuite suite = new WebTestSuite("suite");

    //setup items
    suite.addSetUpItem(new WebCreateVariableFromExpression("count", ExpressionUtils.getExpression("1 + 20 * 30"), ExpressionType.NUMBER));

    //add tests
    suite.addTest(generateSimpleTest("simple1"));
    suite.addTest(generateSimpleTest("simple2"));
    suite.addTest(generateSimpleTest("simple3"));

    return suite;
  }

}
