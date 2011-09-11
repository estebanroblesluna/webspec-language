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
import java.util.Iterator;
import java.util.Map;
	
/**
 * @author Franco Giacosa
 */
public class DataModelFactory {

	private DataModelSingleton dataModelSingleton = DataModelSingleton.getDataModelSingleton();
	
	public Attribute createAttribute(String name, Type type, Boolean key){
		return new Attribute(this.getDataModelSingleton().getAttributeId(),name,type,key);
	}
	public DataModel createDataModel(Map<String, Entity> entitys,
			Map<String, Relationship> relationships){
		return new DataModel(entitys,relationships);
	}
	public Entity createEntity(String name, String duration, Map<String, Attribute> attributes){
		return new Entity(this.getDataModelSingleton().getEntityId(),name,duration,attributes);
	}
	public Relationship createRelationship(String name, String sourceEntity,
			String targetEntity, ArrayList<RelationshipRole> roles){
		return new Relationship(this.getDataModelSingleton().getRelationshipId(),name,sourceEntity,targetEntity,roles);
	}
	public RelationshipRole createRelationshipRole(String name, String maxCard){
		return new RelationshipRole(this.getDataModelSingleton().getRelationshipRoleId(),name,maxCard);
	}
	public Type createType(String type){
		return new Type(type);
	}
	public EntityDecorator createEntityDecorator(Entity entity){
	  EntityDecorator entityDecorator = new EntityDecorator(entity);
	  
    Iterator<String> iteratorA = entity.getAttributes().keySet().iterator();
    while(iteratorA.hasNext()){
      String key = (String)iteratorA.next();
      AttributeDecorator attributeDecorator = this.createAttributeDecorator(entity.getAttributes().get(key));
      entityDecorator.addAttribute(attributeDecorator);
    }
    
	  return entityDecorator;
	}
	public AttributeDecorator createAttributeDecorator(Attribute attribute){
	  return new AttributeDecorator(attribute);
	}
	public RelationshipDecorator createRelationshipDecorator(Relationship relationship){
	  //Falta ver una forma de asociar la relacion a la entidaddecorator
	  return new RelationshipDecorator(relationship);
	}
	public DataModelSingleton getDataModelSingleton() {
		return dataModelSingleton;
	}
}
