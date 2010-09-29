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

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class NavigationTestCase extends TestCase {

  public void testCreation() {
    Interaction i1 = new Interaction("i1");
    Interaction i2 = new Interaction("i2");
    Navigation nav = i1.navigateTo(i2);
    assertEquals("i1->i2", nav.toString());
  }
}
