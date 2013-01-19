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
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.action.ActionVisitor;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.userstories.util.ExpressionPrettyPrinter;

/**
 * @author cristian.cianfagna
 */
public abstract class AbstractExplanationVisitor extends AbstractVisitor {

  private ExpressionPrettyPrinter expressionPrettyPrinter;

  public AbstractExplanationVisitor(MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
    this.setExpressionPrettyPrinter(new ExpressionPrettyPrinter(messageSource, locale));
  }

  protected String getExplanationHeader(Interaction interaction) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.interaction.where"));
    sb.append(" '");
    sb.append(interaction.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.interaction.ableto"));
    sb.append(" ");
    return sb.toString();
  }

  protected String getExplanationHeader(Transition transition) {
	StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.navigation.to"));
    sb.append(" '");
    sb.append(transition.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.navigation.actions"));
    sb.append(" ");
    return sb.toString();
  }

  protected String getPreconditionExplanationHeader(Transition transition) {
	StringBuilder preconditionHeader = new StringBuilder();
	preconditionHeader.append(this.getMessage("userstory.wts1.navigation.precondition.to")).
	append(" '").
	append(transition.getName()).
	append("' ").
	append(this.getMessage("userstory.wts1.navigation.precondition.hasTo"));
	return preconditionHeader.toString();
  }

  protected ActionVisitor getActionVisitor() {
    return new ActionVisitor() {

      public Object visitExpressionAction(ExpressionAction expressionAction) {
        return (String) expressionAction.getExpression().accept(new UserStoryExpressionVisitor(getMessageSource(), getLocale()));
      }
      public Object visitLetVariable(LetVariable letVariable) {
        StringBuilder sb = new StringBuilder();
        sb.append("userstory.wts1.navigation.action.letvariable");
        sb.append(" '");
        sb.append(letVariable.getVariableName());
        sb.append("' ");
        sb.append("userstory.wts1.navigation.action.letvariable.type");
        sb.append(" ");
        sb.append(letVariable.getType().name());
        return sb.toString();
      }
    };
  }

  protected ExpressionPrettyPrinter getExpressionPrettyPrinter() {
	return expressionPrettyPrinter;
  }

  protected void setExpressionPrettyPrinter(
		ExpressionPrettyPrinter expressionPrettyPrinter) {
	this.expressionPrettyPrinter = expressionPrettyPrinter;
  }

  
}
