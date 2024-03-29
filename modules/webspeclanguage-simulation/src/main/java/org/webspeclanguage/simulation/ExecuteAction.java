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
package org.webspeclanguage.simulation;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.expression.core.Expression;

/**
 * Represents an action executed in the browser
 * 
 * @author Esteban Robles Luna
 */
public class ExecuteAction implements SimulationItem {

  private String actionName;
  private List<Expression> arguments;
  
  public ExecuteAction(String actionName, List<Expression> arguments) {
    Validate.notNull(actionName);
    Validate.notNull(arguments);
    
    this.actionName = actionName;
    this.arguments = arguments;
  }
  
  public String getActionName() {
    return actionName;
  }

  public List<Expression> getArguments() {
    return arguments;
  }

  public Object accept(SimulationItemVisitor visitor) {
    return visitor.visitExecuteAction(this);
  }
}