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
public class DataModel implements DataModelElement{

	

	private Map<String,Entity> entitys = new HashMap<String,Entity>();
	private Map<String,Relationship> relationships = new HashMap<String,Relationship>();

	public DataModel(Map<String, Entity> entitys,
			Map<String, Relationship> relationships) {
		super();
		this.entitys = entitys;
		this.relationships = relationships;
	}
	
	public Map<String, Entity> getEntitys() {
		return entitys;
	}

	public void setEntitys(Map<String, Entity> entitys) {
		this.entitys = entitys;
	}

	public Map<String, Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(Map<String, Relationship> relationships) {
		this.relationships = relationships;
	}
	public void accept(DataModelVisitor visitor) {
		visitor.visit(this);
	}
}
