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
package org.webspeclanguage.impl.expression.conjunctivenormalform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.ArrayHolder;
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
 * A cloner visitor clones an {@link Expression} tree
 * 
 * @author Esteban Robles Luna
 */
public class ClonerVisitor implements ExpressionVisitor {

  protected Expression clone(Expression expression) {
    return (Expression) expression.accept(this);
  }
  
  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return new BooleanConstant(booleanConstant.getConstant());
  }

  public Object visitEqualsExpression(EqualsExpression equalsExpression) {
    return new EqualsExpression(
        this.clone(equalsExpression.getOp1()),
        this.clone(equalsExpression.getOp2()));
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    return new GeneratorExpression(generatorExpression.getGeneratorName());
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return new StringConstant(stringConstant.getConstant());
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return new VariableValue(variableValue.getVariableName());
  }

  public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    return new WidgetPropertyReference(
        widgetPropertyReference.getWidget(),
        widgetPropertyReference.getPropertyName(),
        this.clone(widgetPropertyReference.getVariables()));
  }

  private Map<String, Expression> clone(Map<String, Expression> variables) {
    Map<String, Expression> result = new HashMap<String, Expression>();
    for (String variable : variables.keySet()) {
      result.put(variable, this.clone(variables.get(variable)));
    }
    return result;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    return new WidgetReference(widgetReference.getWidget(), this.clone(widgetReference.getVariables()));
  }

  public Object visitNumberConstant(NumberConstant numberConstant) {
    return new NumberConstant(numberConstant.getConstant());
  }

  public Object visitAndExpression(AndExpression and) {
    return new AndExpression(
        this.clone(and.getOp1()), 
        this.clone(and.getOp2()));
  }

  public Object visitConcatExpression(ConcatExpression concatExpression) {
    return new ConcatExpression(
        this.clone(concatExpression.getOp1()),
        this.clone(concatExpression.getOp2()));
  }

  public Object visitNotExpression(NotExpression notExpression) {
    return new NotExpression(this.clone(notExpression.getExpression()));
  }

  public Object visitAddExpression(AddExpression expression) {
    return new AddExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitDivExpression(DivExpression expression) {
    return new DivExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    return new GreaterEqualExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    return new GreaterExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    return new LowerEqualExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitLowerExpression(LowerExpression expression) {
    return new LowerExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitMulExpression(MulExpression expression) {
    return new MulExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    return new NotEqualsExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitOrExpression(OrExpression expression) {
    return new OrExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }

  public Object visitSubExpression(SubExpression expression) {
    return new SubExpression(
        this.clone(expression.getOp1()),
        this.clone(expression.getOp2()));
  }
  
  private List<Expression> clone(List<Expression> expressions) {
    List<Expression> exps = new ArrayList<Expression>();
    for (Expression exp : expressions) {
      exps.add(this.clone(exp));
    }
    return exps;
  }

  public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
    return new FunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        this.clone(functionCallExpression.getArguments()));
  }
  
  public Object visitNativeFunctionCallExpression(NativeFunctionCallExpression functionCallExpression) {
    return new NativeFunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        this.clone(functionCallExpression.getArguments()));
  }
  
  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression functionCallExpression) {
    return new ToBooleanFunctionCallExpression(
        this.clone(functionCallExpression.getArguments()));
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression functionCallExpression) {
    return new ToNumberFunctionCallExpression(
        this.clone(functionCallExpression.getArguments()));
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression functionCallExpression) {
    return new ToStringFunctionCallExpression(
        this.clone(functionCallExpression.getArguments()));
  }

  public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
    return new ImpliesExpression(
        this.clone(impliesExpression.getOp1()),
        this.clone(impliesExpression.getOp2()));
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return arrayExpression;
  }

  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    ArrayHolder arrayExpression = (ArrayHolder) this.clone(arrayAccessExpression.getArrayExpression());
    Expression newIndex = this.clone(arrayAccessExpression.getIndex());
    return new ArrayAccessExpression(arrayExpression, newIndex);
  }

  public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
    return new InteractionPropertyExpression(
            interactionPropertyExpression.getInteraction(), 
            interactionPropertyExpression.getProperty());
  }
}
