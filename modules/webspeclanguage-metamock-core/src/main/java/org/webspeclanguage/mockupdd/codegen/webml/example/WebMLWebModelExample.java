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
	  
	  
	  WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webModelFactory = webModelFacade.getWebModelFactory();
    Type integerType = dataFactory.createType("integer");

		EntityDecorator entD1 = dataFactory.createEntityDecorator(dataModel.getEntitys().get("ent1"));
		
		SiteView siteView = webModelFactory.createSiteView("Site View 1", true);
		
		Page page1 = webModelFactory.createPage("page1", true, true);
    Page page2 = webModelFactory.createPage("page2", false, true);
		
		MultiChoiceIndexUnit mulciu = webModelFactory.createMultiChoiceIndexUnit("multichoice", entD1);
    page1.addContentUnit(mulciu);

    IndexUnit inu1 = webModelFactory.createIndexUnit("indexunit", entD1);
    page1.addContentUnit(inu1);

    EntryUnit eu1 = webModelFactory.createEntryUnit("entryunit1", entD1);
    eu1.createFields();
    eu1.addField(webModelFactory.createSelectionField("selectionfieldinteger", integerType));
    HashMap<String,Parameter> outputparameteres = eu1.getOutputParameters();
    Parameter outputparam = outputparameteres.get("email");
    
    
    page1.addContentUnit(eu1);
    
    MultiEntryUnit meu1 = webModelFactory.createMultiEntryUnit("multientryunit1", entD1);
    meu1.createFields();
    meu1.addField(webModelFactory.createSelectionField("selectionfieldinteger2", integerType));
    HashMap<String,Parameter> inputparameters = meu1.getInputParameters();
    Parameter inputParam = inputparameters.get("email");

    page2.addContentUnit(meu1);

    DataUnit du1 = webModelFactory.createDataUnit("dataunit", entD1);
    page2.addContentUnit(du1);
  
		
		inputParam.getName();
		TransportLink linktranspot = webModelFactory.createTransportLink("transport", true, inu1, eu1);
		inu1.addLink(linktranspot);
		AutomaticLink linkauto = webModelFactory.createAutomaticLink("auto", true, meu1, du1);
		meu1.addLink(linkauto);
    Link linkcoupling = webModelFactory.createNormalLink("link coupling", false, eu1,meu1);
    linkcoupling.addParameterCoupling(webModelFactory.createParameterCoupling("emailtousername", true, false, outputparam, inputParam));
		Link link1 = webModelFactory.createNormalLink("link page 1 to 2", true , inu1,du1);
    Link link2 = webModelFactory.createNormalLink("link page 1 to 2", true , mulciu,du1);
    eu1.addLink(linkcoupling);
    mulciu.addLink(link2);
		inu1.addLink(link1);
		
		siteView.addOperationUnit(webModelFactory.createCreateUnit("createunit1", entD1));
		siteView.addOperationUnit(webModelFactory.createDeleteUnit("deleteunit1", entD1));
		siteView.addOperationUnit(webModelFactory.createModifyUnit("modifyunit1", entD1));
		
		siteView.setHomePage(page1);
		siteView.addPage(page1);
		siteView.addPage(page2);
		
		WebModel webModel = webModelFactory.createWebModel(siteView);
		webModel.addSiteView(siteView);
		return webModel;
	}
}
