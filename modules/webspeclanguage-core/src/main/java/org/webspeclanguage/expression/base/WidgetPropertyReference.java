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
package org.webspeclanguage.expression.base;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.webspeclanguage.widget.Widget;

/**
 * An expression class for "Home.search.visible"
 * 
 * @author Esteban Robles Luna
 */
public class WidgetPropertyReference extends AbstractExpression {

  private Widget widget;
  private String propertyName;
  private String location;

  public WidgetPropertyReference(Widget widget, String propertyName) {
    this.setWidget(widget);
    this.setPropertyName(propertyName);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    WidgetPropertyReference o = (WidgetPropertyReference) obj;
    return new EqualsBuilder().append(this.widget, o.widget).append(
        this.propertyName, o.propertyName).isEquals();
  }

  @Override
  public int hashCode() {
    return this.widget.hashCode() + 7 * this.propertyName.hashCode();
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitWidgetPropertyReference(this);
  }

  public String getPreferedLocation() {
    if (this.getLocation() != null) {
      return this.getLocation();
    } else {
      return this.getWidget().getPreferedLocation();
    }
  }

  @Override
  public String toString() {
    return this.getPreferedLocation();
  }

  public Widget getWidget() {
    return widget;
  }

  private void setWidget(Widget widget) {
    this.widget = widget;
  }

  public String getPropertyName() {
    return propertyName;
  }

  private void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
