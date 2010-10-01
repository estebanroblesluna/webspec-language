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
 * A transition target contains a list of backward transitions
 * 
 * @author Esteban Robles Luna
 */
public interface TransitionTarget extends NamedObject, PathItem {

  /**
   * @return the list of backward transitions
   */
  List<Transition> getBackwardTransitions();
  
  /**
   * Add the transition as a backward transition
   * 
   * @param transition the transition to be added
   */
  void addBackwardTransition(Transition transition);
}
