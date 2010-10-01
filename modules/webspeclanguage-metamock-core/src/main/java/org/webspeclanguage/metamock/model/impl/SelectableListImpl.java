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

import org.webspeclanguage.metamock.model.SelectableList;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link SelectableList}
 * 
 * @author Jose Matias Rivero
 */
public class SelectableListImpl extends ListImpl implements SelectableList {

  private Boolean multipleSelection;

  public SelectableListImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection) {
    super(controlID, x, y, width, height);
    this.setMultipleSelection(multipleSelection);
  }

  public Boolean getMultipleSelection() {
    return this.multipleSelection;
  }

  private void setMultipleSelection(Boolean multipleSelection) {
    this.multipleSelection = multipleSelection;
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitSelectableList(this);
  }

  public Boolean equalInContent(UIControl control) {
    SelectableList other = (SelectableList) control;
    return super.equalInContent(other) && this.getMultipleSelection().equals(other.getMultipleSelection());
  }

  @Override
  public UIControl copyConcreteControl() {
    return new SelectableListImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getMultipleSelection());
  }
}
