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

import org.apache.commons.lang.Validate;
import org.webspeclanguage.expression.base.ConstantExpression;

/**
 * An abstract class for generating a {@link ConstantExpression}
 * from a set of possible values
 * 
 * @author Esteban Robles Luna
 */
public abstract class OneOf<T> extends AbstractGenerator {

  private T[] values;

  protected OneOf(String name, T... values) {
    super(name);
    
    Validate.notNull(values);
    this.values = values.clone();
  }

  @SuppressWarnings("unchecked")
  public ConstantExpression generate() {
    int index = (int) Math.floor(Math.random() * this.values.length);
    return this.computeExpression(this.getValues()[index]);
  }

  @SuppressWarnings("unchecked")
  protected abstract ConstantExpression computeExpression(T aT);

  private T[] getValues() {
    return values;
  }

  protected void setValues(T[] values) {
    Validate.notNull(values);
    this.values = values.clone();
  }
}
