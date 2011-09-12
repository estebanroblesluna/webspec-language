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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelConcreteVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactoryImpl;
import  org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import  org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import  org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
/**
 * @author Franco Giacosa
 */
public class WorkspaceCouplingParameter {

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
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();    IndexUnit indexUnit = webFactory.createIndexUnit("indexunit1", entD1);
    DataUnit dataUnit = webFactory.createDataUnit("datauni1", entD1);
    NormalLink link = webFactory.createNormalLink("link", false, indexUnit, dataUnit);
    
    Parameter parameterout = indexUnit.getParameterKey();
          
     Iterator<String> iteratorin = dataUnit.getInputParameters().keySet().iterator();
      while(iteratorin.hasNext()){
       String keyin = (String)iteratorin.next();
       Parameter parameterin = dataUnit.getInputParameters().get(keyin);
       ParameterCoupling pc = webFactory.createParameterCoupling("indextoout", true, false, parameterout, parameterin);
       link.addParameterCoupling(pc);
     }
    
    
    WebModelConcreteVisitor visitor = new WebModelConcreteVisitor();
    link.accept(visitor);
  }
}
