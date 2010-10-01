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
package org.webspeclanguage.webtest.action;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;

/**
 * A variable declaration inside the test obtained (generally) from a
 * non constant expression
 * 
 * @author Esteban Robles Luna
 */
public class WebCreateVariableFromExpression implements WebAction {

  private String variableName;
  private Expression expression;
  private ExpressionType type;

  public WebCreateVariableFromExpression(String variableName, Expression expression, ExpressionType type) {
    Validate.notNull(variableName);
    Validate.notNull(expression);
    Validate.notNull(type);
    
    this.variableName = variableName;
    this.expression = expression;
    this.type = type;
  }

  public Object accept(WebTestItemVisitor webTestItemVisitor) {
    return webTestItemVisitor.visitWebCreateVariableFromExpression(this);
  }

  public String getVariableName() {
    return variableName;
  }

  public Expression getExpression() {
    return expression;
  }

  public ExpressionType getType() {
    return type;
  }
}
