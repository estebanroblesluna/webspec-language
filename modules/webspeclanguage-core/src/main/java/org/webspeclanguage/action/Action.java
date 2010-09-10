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
package org.webspeclanguage.action;

/**
 * Represents an action performed to move from one
 * interaction to another
 * 
 * @author Esteban Robles Luna
 */
public interface Action {

  /**
   * Accepts an {@link ActionVisitor}
   * 
   * @param actionVisitor the {@link ActionVisitor} to accept
   * @return an object as a result of the visting process
   */
  Object accept(ActionVisitor actionVisitor);
}