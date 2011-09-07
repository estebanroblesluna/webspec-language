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
import java.util.Map;

/**
 * @author Franco Giacosa
 */
public class WebModel implements WebModelElement {

	private Locale locale = new Locale();
	private String defaultLocale = "lcl1";
	private String layoutStyle = "WRDefault";
	private SiteView homeSiteView;
	private Map<String,SiteView> siteViews = new HashMap<String,SiteView>();
	
	
	public WebModel(SiteView homeSiteView){
		this.homeSiteView = homeSiteView;
		this.defaultLocale = locale.getId();
		
	}
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getDefaultLocale() {
		return defaultLocale;
	}
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	public String getLayoutStyle() {
		return layoutStyle;
	}
	public void setLayoutStyle(String layoutStyle) {
		this.layoutStyle = layoutStyle;
	}
	public void addSiteView(SiteView newSiteView){
		this.getSiteViews().put(newSiteView.getId(),newSiteView);
	}
	public void setSiteViews(Map<String,SiteView> siteViews) {
		this.siteViews = siteViews;
	}
	public Map<String,SiteView> getSiteViews() {
		return siteViews;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
	public SiteView getHomeSiteView() {
		return homeSiteView;
	}
	public void setHomeSiteView(SiteView homeSiteView) {
		this.homeSiteView = homeSiteView;
	}
}
