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

import java.util.List;

/**
 * An expression class for "functionName(arg0, arg1 ... , argN)"
 * Represents a function call to the interaction test framework
 * 
 * @author Esteban Robles Luna
 */
public class FunctionCallExpression extends AbstractFunctionCallExpression {

  public FunctionCallExpression(String functionName) {
    super(functionName);
  }

  public FunctionCallExpression(String functionName, Expression... arguments) {
    super(functionName, arguments);
  }

  public FunctionCallExpression(String functionName, List<Expression> arguments) {
    super(functionName, arguments);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitFunctionCallExpression(this);
  }
}
