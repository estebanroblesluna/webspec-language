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

import java.math.BigDecimal;
import java.math.MathContext;

import org.webspeclanguage.api.Generator;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.core.NumberConstant;

/**
 * A {@link Generator} for {@link NumberConstant} 
 * for an interval of numbers
 * 
 * @author Esteban Robles Luna
 */
public class UniformNumberGenerator extends AbstractGenerator {

  private BigDecimal min;
  private BigDecimal max;

  public UniformNumberGenerator(String name, int min, int max) {
    this(name, new BigDecimal(min), new BigDecimal(max));
  }

  public UniformNumberGenerator(String name, BigDecimal min, BigDecimal max) {
    super(name);
    if (min.compareTo(max) > 0) {
      throw new IllegalArgumentException("Illegal interval");
    }
    
    this.min = min;
    this.max = max;
  }

  @SuppressWarnings("unchecked")
  public ConstantExpression generate() {
    BigDecimal length = this.getMax().subtract(this.getMin());
    BigDecimal slice = length.multiply(new BigDecimal(Math.random()),
        MathContext.DECIMAL64);
    return new NumberConstant(this.getMin().add(slice));
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.NUMBER;
  }

  private BigDecimal getMin() {
    return min;
  }

  private BigDecimal getMax() {
    return max;
  }
}
