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

import org.webspeclanguage.mockupdd.specs.hypertext.ActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;


/**
 * Represents a object delete action. The object to be deleted will be taken from an already mapped
 * {@link CompositeWidget}, in case of a Repetition, it should have a {@link AttributeClassMappingSpec} Selectable Widget . This mapped is represented by a {@link ClassClassMappingSpec}.
 * @author Franco Giacosa
 */
public class DeleteActionSpecImpl implements ActionSpec, DeleteActionSpec{

  private ClassMappingSpec spec;

  public DeleteActionSpecImpl(ClassMappingSpec spec) {
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
