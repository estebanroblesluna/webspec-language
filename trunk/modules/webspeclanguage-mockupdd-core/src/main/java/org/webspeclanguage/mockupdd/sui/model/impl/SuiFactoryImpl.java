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
package org.webspeclanguage.mockupdd.sui.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.LayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.CompositeWidgetAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.GridBagLayoutAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.RepetitionAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.TemplateAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.TemplateInstantiationAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.VerticalBoxLayoutAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.annotation.impl.WidgetAnnotationImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagApplicationImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagParameterImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagParameterValueImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagSetImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.content.DataPathNodeImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.content.DataPathTagParameterValueContentImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.content.SimpleTagParameterValueContentImpl;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.GridBagLayoutImpl;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.VerticalBoxLayoutImpl;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.SimpleTagParameterValueContent;
import org.webspeclanguage.mockupdd.translator.DefaultWidgetGroupImpl;
import org.webspeclanguage.mockupdd.translator.WidgetGroup;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * Default implementation of {@link SuiFactory}
 * 
 * @author Jose Matias Rivero
 */
public class SuiFactoryImpl implements SuiFactory {

  public Button createButton(String widgetId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new ButtonImpl(widgetId, x, y, width, height, text);
  }

  public ComboBox createComboBox(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new ComboBoxImpl(widgetId, x, y, width, height);
  }

  public DatePicker createDatePicker(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new DatePickerImpl(widgetId, x, y, width, height);
  }

  public Label createLabel(String widgetId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new LabelImpl(widgetId, x, y, width, height, text);
  }

  public List createList(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new ListImpl(widgetId, x, y, width, height);
  }

  public Page createPage(String widgetId, Integer x, Integer y, Integer width, Integer height, String title, String containerId) {
    return new PageImpl(widgetId, x, y, width, height, title, containerId);
  }

  public Panel createPanel(String widgetId, Integer x, Integer y, Integer width, Integer height, String containerId) {
    return new PanelImpl(widgetId, x, y, width, height, containerId);
  }

  public TextBox createTextBox(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new TextBoxImpl(widgetId, x, y, width, height);
  }

  public Image createImage(String widgetId, Integer x, Integer y, Integer width, Integer height, String imageUrl) {
    return new ImageImpl(widgetId, x, y, width, height, imageUrl);
  }

  public SuiModel createSuiModel() {
    return new SuiModelImpl(new ArrayList<Page>());
  }

  public GridBagLayout createGridBagLayout(Collection<Widget> widgets) {
    return new ScanBasedGridBagLayoutFactory().createLayout(widgets);
  }

  public GridBagLayout createGridBagLayout() {
    return new GridBagLayoutImpl();
  }

  public Table createTable(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new TableImpl(widgetId, x, y, width, height);
  }

  public CheckBox createCheckBox(String widgetId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new CheckBoxImpl(widgetId, x, y, width, height, text);
  }

  public RadioButton createRadioButton(String widgetId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new RadioButtonImpl(widgetId, x, y, width, height, text);
  }

  public Link createLink(String widgetId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new LinkImpl(widgetId, x, y, width, height, text);
  }

  public SelectableList createSelectableList(String widgetId, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection) {
    return new SelectableListImpl(widgetId, x, y, width, height, multipleSelection);
  }

  public WidgetGroup createWidgetGroup(java.util.List<Widget> widgets) {
    return new DefaultWidgetGroupImpl(widgets);
  }

  public Annotation createAnnotation(String widgetId, Integer x, Integer y, Integer width, Integer height, Widget c, String content) {
    return new AnnotationImpl(widgetId, x, y, width, height, c, content);
  }

  public WidgetAnnotation createWidgetAnnotation(Widget widget, String id) {
    return new WidgetAnnotationImpl(widget, id);
  }

  public CompositeWidgetAnnotation createCompositeWidgetAnnotation(Widget widget, String id,
          TemplateInstantiationAnnotation templateInstantiationAnnotation, RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation) {
    return new CompositeWidgetAnnotationImpl(widget, id, templateInstantiationAnnotation, repetitionAnnotation, layoutAnnotation);
  }

  public TemplateInstantiationAnnotation createTemplateInstantiationAnnotation(Widget widget, String templateId, String pageId, String placeholderId) {
    return new TemplateInstantiationAnnotationImpl(widget, templateId, pageId, placeholderId);
  }

  public GridBagLayoutAnnotation createGridBagLayoutAnnotation(Widget widget) {
    return new GridBagLayoutAnnotationImpl(widget);
  }

  public VerticalBoxLayoutAnnotation createVerticalBoxLayoutAnnotation(Widget widget) {
    return new VerticalBoxLayoutAnnotationImpl(widget);
  }

  public VerticalBoxLayout createVerticalBoxLayout(Collection<Widget> widgets) {
    return new VerticalBoxLayoutImpl(SuiUtil.getWidgetsGroupedInColumns(widgets));
  }

  public TextArea createTextArea(String widgetId, Integer x, Integer y, Integer width, Integer height) {
    return new TextAreaImpl(widgetId, x, y, width, height);
  }

  public NumericSpinner createNumericSpinner(String widgetId, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue) {
    return new NumericSpinnerImpl(widgetId, x, y, width, height, minValue, maxValue);
  }

  public TableColumn createTableColumn(String label) {
    return new TableColumnImpl(label);
  }

  public TemplateAnnotation createTemplateAnnotation(Widget widget, String templateId) {
    return new TemplateAnnotationImpl(widget, templateId);
  }

  public Repetition createRepetition(String widgetId, Integer x, Integer y, Integer width, Integer height, Collection<Widget> widgets, Integer rows,
          Integer columns, String containerId) {
    return new RepetitionImpl(widgetId, x, y, width, height, widgets, rows, columns, containerId);
  }

  public RepetitionAnnotation createRepetitionAnnotation(Widget c) {
    return new RepetitionAnnotationImpl(c);
  }

  public TagSet createTagSet(String tagSetName, Tag... tags) {
    return new TagSetImpl(tagSetName, Arrays.asList(tags));
  }

  public Tag createTag(String tagName, java.util.List<TagParameter> tagParameters, Class<? extends Widget>... applicableOver) {
    return new TagImpl(tagName, tagParameters, Arrays.asList(applicableOver));
  }

  public TagParameter createTagParameter(String parameterName) {
    return new TagParameterImpl(parameterName);
  }

  public TagParameterValue createTagParameterValue(TagParameter tagParameter, String value) {
    return new TagParameterValueImpl(tagParameter, new SimpleTagParameterValueContentImpl(value));
  }

  public TagApplication createTagApplication(Widget widget, Tag tag, java.util.List<TagParameterValue> parameterValues) throws TagApplicationException {
    return new TagApplicationImpl(widget, tag, parameterValues);
  }

  @Override
  public SimpleTagParameterValueContent createSimpleTagParameterValueContent(String valueContent) {
    return new SimpleTagParameterValueContentImpl(valueContent);
  }

  @Override
  public DataPathTagParameterValueContent createDataPathTagParameterValueContent(String widgetId, DataPathNode rootNode) {
    return new DataPathTagParameterValueContentImpl(widgetId, rootNode);
  }

  @Override
  public DataPathNode createDataPathNode(String className, String accessorName) {
    return new DataPathNodeImpl(accessorName, className);
  }

  @Override
  public DataPathNode createDataPathNode(String className) {
    return new DataPathNodeImpl(className);
  }

}
