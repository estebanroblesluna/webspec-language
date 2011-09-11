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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.AutomaticLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.KOLink;
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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.KeyCondition;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ModifyUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.MultiChoiceIndexUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.MultiEntryUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.Selector;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.SelectorUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.NormalField;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.SelectionField;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.Slot;

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
    System.out.print("<CreateUnit ");
    System.out.print("id=\"");
    System.out.print(createUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(createUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(createUnit.getEntity().getName());
    if(!createUnit.getLinks().isEmpty())
    {
      System.out.print(">");
      System.out.println(""); 
      Iterator<String> iteratorLink = createUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        createUnit.getLinks().get(key).accept(this);
      }
      System.out.println("</CreateUnit>");
    }
    else
    {
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
     
	}
  public void visit(IndexUnit indexUnit) {
    System.out.print("<IndexUnit ");
    System.out.print("id=\"");
    System.out.print(indexUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(indexUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(indexUnit.getEntity().getName());
    if(!indexUnit.getLinks().isEmpty())
    {
      System.out.print(">");
      System.out.println(""); 
      Iterator<String> iteratorLink = indexUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        indexUnit.getLinks().get(key).accept(this);
      }
      System.out.println("</IndexUnit>");
    }
    else
    {
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }   
  }
  public void visit(MultiChoiceIndexUnit multiChoiceIndexUnit) {
    System.out.print("<MultiChoiceIndexUnit ");
    System.out.print("id=\"");
    System.out.print(multiChoiceIndexUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(multiChoiceIndexUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(multiChoiceIndexUnit.getEntity().getName());
    if(!multiChoiceIndexUnit.getLinks().isEmpty())
    {
      System.out.print(">");
      System.out.println(""); 
      Iterator<String> iteratorLink = multiChoiceIndexUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        multiChoiceIndexUnit.getLinks().get(key).accept(this);
      }
      System.out.println("</MultiChoiceIndexUnit>");
    }
    else
    {
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
  }
  public void visit(SelectorUnit selectorUnit) {
    System.out.print("<SelectorUnit ");
    System.out.print("id=\"");
    System.out.print(selectorUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(selectorUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(selectorUnit.getEntity().getName());
    if(!selectorUnit.getLinks().isEmpty())
    {
      System.out.print(">");
      System.out.println(""); 
      Iterator<String> iteratorLink = selectorUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        selectorUnit.getLinks().get(key).accept(this);
      }
      System.out.println("</SelectorUnit>");
    }
    else
    {
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }    
  }
  public void visit(DataUnit dataUnit) {
    System.out.print("<DataUnit ");
    System.out.print("id=\"");
    System.out.print(dataUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(dataUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(dataUnit.getEntity().getName());
    System.out.print(">");
    System.out.println(""); 
    dataUnit.getSelector().accept(this);
    if(!dataUnit.getLinks().isEmpty())
    { 
      Iterator<String> iteratorLink = dataUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        dataUnit.getLinks().get(key).accept(this);
      }
    }
    System.out.println("</DataUnit>");
	}
	public void visit(DeleteUnit deleteUnit) {
	  System.out.print("<DeleteUnit ");
    System.out.print("id=\"");
    System.out.print(deleteUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(deleteUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(deleteUnit.getEntity().getName());
    System.out.print(">");
    System.out.println(""); 
    deleteUnit.getSelector().accept(this);
    if(!deleteUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = deleteUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        deleteUnit.getLinks().get(key).accept(this);
      }
    }
    System.out.println("</DeleteUnit>");		
	}
	public void visit(ModifyUnit modifyUnit) {
	  System.out.print("<ModifyUnit ");
    System.out.print("id=\"");
    System.out.print(modifyUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(modifyUnit.getName());
    System.out.print("\" ");
    System.out.print("entity=\"");
    System.out.print(modifyUnit.getEntity().getName());
    System.out.print(">");
    System.out.println(""); 
    modifyUnit.getSelector().accept(this);
    if(!modifyUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = modifyUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        modifyUnit.getLinks().get(key).accept(this);
      }
    }
    System.out.println("</ModifyUnit>");      
  }
	public void visit(EntryUnit entryUnit) {
	  System.out.print("<EntryUnit ");
    System.out.print("id=\"");
    System.out.print(entryUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(entryUnit.getName());
    System.out.print(">");
    System.out.println(""); 
    if(!entryUnit.getFields().isEmpty())
    {
      Iterator<String> iteratorField = entryUnit.getFields().keySet().iterator();
      while(iteratorField.hasNext()){
        String key = (String)iteratorField.next();
        entryUnit.getFields().get(key).accept(this);
      }
    }
    if(!entryUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = entryUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        entryUnit.getLinks().get(key).accept(this);
      }
    }
    System.out.println("</EntryUnit>");      
	}
	public void visit(MultiEntryUnit mutiEntryUnit) {
	  System.out.print("<MultiEntryUnit ");
    System.out.print("id=\"");
    System.out.print(mutiEntryUnit.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(mutiEntryUnit.getName());
    System.out.print("\" ");
    System.out.print("minLength=\"");
    System.out.print(mutiEntryUnit.getMinLength());
    System.out.print("\"");
    System.out.print(">");
    System.out.println(""); 
    if(!mutiEntryUnit.getFields().isEmpty())
    {
      Iterator<String> iteratorField = mutiEntryUnit.getFields().keySet().iterator();
      while(iteratorField.hasNext()){
        String key = (String)iteratorField.next();
        mutiEntryUnit.getFields().get(key).accept(this);
      }
    }
    if(!mutiEntryUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = mutiEntryUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        mutiEntryUnit.getLinks().get(key).accept(this);
      }
    }
    System.out.println("</MultiEntryUnit>");   		
	}
	public void visit(AutomaticLink automaticLink) {
	  System.out.print("<Link ");
    System.out.print("id=\"");
    System.out.print(automaticLink.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(automaticLink.getName());
    System.out.print("\" ");
    System.out.print("to=\"");
    System.out.print(automaticLink.getTo().getId());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print("automatic");
    System.out.print("\" ");
    System.out.print("validate=\"");
    System.out.print("true");
    if(automaticLink.getAutomaticCoupling()){
      System.out.print("\" ");
      System.out.print("automaticCoupling=\"");
      System.out.print(automaticLink.getAutomaticCoupling().toString());
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
    else{
      System.out.print(">");
      System.out.println("");
      Iterator<ParameterCoupling> iteratorParam = automaticLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
      System.out.print("</Link>");  
    }
	}
	public void visit(KOLink koLink) {
	  System.out.print("<KOLink ");
    System.out.print("id=\"");
    System.out.print(koLink.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(koLink.getName());
    System.out.print("\" ");
    System.out.print("to=\"");
    System.out.print(koLink.getTo().getId());
    System.out.print("\" ");
    System.out.print("validate=\"");
    System.out.print("true");
    if(koLink.getAutomaticCoupling()){
      System.out.print("\" ");
      System.out.print("automaticCoupling=\"");
      System.out.print(koLink.getAutomaticCoupling().toString());
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
    else{
      System.out.print(">");
      System.out.println("");
      Iterator<ParameterCoupling> iteratorParam = koLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
      System.out.print("</KOLink>");  
    }		
	}
	public void visit(OKLink okLink) {
	  System.out.print("<Link ");
    System.out.print("id=\"");
    System.out.print(okLink.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(okLink.getName());
    System.out.print("\" ");
    System.out.print("to=\"");
    System.out.print(okLink.getTo().getId());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print("normal");
    System.out.print("\" ");
    System.out.print("validate=\"");
    System.out.print("true");
    if(okLink.getAutomaticCoupling()){
      System.out.print("\" ");
      System.out.print("automaticCoupling=\"");
      System.out.print(okLink.getAutomaticCoupling().toString());
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
    else{
      System.out.print(">");
      System.out.println("");
      Iterator<ParameterCoupling> iteratorParam = okLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
      System.out.print("</Link>");  
    }     		
	}
	public void visit(NormalLink normalLink) {
	  System.out.print("<Link ");
    System.out.print("id=\"");
    System.out.print(normalLink.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(normalLink.getName());
    System.out.print("\" ");
    System.out.print("to=\"");
    System.out.print(normalLink.getTo().getId());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print("normal");
    System.out.print("\" ");
    System.out.print("validate=\"");
    System.out.print("true");
    if(normalLink.getAutomaticCoupling()){
      System.out.print("\" ");
      System.out.print("automaticCoupling=\"");
      System.out.print(normalLink.getAutomaticCoupling().toString());
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
    else{
      System.out.print(">");
      System.out.println("");
      Iterator<ParameterCoupling> iteratorParam = normalLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
      System.out.print("</Link>");  
    }  		
	}
	public void visit(TransportLink transportLink) {
	  System.out.print("<Link ");
    System.out.print("id=\"");
    System.out.print(transportLink.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(transportLink.getName());
    System.out.print("\" ");
    System.out.print("to=\"");
    System.out.print(transportLink.getTo().getId());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print("transport");
    System.out.print("\" ");
    System.out.print("validate=\"");
    System.out.print("true");
    if(transportLink.getAutomaticCoupling()){
      System.out.print("\" ");
      System.out.print("automaticCoupling=\"");
      System.out.print(transportLink.getAutomaticCoupling().toString());
      System.out.print("\"");
      System.out.print(">");
      System.out.println("");
    }
    else{
      System.out.print(">");
      System.out.println("");
      Iterator<ParameterCoupling> iteratorParam = transportLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
      System.out.print("</Link>");  
    } 		
	}
  public void visit(ParameterCoupling parameterCoupling) {
    System.out.print("<LinkParameter ");
    System.out.print("id=\"");
    System.out.print(parameterCoupling.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(parameterCoupling.getName());
    System.out.print("\" ");
    System.out.print("source=\"");
    System.out.print(parameterCoupling.getSourceParameter().getName());
    System.out.print("\" ");
    System.out.print("target=\"");
    System.out.print(parameterCoupling.getTargetParameter().getName());
    System.out.print("\"");
    System.out.print(">");
    System.out.println("");
  }
  public void visit(NormalField normalField) {
    System.out.print("<Field ");
    System.out.print("id=\"");
    System.out.print(normalField.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(normalField.getName());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print(normalField.getType().getTypeName());
    System.out.print("\" ");
    System.out.print("modifiable=\"");
    System.out.print(normalField.getModifiable());
    System.out.print("\" ");
    System.out.print("preloaded=\"");
    System.out.print(normalField.getPreloaded());
    System.out.print("\"");
    System.out.print(">");
    System.out.println("");
  }
  public void visit(SelectionField selectionField) {
    System.out.print("<SelectionField ");
    System.out.print("id=\"");
    System.out.print(selectionField.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(selectionField.getName());
    System.out.print("\" ");
    System.out.print("type=\"");
    System.out.print(selectionField.getType().getTypeName());
    System.out.print("\"");
    System.out.print(">");
    System.out.println("");
  }
  public void visit(Slot slot) {
    // TODO Auto-generated method stub
  }
  public void visit(Selector selector) {
    System.out.print("<Selector ");
    System.out.print("id=\"");
    System.out.print(selector.getId());
    System.out.print("\" ");
    System.out.print("defaultPolicy=\"");
    System.out.print(selector.getDefaultPolicy());
    System.out.print("\" ");
    System.out.print("booleanOperator=\"");
    System.out.print(selector.getBooleanOperator());
    if(!selector.getKeyConditions().isEmpty())
    {
      System.out.print(">");
      System.out.println(""); 
      Iterator<String> iteratorKCond = selector.getKeyConditions().keySet().iterator();
      while(iteratorKCond.hasNext()){
        String key = (String)iteratorKCond.next();
        selector.getKeyConditions().get(key).accept(this);
      }
      System.out.println("</SelectorUnit>");
    }
    else{
      System.out.print("\"");
      System.out.print(">");
      System.out.println(""); 
    }
  }
  public void visit(KeyCondition keyCondition) {
    System.out.print("<Keycondition ");
    System.out.print("id=\"");
    System.out.print(keyCondition.getId());
    System.out.print("\" ");
    System.out.print("name=\"");
    System.out.print(keyCondition.getName());
    System.out.print("\" ");
    System.out.print("predicate=\"");
    System.out.print(keyCondition.getPredicate());
    System.out.print("\" ");
    System.out.print("implied=\"");
    System.out.print(keyCondition.getImplied().toString());
    System.out.print("\"");
    System.out.print(">");
    System.out.println(""); 
  }
  public void visit(AttributeParameter attributeParameter) {
    System.out.print(attributeParameter.getAttribute().getName());
  }
  public void visit(CurrentOIDParameter currentOIDParameter) {
    System.out.print("current " + currentOIDParameter.getCurrentOID().getName());
  }
  public void visit(KeyConditionParameter keyConditionParameter) {
    System.out.print(keyConditionParameter.getKeyCondition().getName() + " [" + keyConditionParameter.getKeyCondition().getKey().getName() + "]");
  }
  public void visit(NormalFieldParameter normalFieldParameter) {
    System.out.print(normalFieldParameter.getField().getName());
  }
  public void visit(RelationshipParameter relationshipParameter) {
    System.out.print(relationshipParameter.getRelationship().getName());
  }
  public void visit(SelectionFieldLabelParameter selectionFieldParameter) {
    System.out.print(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(SelectionFieldOutputParameter selectionFieldParameter) {
    System.out.print(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(SelectionFieldPreselectionParameter selectionFieldParameter) {
    System.out.print(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(DefaultUnitParameter defaultUnitParameter) {
    System.out.print(defaultUnitParameter.getId());
  }
  public void visit(OutputSelectionFieldParameter outputSelectionFieldParameter) {
    System.out.print(outputSelectionFieldParameter.getSelectionField().getName());
  }
}
