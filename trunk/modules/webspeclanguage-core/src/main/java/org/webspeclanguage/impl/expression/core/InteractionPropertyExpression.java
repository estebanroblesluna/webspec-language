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

import org.webspeclanguage.api.Interaction;

/**
 * An expression class for "Home.title"
 * 
 * @author Esteban Robles Luna
 */
public class InteractionPropertyExpression extends AbstractExpression {

  private Interaction interaction;
  private String property;
  
  public InteractionPropertyExpression(Interaction interaction, String property) {
    this.interaction = interaction;
    this.property = property;
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitInteractionPropertyExpression(this);
  }

  public Interaction getInteraction() {
    return interaction;
  }
  
  public String getProperty() {
    return property;
  }
}
