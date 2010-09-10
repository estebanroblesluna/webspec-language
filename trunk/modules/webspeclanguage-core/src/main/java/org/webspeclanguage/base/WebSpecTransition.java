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
package org.webspeclanguage.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.action.Action;
import org.webspeclanguage.action.ActionParser;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.utils.ExpressionUtils;

/**
 * An abstract class for any transition between interactions
 * 
 * @author Esteban Robles Luna
 */
public abstract class WebSpecTransition implements WebSpecPathItem {

  private WebSpecInteraction from;
  private WebSpecInteraction to;
  private Expression precondition;
  private List<Action> actions;

  protected WebSpecTransition(WebSpecInteraction from, WebSpecInteraction to) {
    Validate.notNull(from);
    Validate.notNull(to);
    
    this.from = from;
    this.to = to;
    this.actions = new ArrayList<Action>();
  }

  public void addAction(Action action) {
    Validate.notNull(action);
    
    this.getActions().add(action);
  }

  public void setActions(String actionsString) {
    Validate.notNull(actionsString);
    
    List<Action> actions = ActionParser.getActions(actionsString, this.getDiagram());
    for (Action action : actions) {
      this.addAction(action);
    }
  }

  public WebSpecInteraction getFrom() {
    return from;
  }

  public WebSpecInteraction getTo() {
    return to;
  }

  public Expression getPrecondition() {
    return precondition;
  }

  public void setPrecondition(Expression precondition) {
    this.precondition = precondition;
  }

  public void setPrecondition(String input) {
    this.setPrecondition(ExpressionUtils.getExpression(input, getDiagram()));
  }

  public List<Action> getActions() {
    return actions;
  }

  protected WebSpecDiagram getDiagram() {
    return this.getFrom().getDiagram();
  }
}