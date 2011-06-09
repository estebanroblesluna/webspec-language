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

package org.webspeclanguage.metamock.utils;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.List;
import org.webspeclanguage.metamock.model.SuiModelElement;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.SuiTestCase;
import org.webspeclanguage.metamock.model.NumericSpinner;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.RadioButton;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.SelectableList;
import org.webspeclanguage.metamock.model.Table;
import org.webspeclanguage.metamock.model.TableColumn;
import org.webspeclanguage.metamock.model.TextArea;
import org.webspeclanguage.metamock.model.TextBox;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;

/**
 * @author Jose Matias Rivero
 */
public class SuiVisitorTestCase extends SuiTestCase {
  
  private String controlId;
  private Integer x;
  private Integer y;
  private Integer width;
  private Integer height;
  private String text;
  private String url;
  private String containerId;

  protected void setUp() throws Exception {
    super.setUp();
    this.controlId = "controlId";
    this.x = 10;
    this.y = 10;
    this.width = 50;
    this.height = 50;
    this.text = "controlText";
    this.url = "http://site.com/image1.png";
    this.containerId = "containerId";
  }

  @SuppressWarnings("unchecked")
  public void testVisitButton() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Button b = this.getFactory().createButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitButton(b)).andReturn(null);
    verifyVisit(v, b);
  }

  @SuppressWarnings("unchecked")
  public void testVisitComboBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    ComboBox c = this.getFactory().createComboBox(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitComboBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitDatePicker() {
    SuiVisitor v = createMock(SuiVisitor.class);
    DatePicker c = this.getFactory().createDatePicker(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitDatePicker(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitImage() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Image c = this.getFactory().createImage(this.controlId, this.x, this.y, this.width, this.height, this.url);
    expect(v.visitImage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLabel() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Label c = this.getFactory().createLabel(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitLabel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitList() {
    SuiVisitor v = createMock(SuiVisitor.class);
    List c = this.getFactory().createList(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPage() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Page c = this.getFactory().createPage(this.controlId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    expect(v.visitPage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPanel() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Panel c = this.getFactory().createPanel(this.controlId, this.x, this.y, this.width, this.height, this.containerId);
    expect(v.visitPanel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    TextBox c = this.getFactory().createTextBox(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTextBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTable() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Table c = this.getFactory().createTable(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTable(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitCheckBox() {
    SuiVisitor v = createMock(SuiVisitor.class);
    CheckBox c = this.getFactory().createCheckBox(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitCheckBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitRadioButton() {
    SuiVisitor v = createMock(SuiVisitor.class);
    RadioButton c = this.getFactory().createRadioButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitRadioButton(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLink() {
    SuiVisitor v = createMock(SuiVisitor.class);
    Link c = this.getFactory().createLink(this.controlId, this.x, this.y, this.width, this.height, this.text);
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
    Annotation c = this.getFactory().createAnnotation(this.controlId, this.x, this.y, this.width, this.height, null, this.text);
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
    SelectableList c = this.getFactory().createSelectableList(this.controlId, this.x, this.y, this.width, this.height, true);
    expect(v.visitSelectableList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextArea() {
    SuiVisitor v = createMock(SuiVisitor.class);
    TextArea c = this.getFactory().createTextArea(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTextArea(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitNumericSpinner() {
    SuiVisitor v = createMock(SuiVisitor.class);
    NumericSpinner c = this.getFactory().createNumericSpinner(this.controlId, this.x, this.y, this.width, this.height, 0, 10);
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
    Repetition c = this.getFactory().createRepetition(this.controlId, this.x, this.y, this.width, this.height, new ArrayList<Widget>(), 2, 2, this.controlId);
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
