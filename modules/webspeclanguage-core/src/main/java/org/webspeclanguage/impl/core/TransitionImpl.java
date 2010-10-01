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
package org.webspeclanguage.impl.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.api.TransitionSource;
import org.webspeclanguage.api.TransitionTarget;
import org.webspeclanguage.impl.action.ActionParser;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.utils.ExpressionUtils;

/**
 * An abstract class for any transition between interactions
 * 
 * @author Esteban Robles Luna
 */
public abstract class TransitionImpl implements Transition {

  private TransitionSource source;
  private TransitionTarget target;
  private Expression precondition;
  private List<Action> actions;

  protected TransitionImpl(TransitionSource source, TransitionTarget target) {
    Validate.notNull(source);
    Validate.notNull(target);
    
    this.source = source;
    this.target = target;
    this.actions = new ArrayList<Action>();
    
    this.source.addForwardTransition(this);
    this.target.addBackwardTransition(this);
  }

  public void addAction(Action action) {
    Validate.notNull(action);
    
    this.getActions().add(action);
  }

  public void setActions(String actionsString) {
    Validate.notNull(actionsString);
    
    List<Action> parsedActions = ActionParser.getActions(actionsString, this.getDiagram());
    for (Action action : parsedActions) {
      this.addAction(action);
    }
  }

  public TransitionSource getSource() {
    return this.source;
  }

  public TransitionTarget getTarget() {
    return this.target;
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

  protected Diagram getDiagram() {
    //TODO fix this
    return ((InteractionImpl) this.getSource()).getDiagram();
  }
}