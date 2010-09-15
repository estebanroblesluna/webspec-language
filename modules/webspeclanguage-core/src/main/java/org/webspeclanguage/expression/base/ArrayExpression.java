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

/**
 * An expression class for "[1, 2, 3]"
 * 
 * @author Esteban Robles Luna
 */
public class ArrayExpression extends ConstantExpression<ConstantExpression<?>[]> {

  public ArrayExpression(ConstantExpression<?>[] exps) {
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NumberConstant coerceToNumber() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public StringConstant coerceToString() {
    // TODO Auto-generated method stub
    return null;
  }
}
