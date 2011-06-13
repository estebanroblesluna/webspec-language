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
package org.webspeclanguage.mockupdd.io;

import java.io.StringReader;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.webspeclanguage.mockupdd.codegen.xml.MockupXmlGenerator;
import org.webspeclanguage.mockupdd.io.parser.xml.SuiParserContext;
import org.webspeclanguage.mockupdd.io.parser.xml.XmlSuiParser;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * @author Jose Matias Rivero
 */
public class SuiXmlSerializer implements SuiSerializer {

  public XMLOutputter outputter;
  public XmlSuiParser parser;
  
  public SuiXmlSerializer() {
    super();
    this.outputter = new XMLOutputter(Format.getPrettyFormat());
    this.parser = new XmlSuiParser();
  }

  public String serialize(SuiModel model) {
    return this.outputter.outputString(new MockupXmlGenerator().generateInSingleDocument(model));
  }

  public SuiModel deserialize(String serializedModel) throws Exception {
    SAXBuilder builder = new SAXBuilder();
    Document d = builder.build(new StringReader(serializedModel));
    return (SuiModel) this.parser.parse(d.getRootElement(), new SuiParserContext(), null);
  }

}
