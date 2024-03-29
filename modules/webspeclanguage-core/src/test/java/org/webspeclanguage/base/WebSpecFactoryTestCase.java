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
package org.webspeclanguage.base;

import org.webspeclanguage.impl.core.WebSpecFactory;

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class WebSpecFactoryTestCase extends TestCase {

  public void testGenerationAmazon1() {
    assertNotNull(WebSpecFactory.getAmazonExample());
  }

  public void testGenerationAmazon2() {
    assertNotNull(WebSpecFactory.getAmazonExample2());
  }

  public void testGenerationCycle() {
    assertNotNull(WebSpecFactory.getCycleExample());
  }

  public void testGenerationDiagram() {
    assertNotNull(WebSpecFactory.getWebSpecDiagram());
  }
}
