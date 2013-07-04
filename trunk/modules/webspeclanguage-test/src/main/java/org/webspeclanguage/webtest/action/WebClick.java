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
import org.webspeclanguage.webtest.base.WebTestItemVisitor;

/**
 * An action that clicks a field
 * 
 * @author Esteban Robles Luna
 */
public class WebClick implements WebAction {

  private Expression expression;
  
  public WebClick(Expression expression) {
    Validate.notNull(expression);
  }
  
  public Object accept(WebTestItemVisitor webTestItemVisitor) {
    return webTestItemVisitor.visitWebClick(this);
  }

  public Expression getExpression() {
    return expression;
  }
}
