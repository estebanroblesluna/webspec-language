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

import org.webspeclanguage.metamock.model.TextArea;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link TextArea}
 * 
 * @author Jose Matias Rivero
 */
public class TextAreaImpl extends SimpleControlImpl implements TextArea {

  public TextAreaImpl(String controlID, int x, int y, int width, int height) {
    super(controlID, x, y, width, height);
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitTextArea(this);
  }

  public UIControl copyConcreteControl() {
    return new TextAreaImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
  }
}