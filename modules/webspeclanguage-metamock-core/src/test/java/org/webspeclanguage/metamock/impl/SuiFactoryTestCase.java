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
package org.webspeclanguage.metamock.impl;

import java.util.Arrays;

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.List;
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
import org.webspeclanguage.metamock.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.WidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.LayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.SuiAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.translator.Template;

/**
 * @author Jose Matias Rivero
 */
public class SuiFactoryTestCase extends SuiTestCase {

  private String controlId;
  private int x;
  private int y;
  private int width;
  private int height;
  private String text;
  private String containerId;
  private String url;
  private java.util.List<Widget> controls;
  private int controlOffsetX;
  private int controlOffsetY;

  public void setUp() throws Exception {
    super.setUp();
    this.controlId = "controlId";
    this.x = 10;
    this.y = 20;
    this.width = 30;
    this.height = 40;
    this.controlOffsetX = 50;
    this.controlOffsetY = 50;
    this.text = "text";
    this.containerId = "containerId";
    this.url = "http://someurl";
    this.controls = Arrays.asList(
      (Widget)
      this.getFactory().createButton("b1", this.x, this.y, this.width, this.height, this.text),
      this.getFactory().createButton("b2", this.x + this.controlOffsetX, this.y + this.controlOffsetY, this.width, this.height, this.text), 
      this.getFactory().createButton("b3", this.x + this.controlOffsetX * 2, this.y + this.controlOffsetY * 2, this.width, this.height, this.text)   
    );
  }

  public void testCreateButton() {
    Button c = this.getFactory().createButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    Button c2 = (Button) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateComboBox() {
    ComboBox c = this.getFactory().createComboBox(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    
    ComboBox c2 = (ComboBox) c.copy();
    this.assertUIControlFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateDatePicker() {
    DatePicker c = this.getFactory().createDatePicker(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    this.assertUIControlFeatures((Widget) c.copy());
    
    DatePicker c2 = (DatePicker) c.copy();
    this.assertUIControlFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateLabel() {
    Label c = this.getFactory().createLabel(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    Label c2 = (Label) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateList() {
    List c = this.getFactory().createList(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    this.assertUIControlFeatures((Widget) c.copy());
    
    List c2 = (List) c.copy();
    this.assertUIControlFeatures(c2);
    this.assertUIControlFeatures((Widget) c2.copy());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreatePage() {
    Page c = this.getFactory().createPage(this.controlId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    this.assertUIControlFeatures(c);
    this.assertCompositeControlFeatures(c);
    assertEquals(this.text, c.getTitle());
    
    Page c2 = (Page) c.copy();
    this.assertUIControlFeatures(c2);
    this.assertCompositeControlFeatures(c2);
    assertEquals(this.text, c2.getTitle());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreatePanel() {
    Panel c = this.getFactory().createPanel(this.controlId, this.x, this.y, this.width, this.height, this.containerId);
    this.assertUIControlFeatures(c);
    this.assertCompositeControlFeatures(c);
    
    CompositeWidget c2 = (Panel) c.copy();
    this.assertUIControlFeatures(c2);
    this.assertCompositeControlFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateTextBox() {
    TextBox c = this.getFactory().createTextBox(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    TextBox c2 = (TextBox) c.copy();
    this.assertUIControlFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateImage() {
    Image c = this.getFactory().createImage(this.controlId, this.x, this.y, this.width, this.height, this.url);
    this.assertUIControlFeatures(c);
    assertEquals(this.url, c.getImageUrl());
    
    Image c2 = (Image) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.url, c2.getImageUrl());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateModel() {
    SuiModel c = this.getFactory().createSuiModel();
    for (Widget ctrl : this.controls)
      c.addControlOutsidePages(ctrl);
    assertEquals(this.controls.size(), c.getControlsOutsidePages().size());
    for (Widget ctrl : this.controls)
      assertTrue(c.getControlsOutsidePages().contains(ctrl));
    
    Page p = this.getFactory().createPage(this.controlId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    c.addPage(p);
    assertEquals(1, c.getPages().size());
    assertTrue(c.getPages().contains(p));
    assertSame(p, c.getPageById(this.controlId));
    
    for (Widget ctrl : this.controls)
      c.registerControl(ctrl);
    for (Widget ctrl : this.controls)
      assertSame(c.getControlById(ctrl.getControlId()), ctrl);
    assertNull(c.getControlById("fakeId"));
    
    Template template = new Template(
      this.getFactory().createPanel(this.controlId, this.x, this.y, this.width, this.height, this.containerId),
      "templateId",
      this.getFactory());
    c.addTemplate(template);
    assertSame(template, c.getTemplate("templateId", this.containerId));
    assertNull(c.getTemplate("fakeId", this.containerId));
  }

  public void testCreateGridBagLayoutCollectionOfUIControl() {
    GridBagLayout l = this.getFactory().createGridBagLayout(this.controls);
    assertEquals(this.controls.size(), l.getControls().size());
    for (Widget ctrl : this.controls)
      assertTrue(l.getControls().contains(ctrl));  
    
    GridBagLayout l2 = (GridBagLayout) l.copy();
    
    assertEquals(l.getColumnCount(), l2.getColumnCount());
    assertEquals(l.getRowCount(), l2.getRowCount());
    
    for (int col = 0; col < l.getColumnCount(); col++)
      for (int row = 0; row < l.getRowCount(); row++)
        if (l.getCell(row, col).getControl() == null) {
          assertNull(l2.getCell(row, col).getControl());
        } else {
          assertTrue(l.getCell(row, col).getControl().equalInContent(l2.getCell(row, col).getControl()));
        }
  }

  public void testCreateTable() {
    Table c = this.getFactory().createTable(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    TableColumn col1 = this.getFactory().createTableColumn("col1");
    TableColumn col2 = this.getFactory().createTableColumn("col2");
    c.addColumn(col1);
    c.addColumn(col2);
    assertTrue(c.getColumns().contains(col1));
    assertTrue(c.getColumns().contains(col2));
    
    Table c2 = (Table) c.copy();
    assertTrue(c.getColumns().contains(col1));
    assertTrue(c.getColumns().contains(col2));
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateCheckBox() {
    CheckBox c = this.getFactory().createCheckBox(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    CheckBox c2 = (CheckBox) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateRadioButton() {
    RadioButton c = this.getFactory().createRadioButton(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    RadioButton c2 = (RadioButton) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateLink() {
    Link c = this.getFactory().createLink(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    Link c2 = (Link) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateSelectableList() {
    SelectableList c = this.getFactory().createSelectableList(this.controlId, this.x, this.y, this.width, this.height, true);
    this.assertUIControlFeatures(c);
    assertEquals(true, (boolean)c.getMultipleSelection());
    
    SelectableList c2 = (SelectableList) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(true, (boolean)c2.getMultipleSelection());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateControlGroup() {
    Label c = this.getFactory().createLabel(this.controlId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIControlFeatures(c);
    assertEquals(this.text, c.getText());
    
    Label c2 = (Label) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateAnnotation() {
    Annotation c = this.getFactory().createAnnotation(this.controlId, this.x, this.y, this.width, this.height, this.controls.get(0), this.text);
    this.assertUIControlFeatures(c);
    assertSame(this.controls.get(0), c.getTargetElement());
    c.setTargetElement(this.controls.get(1));
    assertSame(this.controls.get(1), c.getTargetElement());
    assertEquals(this.text, c.getContent());
  }

  public void testCreateControlAnnotation() {
    WidgetAnnotation ca = this.getFactory().createControlAnnotation(this.controls.get(0), "customId");
    assertSame(this.controls.get(0), ca.getControl());
    assertEquals("customId", ca.getId());
    ca.setId("customId2");
    assertEquals("customId2", ca.getId());
  }

  public void testCreateCompositeControlAnnotation() {
    TemplateInstantiationAnnotation tia = this.getFactory().createTemplateInstantiationAnnotation(this.controls.get(0), "templateId", "pageId", "placeholderId");
    RepetitionAnnotation ra = this.getFactory().createRepetitionAnnotation(this.controls.get(0));
    LayoutAnnotation gbla = this.getFactory().createGridBagLayoutAnnotation(this.controls.get(0));
    CompositeWidgetAnnotation cca = this.getFactory().createCompositeControlAnnotation(this.controls.get(0), "customId", tia, ra, gbla);
    assertSame(tia, cca.getTemplateInstantiationAnnotation());
    assertSame(ra, cca.getRepetitionAnnotation());
    assertSame(gbla, cca.getLayoutAnnotation());
  }

  public void testCreateTemplateInstantiationAnnotation() {
    TemplateAnnotation tia = this.getFactory().createTemplateAnnotation(this.controls.get(0), "templateId");
    assertEquals("templateId", tia.getTemplateId());
    this.assertControlAnnotationFeatures(tia);
  }

  public void testCreateGridBagLayoutAnnotation() {
    GridBagLayoutAnnotation gbl = this.getFactory().createGridBagLayoutAnnotation(this.controls.get(0));
    this.assertControlAnnotationFeatures(gbl);
  }

  public void testCreateVerticalBoxLayoutAnnotation() {
    VerticalBoxLayoutAnnotation vbl = this.getFactory().createVerticalBoxLayoutAnnotation(this.controls.get(0));
    this.assertControlAnnotationFeatures(vbl);
  }

  public void testCreateVerticalBoxLayout() {
    VerticalBoxLayout vbl = this.getFactory().createVerticalBoxLayout(this.controls);
    for (Widget c : this.controls)
      assertTrue(vbl.getControls().contains(c));
  }

  public void testCreateTextArea() {
    TextArea c = this.getFactory().createTextArea(this.controlId, this.x, this.y, this.width, this.height);
    this.assertUIControlFeatures(c);
    
    TextArea c2 = (TextArea) c.copy();
    this.assertUIControlFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateNumericSpinner() {
    NumericSpinner c = this.getFactory().createNumericSpinner(this.controlId, this.x, this.y, this.width, this.height, 100, 200);
    this.assertUIControlFeatures(c);
    assertEquals(100, (int)c.getMinValue());
    assertEquals(200, (int)c.getMaxValue());
    
    NumericSpinner c2 = (NumericSpinner) c.copy();
    this.assertUIControlFeatures(c2);
    assertEquals(100, (int)c2.getMinValue());
    assertEquals(200, (int)c2.getMaxValue());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateTableColumn() {
    TableColumn c = this.getFactory().createTableColumn("colName");
    assertEquals("colName", c.getLabel());
    
    TableColumn c2 = (TableColumn) c.copy();
    assertEquals("colName", c2.getLabel());
  }

  public void testCreateTemplateAnnotation() {
    TemplateAnnotation ta = this.getFactory().createTemplateAnnotation(this.controls.get(0), "templateId");
    assertControlAnnotationFeatures(ta);
    assertEquals("templateId", ta.getTemplateId());
  }

  public void testCreateRepetition() {
    Repetition c = this.getFactory().createRepetition(this.controlId, this.x, this.y, this.width, this.height, this.controls, 3, 3, this.containerId);
    for (Widget ctrl : this.controls)
      assertTrue(c.getControls().contains(ctrl));
    assertEquals(3, (int)c.getColumns());
    assertEquals(3, (int)c.getRows());
    this.assertUIControlFeatures(c);
    this.assertCompositeControlFeatures(c);  
  }

  public void testCreateRepetitionAnnotation() {
    RepetitionAnnotation ra = this.getFactory().createRepetitionAnnotation(this.controls.get(0));
    assertSame(ra.getControl(), this.controls.get(0));
  }

  private void assertControlAnnotationFeatures(SuiAnnotation a) {
    assertSame(this.controls.get(0), a.getControl());
  }
  
  private void assertUIControlFeatures(Widget c) {
    assertEquals(this.controlId, c.getControlId());
    assertEquals(this.x, (int) c.getX());
    assertEquals(this.y, (int) c.getY());
    assertEquals(this.width, (int) c.getWidth());
    assertEquals(this.height, (int) c.getHeight());
  }

  private void assertCompositeControlFeatures(CompositeWidget c) {
    assertEquals(this.containerId, c.getContainerId());
    for (Widget cc : this.controls)
      c.addChild(cc);
    assertEquals(this.controls.size(), c.getControls().size());
    assertTrue(c.getControls().contains(this.controls.get(0)));
    assertTrue(c.getControls().contains(this.controls.get(1)));
    assertTrue(c.getControls().contains(this.controls.get(2)));
    c.removeChild(this.controls.get(1));
    assertEquals(2, c.getControls().size());
    assertTrue(c.getControls().contains(this.controls.get(0)));
    assertTrue(c.getControls().contains(this.controls.get(2)));
    c.removeAllChildren();
    assertEquals(0, c.getControls().size());
  }

}
