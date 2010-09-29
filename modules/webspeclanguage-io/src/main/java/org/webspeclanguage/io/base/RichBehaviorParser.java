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

import org.webspeclanguage.base.Interaction;
import org.webspeclanguage.base.RichBehavior;
import org.webspeclanguage.base.Transition;

/**
 * A {@link RichBehavior} parser
 * 
 * @author Esteban Robles Luna
 */
public class RichBehaviorParser extends TransitionParser {

  @Override
  protected Transition createTransition(Interaction from, Interaction to) {
    return new RichBehavior(from, to);
  }
}
