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
package org.webspeclanguage.impl.expression.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An abstract class for function calls
 * 
 * @author Esteban Robles Luna
 */
public abstract class AbstractFunctionCallExpression extends AbstractExpression {

  public static final String CLICK = "click";
  public static final String TYPE = "type";

  private String functionName;
  private List<Expression> arguments;

  protected AbstractFunctionCallExpression(String functionName, Expression... arguments) {
    this.setFunctionName(functionName);
    this.setArguments(new ArrayList<Expression>());
    if (arguments != null) {
      for (Expression expression : arguments) {
        this.getArguments().add(expression);
      }
    }
  }

  protected AbstractFunctionCallExpression(String functionName, List<Expression> arguments) {
    this.setFunctionName(functionName);
    this.setArguments(new ArrayList<Expression>());
    if (arguments != null) {
      for (Expression expression : arguments) {
        this.getArguments().add(expression);
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    AbstractFunctionCallExpression o = (AbstractFunctionCallExpression) obj;
    return new EqualsBuilder()
      .append(this.functionName, o.functionName)
      .append(this.arguments, o.arguments)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return this.functionName.hashCode() + 7 * this.arguments.hashCode();
  }

  public void addArgument(Expression expression) {
    this.getArguments().add(expression);
  }

  @SuppressWarnings("unchecked")
  public void getInstancesOfOn(Class<?> aClass, Set instances) {
    super.getInstancesOfOn(aClass, instances);
    for (Expression exp : this.getArguments()) {
      exp.getInstancesOfOn(aClass, instances);
    }
  }

  public String getFunctionName() {
    return functionName;
  }

  private void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  public List<Expression> getArguments() {
    return arguments;
  }

  private void setArguments(List<Expression> arguments) {
    this.arguments = arguments;
  }
}
