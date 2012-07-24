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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.AttributeDecorator;
import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeTypeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.data.impl.AttributeSpecImpl;

/**
 * @author Franco Giacosa
 */
public class DMTransformationFactoryImpl implements DMTransformationFactory {
	
	
  public AttributeSpec2Attribute transformAttributeSpec2Attribute(AttributeSpec attributeSpec) {
    
    AttributeSpec2Attribute attributeSpec2Attribute = new AttributeSpec2Attribute(attributeSpec, this.transformAttributeTypeSpec2Type(attributeSpec.getType()));
    attributeSpec2Attribute.transform();
    return attributeSpec2Attribute;   
  }
  
  public AssociationSpec2Relationship transformAssociationSpec2Relationship(AssociationSpec associationSpec, ClassSpec associationSource, DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel){
	AssociationSpec2Relationship associationSpec2Relationship = new AssociationSpec2Relationship(associationSpec, dataSpecs2WebMLDataModel);
	associationSpec2Relationship.transform(associationSource);
	return associationSpec2Relationship;	  
  }
  
  public AttributeTypeSpec2Type transformAttributeTypeSpec2Type(AttributeTypeSpec attributeTypeSpec) {
        
    AttributeTypeSpec2Type attributeTypeSpec2Type = new AttributeTypeSpec2Type(attributeTypeSpec);
    attributeTypeSpec2Type.transform();
    return attributeTypeSpec2Type;
  }

  public ClassSpec2Entity transformClassSpec2Entity(ClassSpec classSpec) {	  
    List<AttributeSpec2Attribute> attributeSpec2Attributes = new ArrayList<AttributeSpec2Attribute>();
    for(AttributeSpec atts : classSpec.getAttributes()){
    	attributeSpec2Attributes.add(this.transformAttributeSpec2Attribute(atts));      
    }
    
    
    //we add the Entity OID
    attributeSpec2Attributes.add(this.transformAttributeSpec2Attribute(new AttributeSpecImpl("OID", AttributeTypeSpec.INTEGER)));
    
    ClassSpec2Entity classSpec2Entity = new ClassSpec2Entity(classSpec,attributeSpec2Attributes);
    classSpec2Entity.transform();
    for(AttributeSpec2Attribute as2a : classSpec2Entity.getAttributeSpec2Attributes()){
    	AttributeDecorator attribute = as2a.getAttribute();
    	classSpec2Entity.getEntity().addAttribute(attribute);
    }    
    return classSpec2Entity;    
  }


}
