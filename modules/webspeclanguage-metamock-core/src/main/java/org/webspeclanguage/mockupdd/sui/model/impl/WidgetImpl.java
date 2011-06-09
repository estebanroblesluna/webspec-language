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

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;

/**
 * Default implementation of {@link Widget}
 * 
 * @author Jose Matias Rivero
 */
public abstract class WidgetImpl implements Widget {

  private String widgetdId;
  private String friendlyId;
  private Integer x;
  private Integer y;
  private Integer width;
  private Integer height;
  private CompositeWidget parent;
  private Collection<TagApplication> appliedTags;

  protected WidgetImpl(String widgetID, Integer x, Integer y, Integer width, Integer height) {
    super();
    this.setWidgetId(widgetID);
    this.setX(x);
    this.setY(y);
    this.setWidth(width);
    this.setHeight(height);
    this.setAppliedTags(new ArrayList<TagApplication>());
  }

  public final void setWidgetId(String widgetID) {
    this.widgetdId = widgetID;
  }

  public String getWidgetId() {
    return widgetdId;
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

  public void setParent(CompositeWidget parentWidget) {
    this.parent = parentWidget;
  }

  private void setAppliedTags(Collection<TagApplication> appliedTags) {
    this.appliedTags = appliedTags;
  }

  public Collection<TagApplication> getAppliedTags() {
    return appliedTags;
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
      "id: " + this.getWidgetId() + ", " +
      "page: \"" + this.getPage() + "\"" +
      "x: " + this.getX() + ", " +
      "y: " + this.getY();
  }

  public void setFriendlyId(String friendlyId) {
    this.friendlyId = friendlyId;
  }

  public String getFriendlyId() {
    if (this.friendlyId == null) {
      return this.getWidgetId();
    } else {
      return this.friendlyId;
    }
  }

  public Boolean isTheSameAs(CompositeWidget widget) {
    return this.getWidgetId().equals(widget.getWidgetId()) && this.getPage().getWidgetId().equals(widget.getPage().getWidgetId());
  }

  public Boolean equalInContent(Widget widget) {
    Widget other = (Widget) widget;
    if ((this.getPage() == null && widget.getPage() != null) ||
        (this.getPage() != null && widget.getPage() == null)) {
      return false;
    }
    if ((this.getPage() == null && widget.getPage() == null)) {
      return this.getFriendlyId().equals(other.getFriendlyId());
    } else {
      return this.getPage().equalInContent(other.getPage()) && this.getFriendlyId().equals(other.getFriendlyId());
    }
  }

  public abstract Widget copyConcreteWidget();

  public Widget copy() {
    Widget c = this.copyConcreteWidget();
    c.setFriendlyId(this.friendlyId);
    return c;
  }

}
