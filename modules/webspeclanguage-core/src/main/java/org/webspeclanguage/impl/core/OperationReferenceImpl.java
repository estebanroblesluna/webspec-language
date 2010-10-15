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
import org.webspeclanguage.api.Operation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.api.TransitionTarget;

/**
 * A reference to an {@link Operation}
 * 
 * @author Esteban Robles Luna
 */
public class OperationReferenceImpl implements OperationReference {

  private Operation operation;
  private List<Transition> forwardTransitions;
  private List<Transition> backwardTransitions;

  public OperationReferenceImpl(Operation operation) {
    Validate.notNull(operation);
    
    this.operation = operation;
    this.forwardTransitions = new ArrayList<Transition>();
    this.backwardTransitions = new ArrayList<Transition>();
  }
  
  public Operation getReference() {
    return this.operation;
  }

  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitOperationReference(this);
  }
  
  public void addForwardTransition(Transition transition) {
    Validate.notNull(transition);
    
    this.forwardTransitions.add(transition);
  }

  public void addBackwardTransition(Transition transition) {
    Validate.notNull(transition);

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

  public String getName() {
    return this.operation.getName() + "-ref";
  }
}