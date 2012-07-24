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
package org.webspeclanguage.impl.core;

import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.TransitionSource;
import org.webspeclanguage.api.TransitionTarget;

/**
 * A rich behavior represents a transition that happens locally
 * on the browser and does not alter the user's browsing history.
 * 
 * @author Esteban Robles Luna
 */
public class RichBehaviorImpl extends TransitionImpl implements RichBehavior {

  public RichBehaviorImpl(TransitionSource source, TransitionTarget target) {
    super(source, target);
  }

  public String toString() {
    return this.getSource().getName() + "*>" + this.getTarget().getName();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitRichBehavior(this);
  }
}
