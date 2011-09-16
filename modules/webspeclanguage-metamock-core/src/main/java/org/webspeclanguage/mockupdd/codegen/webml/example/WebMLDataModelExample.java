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
