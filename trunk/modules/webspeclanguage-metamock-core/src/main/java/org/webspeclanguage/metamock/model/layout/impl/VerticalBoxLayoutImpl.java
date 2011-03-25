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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GroupedLinearLayoutVisitor;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.utils.ControlList;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation for {@link VerticalBoxLayout}
 * 
 * @author Jose Matias Rivero
 */
public class VerticalBoxLayoutImpl implements VerticalBoxLayout {

  private List<ControlList> controlLists;

  public static VerticalBoxLayoutImpl fromList(List<UIControl> controls) {
    VerticalBoxLayoutImpl vbli = new VerticalBoxLayoutImpl();
    vbli.setControlLists(createControlsList(controls));
    return vbli;
  }

  public VerticalBoxLayoutImpl() {
    super();
    this.setControlLists(new ArrayList<ControlList>());
  }

  public VerticalBoxLayoutImpl(List<ControlList> controlLists) {
    super();
    this.setControlLists(controlLists);
  }

  private static List<ControlList> createControlsList(List<UIControl> ctrls) {
    List<ControlList> controls = new ArrayList<ControlList>();
    for (UIControl c : ctrls) {
      controls.add(new ControlList(c));
    }
    return controls;
  }

  public Collection<UIControl> getControls() {
    List<UIControl> ctrls = new ArrayList<UIControl>();
    for (ControlList cl : this.getControlLists()) {
      ctrls.addAll(cl.getControls());
    }
    return ctrls;
  }

  private List<ControlList> getControlLists() {
    return controlLists;
  }

  final void setControlLists(List<ControlList> controls) {
    this.controlLists = controls;
  }

  public ControlList getControlsAt(Integer i) {
    return this.getControlLists().get(i);
  }

  public <T> T accept(MetaMockVisitor<T> v) {
    return v.visitVerticalBoxLayout(this);
  }

  public <T> List<T> visitControls(GroupedLinearLayoutVisitor<T> v) {
    List<T> results = new ArrayList<T>();
    for (Integer i = 0; i < this.getControlLists().size(); i++) {
      results.add(v.visitControlList(i, this.getControlLists().get(i)));
    }
    return results;
  }

  public Object copy() {
    VerticalBoxLayoutImpl vbl = new VerticalBoxLayoutImpl();
    for (ControlList cl : this.getControlLists()) {
      vbl.addControlList(cl.copy());
    }
    return vbl;
  }

  private void addControlList(ControlList cl) {
    this.getControlLists().add(cl);
  }

}
