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

import java.math.BigDecimal;

import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.NumberConstant;

/**
 * A {@link OneOf} generator for {@link NumberConstant}
 * 
 * @author Esteban Robles Luna
 */
public class OneOfNumbers extends OneOf<BigDecimal> {

  public OneOfNumbers(String name, Integer... values) {
    super(name);
    BigDecimal[] numbers = new BigDecimal[values.length];
    for (int i = 0; i < values.length; i++) {
      Integer integer = values[i];
      numbers[i] = new BigDecimal(integer);
    }
    this.setValues(numbers);
  }

  public OneOfNumbers(String name, BigDecimal... values) {
    super(name, values);
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.NUMBER;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected ConstantExpression computeExpression(BigDecimal at) {
    return new NumberConstant(at);
  }
}
