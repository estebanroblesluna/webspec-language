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

/**
 * @author Franco Giacosa
 */
public class Relationship implements DataModelElement{

	
	private String id;
	private String name;
	private Entity sourceEntity;
	private Entity targetEntity;
	private List<RelationshipRole> roles;
	
	public Relationship(String id, String name, Entity sourceEntity,
			Entity targetEntity, List<RelationshipRole> roles) {
		super();
		this.id = id;
		this.name = name;
		this.sourceEntity = sourceEntity;
		this.targetEntity = targetEntity;
		this.roles = roles;
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
	public Entity getSourceEntity() {
		return sourceEntity;
	}
	public void setSourceEntity(Entity sourceEntity) {
		this.sourceEntity = sourceEntity;
	}
	public Entity getTargetEntity() {
		return targetEntity;
	}
	public void setTargetEntity(Entity targetEntity) {
		this.targetEntity = targetEntity;
	}
	public List<RelationshipRole> getRoles() {
		return roles;
	}
	public void setRoles(List<RelationshipRole> roles) {
		this.roles = roles;
	}
	public void accept(DataModelVisitor visitor) {
		visitor.visit(this);
	}
}
