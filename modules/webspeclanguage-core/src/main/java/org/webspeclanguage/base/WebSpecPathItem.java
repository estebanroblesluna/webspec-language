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

/**
 * A path item is an element that could be included in a path
 * 
 * @author Esteban Robles Luna
 */
public interface WebSpecPathItem {

  /**
   * Accepts a {@link WebSpecPathItemVisitor} visitor
   * 
   * @param pathItemVisitor the visitor
   * @return the result of visiting the item
   */
  Object accept(WebSpecPathItemVisitor pathItemVisitor);
}
