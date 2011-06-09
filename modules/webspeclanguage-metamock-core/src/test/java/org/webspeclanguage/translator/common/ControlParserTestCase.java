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

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.SuiTestCase;
import org.webspeclanguage.metamock.model.NumericSpinner;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.RadioButton;
import org.webspeclanguage.metamock.model.SelectableList;
import org.webspeclanguage.metamock.model.Table;
import org.webspeclanguage.metamock.model.TextArea;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.translator.SuiTranslationException;
import org.webspeclanguage.metamock.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public abstract class ControlParserTestCase extends SuiTestCase {

  public abstract void testModelTranslation() throws SuiTranslationException, Exception;

  @SuppressWarnings("unchecked")
  protected void assertConditionsOnInviteFriendsPage(Page page) {
    Collection<Widget> controls = SuiUtil.filterControlsByType(page.getControls(), Label.class);

    assertEquals(controls.size(), 1);
    assertEquals(((Label) controls.iterator().next()).getText(), "Event: Chris' birthday party");

    controls = SuiUtil.filterControlsByType(page.getControls(), org.webspeclanguage.metamock.model.List.class);

    assertEquals(controls.size(), 1);

    List<Widget> controlList = SuiUtil.sortControlsByXCoord(SuiUtil.filterControlsByType(page.getControls(), Button.class));

    assertEquals(controlList.size(), 2);
    assertEquals(((Button) controlList.get(0)).getText(), "Return");
    assertEquals(((Button) controlList.get(1)).getText(), "Invite");

  }

  @SuppressWarnings("unchecked")
  protected void assertOnNewEventPage(Page page) {
    List<Widget> controls = SuiUtil.sortControlsByYCoord(SuiUtil.filterControlsByType(page.getControls(), Label.class));

    assertEquals(controls.size(), 5);

    Label l = (Label) controls.get(0);
    assertEquals(l.getText(), "Name:");

    l = (Label) controls.get(1);
    assertEquals(l.getText(), "Type:");

    l = (Label) controls.get(2);
    assertEquals(l.getText(), "Place:");

    l = (Label) controls.get(3);
    assertEquals(l.getText(), "Date/Time:");

    l = (Label) controls.get(4);
    assertEquals(l.getText(), "Visibility:");

    controls = SuiUtil.sortControlsByXCoord(SuiUtil.filterControlsByType(page.getControls(), Button.class));

    assertEquals(controls.size(), 2);
    assertEquals(((Button) controls.get(0)).getText(), "Cancel");
    assertEquals(((Button) controls.get(1)).getText(), "Ok");

    controls = SuiUtil.sortControlsByYCoord(SuiUtil.filterControlsByType(page.getControls(), RadioButton.class));

    assertEquals(controls.size(), 2);
    assertEquals(((RadioButton) controls.get(0)).getText(), "Private");
    assertEquals(((RadioButton) controls.get(1)).getText(), "Public");
  }

  @SuppressWarnings("unchecked")
  protected void assertModelFeatures(SuiModel model) {
    Collection<Widget> annotations = SuiUtil.filterControlsByType(model.getControlsOutsidePages(), Annotation.class);
    
    assertEquals(2, annotations.size());
    
    for (Iterator iterator = annotations.iterator(); iterator.hasNext();) {
      Annotation a = (Annotation) iterator.next();
      assertTrue(SuiUtil.controlIsKindOf(a.getTargetElement(), Page.class));
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
    Collection<Widget> controls = SuiUtil.filterControlsByType(page.getControls(), Table.class);
    assertEquals(1, controls.size());
    
    assertEquals(1, SuiUtil.filterControlsByType(page.getControls(), Image.class).size());
    
    Table t = (Table) controls.iterator().next();
    assertEquals(2, t.getColumns().size());
    assertEquals("Priority", t.getColumns().get(0).getLabel());
    assertEquals("Description", t.getColumns().get(1).getLabel());
    
    controls = SuiUtil.filterControlsByType(page.getControls(), Panel.class);
    assertEquals(1, controls.size());
    Panel p = (Panel) controls.iterator().next();
    
    controls = SuiUtil.filterControlsByType(p.getControls(), Link.class);
    assertEquals(1, controls.size());
    Link link = (Link) controls.iterator().next();
    assertEquals("Add Task", link.getText());
    
    assertEquals(1, SuiUtil.filterControlsByType(p.getControls(), TextArea.class).size());
    assertEquals(1, SuiUtil.filterControlsByType(p.getControls(), NumericSpinner.class).size());
    assertEquals(1, SuiUtil.filterControlsByType(p.getControls(), SelectableList.class).size());

    controls = SuiUtil.filterControlsByType(page.getControls(), Label.class);
    assertEquals(1, controls.size());
    Label label = (Label) controls.iterator().next();
    assertEquals("New task", label.getText());
    
    List<Widget> controlList = SuiUtil.sortControlsByYCoord(
    	SuiUtil.filterControlsByType(p.getControls(), Label.class));
    assertEquals(3, controlList.size());
    
    assertEquals("Description:", ((Label) controlList.get(0)).getText());
    assertEquals("Priority:", ((Label) controlList.get(1)).getText());
    assertEquals("Category:", ((Label) controlList.get(2)).getText());    
    
  }

}
