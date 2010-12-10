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
package org.webspeclanguage.metamock.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.Layout;

/**
 * Default implementation of {@link CompositeControl}
 * 
 * @author Jose Matias Rivero
 */
public abstract class CompositeControlImpl extends UIControlImpl implements CompositeControl {

  private Collection<UIControl> controls;
  private Layout layout;
  private Map<String, UIControl> controlsById;
  private String containerId;

  protected CompositeControlImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Collection<UIControl> controls, String containerId) {
    super(controlID, x, y, width, height);
    this.setControls(new HashSet<UIControl>());
    this.setControlsById(new HashMap<String, UIControl>());
    this.addChilds(controls);
    this.setContainerId(containerId);
  }

  public CompositeControlImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String containerId) {
    this(controlID, x, y, width, height, new ArrayList<UIControl>(), containerId);
  }

  public final void setControls(Collection<UIControl> controls) {
    this.controls = controls;
  }

  public final Collection<UIControl> getControls() {
    return controls;
  }

  public final void setLayout(Layout layout) {
    this.layout = layout;
  }

  public final Layout getLayout() {
    return layout;
  }

  private void setControlsById(Map<String, UIControl> controlsById) {
    this.controlsById = controlsById;
  }

  private Map<String, UIControl> getControlsById() {
    return controlsById;
  }

  private void setContainerId(String containerId) {
    this.containerId = containerId;
  }

  public final String getContainerId() {
    return containerId;
  }

  public final void addChild(UIControl c) {
    this.getControls().add(c);
    this.indexControl(c);
    c.setParent(this);
  }

  public final void removeChild(UIControl c) {
    this.getControls().remove(c);
    this.removeControlIndex(c);
    c.setParent(null);
  }

  private void indexControl(UIControl c) {
    this.getControlsById().put(c.getControlId(), c);
    if (this.getParent() != null) {
      ((CompositeControlImpl) this.getParent()).indexControl(c);
    }
  }

  private void removeControlIndex(UIControl c) {
    this.getControlById(c.getControlId());
    if (this.getParent() != null) {
      ((CompositeControlImpl) this.getParent()).removeControlIndex(c);
    }
  }

  public final void addChilds(Collection<UIControl> controls) {
    for (UIControl c : controls) {
      this.addChild(c);
    }
  }

  public final UIControl getControlById(String id) {
    return this.getControlsById().get(id);
  }

  public final void setParent(CompositeControl parentControl) {
    if (this.getParent() != null) {
      this.removeControlIndexesParent();
    }
    super.setParent(parentControl);
    if (parentControl != null) {
      this.addControlIndexesInParent();
    }
  }

  private void addControlIndexesInParent() {
    for (UIControl c : this.getControls()) {
      ((CompositeControlImpl) this.getParent()).indexControl(c);
    }
  }

  private void removeControlIndexesParent() {
    for (UIControl c : this.getControls()) {
      this.getParent().removeChild(c);
    }
  }

  private void cloneChildControlsIn(CompositeControl cc) {
    for (UIControl child : this.getControls()) {
      cc.addChild((UIControl) child.copy());
    }
  }

  public UIControl copyConcreteControl() {
    CompositeControl cc = this.createNewInstance();
    this.cloneChildControlsIn(cc);
    if (this.getLayout() != null) {
      cc.setLayout((Layout) this.getLayout().copy());
    }
    return cc;
  }

  public void removeAllChildren() {
    for (UIControl c : new ArrayList<UIControl>(this.getControls())) {
      this.removeChild(c);
    }
  }

  protected abstract CompositeControl createNewInstance();
}
