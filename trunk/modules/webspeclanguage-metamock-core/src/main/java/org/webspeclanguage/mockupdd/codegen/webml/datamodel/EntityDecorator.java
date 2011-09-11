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
package org.webspeclanguage.mockupdd.codegen.webml.datamodel;

import java.util.*;


import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public class EntityDecorator {

	private Map<String,AttributeDecorator> attributes = new HashMap<String,AttributeDecorator>();
	private Map<String,RelationshipDecorator> relationships = new HashMap<String,RelationshipDecorator>();
	private Entity entity;

	public EntityDecorator(Entity entity) {
		super();
		this.entity = entity;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public String getName() {
		return this.getEntity().getName();
	}
	public String getId() {
		return this.getEntity().getId();
	}
	public String getDuration() {
		return this.getEntity().getDuration();
	}
	public Map<String, AttributeDecorator> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, AttributeDecorator> attributes) {
		this.attributes = attributes;
	}
	public Map<String, RelationshipDecorator> getRelationships() {
		return relationships;
	}
	public void setRelationships(Map<String, RelationshipDecorator> relationships) {
		this.relationships = relationships;
	}
	public AttributeDecorator getEntityKey(){
	  
	  AttributeDecorator keyAttribute = null;
	  Iterator<String> iteratorE = this.getAttributes().keySet().iterator();
    while(iteratorE.hasNext()){
      String key = (String)iteratorE.next();
      AttributeDecorator attribute = this.getAttributes().get(key);
      if(attribute.getKey())
        keyAttribute = attribute;
    }
    
    return keyAttribute;
	}
	public void addAttribute(AttributeDecorator attribute){
	  this.getAttributes().put(attribute.getId(), attribute);
	}
	public HashMap<String,Parameter> getParametersPool(){
		//each attribute and each relationship gets a parameter
	  HashMap<String,Parameter> entityParameters = new HashMap<String,Parameter>();
		
		entityParameters.putAll(this.getAttributesParametersPool());
		entityParameters.putAll(this.getRelationshipsParametersPool());
		
		return entityParameters;
	}
	public HashMap<String,Parameter> getAttributesParametersPool(){
	  HashMap<String,Parameter> attributeParameters = new HashMap<String,Parameter>();
	    Iterator<String> iteratorE = this.getAttributes().keySet().iterator();
  
      while(iteratorE.hasNext()){
          String key = (String)iteratorE.next();
           AttributeParameter attributeParameter = this.getAttributes().get(key).getParameter();
           attributeParameters.put(attributeParameter.getName(),attributeParameter);
            
     }
    return attributeParameters;
  }
	public HashMap<String,Parameter> getRelationshipsParametersPool(){
	  HashMap<String,Parameter> relationshipParameters = new HashMap<String,Parameter>();

	  Iterator<String> iteratorR = this.getRelationships().keySet().iterator();
    while(iteratorR.hasNext()){
      String key = (String)iteratorR.next();
      RelationshipParameter relationshipParameter = this.getRelationships().get(key).getParameter();
      relationshipParameters.put(relationshipParameter.getName(),relationshipParameter);
    }
    return relationshipParameters;
	}
}
