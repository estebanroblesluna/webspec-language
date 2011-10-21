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

package org.webspeclanguage.mockupdd.specs.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents a class specifications (its name, attributes and associations to other classes)
 * 
 * @author Jose Matias Rivero
 */
public class ClassSpec {

  private String name;
  private List<AttributeSpec> attributes;
  private List<AssociationSpec> associations;

  public ClassSpec(String name) {
    super();
    this.setName(name);
    this.setAttributes(new ArrayList<AttributeSpec>());
    this.setAssociations(new ArrayList<AssociationSpec>());
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public List<AttributeSpec> getAttributes() {
    return Collections.unmodifiableList(attributes);
  }

  private void setAttributes(List<AttributeSpec> attributes) {
    this.attributes = attributes;
  }
  
  public void addAttribute(AttributeSpec attribute) {
    this.attributes.add(attribute);
  }

  public List<AssociationSpec> getAssociations() {
    return Collections.unmodifiableList(associations);
  }

  private void setAssociations(List<AssociationSpec> associations) {
    this.associations = associations;
  }
  
  public void addAssociation(AssociationSpec associationSpec) {
    this.associations.add(associationSpec);
  }

}
