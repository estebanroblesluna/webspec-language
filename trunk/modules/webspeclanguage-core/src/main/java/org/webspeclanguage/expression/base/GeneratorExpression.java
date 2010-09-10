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

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An expression class for "$generatorName$"
 * 
 * @author Esteban Robles Luna
 */
public class GeneratorExpression extends AbstractExpression {

  private String generatorName;

  public GeneratorExpression(String generatorName) {
    this.setGeneratorName(generatorName);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    GeneratorExpression o = (GeneratorExpression) obj;
    return new EqualsBuilder().append(this.generatorName, o.generatorName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return this.generatorName.hashCode();
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitGeneratorExpression(this);
  }

  public String getGeneratorName() {
    return generatorName;
  }

  private void setGeneratorName(String generatorName) {
    this.generatorName = generatorName;
  }
}
