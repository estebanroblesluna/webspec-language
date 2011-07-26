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
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.Layout;

/**
 * Default implementation of {@link CompositeWidget}
 * 
 * @author Jose Matias Rivero
 */
public abstract class CompositeWidgetImpl extends WidgetImpl implements CompositeWidget {

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

  public final void addChild(Widget c) {
    this.getWidgets().add(c);
    this.indexWidget(c);
    c.setParent(this);
  }

  public final void removeChild(Widget c) {
    this.getWidgets().remove(c);
    this.removeWidgetIndex(c);
    c.setParent(null);
  }

  private void indexWidget(Widget c) {
    this.getWidgetsById().put(c.getWidgetId(), c);
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).indexWidget(c);
    }
  }

  private void removeWidgetIndex(Widget c) {
    this.getWidgetById(c.getWidgetId());
    if (this.getParent() != null) {
      ((CompositeWidgetImpl) this.getParent()).removeWidgetIndex(c);
    }
  }

  public final void addChilds(Collection<Widget> widgets) {
    for (Widget c : widgets) {
      this.addChild(c);
    }
  }

  public final Widget getWidgetById(String id) {
    return this.getWidgetsById().get(id);
  }

  public final void setParent(CompositeWidget parentWidget) {
    if (this.getParent() != null) {
      this.removeWidgetIndexesParent();
    }
    super.setParent(parentWidget);
    if (parentWidget != null) {
      this.addWidgetIndexesInParent();
    }
  }

  private void addWidgetIndexesInParent() {
    for (Widget c : this.getWidgets()) {
      ((CompositeWidgetImpl) this.getParent()).indexWidget(c);
    }
  }

  private void removeWidgetIndexesParent() {
    for (Widget c : this.getWidgets()) {
      this.getParent().removeChild(c);
    }
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
}
