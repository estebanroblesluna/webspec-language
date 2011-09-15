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

package org.webspeclanguage.mockupdd.codegen.webml.webmodel;
import java.util.HashMap;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import junit.framework.TestCase;


/**
 * @author Franco Giacosa
 */
public class WebModelFactoyTestCase extends TestCase{

  public void testCreateSiteView(){
    SiteView sv1 = new SiteView("sv1","SiteView1",true);
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    SiteView sv2 = webFactory.createSiteView("SiteView1",true);
    
    assertEquals(sv1.getId(),sv2.getId());
    assertEquals(sv1.getName(),sv2.getName());
    assertEquals(sv1.getHomeSiteView(),sv2.getHomeSiteView());
  }
  
  public void testCreatePage(){
    Page p1 = new Page("page1","page1", true, true);
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    Page p2 = webFactory.createPage("page1", true, true);
    
    assertEquals(p1.getId(),p2.getId());
    assertEquals(p1.getName(),p2.getName());
    assertEquals(p1.getHome(),p2.getHome());
    assertEquals(p1.getLandmark(),p2.getLandmark());  
    
  }
  
  public void testCreateDataUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    Selector selector = webFactory.createSelector(entD.getEntityKey());
    DataUnit du1 = new DataUnit("dau1","dataunit",entD, selector);
    DataUnit du2 = webFactory.createDataUnit("dataunit", entD);
    
    assertEquals(du1.getId(),du2.getId());
    assertEquals(du1.getName(),du2.getName());
    assertEquals(du1.getDisplayAllAttributes(),du2.getDisplayAllAttributes());
    assertEquals(du1.getEntity(),du2.getEntity());
    this.parameterTest(du1, du2);
  }
  public void testEntryUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    EntryUnit eu1 = new EntryUnit("enu1","entryunit", entD);
    EntryUnit eu2 = webFactory.createEntryUnit("entryunit", entD);
    eu1.createFields();
    eu2.createFields();
    
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Type stringType = dataFactory.createType("string");
    SelectionField slf = webFactory.createSelectionField("selectionField", stringType);
    
    eu1.addField(slf);
    eu2.addField(slf);
    assertEquals(eu1.getId(),eu2.getId());
    assertEquals(eu1.getName(),eu2.getName());
    assertEquals(eu1.getEntity().getId(),eu2.getEntity().getId());
    this.parameterTest(eu1, eu2);
  }
  public void testMultiEntryUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    MultiEntryUnit meu1 = new MultiEntryUnit("meu1","multientryunit", entD);
    MultiEntryUnit meu2 = webFactory.createMultiEntryUnit("multientryunit", entD);
    meu1.createFields();
    meu2.createFields();
    
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Type stringType = dataFactory.createType("string");
    SelectionField slf = webFactory.createSelectionField("selectionField", stringType);
    
    meu1.addField(slf);
    meu2.addField(slf);
    assertEquals(meu1.getId(),meu2.getId());
    assertEquals(meu1.getName(),meu2.getName());
    assertEquals(meu1.getEntity().getId(),meu2.getEntity().getId());
    this.parameterTest(meu1, meu2);
  }
  public void testIndexUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    IndexUnit inu1 = new IndexUnit("inu1","indexunit", entD);
    IndexUnit inu2 = webFactory.createIndexUnit("indexunit", entD);
        
    assertEquals(inu1.getId(),inu2.getId());
    assertEquals(inu1.getName(),inu2.getName());
    assertEquals(inu1.getEntity().getId(),inu2.getEntity().getId());
    this.parameterTest(inu1, inu2);
  }
  public void testMultiChoiceIndexUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    MultiChoiceIndexUnit mciu1 = new MultiChoiceIndexUnit("mciu1","multichoiceindexunit", entD);
    MultiChoiceIndexUnit mciu2 = webFactory.createMultiChoiceIndexUnit("multichoiceindexunit", entD);
        
    assertEquals(mciu1.getId(),mciu2.getId());
    assertEquals(mciu1.getName(),mciu2.getName());
    assertEquals(mciu1.getEntity().getId(),mciu2.getEntity().getId());
    this.parameterTest(mciu1, mciu2);
  }
  public void testSelectorUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    SelectorUnit seu1 = new SelectorUnit("seu1","selectorunit", entD);
    SelectorUnit seu2 = webFactory.createSelectorUnit("selectorunit", entD);
        
    assertEquals(seu1.getId(),seu2.getId());
    assertEquals(seu1.getName(),seu2.getName());
    assertEquals(seu1.getEntity().getId(),seu2.getEntity().getId());
    this.parameterTest(seu1, seu2);
  }
  public void testCreateUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    CreateUnit cru1 = new CreateUnit("cru1","createunit", entD);
    CreateUnit cru2 = webFactory.createCreateUnit("createunit", entD);
        
    assertEquals(cru1.getId(),cru2.getId());
    assertEquals(cru1.getName(),cru2.getName());
    assertEquals(cru1.getEntity().getId(),cru2.getEntity().getId());
    this.parameterTest(cru1, cru2);
  }
  public void testDeleteUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    
    Selector selector = webFactory.createSelector(entD.getEntityKey());
    
    DeleteUnit dlu1 = new DeleteUnit("dlu1","deleteunit", entD,selector);
    DeleteUnit dlu2 = webFactory.createDeleteUnit("deleteunit", entD);
        
    assertEquals(dlu1.getId(),dlu2.getId());
    assertEquals(dlu1.getName(),dlu2.getName());
    assertEquals(dlu1.getEntity().getId(),dlu2.getEntity().getId());
    this.parameterTest(dlu1, dlu2);
  }
  public void testModifyUnit(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entD = this.getDefaultTestEntity();
    
    Selector selector = webFactory.createSelector(entD.getEntityKey());
    
    ModifyUnit mfu1 = new ModifyUnit("mfu1","modifyunit", entD,selector);
    ModifyUnit mfu2 = webFactory.createModifyUnit("modifyunit", entD);
        
    assertEquals(mfu1.getId(),mfu2.getId());
    assertEquals(mfu1.getName(),mfu2.getName());
    assertEquals(mfu1.getEntity().getId(),mfu2.getEntity().getId());
    this.parameterTest(mfu1, mfu2);
  }
  public void parameterTest(Unit u1, Unit u2){
    assertEquals(u1.getInputParameters().size(),u2.getInputParameters().size());
    assertEquals(u1.getOutputParameters().size(),u2.getOutputParameters().size());
  }
  public EntityDecorator getDefaultTestEntity(){
    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    
    Type integerType = dataFactory.createType("integer");
    Type stringType = dataFactory.createType("string");
    
    Attribute at1 = dataFactory.createAttribute("attribute1",integerType,true);
    Attribute at2 = dataFactory.createAttribute("attribute2",stringType,false);
    Attribute at3 = dataFactory.createAttribute("attribute3",integerType,false);
    Attribute at4 = dataFactory.createAttribute("attribute4",stringType,false);
    
    Entity ent1 = dataFactory.createEntity("Ent1","persistent",new HashMap<String,Attribute>());
    ent1.addAttribute(at1);
    ent1.addAttribute(at2);
    ent1.addAttribute(at3);
    ent1.addAttribute(at4);
    
    return dataFactory.createEntityDecorator(ent1);

  }
}
