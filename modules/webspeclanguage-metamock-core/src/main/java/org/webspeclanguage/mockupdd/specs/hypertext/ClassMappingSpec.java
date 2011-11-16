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

package org.webspeclanguage.mockupdd.specs.hypertext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;

/**
 * Represents a mapping between a {@link CompositeWidget} and a {@link ClassSpec}. 
 * The semantics of this spec is that the {@link CompositeWidget} reads or manages in some way
 * instances of the class represented by the {@link ClassSpec}.
 * 
 * @author Jose Matias Rivero
 */
public class ClassMappingSpec extends ActionSpec {

  private ClassSpec classSpec;
  private List<AttributeMappingSpec> attributeMappings;

  public ClassMappingSpec(ClassSpec classSpec) {
    super();
    this.setClassSpec(classSpec);
    this.setAttributeMappings(new ArrayList<AttributeMappingSpec>());
  }

  public ClassSpec getClassSpec() {
    return classSpec;
  }

  private void setClassSpec(ClassSpec classSpec) {
    this.classSpec = classSpec;
  }

  public List<AttributeMappingSpec> getAttributeMappings() {
    return Collections.unmodifiableList(attributeMappings);
  }

  private void setAttributeMappings(List<AttributeMappingSpec> attributeMappings) {
    this.attributeMappings = attributeMappings;
  }
  
  public void addAttributeMapping(AttributeMappingSpec attributeMappingSpec) {
    this.attributeMappings.add(attributeMappingSpec);
  }
  
}
