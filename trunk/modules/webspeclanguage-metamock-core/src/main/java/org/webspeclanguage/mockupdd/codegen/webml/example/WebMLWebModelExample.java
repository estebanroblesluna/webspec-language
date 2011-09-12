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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;

/**
 * @author Franco Giacosa
 */
public class WebMLWebModelExample {

	
	public WebModel generateWebModel(){
	  WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webModelFactory = webModelFacade.getWebModelFactory();		
		
		
		Page page1 = webModelFactory.createPage("page1", true, true);
		Page page2 = webModelFactory.createPage("page2", false, true);
		
		//Link link = webModelFactory.createNonContextualLink("link page 1 to 2", true , page1,page2);
	//	page1.addLink(link);
		
		
		SiteView siteView = webModelFactory.createSiteView("Site View 1", true);
		siteView.setHomePage(page1);
		siteView.addPage(page1);
		siteView.addPage(page2);
		
		WebModel webModel = webModelFactory.createWebModel(siteView);
		webModel.addSiteView(siteView);
		return webModel;
	}
}
