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
package org.webspeclanguage.impl.expression.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.webspeclanguage.impl.widget.Widget;

/**
 * An expression class for "Home.search"
 * 
 * @author Esteban Robles Luna
 */
public class WidgetReference extends AbstractExpression {

  private Widget widget;
  private String location;
  private Map<String, Expression> variables;
  
  public WidgetReference(Widget widget) {
    this(widget, new HashMap<String, Expression>());
  }
  
  public WidgetReference(Widget widget, Map<String, Expression> variables) {
    this.widget = widget;
    this.variables = new HashMap<String, Expression>(variables);
  }

  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitWidgetReference(this);
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
    WidgetReference o = (WidgetReference) obj;
    return new EqualsBuilder()
      .append(this.widget, o.widget)
      .append(this.variables, o.variables)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return this.widget.hashCode();
  }
  
  public void setVariables(Map<String, Expression> variables) {
    this.variables = new HashMap<String, Expression>(variables);
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
  
  public Map<String, Expression> getVariables() {
    return variables;
  }
}
