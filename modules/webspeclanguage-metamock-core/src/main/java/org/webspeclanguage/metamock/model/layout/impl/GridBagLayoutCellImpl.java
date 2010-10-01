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
package org.webspeclanguage.metamock.model.layout.impl;

import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;

/**
 * Default implementation {@link GridBagLayoutCell}
 * 
 * @author Jose Matias Rivero
 */
public class GridBagLayoutCellImpl implements GridBagLayoutCell {

  private Integer row;
  private Integer column;
  private Integer rowSpan;
  private Integer columnSpan;
  private UIControl control;
  private GridBagLayout gridBoxLayout;

  public GridBagLayoutCellImpl(Integer row, Integer column, UIControl control) {
    this(row, column, 1, 1, control);
  }

  public GridBagLayoutCellImpl(Integer row, Integer column, Integer rowSpan, Integer columnSpan, UIControl control) {
    super();
    this.setRow(row);
    this.setColumn(column);
    this.setControl(control);
    this.setRowSpan(rowSpan);
    this.setColumnSpan(columnSpan);
  }

  public final void setRow(Integer row) {
    this.row = row;
  }

  public Integer getRow() {
    return row;
  }

  public final void setColumn(Integer column) {
    this.column = column;
  }

  public Integer getColumn() {
    return column;
  }

  private void setControl(UIControl control) {
    this.control = control;
  }

  public UIControl getControl() {
    return control;
  }

  public final void setRowSpan(Integer rowSpan) {
    this.rowSpan = rowSpan;
  }

  public Integer getRowSpan() {
    return rowSpan;
  }

  public Boolean hasControl() {
    return this.getControl() != null;
  }

  public final void setColumnSpan(Integer columnSpan) {
    this.columnSpan = columnSpan;
  }

  public Integer getColumnSpan() {
    return columnSpan;
  }

  public void setGridBoxLayout(GridBagLayout gridBoxLayout) {
    this.gridBoxLayout = gridBoxLayout;
  }

  public GridBagLayout getGridBoxLayout() {
    return gridBoxLayout;
  }

  public GridBagLayoutCell copy() {
    return new GridBagLayoutCellImpl(this.getRow(), this.getColumn(), this.getRowSpan(), this.getColumnSpan(), (UIControl) this.getControl().copy());
  }

}
