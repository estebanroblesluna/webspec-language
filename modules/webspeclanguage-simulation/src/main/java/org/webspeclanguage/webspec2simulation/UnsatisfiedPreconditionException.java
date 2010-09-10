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
package org.webspeclanguage.webspec2simulation;

import java.util.Map;

import org.webspeclanguage.base.WebSpecTransition;
import org.webspeclanguage.expression.base.ConstantExpression;

/**
 * An exception thrown when a precondition is not satisfied during path
 * computing
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class UnsatisfiedPreconditionException extends RuntimeException {

  private static final long serialVersionUID = -2769170119849199990L;

  private WebSpecTransition transition;
  private Map<String, ConstantExpression> variables;

  public UnsatisfiedPreconditionException(WebSpecTransition transition, Map<String, ConstantExpression> variables) {
    this.transition = transition;
    this.variables = variables;
  }

  public WebSpecTransition getTransition() {
    return transition;
  }

  public Map<String, ConstantExpression> getVariables() {
    return variables;
  }
}