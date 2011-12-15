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
package org.webspeclanguage.mockupdd.sui.model.layout;

import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.RecursiveGridBagLayoutFactory;
import org.webspeclanguage.mockupdd.utils.SuiUtil;


/**
 * @author Jose Matias Rivero
 */
public class RecursiveGridBagLayoutFactoryTestCase extends SuiTestCase {

  public void setUp() throws Exception {
    super.setUp();
  }
  
  public void testGridBagLayoutInference() {
    List<Widget> widgets = Arrays.asList(
      (Widget)
      this.getFactory().createButton("b1", 0, 0, 200, 50, "button 1"),
      this.getFactory().createButton("b2", 0, 60, 50, 50, "button 2"),
      this.getFactory().createButton("b3", 60, 60, 50, 50, "button 3"),
      this.getFactory().createButton("b4", 120, 60, 40, 140, "button 4"),
      this.getFactory().createButton("b5", 0, 120, 100, 80, "button 5")
    );
      
    GridBagLayout gbl = new RecursiveGridBagLayoutFactory().createLayout(widgets);
    
    for (Widget c : widgets)
      assertEquals(0, SuiUtil.getCollidingWidgets(widgets, c).size());
    
    assertEquals((Integer)3, gbl.getRowCount());
    assertEquals((Integer)3, gbl.getColumnCount());
    
    GridBagLayoutCell cell = gbl.getCell(0, 0);
    assertSame(cell.getWidget(), widgets.get(0));
    assertEquals((Integer)1, cell.getRowSpan());
    assertEquals((Integer)3, cell.getColumnSpan());
    
    cell = gbl.getCell(1, 0);
    assertSame(cell.getWidget(), widgets.get(1));
    assertEquals((Integer)1, cell.getRowSpan());
    assertEquals((Integer)1, cell.getColumnSpan());
    
    cell = gbl.getCell(1, 1);
    assertSame(cell.getWidget(), widgets.get(2));
    assertEquals((Integer)1, cell.getRowSpan());
    assertEquals((Integer)1, cell.getColumnSpan());
    
    cell = gbl.getCell(1, 2);
    assertSame(cell.getWidget(), widgets.get(3));
    assertEquals((Integer)2, cell.getRowSpan());
    assertEquals((Integer)1, cell.getColumnSpan());
    
    cell = gbl.getCell(2, 0);
    assertSame(cell.getWidget(), widgets.get(4));
    assertEquals((Integer)1, cell.getRowSpan());
    assertEquals((Integer)2, cell.getColumnSpan());
  }
  
}
