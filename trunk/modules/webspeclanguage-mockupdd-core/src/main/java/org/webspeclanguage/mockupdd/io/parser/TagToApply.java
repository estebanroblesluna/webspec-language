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
package org.webspeclanguage.mockupdd.io.parser;

import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;

/**
 * @author Jose Matias Rivero
 */
public class TagToApply {

  private Tag tag;
  private List<TagParameterValue> tagParameterValues;

  public TagToApply(Tag tag, List<TagParameterValue> tagParameterValues) {
    super();
    this.setTag(tag);
    this.setTagParameterValues(tagParameterValues);
  }

  private void setTag(Tag tag) {
    this.tag = tag;
  }

  public Tag getTag() {
    return tag;
  }

  private void setTagParameterValues(List<TagParameterValue> tagParameterValues) {
    this.tagParameterValues = tagParameterValues;
  }

  public List<TagParameterValue> getTagParameterValues() {
    return tagParameterValues;
  }
  
  public TagApplication applyOver(Widget widget) throws TagApplicationException {
    return this.getTag().applyOver(widget, this.getTagParameterValues());
  }

}
