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
package org.webspeclanguage.mockupdd.io.parser.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.jdom.Element;
import org.jdom.Namespace;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.io.parser.SuiParser;
import org.webspeclanguage.mockupdd.io.parser.SuiParsingException;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * @author Jose Matias Rivero
 */
public class SuiParserContext {

  private Map<String, SuiParser<Element, SuiParserContext>> parsersByTagName;
  private Map<SuiParser<Element, SuiParserContext>, SuiParser<Element, SuiParserContext>> parentParsers;
  private Map<Class< ? >, Method> constructorByClass;
  private Namespace namespace;
  
  public SuiParserContext() {
    super();
    this.initializeParsers();
    this.initializeConstructorByClass();
    this.namespace = Namespace.getNamespace("http://www.webspeclanguage.org/sui");
  }

  private void initializeConstructorByClass() {
    this.constructorByClass = new HashMap<Class< ? >, Method>();
    for (Method m : SuiFactory.class.getMethods()) {
      this.constructorByClass.put(m.getReturnType(), m);
    }
  }

  private void initializeParsers() {
    this.parsersByTagName = new HashMap<String, SuiParser<Element, SuiParserContext>>();
    this.parentParsers = new HashMap<SuiParser<Element, SuiParserContext>, SuiParser<Element, SuiParserContext>>();
    CompositeWidgetParser cwp = new CompositeWidgetParser();
    InputWidgetParser iwp = new InputWidgetParser();
    WidgetParser wp = new WidgetParser();
    this.addParentParser(cwp, wp);
    this.addParentParser(iwp, wp);
    this.addParser("ui", new PageParser(), cwp);
    this.addParser("panel", new PanelParser(), cwp);
    this.addParser("repetition", new RepetitonParser(), cwp);
    this.addParser("label", new LabelParser(), wp);
    this.addParser("textBox", new TextBoxParser(), iwp);
    this.addParser("checkBox", new CheckBoxParser(), wp);
    this.addParser("comboBox", new ComboBoxParser(), iwp);
    this.addParser("button", new ButtonParser(), wp);
    this.addParser("link", new LinkParser(), wp);
    this.addParser("list", new ListParser(), iwp);
    this.addParser("image", new ImageParser(), wp);
    this.addParser("radioButton", new RadioButtonParser(), wp);
    this.addParser("tag", new TagParser(), null);    
  }

  private void addParser(String tagName, SuiParser<Element, SuiParserContext> parser, SuiParser<Element, SuiParserContext> parentParser) {
    this.parsersByTagName.put(tagName, parser);
    addParentParser(parser, parentParser);
  }

  private SuiParser<Element, SuiParserContext> addParentParser(SuiParser<Element, SuiParserContext> parser, SuiParser<Element, SuiParserContext> parentParser) {
    this.parentParsers.put(parser, parentParser);
    return parser;
  }

  public SuiFactory getSuiFactory() {
    return SuiDefaultConfig.getInstance().getFactory();
  }

  public SuiParser<Element, SuiParserContext> getParserFor(Element element) {
    return this.parsersByTagName.get(element.getName());
  }

  public Widget buildWidget(Class< ? extends Widget> widgetClass, Element element, Object... extraParams) {
    try {
      return (Widget) this.constructorByClass.get(widgetClass).invoke(
              this.getSuiFactory(),
              ArrayUtils.addAll(new Object[] { "id", 0, 0, 0, 0 },
                      extraParams));
    } catch (IllegalArgumentException e) {
      return null;
    } catch (IllegalAccessException e) {
      return null;
    } catch (InvocationTargetException e) {
      return null;
    }
  }
  
  public Namespace getNamespace() {
    return this.namespace;
  }
  
  public Object parse(Element source) throws SuiParsingException {
    Object parsedObject = null;
    SuiParser<Element, SuiParserContext> currentParser;
    currentParser = this.getParserFor(source);
    while (currentParser != null) {
      parsedObject = currentParser.parse(source, this, parsedObject);
      currentParser = this.getParentParserFor(currentParser);
    }
    return parsedObject;
  }

  private SuiParser<Element, SuiParserContext> getParentParserFor(SuiParser<Element, SuiParserContext> parser) {
    return this.parentParsers.get(parser);
  }

}
