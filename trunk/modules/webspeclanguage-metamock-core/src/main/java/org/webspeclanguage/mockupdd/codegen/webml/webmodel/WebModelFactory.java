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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;

/**
 * @author Franco Giacosa
 */
public class WebModelFactory {

	private WebModelSingleton webModelSingleton = WebModelSingleton.getWebModelSingleton();

	public WebModel createWebModel(SiteView homeSiteView){
		return new WebModel(homeSiteView);
	}
	public SiteView createSiteView(String name, Boolean homeSiteView){
		return new SiteView(this.getWebModelSingleton().getSiteViewId(),name,homeSiteView);
	}
	public Page createPage(String name, Boolean home, Boolean landmark){
		return new Page(this.getWebModelSingleton().getPageId(),name,home,landmark);
	}
	public AutomaticLink createAutomaticLink(String name, Boolean automaticCoupling, Unit targetUnit, Unit sourceUnit){
		return new AutomaticLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetUnit,sourceUnit);
	}
	public NormalLink createNormalLink(String name, Boolean automaticCoupling, Unit targetUnit, Unit sourceUnit){
		return new NormalLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetUnit,sourceUnit);
	}
	public TransportLink createTransportLink(String name, Boolean automaticCoupling, Unit targetUnit, Unit sourceUnit){
		return new TransportLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetUnit,sourceUnit);
	}
	public OKLink createOKLink(String name, Boolean automaticCoupling, Unit targetUnit, Unit sourceUnit){
		return new OKLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetUnit,sourceUnit);
	}
	public KOLink createKOLink(String name, Boolean automaticCoupling, Unit targetUnit, Unit sourceUnit){
		return new KOLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetUnit,sourceUnit);
	}
	public NonContextualLink createNonContextualLink(String name, Boolean automaticCoupling, Page targetPage,	Page sourcePage){
		return new NonContextualLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,targetPage,sourcePage);
	}
	public DataUnit createDataUnit(String name, EntityFacade entity){
		return new DataUnit(this.getWebModelSingleton().getDataUnitId(),name, entity);
	}
	public EntryUnit createEntryUnit(String name, EntityFacade entity){
		return new EntryUnit(this.getWebModelSingleton().getEntryUnitId(),name, entity);
	}
	public IndexUnit createIndexUnit(String name, EntityFacade entity){
		return new IndexUnit(this.getWebModelSingleton().getIndexUnitId(),name, entity);
	}
	public MultiChoiceIndexUnit createMultiChoiceIndexUnit(String name, EntityFacade entity){
		return new MultiChoiceIndexUnit(this.getWebModelSingleton().getMultiChoiceIndexUnitId(),name, entity);
	}
	public MultiEntryUnit createMultiEntryUnit(String name, EntityFacade entity){
		return new MultiEntryUnit(this.getWebModelSingleton().getMultiEntryUnitId(),name, entity);
	}
	public SelectorUnit createSelectorUnit(String name, EntityFacade entity){
		return new SelectorUnit(this.getWebModelSingleton().getSelectorUnitId(),name, entity);
	}
	public CreateUnit createCreateUnit(String name, EntityFacade entity){
		return new CreateUnit(this.getWebModelSingleton().getCreateUnitId(),name, entity);
	}
	public DeleteUnit createDeleteUnit(String name, EntityFacade entity){
		return new DeleteUnit(this.getWebModelSingleton().getDeleteUnitId(),name, entity);
	}
	public ModifyUnit createaModifyUnit(String name, EntityFacade entity){
		return new ModifyUnit(this.getWebModelSingleton().getModifyUnitId(),name, entity);
	}	
	public ParameterCoupling createParameterCoupling(String name, Boolean coupling, Boolean passing, Parameter sourceParameter, Parameter targetParameter){
		return new ParameterCoupling(this.getWebModelSingleton().getParameterCouplingId(),name,coupling,passing,sourceParameter,targetParameter);
	}
	public WebModelSingleton getWebModelSingleton() {
		return webModelSingleton;
	}
	
	
}
