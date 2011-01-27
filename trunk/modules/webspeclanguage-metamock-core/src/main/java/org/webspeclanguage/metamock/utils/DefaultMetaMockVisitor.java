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

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.List;
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
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;


/**
 * Trivial {@link MetaMockVisitor} abstract implementation that returns a default value
 * for all metamock controls visited
 * 
 * @author Jose Matias Rivero
 */
public abstract class DefaultMetaMockVisitor<T> implements MetaMockVisitor<T> {

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

  public T visitModel(MetaMockModel metaMockModel) {
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
