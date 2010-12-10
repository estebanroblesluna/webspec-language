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
package org.webspeclanguage.simulation;

/**
 * A visitor that visits simulation items
 * 
 * @author Esteban Robles Luna
 */
public interface SimulationItemVisitor {

  /**
   * Visits a {@link ExecuteAction}
   * 
   * @param executeAction the item to visit
   * @return the result of visiting the item
   */
  Object visitExecuteAction(ExecuteAction executeAction);

  /**
   * Visits a {@link OpenMockup}
   * 
   * @param openMockup the item to visit
   * @return the result of visiting the item
   */
  Object visitOpenMockup(OpenMockup openMockup);

  /**
   * Visits a {@link ShowGeneralDescription}
   * 
   * @param showDescription the item to visit
   * @return the result of visiting the item
   */
  Object visitShowGeneralDescription(ShowGeneralDescription showDescription);

  /**
   * Visits a {@link ShowDescriptionAt}
   * 
   * @param showDescriptionAt the item to visit
   * @return the result of visiting the item
   */
  Object visitShowDescriptionAt(ShowDescriptionAt showDescriptionAt);
}
