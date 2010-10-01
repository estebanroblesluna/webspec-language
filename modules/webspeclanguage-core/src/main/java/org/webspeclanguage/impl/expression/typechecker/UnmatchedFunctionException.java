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
package org.webspeclanguage.impl.expression.typechecker;

import org.webspeclanguage.impl.expression.core.AbstractFunctionCallExpression;

/**
 * An exception thrown when a function does not match its signature
 * 
 * @author Esteban Robles Luna
 */
public class UnmatchedFunctionException extends RuntimeException {

  private static final long serialVersionUID = -8001629820612293070L;

  private AbstractFunctionCallExpression functionCallExpression;
  private FunctionDefinition definition;
  
  public UnmatchedFunctionException(AbstractFunctionCallExpression functionCallExpression,
      FunctionDefinition definition) {
    this.setDefinition(definition);
    this.setFunctionCallExpression(functionCallExpression);
  }
  
  @Override
  public String getMessage() {
    return "Function " 
    + this.getFunctionCallExpression().getFunctionName() 
    + " does not match " 
    + definition.toString();
  }

  public AbstractFunctionCallExpression getFunctionCallExpression() {
    return functionCallExpression;
  }

  private void setFunctionCallExpression(
      AbstractFunctionCallExpression functionCallExpression) {
    this.functionCallExpression = functionCallExpression;
  }

  public FunctionDefinition getDefinition() {
    return definition;
  }

  private void setDefinition(FunctionDefinition definition) {
    this.definition = definition;
  }
}
