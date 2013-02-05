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

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
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
public class WordEnumerationExplanationVisitor extends AbstractWordExplanationVisitor {

  private WordprocessingMLPackage wordprocessingMLPackage;
  private long subsectionFontSize;

  public WordEnumerationExplanationVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, MessageSource messageSource, Locale locale,
          long subsectionFontSize) {
    super(wmlFactory, messageSource, locale);
    this.setWordprocessingMLPackage(wordprocessingMLPackage);
    this.setSubsectionFontSize(subsectionFontSize);
  }

  public Object visitInteraction(Interaction interaction) {
    String interactionDiagramExplanationText = this.getMessage("userstory.wen.section.interaction.diagram.explanation", new String[] { interaction.getName() });
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().createNumberingP(3, 2, interactionDiagramExplanationText, true, getSubsectionFontSize()));
    String explanationHeader = this.getExplanationHeader(interaction);
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP(explanationHeader, LEFT_PADDING_1800));
    String actionString;
    for (Transition transition : interaction.getForwardTransitions()) {
      actionString = this.getExplanationNextAction(transition);
      this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, actionString));
    }
    if (interaction.getForwardTransitions().isEmpty()) {
        actionString = this.getMessage("userstory.interaction.noActions");
        this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, actionString));
    }
    return null;
  }

  public Object visitNavigation(Navigation navigation) {
	  return this.visitTransition(navigation);
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return getWmlFactory().createP();
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
	  return this.visitTransition(richBehavior);
  }

  public Object visitTransition(Transition transition) {
    String explanationHeader = this.getExplanationHeader(transition);
    String transitionDiagramExplanationText = this.getMessage("userstory.wen.section.navigation.diagram.explanation", new String[] { transition.getName() });

    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().createNumberingP(3, 2, transitionDiagramExplanationText, true, getSubsectionFontSize()));

    String preconditionHeader = this.getPreconditionExplanationHeader(transition);
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP(preconditionHeader, LEFT_PADDING_1800));
    String actionString;
    actionString = (String) transition.getPrecondition().accept(this.getExpressionPrettyPrinter());
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, actionString));

    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP(explanationHeader, LEFT_PADDING_1800));
    for (Action action : transition.getActions()) {
      actionString = (String) action.accept(this.getActionVisitor());
      this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, actionString));
    }
    return null;
  }


  private WordprocessingMLPackage getWordprocessingMLPackage() {
    return wordprocessingMLPackage;
  }

  private void setWordprocessingMLPackage(WordprocessingMLPackage wordprocessingMLPackage) {
    this.wordprocessingMLPackage = wordprocessingMLPackage;
  }

  private long getSubsectionFontSize() {
    return subsectionFontSize;
  }

  private void setSubsectionFontSize(long subsectionFontSize) {
    this.subsectionFontSize = subsectionFontSize;
  }

}
