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
package org.webspeclanguage.mockupdd.sui.model.impl.tags;

import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContent;

/**
 * @author Jose Matias Rivero
 */
public class TagParameterValueImpl implements TagParameterValue {

  private TagParameter tagParameter;
  private TagParameterValueContent value;

  public TagParameterValueImpl(TagParameter tagParameter, TagParameterValueContent value) {
    super();
    this.setTagParameter(tagParameter);
    this.setValue(value);
  }

  private void setTagParameter(TagParameter tagParameter) {
    this.tagParameter = tagParameter;
  }

  public TagParameter getTagParameter() {
    return tagParameter;
  }

  private void setValue(TagParameterValueContent value) {
    this.value = value;
  }

  public TagParameterValueContent getValue() {
    return value;
  }

}
