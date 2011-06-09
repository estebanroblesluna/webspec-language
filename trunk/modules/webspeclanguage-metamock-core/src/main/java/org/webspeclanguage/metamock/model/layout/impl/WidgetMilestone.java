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
package org.webspeclanguage.metamock.model.layout.impl;

import org.webspeclanguage.metamock.model.Widget;

class WidgetMilestone {

  private int position;
  private Widget control;
  private MilestoneType type;

  public enum MilestoneType {
    WidgetStart, WidgetEnd
  }

  public WidgetMilestone(int position, Widget control, MilestoneType type) {
    super();
    this.setPosition(position);
    this.setControl(control);
    this.setType(type);
  }

  private void setPosition(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  private void setControl(Widget control) {
    this.control = control;
  }

  public Widget getControl() {
    return control;
  }

  private void setType(MilestoneType type) {
    this.type = type;
  }

  public MilestoneType getType() {
    return type;
  }

}
