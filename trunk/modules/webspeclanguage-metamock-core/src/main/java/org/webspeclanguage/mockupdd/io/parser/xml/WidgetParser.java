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
import org.jdom.Namespace;
import org.webspeclanguage.mockupdd.io.parser.SuiParser;
import org.webspeclanguage.mockupdd.io.parser.SuiParsingException;
import org.webspeclanguage.mockupdd.io.parser.TagToApply;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * @author Jose Matias Rivero
 */
public class WidgetParser implements SuiParser<Element, SuiParserContext> {

  public Object parse(Element source, SuiParserContext parserContext, Object partiallyParsedObject) throws SuiParsingException {
    try {
      Widget w = ((Widget)partiallyParsedObject);
      Namespace ns = parserContext.getNamespace();
      w.setWidgetId(this.getId(source));
      w.setX(this.getX(source, ns));
      w.setY(this.getY(source, ns));
      w.setWidth(this.getWidth(source, ns));
      w.setHeight(this.getHeight(source, ns));
      Element tags = source.getChild("tags", parserContext.getNamespace());
      if (tags != null) {
        for (Object child : tags.getChildren()) {
          ((TagToApply) parserContext.parse((Element) child)).applyOver(w);
        }
      }
      return partiallyParsedObject;
    } catch (TagApplicationException e) {
      throw new SuiParsingException(e.getMessage(), source);
    }
  }
  
  private int getX(Element element, Namespace namespace) {
    return this.getNumericChildAttribute(element, "originalPosition", "x", namespace);
  }

  private int getY(Element element, Namespace namespace) {
    return this.getNumericChildAttribute(element, "originalPosition", "y", namespace);
  }

  private int getWidth(Element element, Namespace namespace) {
    return this.getNumericChildAttribute(element, "originalPosition", "width", namespace);
  }

  private int getHeight(Element element, Namespace namespace) {
    return this.getNumericChildAttribute(element, "originalPosition", "height", namespace);
  }

  private String getId(Element element) {
    return element.getAttributeValue("id");
  }

  private String getChildAttribute(Element element, String childName, String attributeName, Namespace namespace) {
    Element e = element.getChild(childName, namespace);
    if (e == null) {
      return null;
    }
    return e.getAttributeValue(attributeName);
  }

  private int getNumericChildAttribute(Element element, String childName, String attributeName, Namespace namespace) {
    String s = this.getChildAttribute(element, childName, attributeName, namespace);
    if (s == null) {
      return 0;
    }
    return Integer.parseInt(s);
  }


}
