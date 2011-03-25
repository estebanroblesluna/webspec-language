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

import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link Link}
 * 
 * @author Jose Matias Rivero
 */
public class LinkImpl extends SimpleControlImpl implements Link {

  private String text;

  public LinkImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String text) {
    super(controlID, x, y, width, height);
    this.setText(text);
  }

  public <T> T accept(MetaMockVisitor<T> v) {
    return v.visitLink(this);
  }

  private void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public Boolean equalInContent(UIControl control) {
    Link other = (Link) control;
    return super.equalInContent(other) && this.getText().equals(other.getText());
  }

  @Override
  public UIControl copyConcreteControl() {
    return new LinkImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getText());
  }

}
