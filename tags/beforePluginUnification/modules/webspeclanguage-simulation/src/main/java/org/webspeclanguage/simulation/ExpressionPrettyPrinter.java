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
package org.webspeclanguage.simulation;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.expression.core.AbstractFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.BinaryExpression;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.DivExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionVisitor;
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
 * A pretty printer for expressions
 * 
 * @author Esteban Robles Luna
 */
public class ExpressionPrettyPrinter implements ExpressionVisitor {

  public String prettyPrint(Expression expression) {
    Validate.notNull(expression);
    
    return (String) expression.accept(this);
  }
  
  public Object generateForBinaryExpression(BinaryExpression expression, String operand) {
    return
    prettyPrint(expression.getOp1())
      + operand
      + prettyPrint(expression.getOp2());
  }
  
  public Object visitAddExpression(AddExpression expression) {
    return generateForBinaryExpression(expression, " + ");
  }

  public Object visitAndExpression(AndExpression andExpression) {
    return generateForBinaryExpression(andExpression, " and ");
  }

  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    return "arr-acc";
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return "arr";
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return booleanConstant.getConstant().toString();
  }

  public Object visitDivExpression(DivExpression expression) {
    return generateForBinaryExpression(expression, " / ");
  }

  public Object visitEqualsExpression(EqualsExpression equalsExpression) {
    return generateForBinaryExpression(equalsExpression, " = ");
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    return "gen";
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    return generateForBinaryExpression(expression, " >= ");
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    return generateForBinaryExpression(expression, " > ");
  }

  public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
    return generateForBinaryExpression(impliesExpression, " -> ");
  }

  public Object visitFunctionCallExpression(FunctionCallExpression testFunctionCallExpression) {
    return this.printFunction(testFunctionCallExpression);
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    return generateForBinaryExpression(expression, " <= ");
  }

  public Object visitLowerExpression(LowerExpression expression) {
    return generateForBinaryExpression(expression, " < ");
  }

  public Object visitMulExpression(MulExpression expression) {
    return generateForBinaryExpression(expression, " * ");
  }

  public Object visitNativeFunctionCallExpression(NativeFunctionCallExpression nativeFunctionCallExpression) {
    return "%" + this.printFunction(nativeFunctionCallExpression);
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    return generateForBinaryExpression(expression, " != ");
  }

  public Object visitNotExpression(NotExpression notExpression) {
    return "! (" + prettyPrint(notExpression.getExpression()) + ")";
  }

  public Object visitNumberConstant(NumberConstant integerConstant) {
    return integerConstant.getConstant().toPlainString();
  }

  public Object visitOrExpression(OrExpression expression) {
    return generateForBinaryExpression(expression, " or ");
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return "\"" + stringConstant.getConstant().toString() + "\"";
  }

  public Object visitSubExpression(SubExpression expression) {
    return generateForBinaryExpression(expression, " - ");
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return "${" + variableValue.getVariableName() + "}";
  }

  public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    return "Widget: " 
      + widgetPropertyReference.getWidget().getName() 
      + " property:" 
      + widgetPropertyReference.getPropertyName();
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    return "Widget: " + widgetReference.getWidget().getName();
  }

  public Object visitConcatExpression(ConcatExpression concat) {
    return generateForBinaryExpression(concat, " & ");
  }

  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression function) {
    return this.printFunction(function);
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression function) {
    return this.printFunction(function);
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression function) {
    return this.printFunction(function);
  }
  
  private String printFunction(AbstractFunctionCallExpression functionCall) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(functionCall.getFunctionName());
    buffer.append("(");
    for (Expression expression : functionCall.getArguments()) {
      buffer.append(this.prettyPrint(expression));
      buffer.append(",");
    }
    String function = buffer.toString();
    function = functionCall.getArguments().size() > 0 
      ? function.substring(0, function.length() - 1) 
      : function;
    return function + ")";
  }

  public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
    return "Interaction: " 
      + interactionPropertyExpression.getInteraction().getName() 
      + " property: " 
      + interactionPropertyExpression.getProperty();
  }
}
