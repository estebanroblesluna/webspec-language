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

import org.webspeclanguage.metamock.model.annotation.CompositeControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.ControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.LayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.translator.MetaMockControlGroup;

/**
 * Factory interface for constructing MetaMock elements instances
 * 
 * @author Jose Matias Rivero
 */
public interface MetaMockFactory {

  Button createButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Label createLabel(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  List createList(String controlId, Integer x, Integer y, Integer width, Integer height);

  ComboBox createComboBox(String controlId, Integer x, Integer y, Integer width, Integer height);

  Page createPage(String controlId, Integer x, Integer y, Integer width, Integer height, String title, String containerId);

  Panel createPanel(String controlId, Integer x, Integer y, Integer width, Integer height, String containerId);

  TextBox createTextBox(String controlId, Integer x, Integer y, Integer width, Integer height);

  DatePicker createDatePicker(String controlId, Integer x, Integer y, Integer width, Integer height);

  Image createImage(String controlId, Integer x, Integer y, Integer width, Integer height, String imageUrl);

  MetaMockModel createMetaMockModel();

  GridBagLayout createGridBagLayout(Collection<UIControl> controls);

  Table createTable(String controlId, Integer x, Integer y, Integer width, Integer height);

  RadioButton createRadioButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  CheckBox createCheckBox(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Link createLink(String controlId, Integer x, Integer y, Integer width, Integer height, String text);

  Annotation createAnnotation(String controlId, Integer x, Integer y, Integer width, Integer height, UIControl c, String content);

  MetaMockControlGroup createControlGroup(java.util.List<UIControl> controls);

  ControlAnnotation createControlAnnotation(UIControl control, String id);

  CompositeControlAnnotation createCompositeControlAnnotation(UIControl control, String id, TemplateInstantiationAnnotation controlInclude,
          RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation);

  TemplateInstantiationAnnotation createTemplateInstantiationAnnotation(UIControl control, String templateId, String pageId, String placeholderId);

  GridBagLayoutAnnotation createGridBagLayoutAnnotation(UIControl control);

  VerticalBoxLayoutAnnotation createVerticalBoxLayoutAnnotation(UIControl control);

  VerticalBoxLayout createVerticalBoxLayout(Collection<UIControl> controls);

  SelectableList createSelectableList(String controlId, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection);

  TextArea createTextArea(String controlId, Integer x, Integer y, Integer width, Integer height);

  NumericSpinner createNumericSpinner(String controlId, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue);

  TableColumn createTableColumn(String label);

  TemplateAnnotation createTemplateAnnotation(UIControl control, String templateId);

  GridBagLayout createGridBagLayout();

  Repetition createRepetition(String controlId, Integer x, Integer y, Integer width, Integer height, Collection<UIControl> controls, Integer rows,
          Integer columns, String containerId);

  RepetitionAnnotation createRepetitionAnnotation(UIControl control);

}
