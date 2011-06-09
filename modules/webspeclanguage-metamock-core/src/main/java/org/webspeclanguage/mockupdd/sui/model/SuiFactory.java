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
package org.webspeclanguage.mockupdd.sui.model;

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.LayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.translator.WidgetGroup;

/**
 * Factory interface for constructing Sui elements instances
 * 
 * @author Jose Matias Rivero
 */
public interface SuiFactory {

  Button createButton(String widgetId, Integer x, Integer y, Integer width, Integer height, String text);

  Label createLabel(String widgetId, Integer x, Integer y, Integer width, Integer height, String text);

  List createList(String widgetId, Integer x, Integer y, Integer width, Integer height);

  ComboBox createComboBox(String widgetId, Integer x, Integer y, Integer width, Integer height);

  Page createPage(String widgetId, Integer x, Integer y, Integer width, Integer height, String title, String containerId);

  Panel createPanel(String widgetId, Integer x, Integer y, Integer width, Integer height, String containerId);

  TextBox createTextBox(String widgetId, Integer x, Integer y, Integer width, Integer height);

  DatePicker createDatePicker(String widgetId, Integer x, Integer y, Integer width, Integer height);

  Image createImage(String widgetId, Integer x, Integer y, Integer width, Integer height, String imageUrl);

  SuiModel createSuiModel();

  GridBagLayout createGridBagLayout(Collection<Widget> widgets);

  Table createTable(String widgetId, Integer x, Integer y, Integer width, Integer height);

  RadioButton createRadioButton(String widgetId, Integer x, Integer y, Integer width, Integer height, String text);

  CheckBox createCheckBox(String widgetId, Integer x, Integer y, Integer width, Integer height, String text);

  Link createLink(String widgetId, Integer x, Integer y, Integer width, Integer height, String text);

  Annotation createAnnotation(String widgetId, Integer x, Integer y, Integer width, Integer height, Widget c, String content);

  WidgetGroup createWidgetGroup(java.util.List<Widget> widgets);

  WidgetAnnotation createWidgetAnnotation(Widget widget, String id);

  CompositeWidgetAnnotation createCompositeWidgetAnnotation(Widget widget, String id, TemplateInstantiationAnnotation widgetInclude,
          RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation);

  TemplateInstantiationAnnotation createTemplateInstantiationAnnotation(Widget widget, String templateId, String pageId, String placeholderId);

  GridBagLayoutAnnotation createGridBagLayoutAnnotation(Widget widget);

  VerticalBoxLayoutAnnotation createVerticalBoxLayoutAnnotation(Widget widget);

  VerticalBoxLayout createVerticalBoxLayout(Collection<Widget> widgets);

  SelectableList createSelectableList(String widgetId, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection);

  TextArea createTextArea(String widgetId, Integer x, Integer y, Integer width, Integer height);

  NumericSpinner createNumericSpinner(String widgetId, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue);

  TableColumn createTableColumn(String label);

  TemplateAnnotation createTemplateAnnotation(Widget widget, String templateId);

  GridBagLayout createGridBagLayout();

  Repetition createRepetition(String widgetId, Integer x, Integer y, Integer width, Integer height, Collection<Widget> widgets, Integer rows,
          Integer columns, String containerId);

  RepetitionAnnotation createRepetitionAnnotation(Widget widget);

}
