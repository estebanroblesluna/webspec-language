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
package org.webspeclanguage.mockupdd.codegen.webml.example;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;

import java.util.*;

/**
 * @author Franco Giacosa
 */
public class WebMLDataModelExample {

	public DataModel generateDefaultDataModel(){
		HashMap<String,Entity> entitys = new HashMap<String,Entity>();
		Type integerType = new Type("integer");
		Type stringType = new Type("string");
		Type passwordType = new Type("password");

		
		Attribute at1 = new Attribute("oid","userOID",integerType,true);
		Attribute at2 = new Attribute("userName","userName",stringType,false);
		Attribute at3 = new Attribute("password","password",passwordType,false);
		Attribute at4 = new Attribute("email","email",stringType,false);
		HashMap<String,Attribute> attributeOrder1 = new HashMap<String,Attribute>();
		attributeOrder1.put(at1.getId(),at1);
		attributeOrder1.put(at2.getId(),at2);
		attributeOrder1.put(at3.getId(),at3);
		attributeOrder1.put(at4.getId(),at4);

		Entity ent1 = new Entity("ent1","User","persistent",attributeOrder1);
					
		Attribute at5 = new Attribute("oid","groupOID",integerType,true);
		Attribute at6 = new Attribute("groupName","groupName",stringType,false);
		HashMap<String,Attribute> attributeOrder2 = new HashMap<String,Attribute>();
		attributeOrder2.put(at5.getId(),at5);
		attributeOrder2.put(at6.getId(),at6);
		Entity ent2 = new Entity("ent2","Group","persistent",attributeOrder2);
		
		Attribute at7 = new Attribute("oid","moduleOID",integerType,true);
		Attribute at8 = new Attribute("moduleID","moduleID",stringType,false);
		Attribute at9 = new Attribute("moduleName","moduleName",stringType, false);
		HashMap<String,Attribute> attributeOrder3 = new HashMap<String,Attribute>();
		attributeOrder3.put(at7.getId(),at7);
		attributeOrder3.put(at8.getId(),at8);
		attributeOrder3.put(at9.getId(),at9);		
		Entity ent3 = new Entity("ent3","Module","persistent",attributeOrder3);
		
		entitys.put(ent1.getId(),ent1);
		entitys.put(ent2.getId(),ent2);
		entitys.put(ent3.getId(),ent3);
		
		HashMap<String,Relationship> relationships = new HashMap<String,Relationship>();

		
		RelationshipRole rr1 = new RelationshipRole("User2Group","groups","N");
		RelationshipRole rr2 = new RelationshipRole("Group2User","users","N");
		ArrayList<RelationshipRole> roles1 = new ArrayList<RelationshipRole>();
		roles1.add(rr1);
		roles1.add(rr2);
		Relationship r1 = new Relationship("rel1","User_Group", ent1,	ent2, roles1);
		
		RelationshipRole rr3 = new RelationshipRole("User2DefaultGroup","defaultGroup","1");
		RelationshipRole rr4 = new RelationshipRole("DefaultGroup2User","defaultUsers","N");
		ArrayList<RelationshipRole> roles2 = new ArrayList<RelationshipRole>();
		roles2.add(rr3);
		roles2.add(rr4);
		Relationship r2 = new Relationship("rel2", "User_DefaultGroup", ent1, ent2, roles2);
		
		RelationshipRole rr5 = new RelationshipRole("Group2DefaultModule","defaultModule","1");
		RelationshipRole rr6 = new RelationshipRole("DefaultModule2Group","defaultGroups","N");
		ArrayList<RelationshipRole> roles3 = new ArrayList<RelationshipRole>();
		roles3.add(rr5);
		roles3.add(rr6);
		Relationship r3 = new Relationship("rel3","Group_DefaultModule",ent2,ent3, roles3);
		
		RelationshipRole rr7 = new RelationshipRole("Group2Module","modules","N");
		RelationshipRole rr8 = new RelationshipRole("Module2Group","groups","N");
		ArrayList<RelationshipRole> roles4 = new ArrayList<RelationshipRole>();
		roles4.add(rr7);
		roles4.add(rr8);
		Relationship r4 = new Relationship("rel4","Group_Module",ent2,ent3, roles4);
		
		relationships.put(r1.getId(),r1);
		relationships.put(r2.getId(),r2);
		relationships.put(r3.getId(),r3);
		relationships.put(r4.getId(),r4);
		
			
		DataModel dataModel = new DataModel(entitys,relationships);
		return dataModel;
	}
	public DataModel generateDataModel(){
	  DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
		
    DataModel dataModel = dataFactory.createDataModel(new HashMap<String,Entity>(),new HashMap<String,Relationship>());
    
		Type integerType = dataFactory.createType("integer");
		Type stringType = dataFactory.createType("string");
		Type passwordType = dataFactory.createType("password");
			
		Attribute at1 = dataFactory.createAttribute("userOID",integerType,true);
		Attribute at2 = dataFactory.createAttribute("userName",stringType,false);
		Attribute at3 = dataFactory.createAttribute("password",passwordType,false);
		Attribute at4 = dataFactory.createAttribute("email",stringType,false);

		Entity ent1 = dataFactory.createEntity("User","persistent",new HashMap<String,Attribute>());
		
		ent1.addAttribute(at1);
		ent1.addAttribute(at2);
		ent1.addAttribute(at3);
		ent1.addAttribute(at4);
					
		Attribute at5 = dataFactory.createAttribute("groupOID",integerType,true);
		Attribute at6 = dataFactory.createAttribute("groupName",stringType,false);
		
		Entity ent2 = dataFactory.createEntity("Group","persistent",new HashMap<String,Attribute>());
		
		ent2.addAttribute(at5);
		ent2.addAttribute(at6);
		
		Attribute at7 = dataFactory.createAttribute("moduleOID",integerType,true);
		Attribute at8 = dataFactory.createAttribute("moduleID",stringType,false);
		Attribute at9 = dataFactory.createAttribute("moduleName",stringType, false);
		
		Entity ent3 = dataFactory.createEntity("Module","persistent",new HashMap<String,Attribute>());
		
		ent3.addAttribute(at7);
		ent3.addAttribute(at8);
		ent3.addAttribute(at9);
		
		dataModel.addEntity(ent1);
		dataModel.addEntity(ent2);
		dataModel.addEntity(ent3);
		
		RelationshipRole rr1 = dataFactory.createRelationshipRole("groups","N");
		RelationshipRole rr2 = dataFactory.createRelationshipRole("users","N");
		ArrayList<RelationshipRole> roles1 = new ArrayList<RelationshipRole>();
		roles1.add(rr1);
		roles1.add(rr2);
		Relationship r1 = dataFactory.createRelationship("User_Group", ent1,	ent2, roles1);
		
		RelationshipRole rr3 = dataFactory.createRelationshipRole("defaultGroup","1");
		RelationshipRole rr4 = dataFactory.createRelationshipRole("defaultUsers","N");
		ArrayList<RelationshipRole> roles2 = new ArrayList<RelationshipRole>();
		roles2.add(rr3);
		roles2.add(rr4);
		Relationship r2 = dataFactory.createRelationship("User_DefaultGroup", ent1, ent2, roles2);
		
		RelationshipRole rr5 = dataFactory.createRelationshipRole("defaultModule","1");
		RelationshipRole rr6 = dataFactory.createRelationshipRole("defaultGroups","N");
		ArrayList<RelationshipRole> roles3 = new ArrayList<RelationshipRole>();
		roles3.add(rr5);
		roles3.add(rr6);
		Relationship r3 = dataFactory.createRelationship("Group_DefaultModule",ent2,ent3, roles3);
		
		RelationshipRole rr7 = dataFactory.createRelationshipRole("modules","N");
		RelationshipRole rr8 = dataFactory.createRelationshipRole("groups","N");
		ArrayList<RelationshipRole> roles4 = new ArrayList<RelationshipRole>();
		roles4.add(rr7);
		roles4.add(rr8);
		Relationship r4 = dataFactory.createRelationship("Group_Module",ent2,ent3, roles4);
		
				
		dataModel.addRelationship(r1);
		dataModel.addRelationship(r2);
		dataModel.addRelationship(r3);
		dataModel.addRelationship(r4);
			
		
		return dataModel;
	}
}
