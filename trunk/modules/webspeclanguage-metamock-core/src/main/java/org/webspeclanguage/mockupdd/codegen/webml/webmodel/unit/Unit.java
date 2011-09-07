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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;

/**
 * @author Franco Giacosa
 */
public abstract class Unit implements WebModelElement{


	private String name;
	private String id;
	private ArrayList<Parameter> inputParameters = new ArrayList<Parameter>();
	private ArrayList<Parameter> outputParameters= new ArrayList<Parameter>();
	private Map<String,ContextualLink> outputLinks = new HashMap<String,ContextualLink>();
	private EntityFacade entity;
	
	public Unit(String id, String name, EntityFacade entity){
		this.id = id;
		this.name = name;
		this.entity = entity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Parameter> getInputParameters() {
		return inputParameters;
	}
	public void setInputParameters(ArrayList<Parameter> inputParameters) {
		this.inputParameters = inputParameters;
	}
	public ArrayList<Parameter> getOutputParameters() {
		return outputParameters;
	}
	public void setOutputParameters(ArrayList<Parameter> outputParameters) {
		this.outputParameters = outputParameters;
	}
	public Map<String, ContextualLink> getOutputLinks() {
		return outputLinks;
	}
	public void setOutputLinks(Map<String, ContextualLink> outputLinks) {
		this.outputLinks = outputLinks;
	}
	public EntityFacade getEntity() {
		return entity;
	}
	public void setEntity(EntityFacade entity) {
		this.entity = entity;
	}
	public Map<String,Parameter> getParametersPool(){
		return this.getEntity().getParametersPool();
	}

}
