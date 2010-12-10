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
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.VerticalBoxLayout;


/**
 * Metamock control visitor interface
 * 
 * @author Jose Matias Rivero
 */
public interface MetaMockVisitor<T> {

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
	
	T visitGridBoxLayout(GridBagLayout gbl);

	T visitVerticalBoxLayout(VerticalBoxLayout verticalBoxLayout);

	T visitAnnotation(Annotation annotation);

	T visitModel(MetaMockModel metaMockModel);
	
	T visitSelectableList(SelectableList selectableList);

	T visitTextArea(TextArea textArea);

	T visitNumericSpinner(NumericSpinner numericSpinner);

	T visitTableColumn(TableColumn tableColumn);

	T visitRepetition(Repetition repetitionImpl);

	
}
