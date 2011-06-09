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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.metamock.model.Widget;

/**
 * Represents a list of widgets
 * 
 * @author Jose Matias Rivero
 */
public class ControlList {

  private List<Widget> controls;
 
  public ControlList(List<Widget> controls) {
    this.setControls(controls);
  }

  public ControlList(Widget... controls) {
    this.setControls(new ArrayList<Widget>(Arrays.asList(controls)));
  }

  final void setControls(List<Widget> controls) {
    this.controls = controls;
  }

  public List<Widget> getControls() {
    return Collections.unmodifiableList(this.controls);
  }

  public Widget getControlAt(Integer i) {
    return this.controls.get(i);
  }

  public void addControl(Widget c) {
    this.controls.add(c);
  }

  public Integer controlCount() {
    return this.controls.size();
  }

  public ControlList copy() {
    List<Widget> l = new ArrayList<Widget>();
    for (Widget c : this.controls) {
      l.add((Widget) c.copy());
    }
    return new ControlList(l);
  }
  
  public int indexOf(Widget control) {
    return this.getControls().indexOf(control);
  }
  
  public boolean replaceControl(Widget controlToReplace, Widget replacingControl) {
    if (this.indexOf(controlToReplace) == -1) {
      return false;
    }
    this.controls.set(this.indexOf(controlToReplace), replacingControl);
    return true;
  }

}
