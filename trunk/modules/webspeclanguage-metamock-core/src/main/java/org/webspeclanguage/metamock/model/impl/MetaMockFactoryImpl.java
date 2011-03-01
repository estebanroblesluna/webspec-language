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
package org.webspeclanguage.metamock.model.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.List;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.MetaMockModel;
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
import org.webspeclanguage.metamock.model.annotation.CompositeControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.ControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.LayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.impl.CompositeControlAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.ControlAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.GridBagLayoutAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.RepetitionAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.TemplateAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.TemplateInstantiationAnnotationImpl;
import org.webspeclanguage.metamock.model.annotation.impl.VerticalBoxLayoutAnnotationImpl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.model.layout.impl.GridBagLayoutImpl;
import org.webspeclanguage.metamock.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.metamock.model.layout.impl.VerticalBoxLayoutImpl;
import org.webspeclanguage.metamock.translator.DefaultMetaMockControlGroupImpl;
import org.webspeclanguage.metamock.translator.MetaMockControlGroup;
import org.webspeclanguage.metamock.utils.MetaMockUtil;

/**
 * Default implementation of {@link MetaMockFactory}
 * 
 * @author Jose Matias Rivero
 */
public class MetaMockFactoryImpl implements MetaMockFactory {

  public Button createButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new ButtonImpl(controlId, x, y, width, height, text);
  }

  public ComboBox createComboBox(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new ComboBoxImpl(controlId, x, y, width, height);
  }

  public DatePicker createDatePicker(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new DatePickerImpl(controlId, x, y, width, height);
  }

  public Label createLabel(String controlId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new LabelImpl(controlId, x, y, width, height, text);
  }

  public List createList(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new ListImpl(controlId, x, y, width, height);
  }

  public Page createPage(String controlId, Integer x, Integer y, Integer width, Integer height, String title, String containerId) {
    return new PageImpl(controlId, x, y, width, height, title, containerId);
  }

  public Panel createPanel(String controlId, Integer x, Integer y, Integer width, Integer height, String containerId) {
    return new PanelImpl(controlId, x, y, width, height, containerId);
  }

  public TextBox createTextBox(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new TextBoxImpl(controlId, x, y, width, height);
  }

  public Image createImage(String controlId, Integer x, Integer y, Integer width, Integer height, String imageUrl) {
    return new ImageImpl(controlId, x, y, width, height, imageUrl);
  }

  public MetaMockModel createMetaMockModel() {
    return new MetaMockModelImpl(new ArrayList<Page>());
  }

  public GridBagLayout createGridBagLayout(Collection<UIControl> controls) {
    return new ScanBasedGridBagLayoutFactory().createLayout(controls);
  }

  public GridBagLayout createGridBagLayout() {
    return new GridBagLayoutImpl();
  }

  public Table createTable(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new TableImpl(controlId, x, y, width, height);
  }

  public CheckBox createCheckBox(String controlId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new CheckBoxImpl(controlId, x, y, width, height, text);
  }

  public RadioButton createRadioButton(String controlId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new RadioButtonImpl(controlId, x, y, width, height, text);
  }

  public Link createLink(String controlId, Integer x, Integer y, Integer width, Integer height, String text) {
    return new LinkImpl(controlId, x, y, width, height, text);
  }

  public SelectableList createSelectableList(String controlId, Integer x, Integer y, Integer width, Integer height, Boolean multipleSelection) {
    return new SelectableListImpl(controlId, x, y, width, height, multipleSelection);
  }

  public MetaMockControlGroup createControlGroup(java.util.List<UIControl> controls) {
    return new DefaultMetaMockControlGroupImpl(controls);
  }

  public Annotation createAnnotation(String controlId, Integer x, Integer y, Integer width, Integer height, UIControl c, String content) {
    return new AnnotationImpl(controlId, x, y, width, height, c, content);
  }

  public ControlAnnotation createControlAnnotation(UIControl control, String id) {
    return new ControlAnnotationImpl(control, id);
  }

  public CompositeControlAnnotation createCompositeControlAnnotation(UIControl control, String id,
          TemplateInstantiationAnnotation templateInstantiationAnnotation, RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation) {
    return new CompositeControlAnnotationImpl(control, id, templateInstantiationAnnotation, repetitionAnnotation, layoutAnnotation);
  }

  public TemplateInstantiationAnnotation createTemplateInstantiationAnnotation(UIControl control, String templateId, String pageId, String placeholderId) {
    return new TemplateInstantiationAnnotationImpl(control, templateId, pageId, placeholderId);
  }

  public GridBagLayoutAnnotation createGridBagLayoutAnnotation(UIControl control) {
    return new GridBagLayoutAnnotationImpl(control);
  }

  public VerticalBoxLayoutAnnotation createVerticalBoxLayoutAnnotation(UIControl control) {
    return new VerticalBoxLayoutAnnotationImpl(control);
  }

  public VerticalBoxLayout createVerticalBoxLayout(Collection<UIControl> controls) {
    return new VerticalBoxLayoutImpl(MetaMockUtil.getControlsGroupedInColumns(controls));
  }

  public TextArea createTextArea(String controlId, Integer x, Integer y, Integer width, Integer height) {
    return new TextAreaImpl(controlId, x, y, width, height);
  }

  public NumericSpinner createNumericSpinner(String controlId, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue) {
    return new NumericSpinnerImpl(controlId, x, y, width, height, minValue, maxValue);
  }

  public TableColumn createTableColumn(String label) {
    return new TableColumnImpl(label);
  }

  public TemplateAnnotation createTemplateAnnotation(UIControl control, String templateId) {
    return new TemplateAnnotationImpl(control, templateId);
  }

  public Repetition createRepetition(String controlId, Integer x, Integer y, Integer width, Integer height, Collection<UIControl> controls, Integer rows,
          Integer columns, String containerId) {
    return new RepetitionImpl(controlId, x, y, width, height, controls, rows, columns, containerId);
  }

  public RepetitionAnnotation createRepetitionAnnotation(UIControl c) {
    return new RepetitionAnnotationImpl(c);
  }

}
