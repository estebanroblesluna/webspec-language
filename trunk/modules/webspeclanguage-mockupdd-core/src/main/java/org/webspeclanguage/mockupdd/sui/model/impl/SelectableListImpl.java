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

import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link SelectableList}
 * 
 * @author Jose Matias Rivero
 */
public class SelectableListImpl extends SimpleInputWidgetImpl implements SelectableList {

  private Boolean multipleSelection;

  public SelectableListImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection) {
    super(widgetID, x, y, width, height);
    this.setMultipleSelection(multipleSelection);
  }

  public Boolean getMultipleSelection() {
    return this.multipleSelection;
  }

  private void setMultipleSelection(Boolean multipleSelection) {
    this.multipleSelection = multipleSelection;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitSelectableList(this);
  }

  public Boolean equalInContent(Widget widget) {
    SelectableList other = (SelectableList) widget;
    return super.equalInContent(other) && this.getMultipleSelection().equals(other.getMultipleSelection());
  }

  @Override
  public Widget copyConcreteWidget() {
    return new SelectableListImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getMultipleSelection());
  }
}
