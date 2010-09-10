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

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An expression class for "!"
 * 
 * @author Esteban Robles Luna
 */
public class NotExpression extends AbstractExpression {

  private Expression expression;

  public NotExpression(Expression expression) {
    this.setExpression(expression);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitNotExpression(this);
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
    NotExpression o = (NotExpression) obj;
    return new EqualsBuilder()
        .append(this.expression, o.expression)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return this.expression.hashCode();
  }

  @SuppressWarnings("unchecked")
  public void getInstancesOfOn(Class<?> aClass, Set instances) {
    super.getInstancesOfOn(aClass, instances);
    this.getExpression().getInstancesOfOn(aClass, instances);
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }
}
