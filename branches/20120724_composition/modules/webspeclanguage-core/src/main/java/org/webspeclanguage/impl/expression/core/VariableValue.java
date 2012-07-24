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

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * An expression class for "${variableName}"
 * 
 * @author Esteban Robles Luna
 */
public class VariableValue extends AbstractExpression implements ArrayHolder {

  private String variableName;

  public VariableValue(String variableName) {
    this.setVariableName(variableName);
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
    VariableValue o = (VariableValue) obj;
    return new EqualsBuilder().append(this.variableName, o.variableName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return this.getVariableName().hashCode();
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitVariableValue(this);
  }

  public String getVariableName() {
    return variableName;
  }

  private void setVariableName(String variableName) {
    this.variableName = variableName;
  }
}
