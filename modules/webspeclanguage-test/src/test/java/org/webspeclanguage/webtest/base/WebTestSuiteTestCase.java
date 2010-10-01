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

import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestSuite;

/**
 * @author Esteban Robles Luna
 */
public class WebTestSuiteTestCase extends TestCase {

  private WebTestSuite testSuite;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.testSuite = new WebTestSuite("suite");
  }

  public void testAddSetUpItem() {
    assertEquals(0, this.testSuite.getSetUpItems().size());
    this.testSuite.addSetUpItem(new WebExpression(
        new FunctionCallExpression("click")));
    assertEquals(1, this.testSuite.getSetUpItems().size());
  }

  public void testAddTest() {
    assertFalse(this.testSuite.iterator().hasNext());
    assertEquals(0, this.testSuite.getSize());
    SimpleWebTest test = new SimpleWebTest("simple");
    this.testSuite.addTest(test);
    assertTrue(this.testSuite.iterator().hasNext());
    assertEquals(test, this.testSuite.getTest(0));
    assertEquals(1, this.testSuite.getSize());
  }

}
