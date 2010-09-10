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

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An abstract class for constants like numbers, strings and booleans
 * 
 * @author Esteban Robles Luna
 */
public abstract class ConstantExpression<T> extends AbstractExpression {

  private T constant;

  protected ConstantExpression(T constant) {
    this.setConstant(constant);
  }

  @Override
  public int hashCode() {
    return this.getConstant().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ConstantExpression<?> other = (ConstantExpression<?>) obj;
    return new EqualsBuilder().append(this.getConstant(), other.getConstant())
        .isEquals();
  }
  
  public abstract StringConstant coerceToString();

  public abstract BooleanConstant coerceToBoolean();

  public abstract NumberConstant coerceToNumber();

  public boolean isConstant() {
    return true;
  }

  public T getConstant() {
    return constant;
  }

  private void setConstant(T constant) {
    this.constant = constant;
  }
}
