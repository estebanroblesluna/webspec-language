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

import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link Label}
 * 
 * @author Jose Matias Rivero
 */
public class LabelImpl extends SimpleWidgetImpl implements Label {

  private Widget widget;
  private String text;

  public LabelImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, String text) {
    this(widgetID, x, y, width, height, text, null);
  }

  public LabelImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, String text, Widget widget) {
    super(widgetID, x, y, width, height);
    this.setWidget(widget);
    this.setText(text);
  }

  public final void setWidget(Widget widget) {
    this.widget = widget;
  }

  public Widget getWidget() {
    return widget;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitLabel(this);
  }

  public String getText() {
    return text;
  }

  private void setText(String text) {
    this.text = text;
  }

  @Override
  public Boolean equalInContent(Widget widget) {
    Label other = (Label) widget;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public Widget copyConcreteWidget() {
    return new LabelImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }

  @Override
  protected String getProperties() {
    return super.getProperties() + ", text:\"" + this.getText() + "\"";
  }

}
