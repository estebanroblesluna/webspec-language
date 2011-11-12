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
    private AttributeDecorator attribute;
    private AttributeTypeSpec2Type attributeTypeSpec2Type;

    public AttributeSpec2Attribute(AttributeSpec attributeSpec, AttributeTypeSpec2Type attributeTypeSpec2Type ){
      super();
      this.setAttributeSpec(attributeSpec);
      this.setAttributeTypeSpec2Type(attributeTypeSpec2Type);
    }
    
    public AttributeTypeSpec2Type getAttributeTypeSpec2Type() {
      return attributeTypeSpec2Type;
    }
    
    public void setAttributeTypeSpec2Type(AttributeTypeSpec2Type attributeTypeSpec2Type) {
      this.attributeTypeSpec2Type = attributeTypeSpec2Type;
    }

    public AttributeDecorator getAttribute() {
      return attribute;
    }

    public void setAttribute(AttributeDecorator attribute) {
      this.attribute = attribute;
      
    }
    
    public void transform(){
      DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
      DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
      
      AttributeTypeSpec2Type ats2t = new AttributeTypeSpec2Type(this.getAttributeSpec().getType()); 
      Type type = ats2t.getType();
      Attribute att1 = dataFactory.createAttribute(this.getAttributeSpec().getName(),type,false);
      this.setAttribute(dataFactory.createAttributeDecorator(att1));
    }
    
    public AttributeSpec getAttributeSpec() {
      return attributeSpec;
    }
    
    public void setAttributeSpec(AttributeSpec attributeSpec) {
      this.attributeSpec = attributeSpec;
    }

}
