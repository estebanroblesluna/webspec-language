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

import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Widget;

/**
 * Default implementation of {@link Widget}
 * 
 * @author Jose Matias Rivero
 */
public abstract class WidgetImpl implements Widget {

  private String controldId;
  private String friendlyId;
  private Integer x;
  private Integer y;
  private Integer width;
  private Integer height;
  private CompositeWidget parent;

  protected WidgetImpl(String controlID, Integer x, Integer y, Integer width, Integer height) {
    super();
    this.setControlId(controlID);
    this.setX(x);
    this.setY(y);
    this.setWidth(width);
    this.setHeight(height);
  }

  public final void setControlId(String controlID) {
    this.controldId = controlID;
  }

  public String getControlId() {
    return controldId;
  }

  private void setX(Integer x) {
    this.x = x;
  }

  public Integer getX() {
    return x;
  }

  private void setY(Integer y) {
    this.y = y;
  }

  public Integer getY() {
    return y;
  }

  private void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getWidth() {
    return width;
  }

  private void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getHeight() {
    return height;
  }

  public void setParent(CompositeWidget parentControl) {
    this.parent = parentControl;
  }

  public CompositeWidget getParent() {
    return parent;
  }

  public Page getPage() {
    if (this.getParent() == null) {
      return null;
    }
    return (Page) this.getParent().getPage();
  }

  public String toString() {
    return this.getClass().getSimpleName() + "(" + this.getProperties() + ")";
  }
  
  protected String getProperties() {
    return 
      "id: " + this.getControlId() + ", " +
      "page: \"" + this.getPage() + "\"" +
      "x: " + this.getX() + ", " +
      "y: " + this.getY();
  }

  public void setFriendlyId(String friendlyId) {
    this.friendlyId = friendlyId;
  }

  public String getFriendlyId() {
    if (this.friendlyId == null) {
      return this.getControlId();
    } else {
      return this.friendlyId;
    }
  }

  public Boolean isTheSameAs(CompositeWidget control) {
    return this.getControlId().equals(control.getControlId()) && this.getPage().getControlId().equals(control.getPage().getControlId());
  }

  public Boolean equalInContent(Widget control) {
    Widget other = (Widget) control;
    if ((this.getPage() == null && control.getPage() != null) ||
        (this.getPage() != null && control.getPage() == null)) {
      return false;
    }
    if ((this.getPage() == null && control.getPage() == null)) {
      return this.getFriendlyId().equals(other.getFriendlyId());
    } else {
      return this.getPage().equalInContent(other.getPage()) && this.getFriendlyId().equals(other.getFriendlyId());
    }
  }

  public abstract Widget copyConcreteControl();

  public Widget copy() {
    Widget c = this.copyConcreteControl();
    c.setFriendlyId(this.friendlyId);
    return c;
  }

}
