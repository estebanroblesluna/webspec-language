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

import org.webspeclanguage.api.Generator;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;

/**
 * A {@link Generator} for {@link BooleanConstant}
 * 
 * @author Esteban Robles Luna
 */
public class BooleanGenerator extends AbstractGenerator {

  public BooleanGenerator(String name) {
    super(name);
  }

  @SuppressWarnings("unchecked")
  public ConstantExpression generate() {
    if (Math.random() < 0.5d) {
      return BooleanConstant.FALSE;
    } else {
      return BooleanConstant.TRUE;
    }
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.BOOLEAN;
  }
}