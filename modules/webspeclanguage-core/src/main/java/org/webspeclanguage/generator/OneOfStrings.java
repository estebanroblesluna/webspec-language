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
package org.webspeclanguage.generator;

import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.StringConstant;

/**
 * A {@link OneOf} generator for {@link StringConstant}
 * 
 * @author Esteban Robles Luna
 */
public class OneOfStrings extends OneOf<String> {

  public OneOfStrings(String name, String... values) {
    super(name, values);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected ConstantExpression computeExpression(String at) {
    return new StringConstant(at);
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.STRING;
  }
}
