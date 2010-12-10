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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.webspeclanguage.metamock.model.MetaMockElement;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutException;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutVisitor;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link GridBagLayout}
 * 
 * @author Jose Matias Rivero
 */
public class GridBagLayoutImpl implements GridBagLayout {

  private Map<Integer, Map<Integer, GridBagLayoutCell>> columnsMap;
  private Integer rowCount;
  private Integer columnCount;
  private Map<UIControl, GridBagLayoutCell> controlCellMap;

  public GridBagLayoutImpl() {
    super();
    this.setColumnsMap(new HashMap<Integer, Map<Integer, GridBagLayoutCell>>());
    this.setRowCount(0);
    this.setColumnCount(0);
    this.setControlCellMap(new HashMap<UIControl, GridBagLayoutCell>());
  }

  private void setColumnsMap(Map<Integer, Map<Integer, GridBagLayoutCell>> columnsMap) {
    this.columnsMap = columnsMap;
  }

  private Map<Integer, Map<Integer, GridBagLayoutCell>> getColumnsMap() {
    return columnsMap;
  }

  public Collection<UIControl> getColumnContent(Integer i) {
    Map<Integer, GridBagLayoutCell> col = this.getColumnsMap().get(i);
    if (col == null) {
      return new ArrayList<UIControl>();
    } else {
      return this.getControlsFromCells(col.values());
    }
  }

  private Collection<UIControl> getControlsFromCells(Collection<GridBagLayoutCell> cells) {
    List<UIControl> ctrls = new ArrayList<UIControl>();
    for (GridBagLayoutCell c : cells) {
      if (c.getControl() != null) {
        ctrls.add(c.getControl());
      }
    }
    return ctrls;
  }

  public GridBagLayoutCell getCell(Integer rowIndex, Integer columnIndex) {
    GridBagLayoutCell cell = null;
    Map<Integer, GridBagLayoutCell> columnMap = this.getColumnsMap().get(columnIndex);
    if (columnMap != null) {
      cell = columnMap.get(rowIndex);
    }
    if (cell == null) {
      cell = new GridBagLayoutCellImpl(rowIndex, columnIndex, null);
      cell.setGridBoxLayout(this);
    }
    return cell;
  }

  public Collection<UIControl> getRowContent(Integer i) {
    Set<UIControl> controls = new HashSet<UIControl>();
    for (Map<Integer, GridBagLayoutCell> m : this.getColumnsMap().values()) {
      if (m.get(i) != null) {
        if (m.get(i).getControl() != null) {
          controls.add(m.get(i).getControl());
        }
      }
    }
    return controls;
  }

  public Collection<UIControl> getControls() {
    Set<UIControl> controls = new HashSet<UIControl>();
    for (Map<Integer, GridBagLayoutCell> m : this.getColumnsMap().values()) {
      for (UIControl c : this.getControlsFromCells(m.values())) {
        controls.add(c);
      }
    }
    return controls;
  }

  public Integer getRowCount() {
    return rowCount;
  }

  private void setRowCount(Integer rows) {
    this.rowCount = rows;
  }

  public Integer getColumnCount() {
    return columnCount;
  }

  private void setColumnCount(Integer columns) {
    this.columnCount = columns;
  }

  private void setControlCellMap(Map<UIControl, GridBagLayoutCell> controlCell) {
    this.controlCellMap = controlCell;
  }

  private Map<UIControl, GridBagLayoutCell> getControlCellMap() {
    return controlCellMap;
  }

  private void addControl(Integer rowIndex, Integer columnIndex, UIControl c) throws GridBagLayoutException {
    this.add(new GridBagLayoutCellImpl(rowIndex, columnIndex, c));
  }

  public void add(GridBagLayoutCell cell) throws GridBagLayoutException {
    if (cell.getControl() == null) {
      throw new GridBagLayoutException("Cell is empty", this);
    }
    for (Integer iRow = 0; iRow < cell.getRowSpan(); iRow++) {
      for (Integer iCol = 0; iCol < cell.getColumnSpan(); iCol++) {
        this.indexCell(cell, cell.getRow() + iRow, cell.getColumn() + iCol);
      }
    }
    cell.setGridBoxLayout(this);
  }

  private void indexCell(GridBagLayoutCell cell, Integer rowIndex, Integer columnIndex) throws GridBagLayoutException {
    if (this.getCell(rowIndex, columnIndex).getControl() != null) {
      throw new GridBagLayoutException("Component collision in cell (" + rowIndex
        + "," + columnIndex + ") is not empty", this);
    }
    if (!this.getColumnsMap().containsKey(columnIndex)) {
      this.getColumnsMap().put(columnIndex, new HashMap<Integer, GridBagLayoutCell>());
    }
    this.getControlCellMap().put(cell.getControl(), cell);
    this.getColumnsMap().get(columnIndex).put(rowIndex, cell);
    if (rowIndex + 1 > this.getRowCount()) {
      this.setRowCount(rowIndex + 1);
    }
    if (columnIndex + 1 > this.getColumnCount()) {
      this.setColumnCount(columnIndex + 1);
    }
  }

  public GridBagLayoutCell getCell(MetaMockElement c) {
    return this.getControlCellMap().get(c);
  }

  // TODO: when removing controls, check if empty columns/rows
  // appears and delete them
  public void removeControl(Integer rowIndex, Integer columnIndex) {
    if (this.getCell(rowIndex, columnIndex) != null) {
      this.removeCell(this.getCell(rowIndex, columnIndex));
    }
  }

  public void removeControl(UIControl c) {
    if (!this.getControlCellMap().containsKey(c)) {
      return;
    }
    GridBagLayoutCell cell = this.getControlCellMap().get(c);
    this.removeCell(cell);
  }

  private void removeCell(GridBagLayoutCell cell) {
    for (Integer iRow = 0; iRow < cell.getRowSpan(); iRow++) {
      for (Integer iCol = 0; iCol < cell.getColumnSpan(); iCol++) {
        this.removeCellEntry(cell.getRow() + iRow, cell.getColumn() + iCol);
      }
    }
    this.removeControlEntry(cell.getControl());
    cell.setGridBoxLayout(null);
  }

  private void removeControlEntry(MetaMockElement control) {
    this.getControlCellMap().remove(control);
  }

  private void removeCellEntry(int rowIndex, int columnIndex) {
    this.getColumnsMap().get(columnIndex).remove(rowIndex);
  }

  void addRow(Integer iRow, List<UIControl> controls) throws GridBagLayoutException {
    for (Integer iCol = 0; iCol < controls.size(); iCol++) {
      this.addControl(iRow, iCol, controls.get(iCol));
    }
  }

  public GridBagLayoutCell getControlCell(UIControl c) {
    return this.getControlCellMap().get(c);
  }

  public <TCell, TRow> List<TRow> visitByRows(GridBagLayoutVisitor<TCell, TRow> v) {
    Set<GridBagLayoutCell> visitedCells = new HashSet<GridBagLayoutCell>();
    GridBagLayoutCell cell;
    List<TRow> rowResults = new ArrayList<TRow>();
    for (Integer row = 0; row < this.getRowCount(); row++) {
      List<TCell> cellResults = new ArrayList<TCell>();
      for (Integer col = 0; col < this.getColumnCount(); col++) {
        cell = this.getCell(row, col);
        if (!visitedCells.contains(cell)) {
          cellResults.add(v.visitCell(cell));
          visitedCells.add(cell);
        }
      }
      rowResults.add(v.visitRow(row, cellResults));
    }
    return rowResults;
  }

  public Collection<UIControl> getControlsInRange(Integer row, Integer col, Integer rowspan, Integer colspan) {
    Set<UIControl> controls = new HashSet<UIControl>();
    for (Integer iRow = 0; iRow < rowspan; iRow++) {
      for (Integer iCol = 0; iCol < colspan; iCol++) {
        if (this.getCell(row + iRow, col + iCol).getControl() != null) {
          controls.add(this.getCell(row + iRow, col + iCol).getControl());
        }
      }
    }
    return controls;
  }

  private Collection<GridBagLayoutCell> getCells() {
    List<GridBagLayoutCell> cells = new ArrayList<GridBagLayoutCell>();
    for (UIControl c : this.getControls()) {
      cells.add(this.getCell(c));
    }
    return cells;
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitGridBoxLayout(this);
  }

  public Object copy() {
    GridBagLayout gbl = new GridBagLayoutImpl();
    try {
      for (GridBagLayoutCell cell : this.getCells()) {
        gbl.add(cell.copy());
      }
    } catch (GridBagLayoutException e) { }
    return gbl;
  }

}
