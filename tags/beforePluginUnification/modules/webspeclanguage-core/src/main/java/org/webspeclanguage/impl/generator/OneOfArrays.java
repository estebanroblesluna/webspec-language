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
package org.webspeclanguage.impl.generator;

import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;

/**
 * A {@link OneOf} generator for {@link ArrayExpression}
 * 
 * @author Esteban Robles Luna
 */
public class OneOfArrays extends OneOf<ArrayExpression> {

  public OneOfArrays(String name, ArrayExpression... values) {
    super(name, values);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected ConstantExpression computeExpression(ArrayExpression aT) {
    return aT;
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.ARRAY;
  }
}
