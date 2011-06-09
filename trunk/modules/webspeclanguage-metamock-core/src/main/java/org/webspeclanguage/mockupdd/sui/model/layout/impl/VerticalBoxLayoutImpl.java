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
package org.webspeclanguage.mockupdd.sui.model.layout.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GroupedLinearLayoutVisitor;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;
import org.webspeclanguage.mockupdd.utils.WidgetList;

/**
 * Default implementation for {@link VerticalBoxLayout}
 * 
 * @author Jose Matias Rivero
 */
public class VerticalBoxLayoutImpl implements VerticalBoxLayout {

  private List<WidgetList> widgetLists;

  public static VerticalBoxLayoutImpl fromList(List<Widget> widgets) {
    VerticalBoxLayoutImpl vbli = new VerticalBoxLayoutImpl();
    vbli.setWidgetLists(createWidgetsList(widgets));
    return vbli;
  }

  public VerticalBoxLayoutImpl() {
    super();
    this.setWidgetLists(new ArrayList<WidgetList>());
  }

  public VerticalBoxLayoutImpl(List<WidgetList> widgetLists) {
    super();
    this.setWidgetLists(widgetLists);
  }

  private static List<WidgetList> createWidgetsList(List<Widget> ctrls) {
    List<WidgetList> widgets = new ArrayList<WidgetList>();
    for (Widget c : ctrls) {
      widgets.add(new WidgetList(c));
    }
    return widgets;
  }

  public Collection<Widget> getWidgets() {
    List<Widget> ctrls = new ArrayList<Widget>();
    for (WidgetList cl : this.getWidgetLists()) {
      ctrls.addAll(cl.getWidgets());
    }
    return ctrls;
  }

  private List<WidgetList> getWidgetLists() {
    return widgetLists;
  }

  final void setWidgetLists(List<WidgetList> widgets) {
    this.widgetLists = widgets;
  }

  public WidgetList getWidgetsAt(Integer i) {
    return this.getWidgetLists().get(i);
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitVerticalBoxLayout(this);
  }

  public <T> List<T> visitWidgets(GroupedLinearLayoutVisitor<T> v) {
    List<T> results = new ArrayList<T>();
    for (Integer i = 0; i < this.getWidgetLists().size(); i++) {
      results.add(v.visitWidgetList(i, this.getWidgetLists().get(i)));
    }
    return results;
  }

  public Object copy() {
    VerticalBoxLayoutImpl vbl = new VerticalBoxLayoutImpl();
    for (WidgetList cl : this.getWidgetLists()) {
      vbl.addWidgetList(cl.copy());
    }
    return vbl;
  }

  private void addWidgetList(WidgetList cl) {
    this.getWidgetLists().add(cl);
  }

  public void replaceWidget(Widget widgetToReplace, Widget replacingWidget) {
    for (WidgetList widgetList : this.getWidgetLists()) {
      if (widgetList.replaceWidget(widgetToReplace, replacingWidget)) {
        return;
      }
    }
  }

}
