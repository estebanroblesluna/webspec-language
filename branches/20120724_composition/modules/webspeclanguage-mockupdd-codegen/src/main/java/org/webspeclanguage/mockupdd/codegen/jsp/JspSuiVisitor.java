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
package org.webspeclanguage.mockupdd.codegen.jsp;

import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.DefaultSuiVisitor;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Soledad Palacios, Marga Kitagawa
 */
public class JspSuiVisitor extends DefaultSuiVisitor<String> {

  private boolean hayForm = false;
  int num = 1;
  int inden = 2;

  public String indentacion(int cant) {
    String res = "";
    for (int i = 0; i < cant; i++)
      res += "\t";
    return res;
  }

  public String visitButton(Button button) {
    return indentacion(inden) + "<input type='submit' id='" + button.getWidgetId() + "' value='" + button.getText() + "'></input>\n";
  }
  public String visitTextBox(TextBox textBox) {
    return indentacion(inden) + "<input type='text' id='" + textBox.getWidgetId() + "'></input>\n";
  }
  public String visitPage(Page page) {
    inden++;

    String res = indentacion(inden) + "<div id='" + page.getWidgetId() + "'>\n";
    for (Widget w : page.getWidgets())
      res += w.accept(this) + "\n";

    inden--;
    res += indentacion(inden) + "</div>\n";
    return res;
  }

  public String visitPanel(Panel panel) {
    inden++;
    String res = indentacion(inden) + "<div id='" + panel.getWidgetId() + "'>\n";
    if ((SuiUtil.hasInputWidgets(panel)) && (!hayForm)) {
      inden++;
      res += indentacion(inden) + "<form method='post' action=''> \n";
      hayForm = true;
      for (Widget w : panel.getWidgets())
        res += "\t" + w.accept(this);
      inden--;
      res += indentacion(inden) + "</form>\n";
      hayForm = false;

    } else
      for (Widget w : panel.getWidgets())
        res += indentacion(inden) + w.accept(this) + "\n";
    inden--;
    res += indentacion(inden) + "</div>\n";

    return res;
  }

  public String visitRepetition(Repetition repetition) {
    inden++;
    String res = indentacion(inden) + "<div id='" + repetition.getWidgetId() + "'>\n";
    if ((SuiUtil.hasInputWidgets(repetition)) && (!hayForm)) {
      inden++;
      res += indentacion(inden) + "<form method='post' action=''> \n\t";
      hayForm = true;
    }
    if (hayForm)
      for (Widget w : repetition.getWidgets())
        res += w.accept(this);
    else {
      num++;
      inden++;
      res += indentacion(inden) + "<%Map<Integer, String> dict" + num + "= new HashMap<Integer, String>();\n" + indentacion(inden) + "\tdict" + num
              + ".put(1,\" opcion 1\" );\n" + indentacion(inden) + "\tdict" + num + ".put(2,\"opcion 2\");\n" + indentacion(inden) + "\tdict" + num
              + ".put(3,\"opcion 3\");\n" + indentacion(inden) + "int z" + num + "=1;\n" + indentacion(inden) + "for(String s:dict" + num + ".values()){"
              + indentacion(inden) + "%>\n\t" + indentacion(inden) + "<p><%=s%></p>\n" + indentacion(inden) + "<%z" + num + "++;" + "}%>\n";

    }

    if (hayForm) {
      inden--;
      res += indentacion(inden) + "</form>";
      hayForm = false;
    }
    res += indentacion(inden) + "</div>\n";
    return res;
  }
  public String visitCheckBox(CheckBox checkBox) {
    inden++;
    String res = indentacion(inden) + "<input type='checkbox' name=''></input>\n";
    inden--;
    return res;
  }

  public String visitComboBox(ComboBox comboBox) {
    inden++;
    String res = indentacion(inden) + "<div id=\"" + comboBox.getWidgetId() + "\">\n";
    inden++;
    res += indentacion(inden++) + "<select id='" + comboBox.getWidgetId() + "'>\n\t" + indentacion(inden) + "<%\n";

    num++;
    res += indentacion(inden) + "Map<Integer, String> dict" + num + "= new HashMap<Integer, String>();\n" + indentacion(inden) + "dict" + num
            + ".put(1,\" opcion 1\" );\n" + indentacion(inden) + "dict" + num + ".put(2,\"opcion 2\");\n" + indentacion(inden) + "dict" + num
            + ".put(3,\"opcion 3\");\n" + indentacion(inden) + "int z" + num + "=1;\n" + indentacion(inden) + "Iterator it" + num + " =dict" + num
            + ".entrySet().iterator();\n" + indentacion(inden) + "while(it" + num + ".hasNext()) {\n" + indentacion(inden) + "Map.Entry ent" + num
            + " = (Map.Entry)it" + num + ".next();\n" + indentacion(inden) + "%><option value=<%= ent" + num + ".getKey()%>><%=ent" + num
            + ".getValue()%></option>\n" +

            indentacion(inden) + "<%z" + num + "++;\n" + indentacion(inden) + "}%>\n";
    inden--;
    res += indentacion(inden) + "</select>\n";
    inden--;
    res += indentacion(inden) + "</div>\n";

    return res;
  }

  public String visitImage(Image image) {
    inden++;
    String res = indentacion(inden) + "<img id='" + image.getWidgetId() + "' src='" + image.getImageUrl() + "'></img>\n";
    inden--;
    return res;
  }

  public String visitLabel(Label label) {
    inden++;
    String res = indentacion(inden) + "<label id='" + label.getWidgetId() + "'>" + label.getText() + "</label>\n";
    inden--;
    return res;
  }
  public String visitLink(Link link) {
    inden++;
    String res = indentacion(inden) + "<a href='#'>" + link.getText() + "</a>";
    inden--;
    return res;
  }

  public String visitSelectableList(SelectableList list) {

    inden++;
    String res = indentacion(inden) + "<div id=\"" + list.getWidgetId() + "\">\n";
    inden++;
    res += indentacion(inden++) + "<select multiple=\"multiple\" id='" + list.getWidgetId() + "'>\n\t" + indentacion(inden) + "<%\n";

    num++;
    res += indentacion(inden) + "Map<Integer, String> dict" + num + "= new HashMap<Integer, String>();\n" + indentacion(inden) + "dict" + num
            + ".put(1,\" opcion 1\" );\n" + indentacion(inden) + "dict" + num + ".put(2,\"opcion 2\");\n" + indentacion(inden) + "dict" + num
            + ".put(3,\"opcion 3\");\n" + indentacion(inden) + "int z" + num + "=1;\n" + indentacion(inden) + "Iterator it" + num + " =dict" + num
            + ".entrySet().iterator();\n" + indentacion(inden) + "while(it" + num + ".hasNext()) {\n" + indentacion(inden) + "Map.Entry ent" + num
            + " = (Map.Entry)it" + num + ".next();\n" + indentacion(inden) + "%><option value=<%= ent" + num + ".getKey()%>><%=ent" + num
            + ".getValue()%></option>\n" +

            indentacion(inden) + "<%z" + num + "++;\n" + indentacion(inden) + "}%>\n";
    inden--;
    res += indentacion(inden) + "</select>\n";
    inden--;
    res += indentacion(inden) + "</div>\n";

    return res;
  }

  public String visitTable(Table table) {
    inden++;
    String res = indentacion(inden) + "<%String nombreDeTabla=\"Materias\";%>\n";
    List<TableColumn> col = table.getColumns();
    res += indentacion(inden) + "<table id='" + table.getWidgetId() + "'>\n\t";
    inden++;
    for (TableColumn c : col) {
      res += indentacion(inden) + "<tr>\n" + c.accept(this) + indentacion(inden) + "</tr>\n";
    }
    inden--;
    return res += indentacion(inden) + "</table>\n";
  }

  public String visitTableColumn(TableColumn tableColumn) {
    String res;
    inden++;
    res = indentacion(inden) + "<td>" + tableColumn.getLabel() + "</td>\n";
    inden--;
    return res;
  }

  public String visitRadioButton(RadioButton radioButton) {
    return indentacion(inden) + "<input type='radio' id='" + radioButton.getWidgetId() + "' value='" + radioButton.getText() + "'/>" + radioButton.getText()
            + "\n";
  }

  public String visitTextArea(TextArea textArea) {
    return indentacion(inden) + "<textarea rows=" + textArea.getWidth() + " cols=" + textArea.getHeight() + "></textarea>\n ";
  }

  @Override
  public String getDefaultValue() {
    // TODO Auto-generated method stub
    return null;
  }

  public JspSuiVisitor() {
    super();
    // TODO Auto-generated constructor stub
  }

}
