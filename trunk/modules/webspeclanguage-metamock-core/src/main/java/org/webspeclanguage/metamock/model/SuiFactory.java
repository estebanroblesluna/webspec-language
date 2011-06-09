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
package org.webspeclanguage.metamock.model;

import java.util.Collection;

import org.webspeclanguage.metamock.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.WidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.LayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.translator.WidgetGroup;

/**
 * Factory interface for constructing Sui elements instances
 * 
 * @author Jose Matias Rivero
 */
public interface SuiFactory {

  Button createButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Label createLabel(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  List createList(String controlId, Integer x, Integer y, Integer width, Integer height);

  ComboBox createComboBox(String controlId, Integer x, Integer y, Integer width, Integer height);

  Page createPage(String controlId, Integer x, Integer y, Integer width, Integer height, String title, String containerId);

  Panel createPanel(String controlId, Integer x, Integer y, Integer width, Integer height, String containerId);

  TextBox createTextBox(String controlId, Integer x, Integer y, Integer width, Integer height);

  DatePicker createDatePicker(String controlId, Integer x, Integer y, Integer width, Integer height);

  Image createImage(String controlId, Integer x, Integer y, Integer width, Integer height, String imageUrl);

  SuiModel createSuiModel();

  GridBagLayout createGridBagLayout(Collection<Widget> controls);

  Table createTable(String controlId, Integer x, Integer y, Integer width, Integer height);

  RadioButton createRadioButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  CheckBox createCheckBox(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Link createLink(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Annotation createAnnotation(String controlId, Integer x, Integer y, Integer width, Integer height, Widget c, String content);

  WidgetGroup createControlGroup(java.util.List<Widget> controls);

  WidgetAnnotation createControlAnnotation(Widget control, String id);

  CompositeWidgetAnnotation createCompositeControlAnnotation(Widget control, String id, TemplateInstantiationAnnotation controlInclude,
          RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation);

  TemplateInstantiationAnnotation createTemplateInstantiationAnnotation(Widget control, String templateId, String pageId, String placeholderId);

  GridBagLayoutAnnotation createGridBagLayoutAnnotation(Widget control);

  VerticalBoxLayoutAnnotation createVerticalBoxLayoutAnnotation(Widget control);

  VerticalBoxLayout createVerticalBoxLayout(Collection<Widget> controls);

  SelectableList createSelectableList(String controlId, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection);

  TextArea createTextArea(String controlId, Integer x, Integer y, Integer width, Integer height);

  NumericSpinner createNumericSpinner(String controlId, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue);

  TableColumn createTableColumn(String label);

  TemplateAnnotation createTemplateAnnotation(Widget control, String templateId);

  GridBagLayout createGridBagLayout();

  Repetition createRepetition(String controlId, Integer x, Integer y, Integer width, Integer height, Collection<Widget> controls, Integer rows,
          Integer columns, String containerId);

  RepetitionAnnotation createRepetitionAnnotation(Widget control);

}
