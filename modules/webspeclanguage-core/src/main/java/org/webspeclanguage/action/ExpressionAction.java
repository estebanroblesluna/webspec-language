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
import org.webspeclanguage.expression.base.Expression;

/**
 * Represents an action in the form of: "click(Home.search);"
 * 
 * @author Esteban Robles Luna
 */
public class ExpressionAction implements Action {

  private Expression expression;

  public ExpressionAction(Expression expression) {
    Validate.notNull(expression);
    
    this.expression = expression;
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(ActionVisitor actionVisitor) {
    return actionVisitor.visitExpressionAction(this);
  }

  public Expression getExpression() {
    return expression;
  }
}