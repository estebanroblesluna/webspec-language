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
package org.webspeclanguage.impl.action;

import org.webspeclanguage.api.Action;

/**
 * An interface for visiting {@link Action} 
 * 
 * @author Esteban Robles Luna
 */
public interface ActionVisitor {

  /**
   * Visits a {@link LetVariable}
   * 
   * @param letVariable the action to visit
   * @return the result of visiting the action
   */
  Object visitLetVariable(LetVariable letVariable);

  /**
   * Visits a {@link ExpressionAction}
   * 
   * @param expressionAction the action to visit
   * @return the result of visiting the action
   */
  Object visitExpressionAction(ExpressionAction expressionAction);
}
