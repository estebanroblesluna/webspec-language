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
 * A item that can be simulated
 * 
 * @author Esteban Robles Luna
 */
public interface SimulationItem {

  /**
   * Accepts a {@link SimulationItemVisitor} as a visitor of this 
   * simulation item
   * 
   * @param visitor a visitor for the simulation item
   * @return the result of visiting
   */
  Object accept(SimulationItemVisitor visitor);
}
