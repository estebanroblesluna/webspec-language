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
package org.webspeclanguage.mockupdd.codegen.common;

import org.webspeclanguage.mockupdd.codegen.artifacts.CodeBlock;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
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
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.Layout;
import org.webspeclanguage.mockupdd.sui.model.layout.VerticalBoxLayout;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public abstract class DefaultWidgetGenerator<T> implements SuiVisitor<T> {

  public T visitButton(Button button) {
    return this.getDefault();
  }

  public T visitCheckBox(CheckBox checkBox) {
    return this.getDefault();
  }

  public T visitComboBox(ComboBox comboBox) {
    return this.getDefault();
  }

  public T visitDatePicker(DatePicker datePicker) {
    return this.getDefault();
  }

  public T visitImage(Image image) {
    return this.getDefault();
  }

  public T visitLabel(Label label) {
    return this.getDefault();
  }

  public T visitLink(Link link) {
    return this.getDefault();
  }

  public T visitList(List list) {
    return this.getDefault();
  }

  public T visitPage(Page page) {
    return this.getDefault();
  }

  public T visitPanel(Panel panel) {
    return this.getDefault();
  }

  public T visitRadioButton(RadioButton radioButton) {
    return this.getDefault();
  }

  public T visitTable(Table table) {
    return this.getDefault();
  }

  public T visitTextBox(TextBox textBox) {
    return this.getDefault();
  }

  public T visitGridBagLayout(GridBagLayout gbl) {
    return this.getDefault();
  }

  public T visitVerticalBoxLayout(VerticalBoxLayout verticalBoxLayout) {
    return this.getDefault();
  }

  public T visitAnnotation(Annotation annotation) {
    return this.getDefault();
  }

  public T visitModel(SuiModel metaMockModel) {
    return this.getDefault();
  }

  public T visitSelectableList(SelectableList selectableList) {
    return this.getDefault();
  }

  public T visitTextArea(TextArea textArea) {
    return this.getDefault();
  }

  public T visitNumericSpinner(NumericSpinner numericSpinner) {
    return this.getDefault();
  }

  public T visitTableColumn(TableColumn tableColumn) {
    return this.getDefault();
  }

  public T visitRepetition(Repetition repetition) {
    return this.getDefault();
  }

  public T visitGridBagLayoutCell(GridBagLayoutCell gridBagLayoutCell) {
    return this.getDefault();
  }

  public T visitAbsoluteLayoutInfo(AbsoluteLayoutInfo absoluteLayoutInfo) {
    return this.getDefault();
  }

  public T visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    return this.getDefault();
  }

  public abstract T getDefault();

}
