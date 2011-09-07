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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;

import java.util.*;

/**
 * @author Franco Giacosa
 */
public class MultiEntryUnit extends ContentUnit {

	public MultiEntryUnit(String id, String name, EntityFacade entity) {
		super(id, name, entity);
		// TODO Auto-generated constructor stub
	}

	private Map<String,Field> fields = new HashMap<String,Field>();

	public Map<String, Field> getFields() {
		return fields;
	}

	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	
	public Map<String,Parameter> getParametersPool(){
		//here we obtain every parameter that the unit can use
		HashMap<String,Parameter> fields = new HashMap<String,Parameter>();
		Iterator<String> iterator = this.getFields().keySet().iterator();
		
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			List<Parameter> parameterList = this.getFields().get(key).getParameters();
			
			Iterator<Parameter> parameterIterator = parameterList.iterator();
			while(parameterIterator.hasNext()){
				Parameter parameter = (Parameter)parameterIterator.next();
				fields.put(parameter.getName(), parameter);
			}
		}
		return fields;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
}
