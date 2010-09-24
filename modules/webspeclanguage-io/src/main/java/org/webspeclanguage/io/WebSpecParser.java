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
package org.webspeclanguage.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.lf5.util.StreamUtils;
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.exception.WebspecException;
import org.webspeclanguage.io.base.DiagramParser;
import org.webspeclanguage.io.base.InteractionParser;
import org.webspeclanguage.io.base.NavigationParser;
import org.webspeclanguage.io.base.RichBehaviorParser;
import org.webspeclanguage.io.generators.OneOfStringParser;
import org.webspeclanguage.io.widgets.ButtonParser;
import org.webspeclanguage.io.widgets.LabelParser;
import org.webspeclanguage.io.widgets.ListOfParser;
import org.webspeclanguage.io.widgets.TextFieldParser;

/**
 * A {@link WebSpecDiagram} parser
 * 
 * @author Esteban Robles Luna
 */
public class WebSpecParser {

  private Map<String, ElementParser> parsersMappings;
  
  private Object result;

  public WebSpecParser() {
    this.parsersMappings = new HashMap<String, ElementParser>();
    
    this.parsersMappings.put("diagram", new DiagramParser());
    
    this.parsersMappings.put("interaction", new InteractionParser());
    this.parsersMappings.put("navigation", new NavigationParser());
    this.parsersMappings.put("rich-behavior", new RichBehaviorParser());
    
    this.parsersMappings.put("button", new ButtonParser());
    this.parsersMappings.put("textfield", new TextFieldParser());
    this.parsersMappings.put("list-of", new ListOfParser());
    this.parsersMappings.put("label", new LabelParser());
    
    this.parsersMappings.put("one-of-string-generator", new OneOfStringParser());
  }

  public WebSpecDiagram parse(String resource) {
    InputStream stream = null;
    try {
      stream = Thread.currentThread().getContextClassLoader().getResource(resource).openStream();
      return this.parse(stream);
    } catch (IOException e) {
      throw new WebspecException(e);
    }
  }

  public WebSpecDiagram parse(InputStream inputStream) {
    try {
      InputStream allStream = new ByteArrayInputStream(StreamUtils.getBytes(inputStream));
      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = factory.newSchema(Thread.currentThread().getContextClassLoader().getResource("webspec.xsd"));
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      saxParserFactory.setSchema(schema);

      Validator validator = schema.newValidator();
      validator.validate(new javax.xml.transform.stream.StreamSource(allStream));
      allStream.reset();
      
      SAXParser saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(allStream, new ParseHandler(this));
      return (WebSpecDiagram) this.getResult();
    } catch (Exception e) {
      throw new WebspecException(e);
    }
  }

  public ElementParser getParserFor(String qName) {
    return this.parsersMappings.get(qName);
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }
}
