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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;

import java.util.Collection;
import java.util.List;

import org.webspeclanguage.metamock.model.MetaMockTestCase;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.impl.GridBagLayoutImpl;
import org.webspeclanguage.metamock.utils.ValueHolder;

/**
 * @author Jose Matias Rivero
 */
public class GridBagLayoutImplTestCase extends MetaMockTestCase {

  public void setUp() throws Exception {
    super.setUp();
  }

  private GridBagLayoutImpl getNewGridBagLayout() {
    return new GridBagLayoutImpl();
  }

  public void testAddControlsToGridBagLayout() throws GridBagLayoutException {
    GridBagLayoutImpl gbl = this.getNewGridBagLayout();
    GridBagLayoutCell cell1 = this.createCell(0, 0, 1, 1, this.getFactory().createButton("b1", 0, 0, 100, 100, "button 1"), gbl);
    GridBagLayoutCell cell2 = this.createCell(0, 1, 1, 1, this.getFactory().createButton("b2", 0, 0, 100, 100, "button 2"), gbl);
    GridBagLayoutCell cell3 = this.createCell(1, 0, 1, 2, this.getFactory().createButton("b3", 0, 0, 100, 100, "button 3"), gbl);
    GridBagLayoutCell cell4 = this.createCell(2, 0, 2, 3, this.getFactory().createButton("b4", 0, 0, 100, 100, "button 4"), gbl);
    GridBagLayoutCell cell5 = this.createCell(2, 2, 1, 1, this.getFactory().createButton("b5", 0, 0, 100, 100, "button 4"), gbl);
    gbl.add(cell1);
    gbl.add(cell2);
    gbl.add(cell3);
    assertEquals((Integer) 2, gbl.getColumnCount());
    assertEquals((Integer) 2, gbl.getRowCount());
    assertSame(gbl.getCell(0, 0), cell1);
    assertSame(gbl.getCell(0, 1), cell2);
    assertSame(gbl.getCell(1, 0), cell3);
    assertSame(gbl.getCell(1, 1), cell3);
    
    Collection<UIControl> controls = gbl.getRowContent(0);
    assertTrue(controls.contains(cell1.getControl()));
    assertTrue(controls.contains(cell2.getControl()));
    assertFalse(controls.contains(cell3.getControl()));
    
    controls = gbl.getRowContent(1);
    assertFalse(controls.contains(cell1.getControl()));
    assertFalse(controls.contains(cell2.getControl()));
    assertTrue(controls.contains(cell3.getControl()));
    
    controls = gbl.getColumnContent(0);
    assertTrue(controls.contains(cell1.getControl()));
    assertFalse(controls.contains(cell2.getControl()));
    assertTrue(controls.contains(cell3.getControl()));
    
    controls = gbl.getColumnContent(1);
    assertFalse(controls.contains(cell1.getControl()));
    assertTrue(controls.contains(cell2.getControl()));
    assertTrue(controls.contains(cell3.getControl()));
    
    gbl.add(cell4);
    assertEquals((Integer) 3, gbl.getColumnCount());
    assertEquals((Integer) 4, gbl.getRowCount());
    assertSame(gbl.getCell(0, 0), cell1);
    assertSame(gbl.getCell(0, 1), cell2);
    assertNull(gbl.getCell(0, 2).getControl());
    assertSame(gbl.getCell(1, 0), cell3);
    assertSame(gbl.getCell(1, 1), cell3);
    assertNull(gbl.getCell(1, 2).getControl());
    assertSame(gbl.getCell(2, 0), cell4);
    assertSame(gbl.getCell(2, 1), cell4);
    assertSame(gbl.getCell(2, 2), cell4);
    try {
      gbl.add(cell5);
      fail();
    } catch (GridBagLayoutException e) {

    } catch (Exception e) {
      fail();
    }
    
    gbl.removeControl(1, 1);
    assertSame(gbl.getCell(0, 0), cell1);
    assertSame(gbl.getCell(0, 1), cell2);
    assertNull(gbl.getCell(0, 2).getControl());
    assertNull(gbl.getCell(1, 0).getControl());
    assertNull(gbl.getCell(1, 1).getControl());
    assertNull(gbl.getCell(1, 2).getControl());
    assertSame(gbl.getCell(2, 0), cell4);
    assertSame(gbl.getCell(2, 1), cell4);
    assertSame(gbl.getCell(2, 2), cell4);
    
    gbl.removeControl(cell1.getControl());
    assertNull(gbl.getCell(0, 0).getControl());
    assertSame(gbl.getCell(0, 1), cell2);
    assertNull(gbl.getCell(0, 2).getControl());
    assertNull(gbl.getCell(1, 0).getControl());
    assertNull(gbl.getCell(1, 1).getControl());
    assertNull(gbl.getCell(1, 2).getControl());
    assertSame(gbl.getCell(2, 0), cell4);
    assertSame(gbl.getCell(2, 1), cell4);
    assertSame(gbl.getCell(2, 2), cell4);
    
  }
  
  public void testVisitGridBagLayoutByRows() throws GridBagLayoutException {
    GridBagLayoutImpl gbl = new GridBagLayoutImpl();
    final GridBagLayoutCell cell1 = this.createCell(0, 0, 1, 1, this.getFactory().createButton("b1", 0, 0, 100, 100, "button 1"), gbl);
    final GridBagLayoutCell cell2 = this.createCell(0, 1, 1, 1, this.getFactory().createButton("b2", 0, 0, 100, 100, "button 2"), gbl);
    final GridBagLayoutCell cell3 = this.createCell(1, 0, 1, 2, this.getFactory().createButton("b3", 0, 0, 100, 100, "button 3"), gbl);
    gbl.add(cell1);
    gbl.add(cell2);
    gbl.add(cell3);
    final ValueHolder<Integer> cellCounter = new ValueHolder<Integer>(0);
    List<List<Integer>> visitedCoords = gbl.visitByRows(new GridBagLayoutVisitor<Integer, List<Integer>>() {
      
      public Integer visitCell(GridBagLayoutCell c) {
        cellCounter.setValue(cellCounter.getValue() + 1);
        if (c.getColumn() == 0 & c.getRow() == 0)
          assertSame(c.getControl(), cell1.getControl());
        if (c.getColumn() == 1 & c.getRow() == 0)
          assertSame(c.getControl(), cell2.getControl());
        if (c.getRow() == 1)
          assertSame(c.getControl(), cell3.getControl());
        return c.getRow() * 10 + c.getColumn();
      }

      public List<Integer> visitRow(Integer columnIndex, List<Integer> visitedRowContent) {
        return visitedRowContent;
      }
      
    });
    assertEquals(2, visitedCoords.get(0).size());
    assertEquals(1, visitedCoords.get(1).size());
    assertTrue(visitedCoords.get(0).contains(0));
    assertTrue(visitedCoords.get(0).contains(1));
    assertTrue(visitedCoords.get(1).contains(10));
    assertEquals(3, (int)cellCounter.getValue());
  }

  private GridBagLayoutCell createCell(Integer row, Integer column, Integer rowspan, Integer colspan, UIControl control, GridBagLayout gridBoxLayout) {
    GridBagLayoutCell c = createMock(GridBagLayoutCell.class);
    expect(c.getColumn()).andStubReturn(column);
    expect(c.getRow()).andStubReturn(row);
    expect(c.getColumnSpan()).andStubReturn(colspan);
    expect(c.getRowSpan()).andStubReturn(rowspan);
    expect(c.getControl()).andStubReturn(control);
    expect(c.getGridBoxLayout()).andStubReturn(gridBoxLayout);
    c.setGridBoxLayout(anyObject(GridBagLayout.class));
    expectLastCall().asStub();
    replay(c);
    return c;
  }

}
