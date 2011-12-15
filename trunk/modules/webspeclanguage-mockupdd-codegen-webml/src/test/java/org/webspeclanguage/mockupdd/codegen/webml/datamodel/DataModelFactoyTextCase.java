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

import junit.framework.TestCase;
import java.util.*;
/**
 * @author Franco Giacosa
 */
public class DataModelFactoyTextCase extends TestCase{

  
  public void testCreateAttribute(){
    Type integerType = new Type("integer");
    Attribute at1 = new Attribute("att1","userOID",integerType,true);
    
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    Attribute at2 = dataFactory.createAttribute("userOID",integerType,true);
    
    assertEquals(at1.getId(),at2.getId());
    assertEquals(at1.getName(),at2.getName());
    assertEquals(at1.getAttributeType(),at2.getAttributeType());
    assertEquals(at1.getKey(),at2.getKey());
  }
  public void testCreateEntity(){
    Entity ent1 = new Entity("ent1","User","persistent",new HashMap<String,Attribute>());

    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    Entity ent2 = dataFactory.createEntity("User","persistent",new HashMap<String,Attribute>());
    
    assertEquals(ent1.getId(),ent2.getId());
    assertEquals(ent1.getName(),ent2.getName());
    assertEquals(ent1.getDuration(),ent2.getDuration());
  }
  public void testCreateRelationship(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();

    Entity ent1 = dataFactory.createEntity("Ent1","persistent",new HashMap<String,Attribute>());
    Entity ent2 = dataFactory.createEntity("Ent2","persistent",new HashMap<String,Attribute>());
     
    Relationship rel1 = new Relationship("rel1","Ent1toEnt2",ent1,ent2,new ArrayList<RelationshipRole>());
    Relationship rel2 = dataFactory.createRelationship("Ent1toEnt2",ent1,ent2,new ArrayList<RelationshipRole>());
    
    assertEquals(rel1.getId(),rel2.getId());
    assertEquals(rel1.getName(),rel2.getName());
    assertEquals(rel1.getSourceEntity().getId(),rel2.getSourceEntity().getId());
    assertEquals(rel1.getTargetEntity().getId(),rel2.getTargetEntity().getId());
  }
  
  public void testCreateRelationshipRole(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    RelationshipRole rel1 = new RelationshipRole("rel2","atob","N");
    RelationshipRole rel2 = dataFactory.createRelationshipRole("atob", "N");
    assertEquals(rel1.getId(),rel2.getId());
    assertEquals(rel1.getName(),rel2.getName());
    assertEquals(rel1.getMaxCard(),rel2.getMaxCard()); 
  }
  public void testEntityDecorator(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Entity ent1 = dataFactory.createEntity("ent1", "persistent", new HashMap<String,Attribute>());
    
    EntityDecorator entD1 = dataFactory.createEntityDecorator(ent1);
    
    assertEquals(entD1.getId(),ent1.getId());
    assertEquals(entD1.getDuration(),ent1.getDuration());
    
  }
  public void testAttributeDecorator(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Type integerType = new Type("integer");

    Attribute att1 = dataFactory.createAttribute("attribute1", integerType, true);
    AttributeDecorator attD1 = dataFactory.createAttributeDecorator(att1);
    
    assertEquals(attD1.getId(),att1.getId());
    assertEquals(attD1.getName(),att1.getName());
    assertEquals(attD1.getAttributeType(),att1.getAttributeType());
    
  }
  public void testRelationshipDecorator(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Entity ent1 = dataFactory.createEntity("Ent1","persistent",new HashMap<String,Attribute>());
    Entity ent2 = dataFactory.createEntity("Ent2","persistent",new HashMap<String,Attribute>());
    dataModelFacade.addEntity(dataModelFacade.getDataModelFactory().createEntityDecorator(ent1));
    dataModelFacade.addEntity(dataModelFacade.getDataModelFactory().createEntityDecorator(ent2));
    Relationship rel1 = dataFactory.createRelationship("rel1", ent1, ent2, new ArrayList<RelationshipRole>());
    RelationshipDecorator relD1 = dataFactory.createRelationshipDecorator(rel1);
    
    assertEquals(relD1.getId(),rel1.getId());
    assertEquals(relD1.getName(),rel1.getName());
    assertEquals(relD1.getSourceEntity().getId(),rel1.getSourceEntity().getId());
    assertEquals(relD1.getTargetEntity().getId(),rel1.getTargetEntity().getId());
    
  }
  
}
