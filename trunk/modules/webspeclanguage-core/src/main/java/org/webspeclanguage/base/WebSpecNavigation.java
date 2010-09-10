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
 * A navigation represents a movement in the navigation space of the application
 * perceive by the user in its browsing history 
 * 
 * @author Esteban Robles Luna
 */
public class WebSpecNavigation extends WebSpecTransition {

  public WebSpecNavigation(WebSpecInteraction from, WebSpecInteraction to) {
    super(from, to);
  }

  public String toString() {
    return this.getFrom().getName() + "->" + this.getTo().getName();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(WebSpecPathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitWebSpecNavigation(this);
  }
}
