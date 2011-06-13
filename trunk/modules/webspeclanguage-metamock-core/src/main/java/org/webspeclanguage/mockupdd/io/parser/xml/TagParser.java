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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.io.parser.SuiParser;
import org.webspeclanguage.mockupdd.io.parser.SuiParsingException;
import org.webspeclanguage.mockupdd.io.parser.TagToApply;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;

/**
 * @author Jose Matias Rivero
 */
public class TagParser implements SuiParser<Element, SuiParserContext> {

  public Object parse(Element source, SuiParserContext parserContext, Object partiallyParsedObject) throws SuiParsingException {
    TagSet tagSet = SuiDefaultConfig.getInstance().getTagSetByName(source.getAttributeValue("tagSet"));
    Tag tag = tagSet.getTagByName(source.getAttributeValue("name"));
    List<TagParameterValue> params = this.buildParams(source, tag, this.parseParams(source), parserContext);
    return new TagToApply(tag, params);
  }

  private List<TagParameterValue> buildParams(Element source, Tag tag, List<String> parsedParams, SuiParserContext parserContext) throws SuiParsingException {
    if (tag.getParameters().size() != parsedParams.size()) {
      throw new SuiParsingException("Actual and formal parameters don't match for tag " + tag.getName(), source);
    }
    List<TagParameterValue> tagParameterValues = new ArrayList<TagParameterValue>();
    for (int i = 0; i < parsedParams.size(); i++) {
      tagParameterValues.add(parserContext.getSuiFactory().createTagParameterValue(tag.getParameters().get(i), parsedParams.get(i)));
    }
    return tagParameterValues;
  }

  private List<String> parseParams(Element source) {
    List<String> params = new ArrayList<String>();
    Iterator children = source.getChildren().iterator();
    while (children.hasNext()) {
      params.add(((Element) children.next()).getAttributeValue("value")); 
    }
    return params;
  }

}
