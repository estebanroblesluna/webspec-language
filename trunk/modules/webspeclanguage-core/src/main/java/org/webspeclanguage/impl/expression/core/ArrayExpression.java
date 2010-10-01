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

import java.math.BigDecimal;

/**
 * An expression class for "[1, 2, 3]"
 * 
 * @author Esteban Robles Luna
 */
public class ArrayExpression extends ConstantExpression<ConstantExpression<?>[]> implements ArrayHolder {

  public ArrayExpression(ConstantExpression<?>... exps) {
    super(exps);
  }
  
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitArrayExpression(this);
  }

  public ConstantExpression<?> get(int index) {
    return this.getConstant()[index];
  }

  @Override
  public BooleanConstant coerceToBoolean() {
    return BooleanConstant.FALSE;
  }

  @Override
  public NumberConstant coerceToNumber() {
    return new NumberConstant(BigDecimal.ZERO);
  }

  @Override
  public StringConstant coerceToString() {
    return new StringConstant("[]");
  }
}
