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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.DatePicker;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.List;
import org.webspeclanguage.mockupdd.sui.model.NumericSpinner;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public class SuiVisitorTestCase extends SuiTestCase {
  
  private String widgetId;
  private Integer x;
  private Integer y;
  private Integer width;
  private Integer height;
  private String text;
  private String url;
  private String containerId;

  protected void setUp() throws Exception {
    super.setUp();
    this.widgetId = "widgetId";
    this.x = 10;
    this.y = 10;
    this.width = 50;
    this.height = 50;
    this.text = "widgetText";
    this.url = "http://site.com/image1.png";
    this.containerId = "containerId";
  }

  @SuppressWarnings("unchecked")
  public void testVisitButton() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Button b = this.getFactory().createButton(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitButton(b)).andReturn(null);
    verifyVisit(v, b);
  }

  @SuppressWarnings("unchecked")
  public void testVisitComboBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    ComboBox c = this.getFactory().createComboBox(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitComboBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitDatePicker() {
    SuiVisitor v = createMock(SuiVisitor.class);
    DatePicker c = this.getFactory().createDatePicker(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitDatePicker(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitImage() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Image c = this.getFactory().createImage(this.widgetId, this.x, this.y, this.width, this.height, this.url);
    expect(v.visitImage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLabel() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Label c = this.getFactory().createLabel(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitLabel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitList() {
    SuiVisitor v = createMock(SuiVisitor.class);
    List c = this.getFactory().createList(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPage() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Page c = this.getFactory().createPage(this.widgetId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    expect(v.visitPage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPanel() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Panel c = this.getFactory().createPanel(this.widgetId, this.x, this.y, this.width, this.height, this.containerId);
    expect(v.visitPanel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    TextBox c = this.getFactory().createTextBox(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitTextBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTable() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Table c = this.getFactory().createTable(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitTable(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitCheckBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    CheckBox c = this.getFactory().createCheckBox(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitCheckBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitRadioButton() {
    SuiVisitor v = createMock(SuiVisitor.class);
    RadioButton c = this.getFactory().createRadioButton(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitRadioButton(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLink() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Link c = this.getFactory().createLink(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitLink(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitGridBoxLayout() {
    SuiVisitor v = createMock(SuiVisitor.class);
    GridBagLayout c = this.getFactory().createGridBagLayout();
    expect(v.visitGridBagLayout(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitVerticalBoxLayout() {
    SuiVisitor v = createMock(SuiVisitor.class);
    VerticalBoxLayout c = this.getFactory().createVerticalBoxLayout(new ArrayList<Widget>());
    expect(v.visitVerticalBoxLayout(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitAnnotation() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Annotation c = this.getFactory().createAnnotation(this.widgetId, this.x, this.y, this.width, this.height, null, this.text);
    expect(v.visitAnnotation(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitModel() {
    SuiVisitor v = createMock(SuiVisitor.class);
    SuiModel c = this.getFactory().createSuiModel();
    expect(v.visitModel(c)).andReturn(null);
    replay(v);
    c.visit(v);
    verify(v);
  }

  @SuppressWarnings("unchecked")
  public void testVisitSelectableList() {
    SuiVisitor v = createMock(SuiVisitor.class);
    SelectableList c = this.getFactory().createSelectableList(this.widgetId, this.x, this.y, this.width, this.height, true);
    expect(v.visitSelectableList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextArea() {
    SuiVisitor v = createMock(SuiVisitor.class);
    TextArea c = this.getFactory().createTextArea(this.widgetId, this.x, this.y, this.width, this.height);
    expect(v.visitTextArea(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitNumericSpinner() {
    SuiVisitor v = createMock(SuiVisitor.class);
    NumericSpinner c = this.getFactory().createNumericSpinner(this.widgetId, this.x, this.y, this.width, this.height, 0, 10);
    expect(v.visitNumericSpinner(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTableColumn() {
    SuiVisitor v = createMock(SuiVisitor.class);
    TableColumn c = this.getFactory().createTableColumn(this.text);
    expect(v.visitTableColumn(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitRepetition() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Repetition c = this.getFactory().createRepetition(this.widgetId, this.x, this.y, this.width, this.height, new ArrayList<Widget>(), 2, 2, this.widgetId);
    expect(v.visitRepetition(c)).andReturn(null);
    verifyVisit(v, c);
  }
  
  @SuppressWarnings("unchecked")
  private void verifyVisit(SuiVisitor v, SuiModelElement b) {
    replay(v);
    b.accept(v);
    verify(v);
  }

}
