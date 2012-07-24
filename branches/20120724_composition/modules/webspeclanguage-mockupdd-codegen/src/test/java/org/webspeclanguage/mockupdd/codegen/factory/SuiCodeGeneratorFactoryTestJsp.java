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
package org.webspeclanguage.mockupdd.codegen.factory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFileList;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeWriter;
import org.webspeclanguage.mockupdd.codegen.framework.core.DefaultCodeWriter;
import org.webspeclanguage.mockupdd.codegen.jsp.JspGenerator;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TableColumn;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * @author Soledad Palacios, Marga Kitagawa
 */
public class SuiCodeGeneratorFactoryTestJsp extends TestCase {

  private static String getDestinationFolder() {
    return "src/test/resources/org/webspeclanguage/mockupdd/codegen/factory/";
  }

  @Override
  public void setUp() {

  }

  public void testJsp() throws Exception {
    SuiFactory factory = SuiDefaultConfig.getInstance().getFactory();
    SuiModel m = factory.createSuiModel();
    Button b = factory.createButton("button", 15, 15, 15, 15, "miButton");
    TextBox tb = factory.createTextBox("tb", 15, 15, 15, 15);
    Page p = factory.createPage("p", 100, 100, 100, 100, "mi page", "container");

    p.addChild(tb);
    m.addPage(p);
    ComboBox cm = factory.createComboBox("combobox", 15, 15, 12, 12);
    p.addChild(cm);
    p.addChild(b);
    SelectableList sl = factory.createSelectableList("miSelectealbeList", 23, 23, 23, 23, true);
    p.addChild(sl);
    Table t = factory.createTable("tabla", 34, 45, 45, 45);
    TableColumn col = factory.createTableColumn("taller de java");
    TableColumn col2 = factory.createTableColumn("Objetos 2");
    t.addColumn(col);
    t.addColumn(col2);
    p.addChild(t);

    Page p1 = factory.createPage("p1", 100, 100, 100, 100, "tu page", "container");
    Panel panel = factory.createPanel("panel", 0, 0, 100, 100, "c");
    Panel innerPanel = factory.createPanel("innerPanel", 0, 0, 100, 100, "c");
    Button button = factory.createButton("b", 0, 0, 100, 100, "Button");
    // innerPanel.addChild(button);
    panel.addChild(innerPanel);
    panel.addChild(button);
    panel.addChild(tb);
    TextArea ta = factory.createTextArea("miTextArea", 50, 50, 50, 50);

    RadioButton radio = factory.createRadioButton("miRadioButton", 23, 23, 23, 23, "radiobutton");
    panel.addChild(radio);
    panel.addChild(ta);

    p1.addChild(panel);
    m.addPage(p1);
    List<Widget> misW = new ArrayList<Widget>();

    List<Widget> misW2 = new ArrayList<Widget>();

    Repetition r = factory.createRepetition("miRepetition", 12, 12, 12, 12, misW2, 3, 1, "nahhh");
    Repetition r2 = factory.createRepetition("miRepetition2", 12, 12, 12, 12, misW, 3, 1, "nahhh2");
    p1.addChild(r);
    p1.addChild(r2);
    CodeWriter cw = new DefaultCodeWriter();
    JspGenerator jspG = new JspGenerator();
    CodeFileList<CodeArtifact> artifacts = jspG.generateFrom(m);
    artifacts.setFolderPathForFiles(getDestinationFolder());
    artifacts.writeOn(cw);
    cw.flush();

    this.assertFiles("", "mipage.jsp");
  }

  public void assertFiles(String folder, String... filenames) {
    for (String filename : filenames) {
      System.out.println("filename es:" + filename);
      assertTrue(new File(getDestinationFolder() + filename).exists());
    }
  }

  public void assertNoFiles(String folder) {
    HiddenFileFilter hf = new HiddenFileFilter();
    File f = new File(getDestinationFolder() + folder);
    System.out.println(f.listFiles(hf).length);
    // assertEquals(0, new File(getDestinationFolder() + folder).listFiles(new
    // HiddenFileFilter()).length);
  }

  private class HiddenFileFilter implements FileFilter {

    public boolean accept(File pathname) {
      return !pathname.isHidden();
    }
  }
}