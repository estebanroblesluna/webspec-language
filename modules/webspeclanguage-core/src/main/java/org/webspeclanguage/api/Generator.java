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
package org.webspeclanguage.api;

import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;

/**
 * A generator of {@link ConstantExpression} expressions
 * 
 * @author Esteban Robles Luna
 */
public interface Generator {

  /**
   * Generates a constant expression
   * 
   * @return the constant expression generated
   */
  @SuppressWarnings("unchecked")
  ConstantExpression generate();

  /**
   * @return the type of the expression that will be generated
   */
  ExpressionType getGenerationType();

  /**
   * @return the name of the generator
   */
  String getName();
}
