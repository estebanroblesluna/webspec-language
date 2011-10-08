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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;

/**
 * @author Franco Giacosa
 */
public class RelationshipParameter extends Parameter {

	private RelationshipDecorator relationship;

	public RelationshipParameter(String id, RelationshipDecorator relationship) {
		super(id,relationship.getName());
		this.relationship = relationship;
	}
	public RelationshipDecorator getRelationship() {
		return relationship;
	}
	public void setRelationship(RelationshipDecorator relationship) {
		this.relationship = relationship;
	}
	public void accept(WebModelVisitor visitor) {
    visitor.visit(this);
  }
}
