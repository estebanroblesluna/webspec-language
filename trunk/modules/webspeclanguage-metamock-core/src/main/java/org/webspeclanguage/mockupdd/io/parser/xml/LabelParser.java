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

import org.jdom.Element;
import org.webspeclanguage.mockupdd.io.parser.SuiParser;
import org.webspeclanguage.mockupdd.sui.model.Label;

/**
 * @author Jose Matias Rivero
 */
public class LabelParser implements SuiParser<Element, SuiParserContext> {

  public Object parse(Element source, SuiParserContext parserContext, Object partiallyParsedObject) {
    return parserContext.buildWidget(Label.class, source, source.getAttributeValue("label"));
  }

}
