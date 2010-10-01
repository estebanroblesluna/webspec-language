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
package org.webspeclanguage.impl.widget;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * An abstract class for widget containment
 * 
 * @author Esteban Robles Luna
 */
public abstract class Container extends Widget {

  private List<Widget> widgets;

  public Container() {
    this.widgets = new ArrayList<Widget>();
  }

  public void addWidget(Widget widget) {
    Validate.notNull(widget);
    
    if (this.containsWidgetNamed(widget)) {
      throw new ExistingWidgetException(widget.getName());
    } else {
      this.getWidgets().add(widget);
      widget.setContainer(this);
    }
  }

  private boolean containsWidgetNamed(Widget widget) {
    return this.getWidgetNamed(widget.getName()) != null;
  }

  public Widget getWidgetNamed(String widgetName) {
    for (Widget widget : this.getWidgets()) {
      if (widget.getName() != null && widget.getName().equals(widgetName)) {
        return widget;
      }
    }
    return null;
  }

  public TextField createTextFieldWithLocation(String aLocation) {
    TextField tf = new TextField();
    tf.setLocation(aLocation);
    return tf;
  }

  public Button createButtonWithLocation(String aLocation) {
    Button b = new Button();
    b.setLocation(aLocation);
    return b;
  }

  public TextField createTextFieldWithId(String anID) {
    TextField tf = new TextField();
    tf.setId(anID);
    return tf;
  }

  public ListOfContainer createListOfContainer(String indexVariable) {
    ListOfContainer list = new ListOfContainer();
    list.setIndexVariable(indexVariable);
    return list;
  }

  public Label createLabelWithLocation(String location) {
    Label label = new Label();
    label.setLocation(location);
    return label;
  }

  private List<Widget> getWidgets() {
    return widgets;
  }
}