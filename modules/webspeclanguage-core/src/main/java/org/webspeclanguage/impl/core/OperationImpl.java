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
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.Operation;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.api.TransitionSource;
import org.webspeclanguage.api.TransitionTarget;

/**
 * An operation represents a sequence of actions perform over
 * a sequence of {@link InteractionImpl}s
 * 
 * @author Esteban Robles Luna
 */
public class OperationImpl implements Operation {

  private String name;
  private List<Transition> forwardTransitions;
  private List<Transition> backwardTransitions;

  private List<PathItem> items;
  
  public OperationImpl(String name) {
    Validate.notNull(name);
    
    this.name = name;
    this.items = new ArrayList<PathItem>();
  }
  
  public void addItem(PathItem item) {
    Validate.notNull(item);
    
    this.checkAddition(item);
    this.items.add(item);
  }

  private void checkAddition(PathItem item) {
    item.accept(new PathItemVisitor() {
      
      private Object visitTransition(Transition transition) {
        if (!items.isEmpty() && !(items.get(items.size() - 1) instanceof TransitionSource)) {
          throw new IllegalArgumentException("Last element must be a transition or it should be the first element");
        }
        return null;
      }

      public Object visitRichBehavior(RichBehavior richBehavior) {
        return this.visitTransition(richBehavior);
      }
      
      public Object visitNavigation(Navigation navigation) {
        return this.visitTransition(navigation);
      }
      
      public Object visitInteraction(Interaction interaction) {
        if (!items.isEmpty() && !(items.get(items.size() - 1) instanceof TransitionImpl)) {
          throw new IllegalArgumentException("Last element must be a transition or it should be the first element");
        }
        return null;
      }

      public Object visitOperation(Operation operation) {
        // TODO Auto-generated method stub
        return null;
      }
    });
  }

  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitOperation(this);
  }

  public String getName() {
    return this.name;
  }
  
  public List<PathItem> getItems() {
    return Collections.unmodifiableList(this.items);
  }

  public void addForwardTransition(Transition transition) {
    this.forwardTransitions.add(transition);
  }

  public void addBackwardTransition(Transition transition) {
    this.backwardTransitions.add(transition);
  }
  
  public List<Transition> getForwardTransitions() {
    return Collections.unmodifiableList(this.forwardTransitions);
  }

  public List<Transition> getBackwardTransitions() {
    return Collections.unmodifiableList(this.backwardTransitions);
  }
  
  public <T extends Transition> List<T> getForwardTransitionsTo(TransitionTarget target, Class<T> theClass) {
    List<T> result = new ArrayList<T>();
    for (Transition transition : this.getForwardTransitions()) {
      if (theClass.isAssignableFrom(transition.getClass())
          && transition.getTarget().equals(target)) {
        result.add((T) transition);
      }
    }
    return result;
  }
}
