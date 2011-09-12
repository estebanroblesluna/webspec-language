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

import java.util.HashMap;
import java.util.Iterator;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Attribute;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.AttributeDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public class WorkspaceParameters {

  public static void main(String[] args) {
    DataModelFactory dataFactory = new DataModelFactory();
    Type integerType = dataFactory.createType("integer");
    Type stringType = dataFactory.createType("string");
    Type passwordType = dataFactory.createType("password");
            
    Attribute at1 = dataFactory.createAttribute("userOID",integerType,true);
    Attribute at2 = dataFactory.createAttribute("userName",stringType,false);
    Attribute at3 = dataFactory.createAttribute("password",passwordType,false);
    Attribute at4 = dataFactory.createAttribute("email",stringType,false);
    HashMap<String,Attribute> attributeOrder1 = new HashMap<String,Attribute>();
    attributeOrder1.put(at1.getId(),at1);
    attributeOrder1.put(at2.getId(),at2);
    attributeOrder1.put(at3.getId(),at3);
    attributeOrder1.put(at4.getId(),at4);

    Entity ent1 = dataFactory.createEntity("User","persistent",attributeOrder1);
    EntityDecorator entD1 = dataFactory.createEntityDecorator(ent1);

    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    Page page1 = webFactory.createPage("page1", true , true);
    Page page2 = webFactory.createPage("page2", false, true);
    IndexUnit indexUnit = webFactory.createIndexUnit("indexunit1", entD1);
    DataUnit dataUnit = webFactory.createDataUnit("dataunit1", entD1);
    page1.addContentUnit(indexUnit);
    page2.addContentUnit(dataUnit);
    NormalLink normalLink = webFactory.createNormalLink("link1", true, indexUnit, dataUnit);
    HashMap<String,Parameter> parametersoutput = indexUnit.getOutputParameters();
    HashMap<String,Parameter> parametersInput = dataUnit.getInputParameters();
    
    Iterator<String> iteratorP = parametersoutput.keySet().iterator();
    while(iteratorP.hasNext()){
      String key = (String)iteratorP.next();
      Parameter parameterout = parametersoutput.get(key);
     

      Iterator<String> iteratorPi = parametersInput.keySet().iterator();
      
      while(iteratorPi.hasNext()){
        String key2 = (String)iteratorPi.next();
        Parameter parameterin = parametersInput.get(key2);
        System.out.println(parameterout.getName());
        System.out.println(parameterout.getId());
        System.out.println(parameterin.getId());

        if(parameterout.getId().equalsIgnoreCase(parameterin.getId())){

          ParameterCoupling parameterCoupling = webFactory.createParameterCoupling("coupling", true, false, parameterout, parameterin);
          normalLink.addParameterCoupling(parameterCoupling);
        }
      }
    }
    
    System.out.print(normalLink.getParameterCoupling().toString());
    
  }
}
