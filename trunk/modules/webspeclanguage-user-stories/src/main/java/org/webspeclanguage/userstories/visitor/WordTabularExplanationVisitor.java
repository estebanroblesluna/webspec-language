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

import org.docx4j.wml.Tc;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public class WordTabularExplanationVisitor extends AbstractWordExplanationVisitor {

  private int cellWidthTwips;

  public WordTabularExplanationVisitor(WmlFactory wmlFactory, MessageSource messageSource, Locale locale, int cellWidthTwips) {
    super(wmlFactory, messageSource, locale);
    this.setCellWidthTwips(cellWidthTwips);
  }

  public Object visitInteraction(Interaction interaction) {
    String explanationHeader = this.getExplanationHeader(interaction);
    Tc tc = this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
    tc.getContent().add(this.getWmlFactory().createP(explanationHeader));
    String actionString;
    for (Transition transition : interaction.getForwardTransitions()) {
      actionString = this.getExplanationNextAction(transition);
      tc.getContent().add(getWmlFactory().createNumberingP(2, actionString));
    }
    return tc;
  }

  public Object visitNavigation(Navigation navigation) {
    String explanationHeader = this.getExplanationHeader(navigation);
    String actionString;
    Tc tc = this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
    tc.getContent().add(this.getWmlFactory().createP(explanationHeader));
    for (Action action : navigation.getActions()) {
      actionString = (String) action.accept(this.getActionVisitor());
      tc.getContent().add(getWmlFactory().createNumberingP(2, actionString));
    }
    return tc;
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return getWmlFactory().createP();
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return getWmlFactory().createP();
  }

  private int getCellWidthTwips() {
    return cellWidthTwips;
  }

  private void setCellWidthTwips(int cellWidthTwips) {
    this.cellWidthTwips = cellWidthTwips;
  }
}
