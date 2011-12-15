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

/**
 * @author Franco Giacosa
 */
public class Attribute implements DataModelElement{
	
	private String name;
	private String id;
	private Type attributeType;
	private Boolean key = false;
	
	public Attribute(String id, String name, Type attributeType, Boolean key) {
		super();
		this.id = id;
		this.attributeType = attributeType;
		this.name = name;
		this.key = key;
	}
		
	public Type getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(Type attributeType) {
		this.attributeType = attributeType;
	}
	public Boolean getKey() {
		return key;
	}
	public void setKey(Boolean key) {
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void accept(DataModelVisitor visitor) {
		visitor.visit(this);
	}
}
