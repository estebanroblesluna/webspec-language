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
package org.webspeclanguage.mockupdd.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * Represents a list of widgets
 * 
 * @author Jose Matias Rivero
 */
public class WidgetList {

  private List<Widget> widgets;
 
  public WidgetList(List<Widget> widgets) {
    this.setWidgets(widgets);
  }

  public WidgetList(Widget... widgets) {
    this.setWidgets(new ArrayList<Widget>(Arrays.asList(widgets)));
  }

  final void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }

  public List<Widget> getWidgets() {
    return Collections.unmodifiableList(this.widgets);
  }

  public Widget getWidgetAt(Integer i) {
    return this.widgets.get(i);
  }

  public void addWidget(Widget c) {
    this.widgets.add(c);
  }

  public Integer widgetCount() {
    return this.widgets.size();
  }

  public WidgetList copy() {
    List<Widget> l = new ArrayList<Widget>();
    for (Widget c : this.widgets) {
      l.add((Widget) c.copy());
    }
    return new WidgetList(l);
  }
  
  public int indexOf(Widget widget) {
    return this.getWidgets().indexOf(widget);
  }
  
  public boolean replaceWidget(Widget widgetToReplace, Widget replacingWidget) {
    if (this.indexOf(widgetToReplace) == -1) {
      return false;
    }
    this.widgets.set(this.indexOf(widgetToReplace), replacingWidget);
    return true;
  }

}
