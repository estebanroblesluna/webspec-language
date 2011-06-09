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

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;

/**
 * @author Jose Matias Rivero
 */
public class TagSetImpl implements TagSet {

  private String name;
  private Collection<Tag> tags;

  public TagSetImpl(String name, Collection<Tag> tags) {
    super();
    this.setName(name);
    this.setTags(tags);
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private void setTags(Collection<Tag> tags) {
    this.tags = tags;
  }

  public Collection<Tag> getTags() {
    return tags;
  }

}
