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

import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;

/**
 * @author Franco Giacosa
 */
public class SiteView implements WebModelElement{

	private String id;
	private String name;
	private Page homePage;
	private Boolean homeSiteView = false;
	private HashMap<String,Page> pages = new HashMap<String,Page>();
	private HashMap<String,OperationUnit> operationUnits = new HashMap<String,OperationUnit>();
	
	public SiteView(String id, String name, Boolean homeSiteView){
		this.id = id;
		this.name = name;
		this.homeSiteView = homeSiteView;
		
	}
	public SiteView(){
		//creamos el siteview por default ya que por ahora no son necesarios más de 1 site view
		this.name = "Site View 1";
		this.id = "sv1";		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Page getHomePage() {
		return homePage;
	}
	public void setHomePage(Page homePage) {
		this.homePage = homePage;
	}
	public HashMap<String, Page> getPages() {
		return pages;
	}
	public void setPages(HashMap<String, Page> pages) {
		this.pages = pages;
	}
	public void addPage(Page newPage){
		this.getPages().put(newPage.getId(), newPage);
	}
	public void addOperationUnit(OperationUnit newOperationUnit){
		this.getOperationUnits().put(newOperationUnit.getId(), newOperationUnit);
	}
	public HashMap<String, OperationUnit> getOperationUnits() {
		return operationUnits;
	}
	public void setOperationUnits(HashMap<String, OperationUnit> operationUnits) {
		this.operationUnits = operationUnits;
	}
	public Boolean getHomeSiteView() {
		return homeSiteView;
	}
	public void setHomeSiteView(Boolean homeSiteView) {
		this.homeSiteView = homeSiteView;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
}
