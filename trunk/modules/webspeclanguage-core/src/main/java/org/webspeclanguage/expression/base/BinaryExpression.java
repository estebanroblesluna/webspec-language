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
 * An abstract class for binary operations like: "+", "-", etc
 * 
 * @author Esteban Robles Luna
 */
public abstract class BinaryExpression extends AbstractExpression {

  private Expression op1;
  private Expression op2;

  protected BinaryExpression(Expression op1, Expression op2) {
    this.op1 = op1;
    this.op2 = op2;
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
    BinaryExpression o = (BinaryExpression) obj;
    return new EqualsBuilder().append(this.op1, o.op1).append(this.op2, o.op2)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return this.op1.hashCode() + 7 * this.op2.hashCode();
  }

  @SuppressWarnings("unchecked")
  public void getInstancesOfOn(Class<?> aClass, Set instances) {
    super.getInstancesOfOn(aClass, instances);
    this.getOp1().getInstancesOfOn(aClass, instances);
    this.getOp2().getInstancesOfOn(aClass, instances);
  }

  public Expression getOp1() {
    return op1;
  }

  public void setOp1(Expression op1) {
    this.op1 = op1;
  }

  public Expression getOp2() {
    return op2;
  }

  public void setOp2(Expression op2) {
    this.op2 = op2;
  }
}
