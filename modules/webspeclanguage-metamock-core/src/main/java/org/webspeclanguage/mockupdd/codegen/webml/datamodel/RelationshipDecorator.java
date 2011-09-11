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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.RelationshipParameter;

/**
 * @author Franco Giacosa
 */
public class RelationshipDecorator {
	
	public RelationshipDecorator(Relationship relationship) {
		super();
		this.relationship = relationship;
	}
	public Relationship relationship;

	public Relationship getRelationship() {
		return relationship;
	}
	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}
	public String getId() {
		return this.getRelationship().getId();
	}
	public String getName() {
		return this.getRelationship().getName();
	}
	public String getSourceEntity() {
		return this.getRelationship().getSourceEntity();
	}
	public String getTargetEntity() {
		return this.getRelationship().getTargetEntity();
	}
	public RelationshipParameter getParameter(){
	  WebModelFactory webFactory = new WebModelFactory();
    return webFactory.createRelationshipParameter(this);
	}
}
