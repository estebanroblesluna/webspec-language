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

import java.util.Iterator;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.AutomaticLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.KOLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NonContextualLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.OKLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.TransportLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationBoolean;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationDate;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationDecimal;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationFloat;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationInteger;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationTime;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.PatternConfigurationTimeStamp;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.CreateUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DataUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DeleteUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.EntryUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.IndexUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ModifyUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.MultiChoiceIndexUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.MultiEntryUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.SelectorUnit;

/**
 * @author Franco Giacosa
 */
public class WebModelConcreteVisitor implements WebModelVisitor {

	public void visit(WebModel webModel) {
		System.out.print("<WebModel ");
		System.out.print("defaultLocale\"");
		System.out.print(webModel.getDefaultLocale());
		System.out.print("\" ");
		System.out.print("layout:style=\"");
		System.out.print(webModel.getLayoutStyle());
		System.out.print("\" ");
		System.out.print("homeSiteView=\"");
		System.out.print(webModel.getHomeSiteView().getId());
		System.out.print("\"");
		System.out.print(">");
		System.out.println("");
		
		webModel.getLocale().accept(this);
		
		System.out.println("<ContextParameter id=\"UserCtxParam\" name=\"UserCtxParam\" entity=\"User\" type=\"entity\"/>");
		System.out.println("<ContextParameter id=\"GroupCtxParam\" name=\"GroupCtxParam\" entity=\"Group\" type=\"entity\"/>");
		System.out.println("<ContextParameter id=\"LanguageISOCtxParam\" name=\"LanguageISOCtxParam\" type=\"string\"/>");
		System.out.println("<ContextParameter id=\"CountryISOCtxParam\" name=\"CountryISOCtxParam\" type=\"string\"/>");

		System.out.println("</WebModel>");
		
		Iterator<String> iteratorSV = webModel.getSiteViews().keySet().iterator();
		while(iteratorSV.hasNext()){
			String key = (String)iteratorSV.next();
			webModel.getSiteViews().get(key).accept(this);
		}
	}
	public void visit(Locale locale) {
		System.out.print("<Locale ");
		System.out.print("id=\"");
		System.out.print(locale.getId());
		System.out.print("\" ");
		System.out.print("country=\"");
		System.out.print(locale.getCountry());
		System.out.print("\" ");
		System.out.print("language=\"");
		System.out.print(locale.getLanguage());
		System.out.print("\"");
		System.out.print(">");
		System.out.println("");
		Iterator<String> iteratorPa = locale.getPatternsConfiguration().keySet().iterator();
		while(iteratorPa.hasNext()){
			String key = (String)iteratorPa.next();
			locale.getPatternsConfiguration().get(key).accept(this);
		}
		System.out.println("</Locale>");
		
	}
	public void visit(PatternConfigurationBoolean patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("pattern=\"");
		System.out.print(patternConfiguration.getPattern());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");
	}
	public void visit(PatternConfigurationDecimal patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("useNumberPattern=\"");
		System.out.print(patternConfiguration.getUseNumberPattern().toString());
		System.out.print("\" ");
		System.out.print("maxDecimal=\"");
		System.out.print(patternConfiguration.getMaxDecimal().toString());
		System.out.print("\" ");
		System.out.print("minDecimal=\"");
		System.out.print(patternConfiguration.getMinDecimal().toString());
		System.out.print("\" ");
		System.out.print("minIngeter=\"");
		System.out.print(patternConfiguration.getMinInteger().toString());
		System.out.print("\" ");
		System.out.print("useThousandSeparator=\"");
		System.out.print(patternConfiguration.getUseThousandSeparator());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");		
	}
	public void visit(PatternConfigurationDate patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("pattern=\"");
		System.out.print(patternConfiguration.getPattern());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");		
	}
	public void visit(PatternConfigurationFloat patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("useNumberPattern=\"");
		System.out.print(patternConfiguration.getUseNumberPattern().toString());
		System.out.print("\" ");
		System.out.print("maxDecimal=\"");
		System.out.print(patternConfiguration.getMaxDecimal().toString());
		System.out.print("\" ");
		System.out.print("minDecimal=\"");
		System.out.print(patternConfiguration.getMinDecimal().toString());
		System.out.print("\" ");
		System.out.print("minIngeter=\"");
		System.out.print(patternConfiguration.getMinInteger().toString());
		System.out.print("\" ");
		System.out.print("useThousandSeparator=\"");
		System.out.print(patternConfiguration.getUseThousandSeparator());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");			
	}
	public void visit(PatternConfigurationInteger patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("useNumberPattern=\"");
		System.out.print(patternConfiguration.getUseNumberPattern().toString());
		System.out.print("\" ");
		System.out.print("minIngeter=\"");
		System.out.print(patternConfiguration.getMinInteger().toString());
		System.out.print("\"");
		System.out.print("useThousandSeparator=\"");
		System.out.print(patternConfiguration.getUseThousandSeparator());
		System.out.print("\" ");
		System.out.print("/>");
		System.out.println("");			
	}
	public void visit(PatternConfigurationTime patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("pattern=\"");
		System.out.print(patternConfiguration.getPattern());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");				
	}
	public void visit(PatternConfigurationTimeStamp patternConfiguration) {
		System.out.print("<PatternConfiuration ");
		System.out.print("type=\"");
		System.out.print(patternConfiguration.getType());
		System.out.print("\" ");
		System.out.print("pattern=\"");
		System.out.print(patternConfiguration.getPattern());
		System.out.print("\"");
		System.out.print("/>");
		System.out.println("");				
	}

	public void visit(SiteView siteView) {
		System.out.print("<SiteView ");
		System.out.print("id=\"");
		System.out.print(siteView.getId());
		System.out.print("\" ");
		System.out.print("name=\"");
		System.out.print(siteView.getName());
		System.out.print("\" ");
		System.out.print("homePage=\"");
		if(siteView.getHomePage() != null)
			System.out.print(siteView.getHomePage().getId());
		else
			System.out.print("");
		System.out.print("\"");
		System.out.print(">");
		System.out.println("");
		//we visit de operation units
		if(siteView.getOperationUnits().isEmpty())
			System.out.println("<OperationUnits />");

		else
		{
			System.out.println("<OperationUnits>");
			Iterator<String> iteratorOpUnit = siteView.getOperationUnits().keySet().iterator();
			while(iteratorOpUnit.hasNext()){
				String key = (String)iteratorOpUnit.next();
				siteView.getOperationUnits().get(key).accept(this);
			}
			System.out.println("</OperationUnits>");
		}
		System.out.println("</SiteView>");
		
		Iterator<String> iteratorPage = siteView.getPages().keySet().iterator();
		while(iteratorPage.hasNext()){
			String key = (String)iteratorPage.next();
			siteView.getPages().get(key).accept(this);
		}
	}

	public void visit(Page page) {
		System.out.print("<Page ");
		System.out.print("id=\"");
		System.out.print(page.getId());
		System.out.print("\" ");
		System.out.print("name=\"");
		System.out.print(page.getName());
		if(page.getLandmark()){
			System.out.print("\" ");
			System.out.print("landmark=\"");
			System.out.print(page.getLandmark().toString());
		}
		if(!page.getLinks().isEmpty()){
			System.out.print("\" ");
			System.out.print("linkOrder=\"");
			System.out.print(page.getLinkOrderStringMode());
		}

		System.out.print("\"");
		System.out.print(">");
		System.out.println("");	
		
		if(page.getContentUnits().isEmpty())
			System.out.println("<ContentUnits />");
		else
		{
			System.out.println("<ContentUnits>");
			Iterator<String> iteratorContentUnit = page.getContentUnits().keySet().iterator();
			while(iteratorContentUnit.hasNext()){
				String key = (String)iteratorContentUnit.next();
				page.getContentUnits().get(key).accept(this);
			}
			System.out.println("</ContentUnits>");
		}
		
		if(!page.getLinks().isEmpty())
		{
			Iterator<String> iteratorLink = page.getLinks().keySet().iterator();
			while(iteratorLink.hasNext()){
				String key = (String)iteratorLink.next();
				page.getLinks().get(key).accept(this);
			}
		}
		System.out.println("</Page>");
	}

	public void visit(CreateUnit createUnit) {
		
	}
	public void visit(DataUnit dataUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(DeleteUnit deleteUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(EntryUnit entryUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(IndexUnit indexUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(ModifyUnit modifyUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(MultiChoiceIndexUnit multiChoiceIndexUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(MultiEntryUnit mutiEntryUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(SelectorUnit selectorUnit) {
		// TODO Auto-generated method stub
		
	}
	public void visit(AutomaticLink automaticLink) {
		// TODO Auto-generated method stub
		
	}
	public void visit(KOLink koLink) {
		// TODO Auto-generated method stub
		
	}
	public void visit(NonContextualLink nonContextualLink) {
		// TODO Auto-generated method stub
		
	}
	public void visit(OKLink okLink) {
		// TODO Auto-generated method stub
		
	}
	public void visit(NormalLink normalLink) {
		// TODO Auto-generated method stub
		
	}
	public void visit(TransportLink transportLink) {
		// TODO Auto-generated method stub
		
	}




}
