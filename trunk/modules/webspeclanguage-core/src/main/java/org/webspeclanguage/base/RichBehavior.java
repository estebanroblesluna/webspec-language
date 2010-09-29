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
 * A rich behavior represents a transition that happens locally
 * on the browser and does not alter the user's browsing history.
 * 
 * @author Esteban Robles Luna
 */
public class RichBehavior extends Transition {

  public RichBehavior(Interaction from, Interaction to) {
    super(from, to);
  }

  public String toString() {
    return this.getFrom().getName() + "*>" + this.getTo().getName();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitRichBehavior(this);
  }
}
