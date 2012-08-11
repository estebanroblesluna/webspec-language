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
package org.webspeclanguage.userstories.visitor;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public abstract class AbstractWordExplanationVisitor extends AbstractExplanationVisitor {
  
  protected final static long LEFT_PADDING_1800 = 1800;

  private WmlFactory wmlFactory;

  public AbstractWordExplanationVisitor(WmlFactory wmlFactory, MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
    this.setWmlFactory(wmlFactory);
  }

  protected String getExplanationNextAction(Transition transition) {
    StringBuilder transitionSB;
    transitionSB = new StringBuilder();
    transitionSB.append("'");
    transitionSB.append(transition.getName());
    transitionSB.append("'");
    return transitionSB.toString();
  }
  
  protected WmlFactory getWmlFactory() {
    return wmlFactory;
  }
  
  private void setWmlFactory(WmlFactory wmlFactory) {
    this.wmlFactory = wmlFactory;
  }

}
