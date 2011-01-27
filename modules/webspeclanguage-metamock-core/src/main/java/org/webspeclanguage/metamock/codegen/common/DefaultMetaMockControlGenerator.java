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
package org.webspeclanguage.metamock.codegen.common;

import org.webspeclanguage.metamock.codegen.artifacts.CodeBlock;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.CompositeControl;
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
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.Layout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * @author Jose Matias Rivero
 */
public abstract class DefaultMetaMockControlGenerator<T extends CodeArtifact> implements MetaMockVisitor<T> {

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

  public T visitModel(MetaMockModel metaMockModel) {
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

  public CodeBlock<CodeArtifact> generateBlockFromCompositeControl(CompositeControl cc) {
    CodeBlock<CodeArtifact> cb = new CodeBlock<CodeArtifact>();
    for (UIControl c : cc.getControls()) {
      cb.add(c.visit(this));
    }
    return cb;
  }

  public abstract T getDefault();

}
