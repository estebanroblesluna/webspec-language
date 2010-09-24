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
package org.webspeclanguage.action;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.webspeclanguage.expression.base.BinaryExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;

/**
 * Represents a variable declaration of the form:
 * "String message = Home.message"
 * 
 * @author Esteban Robles Luna
 */
public class LetVariable implements Action {

  private String variableName;
  private Expression expression;
  private ExpressionType type;

  public LetVariable(String variableName, Expression expression, ExpressionType type) {
    Validate.notNull(variableName);
    Validate.notNull(expression);
    
    this.variableName = variableName;
    this.expression = expression;
    this.type = type;
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
    LetVariable o = (LetVariable) obj;
    return new EqualsBuilder()
      .append(this.variableName, o.variableName)
      .append(this.expression, o.expression)
      .append(this.expression, o.expression)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return this.variableName.hashCode() + 7 * this.expression.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(ActionVisitor actionVisitor) {
    return actionVisitor.visitLetVariable(this);
  }

  public String getVariableName() {
    return variableName;
  }

  public Expression getExpression() {
    return expression;
  }

  public ExpressionType getType() {
    return type;
  }
}