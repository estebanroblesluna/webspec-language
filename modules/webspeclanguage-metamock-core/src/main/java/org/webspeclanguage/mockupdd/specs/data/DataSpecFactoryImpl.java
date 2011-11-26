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


/**
 * @author Franco Giacosa
 */
public class DataSpecFactoryImpl implements DataSpecFactory {

  public AssociationSpec createAssociationSpec(ClassSpec destinationClass, String associationName, MaximumCardinality maximumCardinality) {
    AssociationSpec associationSpec = new AssociationSpec(destinationClass, associationName, maximumCardinality);
    DataSpecFactory.associationSpecs.add(associationSpec);
    return associationSpec;
  }

  public AttributeSpec createAttributeSpec(String name, AttributeTypeSpec type) {
    AttributeSpec attributeSpec = new AttributeSpec(name, type);
    DataSpecFactory.attributeSpecs.add(attributeSpec);
    return attributeSpec;  
  }

  public ClassSpec createClassSpec(String name) {
    ClassSpec classSpec = new ClassSpec(name);
    DataSpecFactory.classSpecs.add(classSpec);
    return classSpec;  
  }

}
