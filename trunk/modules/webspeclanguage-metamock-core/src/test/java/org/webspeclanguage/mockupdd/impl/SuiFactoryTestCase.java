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
package org.webspeclanguage.mockupdd.impl;

import java.util.Arrays;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
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
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.LayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.translator.Template;

/**
 * @author Jose Matias Rivero
 */
public class SuiFactoryTestCase extends SuiTestCase {

  private String widgetId;
  private int x;
  private int y;
  private int width;
  private int height;
  private String text;
  private String containerId;
  private String url;
  private java.util.List<Widget> widgets;
  private int widgetOffsetX;
  private int widgetOffsetY;

  public void setUp() throws Exception {
    super.setUp();
    this.widgetId = "widgetId";
    this.x = 10;
    this.y = 20;
    this.width = 30;
    this.height = 40;
    this.widgetOffsetX = 50;
    this.widgetOffsetY = 50;
    this.text = "text";
    this.containerId = "containerId";
    this.url = "http://someurl";
    this.widgets = Arrays.asList(
      (Widget)
      this.getFactory().createButton("b1", this.x, this.y, this.width, this.height, this.text),
      this.getFactory().createButton("b2", this.x + this.widgetOffsetX, this.y + this.widgetOffsetY, this.width, this.height, this.text), 
      this.getFactory().createButton("b3", this.x + this.widgetOffsetX * 2, this.y + this.widgetOffsetY * 2, this.width, this.height, this.text)   
    );
  }

  public void testCreateButton() {
    Button c = this.getFactory().createButton(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    Button c2 = (Button) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateComboBox() {
    ComboBox c = this.getFactory().createComboBox(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
    
    ComboBox c2 = (ComboBox) c.copy();
    this.assertUIWidgetFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateDatePicker() {
    DatePicker c = this.getFactory().createDatePicker(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
    this.assertUIWidgetFeatures((Widget) c.copy());
    
    DatePicker c2 = (DatePicker) c.copy();
    this.assertUIWidgetFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateLabel() {
    Label c = this.getFactory().createLabel(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    Label c2 = (Label) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateList() {
    List c = this.getFactory().createList(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
    this.assertUIWidgetFeatures((Widget) c.copy());
    
    List c2 = (List) c.copy();
    this.assertUIWidgetFeatures(c2);
    this.assertUIWidgetFeatures((Widget) c2.copy());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreatePage() {
    Page c = this.getFactory().createPage(this.widgetId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    this.assertUIWidgetFeatures(c);
    this.assertCompositeWidgetFeatures(c);
    assertEquals(this.text, c.getTitle());
    
    Page c2 = (Page) c.copy();
    this.assertUIWidgetFeatures(c2);
    this.assertCompositeWidgetFeatures(c2);
    assertEquals(this.text, c2.getTitle());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreatePanel() {
    Panel c = this.getFactory().createPanel(this.widgetId, this.x, this.y, this.width, this.height, this.containerId);
    this.assertUIWidgetFeatures(c);
    this.assertCompositeWidgetFeatures(c);
    
    CompositeWidget c2 = (Panel) c.copy();
    this.assertUIWidgetFeatures(c2);
    this.assertCompositeWidgetFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateTextBox() {
    TextBox c = this.getFactory().createTextBox(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
    TextBox c2 = (TextBox) c.copy();
    this.assertUIWidgetFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateImage() {
    Image c = this.getFactory().createImage(this.widgetId, this.x, this.y, this.width, this.height, this.url);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.url, c.getImageUrl());
    
    Image c2 = (Image) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.url, c2.getImageUrl());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateModel() {
    SuiModel c = this.getFactory().createSuiModel();
    for (Widget ctrl : this.widgets)
      c.addWidgetOutsidePages(ctrl);
    assertEquals(this.widgets.size(), c.getWidgetsOutsidePages().size());
    for (Widget ctrl : this.widgets)
      assertTrue(c.getWidgetsOutsidePages().contains(ctrl));
    
    Page p = this.getFactory().createPage(this.widgetId, this.x, this.y, this.width, this.height, this.text, this.containerId);
    c.addPage(p);
    assertEquals(1, c.getPages().size());
    assertTrue(c.getPages().contains(p));
    assertSame(p, c.getPageById(this.widgetId));
    
    for (Widget ctrl : this.widgets)
      c.registerWidget(ctrl);
    for (Widget ctrl : this.widgets)
      assertSame(c.getWidgetById(ctrl.getWidgetId()), ctrl);
    assertNull(c.getWidgetById("fakeId"));
    
    Template template = new Template(
      this.getFactory().createPanel(this.widgetId, this.x, this.y, this.width, this.height, this.containerId),
      "templateId",
      this.getFactory());
    c.addTemplate(template);
    assertSame(template, c.getTemplate("templateId", this.containerId));
    assertNull(c.getTemplate("fakeId", this.containerId));
  }

  public void testCreateGridBagLayoutCollectionOfUIWidget() {
    GridBagLayout l = this.getFactory().createGridBagLayout(this.widgets);
    assertEquals(this.widgets.size(), l.getWidgets().size());
    for (Widget ctrl : this.widgets)
      assertTrue(l.getWidgets().contains(ctrl));  
    
    GridBagLayout l2 = (GridBagLayout) l.copy();
    
    assertEquals(l.getColumnCount(), l2.getColumnCount());
    assertEquals(l.getRowCount(), l2.getRowCount());
    
    for (int col = 0; col < l.getColumnCount(); col++)
      for (int row = 0; row < l.getRowCount(); row++)
        if (l.getCell(row, col).getWidget() == null) {
          assertNull(l2.getCell(row, col).getWidget());
        } else {
          assertTrue(l.getCell(row, col).getWidget().equalInContent(l2.getCell(row, col).getWidget()));
        }
  }

  public void testCreateTable() {
    Table c = this.getFactory().createTable(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
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
    CheckBox c = this.getFactory().createCheckBox(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    CheckBox c2 = (CheckBox) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateRadioButton() {
    RadioButton c = this.getFactory().createRadioButton(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    RadioButton c2 = (RadioButton) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateLink() {
    Link c = this.getFactory().createLink(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    Link c2 = (Link) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateSelectableList() {
    SelectableList c = this.getFactory().createSelectableList(this.widgetId, this.x, this.y, this.width, this.height, true);
    this.assertUIWidgetFeatures(c);
    assertEquals(true, (boolean)c.getMultipleSelection());
    
    SelectableList c2 = (SelectableList) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(true, (boolean)c2.getMultipleSelection());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateWidgetGroup() {
    Label c = this.getFactory().createLabel(this.widgetId, this.x, this.y, this.width, this.height, this.text);
    this.assertUIWidgetFeatures(c);
    assertEquals(this.text, c.getText());
    
    Label c2 = (Label) c.copy();
    this.assertUIWidgetFeatures(c2);
    assertEquals(this.text, c2.getText());
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateAnnotation() {
    Annotation c = this.getFactory().createAnnotation(this.widgetId, this.x, this.y, this.width, this.height, this.widgets.get(0), this.text);
    this.assertUIWidgetFeatures(c);
    assertSame(this.widgets.get(0), c.getTargetElement());
    c.setTargetElement(this.widgets.get(1));
    assertSame(this.widgets.get(1), c.getTargetElement());
    assertEquals(this.text, c.getContent());
  }

  public void testCreateWidgetAnnotation() {
    WidgetAnnotation ca = this.getFactory().createWidgetAnnotation(this.widgets.get(0), "customId");
    assertSame(this.widgets.get(0), ca.getWidget());
    assertEquals("customId", ca.getId());
    ca.setId("customId2");
    assertEquals("customId2", ca.getId());
  }

  public void testCreateCompositeWidgetAnnotation() {
    TemplateInstantiationAnnotation tia = this.getFactory().createTemplateInstantiationAnnotation(this.widgets.get(0), "templateId", "pageId", "placeholderId");
    RepetitionAnnotation ra = this.getFactory().createRepetitionAnnotation(this.widgets.get(0));
    LayoutAnnotation gbla = this.getFactory().createGridBagLayoutAnnotation(this.widgets.get(0));
    CompositeWidgetAnnotation cca = this.getFactory().createCompositeWidgetAnnotation(this.widgets.get(0), "customId", tia, ra, gbla);
    assertSame(tia, cca.getTemplateInstantiationAnnotation());
    assertSame(ra, cca.getRepetitionAnnotation());
    assertSame(gbla, cca.getLayoutAnnotation());
  }

  public void testCreateTemplateInstantiationAnnotation() {
    TemplateAnnotation tia = this.getFactory().createTemplateAnnotation(this.widgets.get(0), "templateId");
    assertEquals("templateId", tia.getTemplateId());
    this.assertWidgetAnnotationFeatures(tia);
  }

  public void testCreateGridBagLayoutAnnotation() {
    GridBagLayoutAnnotation gbl = this.getFactory().createGridBagLayoutAnnotation(this.widgets.get(0));
    this.assertWidgetAnnotationFeatures(gbl);
  }

  public void testCreateVerticalBoxLayoutAnnotation() {
    VerticalBoxLayoutAnnotation vbl = this.getFactory().createVerticalBoxLayoutAnnotation(this.widgets.get(0));
    this.assertWidgetAnnotationFeatures(vbl);
  }

  public void testCreateVerticalBoxLayout() {
    VerticalBoxLayout vbl = this.getFactory().createVerticalBoxLayout(this.widgets);
    for (Widget c : this.widgets)
      assertTrue(vbl.getWidgets().contains(c));
  }

  public void testCreateTextArea() {
    TextArea c = this.getFactory().createTextArea(this.widgetId, this.x, this.y, this.width, this.height);
    this.assertUIWidgetFeatures(c);
    
    TextArea c2 = (TextArea) c.copy();
    this.assertUIWidgetFeatures(c2);
    
    assertTrue(c.equalInContent(c2));
  }

  public void testCreateNumericSpinner() {
    NumericSpinner c = this.getFactory().createNumericSpinner(this.widgetId, this.x, this.y, this.width, this.height, 100, 200);
    this.assertUIWidgetFeatures(c);
    assertEquals(100, (int)c.getMinValue());
    assertEquals(200, (int)c.getMaxValue());
    
    NumericSpinner c2 = (NumericSpinner) c.copy();
    this.assertUIWidgetFeatures(c2);
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
    TemplateAnnotation ta = this.getFactory().createTemplateAnnotation(this.widgets.get(0), "templateId");
    assertWidgetAnnotationFeatures(ta);
    assertEquals("templateId", ta.getTemplateId());
  }

  public void testCreateRepetition() {
    Repetition c = this.getFactory().createRepetition(this.widgetId, this.x, this.y, this.width, this.height, this.widgets, 3, 3, this.containerId);
    for (Widget ctrl : this.widgets)
      assertTrue(c.getWidgets().contains(ctrl));
    assertEquals(3, (int)c.getColumns());
    assertEquals(3, (int)c.getRows());
    this.assertUIWidgetFeatures(c);
    this.assertCompositeWidgetFeatures(c);  
  }

  public void testCreateRepetitionAnnotation() {
    RepetitionAnnotation ra = this.getFactory().createRepetitionAnnotation(this.widgets.get(0));
    assertSame(ra.getWidget(), this.widgets.get(0));
  }

  private void assertWidgetAnnotationFeatures(SuiAnnotation a) {
    assertSame(this.widgets.get(0), a.getWidget());
  }
  
  private void assertUIWidgetFeatures(Widget c) {
    assertEquals(this.widgetId, c.getWidgetId());
    assertEquals(this.x, (int) c.getX());
    assertEquals(this.y, (int) c.getY());
    assertEquals(this.width, (int) c.getWidth());
    assertEquals(this.height, (int) c.getHeight());
  }

  private void assertCompositeWidgetFeatures(CompositeWidget c) {
    assertEquals(this.containerId, c.getContainerId());
    for (Widget cc : this.widgets)
      c.addChild(cc);
    assertEquals(this.widgets.size(), c.getWidgets().size());
    assertTrue(c.getWidgets().contains(this.widgets.get(0)));
    assertTrue(c.getWidgets().contains(this.widgets.get(1)));
    assertTrue(c.getWidgets().contains(this.widgets.get(2)));
    c.removeChild(this.widgets.get(1));
    assertEquals(2, c.getWidgets().size());
    assertTrue(c.getWidgets().contains(this.widgets.get(0)));
    assertTrue(c.getWidgets().contains(this.widgets.get(2)));
    c.removeAllChildren();
    assertEquals(0, c.getWidgets().size());
  }

}
