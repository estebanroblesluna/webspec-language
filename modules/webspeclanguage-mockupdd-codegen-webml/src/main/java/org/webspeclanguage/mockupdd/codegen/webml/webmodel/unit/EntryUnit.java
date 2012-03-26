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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

import java.util.*;

/**
 * @author Franco Giacosa
 */
public class EntryUnit extends ContentUnit {

	private Map<String,Field> fields = new HashMap<String,Field>();
	
	public EntryUnit(String id, String name, EntityDecorator entity) {
		super(id, name, entity);
	}
	public EntryUnit(String id, String name){
	  super(id,name,null);
	}
	public void addField(Field field){
	  this.getFields().put(field.getId(), field);
	}
	public Map<String, Field> getFields() {
		return fields;
	}
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
	public void createFields(){
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
		WebModelFactory webFactory = webModelFacade.getWebModelFactory();
		if(this.getEntity() != null){
			Iterator<String> iteratorAttributes = this.getEntity().getAttributes().keySet().iterator();
			while(iteratorAttributes.hasNext()){
				String keyAttribute = (String)iteratorAttributes.next();
				AttributeDecorator attribute = this.getEntity().getAttributes().get(keyAttribute);
				this.addField(webFactory.createNormalField(attribute));
			}
		}
	}
	public Map<String,Parameter> getInputParameters() {
		Map<String,Parameter> inputParameters = new HashMap<String,Parameter>();

		Iterator<String> iteratorFields = this.getFields().keySet().iterator();

		while(iteratorFields.hasNext()){
			String key = (String)iteratorFields.next();
			List<Parameter> parameterList = this.getFields().get(key).getInputParameters();

			Iterator<Parameter> parameterIterator = parameterList.iterator();
			while(parameterIterator.hasNext()){
				Parameter parameter = parameterIterator.next();
				inputParameters.put(parameter.getName(),parameter);
			}
		}
		return inputParameters;  
	}
	public Map<String,Parameter> getOutputParameters() {
		Map<String,Parameter> outputParameters = new HashMap<String,Parameter>();

		Iterator<String> iteratorFields = this.getFields().keySet().iterator();

		while(iteratorFields.hasNext()){
			String key = (String)iteratorFields.next();
			List<Parameter> parameterList = this.getFields().get(key).getOutputParameters();

			Iterator<Parameter> parameterIterator = parameterList.iterator();
			while(parameterIterator.hasNext()){
				Parameter parameter = (Parameter)parameterIterator.next();     
				outputParameters.put(parameter.getName(),parameter);
			}
		}
		return outputParameters;  
	}
}
