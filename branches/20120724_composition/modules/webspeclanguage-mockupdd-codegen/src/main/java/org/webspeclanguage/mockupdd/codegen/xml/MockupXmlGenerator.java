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
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.webspeclanguage.mockupdd.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.InputWidget;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;

/**
 * @author Jose Matias Rivero
 */
public class MockupXmlGenerator extends DefaultWidgetGenerator<Element> implements SuiCodeGenerator<Collection<DocumentFile>> {

  private Namespace namespace;
  private boolean generateOriginalPosition;
  private boolean generateLayout;
  
  public MockupXmlGenerator() {
    this(false, false);
  }
  
  public MockupXmlGenerator(boolean generateOriginalPosition, boolean generateLayout) {
    this.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/sui"));
    this.generateOriginalPosition = generateOriginalPosition;
    this.generateLayout = generateLayout;
  }
  
  @Override
  public Element visitButton(Button button) {
    return 
      this.addCommonFeatures(this.createElement("button")
      .setAttribute("label", button.getText()), button);
  }

  @Override
  public Element visitLink(Link link) {
    return
      this.addCommonFeatures(this.createElement("link")
      .setAttribute("label", link.getText()), link);
  }

  @Override
  public Element visitList(org.webspeclanguage.mockupdd.sui.model.List list) {
    return this.addCommonFeatures(this.createElement("list"), list);
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
      this.addCommonFeatures(this.createElement("checkBox")
      .setAttribute("label", checkBox.getText()), checkBox);
  }
  
  @Override
  public Element visitLabel(Label label) {
    return 
      this.addCommonFeatures(this.createElement("label")
      .setAttribute("label", label.getText()), label);
  }

  @Override
  public Element visitComboBox(ComboBox comboBox) {
    return this.addCommonFeatures(this.createElement("comboBox"), comboBox);
  }

  @Override
  public Element visitPage(Page page) {
    return 
      this.addFlowLayout(0, 
        this.createElement("ui")
        .setAttribute("id", page.getWidgetId())
        .setAttribute("title", page.getTitle())
        .addContent(this.generateCompositeWidgetContent(page.getWidgets())));      
  }

  private Document createDocument(Element root) {
    Namespace xsiNs = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    root.addNamespaceDeclaration(xsiNs);
    root.setNamespace(Namespace.getNamespace("http://www.webspeclanguage.org/sui"));
    return new Document(root
      .setAttribute("schemaLocation", "http://www.webspeclanguage.org/sui sui.xsd", xsiNs));
  }

  private Element generateCompositeWidgetContent(Collection<Widget> widgets) {
    Collection<Element> generatedElements = new ArrayList<Element>();
    for (Widget c : widgets) {
      Element e = c.accept(this);
      if (e != null) {
        generatedElements.add(e);
      }
    }
    return this.createElement("widgets").addContent(generatedElements);
  }

  @Override
  public Element visitPanel(Panel panel) {
    return this.generateSimpleCompositeWidget(panel, "panel");
  }

  private Element generateSimpleCompositeWidget(CompositeWidget widget, String widgetName) {
    return this.addFlowLayout(1, 
    this.addCommonFeatures(
      this.createElement(widgetName), widget)
      .addContent(this.generateCompositeWidgetContent(widget.getWidgets())));
  }
  
  @Override
  public Element visitRepetition(Repetition repetition) {
    return this.generateSimpleCompositeWidget(repetition, "repetition");
  }

  @Override
  public Element visitRadioButton(RadioButton radioButton) {
    return 
      this.addCommonFeatures(this.createElement("radioButton")
      .setAttribute("label", radioButton.getText()), radioButton);
  }

  @Override
  public Element visitTextBox(TextBox textBox) {
    return this.addCommonFeatures(this.createElement("textBox"), textBox);
  }

  @Override
  public Element visitImage(Image image) {
    return this.addCommonFeatures(this.createElement("image"), image);
  }

  @Override
  public Element visitTextArea(TextArea textArea) {
    return this.addCommonFeatures(this.createElement("textArea"), textArea);
  }

  @Override
  public Element visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    return super.visitAbsoluteLayout(absoluteLayout);
  }

  @Override
  public Element getDefault() {
    return null;
  }
  
  private Element addCommonFeatures(Element element, Widget widget) {
    Element e = element.setAttribute("id", this.getWidgetId(widget));
    if (this.generateOriginalPosition) {
      e.addContent(this.createElement("originalPosition")
          .setAttribute("x", widget.getX().toString())
          .setAttribute("y", widget.getY().toString())
          .setAttribute("width", widget.getWidth().toString())
          .setAttribute("height", widget.getHeight().toString())
        );
    }
    if (widget.getAppliedTags().size() > 0) {
      this.addAppliedTags(e, widget);
    }
    if (widget instanceof InputWidget) {
      String sampleData = ((InputWidget) widget).getSampleData();
      if (sampleData != null) {
        e.setAttribute("sampleData", sampleData);
      }
    }
    return e;
  }

  private Element addAppliedTags(Element content, Widget widget) {
    Element tags = this.createElement("tags");
    for (TagApplication ta : widget.getAppliedTags()) {
      List<Element> values = new ArrayList<Element>();
      for (TagParameterValue pv : ta.getParameterValues()) {
        values.add(this.createElement("param").setAttribute("value", pv.getValue().getTextualRepresentation()));
      }
      tags.addContent(this.createElement("tag")
              .setAttribute("name", ta.getTag().getName())
              .setAttribute("tagSet", ta.getTag().getTagSet().getName())
              .addContent(values));
    }
    return content.addContent(tags);
  }

  private String getWidgetId(Widget widget) {
    if (widget.getFriendlyId().matches("^\\d*$")) {
      return "control" + widget.getFriendlyId();
    } else {
      return widget.getFriendlyId();
    }
  }
  
  private Element addFlowLayout(int position, Element compositeWidgetElement) {
    if (!this.generateLayout) {
      return compositeWidgetElement;
    }
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
