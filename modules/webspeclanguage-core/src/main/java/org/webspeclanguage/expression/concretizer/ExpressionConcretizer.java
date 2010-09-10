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
package org.webspeclanguage.expression.concretizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.expression.base.AddExpression;
import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.ArrayAccessExpression;
import org.webspeclanguage.expression.base.ArrayExpression;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.ConcatExpression;
import org.webspeclanguage.expression.base.ConstantExpression;
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
import org.webspeclanguage.generator.Generator;

/**
 * A concretizer replaces all the occurrences of {@link Generator}
 * and {@link VariableValue} with {@link ConstantExpression}
 * according to its configuration
 * 
 * @author Esteban Robles Luna
 */
//TODO make this class uses ClonerVisitor
@SuppressWarnings("unchecked")
public class ExpressionConcretizer implements ExpressionVisitor {

  private Map<String, ConstantExpression> variables;
  private Map<String, Generator> generators;

  public ExpressionConcretizer() {
    this.setGenerators(new HashMap<String, Generator>());
    this.setVariables(new HashMap<String, ConstantExpression>());
  }

  public Expression makeConcrete(Expression expression) {
    return (Expression) expression.accept(this);
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return booleanConstant;
  }

  public Object visitEqualsExpression(EqualsExpression equalsExpression) {
    return new EqualsExpression(makeConcrete(equalsExpression.getOp1()),
        makeConcrete(equalsExpression.getOp2()));
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    if (this.getGenerator(generatorExpression.getGeneratorName()) == null) {
      return generatorExpression;
    } else {
      return this.getGenerator(generatorExpression.getGeneratorName())
          .generate();
    }
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return stringConstant;
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return this.getVariables().containsKey(variableValue.getVariableName()) ? this
        .getVariables().get(variableValue.getVariableName())
        : variableValue;
  }

  public Object visitWidgetPropertyReference(
      WidgetPropertyReference widgetPropertyReference) {
    return widgetPropertyReference;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    return widgetReference;
  }

  public Object visitNumberConstant(NumberConstant integerConstant) {
    return integerConstant;
  }

  public Object visitAndExpression(AndExpression and) {
    return new AndExpression(makeConcrete(and.getOp1()), makeConcrete(and
        .getOp2()));
  }

  public Object visitConcatExpression(ConcatExpression concatExpression) {
    return new ConcatExpression(makeConcrete(concatExpression.getOp1()),
        makeConcrete(concatExpression.getOp2()));
  }

  public Object visitNotExpression(NotExpression notExpression) {
    return new NotExpression(makeConcrete(notExpression.getExpression()));
  }

  public Object visitAddExpression(AddExpression expression) {
    return new AddExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitDivExpression(DivExpression expression) {
    return new DivExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    return new GreaterEqualExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    return new GreaterExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    return new LowerEqualExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitLowerExpression(LowerExpression expression) {
    return new LowerExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitMulExpression(MulExpression expression) {
    return new MulExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    return new NotEqualsExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitOrExpression(OrExpression expression) {
    return new OrExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }

  public Object visitSubExpression(SubExpression expression) {
    return new SubExpression(makeConcrete(expression.getOp1()),
        makeConcrete(expression.getOp2()));
  }
  
  private List<Expression> makeConcrete(List<Expression> expressions) {
    List<Expression> exps = new ArrayList<Expression>();
    for (Expression exp : expressions) {
      exps.add(makeConcrete(exp));
    }
    return exps;
  }

  public Object visitFunctionCallExpression(
      FunctionCallExpression functionCallExpression) {
    return new FunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        makeConcrete(functionCallExpression.getArguments()));
  }
  
  public Object visitNativeFunctionCallExpression(
      NativeFunctionCallExpression functionCallExpression) {
    return new NativeFunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        makeConcrete(functionCallExpression.getArguments()));
  }
  
  public Object visitToBooleanFunctionCallExpression(
      ToBooleanFunctionCallExpression functionCallExpression) {
    return new ToBooleanFunctionCallExpression(
        makeConcrete(functionCallExpression.getArguments()));
  }

  public Object visitToNumberFunctionCallExpression(
      ToNumberFunctionCallExpression functionCallExpression) {
    return new ToNumberFunctionCallExpression(
        makeConcrete(functionCallExpression.getArguments()));
  }

  public Object visitToStringFunctionCallExpression(
      ToStringFunctionCallExpression functionCallExpression) {
    return new ToStringFunctionCallExpression(
        makeConcrete(functionCallExpression.getArguments()));
  }

  public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
    return new ImpliesExpression(makeConcrete(impliesExpression.getOp1()),
        makeConcrete(impliesExpression.getOp2()));
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return arrayExpression;
  }

  public Object visitArrayAccessExpression(
      ArrayAccessExpression arrayAccessExpression) {
    // TODO
    return null;
    // return arrayAccessExpression.evaluate(getGeneratedVariables());
  }

  private Generator getGenerator(String generatorName) {
    return this.getGenerators().get(generatorName);
  }

  public void set(String variableName, ConstantExpression expression) {
    this.getVariables().put(variableName, expression);
  }

  public void removeConstantVariable(String variableName) {
    this.getVariables().remove(variableName);
  }

  public void set(String generatorName, Generator generator) {
    this.getGenerators().put(generatorName, generator);
  }

  public Map<String, ConstantExpression> getVariables() {
    return variables;
  }

  private void setVariables(Map<String, ConstantExpression> variables) {
    this.variables = variables;
  }

  private Map<String, Generator> getGenerators() {
    return generators;
  }

  private void setGenerators(Map<String, Generator> generators) {
    this.generators = generators;
  }
}
