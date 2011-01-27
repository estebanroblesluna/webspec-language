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
import org.webspeclanguage.metamock.model.MetaMockElement;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.MetaMockTestCase;
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
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockVisitorTestCase extends MetaMockTestCase {
  
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
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Button b = this.getFactory().createButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitButton(b)).andReturn(null);
    verifyVisit(v, b);
  }

  @SuppressWarnings("unchecked")
  public void testVisitComboBox() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    ComboBox c = this.getFactory().createComboBox(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitComboBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitDatePicker() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    DatePicker c = this.getFactory().createDatePicker(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitDatePicker(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitImage() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Image c = this.getFactory().createImage(this.controlId, this.x, this.y, this.width, this.height, this.url);
    expect(v.visitImage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLabel() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Label c = this.getFactory().createLabel(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitLabel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitList() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    List c = this.getFactory().createList(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPage() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Page c = this.getFactory().createPage(this.controlId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    expect(v.visitPage(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitPanel() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Panel c = this.getFactory().createPanel(this.controlId, this.x, this.y, this.width, this.height, this.containerId);
    expect(v.visitPanel(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextBox() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    TextBox c = this.getFactory().createTextBox(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTextBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTable() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Table c = this.getFactory().createTable(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTable(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitCheckBox() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    CheckBox c = this.getFactory().createCheckBox(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitCheckBox(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitRadioButton() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    RadioButton c = this.getFactory().createRadioButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitRadioButton(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitLink() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Link c = this.getFactory().createLink(this.controlId, this.x, this.y, this.width, this.height, this.text);
    expect(v.visitLink(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitGridBoxLayout() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    GridBagLayout c = this.getFactory().createGridBagLayout();
    expect(v.visitGridBagLayout(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitVerticalBoxLayout() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    VerticalBoxLayout c = this.getFactory().createVerticalBoxLayout(new ArrayList<UIControl>());
    expect(v.visitVerticalBoxLayout(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitAnnotation() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Annotation c = this.getFactory().createAnnotation(this.controlId, this.x, this.y, this.width, this.height, null, this.text);
    expect(v.visitAnnotation(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitModel() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    MetaMockModel c = this.getFactory().createMetaMockModel();
    expect(v.visitModel(c)).andReturn(null);
    replay(v);
    c.visit(v);
    verify(v);
  }

  @SuppressWarnings("unchecked")
  public void testVisitSelectableList() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    SelectableList c = this.getFactory().createSelectableList(this.controlId, this.x, this.y, this.width, this.height, true);
    expect(v.visitSelectableList(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTextArea() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    TextArea c = this.getFactory().createTextArea(this.controlId, this.x, this.y, this.width, this.height);
    expect(v.visitTextArea(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitNumericSpinner() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    NumericSpinner c = this.getFactory().createNumericSpinner(this.controlId, this.x, this.y, this.width, this.height, 0, 10);
    expect(v.visitNumericSpinner(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitTableColumn() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    TableColumn c = this.getFactory().createTableColumn(this.text);
    expect(v.visitTableColumn(c)).andReturn(null);
    verifyVisit(v, c);
  }

  @SuppressWarnings("unchecked")
  public void testVisitRepetition() {
    MetaMockVisitor v = createMock(MetaMockVisitor.class);
    Repetition c = this.getFactory().createRepetition(this.controlId, this.x, this.y, this.width, this.height, new ArrayList<UIControl>(), 2, 2, this.controlId);
    expect(v.visitRepetition(c)).andReturn(null);
    verifyVisit(v, c);
  }
  
  @SuppressWarnings("unchecked")
  private void verifyVisit(MetaMockVisitor v, MetaMockElement b) {
    replay(v);
    b.visit(v);
    verify(v);
  }

}
