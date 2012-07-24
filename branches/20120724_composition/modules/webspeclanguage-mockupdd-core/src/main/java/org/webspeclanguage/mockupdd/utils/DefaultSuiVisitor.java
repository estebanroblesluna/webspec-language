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
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;


/**
 * Trivial {@link SuiVisitor} abstract implementation that returns a default value
 * for all metamock widgets visited
 * 
 * @author Jose Matias Rivero
 */
public abstract class DefaultSuiVisitor<T> implements SuiVisitor<T> {

  public abstract T getDefaultValue();

  public T visitAnnotation(Annotation annotation) {
    return this.getDefaultValue();
  }

  public T visitButton(Button button) {
    return this.getDefaultValue();
  }

  public T visitCheckBox(CheckBox checkBox) {
    return this.getDefaultValue();
  }

  public T visitComboBox(ComboBox comboBox) {
    return this.getDefaultValue();
  }

  public T visitDatePicker(DatePicker datePicker) {
    return this.getDefaultValue();
  }

  public T visitGridBagLayout(GridBagLayout gbl) {
    return this.getDefaultValue();
  }

  public T visitImage(Image image) {
    return this.getDefaultValue();
  }

  public T visitLabel(Label label) {
    return this.getDefaultValue();
  }

  public T visitLink(Link link) {
    return this.getDefaultValue();
  }

  public T visitList(List list) {
    return this.getDefaultValue();
  }

  public T visitModel(SuiModel metaMockModel) {
    return this.getDefaultValue();
  }

  public T visitPage(Page page) {
    return this.getDefaultValue();
  }

  public T visitPanel(Panel panel) {
    return this.getDefaultValue();
  }

  public T visitRadioButton(RadioButton radioButton) {
    return this.getDefaultValue();
  }

  public T visitTable(Table table) {
    return this.getDefaultValue();
  }

  public T visitTextBox(TextBox textBox) {
    return this.getDefaultValue();
  }

  public T visitVerticalBoxLayout(VerticalBoxLayout verticalBoxLayout) {
    return this.getDefaultValue();
  }

  public T visitSelectableList(SelectableList selectableList) {
    return this.getDefaultValue();
  }

  public T visitTextArea(TextArea textArea) {
    return this.getDefaultValue();
  }

  public T visitNumericSpinner(NumericSpinner numericSpinner) {
    return this.getDefaultValue();
  }

  public T visitTableColumn(TableColumn tableColumn) {
    return this.getDefaultValue();
  }

  public T visitRepetition(Repetition repetition) {
    return this.getDefaultValue();
  }

  public T visitGridBagLayoutCell(GridBagLayoutCell gridBagLayoutCell) {
    return this.getDefaultValue();
  }

  public T visitAbsoluteLayoutInfo(AbsoluteLayoutInfo absoluteLayoutInfo) {
    return this.getDefaultValue();
  }

  public T visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    return this.getDefaultValue();
  }
  
  
}
