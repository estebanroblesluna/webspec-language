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
package org.webspeclanguage.mockupdd.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.Rectangle;
import org.webspeclanguage.mockupdd.utils.SuiUtil;
import org.webspeclanguage.mockupdd.utils.WidgetList;

/**
 * @author Jose Matias Rivero
 */
public class SuiUtilTestCase extends SuiTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  public void testIsComposite() {
    assertFalse(SuiUtil.isComposite(this.getFactory().createButton("b1", 0, 0, 100, 100, "aButton")));
    assertTrue(SuiUtil.isComposite(this.getFactory().createPanel("p1", 0, 0, 100, 100, "aContainerId")));
    assertTrue(SuiUtil.isComposite(this.getFactory().createPage("p1", 1, 1, 100, 100, "aPageTitle", "aContainerId")));
  }

  public void testIsHorizontallyIncludedIn() {
    Integer x = 150;
    Integer width = 100;
    assertTrue(SuiUtil.isHorizontallyIncludedIn(
      this.getFactory().createLabel("c1", x + width + 1, 1, width, 98, "labelText"),
      this.getFactory().createPanel("c2", x, 0, width, 100, "aContainerId")));
    assertFalse(SuiUtil.isHorizontallyIncludedIn(
      this.getFactory().createLabel("c3", x + width + 1, 1, width, 100, "labelText"), 
      this.getFactory().createPanel("c4", x, 0, width, 100, "aContainerId")));
    assertFalse(SuiUtil.isHorizontallyIncludedIn(
      this.getFactory().createLabel("c5", x + width + 1, 3, width, 98, "labelText"), 
      this.getFactory().createPanel("c6", 0, 0, width, 100, "aContainerId")));
  }
  
  public void testIsVerticallyIncludedIn() {
    Integer y = 150;
    Integer height = 100;
    assertTrue(SuiUtil.isVerticallyIncludedIn(
      this.getFactory().createLabel("c1", 1, y + height + 1, 98, height, "labelText"),
      this.getFactory().createPanel("c2", 0, y, 100, height, "aContainerId")));
    assertFalse(SuiUtil.isVerticallyIncludedIn(
      this.getFactory().createButton("c3", 1, y + height + 1, 100, height, "labelText"),
      this.getFactory().createPanel("c4", 0, y, 100, height, "aContainerId")));
    assertFalse(SuiUtil.isVerticallyIncludedIn(
      this.getFactory().createButton("c5", 3, y + height + 1, 98, height, "labelText"),
      this.getFactory().createPanel("c6", 0, y, 100, height, "aContainerId")));
  }
  
  public void testIsIncludedIn() {
    assertTrue(SuiUtil.isIncludedIn(
      this.getFactory().createLabel("c1", 1, 1, 98, 98, "labelText"),
      this.getFactory().createPanel("c2", 0, 0, 100, 100, "aContainerId")));
    assertFalse(SuiUtil.isIncludedIn(
      this.getFactory().createLabel("c3", 1, 1, 100, 98, "labelText"),
      this.getFactory().createPanel("c4", 0, 0, 100, 100, "aContainerId")));
    assertFalse(SuiUtil.isIncludedIn(
      this.getFactory().createLabel("c5", 0, 1, 98, 98, "labelText"),
      this.getFactory().createPanel("c6", 0, 0, 100, 100, "aContainerId")));
  }
  
  public void testWidgetsIncludedIn() {
    Label label = this.getFactory().createLabel("c1", 1, 1, 98, 98, "labelText");
    Collection<Widget> ctrls = Arrays.asList(
      (Widget)
      label,
      this.getFactory().createButton("c2", 1, 1, 100, 100, "buttonText")
    );
    Collection<Widget> included = SuiUtil.widgetsIncludedIn(
      this.getFactory().createPanel("c3", 0, 0, 100, 100, "aContainerId"), ctrls);
    assertEquals(1, included.size());
    assertSame(label, included.iterator().next());
  }
  
  public void testWidgetIsKindOf() {
    assertTrue(SuiUtil.widgetIsKindOf(this.getFactory().createButton("c1", 0, 0, 100, 100, "button"), Button.class));
    assertTrue(SuiUtil.widgetIsKindOf(this.getFactory().createButton("c2", 0, 0, 100, 100, "button"), SimpleWidget.class));
    assertFalse(SuiUtil.widgetIsKindOf(this.getFactory().createButton("c3", 0, 0, 100, 100, "button"), CompositeWidget.class));
    assertTrue(SuiUtil.widgetIsKindOf(this.getFactory().createButton("c4", 0, 0, 100, 100, "button"), Widget.class));
    
    assertFalse(SuiUtil.widgetIsKindOf(this.getFactory().createPanel("c5", 0, 0, 100, 100, "containerId"), Button.class));
    assertFalse(SuiUtil.widgetIsKindOf(this.getFactory().createPanel("c6", 0, 0, 100, 100, "containerId"), SimpleWidget.class));
    assertTrue(SuiUtil.widgetIsKindOf(this.getFactory().createPanel("c7", 0, 0, 100, 100, "containerId"), CompositeWidget.class));
    assertTrue(SuiUtil.widgetIsKindOf(this.getFactory().createPanel("c8", 0, 0, 100, 100, "containerId"), Widget.class));
  }
  
  @SuppressWarnings("unchecked")
  public void testFilterWidgetsByType() {
    Collection<Widget> ctrls = Arrays.asList(
      (Widget)
      this.getFactory().createButton("c1", 0, 0, 100, 100, "button"),
      this.getFactory().createPanel("c1", 0, 0, 100, 100, "containerId"));
    assertEquals(2, SuiUtil.filterWidgetsByType(ctrls, Widget.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(ctrls, SimpleWidget.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(ctrls, CompositeWidget.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(ctrls, Button.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(ctrls, Panel.class).size());
    assertEquals(0, SuiUtil.filterWidgetsByType(ctrls, Label.class).size());
  }
  
  public void testGetParentFromCollection() {
    CompositeWidget expectedParent = this.getFactory().createPanel("parent", 10, 10, 80, 80, "aContainerId");
    Widget childWidget = this.getFactory().createLabel("child", 40, 40, 20, 20, "labelText");
    Widget superParent = this.getFactory().createPanel("anotherWidget", 0, 0, 100, 100, "aContainerId");
    Collection<Widget> ctrls = Arrays.asList((Widget) childWidget, superParent, expectedParent);
    assertSame(expectedParent, SuiUtil.getParentWidgetFromCollection(childWidget, ctrls));
    assertSame(superParent, SuiUtil.getParentWidgetFromCollection(expectedParent, ctrls));
    assertNull(SuiUtil.getParentWidgetFromCollection(superParent, ctrls));
  }
  
  public void testSortWidgetsByX() {
    List<Widget> sortedCtrls = SuiUtil.sortWidgetsByXCoord(Arrays.asList(
      (Widget)
      this.getFactory().createButton("c1", 345, 0, 100, 100, "button"),
      this.getFactory().createButton("c2", 123, 0, 100, 100, "button"),
      this.getFactory().createButton("c3", 2, 0, 100, 100, "button")));
    for (Widget c : sortedCtrls)
      if (sortedCtrls.indexOf(c) < sortedCtrls.size() - 1)
        assertTrue(sortedCtrls.get(sortedCtrls.indexOf(c)).getX() <
            sortedCtrls.get(sortedCtrls.indexOf(c) + 1).getX());
  }
  
  public void testSortWidgetsByY() {
    List<Widget> sortedCtrls = SuiUtil.sortWidgetsByYCoord(Arrays.asList(
      (Widget)
      this.getFactory().createButton("c1", 0, 345, 100, 100, "button"),
      this.getFactory().createButton("c2", 0, 123, 100, 100, "button"),
      this.getFactory().createButton("c3", 0, 2, 100, 100, "button")));
    for (Widget c : sortedCtrls)
      if (sortedCtrls.indexOf(c) < sortedCtrls.size() - 1)
        assertTrue(sortedCtrls.get(sortedCtrls.indexOf(c)).getY() <
            sortedCtrls.get(sortedCtrls.indexOf(c) + 1).getY());
  }
  
  public void testSortWidgets() {
	  List<Widget> sortedCtrls = SuiUtil.sortWidgets(
		  Arrays.asList(
		      (Widget)
		      this.getFactory().createButton("c3", 0, 345, 100, 100, "b3"),
		      this.getFactory().createButton("c1", 0, 123, 100, 100, "b2"),
		      this.getFactory().createButton("c2", 0, 2, 100, 100, "b1")),
	      new Comparator<Widget>() {
			public int compare(Widget c1, Widget c2) {
				return c1.getWidgetId().compareTo(c2.getWidgetId());
			}
		});
	  assertEquals("c1", sortedCtrls.get(0).getWidgetId());
	  assertEquals("c2", sortedCtrls.get(1).getWidgetId());
	  assertEquals("c3", sortedCtrls.get(2).getWidgetId());
  }
  
  @SuppressWarnings("unchecked")
  public void testGetAllWidgetsRecursively() {
	  Page p = this.getFactory().createPage("page", 0, 0, 1000, 1000, "Page", "containerId");
	  p.addChild(this.getFactory().createButton("b1", 100, 100, 50, 50, "button 1"));
	  Panel panel = this.getFactory().createPanel("panel", 200, 200, 500, 500, "containerId");
	  p.addChild(panel);
	  panel.addChild(this.getFactory().createButton("b2", 250, 250, 50, 50, "button 2"));
	  panel.addChild(this.getFactory().createLabel("l1", 350, 250, 50, 50, "label"));
	  
	  assertEquals(2, SuiUtil.getAllWidgetsRecursively(panel).size());
	  assertEquals(4, SuiUtil.getAllWidgetsRecursively(p).size());
	  
	  assertEquals("b2", SuiUtil.filterWidgetsByType(
		  SuiUtil.getAllWidgetsRecursively(panel), Button.class).iterator().next().getWidgetId());
	  assertEquals("l1", SuiUtil.filterWidgetsByType(
		  SuiUtil.getAllWidgetsRecursively(panel), Label.class).iterator().next().getWidgetId());
	  assertEquals(2, SuiUtil.filterWidgetsByType(
		  SuiUtil.getAllWidgetsRecursively(p), Button.class).size());
	  assertEquals("l1", SuiUtil.filterWidgetsByType(
		  SuiUtil.getAllWidgetsRecursively(p), Label.class).iterator().next().getWidgetId());
	  assertEquals(1, SuiUtil.filterWidgetsByType(
			  SuiUtil.getAllWidgetsRecursively(p), Panel.class).size());  
  }
  
  public void testGetCollidingWidgets() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 20, 20, 85, 85, "button 1"), 
      this.getFactory().createButton("c2", 120, 80, 10, 30, "button 2"),
      this.getFactory().createButton("c3", 310, 310, 100, 100, "button 3"));
    Collection<Widget> collidingWidgets = 
      SuiUtil.getCollidingWidgets(widgets, this.getFactory().createLabel("c4", 100, 100, 200, 200, "button 4"));
    assertEquals(2, collidingWidgets.size());
    assertTrue(collidingWidgets.contains(widgets.get(0)));
    assertTrue(collidingWidgets.contains(widgets.get(1)));
    assertFalse(collidingWidgets.contains(widgets.get(2)));
  }
  
  public void testGetVerticallyCollidingWidgets() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 50, "button 1"), 
      this.getFactory().createButton("c2", 100, 100, 50, 50, "button 2"),
      this.getFactory().createButton("c3", 200, 200, 50, 50, "button 3"));
    Collection<Widget> collidingWidgets = 
      SuiUtil.getVerticallyCollidingWidgets(widgets, this.getFactory().createLabel("c4", 75, 0, 75, 0, "button 4"));
    assertEquals(1, collidingWidgets.size());
    assertFalse(collidingWidgets.contains(widgets.get(0)));
    assertTrue(collidingWidgets.contains(widgets.get(1)));
    assertFalse(collidingWidgets.contains(widgets.get(2)));
  }

  public void testGetHorizontallyCollidingWidgets() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 50, "button 1"), 
      this.getFactory().createButton("c2", 100, 100, 50, 50, "button 2"),
      this.getFactory().createButton("c3", 200, 200, 50, 50, "button 3"));
    Collection<Widget> collidingWidgets = 
      SuiUtil.getHorizontallyCollidingWidgets(widgets, this.getFactory().createLabel("c4", 0, 75, 0, 75, "button 4"));
    assertEquals(1, collidingWidgets.size());
    assertFalse(collidingWidgets.contains(widgets.get(0)));
    assertTrue(collidingWidgets.contains(widgets.get(1)));
    assertFalse(collidingWidgets.contains(widgets.get(2)));
  }
  
  public void testGetParents() {
    Page page = this.getFactory().createPage("page", 0, 0, 1000, 1000, "Page", "containerId");
    Panel panel = this.getFactory().createPanel("panel", 200, 200, 500, 500, "containerId");
    page.addChild(panel);
    Button button = this.getFactory().createButton("b2", 250, 250, 50, 50, "button 2");
    panel.addChild(button);
    Collection<Widget> parents = SuiUtil.getParents(button);
    assertTrue(parents.contains(page));
    assertTrue(parents.contains(panel));
  }
  
  @SuppressWarnings("unchecked")
  public void testExcludeWidgetTypes() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 50, "button 1"), 
      this.getFactory().createLabel("c2", 100, 100, 50, 50, "label 1"),
      this.getFactory().createPanel("c3", 200, 200, 50, 50, "panel 1"));
    Collection<Widget> widgetsNotExcluded = SuiUtil.excludeWidgetTypes(widgets, Label.class);
    assertEquals(2, widgetsNotExcluded.size());
    assertTrue(widgetsNotExcluded.contains(widgets.get(0)));
    assertTrue(widgetsNotExcluded.contains(widgets.get(2)));
    widgetsNotExcluded = SuiUtil.excludeWidgetTypes(widgetsNotExcluded, SimpleWidget.class);
    assertEquals(1, widgetsNotExcluded.size());
    assertTrue(widgetsNotExcluded.contains(widgets.get(2)));
    widgetsNotExcluded = SuiUtil.excludeWidgetTypes(widgetsNotExcluded, CompositeWidget.class);
    assertEquals(0, widgetsNotExcluded.size());
  }
  
  public void testGetAllVerticallyCollidingWidgets() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 50, "button 1"), 
      this.getFactory().createButton("c2", 30, 60, 50, 50, "button 2"), 
      this.getFactory().createButton("c3", 50, 120, 50, 50, "button 3"),
      this.getFactory().createButton("c4", 20, 180, 50, 50, "button 4")
    );
    Collection<Widget> ctrls = SuiUtil.getAllVerticallyCollidingWidgets(widgets, widgets.get(0));
    assertEquals(3, ctrls.size());
    assertTrue(ctrls.contains(widgets.get(1)));
    assertTrue(ctrls.contains(widgets.get(2)));
    assertTrue(ctrls.contains(widgets.get(3)));
  }
  
  public void testGetAllHorizontallyCollidingWidgets() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 50, "button 1"), 
      this.getFactory().createButton("c2", 60, 30, 50, 50, "button 2"), 
      this.getFactory().createButton("c3", 120, 50, 50, 50, "button 3"),
      this.getFactory().createButton("c4", 180, 20, 50, 50, "button 4")
    );
    Collection<Widget> ctrls = SuiUtil.getAllHorizontallyCollidingWidgets(widgets, widgets.get(0));
    assertEquals(3, ctrls.size());
    assertTrue(ctrls.contains(widgets.get(1)));
    assertTrue(ctrls.contains(widgets.get(2)));
    assertTrue(ctrls.contains(widgets.get(3)));
  }
  
  public void testGetWidgetsGroupedInColumns() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 100, 50, "button 1"), 
      this.getFactory().createLabel("c2", 90, 100, 50, 50, "label 1"),
      this.getFactory().createLabel("c3", 10, 110, 10, 10, "label 2"),
      this.getFactory().createCheckBox("c4", 200, 200, 50, 50, "checkbox 1"),
      this.getFactory().createLabel("c5", 190, 300, 50, 50, "label 3"),
      this.getFactory().createButton("c6", 300, 400, 50, 110, "button 2"));
    List<WidgetList> columns = SuiUtil.getWidgetsGroupedInColumns(widgets);
    assertEquals(3, columns.size());
    assertTrue(columns.get(0).getWidgets().contains(widgets.get(0)));
    assertTrue(columns.get(0).getWidgets().contains(widgets.get(1)));
    assertTrue(columns.get(0).getWidgets().contains(widgets.get(2)));
    assertTrue(columns.get(1).getWidgets().contains(widgets.get(3)));
    assertTrue(columns.get(1).getWidgets().contains(widgets.get(4)));
    assertTrue(columns.get(2).getWidgets().contains(widgets.get(5)));
  }
  
  public void testGetWidgetsGroupedInRows() {
    List<Widget> widgets = Arrays.asList((Widget) 
      this.getFactory().createButton("c1", 0, 0, 50, 100, "button 1"), 
      this.getFactory().createLabel("c2", 100, 90, 50, 50, "label 1"),
      this.getFactory().createCheckBox("c3", 200, 200, 50, 50, "checkbox 1"),
      this.getFactory().createLabel("c4", 300, 190, 50, 50, "label 2"),
      this.getFactory().createButton("c5", 400, 300, 110, 50, "button 2"));
    List<WidgetList> columns = SuiUtil.getWidgetsGroupedInRows(widgets);
    assertEquals(3, columns.size());
    assertTrue(columns.get(0).getWidgets().contains(widgets.get(0)));
    assertTrue(columns.get(0).getWidgets().contains(widgets.get(1)));
    assertTrue(columns.get(1).getWidgets().contains(widgets.get(2)));
    assertTrue(columns.get(1).getWidgets().contains(widgets.get(3)));
    assertTrue(columns.get(2).getWidgets().contains(widgets.get(4)));
  }
  
  public void testAreGraphicallySimilar() {
    assertFalse(SuiUtil.areGraphicallySimilar(
      this.getFactory().createButton("c1", 100, 100, 50, 100, "button 1"),
      this.getFactory().createLabel("c2", 300, 300, 50, 100, "label 1"),
      1
    ));
    assertTrue(SuiUtil.areGraphicallySimilar(
      this.getFactory().createButton("c1", 100, 100, 50, 100, "button 1"),
      this.getFactory().createButton("c2", 300, 300, 50, 100, "label 1"),
      1
    ));
    assertTrue(SuiUtil.areGraphicallySimilar(
      this.getFactory().createButton("c1", 100, 100, 50, 100, "button 1"),
      this.getFactory().createButton("c2", 300, 300, 52, 98, "label 1"),
      2
    ));
    assertFalse(SuiUtil.areGraphicallySimilar(
      this.getFactory().createButton("c1", 100, 100, 50, 100, "button 1"),
      this.getFactory().createButton("c2", 300, 300, 53, 98, "label 1"),
      2
    ));
  }
  
  public void testGetSurroundingRectangle() {
    Rectangle r = SuiUtil.getSurroundingRectangle(Arrays.asList((Widget)
      this.getFactory().createButton("c1", 100, 100, 50, 100, "button 1"),
      this.getFactory().createButton("c2", 100, 300, 50, 100, "button 2"),
      this.getFactory().createButton("c3", 300, 100, 50, 100, "button 3"),
      this.getFactory().createButton("c4", 250, 250, 250, 50, "button 4"))
      , 1);
    assertEquals((Integer) 99, r.getLeft());
    assertEquals((Integer) 501, r.getRight());
    assertEquals((Integer) 99, r.getTop());
    assertEquals((Integer) 401, r.getBottom());
  }
  
}

