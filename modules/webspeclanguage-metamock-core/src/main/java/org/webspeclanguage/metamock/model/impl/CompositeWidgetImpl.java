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

import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.Layout;

/**
 * Default implementation of {@link CompositeWidget}
 * 
 * @author Jose Matias Rivero
 */
public abstract class CompositeWidgetImpl extends WidgetImpl implements CompositeWidget {

  private Collection<Widget> controls;
  private Layout layout;
  private Map<String, Widget> controlsById;
  private String containerId;

  protected CompositeWidgetImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Collection<Widget> controls, String containerId) {
    super(controlID, x, y, width, height);
    this.setControls(new HashSet<Widget>());
    this.setControlsById(new HashMap<String, Widget>());
    this.addChilds(controls);
    this.setContainerId(containerId);
  }

  public CompositeWidgetImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String containerId) {
    this(controlID, x, y, width, height, new ArrayList<Widget>(), containerId);
  }

  public final void setControls(Collection<Widget> controls) {
    this.controls = controls;
  }

  public final Collection<Widget> getControls() {
    return controls;
  }

  public final void setLayout(Layout layout) {
    this.layout = layout;
  }

  public final Layout getLayout() {
    return layout;
  }

  private void setControlsById(Map<String, Widget> controlsById) {
    this.controlsById = controlsById;
  }

  private Map<String, Widget> getControlsById() {
    return controlsById;
  }

  private void setContainerId(String containerId) {
    this.containerId = containerId;
  }

  public final String getContainerId() {
    return containerId;
  }

  public final void addChild(Widget c) {
    this.getControls().add(c);
    this.indexControl(c);
    c.setParent(this);
  }

  public final void removeChild(Widget c) {
    this.getControls().remove(c);
    this.removeControlIndex(c);
    c.setParent(null);
  }

  private void indexControl(Widget c) {
    this.getControlsById().put(c.getControlId(), c);
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).indexControl(c);
    }
  }

  private void removeControlIndex(Widget c) {
    this.getControlById(c.getControlId());
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).removeControlIndex(c);
    }
  }

  public final void addChilds(Collection<Widget> controls) {
    for (Widget c : controls) {
      this.addChild(c);
    }
  }

  public final Widget getControlById(String id) {
    return this.getControlsById().get(id);
  }

  public final void setParent(CompositeWidget parentControl) {
    if (this.getParent() != null) {
      this.removeControlIndexesParent();
    }
    super.setParent(parentControl);
    if (parentControl != null) {
      this.addControlIndexesInParent();
    }
  }

  private void addControlIndexesInParent() {
    for (Widget c : this.getControls()) {
      ((CompositeWidgetImpl) this.getParent()).indexControl(c);
    }
  }

  private void removeControlIndexesParent() {
    for (Widget c : this.getControls()) {
      this.getParent().removeChild(c);
    }
  }

  private void cloneChildControlsIn(CompositeWidget cc) {
    for (Widget child : this.getControls()) {
      cc.addChild((Widget) child.copy());
    }
  }

  public Widget copyConcreteControl() {
    CompositeWidget cc = this.createNewInstance();
    this.cloneChildControlsIn(cc);
    if (this.getLayout() != null) {
      cc.setLayout((Layout) this.getLayout().copy());
    }
    return cc;
  }

  public void removeAllChildren() {
    for (Widget c : new ArrayList<Widget>(this.getControls())) {
      this.removeChild(c);
    }
  }
  
  public void addAll(Collection<Widget> controls) {
    this.getControls().addAll(controls);
  }
 
  public void replaceControl(Widget controlToReplace, Widget replacingControl) {
    this.removeChild(controlToReplace);
    this.addChild(replacingControl);
    if (this.getLayout() != null) {
      this.getLayout().replaceControl(controlToReplace, replacingControl);
    }
  }

  protected abstract CompositeWidget createNewInstance();
}
