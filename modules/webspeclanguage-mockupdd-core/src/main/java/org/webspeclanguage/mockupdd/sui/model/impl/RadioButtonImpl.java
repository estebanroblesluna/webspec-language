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

import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link RadioButton}
 * 
 * @author Jose Matias Rivero
 */
public class RadioButtonImpl extends SimpleWidgetImpl implements RadioButton {

  private String text;

  public RadioButtonImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, String text) {
    super(widgetID, x, y, width, height);
    this.setText(text);
  }

  public String getText() {
    return this.text;
  }

  private void setText(String text) {
    this.text = text;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitRadioButton(this);
  }

  @Override
  public Boolean equalInContent(Widget widget) {
    RadioButton other = (RadioButton) widget;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public Widget copyConcreteWidget() {
    return new RadioButtonImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }
  
  @Override
  protected String getProperties() {
    return super.getProperties() + ", text:\"" + this.getText() + "\"";
  }
}
