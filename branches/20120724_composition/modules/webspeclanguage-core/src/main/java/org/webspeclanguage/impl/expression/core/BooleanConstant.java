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
 * An expression class for "true" and "false"
 * 
 * @author Esteban Robles Luna
 */
public class BooleanConstant extends ConstantExpression<Boolean> {

  public static final BooleanConstant TRUE = new BooleanConstant(true);
  public static final BooleanConstant FALSE = new BooleanConstant(false);

  public BooleanConstant(Boolean constant) {
    super(constant);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitBooleanConstant(this);
  }

  @Override
  public BooleanConstant coerceToBoolean() {
    return this;
  }

  @Override
  public NumberConstant coerceToNumber() {
    if (this.equals(BooleanConstant.TRUE)) {
      return new NumberConstant(BigDecimal.ONE);
    } else {
      return new NumberConstant(BigDecimal.ZERO);
    }
  }

  @Override
  public StringConstant coerceToString() {
    return new StringConstant(this.getConstant().toString());
  }
}
