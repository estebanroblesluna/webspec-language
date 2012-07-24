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

/**
 * A visitor of tests
 * 
 * @author Esteban Robles Luna
 */
public interface WebTestVisitor {

  /**
   * Visits a {@link WebTestSuite}
   * 
   * @param webTestSuite the test to visit
   * @return the result of visiting the item
   */
  Object visitWebTestSuite(WebTestSuite webTestSuite);

  /**
   * Visits a {@link SimpleWebTest}
   * 
   * @param simpleWebTest the test to visit
   * @return the result of visiting the item
   */
  Object visitSimpleWebTest(SimpleWebTest simpleWebTest);
}