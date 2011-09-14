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
public interface DataModelFactory {
  
  public abstract Attribute createAttribute(String name, Type type, Boolean key);
  public abstract DataModel createDataModel(Map<String, Entity> entitys,
      Map<String, Relationship> relationships);
  public abstract Entity createEntity(String name, String duration, Map<String, Attribute> attributes);
  public abstract Relationship createRelationship(String name, Entity sourceEntity,
          Entity targetEntity, ArrayList<RelationshipRole> roles);
  public abstract RelationshipRole createRelationshipRole(String name, String maxCard);
  public abstract Type createType(String type);
  public abstract EntityDecorator createEntityDecorator(Entity entity);
  public abstract AttributeDecorator createAttributeDecorator(Attribute attribute);
  public abstract RelationshipDecorator createRelationshipDecorator(Relationship relationship);
  public abstract DataModelIds getDataModelIds();
}
