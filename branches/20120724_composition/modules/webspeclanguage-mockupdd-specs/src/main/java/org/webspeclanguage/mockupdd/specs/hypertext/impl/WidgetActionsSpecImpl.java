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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import java.util.List;

import org.webspeclanguage.mockupdd.specs.hypertext.ActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.WidgetActionsSpec;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;

/**
 * Represents a set of {@link ActionSpec} associated to a {@link TriggerWidget}
 * 
 * @author Franco Giacosa
 */
public class WidgetActionsSpecImpl implements WidgetActionsSpec {

  private List<ActionSpec> actions;
  private TriggerWidget trigger;
  
  public WidgetActionsSpecImpl(TriggerWidget trigger, List<ActionSpec> actions) {
    super();
    this.setTrigger(trigger);
    this.setActions(actions);
  }

  public List<ActionSpec> getActions() {
    return actions;
  }
  
  public void setActions(List<ActionSpec> actions) {
    this.actions = actions;
  }
  
  public void addAction(ActionSpec action){
    this.getActions().add(action);
  }
  
  public TriggerWidget getTrigger() {
    return trigger;
  }
  
  public void setTrigger(TriggerWidget trigger) {
    this.trigger = trigger;
  }
}
