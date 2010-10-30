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

import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link CheckBox}
 * 
 * @author Jose Matias Rivero
 */
public class CheckBoxImpl extends SimpleControlImpl implements CheckBox {

  private String text;

  public CheckBoxImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String text) {
    super(controlID, x, y, width, height);
    this.setText(text);
  }

  public final <T> T visit(MetaMockVisitor<T> v) {
    return v.visitCheckBox(this);
  }

  private void setText(String text) {
    this.text = text;
  }

  public final String getText() {
    return text;
  }

  @Override
  public Boolean equalInContent(UIControl control) {
    CheckBox other = (CheckBox) control;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public UIControl copyConcreteControl() {
    return new CheckBoxImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }

}
