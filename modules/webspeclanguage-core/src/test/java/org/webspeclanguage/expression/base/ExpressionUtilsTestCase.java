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
package org.webspeclanguage.expression.base;

import junit.framework.TestCase;

import org.webspeclanguage.expression.utils.ExpressionUtils;

/**
 * @author Esteban Robles Luna
 */
public class ExpressionUtilsTestCase extends TestCase {

  public void testHasVariables() {
    assertTrue(ExpressionUtils.hasVariables("${aaa}"));
    assertTrue(ExpressionUtils.hasVariables("${AFASasfasf}"));
    assertFalse(ExpressionUtils.hasVariables("${}"));
    assertTrue(ExpressionUtils.hasVariables("//div[@id='${aa}/hola'"));
    assertTrue(ExpressionUtils
        .hasVariables("//div[@id='${aa}/hola${aa}'/a${productIndex}"));
    assertTrue(ExpressionUtils
        .hasVariables("//div[@id='hola'/a${productIndex}"));
    assertFalse(ExpressionUtils.hasVariables("//div[@id='hola'/a"));
  }

  public void testGetVariables() {
    assertEquals("${aaa}", ExpressionUtils.firstVariable("${aaa}"));
    assertEquals("${AFASasfasf}", ExpressionUtils
        .firstVariable("${AFASasfasf}"));
    assertEquals("${aa}", ExpressionUtils
        .firstVariable("//div[@id='${aa}/hola'"));
    assertEquals("${aa}", ExpressionUtils.variables(
        "//div[@id='${aa}/hola${bb}'/a${productIndex}").get(0));
    assertEquals("${bb}", ExpressionUtils.variables(
        "//div[@id='${aa}/hola${bb}'/a${productIndex}").get(1));
    assertEquals("${productIndex}", ExpressionUtils.variables(
        "//div[@id='${aa}/hola${bb}'/a${productIndex}").get(2));
    assertEquals("${productIndex}", ExpressionUtils
        .firstVariable("//div[@id='hola'/a${productIndex}"));
  }

  public void testGetStrings() {
    assertArrays(ExpressionUtils.strings("\"\"").toArray(), "");
    assertArrays(ExpressionUtils.strings("\"aaa\"").toArray(), "aaa");
    assertArrays(ExpressionUtils.strings("\"aaa\",\"b\",\"b 23t5 2, 35\"")
        .toArray(), "aaa", "b", "b 23t5 2, 35");
  }

  public void testGetArrays() {
    assertArrays(ExpressionUtils.arrayStrings("[1,2], [3,4]").toArray(),
        "[1,2]", "[3,4]");
  }

  public static void assertArrays(Object[] objs, Object... objs2) {
    for (int i = 0; i < objs2.length; i++) {
      Object object = objs2[i];
      assertEquals(objs[i], object);
    }
  }
}
