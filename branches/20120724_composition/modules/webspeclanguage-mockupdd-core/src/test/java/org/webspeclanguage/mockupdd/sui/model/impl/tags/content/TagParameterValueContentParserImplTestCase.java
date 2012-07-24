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
package org.webspeclanguage.mockupdd.sui.model.impl.tags.content;

import org.junit.Test;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContent;

/**
 * @author Jose Matias Rivero
 */
public class TagParameterValueContentParserImplTestCase extends SuiTestCase {

  private TagParameterValueContentParserImpl parser;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.parser = new TagParameterValueContentParserImpl(this.getFactory());
  }

  @Test
  public void testTagParameterValueContentParsing() throws TagContentParsingException {
    TagParameterValueContent content = this.parser.parse("Class");
    assertEquals("Class", ((SimpleTagParameterValueContentImpl)content).getTextualRepresentation());
    
    DataPathTagParameterValueContent dataPathContent = (DataPathTagParameterValueContent)
            this.parser.parse("Class.attribute");
    
    assertNull(dataPathContent.getWidgetId());
    assertEquals(1, dataPathContent.getRootNode().size());
    assertEquals("Class", dataPathContent.getRootNode().getClassName());
    assertEquals("attribute", dataPathContent.getRootNode().getAccessorName());
    
    dataPathContent = (DataPathTagParameterValueContent) this.parser.parse("w1:Class");
    assertEquals("w1", dataPathContent.getWidgetId());
    assertEquals(1, dataPathContent.getRootNode().size());
    assertEquals("Class", dataPathContent.getRootNode().getClassName());
    
    dataPathContent = (DataPathTagParameterValueContent) this.parser.parse("w1:C1.a -> C2");
    assertEquals("w1", dataPathContent.getWidgetId());
    assertEquals(2, dataPathContent.getRootNode().size());
    assertEquals("C1", dataPathContent.getRootNode().getClassName());
    assertEquals("a", dataPathContent.getRootNode().getAccessorName());
    assertEquals("C2", dataPathContent.getRootNode().getNextNode().getClassName());
    assertNull(dataPathContent.getRootNode().getNextNode().getAccessorName());
    
    dataPathContent = (DataPathTagParameterValueContent) this.parser.parse("w1:C1.a -> C2.b");
    assertEquals("w1", dataPathContent.getWidgetId());
    assertEquals(2, dataPathContent.getRootNode().size());
    assertEquals("C1", dataPathContent.getRootNode().getClassName());
    assertEquals("a", dataPathContent.getRootNode().getAccessorName());
    assertEquals("C2", dataPathContent.getRootNode().getNextNode().getClassName());
    assertEquals("b", dataPathContent.getRootNode().getNextNode().getAccessorName());
    
    try {
      dataPathContent = (DataPathTagParameterValueContent) this.parser.parse("w1:C1 -> C2.b");
      fail();
    } catch (TagContentParsingException e) {
    }
  }
  
  
}
