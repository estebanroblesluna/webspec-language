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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;

/**
 * @author Franco Giacosa
 */
public class Page implements WebModelElement, LinkElement{

	private String id;
	private String name;
	private Boolean home = false;
	private Boolean landmark = false;
	
	private HashMap<String,Link> links = new HashMap<String,Link>();
	private HashMap<String,ContentUnit> contentUnits = new HashMap<String,ContentUnit>();
	
	public Page(String id, String name, Boolean home, Boolean landmark){
		this.id = id;
		this.name = name;
		this.home = home;
		this.landmark = landmark;
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
	public HashMap<String, Link> getLinks() {
		return links;
	}
	public void setLinks(HashMap<String, Link> links) {
		this.links = links;
	}
	public HashMap<String, ContentUnit> getContentUnits() {
		return contentUnits;
	}
	public void setContentUnits(HashMap<String, ContentUnit> contentUnits) {
		this.contentUnits = contentUnits;
	}
	public void addContentUnit(ContentUnit newUnit){
		this.getContentUnits().put(newUnit.getId(), newUnit);
	}
	public void addLink(Link newLink){
		this.getLinks().put(newLink.getId(), newLink);
	}
	public String getLinkOrderStringMode(){
		Iterator<String> iterator = this.getLinks().keySet().iterator();
		String stringLinkOrder = "";
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			stringLinkOrder = stringLinkOrder + key + " ";
		}
		return stringLinkOrder;
	}
	public Boolean getHome() {
		return home;
	}
	public void setHome(Boolean home) {
		this.home = home;
	}
	public Boolean getLandmark() {
		return landmark;
	}
	public void setLandmark(Boolean landmark) {
		this.landmark = landmark;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
  public HashMap<String,Parameter> getInputParameters() {
    return new HashMap<String,Parameter>();
  }
  public HashMap<String,Parameter> getOutputParameters() {
    return new HashMap<String,Parameter>();
  }


}
