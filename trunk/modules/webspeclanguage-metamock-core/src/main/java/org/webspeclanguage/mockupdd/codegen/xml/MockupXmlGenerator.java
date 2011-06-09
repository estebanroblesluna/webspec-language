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
package org.webspeclanguage.mockupdd.codegen.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.webspeclanguage.mockupdd.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;

/**
 * @author Jose Matias Rivero
 */
public class MockupXmlGenerator extends DefaultWidgetGenerator<Element> implements SuiCodeGenerator<Collection<DocumentFile>> {

  private Namespace namespace;
  
  public MockupXmlGenerator() {
    this.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/metamock"));
  }
  
  public Document generateInSingleDocument(SuiModel model) {
    Element mockups = this.createElement("mockups");
    for (Page p : model.getPages()) {
      mockups.addContent(p.accept(this)); 
    };
    return this.createDocument(mockups);
  }
  
  public Collection<DocumentFile> generateFrom(SuiModel model) {
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
        .setAttribute("id", page.getWidgetId())
        .setAttribute("title", page.getTitle())
        .addContent(this.generateCompositeWidgetContent(page.getLayout().getWidgets())));      
  }

  private Document createDocument(Element root) {
    Namespace xsiNs = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    root.addNamespaceDeclaration(xsiNs);
    root.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/metamock"));
    return new Document(root
      .setAttribute("schemaLocation", "http://www.webspeclanguage.org/metamock ../metamock.xsd", xsiNs));
  }

  private Collection<Element> generateCompositeWidgetContent(Collection<Widget> widgets) {
    Collection<Element> generatedElements = new ArrayList<Element>();
    for (Widget c : widgets) {
      Element e = c.accept(this);
      if (e != null) {
        generatedElements.add(e);
      }
    }
    return generatedElements;
  }

  @Override
  public Element visitPanel(Panel panel) {
    return this.generateSimpleCompositeWidget(panel, "panel");
  }

  private Element generateSimpleCompositeWidget(CompositeWidget widget, String widgetName) {
    return this.addFlowLayout(1, 
    this.addCommonAttributes(
      this.createElement(widgetName), widget)
      .addContent(this.generateCompositeWidgetContent(widget.getLayout().getWidgets())));
  }
  
  @Override
  public Element visitRepetition(Repetition repetition) {
    return this.generateSimpleCompositeWidget(repetition, "repetition");
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
  
  private Element addCommonAttributes(Element element, Widget widget) {
    return element
      .setAttribute("id", this.getWidgetId(widget))
      .addContent(this.createElement("originalPosition")
        .setAttribute("x", widget.getX().toString())
        .setAttribute("y", widget.getY().toString())
        .setAttribute("width", widget.getWidth().toString())
        .setAttribute("height", widget.getHeight().toString())
      );
  }

  private String getWidgetId(Widget widget) {
    if (widget.getFriendlyId().matches("^\\d*$")) {
      return "control" + widget.getFriendlyId();
    } else {
      return widget.getFriendlyId();
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
