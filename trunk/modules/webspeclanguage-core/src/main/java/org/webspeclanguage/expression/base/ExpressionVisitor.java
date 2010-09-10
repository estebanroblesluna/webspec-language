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
package org.webspeclanguage.expression.base;

/**
 * A visitor for {@link Expression}
 * 
 * @author Esteban Robles Luna
 */
public interface ExpressionVisitor {

  Object visitEqualsExpression(EqualsExpression equalsExpression);

  Object visitBooleanConstant(BooleanConstant booleanConstant);

  Object visitStringConstant(StringConstant stringConstant);

  Object visitVariableValue(VariableValue variableValue);

  Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference);

  Object visitWidgetReference(WidgetReference widgetReference);

  Object visitGeneratorExpression(GeneratorExpression generatorExpression);

  Object visitNumberConstant(NumberConstant integerConstant);

  Object visitAndExpression(AndExpression andExpression);

  Object visitNotExpression(NotExpression notExpression);

  Object visitAddExpression(AddExpression expression);

  Object visitDivExpression(DivExpression expression);

  Object visitGreaterExpression(GreaterExpression expression);

  Object visitGreaterEqualExpression(GreaterEqualExpression expression);

  Object visitLowerExpression(LowerExpression expression);

  Object visitLowerEqualExpression(LowerEqualExpression expression);

  Object visitMulExpression(MulExpression expression);

  Object visitNotEqualsExpression(NotEqualsExpression expression);

  Object visitOrExpression(OrExpression expression);

  Object visitSubExpression(SubExpression expression);

  Object visitImpliesExpression(ImpliesExpression impliesExpression);

  Object visitArrayExpression(ArrayExpression arrayExpression);

  Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression);

  Object visitNativeFunctionCallExpression(NativeFunctionCallExpression nativeFunctionCallExpression);

  Object visitFunctionCallExpression(FunctionCallExpression testFunctionCallExpression);

  Object visitConcatExpression(ConcatExpression concatExpression);

  Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression toBooleanFunctionCallExpression);

  Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression toNumberFunctionCallExpression);

  Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression toStringFunctionCallExpression);
}
