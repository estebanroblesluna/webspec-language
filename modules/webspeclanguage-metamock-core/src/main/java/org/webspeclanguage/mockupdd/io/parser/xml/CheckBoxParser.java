package org.webspeclanguage.mockupdd.io.parser.xml;

import org.jdom.Element;
import org.webspeclanguage.mockupdd.io.parser.SuiParser;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;

/**
 * @author Jose Matias Rivero
 */
public class CheckBoxParser implements SuiParser<Element, SuiParserContext> {

  public Object parse(Element source, SuiParserContext parserContext, Object partiallyParsedObject) {
    return parserContext.buildWidget(CheckBox.class, source, source.getAttributeValue("label"));
  }

}
