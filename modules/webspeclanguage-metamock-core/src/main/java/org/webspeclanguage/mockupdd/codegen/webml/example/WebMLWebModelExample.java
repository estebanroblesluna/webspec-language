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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
/**
 * @author Franco Giacosa
 */
public class WebMLWebModelExample {

	
	public WebModel generateWebModel(){
	  DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
    WebMLDataModelExample datamodelexample = new WebMLDataModelExample();  
    DataModel dataModel = datamodelexample.generateDataModel();
	  
	  
	  
	  WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webModelFactory = webModelFacade.getWebModelFactory();		
		EntityDecorator entD1 = dataFactory.createEntityDecorator(dataModel.getEntitys().get("ent1"));
    IndexUnit inu1 = webModelFactory.createIndexUnit("indexunit", entD1);
    DataUnit du1 = webModelFactory.createDataUnit("dataunit", entD1);

    
    
		
		Page page1 = webModelFactory.createPage("page1", true, true);
		Page page2 = webModelFactory.createPage("page2", false, true);
		page1.addContentUnit(inu1);
		page2.addContentUnit(du1);
		Link link = webModelFactory.createNormalLink("link page 1 to 2", true , inu1,du1);
		inu1.addLink(link);
		
		
		SiteView siteView = webModelFactory.createSiteView("Site View 1", true);
		siteView.setHomePage(page1);
		siteView.addPage(page1);
		siteView.addPage(page2);
		
		WebModel webModel = webModelFactory.createWebModel(siteView);
		webModel.addSiteView(siteView);
		return webModel;
	}
}
