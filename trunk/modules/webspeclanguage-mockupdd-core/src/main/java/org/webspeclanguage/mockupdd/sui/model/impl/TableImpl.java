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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link Table}
 * 
 * @author Jose Matias Rivero
 */
public class TableImpl extends SimpleWidgetImpl implements Table {

  private List<TableColumn> columns;

  public TableImpl(String widgetID, int x, int y, int width, int height) {
    super(widgetID, x, y, width, height);
    this.setColumns(new ArrayList<TableColumn>());
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitTable(this);
  }

  final void setColumns(List<TableColumn> columns) {
    this.columns = columns;
  }

  public List<TableColumn> getColumns() {
    return columns;
  }

  public void addColumn(TableColumn tableColumn) {
    this.getColumns().add(tableColumn);
  }

  public Boolean equalInContent(Widget widget) {
    Table other = (Table) widget;
    return super.equalInContent(other) && this.columnsAreEqual(other);
  }

  private Boolean columnsAreEqual(Table other) {
    if (this.getColumns().size() != other.getColumns().size()) {
      return false;
    }
    for (Integer iCol = 0; iCol < this.getColumns().size(); iCol++) {
      if (!this.getColumns().get(iCol).equals(other.getColumns().get(iCol))) {
        return false;
      }
    }
    return true;
  }

  public Widget copyConcreteWidget() {
    TableImpl t = new TableImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    for (TableColumn tc : this.getColumns()) {
      t.addColumn(tc);
    }
    return t;
  }

}
