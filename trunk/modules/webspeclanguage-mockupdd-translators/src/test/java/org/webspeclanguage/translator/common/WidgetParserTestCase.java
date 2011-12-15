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
package org.webspeclanguage.translator.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.NumericSpinner;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public abstract class WidgetParserTestCase extends SuiTestCase {

  public abstract void testModelTranslation() throws SuiTranslationException, Exception;

  @SuppressWarnings("unchecked")
  protected void assertConditionsOnInviteFriendsPage(Page page) {
    Collection<Widget> widgets = SuiUtil.filterWidgetsByType(page.getWidgets(), Label.class);

    assertEquals(widgets.size(), 1);
    assertEquals(((Label) widgets.iterator().next()).getText(), "Event: Chris' birthday party");

    widgets = SuiUtil.filterWidgetsByType(page.getWidgets(), org.webspeclanguage.mockupdd.sui.model.List.class);

    assertEquals(widgets.size(), 1);

    List<Widget> widgetList = SuiUtil.sortWidgetsByXCoord(SuiUtil.filterWidgetsByType(page.getWidgets(), Button.class));

    assertEquals(widgetList.size(), 2);
    assertEquals(((Button) widgetList.get(0)).getText(), "Return");
    assertEquals(((Button) widgetList.get(1)).getText(), "Invite");

  }

  @SuppressWarnings("unchecked")
  protected void assertOnNewEventPage(Page page) {
    List<Widget> widgets = SuiUtil.sortWidgetsByYCoord(SuiUtil.filterWidgetsByType(page.getWidgets(), Label.class));

    assertEquals(widgets.size(), 5);

    Label l = (Label) widgets.get(0);
    assertEquals(l.getText(), "Name:");

    l = (Label) widgets.get(1);
    assertEquals(l.getText(), "Type:");

    l = (Label) widgets.get(2);
    assertEquals(l.getText(), "Place:");

    l = (Label) widgets.get(3);
    assertEquals(l.getText(), "Date/Time:");

    l = (Label) widgets.get(4);
    assertEquals(l.getText(), "Visibility:");

    widgets = SuiUtil.sortWidgetsByXCoord(SuiUtil.filterWidgetsByType(page.getWidgets(), Button.class));

    assertEquals(widgets.size(), 2);
    assertEquals(((Button) widgets.get(0)).getText(), "Cancel");
    assertEquals(((Button) widgets.get(1)).getText(), "Ok");

    widgets = SuiUtil.sortWidgetsByYCoord(SuiUtil.filterWidgetsByType(page.getWidgets(), RadioButton.class));

    assertEquals(widgets.size(), 2);
    assertEquals(((RadioButton) widgets.get(0)).getText(), "Private");
    assertEquals(((RadioButton) widgets.get(1)).getText(), "Public");
  }

  @SuppressWarnings("unchecked")
  protected void assertModelFeatures(SuiModel model) {
    Collection<Widget> annotations = SuiUtil.filterWidgetsByType(model.getWidgetsOutsidePages(), Annotation.class);
    
    assertEquals(2, annotations.size());
    
    for (Iterator iterator = annotations.iterator(); iterator.hasNext();) {
      Annotation a = (Annotation) iterator.next();
      assertTrue(SuiUtil.widgetIsKindOf(a.getTargetElement(), Page.class));
      if (a.getContent().equals("layout: \"vbox\""))
        assertTrue(((Page) a.getTargetElement()).getTitle().equals("Invite friends to event"));
      else if (a.getContent().equals("layout: \"gridbag\""))
        assertTrue(((Page) a.getTargetElement()).getTitle().equals("New event"));
      else
        fail("Unexpected annotation content");   
    }
;
  }

  @SuppressWarnings("unchecked")
  protected void assertConditionsOnTaskManagerPage(Page page) {
    Collection<Widget> widgets = SuiUtil.filterWidgetsByType(page.getWidgets(), Table.class);
    assertEquals(1, widgets.size());
    
    assertEquals(1, SuiUtil.filterWidgetsByType(page.getWidgets(), Image.class).size());
    
    Table t = (Table) widgets.iterator().next();
    assertEquals(2, t.getColumns().size());
    assertEquals("Priority", t.getColumns().get(0).getLabel());
    assertEquals("Description", t.getColumns().get(1).getLabel());
    
    widgets = SuiUtil.filterWidgetsByType(page.getWidgets(), Panel.class);
    assertEquals(1, widgets.size());
    Panel p = (Panel) widgets.iterator().next();
    
    widgets = SuiUtil.filterWidgetsByType(p.getWidgets(), Link.class);
    assertEquals(1, widgets.size());
    Link link = (Link) widgets.iterator().next();
    assertEquals("Add Task", link.getText());
    
    assertEquals(1, SuiUtil.filterWidgetsByType(p.getWidgets(), TextArea.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(p.getWidgets(), NumericSpinner.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(p.getWidgets(), SelectableList.class).size());

    widgets = SuiUtil.filterWidgetsByType(page.getWidgets(), Label.class);
    assertEquals(1, widgets.size());
    Label label = (Label) widgets.iterator().next();
    assertEquals("New task", label.getText());
    
    List<Widget> widgetList = SuiUtil.sortWidgetsByYCoord(
    	SuiUtil.filterWidgetsByType(p.getWidgets(), Label.class));
    assertEquals(3, widgetList.size());
    
    assertEquals("Description:", ((Label) widgetList.get(0)).getText());
    assertEquals("Priority:", ((Label) widgetList.get(1)).getText());
    assertEquals("Category:", ((Label) widgetList.get(2)).getText());    
    
  }

}
