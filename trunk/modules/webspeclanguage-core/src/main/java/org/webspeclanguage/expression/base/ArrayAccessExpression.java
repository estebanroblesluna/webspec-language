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
 * An expression class for "${var}[index]"
 * 
 * @author Esteban Robles Luna
 */
public class ArrayAccessExpression extends AbstractExpression {

  private Expression expression;
  private NumberConstant index;

  public ArrayAccessExpression(Expression expression, NumberConstant index) {
    this.setExpression(expression);
    this.setIndex(index);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitArrayAccessExpression(this);
  }

  public Expression getExpression() {
    return expression;
  }

  private void setExpression(Expression expression) {
    this.expression = expression;
  }

  public NumberConstant getIndex() {
    return index;
  }

  private void setIndex(NumberConstant index) {
    this.index = index;
  }
}
