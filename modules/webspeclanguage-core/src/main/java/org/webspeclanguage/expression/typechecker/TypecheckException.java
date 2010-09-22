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
package org.webspeclanguage.expression.typechecker;

import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;

/**
 * An exception thrown when a typechecking error is found
 * 
 * @author Esteban Robles Luna
 */
public class TypecheckException extends RuntimeException {

  private static final long serialVersionUID = 1768273040830838419L;

  private Expression expression;
  private ExpressionType expectedType;
  private ExpressionType actualType;
  
  public TypecheckException(Expression expression, ExpressionType expectedType, ExpressionType actualType) {
    this.expression = expression;
    this.expectedType = expectedType;
    this.actualType = actualType;
  }

  @Override
  public String getMessage() {
    return "Expression: " + this.getExpression().toString() + " is of type "
        + this.getActualType() + " but should be of type "
        + this.getExpectedType();
  }

  public Expression getExpression() {
    return expression;
  }

  public ExpressionType getExpectedType() {
    return expectedType;
  }

  public ExpressionType getActualType() {
    return actualType;
  }
}
