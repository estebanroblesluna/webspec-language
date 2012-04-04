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

import java.util.Map;
import java.util.HashMap;

/**
 * @author Franco Giacosa
 */
public class Entity implements DataModelElement{

	private String id;
	private String name;
	private String duration;
	private Map<String,Attribute> attributes = new HashMap<String,Attribute>();
	private Map<String,Relationship> relationships = new HashMap<String,Relationship>();

	public Entity(String id, String name, String duration, Map<String, Attribute> attributes) {
		super();
		this.duration = duration;
		this.id = id;
		this.name = name;
		this.attributes = attributes;
	}
	public void addAttribute(Attribute at){
		this.getAttributes().put(at.getId(), at);
	}
	public void addRelationship(Relationship rel){
		this.getRelationships().put(rel.getId(), rel);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Attribute> attributes) {
		this.attributes = attributes;
	}
	public Map<String, Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(Map<String, Relationship> relationships) {
		this.relationships = relationships;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void accept(DataModelVisitor visitor) {
		visitor.visit(this);
	}
}
