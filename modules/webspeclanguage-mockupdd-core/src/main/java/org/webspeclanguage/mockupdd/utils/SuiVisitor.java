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
 * Metamock widget visitor interface
 * 
 * @author Jose Matias Rivero
 */
public interface SuiVisitor<T> {

	T visitButton(Button button);

	T visitComboBox(ComboBox comboBox);

	T visitDatePicker(DatePicker datePicker);

	T visitImage(Image image);

	T visitLabel(Label label);

	T visitList(List list);

	T visitPage(Page page);

	T visitPanel(Panel panel);

	T visitTextBox(TextBox textBox);

	T visitTable(Table table);

	T visitCheckBox(CheckBox checkBox); 

	T visitRadioButton(RadioButton radioButton);

	T visitLink(Link link);
	
	T visitGridBagLayout(GridBagLayout gbl);

	T visitVerticalBoxLayout(VerticalBoxLayout verticalBoxLayout);

	T visitAnnotation(Annotation annotation);

	T visitModel(SuiModel metaMockModel);
	
	T visitSelectableList(SelectableList selectableList);

	T visitTextArea(TextArea textArea);

	T visitNumericSpinner(NumericSpinner numericSpinner);

	T visitTableColumn(TableColumn tableColumn);

	T visitRepetition(Repetition repetitionImpl);

  T visitGridBagLayoutCell(GridBagLayoutCell gridBagLayoutCell);

  T visitAbsoluteLayoutInfo(AbsoluteLayoutInfo absoluteLayoutInfo);

  T visitAbsoluteLayout(AbsoluteLayout absoluteLayout);

	
}
