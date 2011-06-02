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
package org.webspeclanguage.metamock.codegen.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.webspeclanguage.metamock.codegen.common.DefaultMetaMockControlGenerator;
import org.webspeclanguage.metamock.codegen.common.MetaMockCodeGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.RadioButton;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.TextArea;
import org.webspeclanguage.metamock.model.TextBox;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;

/**
 * @author Jose Matias Rivero
 */
public class MockupXmlGenerator extends DefaultMetaMockControlGenerator<Element> implements MetaMockCodeGenerator<Collection<DocumentFile>> {

  private Namespace namespace;
  
  public MockupXmlGenerator() {
    this.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/metamock"));
  }
  
  public Document generateInSingleDocument(MetaMockModel model) {
    Element mockups = this.createElement("mockups");
    for (Page p : model.getPages()) {
      mockups.addContent(p.accept(this)); 
    };
    return this.createDocument(mockups);
  }
  
  public Collection<DocumentFile> generateFrom(MetaMockModel model) {
    Collection<DocumentFile> generatedFiles = new ArrayList<DocumentFile>();
    for (Page p : model.getPages()) {
      generatedFiles.add(
              new DocumentFile(CodegenUtil.convertToIdentifier(p.getTitle()) + ".xml", 
                      this.generateDocumentForPage(p)));
    }
    return generatedFiles;
  }

  private Document generateDocumentForPage(Page p) {
    return this.createDocument(this.createElement("mockups").addContent(p.accept(this)));
  }

  @Override
  public Element visitCheckBox(CheckBox checkBox) {
    return 
      this.addCommonAttributes(this.createElement("checkBox")
      .setAttribute("label", checkBox.getText()), checkBox);
  }

  @Override
  public Element visitComboBox(ComboBox comboBox) {
    return this.addCommonAttributes(this.createElement("comboBox"), comboBox);
  }

  @Override
  public Element visitPage(Page page) {
    return 
      this.addFlowLayout(0, 
        this.createElement("ui")
        .setAttribute("id", page.getControlId())
        .setAttribute("title", page.getTitle())
        .addContent(this.generateCompositeControlContent(page.getLayout().getControls())));      
  }

  private Document createDocument(Element root) {
    Namespace xsiNs = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    root.addNamespaceDeclaration(xsiNs);
    root.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/metamock"));
    return new Document(root
      .setAttribute("schemaLocation", "http://www.webspeclanguage.org/metamock ../metamock.xsd", xsiNs));
  }

  private Collection<Element> generateCompositeControlContent(Collection<UIControl> controls) {
    Collection<Element> generatedElements = new ArrayList<Element>();
    for (UIControl c : controls) {
      Element e = c.accept(this);
      if (e != null) {
        generatedElements.add(e);
      }
    }
    return generatedElements;
  }

  @Override
  public Element visitPanel(Panel panel) {
    return this.generateSimpleCompositeControl(panel, "panel");
  }

  private Element generateSimpleCompositeControl(CompositeControl control, String controlName) {
    return this.addFlowLayout(1, 
    this.addCommonAttributes(
      this.createElement(controlName), control)
      .addContent(this.generateCompositeControlContent(control.getLayout().getControls())));
  }
  
  @Override
  public Element visitRepetition(Repetition repetition) {
    return this.generateSimpleCompositeControl(repetition, "repetition");
  }

  @Override
  public Element visitRadioButton(RadioButton radioButton) {
    return 
      this.addCommonAttributes(this.createElement("radioButton")
      .setAttribute("label", radioButton.getText()), radioButton);
  }

  @Override
  public Element visitTextBox(TextBox textBox) {
    return this.addCommonAttributes(this.createElement("textBox"), textBox);
  }

  @Override
  public Element visitTextArea(TextArea textArea) {
    return this.addCommonAttributes(this.createElement("textArea"), textArea);
  }

  @Override
  public Element visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    return super.visitAbsoluteLayout(absoluteLayout);
  }

  @Override
  public Element getDefault() {
    return null;
  }
  
  private Element addCommonAttributes(Element element, UIControl control) {
    return element
      .setAttribute("id", this.getControlId(control))
      .addContent(this.createElement("originalPosition")
        .setAttribute("x", control.getX().toString())
        .setAttribute("y", control.getY().toString())
        .setAttribute("width", control.getWidth().toString())
        .setAttribute("height", control.getHeight().toString())
      );
  }

  private String getControlId(UIControl control) {
    if (control.getFriendlyId().matches("^\\d*$")) {
      return "control" + control.getFriendlyId();
    } else {
      return control.getFriendlyId();
    }
  }
  
  private Element addFlowLayout(int position, Element compositeWidgetElement) {
    return compositeWidgetElement.addContent(position,
            this.createElement("layout").addContent(
                    this.createElement("flowLayout")));
  }
  
  private Element createElement(String name) {
    return new Element(name, this.getNamespace());
  }

  private void setNamespace(Namespace namespace) {
    this.namespace = namespace;
  }

  private Namespace getNamespace() {
    return namespace;
  }

}
