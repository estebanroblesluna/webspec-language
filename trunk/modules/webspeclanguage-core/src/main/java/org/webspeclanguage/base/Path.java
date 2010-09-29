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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

/**
 * A path is a sequence of interactions and transitions of a diagram.
 * 
 * @author Esteban Robles Luna
 */
public class Path {

  private List<PathItem> items;
  private String name;
  private Map<PathItem, Integer> occurences;
  private int cyclesCount;

  public Path(Interaction startingInteraction) {
    Validate.notNull(startingInteraction);
    
    this.items = new ArrayList<PathItem>();
    this.occurences = new HashMap<PathItem, Integer>();
    this.cyclesCount = 0;
    
    this.basicAddItem(startingInteraction);
  }

  public String toString() {
    return this.getName();
  }

  public String getName() {
    if (this.name == null) {
      this.name = this.getNameUsing("_");
    }
    return this.name;
  }

  public String getNameUsing(String separator) {
    Interaction previousInteraction = this.getInteraction(0);
    String computedName = this.getInteraction(0).getName();
    
    if (this.getItems().size() != 1) {
      for (int i = 1; i < this.getItems().size(); i = i + 2) {
        Transition currentTransition = (Transition) this.getItems().get(i);
        Interaction currentInteraction = (Interaction) this.getItems().get(i + 1);
        List<Navigation> navigations = previousInteraction.navigationsTo(currentInteraction);
        if (navigations.size() == 1) {
          computedName += separator + currentInteraction.getName();
        } else {
          computedName += separator + navigations.indexOf(currentTransition)
              + currentInteraction.getName();
        }
        previousInteraction = currentInteraction;
      }
    }
    
    return computedName.replaceAll(" ", "");
  }

  private void basicAddItem(PathItem item) {
    this.getItems().add(item);
    
    if (this.occurences.containsKey(item)) {
      this.occurences.put(item, this.occurences.get(item) + 1);
      this.cyclesCount = Math.max(this.cyclesCount, this.occurrencesOf(item) - 1);
    } else {
      this.occurences.put(item, 1);
    }
  }

  public Path extendWith(Transition transition, Interaction to) {
    Validate.notNull(transition);
    Validate.notNull(to);
    
    Path newPath = this.copy();
    newPath.basicAddItem(transition);
    newPath.basicAddItem(to);
    return newPath;
  }

  private Path copy() {
    Path newPath = new Path((Interaction) this.getItems().get(0));
    for (int i = 1; i < this.getItems().size(); i++) {
      newPath.basicAddItem(this.getItems().get(i));
    }
    return newPath;
  }

  public List<PathItem> getItems() {
    return items;
  }

  public Interaction getInteraction(int i) {
    return (Interaction) this.getItems().get(i * 2);
  }

  public Navigation getNavigation(int i) {
    return (Navigation) this.getItems().get((i * 2) + 1);
  }

  public boolean contains(PathItem item) {
    return this.getItems().contains(item);
  }

  public int occurrencesOf(PathItem item) {
    return this.occurences.get(item);
  }

  public int length() {
    return this.getItems().size();
  }

  public boolean hasCycles() {
    for (PathItem item : this.occurences.keySet()) {
      if (this.occurences.get(item) > 1) {
        return true;
      }
    }
    return false;
  }

  public int getCyclesCount() {
    return cyclesCount;
  }
}