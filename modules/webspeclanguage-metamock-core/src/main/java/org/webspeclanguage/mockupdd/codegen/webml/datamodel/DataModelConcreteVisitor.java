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
		System.out.println("<DataModel>");
		
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
		System.out.println("</DataModel>");

	}

	public void visit(Entity entity) {
		System.out.print("<Entity id=\"");
		System.out.print(entity.getId());
		System.out.print("\" name=\"");
		System.out.print(entity.getName());
		System.out.print("\" duration=\"");
		System.out.print(entity.getDuration());
		System.out.print("\"attributeOrder=\"");
		System.out.print(">");
		System.out.println("");

		Iterator<String> iteratorAt = entity.getAttributes().keySet().iterator();
		while(iteratorAt.hasNext()){
			String key = (String)iteratorAt.next();
			entity.getAttributes().get(key).accept(this);
		}
		System.out.println("</Entity>");
	}

	public void visit(Attribute attribute) {
		System.out.print("<Attribute name=\"");
		System.out.print(attribute.getName());
		System.out.print("\" id=\"");
		System.out.print(attribute.getId());
		System.out.print("\" type=\"");
		System.out.print(attribute.getAttributeType().getTypeName());
		System.out.print("\" key=\"");
		System.out.print(attribute.getKey().toString());
		System.out.print("/>");
		System.out.println("");
	}

	public void visit(Relationship relationship) {
		System.out.print("<Relationship id=\"");
		System.out.print(relationship.getId());
		System.out.print("\" name=\"");
		System.out.print(relationship.getName());
		System.out.print("\" sourceEntity=\"");
		System.out.print(relationship.getSourceEntity().toString());
		System.out.print("\" targetEntity=\"");
		System.out.print(relationship.getTargetEntity().toString());
		System.out.print(">");
		System.out.println("");

		Iterator<RelationshipRole> iteratorRol = relationship.getRoles().iterator();
		while(iteratorRol.hasNext()){
			RelationshipRole rol = (RelationshipRole)iteratorRol.next();
			rol.accept(this);
		}
		System.out.println("</Relationship>");
	}

	public void visit(RelationshipRole relationshipRole) {
		System.out.print("<RelationshipRole id=\"");
		System.out.print(relationshipRole.getId());
		System.out.print("\" name=\"");
		System.out.print(relationshipRole.getName());
		System.out.print("\" maxCard=\"");
		System.out.print(relationshipRole.getMaxCard());
		System.out.print("/>");
		System.out.println("");
		System.out.println("</RelationshipRole>");
	}
}
