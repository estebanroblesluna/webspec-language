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

package org.webspeclanguage.mockupdd.specs.data.impl;

import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeTypeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;

/**
 * Represents an individual attribute in the context of a {@link ClassSpec}
 * 
 * @author Jose Matias Rivero
 */
public class AttributeSpecImpl implements AttributeSpec {

  private String name;
  private AttributeTypeSpec type;

  public AttributeSpecImpl(String name, AttributeTypeSpec type) {
    super();
    this.setName(name);
    this.setType(type);
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public AttributeTypeSpec getType() {
    return type;
  }

  private void setType(AttributeTypeSpec type) {
    this.type = type;
  }

}
