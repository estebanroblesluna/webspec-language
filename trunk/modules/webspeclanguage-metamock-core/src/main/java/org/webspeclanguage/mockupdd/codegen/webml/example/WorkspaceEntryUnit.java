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
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactoryImpl;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.ParameterCoupling;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;

/**
 * @author Franco Giacosa
 */
public class WorkspaceEntryUnit {

  public static void main(String[] args) {
    DataModelFactory dataFactory = new DataModelFactory();
    Type integerType = dataFactory.createType("integer");
    Type stringType = dataFactory.createType("string");
    Type textType = dataFactory.createType("text");
    
    Attribute at1 = dataFactory.createAttribute("OID",integerType,true);
    Attribute at2 = dataFactory.createAttribute("description",textType,false);
    Attribute at3 = dataFactory.createAttribute("title",stringType,false);
    Attribute at4 = dataFactory.createAttribute("language",stringType,false);
    Attribute at5 = dataFactory.createAttribute("pages",stringType,false);
    HashMap<String,Attribute> attributeOrder1 = new HashMap<String,Attribute>();
    attributeOrder1.put(at1.getId(),at1);
    attributeOrder1.put(at2.getId(),at2);
    attributeOrder1.put(at3.getId(),at3);
    attributeOrder1.put(at4.getId(),at4);
    attributeOrder1.put(at5.getId(),at5);

    Entity ent1 = dataFactory.createEntity("Book","persistent",attributeOrder1);
    EntityDecorator entD1 = dataFactory.createEntityDecorator(ent1);

    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();   // Page page1 = webFactory.createPage("page1", true , true);
    //NormalField nf1 = webFactory.createNormalField("OID", integerType);
    //NormalField nf2 = webFactory.createNormalField("description", textType);
    //NormalField nf3 = webFactory.createNormalField("title", stringType);
    EntryUnit entryUnit = webFactory.createEntryUnit("entryunit1", entD1);
    entryUnit.createFields();
    SelectionField sf1 = webFactory.createSelectionField("pages", stringType);
   
    entryUnit.addField(sf1);
    
    WebModelConcreteVisitor visitor = new WebModelConcreteVisitor();
    entryUnit.accept(visitor);
    /*
    HashMap<String,Parameter> parametersInput = entryUnit.getInputParameters();
    
    Iterator<String> iteratorPi = parametersInput.keySet().iterator();
      while(iteratorPi.hasNext()){
        String key = (String)iteratorPi.next();
        Parameter parameterin = parametersInput.get(key);
        System.out.println("parameterin Name: " + parameterin.getName());
        System.out.println("parameterin ID: " + parameterin.getId());
      }
    
    HashMap<String,Parameter> parametersOutput = entryUnit.getOutputParameters();
      
    Iterator<String> iteratorPo = parametersOutput.keySet().iterator();
        while(iteratorPo.hasNext()){
          String key = (String)iteratorPo.next();
          Parameter parameterout = parametersOutput.get(key);
          System.out.println("parameterout Name: " + parameterout.getName());
          System.out.println("parameterout ID: " + parameterout.getId());
          
        }
    
    WebModelConcreteVisitor visitor = new WebModelConcreteVisitor();
    entryUnit.accept(visitor);*/

  }

}
