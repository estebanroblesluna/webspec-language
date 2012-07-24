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
package org.webspeclanguage.mockupdd.io.parser.xml;

import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;

/**
 * @author Jose Matias Rivero
 */
public class SuiParserTestCase extends TestCase {

  private Page page;
  
  public void testXmlSuiModelParsing() throws Exception {
    this.setUp();
    
    assertEquals(page.getTitle(), "Login screen");
    assertEquals(page.getWidgetId(), "loginScreen");
    assertEquals(page.getWidgets().size(), 7);
    
    this.assertAppliedTags(page.getAppliedTags());    
    
    Label l = (Label) page.getWidgetById("usernameLabel");
    assertWidgetFeatures(l, "usernameLabel", 10, 10, 100, 25);
    assertEquals("Username: ", l.getText());
    
    TextBox tb = (TextBox) page.getWidgetById("usernameTextbox");
    assertWidgetFeatures(tb, "usernameTextbox", 125, 10, 100, 25);
    assertEquals("username1", tb.getSampleData());
    
    Panel panel = (Panel) page.getWidgetById("loginPanel");
    assertWidgetFeatures(panel, "loginPanel", 10, 65, 200, 50);
    assertEquals(2, panel.getWidgets().size());
    
    CheckBox cb = (CheckBox) panel.getWidgetById("rememberMeCheckbox");
    assertWidgetFeatures(cb, "rememberMeCheckbox", 20, 95, 100, 25);
    
    Repetition repetition = (Repetition) page.getWidgetById("newsIndex");
    assertWidgetFeatures(repetition, "newsIndex", 10, 200, 100, 200);
    assertEquals(1, repetition.getWidgets().size());
    
    Link link = (Link) repetition.getWidgetById("newsLink");
    assertWidgetFeatures(link, "newsLink", 20, 210, 80, 30);
    
    panel = (Panel) page.getWidgetById("loginType");
    assertWidgetFeatures(panel, "loginType", 10, 110, 100, 100);
    assertEquals(4, panel.getWidgets().size());
    
    RadioButton radioButton = (RadioButton) panel.getWidgetById("normalLogin");
    assertEquals("Normal login", radioButton.getText());
    
    ComboBox comboBox = (ComboBox) panel.getWidgetById("loginAs");
    assertWidgetFeatures(comboBox, "loginAs", 30, 240, 200, 200);
    assertEquals("administrator", comboBox.getSampleData());
    
    Image image = (Image) panel.getWidgetById("loginBanner");
    assertWidgetFeatures(image, "loginBanner", 20, 160, 80, 30);
  }

  protected void setUp() throws Exception {
    SAXBuilder builder = new SAXBuilder();
    Document d = builder.build(new FileReader("src/test/resources/org/webspeclanguage/metamock/io/suiExample.xml"));
    SuiModel model = (SuiModel) new XmlSuiParser().parse(d.getRootElement(), new SuiParserContext(), null);
    assertEquals(model.getPages().size(), 1);
    this.page = model.getPages().iterator().next();
  }

  private void assertAppliedTags(Collection<TagApplication> appliedTags) {
    assertEquals(2, appliedTags.size());
    Iterator<TagApplication> iterator = appliedTags.iterator();
    while (iterator.hasNext()) {
      TagApplication tag = iterator.next();
      assertEquals(1, tag.getParameterValues().size());
      if (tag.getTag().getName().equals("Data")) {
        assertEquals("User", tag.getParameterValues().get(0).getValue().getTextualRepresentation());
      } else if (tag.getTag().getName().equals("Node")) {
        assertEquals("Login", tag.getParameterValues().get(0).getValue().getTextualRepresentation());
      }
    }
    
  }

  private void assertWidgetFeatures(Widget w, String id, int x, int y, int width, int height) {
    assertEquals(id, w.getWidgetId());
    assertEquals(x, (int) w.getX());
    assertEquals(y, (int) w.getY());
    assertEquals(width, (int) w.getWidth());
    assertEquals(height, (int) w.getHeight());
  }

}
