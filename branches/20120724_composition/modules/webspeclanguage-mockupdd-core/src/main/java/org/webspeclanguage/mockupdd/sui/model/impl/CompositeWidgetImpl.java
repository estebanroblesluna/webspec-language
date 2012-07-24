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
package org.webspeclanguage.mockupdd.sui.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.DataBoundWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.Layout;

/**
 * Default implementation of {@link CompositeWidget}
 * 
 * @author Jose Matias Rivero
 */
public abstract class CompositeWidgetImpl extends WidgetImpl implements CompositeWidget, DataBoundWidget{

  private Collection<Widget> widgets;
  private Layout layout;
  private Map<String, Widget> widgetsById;
  private String containerId;

  protected CompositeWidgetImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, Collection<Widget> widgets, String containerId) {
    super(widgetID, x, y, width, height);
    this.setWidgets(new HashSet<Widget>());
    this.setWidgetsById(new HashMap<String, Widget>());
    this.addChilds(widgets);
    this.setContainerId(containerId);
  }

  public CompositeWidgetImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, String containerId) {
    this(widgetID, x, y, width, height, new ArrayList<Widget>(), containerId);
  }

  public final void setWidgets(Collection<Widget> widgets) {
    this.widgets = widgets;
  }

  public final Collection<Widget> getWidgets() {
    if (this.getLayout() != null) {
      return this.getLayout().getWidgets();
    }
    return widgets;
  }

  public final void setLayout(Layout layout) {
    this.layout = layout;
  }

  public final Layout getLayout() {
    return layout;
  }

  private void setWidgetsById(Map<String, Widget> widgetsById) {
    this.widgetsById = widgetsById;
  }

  private Map<String, Widget> getWidgetsById() {
    return widgetsById;
  }

  private void setContainerId(String containerId) {
    this.containerId = containerId;
  }

  public final String getContainerId() {
    return containerId;
  }

  public final void addChild(Widget w) {
    this.getWidgets().add(w);
    w.setParent(this);
  }

  public void removeChild(Widget w) {
    this.getWidgets().remove(w);
    w.setParent(null);
  }

  public void registerChild(Widget w) {
    this.getWidgetsById().put(w.getWidgetId(), w);
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).registerChild(w);
    }
  }

  public void unregisterChild(Widget w) {
    this.getWidgetsById().remove(w.getWidgetId());
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).unregisterChild(w);
    }
  }

  public void registerChildren(Collection<Widget> widgets) {
    for (Widget w : widgets) {
      this.registerChild(w);
    }
  }

  public void unregisterChildren(Collection<Widget> widgets) {
    for (Widget w : widgets) {
      this.unregisterChild(w);
    }
  }

  private Collection<Widget> getRegisteredChildren() {
    return this.getWidgetsById().values();
  }

  public final void addChilds(Collection<Widget> widgets) {
    for (Widget c : widgets) {
      this.addChild(c);
    }
  }

  public final Widget getWidgetById(String id) {
    return this.getWidgetsById().get(id);
  }

  private void cloneChildWidgetsIn(CompositeWidget cc) {
    for (Widget child : this.getWidgets()) {
      cc.addChild((Widget) child.copy());
    }
  }

  public Widget copyConcreteWidget() {
    CompositeWidget cc = this.createNewInstance();
    this.cloneChildWidgetsIn(cc);
    if (this.getLayout() != null) {
      cc.setLayout((Layout) this.getLayout().copy());
    }
    return cc;
  }

  public void removeAllChildren() {
    for (Widget c : new ArrayList<Widget>(this.getWidgets())) {
      this.removeChild(c);
    }
  }

  public void addAll(Collection<Widget> widgets) {
    this.getWidgets().addAll(widgets);
  }

  public void replaceWidget(Widget widgetToReplace, Widget replacingWidget) {
    this.removeChild(widgetToReplace);
    this.addChild(replacingWidget);
    if (this.getLayout() != null) {
      this.getLayout().replaceWidget(widgetToReplace, replacingWidget);
    }
  }

  protected abstract CompositeWidget createNewInstance();

  protected void registerWidgetsInNewParent(CompositeWidget oldParent, CompositeWidget newParent) {
    if (oldParent != null) {
      ((CompositeWidgetImpl) oldParent).unregisterChild(this);
      ((CompositeWidgetImpl) oldParent).unregisterChildren(this.getRegisteredChildren());
    }
    if (newParent != null) {
      ((CompositeWidgetImpl) newParent).registerChild(this);
      ((CompositeWidgetImpl) newParent).registerChildren(this.getRegisteredChildren());
    }
  }

}
