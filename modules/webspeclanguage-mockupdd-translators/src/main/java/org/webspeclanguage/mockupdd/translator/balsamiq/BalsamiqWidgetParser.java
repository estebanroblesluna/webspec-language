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
package org.webspeclanguage.mockupdd.translator.balsamiq;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.DatePicker;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.WidgetGroup;
import org.webspeclanguage.mockupdd.sui.model.impl.DefaultWidgetGroupImpl;
import org.webspeclanguage.mockupdd.translator.MockupSourceParsingException;
import org.webspeclanguage.mockupdd.translator.WidgetParser;

/**
 * {@link WidgetParser} implementation for mockup widget parsing from
 * Balsamiq files
 * 
 * @author Jose Matias Rivero
 */
public class BalsamiqWidgetParser implements WidgetParser<String> {

  private static final String CONTROL_PROPERTIES = "controlProperties";
  private SuiFactory factory;
  private Map<String, Method> parserMapping;

  public BalsamiqWidgetParser(SuiFactory factory) {
    this.setFactory(factory);
    this.setParserMapping(new HashMap<String, Method>());
    this.initializeParsingMethods();
  }

  private void initializeParsingMethods() {
    this.addParserMapping("com.balsamiq.mockups::Button", "parseButton");
    this.addParserMapping("com.balsamiq.mockups::TextInput", "parseTextBox");
    this.addParserMapping("com.balsamiq.mockups::SearchBox", "parseTextBox");
    this.addParserMapping("com.balsamiq.mockups::TextArea", "parseTextArea");
    this.addParserMapping("com.balsamiq.mockups::NumericStepper", "parseNumericSpinner");
    this.addParserMapping("com.balsamiq.mockups::Label", "parseLabel");
    this.addParserMapping("com.balsamiq.mockups::Canvas", "parsePanel");
    this.addParserMapping("com.balsamiq.mockups::DateChooser", "parseDatePicker");
    this.addParserMapping("com.balsamiq.mockups::List", "parseList");
    this.addParserMapping("com.balsamiq.mockups::CheckBoxGroup", "parseSelectableList");
    this.addParserMapping("com.balsamiq.mockups::ComboBox", "parseComboBox");
    this.addParserMapping("com.balsamiq.mockups::DataGrid", "parseTable");
    this.addParserMapping("com.balsamiq.mockups::Image", "parseImage");
    this.addParserMapping("com.balsamiq.mockups::LineChart", "parseImage");
    this.addParserMapping("com.balsamiq.mockups::CheckBox", "parseCheckBox");
    this.addParserMapping("com.balsamiq.mockups::RadioButton", "parseRadioButton");
    this.addParserMapping("com.balsamiq.mockups::TitleWindow", "parsePage");
    this.addParserMapping("com.balsamiq.mockups::Link", "parseLink");
    this.addParserMapping("com.balsamiq.mockups::CallOut", "parseAnnotation");
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  private SuiFactory getFactory() {
    return factory;
  }

  private void setParserMapping(Map<String, Method> parsingMethodsMapping) {
    this.parserMapping = parsingMethodsMapping;
  }

  private Map<String, Method> getParserMapping() {
    return parserMapping;
  }

  private String getWidgetId(Element e) {
    return e.getAttributeValue("controlID");
  }
  
  private void addParserMapping(String widgetType, String parsingMethod) {
    try {
      this.getParserMapping().put(widgetType, this.getClass().getDeclaredMethod(parsingMethod, Element.class));
    } catch (Exception e) { }
  }

  private Widget parseElement(Element e) {
    Method m = this.getParserMapping().get(e.getAttribute("controlTypeID").getValue());
    if (m == null) {
      return null;
    }
    try {
      return (Widget) m.invoke(this, e);
    } catch (Exception ex) { 
      return null;
    }
  }

  @SuppressWarnings("unused")
  private Widget parseNumericSpinner(Element e) {
    return this.getFactory().createNumericSpinner(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e), null, null);
  }

  @SuppressWarnings("unused")
  private Widget parseTextArea(Element e) {
    return this.getFactory().createTextArea(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
  }

  @SuppressWarnings("unused")
  private SelectableList parseSelectableList(Element e) {
    return this.getFactory().createSelectableList(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e), false);
  }

  @SuppressWarnings("unused")
  private Widget parseAnnotation(Element e) {
    return this.getFactory().createAnnotation(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e), null,
            this.urlDecode(this.getChildText(e)));
  }

  @SuppressWarnings("unused")
  private Widget parseLink(Element e) {
    return this.getFactory().createLink(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)));
  }

  @SuppressWarnings("unused")
  private Widget parseRadioButton(Element e) {
    return this.getFactory().createRadioButton(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)));
  }

  @SuppressWarnings("unused")
  private Widget parseCheckBox(Element e) {
    return this.getFactory().createCheckBox(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)));
  }
  
  @SuppressWarnings("unused")
  private final Button parseButton(Element e) {
    return this.getFactory().createButton(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)));
  }
  
  @SuppressWarnings("unused")
  private final Panel parsePanel(Element e) {
    return this.getFactory().createPanel(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e), "");
  }
  
  @SuppressWarnings("unused")
  private final Page parsePage(Element e) {
    return this.getFactory().createPage(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)), "");
  }
  
  @SuppressWarnings("unused")
  private final TextBox parseTextBox(Element e) {
    return this.getFactory().createTextBox(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
  }
  
  @SuppressWarnings("unused")
  private final Label parseLabel(Element e) {
    return this.getFactory().createLabel(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e),
            this.urlDecode(this.getChildText(e)));
  }
  
  @SuppressWarnings("unused")
  private final DatePicker parseDatePicker(Element e) {
    return this.getFactory().createDatePicker(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
  }
  
  @SuppressWarnings("unused")
  private final org.webspeclanguage.mockupdd.sui.model.List parseList(Element e) {
    return this.getFactory().createList(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
  }
  
  @SuppressWarnings("unused")
  private final ComboBox parseComboBox(Element e) {
    return this.getFactory().createComboBox(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
  }

  private int getX(Element e) {
    return Integer.parseInt(e.getAttribute("x").getValue());
  }

  private int getY(Element e) {
    return Integer.parseInt(e.getAttribute("y").getValue());
  }

  private int getWidth(Element e) {
    Integer width = Integer.parseInt(e.getAttribute("w").getValue());
    if (width == -1) {
      width = Integer.parseInt(e.getAttribute("measuredW").getValue());
    }
    return width;
  }

  private int getHeight(Element e) {
    Integer height = Integer.parseInt(e.getAttribute("h").getValue());
    if (height == -1) {
      height = Integer.parseInt(e.getAttribute("measuredH").getValue());
    }
    return height;
  }

  public final Image parseImage(Element e) {
    String src = null;
    if (e.getChild(CONTROL_PROPERTIES) != null) {
      src = e.getChild(CONTROL_PROPERTIES).getAttributeValue("src");
    }
    if (src == null) {
      src = "";
    } else {
      src = this.urlDecode(src);
    }
    return this.getFactory().createImage(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e), src);
  }

  @SuppressWarnings("unused")
  private Widget parseTable(Element e) {
    Table t = this.getFactory().createTable(this.getWidgetId(e), this.getX(e), this.getY(e), this.getWidth(e), this.getHeight(e));
    String[] cols = this.urlDecode(this.getChildText(e)).split("\n")[0].split(",");
    for (String col : cols) {
      // create columns, removing "^" and "\r" marks
      t.addColumn(this.getFactory().createTableColumn(col.trim().replaceAll("(\\^|\\\\r)", "")));
    }
    return t;
  }

  private String getChildText(Element e) {
    return e.getChild(CONTROL_PROPERTIES).getChildText("text");
  }

  private String urlDecode(String urlEncodedString) {
    try {
      // Balsamiq bug: "+" character is not properly encoded
      return URLDecoder.decode(urlEncodedString.replaceAll("\\+", "%2B"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public final Collection<WidgetGroup> parseWidgets(String source) throws MockupSourceParsingException {
    DefaultWidgetGroupImpl group = new DefaultWidgetGroupImpl(new ArrayList<Widget>());
    SAXBuilder builder = new SAXBuilder();
    Document d;
    try {
      d = builder.build(new StringReader(source));
    } catch (Exception e) {
      throw new MockupSourceParsingException(source, e);
    }
    for (Object e : d.getRootElement().getChild("controls").getChildren()) {
      Widget c = this.parseElement((Element) e);
      this.addFriendlyIdIfPresent(c, (Element) e);
      if (c != null) {
        group.addWidget(c);
        c = this.customData2Annotation((Element) e, c);
        if (c != null) {
          group.addWidget(c);
        }
      }
    }
    return new ArrayList<WidgetGroup>(Arrays.asList(new WidgetGroup[] { group }));
  }

  private void addFriendlyIdIfPresent(Widget c, Element e) {
    Element idElem = e.getChild(CONTROL_PROPERTIES);
    if (idElem != null) {
      idElem = idElem.getChild("customID");
    }
    if (idElem != null) {
      c.setFriendlyId(idElem.getText());
    }
  }

  private Widget customData2Annotation(Element e, Widget c) {
    Element data = e.getChild(CONTROL_PROPERTIES);
    if (data != null) {
      data = data.getChild("customData");
    }
    if (data != null) {
      if (data.getText().trim().length() > 0) {
        return this.getFactory().createAnnotation(c.getWidgetId() + "Annotation", c.getX(), c.getY(), c.getWidth(), c.getHeight(), c,
                this.urlDecode(this.urlDecode(data.getText())));
      }
    }
    return null;
  }

}
