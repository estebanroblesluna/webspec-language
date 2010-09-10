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

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.generator.UniformNumberGenerator;

/**
 * @author Esteban Robles Luna
 */
public class UniformNumberGeneratorTestCase extends TestCase {

  public void testGenerate() {
    UniformNumberGenerator generator = new UniformNumberGenerator(0, 1);
    NumberConstant constant = (NumberConstant) generator.generate();
    assertEquals(NumberConstant.class, constant.getClass());
    assertTrue(constant.getConstant().compareTo(new BigDecimal(0)) >= 0
        && constant.getConstant().compareTo(new BigDecimal(1)) <= 0);
  }
}
