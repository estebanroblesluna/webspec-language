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
package org.webspeclanguage.impl.expression.concretizer;

import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.api.Generator;
import org.webspeclanguage.impl.expression.conjunctivenormalform.ClonerVisitor;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayHolder;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.GeneratorExpression;
import org.webspeclanguage.impl.expression.core.VariableValue;

/**
 * A concretizer replaces all the occurrences of {@link Generator}
 * and {@link VariableValue} with {@link ConstantExpression}
 * according to its configuration
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class ExpressionConcretizer extends ClonerVisitor {

  private Map<String, ConstantExpression> variables;
  private Map<String, Generator> generators;

  public ExpressionConcretizer() {
    this.setGenerators(new HashMap<String, Generator>());
    this.setVariables(new HashMap<String, ConstantExpression>());
  }

  public Expression makeConcrete(Expression expression) {
    return (Expression) expression.accept(this);
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    if (this.getGenerator(generatorExpression.getGeneratorName()) == null) {
      return generatorExpression;
    } else {
      return this.getGenerator(generatorExpression.getGeneratorName()).generate();
    }
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return this.getVariables().containsKey(variableValue.getVariableName()) 
      ? this.getVariables().get(variableValue.getVariableName())
      : variableValue;
  }
  
  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    Expression index = this.makeConcrete(arrayAccessExpression.getIndex());
    Expression array = this.makeConcrete(arrayAccessExpression.getArrayExpression());
    return new ArrayAccessExpression((ArrayHolder) array, index);
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

  public void set(Generator generator) {
    this.getGenerators().put(generator.getName(), generator);
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
