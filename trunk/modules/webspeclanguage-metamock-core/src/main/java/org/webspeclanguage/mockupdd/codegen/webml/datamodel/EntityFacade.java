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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public class EntityFacade {

	private Map<String,AttributeFacade> attributes = new HashMap<String,AttributeFacade>();
	private Map<String,RelationshipFacade> relationships = new HashMap<String,RelationshipFacade>();
	private Entity entity;

	public EntityFacade(Entity entity) {
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
	public Map<String, AttributeFacade> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, AttributeFacade> attributes) {
		this.attributes = attributes;
	}
	public Map<String, RelationshipFacade> getRelationships() {
		return relationships;
	}
	public void setRelationships(Map<String, RelationshipFacade> relationships) {
		this.relationships = relationships;
	}

	public Map<String,Parameter> getParametersPool(){
		//each attribute and each relationship gets a parameter
		HashMap<String,Parameter> entityParameters = new HashMap<String,Parameter>();
		
		
		Iterator<String> iteratorE = this.getAttributes().keySet().iterator();
		
		while(iteratorE.hasNext()){
			String key = (String)iteratorE.next();
			List<Parameter> parameterList = this.getAttributes().get(key).getParameters();
			
			Iterator<Parameter> parameterIterator = parameterList.iterator();
			while(parameterIterator.hasNext()){
				Parameter parameter = (Parameter)parameterIterator.next();
				entityParameters.put(parameter.getName(), parameter);
			}
		}
		Iterator<String> iteratorR = this.getRelationships().keySet().iterator();
		
		while(iteratorR.hasNext()){
			String key = (String)iteratorR.next();
			List<Parameter> parameterList = this.getRelationships().get(key).getParameters();
			
			Iterator<Parameter> parameterIterator = parameterList.iterator();
			while(parameterIterator.hasNext()){
				Parameter parameter = (Parameter)parameterIterator.next();
				entityParameters.put(parameter.getName(), parameter);
			}
		}
		return entityParameters;
	}
}
