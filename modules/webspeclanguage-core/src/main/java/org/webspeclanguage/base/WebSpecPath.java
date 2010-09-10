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
public class WebSpecPath {

  private List<WebSpecPathItem> items;
  private String name;
  private Map<WebSpecPathItem, Integer> occurences;
  private int cyclesCount;

  public WebSpecPath(WebSpecInteraction startingInteraction) {
    Validate.notNull(startingInteraction);
    
    this.items = new ArrayList<WebSpecPathItem>();
    this.occurences = new HashMap<WebSpecPathItem, Integer>();
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
    WebSpecInteraction previousInteraction = this.getInteraction(0);
    String computedName = this.getInteraction(0).getName();
    
    if (this.getItems().size() != 1) {
      for (int i = 1; i < this.getItems().size(); i = i + 2) {
        WebSpecTransition currentTransition = (WebSpecTransition) this.getItems().get(i);
        WebSpecInteraction currentInteraction = (WebSpecInteraction) this.getItems().get(i + 1);
        List<WebSpecNavigation> navigations = previousInteraction.navigationsTo(currentInteraction);
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

  private void basicAddItem(WebSpecPathItem item) {
    this.getItems().add(item);
    
    if (this.occurences.containsKey(item)) {
      this.occurences.put(item, this.occurences.get(item) + 1);
      this.cyclesCount = Math.max(this.getCyclesCount(), this.occurrencesOf(item) - 1);
    } else {
      this.occurences.put(item, 1);
    }
  }

  public WebSpecPath extendWith(WebSpecTransition transition, WebSpecInteraction to) {
    Validate.notNull(transition);
    Validate.notNull(to);
    
    WebSpecPath newPath = this.copy();
    newPath.basicAddItem(transition);
    newPath.basicAddItem(to);
    return newPath;
  }

  private WebSpecPath copy() {
    WebSpecPath newPath = new WebSpecPath((WebSpecInteraction) this.getItems().get(0));
    for (int i = 1; i < this.getItems().size(); i++) {
      newPath.basicAddItem(this.getItems().get(i));
    }
    return newPath;
  }

  public List<WebSpecPathItem> getItems() {
    return items;
  }

  public WebSpecInteraction getInteraction(int i) {
    return (WebSpecInteraction) this.getItems().get(i * 2);
  }

  public WebSpecNavigation getNavigation(int i) {
    return (WebSpecNavigation) this.getItems().get((i * 2) + 1);
  }

  public boolean contains(WebSpecPathItem item) {
    return this.getItems().contains(item);
  }

  public int occurrencesOf(WebSpecPathItem item) {
    return this.occurences.get(item);
  }

  public int length() {
    return this.getItems().size();
  }

  public boolean hasCycles() {
    for (WebSpecPathItem item : this.occurences.keySet()) {
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