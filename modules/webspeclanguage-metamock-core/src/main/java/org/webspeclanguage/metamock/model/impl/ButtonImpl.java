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

import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * Default implementation of {@link Button}
 * 
 * @author Jose Matias Rivero
 */
public class ButtonImpl extends SimpleControlImpl implements Button {

  private String text;

  public ButtonImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String text) {
    super(controlID, x, y, width, height);
    this.setText(text);
  }

  private void setText(String text) {
    this.text = text;
  }

  public final String getText() {
    return text;
  }

  public final <T> T accept(SuiVisitor<T> v) {
    return v.visitButton(this);
  }

  @Override
  public Boolean equalInContent(Widget control) {
    Button other = (Button) control;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public Widget copyConcreteControl() {
    return new ButtonImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }
  
  @Override
  protected String getProperties() {
    return super.getProperties() + ", text:\"" + this.getText() + "\"";
  }
}
