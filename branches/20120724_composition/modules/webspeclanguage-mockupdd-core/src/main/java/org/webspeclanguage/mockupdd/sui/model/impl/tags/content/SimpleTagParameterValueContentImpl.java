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

import org.webspeclanguage.mockupdd.sui.model.tags.content.SimpleTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;

/**
 * @author Jose Matias Rivero
 */
public class SimpleTagParameterValueContentImpl implements SimpleTagParameterValueContent {

  private String content;

  public SimpleTagParameterValueContentImpl(String content) {
    super();
    this.setContent(content);
  }

  @Override
  public String getTextualRepresentation() {
    return this.getContent();
  }

  private String getContent() {
    return content;
  }

  private void setContent(String content) {
    this.content = content;
  }
  
  @Override
  public <T> T accept(TagParameterValueContentVisitor<T> visitor) {
    return visitor.visitSimpleTagParameterValueContent(this);
  }

}
