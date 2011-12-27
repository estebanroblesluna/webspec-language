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
package org.webspeclanguage.mockupdd.specs.hypertext;

import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;

/**
 * @author Franco Giacosa
 */
public interface WidgetActionsSpec {

  public abstract List<ActionSpec> getActions();

  public abstract void setActions(List<ActionSpec> actions);

  public abstract void addAction(ActionSpec action);

  public abstract TriggerWidget getTrigger();

  public abstract void setTrigger(TriggerWidget trigger);

}