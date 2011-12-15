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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;

/**
 * Represents a object save action. The object to be saved will be taken from an already mapped
 * {@link CompositeWidget}. This mapped is represented by a {@link ClassMappingSpec}.
 * 
 * @author Jose Matias Rivero
 */
public class SaveActionSpecImpl extends ActionSpecImpl implements SaveActionSpec{

  private ClassMappingSpec spec;

  public SaveActionSpecImpl(ClassMappingSpec spec) {
    super();
    this.setSpec(spec);
  }

  public ClassMappingSpec getSpec() {
    return spec;
  }

  private void setSpec(ClassMappingSpec spec) {
    this.spec = spec;
  }
  
}
