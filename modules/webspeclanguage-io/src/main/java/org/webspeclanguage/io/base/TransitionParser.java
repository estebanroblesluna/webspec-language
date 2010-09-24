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
import org.webspeclanguage.base.WebSpecTransition;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * An abstract class for {@link WebSpecTransition} parsers
 * 
 * @author Esteban Robles Luna
 */
public abstract class TransitionParser extends AbstractElementParser {

  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    String fromInteraction = attributes.getValue("from");
    String toInteraction = attributes.getValue("to");
    String actions = attributes.getValue("actions");
    String precondition = attributes.getValue("precondition");

    WebSpecInteraction from = (WebSpecInteraction) context.get(fromInteraction + "-Interaction");
    WebSpecInteraction to = (WebSpecInteraction) context.get(toInteraction + "-Interaction");

    WebSpecTransition transition = this.createTransition(from, to);
    transition.setActions(actions);
    transition.setPrecondition(precondition);
    
    this.setResult(transition);
  }

  protected abstract WebSpecTransition createTransition(WebSpecInteraction from, WebSpecInteraction to);
}
