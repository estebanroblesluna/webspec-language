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

import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link Label}
 * 
 * @author Jose Matias Rivero
 */
public class LabelImpl extends SimpleControlImpl implements Label {

  private UIControl control;
  private String text;

  public LabelImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String text) {
    this(controlID, x, y, width, height, text, null);
  }

  public LabelImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String text, UIControl control) {
    super(controlID, x, y, width, height);
    this.setControl(control);
    this.setText(text);
  }

  public final void setControl(UIControl control) {
    this.control = control;
  }

  public UIControl getControl() {
    return control;
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitLabel(this);
  }

  public String getText() {
    return text;
  }

  private void setText(String text) {
    this.text = text;
  }

  @Override
  public Boolean equalInContent(UIControl control) {
    Label other = (Label) control;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public UIControl copyConcreteControl() {
    return new LabelImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }

  @Override
  protected String getProperties() {
    return super.getProperties() + ", text:\"" + this.getText() + "\"";
  }

}
