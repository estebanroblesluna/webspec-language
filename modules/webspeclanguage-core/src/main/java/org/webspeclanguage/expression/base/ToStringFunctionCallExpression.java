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

import java.util.List;

/**
 * An expression class for "toString(arg0, arg1 ... , argN)"
 * 
 * @author Esteban Robles Luna
 */
public class ToStringFunctionCallExpression extends AbstractFunctionCallExpression {

  public ToStringFunctionCallExpression(List<Expression> arguments) {
    super("toString", arguments);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitToStringFunctionCallExpression(this);
  }
}
