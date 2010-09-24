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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.exception.WebspecException;
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
import org.webspeclanguage.expression.base.InteractionPropertyExpression;
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

  private static Map<String, FunctionDefinition> functionDefinitions = new HashMap<String, FunctionDefinition>();
  private static Map<Class<?>, Map<String, ExpressionType>> propertyDefinitions = new HashMap<Class<?>, Map<String, ExpressionType>>();

  private static void initializeFunctionDefinitions() {
    try {
      Properties properties = new Properties();
      InputStream in = Thread.currentThread().getContextClassLoader().getResource("typechecker.rules").openStream();
      properties.load(in);
      in.close();
      
      for (Object keyObject : properties.keySet()) {
        String key = (String) keyObject;
        String value = properties.getProperty(key);
        
        if (key.contains("-")) {
          //it is a property
          importPropertyDefinition(key, value);
        } else {
          //it is a function definition
          importFunctionDefinition(key, value);
        }
      }
    } catch (FileNotFoundException e) {
      throw new WebspecException(e);
    } catch (IOException e) {
      throw new WebspecException(e);
    } catch (ClassNotFoundException e) {
      throw new WebspecException(e);
    }
  }

  private static void importFunctionDefinition(String key, String value) throws ClassNotFoundException {
    int index = value.indexOf("->");
    String[] argumentTypesAsString = value.substring(0, index).split(",");
    String returnTypeAsString = value.substring(index + 2, value.length());
    ExpressionType returnType = ExpressionType.valueOf(returnTypeAsString);
    Matcher[] matchers = new Matcher[argumentTypesAsString.length];
    for (int i = 0; i < argumentTypesAsString.length; i++) {
      String argumentTypeAsString = argumentTypesAsString[i];
      ExpressionType argumentType = ExpressionType.safeValueOf(argumentTypeAsString);
      if (argumentType != null) {
        matchers[i] = new TypeMatcher(argumentType);
      } else {
        matchers[i] = new ClassMatcher(Class.forName(argumentTypeAsString));
      }
    }
    FunctionDefinition functionDefinition = new FunctionDefinition(key, returnType, matchers);
    functionDefinitions.put(key, functionDefinition);
  }

  private static void importPropertyDefinition(String key, String value) throws ClassNotFoundException {
    int index = key.indexOf('-');
    String className = key.substring(0, index);
    String propertyName = key.substring(index + 1, key.length());
    Class<?> theClass = Class.forName(className);
    if (!propertyDefinitions.containsKey(theClass)) {
      propertyDefinitions.put(theClass, new HashMap<String, ExpressionType>());
    }
    propertyDefinitions.get(theClass).put(propertyName, ExpressionType.valueOf(value));
  }
  
  static {
    initializeFunctionDefinitions();
  }
  
  private Map<String, ExpressionType> variables;
  private Map<String, Generator> generators;
  
  public ExpressionTypechecker(WebSpecDiagram diagram) {
    this.generators = new HashMap<String, Generator>();
    this.variables = new HashMap<String, ExpressionType>();
    
    this.initializeGenerators(diagram);
  }

  private void initializeGenerators(WebSpecDiagram diagram) {
    if (diagram != null) {
      for (String generatorName : diagram.getGeneratorsNames()) {
        Generator generator = diagram.getGeneratorNamed(generatorName);
        this.set(generator);
      }
    }
  }

  public ExpressionType typecheck(Expression expression) {
    return (ExpressionType) expression.accept(this);
  }

  private void shouldNotBe(Expression e, ExpressionType unexpectedType, ExpressionType type) {
    if (type.equals(unexpectedType)) {
      throw new TypecheckException(e, unexpectedType, type);
    }
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
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression.getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitAndExpression(AndExpression expression) {
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitConcatExpression(ConcatExpression expression) {
    this.shouldBe(ExpressionType.STRING, expression.getOp1(), expression.getOp2());
    return ExpressionType.STRING;
  }

  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    this.shouldBe(ExpressionType.ARRAY, arrayAccessExpression.getArrayExpression());
    this.shouldBe(ExpressionType.NUMBER, arrayAccessExpression.getIndex());
    //TODO
    return null;
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return ExpressionType.ARRAY;
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return ExpressionType.BOOLEAN;
  }

  public Object visitDivExpression(DivExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression.getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitEqualsExpression(EqualsExpression expression) {
    this.shouldBeOfEqualType(expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    Generator generator = this.generators.get(generatorExpression.getGeneratorName());
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
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitFunctionCallExpression(
      FunctionCallExpression testFunctionCallExpression) {
    return this.typecheck(testFunctionCallExpression);
  }
  
  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression toBooleanFunctionCallExpression) {
    return this.typecheck(toBooleanFunctionCallExpression);
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression toNumberFunctionCallExpression) {
    return this.typecheck(toNumberFunctionCallExpression);
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression toStringFunctionCallExpression) {
    return this.typecheck(toStringFunctionCallExpression);
  }
  
  private ExpressionType typecheck(AbstractFunctionCallExpression functionCall) {
    FunctionDefinition definition = functionDefinitions.get(functionCall.getFunctionName());
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
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression.getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitNativeFunctionCallExpression(NativeFunctionCallExpression nativeFunctionCallExpression) {
    for (Expression e : nativeFunctionCallExpression.getArguments()) {
      this.typecheck(e);
    }
    return ExpressionType.VOID;
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
    this.shouldBe(ExpressionType.BOOLEAN, expression.getOp1(), expression.getOp2());
    return ExpressionType.BOOLEAN;
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return ExpressionType.STRING;
  }

  public Object visitSubExpression(SubExpression expression) {
    this.shouldBe(ExpressionType.NUMBER, expression.getOp1(), expression.getOp2());
    return ExpressionType.NUMBER;
  }

  public Object visitVariableValue(VariableValue variableValue) {
    ExpressionType type = this.variables.get(variableValue.getVariableName());
    if (type != null) {
      return type;
    } else {
      throw new UndeclaredVariableException(variableValue.getVariableName());
    }
  }
  
  public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
    return propertyDefinitions.get(WebSpecInteraction.class).get(interactionPropertyExpression.getProperty());
  }

  public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    ExpressionType type = this.getTypeOfProperty(widgetPropertyReference.getWidget().getClass(), widgetPropertyReference.getPropertyName());
    this.shouldNotBe(widgetPropertyReference, ExpressionType.UNKNOWN, type);
    for (Expression expression : widgetPropertyReference.getVariables().values()) {
      this.shouldBe(ExpressionType.NUMBER, expression);
    }
    return type;
  }
  
  private ExpressionType getTypeOfProperty(Class<?> aWidgetClass, String property) {
    for (Class<?> aClass : propertyDefinitions.keySet()) {
      if (aClass.isAssignableFrom(aWidgetClass)) {
        Map<String, ExpressionType> types = propertyDefinitions.get(aClass);
        if (types != null) {
          ExpressionType type = types.get(property);
          if (type != null) {
            return type;
          }
        }
      }
    }
    return ExpressionType.UNKNOWN;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    for (Expression expression : widgetReference.getVariables().values()) {
      this.shouldBe(ExpressionType.NUMBER, expression);
    }
    return ExpressionType.WIDGET;
  }

  public void set(String variableName, ConstantExpression expression) {
    this.variables.put(variableName, (ExpressionType) expression.accept(this));
  }

  public void set(String variableName, ExpressionType type) {
    this.variables.put(variableName, type);
  }

  public void set(Generator generator) {
    this.generators.put(generator.getName(), generator);
  }

  public ExpressionType getType(String variableName) {
    return this.variables.get(variableName);
  }
}
