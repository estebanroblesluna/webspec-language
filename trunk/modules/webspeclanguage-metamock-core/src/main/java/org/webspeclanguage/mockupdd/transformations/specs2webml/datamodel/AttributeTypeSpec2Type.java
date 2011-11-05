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

package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.specs.data.*;

/**
 * @author Franco Giacosa
 */
public class AttributeTypeSpec2Type {
 
  private AttributeTypeSpec attributeTypeSpec;
  
  public AttributeTypeSpec2Type(AttributeTypeSpec attributeTypeSpec){
    this.setAttributeTypeSpec(attributeTypeSpec);
  }

  public Type getType(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    return dataFactory.createType(this.getAttributeTypeSpec().name());
  }
  public AttributeTypeSpec getAttributeTypeSpec() {
    return attributeTypeSpec;
  }

  
  public void setAttributeTypeSpec(AttributeTypeSpec attributeTypeSpec) {
    this.attributeTypeSpec = attributeTypeSpec;
  }
}
