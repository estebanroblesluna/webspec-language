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

import java.util.Iterator;

/**
 * @author Franco Giacosa
 */
public class DataModelConcreteVisitor implements DataModelVisitor {
  

  
	public void visit(DataModel dataModel) {
		Iterator<String> iteratorEnt = dataModel.getEntitys().keySet().iterator();
		while(iteratorEnt.hasNext()){
			String key = (String)iteratorEnt.next();
			dataModel.getEntitys().get(key).accept(this);
		}
		Iterator<String> iteratorRel = dataModel.getRelationships().keySet().iterator();
		while(iteratorRel.hasNext()){
			String key = (String)iteratorRel.next();
			dataModel.getRelationships().get(key).accept(this);
		}
	}
	public void visit(Entity entity) {

		Iterator<String> iteratorAt = entity.getAttributes().keySet().iterator();
		while(iteratorAt.hasNext()){
			String key = (String)iteratorAt.next();
			entity.getAttributes().get(key).accept(this);
		}
	}
	public void visit(Attribute attribute) {
	}
	public void visit(Relationship relationship) {

		Iterator<RelationshipRole> iteratorRol = relationship.getRoles().iterator();
		while(iteratorRol.hasNext()){
			RelationshipRole rol = (RelationshipRole)iteratorRol.next();
			rol.accept(this);
		}
	}

	public void visit(RelationshipRole relationshipRole) {
	}
}
