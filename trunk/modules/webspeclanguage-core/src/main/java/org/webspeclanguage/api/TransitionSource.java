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

import java.util.List;


/**
 * A transition source contains a list of forward transitions
 * 
 * @author Esteban Robles Luna
 */
public interface TransitionSource extends NamedObject, PathItem {

  /**
   * @return the list of forward transitions
   */
  List<Transition> getForwardTransitions();
  
  /**
   * Add the transition as a forward transition
   * 
   * @param transition the transition to be added
   */
  void addForwardTransition(Transition transition);

  /**
   * Returns the transitions whose source is this and whose target is target
   * 
   * @param target the transition target
   * @return the list of transitions
   */
  <T extends Transition> List<T> getForwardTransitionsTo(TransitionTarget target, Class<T> theClass);
}
