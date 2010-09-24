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

import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecRichBehavior;
import org.webspeclanguage.base.WebSpecTransition;

/**
 * A {@link WebSpecRichBehavior} parser
 * 
 * @author Esteban Robles Luna
 */
public class RichBehaviorParser extends TransitionParser {

  @Override
  protected WebSpecTransition createTransition(WebSpecInteraction from, WebSpecInteraction to) {
    return new WebSpecRichBehavior(from, to);
  }
}
