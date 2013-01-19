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
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.DivExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.impl.expression.core.GeneratorExpression;
import org.webspeclanguage.impl.expression.core.GreaterEqualExpression;
import org.webspeclanguage.impl.expression.core.GreaterExpression;
import org.webspeclanguage.impl.expression.core.ImpliesExpression;
import org.webspeclanguage.impl.expression.core.InteractionPropertyExpression;
import org.webspeclanguage.impl.expression.core.LowerEqualExpression;
import org.webspeclanguage.impl.expression.core.LowerExpression;
import org.webspeclanguage.impl.expression.core.MulExpression;
import org.webspeclanguage.impl.expression.core.NativeFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.NotEqualsExpression;
import org.webspeclanguage.impl.expression.core.NotExpression;
import org.webspeclanguage.impl.expression.core.NumberConstant;
import org.webspeclanguage.impl.expression.core.OrExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.SubExpression;
import org.webspeclanguage.impl.expression.core.ToBooleanFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToNumberFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToStringFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.expression.core.WidgetReference;

/**
 * Visitor implementation that help to wrap large amount of methods to be
 * implemented due to the visitor interface.
 * 
 * @author cristian.cianfagna
 */
public class UserStoryExpressionVisitor implements org.webspeclanguage.impl.expression.core.ExpressionVisitor {

  private MessageSource messageSource;
  private Locale locale;

  public UserStoryExpressionVisitor(MessageSource messageSource, Locale locale) {
    this.setMessageSource(messageSource);
    this.setLocale(locale);
  }

  public Object visitAddExpression(AddExpression expression) {
    return null;
  }

  public Object visitAndExpression(AndExpression andExpression) {
    return null;
  }

  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    return null;
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return null;
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return null;
  }

  public Object visitConcatExpression(ConcatExpression concatExpression) {
    return null;
  }

  public Object visitDivExpression(DivExpression expression) {
    return null;
  }

  public Object visitEqualsExpression(EqualsExpression equalsExpression) {
    return null;
  }

  public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
    StringBuilder sb = new StringBuilder();
    if (FunctionCallExpression.TYPE.equals(functionCallExpression.getFunctionName())) {
      sb.append(this.getMessage("userstory.wts1.navigation.action.complete"));
      sb.append(" ");
    } else {
      sb.append(this.getMessage("userstory.wts1.navigation.action.clickon"));
      sb.append(" ");
    }
    ;
    for (Expression expression : functionCallExpression.getArguments()) {
      if (WidgetReference.class.isInstance(expression)) {
        WidgetReference widgetReference = (WidgetReference) expression;
        sb.append("'");
        sb.append(widgetReference.getWidget().getId());
        sb.append("'");
      }
    }
    return sb.toString();
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    return null;
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    return null;
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    return null;
  }

  public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
    return null;
  }

  public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
    return null;
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    return null;
  }

  public Object visitLowerExpression(LowerExpression expression) {
    return null;
  }

  public Object visitMulExpression(MulExpression expression) {
    return null;
  }

  public Object visitNativeFunctionCallExpression(NativeFunctionCallExpression nativeFunctionCallExpression) {
    return null;
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    return null;
  }

  public Object visitNotExpression(NotExpression notExpression) {
    return null;
  }

  public Object visitNumberConstant(NumberConstant integerConstant) {
    return null;
  }

  public Object visitOrExpression(OrExpression expression) {
    return null;
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return null;
  }

  public Object visitSubExpression(SubExpression expression) {
    return null;
  }

  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression toBooleanFunctionCallExpression) {
    return null;
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression toNumberFunctionCallExpression) {
    return null;
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression toStringFunctionCallExpression) {
    return null;
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return null;
  }

  public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    return null;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    return null;
  }

  private String getMessage(String bundleKey) {
    return this.getMessageSource().getMessage(bundleKey, new Object[] {}, this.getLocale());
  }

  private void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  private MessageSource getMessageSource() {
    return this.messageSource;
  }

  private Locale getLocale() {
    return locale;
  }

  private void setLocale(Locale locale) {
    this.locale = locale;
  }

}
