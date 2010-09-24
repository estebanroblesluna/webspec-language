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

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX handler for processing the XML tree structure
 * 
 * @author Esteban Robles Luna
 */
public class ParseHandler extends DefaultHandler {

  private WebSpecParser webSpecParser;

  private Stack<ElementParser> parsersStack;
  
  private ParseContext context;

  public ParseHandler(WebSpecParser webSpecParser) {
    this.webSpecParser = webSpecParser;
    this.parsersStack = new Stack<ElementParser>();
    this.context = new ParseContext();
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    ElementParser parser = this.parsersStack.pop();
    
    if (!this.parsersStack.isEmpty()) {
      ElementParser parentParser = this.parsersStack.peek();
      Object result = parser.getResult();
      parentParser.handleChild(result);
    } else {
      webSpecParser.setResult(parser.getResult());
    }
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    ElementParser parser = this.webSpecParser.getParserFor(qName);
    this.parsersStack.push(parser);
    parser.parse(attributes, this.context);
  }
}
