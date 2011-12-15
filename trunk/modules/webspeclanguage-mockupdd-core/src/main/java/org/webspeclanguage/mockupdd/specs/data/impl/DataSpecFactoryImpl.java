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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeTypeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.data.DataSpecFactory;
import org.webspeclanguage.mockupdd.specs.data.MaximumCardinality;



/**
 * @author Franco Giacosa
 */
public class DataSpecFactoryImpl implements DataSpecFactory {


  private List<AssociationSpec> associationSpecs = new ArrayList<AssociationSpec>();
  private List<AttributeSpec> attributeSpecs = new ArrayList<AttributeSpec>();
  private List<ClassSpec> classSpecs = new ArrayList<ClassSpec>();
  
  public AssociationSpec createAssociationSpec(ClassSpec destinationClass, String associationName, MaximumCardinality maximumCardinality) {
    AssociationSpec associationSpec = new AssociationSpecImpl(destinationClass, associationName, maximumCardinality);
    this.getAssociationSpecs().add(associationSpec);
    return associationSpec;
  }

  public AttributeSpec createAttributeSpec(String name, AttributeTypeSpec type) {
    AttributeSpec attributeSpec = new AttributeSpecImpl(name, type);
    this.getAttributeSpecs().add(attributeSpec);
    return attributeSpec;  
  }

  public ClassSpec createClassSpec(String name) {
    ClassSpec classSpec = new ClassSpecImpl(name);
    this.getClassSpecs().add(classSpec);
    return classSpec;  
  }
  
  public List<AssociationSpec> getAssociationSpecs() {
    return associationSpecs;
  }
  
  public void setAssociationSpecs(List<AssociationSpec> associationSpecs) {
    this.associationSpecs = associationSpecs;
  }
  
  public List<AttributeSpec> getAttributeSpecs() {
    return attributeSpecs;
  }
  
  public void setAttributeSpecs(List<AttributeSpec> attributeSpecs) {
    this.attributeSpecs = attributeSpecs;
  }
  
  public List<ClassSpec> getClassSpecs() {
    return classSpecs;
  }
  
  public void setClassSpecs(List<ClassSpec> classSpecs) {
    this.classSpecs = classSpecs;
  }

}
