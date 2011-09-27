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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.SelectionField;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
/**
 * @author Franco Giacosa
 */
public class WebMLWebModelExample {

	
	public WebModel generateWebModel(DataModel dataModel){

	  DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    EntityDecorator entD1 = dataFactory.createEntityDecorator(dataModel.getEntitys().get("ent1"));
    Type integerType = dataFactory.createType("integer");
    
	  WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webModelFactory = webModelFacade.getWebModelFactory();
    //SiteView creation
    SiteView siteView = webModelFactory.createSiteView("Site View 1", true);
    //WebModel creation
    WebModel webModel = webModelFactory.createWebModel(siteView);
		//Pages creation
		Page page1 = webModelFactory.createPage("page1", true, true);
    Page page2 = webModelFactory.createPage("page2", false, true);
		
    //ContentUnits creation
		MultiChoiceIndexUnit mulciu = webModelFactory.createMultiChoiceIndexUnit("multichoice", entD1);
    
		IndexUnit inu1 = webModelFactory.createIndexUnit("indexunit", entD1);
    
		DataUnit du1 = webModelFactory.createDataUnit("dataunit", entD1);
    
		EntryUnit eu1 = webModelFactory.createEntryUnit("entryunit1", entD1);
    eu1.addField(webModelFactory.createSelectionField("selectionfieldinteger", integerType));
    HashMap<String,Parameter> outputparameteres = eu1.getOutputParameters();
    Parameter outputparam = outputparameteres.get("email");
    
    MultiEntryUnit meu1 = webModelFactory.createMultiEntryUnit("multientryunit1", entD1);
    meu1.addField(webModelFactory.createSelectionField("selectionfieldinteger2", integerType));
    HashMap<String,Parameter> inputparameters = meu1.getInputParameters();
    Parameter inputParam = inputparameters.get("email");
    
    //Content Units added
    page1.addContentUnit(inu1);
    page1.addContentUnit(mulciu);
    page1.addContentUnit(eu1);
    page2.addContentUnit(meu1);
    page2.addContentUnit(du1);
    
    //OperationUnits creation
    CreateUnit cu1 = webModelFactory.createCreateUnit("createunit1", entD1);
    DeleteUnit delu1 = webModelFactory.createDeleteUnit("deleteunit1", entD1);
    ModifyUnit mu1 = webModelFactory.createModifyUnit("modifyunit1", entD1);
    
    //Links creation
		webModelFactory.createTransportLink("transport", true, inu1, eu1);
		webModelFactory.createAutomaticLink("auto", true, meu1, du1);
    Link linkcoupling = webModelFactory.createNormalLink("link coupling", false, eu1,meu1);
		webModelFactory.createNormalLink("link page 1 to 2", true , inu1,du1);
    webModelFactory.createNormalLink("link page 1 to 2", true , mulciu,du1);
   	webModelFactory.createNormalLink("tocreateunit",true, eu1, cu1);
    webModelFactory.createOKLink("oklink",true, delu1, inu1);
    webModelFactory.createKOLink("koLink",true, mu1, mulciu);
    
    //Coupling creation
    linkcoupling.addParameterCoupling(webModelFactory.createParameterCoupling("emailtousername", true, false, outputparam, inputParam));

    //OperationUnits added
		siteView.addOperationUnit(cu1);
		siteView.addOperationUnit(delu1);
		siteView.addOperationUnit(mu1);
		
		//HomePage set, pages added to siteview
		siteView.setHomePage(page1);
		siteView.addPage(page1);
		siteView.addPage(page2);
		
		//Siteview added
		webModel.addSiteView(siteView);
		return webModel;
	}
}
