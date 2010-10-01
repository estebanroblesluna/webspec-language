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

import java.util.Collection;

/**
 * A diagram represents a set of scenarios that the
 * Web application must satisfy
 * 
 * @author Esteban Robles Luna
 */
public interface Diagram extends NamedObject {

  /**
   * Adds the interaction into the diagram
   * 
   * @param interaction the interaction to be added
   */
  void addInteraction(Interaction interaction);
  
  /**
   * Adds the generator into the diagram
   * 
   * @param generator the generator to be added
   */
  void addGenerator(Generator generator);

  /**
   * Obtains the interaction whose name is interactionName or null
   * if not found
   * 
   * @param interactionName the interaction name
   * @return the interaction or null
   */
  Interaction getInteractionNamed(String interactionName);

  /**
   * Returns the starting interaction
   * 
   * @return the starting interaction
   */
  Interaction getStartingInteraction();
  
  /**
   * Returns the {@link Generator} whose name is name or null if not found
   * 
   * @param name the name of the generator
   * @return the generator or null
   */
  Generator getGeneratorNamed(String name);
  
  /**
   * @return the collection of {@link Generator}
   */
  Collection<Generator> getGenerators();
}