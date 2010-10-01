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
package org.webspeclanguage.metamock.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.metamock.model.UIControl;

/**
 * Represents a list of widgets
 * 
 * @author Jose Matias Rivero
 */
public class ControlList {

  private List<UIControl> controls;

  public ControlList(List<UIControl> controls) {
    this.setControls(controls);
  }

  public ControlList(UIControl... controls) {
    this.setControls(new ArrayList<UIControl>(Arrays.asList(controls)));
  }

  final void setControls(List<UIControl> controls) {
    this.controls = controls;
  }

  public List<UIControl> getControls() {
    return controls;
  }

  public UIControl getControlAt(Integer i) {
    return this.getControls().get(i);
  }

  public void addControl(UIControl c) {
    this.getControls().add(c);
  }

  public Integer controlCount() {
    return this.getControls().size();
  }

  public ControlList copy() {
    List<UIControl> l = new ArrayList<UIControl>();
    for (UIControl c : this.getControls()) {
      l.add((UIControl) c.copy());
    }
    return new ControlList(l);
  }

}
