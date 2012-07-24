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
public class DataModelIds {

    
 

  private Integer attributeId = 1;
	private Integer entityId = 1;
	private Integer relationshipId = 1;


	 public DataModelIds() {
	    super();
	  }
	 
	public String getAttributeId() {
		return "att" + (attributeId ++).toString();
	}

	public String getEntityId() {
		return "ent" + (entityId ++).toString();
	}

	public String getRelationshipId() {
		return "rel" + (relationshipId ++).toString();
	}

	public String getRelationshipRoleId() {
		return "rel" + (relationshipId ++).toString();
	}
}
