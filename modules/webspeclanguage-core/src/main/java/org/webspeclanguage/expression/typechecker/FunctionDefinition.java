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
package org.webspeclanguage.expression.typechecker;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.AbstractFunctionCallExpression;

/**
 * Represents a signature of a function
 * 
 * @author Esteban Robles Luna
 */
public class FunctionDefinition {

  private String functionName;
  private List<Matcher> matchers;
  private ExpressionType returnType;
  
  public FunctionDefinition(String functionName, ExpressionType returnType, Matcher... matchers) {
    this.setFunctionName(functionName);
    this.setReturnType(returnType);
    this.setMatchers(new ArrayList<Matcher>());
    if (matchers != null) {
      for (Matcher matcher : matchers) {
        this.getMatchers().add(matcher);
      }
    }
  }

  @Override
  public String toString() {
    return this.getFunctionName() + "(" + this.argumentsToString() + ")";
  }


  private String argumentsToString() {
    if (this.getMatchers().isEmpty()) {
      return "";
    } else {
      String v = "";
      for (Matcher matcher : this.getMatchers()) {
        v += matcher.toString() + ", ";
      }
      return v.substring(0, v.length() - 2);
    }
  }

  public boolean matches(AbstractFunctionCallExpression callExpression, ExpressionTypechecker typeChecker) {
    return 
      this.hasSameSizeOfArguments(callExpression)
      && this.matchesFunctionName(callExpression)
      && this.matchesArguments(callExpression, typeChecker);
  }
  
  private boolean matchesArguments(AbstractFunctionCallExpression callExpression, ExpressionTypechecker typeChecker) {
    int index = 0;
    for (Expression expression : callExpression.getArguments()) {
      if (!this.getMatchers().get(index).matches(expression, typeChecker)) {
        return false;
      }
      index++;
    }
    return true;
  }

  private boolean matchesFunctionName(AbstractFunctionCallExpression callExpression) {
    return this.getFunctionName().equals(callExpression.getFunctionName());
  }

  private boolean hasSameSizeOfArguments(AbstractFunctionCallExpression callExpression) {
    return callExpression.getArguments().size() == this.getMatchers().size();
  }

  public String getFunctionName() {
    return functionName;
  }

  private void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  private List<Matcher> getMatchers() {
    return matchers;
  }

  private void setMatchers(List<Matcher> matchers) {
    this.matchers = matchers;
  }

  public ExpressionType getReturnType() {
    return returnType;
  }

  private void setReturnType(ExpressionType returnType) {
    this.returnType = returnType;
  }
}
