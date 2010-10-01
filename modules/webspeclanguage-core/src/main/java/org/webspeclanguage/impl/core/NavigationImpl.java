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

import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.TransitionSource;
import org.webspeclanguage.api.TransitionTarget;

/**
 * A navigation represents a movement in the navigation space of the application
 * perceive by the user in its browsing history 
 * 
 * @author Esteban Robles Luna
 */
public class NavigationImpl extends TransitionImpl implements Navigation {

  public NavigationImpl(TransitionSource source, TransitionTarget target) {
    super(source, target);
  }

  public String toString() {
    return this.getSource().getName() + "->" + this.getTarget().getName();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitNavigation(this);
  }
}
