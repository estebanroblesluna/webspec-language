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
import java.util.List;

import org.webspeclanguage.mockupdd.specs.data.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import java.util.*;
/**
 * @author Franco Giacosa
 */
public class ClassSpec2Entity {

    private ClassSpec classSpec;
    private EntityDecorator entity;
    private List<AttributeSpec2Attribute> attributeSpec2Attributes = new ArrayList<AttributeSpec2Attribute>();

    public ClassSpec2Entity(ClassSpec classSpec, List<AttributeSpec2Attribute> attributeSpec2Attributes){
      super();
      this.setClassSpec(classSpec);
      this.setAttributeSpec2Attributes(attributeSpec2Attributes);
    }
    
    public void transform(){
      DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
      DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
           
      this.setEntity(dataFactory.createEntityDecorator(dataFactory.createEntity(this.getClassSpec().getName(), "persistent", new HashMap<String,Attribute>())));
      
      Iterator<AttributeSpec2Attribute> iteratorAtt = this.getAttributeSpec2Attributes().iterator();
      while(iteratorAtt.hasNext()){
        AttributeSpec2Attribute attSpec2Att = (AttributeSpec2Attribute)iteratorAtt.next();
        this.getEntity().addAttribute(attSpec2Att.getAttribute());   
      }
            
    }

    public ClassSpec getClassSpec() {
      return classSpec;
    }
    
    public void setClassSpec(ClassSpec classSpec) {
      this.classSpec = classSpec;
    }
    
    
    public List<AttributeSpec2Attribute> getAttributeSpec2Attributes() {
      return attributeSpec2Attributes;
    }
    
    public void setAttributeSpec2Attributes(List<AttributeSpec2Attribute> attributeSpec2Attributes) {
      this.attributeSpec2Attributes = attributeSpec2Attributes;
    }

    public EntityDecorator getEntity() {
      return entity;
    }
 
    public void setEntity(EntityDecorator entity) {
      this.entity = entity;
    }

}
