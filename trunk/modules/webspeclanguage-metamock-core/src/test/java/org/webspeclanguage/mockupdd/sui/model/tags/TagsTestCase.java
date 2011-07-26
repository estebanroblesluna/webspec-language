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
package org.webspeclanguage.mockupdd.sui.model.tags;

import java.util.Arrays;

import org.junit.Test;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;

/**
 * @author Jose Matias Rivero
 */
public class TagsTestCase extends SuiTestCase {

  private TagSet tagSet;
  
  protected void setUp() throws Exception {
    super.setUp();
    this.tagSet = this.getFactory().createTagSet("TagSet1", 
            this.getFactory().createTag(
                    "Tag1", 
                    Arrays.asList(this.getFactory().createTagParameter("Param1")), 
                    Button.class));
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }
  
  @Test
  public void testTagApplication() throws TagApplicationException {
    Button b = this.getFactory().createButton("b", 0, 0, 0, 0, "button");
    Tag t = this.tagSet.getTagByName("Tag1");
    assertNotNull(t);
    TagApplication at = t.applyOver(b, Arrays.asList(this.getFactory().createTagParameterValue(t.getParameters().iterator().next(), "value1")));
    assertEquals(1, b.getAppliedTags().size());
    assertEquals(b.getAppliedTags().iterator().next(), at);
    assertEquals(at.getTag(), t);
    assertEquals(t.getParameters().iterator().next(), at.getParameterValues().get(0).getTagParameter());
    assertEquals("value1", at.getParameterValues().get(0).getValue());
  }
  
  @Test
  public void testTagApplicationErrors() {
    Button b = this.getFactory().createButton("b", 0, 0, 0, 0, "button");
    Link l = this.getFactory().createLink("l", 0, 0, 0, 0, "link");
    Tag t = this.tagSet.getTagByName("Tag1");
    assertNotNull(t);
    // wrong parameters
    try {
      t.applyOver(b, Arrays.asList(new TagParameterValue[]{}));
      fail();
    } catch (TagApplicationException e) { }
    // wrong widget type
    try {
      t.applyOver(l, Arrays.asList(this.getFactory().createTagParameterValue(t.getParameters().iterator().next(), "value1")));
      fail();
    } catch (TagApplicationException e) { }
    // same tag applied twice
    try {
      t.applyOver(b, Arrays.asList(this.getFactory().createTagParameterValue(t.getParameters().iterator().next(), "value1")));
      t.applyOver(b, Arrays.asList(this.getFactory().createTagParameterValue(t.getParameters().iterator().next(), "value1")));
      fail();
    } catch (TagApplicationException e) { }
  }
  

}
