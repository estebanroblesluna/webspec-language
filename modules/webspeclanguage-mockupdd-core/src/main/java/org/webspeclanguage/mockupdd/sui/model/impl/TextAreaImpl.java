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

import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link TextArea}
 * 
 * @author Jose Matias Rivero
 */
public class TextAreaImpl extends SimpleInputWidgetImpl implements TextArea {

  public TextAreaImpl(String widgetID, int x, int y, int width, int height) {
    super(widgetID, x, y, width, height);
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitTextArea(this);
  }

  public Widget copyConcreteWidget() {
    return new TextAreaImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
  }
}
