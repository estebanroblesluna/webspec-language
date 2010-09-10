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
package org.webspeclanguage.expression.typechecker;

import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.expression.base.AbstractFunctionCallExpression;
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
import org.webspeclanguage.expression.base.ExpressionType;
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
 * A typechecker for {@link Expression} in the context of a
 * {@link WebSpecDiagram}
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class ExpressionTypechecker implements ExpressionVisitor {

  private Map<String, ExpressionType> variables;
  private Map<String, Generator> generators;
  private Map<String, FunctionDefinition> functionDefinitions;
  
  public ExpressionTypechecker(WebSpecDiagram diagram) {
    this.setGenerators(new HashMap<String, Generator>());
    this.setVariables(new HashMap<String, ExpressionType>());
    this.setFunctionDefinitions(new HashMap<String, FunctionDefinition>());
    
    this.initializeGenerators(diagram);
    this.initializeFunctionDefinitions();
  }

  private void initializeFunctionDefinitions() {
    this.addFunctionDefinition(
        "click",
        ExpressionType.UNKNOWN,
        new ClassMatcher(WidgetReference.class));
    
    this.addFunctionDefinition(
        "type", 
        ExpressionType.UNKNOWN,
        new ClassMatcher(WidgetReference.class), 
        new TypeMatcher(ExpressionType.STRING));
    
    this.addFunctionDefinition(
        "toString", 
        ExpressionType.STRING, 
        new ClassMatcher(Expression.class));
    
    this.addFunctionDefinition(
        "toNumber", 
        ExpressionType.NUMBER, 
        new ClassMatcher(Expression.class));

    this.addFunctionDefinition(
        "toBoolean", 
        ExpressionType.BOOLEAN, 
        new ClassMatcher(Expression.class));
  }

  private void addFunctionDefinition(String functionName, ExpressionType returnType, Matcher... matchers) {
    FunctionDefinition definition = new FunctionDefinition(functionName, returnType, matchers);
    this.getFunctionDefinitions().put(functionName, definition);
  }

  private void initializeGenerators(WebSpecDiagram diagram) {
    if (diagram != null) {
      for (String generatorName : diagram.getGeneratorsNames()) {
        this.set(generatorName, diagram.getGeneratorNamed(generatorName));
      }
    }
  }

  public ExpressionType typecheck(Expression expression) {
    return (ExpressionType) expression.accept(this);
  }

  private void shouldBe(ExpressionType expectedType, Expression... expressions) {
    for (Expression e : expressions) {
      ExpressionType actualType = this.typecheck(e);
      if (!expectedType.equals(actualType)) {
        throw new TypecheckException(e, expectedType, actualType);
      }
    }
  }

  private void shouldBeOfEqualType(Expression... expressions) {
    ExpressionType expectedType = this.typecheck(expressions[0]);
    this.shouldBe(expectedType, expressions);
  }

  public Object visitAddExpression(AddExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitAndExpression(AndExpression expression) {
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitConcatExpression(ConcatExpression expression) {
    this.shouldBe(ExpressionType.STRING, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.STRING;
  }

  public Object visitArrayAccessExpression(
      ArrayAccessExpression arrayAccessExpression) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return ExpressionType.ARRAY;
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return ExpressionType.BOOLEAN;
  }

  public Object visitDivExpression(DivExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitEqualsExpression(EqualsExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    Generator generator = this.getGenerators().get(
        generatorExpression.getGeneratorName());
    if (generator == null) {
      throw new InexistentGeneratorException(generatorExpression.getGeneratorName());
    } else {
      return generator.getGenerationType();
    }
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitImpliesExpression(ImpliesExpression expression) {
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitFunctionCallExpression(
      FunctionCallExpression testFunctionCallExpression) {
    return this.typecheck(testFunctionCallExpression);
  }
  
  public Object visitToBooleanFunctionCallExpression(
      ToBooleanFunctionCallExpression toBooleanFunctionCallExpression) {
    return this.typecheck(toBooleanFunctionCallExpression);
  }

  public Object visitToNumberFunctionCallExpression(
      ToNumberFunctionCallExpression toNumberFunctionCallExpression) {
    return this.typecheck(toNumberFunctionCallExpression);
  }

  public Object visitToStringFunctionCallExpression(
      ToStringFunctionCallExpression toStringFunctionCallExpression) {
    return this.typecheck(toStringFunctionCallExpression);
  }
  
  private ExpressionType typecheck(AbstractFunctionCallExpression functionCall) {
    FunctionDefinition definition = this.getFunctionDefinitions().get(functionCall.getFunctionName());
    if (definition != null) {
      if (definition.matches(functionCall, this)) {
        return definition.getReturnType();
      } else {
        throw new UnmatchedFunctionException(functionCall, definition);
      }
    } else {
      throw new InexistentFunctionException(functionCall.getFunctionName());
    }
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitLowerExpression(LowerExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitMulExpression(MulExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitNativeFunctionCallExpression(
      NativeFunctionCallExpression nativeFunctionCallExpression) {
    for (Expression e : nativeFunctionCallExpression.getArguments()) {
      this.typecheck(e);
    }
    return ExpressionType.UNKNOWN;
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitNotExpression(NotExpression notExpression) {
    this.shouldBe(ExpressionType.BOOLEAN, notExpression.getExpression());
    return ExpressionType.BOOLEAN;
  }

  public Object visitNumberConstant(NumberConstant integerConstant) {
    return ExpressionType.NUMBER;
  }

  public Object visitOrExpression(OrExpression expression) {
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return ExpressionType.STRING;
  }

  public Object visitSubExpression(SubExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression
        .getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitVariableValue(VariableValue variableValue) {
    ExpressionType type = this.getVariables().get(
        variableValue.getVariableName());
    if (type != null) {
      return type;
    } else {
      throw new UndeclaredVariableException(variableValue.getVariableName());
    }
  }

  public Object visitWidgetPropertyReference(
      WidgetPropertyReference widgetPropertyReference) {
    return ExpressionType.STRING;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    return ExpressionType.STRING;
  }

  public void set(String variableName, ConstantExpression expression) {
    this.getVariables().put(variableName,
        (ExpressionType) expression.accept(this));
  }

  public void set(String variableName, ExpressionType type) {
    this.getVariables().put(variableName, type);
  }

  public void set(String generatorName, Generator generator) {
    this.getGenerators().put(generatorName, generator);
  }

  public ExpressionType getType(String variableName) {
    return this.getVariables().get(variableName);
  }

  private Map<String, ExpressionType> getVariables() {
    return variables;
  }

  private void setVariables(Map<String, ExpressionType> variables) {
    this.variables = variables;
  }

  private Map<String, Generator> getGenerators() {
    return generators;
  }

  private void setGenerators(Map<String, Generator> generators) {
    this.generators = generators;
  }

  private Map<String, FunctionDefinition> getFunctionDefinitions() {
    return functionDefinitions;
  }

  private void setFunctionDefinitions(
      Map<String, FunctionDefinition> functionDefinitions) {
    this.functionDefinitions = functionDefinitions;
  }
}
