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
package org.webspeclanguage.io.base;

import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.core.NavigationImpl;
import org.webspeclanguage.impl.core.TransitionImpl;

/**
 * A {@link NavigationImpl} parser
 * 
 * @author Esteban Robles Luna
 */
public class NavigationParser extends TransitionParser {

  @Override
  protected TransitionImpl createTransition(InteractionImpl from, InteractionImpl to) {
    return new NavigationImpl(from, to);
  }
}
