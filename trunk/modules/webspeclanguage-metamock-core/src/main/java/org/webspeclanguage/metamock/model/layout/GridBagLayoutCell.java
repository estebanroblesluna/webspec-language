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
package org.webspeclanguage.metamock.model.layout;

import org.webspeclanguage.metamock.model.UIControl;

/**
 * Represents a cell in a {@link GridBagLayout}. A GridBagLayoutCell has the row
 * and column number in which is placed in a {@link GridBagLayout}, and its row
 * and column span. Also, it holds a reference to the control being placed
 * 
 * @author Jose Matias Rivero
 */
public interface GridBagLayoutCell {

  GridBagLayout getGridBoxLayout();

  Integer getRow();

  Integer getColumn();

  Integer getRowSpan();

  Integer getColumnSpan();

  UIControl getControl();

  Boolean hasControl();

  void setRow(Integer i);

  void setColumn(Integer i);

  void setGridBoxLayout(GridBagLayout cell);

  void setColumnSpan(Integer cols);

  void setRowSpan(Integer i);

  GridBagLayoutCell copy();

}
