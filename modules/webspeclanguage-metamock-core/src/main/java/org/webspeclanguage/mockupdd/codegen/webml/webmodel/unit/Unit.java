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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;

/**
 * @author Franco Giacosa
 */
public abstract class Unit implements WebModelElement, LinkElement{


	private String name;
	private String id;

	private Map<String,Link> links = new HashMap<String,Link>();
	private EntityDecorator entity;
	
	public Unit(String id, String name, EntityDecorator entity){
		this.id = id;
		this.name = name;
		this.entity = entity;
	}
	public void addLink(Link link){
	  this.getLinks().put(link.getId(), link);
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
	public Map<String, Link> getLinks() {
		return links;
	}
	public void setLinks(Map<String, Link> outputLinks) {
		this.links = outputLinks;
	}
	public EntityDecorator getEntity() {
		return entity;
	}
	public void setEntity(EntityDecorator entity) {
		this.entity = entity;
	}
  public abstract void accept(WebModelVisitor visitor);

}
