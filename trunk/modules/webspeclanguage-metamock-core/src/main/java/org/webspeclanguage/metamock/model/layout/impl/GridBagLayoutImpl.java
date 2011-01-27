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
import java.util.Collections;
import java.util.Comparator;
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
  
  public String toString() {
    StringBuffer sb = new StringBuffer("");
    sb.append("[");
    for (GridBagLayoutCell cell : this.getCells()) {
      sb.append(cell.toString());
    }
    sb.append("]");
    return sb.toString();
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
    GridBagLayoutCell cell = this.getCellIfExists(rowIndex, columnIndex);
    if (cell == null) {
      cell = new GridBagLayoutCellImpl(rowIndex, columnIndex, null);
      cell.setGridBagLayout(this);
    }
    return cell;
  }

  public GridBagLayoutCell getCellIfExists(Integer rowIndex, Integer columnIndex) {
    GridBagLayoutCell cell = null;
    Map<Integer, GridBagLayoutCell> columnMap = this.getColumnsMap().get(columnIndex);
    if (columnMap != null) {
      cell = columnMap.get(rowIndex);
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
    Collection<GridBagLayoutCell> collidingCells = this.getCollidingCells(cell);
    if (collidingCells.size() > 0) {
      GridBagLayoutCell collidingCell = collidingCells.iterator().next();
      throw new GridBagLayoutException("Component collision in cell (" + collidingCell.getRow() + "," + collidingCell.getColumn() + "). There is a "
              + collidingCell.getControl().toString() + " in that cell. " + cell.getControl() + " cannot be inserted.", this);
    }
    this.indexCell(cell);
    cell.setGridBagLayout(this);
  }

  private void indexCell(GridBagLayoutCell cell) throws GridBagLayoutException {
    for (Integer iRow = 0; iRow < cell.getRowSpan(); iRow++) {
      for (Integer iCol = 0; iCol < cell.getColumnSpan(); iCol++) {
        this.indexCell(cell, cell.getRow() + iRow, cell.getColumn() + iCol);
      }
    }
  }

  private void indexCell(GridBagLayoutCell cell, Integer rowIndex, Integer columnIndex) throws GridBagLayoutException {
    this.addCellToIndex(cell, rowIndex, columnIndex);
    this.getControlCellMap().put(cell.getControl(), cell);
  }

  private void addCellToIndex(GridBagLayoutCell cell, Integer rowIndex, Integer columnIndex) {
    if (!this.getColumnsMap().containsKey(columnIndex)) {
      this.getColumnsMap().put(columnIndex, new HashMap<Integer, GridBagLayoutCell>());
    }
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
    cell.setGridBagLayout(null);
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
    return v.visitGridBagLayout(this);
  }

  public Object copy() {
    GridBagLayout gbl = new GridBagLayoutImpl();
    try {
      for (GridBagLayoutCell cell : this.getCells()) {
        gbl.add(cell.copy());
      }
    } catch (GridBagLayoutException e) {
    }
    return gbl;
  }

  public Collection<GridBagLayoutCell> getCollidingCells(GridBagLayoutCell cell) {
    Set<GridBagLayoutCell> collidingCells = new HashSet<GridBagLayoutCell>();
    for (Integer iRow = 0; iRow < cell.getRowSpan(); iRow++) {
      for (Integer iCol = 0; iCol < cell.getColumnSpan(); iCol++) {
        GridBagLayoutCell collidingCell = this.getCellIfExists(cell.getRow() + iRow, cell.getColumn() + iCol);
        if (collidingCell != null) {
          collidingCells.add(collidingCell);
        }
      }
    }
    return collidingCells;
  }

  // TODO: Unify repeated methods to work with Rows/Cols with some
  // GridBagLayoutCellAccessStrategy.
  public void insertRow(int rowIndex) {
    for (int iRow = this.getRowCount(); iRow > rowIndex; iRow--) {
      for (int iCol = 0; iCol < this.getColumnCount(); iCol++) {
        GridBagLayoutCell c = this.getCell(iRow - 1, iCol);
        if (c.getControl() != null) {
          this.extendCellToNextRow(iRow, iCol, c);
          this.removeCellFromFirstRow(iRow, iCol, c);
        }
      }
    }
  }

  private void removeCellFromFirstRow(int iRow, int iCol, GridBagLayoutCell c) {
    // if this is the first ocuppied row by the GridBagLayoutCell...
    if (iRow - 1 == c.getRow()) {
      // removes the cell from this place
      this.removeCellEntry(iRow - 1, iCol);
      // if this is the last column occupied by this GridBagLayoutCell,
      // then increments its row position
      if (iCol == c.getColumn() + c.getColumnSpan() - 1) {
        c.setRow(c.getRow() + 1);
      }
    }
  }

  private void extendCellToNextRow(int iRow, int iCol, GridBagLayoutCell c) {
    // if this is the last occupied cell of this GridBagLayoutCell...
    if (iRow == c.getRow() + c.getRowSpan()) {
      // extends the cell one place down and adjust rowspan
      this.addCellToIndex(c, iRow, iCol);
      if (c.getRowSpan() > 1) {
        c.setRowSpan(c.getRowSpan() + 1);
      }
    }
  }

  public void insertColumn(int columnIndex) {
    for (int iColumn = this.getColumnCount(); iColumn > columnIndex; iColumn--) {
      for (int iRow = 0; iRow < this.getRowCount(); iRow++) {
        GridBagLayoutCell c = this.getCell(iRow, iColumn - 1);
        if (c.getControl() != null) {
          this.extendCellToNextColumn(iColumn, iRow, c);
          this.removeCellFromFirstColumn(iColumn, iRow, c);
        }
      }
    }
  }

  private void removeCellFromFirstColumn(int iColumn, int iRow, GridBagLayoutCell c) {
    // if this is the first occupied column by the GridBagLayoutCell...
    if (iColumn - 1 == c.getColumn()) {
      // removes the cell from this place
      this.removeCellEntry(iRow, iColumn - 1);
      // if this is the last row occupied by this GridBagLayoutCell,
      // then increments its column position
      if (iRow == c.getRow() + c.getRowSpan() - 1) {
        c.setColumn(c.getColumn() + 1);
      }
    }
  }

  private void extendCellToNextColumn(int iColumn, int iRow, GridBagLayoutCell c) {
    // if this is the last occupied column by the GridBagLayoutCell...
    if (iColumn == c.getColumn() + c.getColumnSpan()) {
      // extends the cell one place down and adjust columnsSpan
      this.addCellToIndex(c, iRow, iColumn);
      if (c.getColumnSpan() > 1) {
        c.setColumnSpan(c.getColumnSpan() + 1);
      }
    }
  }

  public void addAddShift(final GridBagLayoutCell cell) {
    boolean cellAdded = false;
    while (!cellAdded) {
      try {
        this.add(cell);
        cellAdded = true;
      } catch (GridBagLayoutException e) {
        List<GridBagLayoutCell> collidingCells = new ArrayList<GridBagLayoutCell>(this.getCollidingCells(cell));
        Collections.sort(collidingCells, new SlopeComparator(cell));
        if (collidingCells.size() > 0) {
          for (GridBagLayoutCell collidingCell : collidingCells) {
            float slope = this.calculateSlope(cell, collidingCell);
            if (slope > 1) {
              this.insertRow(collidingCell.getRow());
            } else {
              this.insertColumn(collidingCell.getColumn());
            }
          }
        }
      }
    }
  }

  private float calculateSlope(GridBagLayoutCell cell, GridBagLayoutCell collidingCell) {
    float slope = 0;
    if (collidingCell.getColumn() == cell.getColumn()) {
      slope = Integer.MAX_VALUE;
    } else {
      slope = (collidingCell.getRow() - cell.getRow()) / (collidingCell.getColumn() - cell.getColumn());
    }
    return slope;
  }

  private class SlopeComparator implements Comparator<GridBagLayoutCell> {

    private GridBagLayoutCell cell;

    public SlopeComparator(GridBagLayoutCell cell) {
      super();
      this.setCell(cell);
    }

    private void setCell(GridBagLayoutCell cell) {
      this.cell = cell;
    }

    private GridBagLayoutCell getCell() {
      return cell;
    }

    public int compare(GridBagLayoutCell cell1, GridBagLayoutCell cell2) {
      return (GridBagLayoutImpl.this.calculateSlope(this.getCell(), cell1) < GridBagLayoutImpl.this.calculateSlope(this.getCell(), cell2)) ? -1 : 1;
    }

  }
}
