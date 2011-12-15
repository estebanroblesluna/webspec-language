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
package org.webspeclanguage.mockupdd.sui.model.layout.impl;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

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
  private Widget widget;
  private GridBagLayout gridBagLayout;

  public GridBagLayoutCellImpl(Integer row, Integer column, Widget widget) {
    this(row, column, 1, 1, widget);
  }

  public GridBagLayoutCellImpl(Integer row, Integer column, Integer rowSpan, Integer columnSpan, Widget widget) {
    super();
    this.setRow(row);
    this.setColumn(column);
    this.setWidget(widget);
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

  public void setWidget(Widget widget) {
    this.widget = widget;
  }

  public Widget getWidget() {
    return widget;
  }

  public final void setRowSpan(Integer rowSpan) {
    this.rowSpan = rowSpan;
  }

  public Integer getRowSpan() {
    return rowSpan;
  }

  public Boolean hasWidget() {
    return this.getWidget() != null;
  }

  public final void setColumnSpan(Integer columnSpan) {
    this.columnSpan = columnSpan;
  }

  public Integer getColumnSpan() {
    return columnSpan;
  }

  public void setGridBagLayout(GridBagLayout gridBoxLayout) {
    this.gridBagLayout = gridBoxLayout;
  }

  public GridBagLayout getGridBagLayout() {
    return gridBagLayout;
  }

  public GridBagLayoutCell copy() {
    return new GridBagLayoutCellImpl(this.getRow(), this.getColumn(), this.getRowSpan(), this.getColumnSpan(), (Widget) this.getWidget().copy());
  }
  
  public String toString() {
    return "(" + this.getRow() + ", " + this.getColumn() + ", " + this.getRowSpan() +
    ", " + this.getColumnSpan() + ", " + this.getWidget() + ")";
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitGridBagLayoutCell(this);
  }

}
