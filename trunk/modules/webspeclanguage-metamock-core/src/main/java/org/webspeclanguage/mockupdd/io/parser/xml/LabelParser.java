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
