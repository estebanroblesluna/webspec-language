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
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.api.TransitionSource;
import org.webspeclanguage.api.TransitionTarget;
import org.webspeclanguage.impl.widget.Widget;

/**
 * An operation represents a sequence of actions perform over
 * a sequence of {@link Interaction}s
 * 
 * @author Esteban Robles Luna
 */
public class OperationImpl implements Operation {

  private String name;
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

  public void addItem(Operation operation) {
    Validate.notNull(operation);
    
    OperationReference reference = new OperationReferenceImpl(operation);
    
    this.checkAddition(reference);
    this.items.add(reference);
  }

  private void checkAddition(PathItem item) {
    item.accept(new PathItemVisitor() {
      
      private Object visitTransition(Transition transition) {
        if (!items.isEmpty() && !(items.get(items.size() - 1) instanceof TransitionSource)) {
          throw new IllegalArgumentException("Last element must be a TransitionSource");
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
        return this.visitTransitionTarget(interaction);
      }

      public Object visitOperationReference(OperationReference operationReference) {
        return this.visitTransitionTarget(operationReference);
      }

      private Object visitTransitionTarget(TransitionTarget target) {
        if (items.isEmpty() || !(items.get(items.size() - 1) instanceof Transition)) {
          throw new IllegalArgumentException("Last element must be a Transition or it should be the first element");
        }
        return null;
      }
    });
  }

  public Widget getWidget(String widgetPath) {
    Validate.notNull(widgetPath);
    
    String interactionName = widgetPath.substring(0, widgetPath.indexOf('.'));
    Interaction interaction = this.getInteractionNamed(interactionName);
    
    if (interaction == null) {
      throw new IllegalArgumentException("Interaction: " + interactionName + " does not exists");
    } else {
      return interaction.getWidget(widgetPath.substring(widgetPath.indexOf('.') + 1));
    }
  }
  
  private Interaction getInteractionNamed(String interactionName) {
    for (PathItem item : this.items) {
      if (item instanceof Interaction) {
        Interaction interaction = (Interaction) item;
        if (interaction.getName().equals(interactionName)) {
          return interaction;
        }
      }
    }
    return null;
  }

  public String getName() {
    return this.name;
  }
  
  public List<PathItem> getItems() {
    return Collections.unmodifiableList(this.items);
  }
}
