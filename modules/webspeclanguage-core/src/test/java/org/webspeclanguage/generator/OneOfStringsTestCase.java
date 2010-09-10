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
package org.webspeclanguage.generator;

import junit.framework.TestCase;

import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.generator.OneOfStrings;

/**
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class OneOfStringsTestCase extends TestCase {

  public void testGenerate() {
    OneOfStrings generator = new OneOfStrings("a", "b");
    ConstantExpression constant = generator.generate();
    assertEquals(StringConstant.class, constant.getClass());
    assertTrue(constant.getConstant().equals("a")
        || constant.getConstant().equals("b"));
  }
}
