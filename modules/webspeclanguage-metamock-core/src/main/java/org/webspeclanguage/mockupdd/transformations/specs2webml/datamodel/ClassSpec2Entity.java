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
import java.util.HashMap;
import java.util.Iterator;

import org.webspeclanguage.mockupdd.specs.data.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import java.util.*;
/**
 * @author Franco Giacosa
 */
public class ClassSpec2Entity {

    private ClassSpec classSpec;
    
    public ClassSpec2Entity(ClassSpec classSpec){
      super();
      this.setClassSpec(classSpec);
    }
    
    public EntityDecorator getEntity(){
      DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
      DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
      
      Map<String,Attribute> attributes = new HashMap<String,Attribute>();
      Map<String,AttributeDecorator> attributesDec = new HashMap<String,AttributeDecorator>();
      Entity ent1 = dataFactory.createEntity(this.getClassSpec().getName(), "persistent", attributes);
      EntityDecorator entD1 = dataFactory.createEntityDecorator(ent1);
      entD1.setAttributes(attributesDec);
      
      Iterator<AttributeSpec> iteratorA = this.getClassSpec().getAttributes().iterator();
      while(iteratorA.hasNext()){
        AttributeSpec atts = (AttributeSpec)iteratorA.next();
        AttributeSpec2Attribute aS2A = new AttributeSpec2Attribute(atts);
        AttributeDecorator attD1 = aS2A.getAttribute();
        attributesDec.put(attD1.getId(), attD1);
        attributes.put(attD1.getAttribute().getId(), attD1.getAttribute());
      }
            
      //we add the entity key
      AttributeDecorator attD1 = this.buildAttribute("OID",dataFactory.createType("integer"), true);
      attributesDec.put(attD1.getId(), attD1);
      attributes.put(attD1.getAttribute().getId(), attD1.getAttribute());
      
      return entD1;
    }
    public AttributeDecorator buildAttribute(String name, Type type, Boolean key){
      DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
      DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
      
      Attribute att1 = dataFactory.createAttribute(name,type,key);
      return dataFactory.createAttributeDecorator(att1);
    }
    
    public ClassSpec getClassSpec() {
      return classSpec;
    }
    
    public void setClassSpec(ClassSpec classSpec) {
      this.classSpec = classSpec;
    }

}
