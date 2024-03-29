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

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An expression class for "${var}[index]"
 * 
 * @author Esteban Robles Luna
 */
public class ArrayAccessExpression extends AbstractExpression {

  private ArrayHolder arrayExpression;
  private Expression index;

  public ArrayAccessExpression(ArrayHolder arrayExpression, Expression index) {
    this.arrayExpression = arrayExpression;
    this.index = index;
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
    ArrayAccessExpression o = (ArrayAccessExpression) obj;
    return new EqualsBuilder()
      .append(this.arrayExpression, o.arrayExpression)
      .append(this.index, o.index)
      .isEquals();
  }
  
  @Override
  public int hashCode() {
    return this.arrayExpression.hashCode() + 7 * this.index.hashCode();
  }
  
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitArrayAccessExpression(this);
  }

  public ArrayHolder getArrayExpression() {
    return arrayExpression;
  }

  public Expression getIndex() {
    return index;
  }
}
