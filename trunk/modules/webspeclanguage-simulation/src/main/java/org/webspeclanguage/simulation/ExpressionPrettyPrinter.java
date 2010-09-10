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
import org.webspeclanguage.expression.base.AddExpression;
import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.ArrayAccessExpression;
import org.webspeclanguage.expression.base.ArrayExpression;
import org.webspeclanguage.expression.base.BinaryExpression;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.ConcatExpression;
import org.webspeclanguage.expression.base.DivExpression;
import org.webspeclanguage.expression.base.EqualsExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionVisitor;
import org.webspeclanguage.expression.base.FunctionCallExpression;
import org.webspeclanguage.expression.base.GeneratorExpression;
import org.webspeclanguage.expression.base.GreaterEqualExpression;
import org.webspeclanguage.expression.base.GreaterExpression;
import org.webspeclanguage.expression.base.ImpliesExpression;
import org.webspeclanguage.expression.base.LowerEqualExpression;
import org.webspeclanguage.expression.base.LowerExpression;
import org.webspeclanguage.expression.base.MulExpression;
import org.webspeclanguage.expression.base.NativeFunctionCallExpression;
import org.webspeclanguage.expression.base.NotEqualsExpression;
import org.webspeclanguage.expression.base.NotExpression;
import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.expression.base.OrExpression;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.base.SubExpression;
import org.webspeclanguage.expression.base.ToBooleanFunctionCallExpression;
import org.webspeclanguage.expression.base.ToNumberFunctionCallExpression;
import org.webspeclanguage.expression.base.ToStringFunctionCallExpression;
import org.webspeclanguage.expression.base.VariableValue;
import org.webspeclanguage.expression.base.WidgetPropertyReference;
import org.webspeclanguage.expression.base.WidgetReference;

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
    return "int-test-fwk";
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
    return "native-func";
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

  public Object visitConcatExpression(ConcatExpression arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression arg0) {
    // TODO Auto-generated method stub
    return null;
  }
}
