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
		
		webModel.getLocale().accept(this);
		
		Iterator<String> iteratorSV = webModel.getSiteViews().keySet().iterator();
		while(iteratorSV.hasNext()){
			String key = (String)iteratorSV.next();
			webModel.getSiteViews().get(key).accept(this);
		}
	}
	public void visit(Locale locale) {
		Iterator<String> iteratorPa = locale.getPatternsConfiguration().keySet().iterator();
		while(iteratorPa.hasNext()){
			String key = (String)iteratorPa.next();
			locale.getPatternsConfiguration().get(key).accept(this);
		}
	}
	public void visit(PatternConfigurationBoolean patternConfiguration) {
	}
	public void visit(PatternConfigurationDecimal patternConfiguration) {
	}
	public void visit(PatternConfigurationDate patternConfiguration) {
	}
	public void visit(PatternConfigurationFloat patternConfiguration) {
	}
	public void visit(PatternConfigurationInteger patternConfiguration) {
	}
	public void visit(PatternConfigurationTime patternConfiguration) {
	}
	public void visit(PatternConfigurationTimeStamp patternConfiguration) {
	}
	public void visit(SiteView siteView) {

		if(!siteView.getOperationUnits().isEmpty()){
			Iterator<String> iteratorOpUnit = siteView.getOperationUnits().keySet().iterator();
			while(iteratorOpUnit.hasNext()){
				String key = (String)iteratorOpUnit.next();
				siteView.getOperationUnits().get(key).accept(this);
			}
		}
    if(!siteView.getPages().isEmpty()){

      Iterator<String> iteratorPage = siteView.getPages().keySet().iterator();
      while(iteratorPage.hasNext()){
        String key = (String)iteratorPage.next();
        siteView.getPages().get(key).accept(this);
      }
    }
	}
	public void visit(Page page) {
				
		if(!page.getContentUnits().isEmpty()){
			Iterator<String> iteratorContentUnit = page.getContentUnits().keySet().iterator();
			while(iteratorContentUnit.hasNext()){
				String key = (String)iteratorContentUnit.next();
				page.getContentUnits().get(key).accept(this);
			}
		}
		if(!page.getLinks().isEmpty())
		{
			Iterator<String> iteratorLink = page.getLinks().keySet().iterator();
			while(iteratorLink.hasNext()){
				String key = (String)iteratorLink.next();
				page.getLinks().get(key).accept(this);
			}
		}
	}
	public void visit(CreateUnit createUnit) {

    if(!createUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = createUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        createUnit.getLinks().get(key).accept(this);
      }
    }
	}
  public void visit(IndexUnit indexUnit) {

    if(!indexUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = indexUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        indexUnit.getLinks().get(key).accept(this);
      }
    }
  }
  public void visit(MultiChoiceIndexUnit multiChoiceIndexUnit) {

    if(!multiChoiceIndexUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = multiChoiceIndexUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        multiChoiceIndexUnit.getLinks().get(key).accept(this);
      }
    }
  }
  public void visit(SelectorUnit selectorUnit) {

    if(!selectorUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = selectorUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        selectorUnit.getLinks().get(key).accept(this);
      }
    }
  }
  public void visit(DataUnit dataUnit) {

    dataUnit.getSelector().accept(this);
    if(!dataUnit.getLinks().isEmpty())
    { 
      Iterator<String> iteratorLink = dataUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        dataUnit.getLinks().get(key).accept(this);
      }
    }
	}
	public void visit(DeleteUnit deleteUnit) {
	 
    deleteUnit.getSelector().accept(this);
    if(!deleteUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = deleteUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        deleteUnit.getLinks().get(key).accept(this);
      }
    }
	}
	public void visit(ModifyUnit modifyUnit) {
	 
    modifyUnit.getSelector().accept(this);
    if(!modifyUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = modifyUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        modifyUnit.getLinks().get(key).accept(this);
      }
    }
  }
	public void visit(EntryUnit entryUnit) {
	    
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
   
	}
	public void visit(MultiEntryUnit multiEntryUnit) {

    if(!multiEntryUnit.getFields().isEmpty())
    {
      Iterator<String> iteratorField = multiEntryUnit.getFields().keySet().iterator();
      while(iteratorField.hasNext()){
        String key = (String)iteratorField.next();
        multiEntryUnit.getFields().get(key).accept(this);
      }
    }
    if(!multiEntryUnit.getLinks().isEmpty())
    {
      Iterator<String> iteratorLink = multiEntryUnit.getLinks().keySet().iterator();
      while(iteratorLink.hasNext()){
        String key = (String)iteratorLink.next();
        multiEntryUnit.getLinks().get(key).accept(this);
      }
    }      		
	}
	public void visit(AutomaticLink automaticLink) {

    if(!automaticLink.getAutomaticCoupling()){
      Iterator<ParameterCoupling> iteratorParam = automaticLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
    }
	}
	public void visit(KOLink koLink) {
	 
    if(!koLink.getAutomaticCoupling()){    
      Iterator<ParameterCoupling> iteratorParam = koLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
    }		
	}
	public void visit(OKLink okLink) {

    if(!okLink.getAutomaticCoupling()){
      Iterator<ParameterCoupling> iteratorParam = okLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
    }     		
	}
	public void visit(NormalLink normalLink) {
	 
    if(!normalLink.getAutomaticCoupling()){
      Iterator<ParameterCoupling> iteratorParam = normalLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
    }  		
	}
	public void visit(TransportLink transportLink) {
	
    if(!transportLink.getAutomaticCoupling()){
      Iterator<ParameterCoupling> iteratorParam = transportLink.getParameterCoupling().iterator();
      while(iteratorParam.hasNext()){
        ParameterCoupling parameterCoupling = (ParameterCoupling)iteratorParam.next();
        parameterCoupling.accept(this);
      }
    } 		
	}
  public void visit(ParameterCoupling parameterCoupling) {
    
  }
  public void visit(NormalField normalField) {
  
  }
  public void visit(SelectionField selectionField) {
    
  }
  public void visit(Slot slot) {
  }
  public void visit(Selector selector) {
   
    if(!selector.getKeyConditions().isEmpty())
    {
      Iterator<String> iteratorKCond = selector.getKeyConditions().keySet().iterator();
      while(iteratorKCond.hasNext()){
        String key = (String)iteratorKCond.next();
        selector.getKeyConditions().get(key).accept(this);
      }
    }
  }
  public void visit(KeyCondition keyCondition) {
  }
  public void visit(AttributeParameter attributeParameter) {
  }
  public void visit(CurrentOIDParameter currentOIDParameter) {
  }
  public void visit(KeyConditionParameter keyConditionParameter) {
  }
  public void visit(NormalFieldParameter normalFieldParameter) {
  }
  public void visit(RelationshipParameter relationshipParameter) {
  }
  public void visit(SelectionFieldLabelParameter selectionFieldParameter) {
  }
  public void visit(SelectionFieldOutputParameter selectionFieldParameter) {
  }
  public void visit(SelectionFieldPreselectionParameter selectionFieldParameter) {
  }
  public void visit(DefaultUnitParameter defaultUnitParameter) {
  }
  public void visit(OutputSelectionFieldParameter outputSelectionFieldParameter) {
  }
}

