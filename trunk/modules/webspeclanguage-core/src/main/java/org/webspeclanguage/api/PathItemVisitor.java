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
package org.webspeclanguage.api;

/**
 * A visitor for visiting path items
 * 
 * @author Esteban Robles Luna
 */
public interface PathItemVisitor {

  /**
   * Visits a {@link Navigation}
   * 
   * @param navigation the item to visit
   * @return the result of visiting the item
   */
  Object visitNavigation(Navigation navigation);

  /**
   * Visits a {@link Interaction}
   * 
   * @param interaction the item to visit
   * @return the result of visiting the item
   */
  Object visitInteraction(Interaction interaction);

  /**
   * Visits a {@link RichBehavior}
   * 
   * @param richBehavior the item to visit
   * @return the result of visiting the item
   */
  Object visitRichBehavior(RichBehavior richBehavior);

  /**
   * Visits an {@link Operation}
   * 
   * @param operation the item to visit
   * @return the result of visiting the item
   */
  Object visitOperation(Operation operation);
}
