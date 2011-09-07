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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.AttributeParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;

/**
 * @author Franco Giacosa
 */
public class AttributeFacade {

	public AttributeFacade(Attribute attribute) {
		super();
		this.attribute = attribute;
	}
	public Attribute attribute;
	
	public Type getAttributeType() {
		return this.getAttribute().getAttributeType();
	}
	public Boolean getKey() {
		return this.getAttribute().getKey();
	}
	public String getId() {
		return this.getAttribute().getId();
	}
	public String getName() {
		return this.getAttribute().getName();
	}
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	public List<Parameter> getParameters(){
		List<Parameter> listParameter = new ArrayList<Parameter>();
		listParameter.add(new AttributeParameter(this.getName(),this));
		return listParameter;
	}
	
}
