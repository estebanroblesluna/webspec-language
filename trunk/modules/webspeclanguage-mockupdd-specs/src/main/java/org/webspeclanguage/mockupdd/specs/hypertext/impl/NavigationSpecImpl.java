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
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;

/**
 * Represents a navigation triggered by a {@link TriggerWidget} to an existing {@link Page}.
 * Before executing the navigation, a set of {@link ActionSpec} are executed; then, the navigation
 * is done carrying the objects specified by the transfers List
 * 
 * @author Jose Matias Rivero
 */
public class NavigationSpecImpl extends WidgetActionsSpecImpl implements NavigationSpec {

  private Page to;
  private List<ObjectTransferSpec> transfers;
  
  public NavigationSpecImpl(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers) {
    super(trigger,actions);
    this.setTo(to);
    this.setTransfers(transfers);
  }

  public Page getTo() {
    return to;
  }

  private void setTo(Page to) {
    this.to = to;
  }

  public List<ObjectTransferSpec> getTransfers(){
    return transfers;
  }
  
  public void setTransfers(List<ObjectTransferSpec> transfers){
    this.transfers = transfers;
  }
  
  public void addTransfer(ObjectTransferSpec transfer){
    this.getTransfers().add(transfer);
  }
  
}
