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


import org.webspeclanguage.mockupdd.specs.data.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

/**
 * @author Franco Giacosa
 */
public class AttributeSpec2Attribute {

    private AttributeSpec attributeSpec;
   
    public AttributeSpec2Attribute(AttributeSpec attributeSpec){
      super();
      this.setAttributeSpec(attributeSpec);
    }
    
    public AttributeDecorator getAttribute(){
      DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
      DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
      dataFactory.createType(attributeSpec.getType().name());
      
      AttributeTypeSpec2Type ats2t = new AttributeTypeSpec2Type(this.getAttributeSpec().getType()); 
      Type type = ats2t.getType();
      Attribute att1 = dataFactory.createAttribute(attributeSpec.getName(),type,false);
      return dataFactory.createAttributeDecorator(att1);
    }

    
    public AttributeSpec getAttributeSpec() {
      return attributeSpec;
    }

    
    public void setAttributeSpec(AttributeSpec attributeSpec) {
      this.attributeSpec = attributeSpec;
    }

}
