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

import org.webspeclanguage.impl.expression.core.Expression;

/**
 * A transition represents a movement from a {@link TransitionSource}
 * to a {@link TransitionTarget}
 * 
 * @author Esteban Robles Luna
 */
public interface Transition extends PathItem, NamedObject {

  /**
   * @return the transition source
   */
  TransitionSource getSource();
  
  /**
   * @return the transition target
   */
  TransitionTarget getTarget();

  /**
   * @return the list of actions to be performed to move from source to target
   */
  List<Action> getActions();

  /**
   * @return the precondition that needs to be hold to execute the actions
   */
  Expression getPrecondition();
  
  /**
   * Sets the actions represented in actionsString
   * 
   * @param actionsString the actions as string
   */
  void setActions(String actionsString);
  
  /**
   * Sets the precondition represented in preconditionString
   * 
   * @param preconditionString the precondition as string
   */
  void setPrecondition(String preconditionString);
}
