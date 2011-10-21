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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;

/**
 * Represents a navigation triggered by a {@link TriggerWidget} to an existing {@link Page}.
 * Before executing the navigation, a set of {@link ActionSpec} are executed; then, the navigation
 * is done carrying the objects specified by {@link ObjectTransferSpec}s
 * 
 * @author Jose Matias Rivero
 */
public class NavigationSpec {

  private TriggerWidget from;
  private Page to;
  private List<ObjectTransferSpec> objectTransfers;
  private List<ActionSpec> actions;

  public NavigationSpec(TriggerWidget from, Page to) {
    super();
    this.setFrom(from);
    this.setTo(to);
    this.setObjectTransfers(new ArrayList<ObjectTransferSpec>());
    this.setActions(new ArrayList<ActionSpec>());
  }

  public TriggerWidget getFrom() {
    return from;
  }

  
  private void setFrom(TriggerWidget from) {
    this.from = from;
  }

  public Page getTo() {
    return to;
  }

  private void setTo(Page to) {
    this.to = to;
  }

  public List<ObjectTransferSpec> getObjectTransfers() {
    return Collections.unmodifiableList(objectTransfers);
  }

  private void setObjectTransfers(List<ObjectTransferSpec> objectTransfers) {
    this.objectTransfers = objectTransfers;
  }

  public void addObjectTransfer(ObjectTransferSpec objectTransfer) {
    this.objectTransfers.add(objectTransfer);
  }

  public List<ActionSpec> getActions() {
    return Collections.unmodifiableList(actions);
  }

  private void setActions(List<ActionSpec> actions) {
    this.actions = actions;
  }
  
  public void addActionSpec(ActionSpec actionSpec) {
    this.actions.add(actionSpec);
  }

}
