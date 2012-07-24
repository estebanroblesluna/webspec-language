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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;
/**
 * @author Franco Giacosa
 */
public class WebModelFactoryImpl implements WebModelFactory{

	
	private WebModelIds webModelIds;

	public WebModelFactoryImpl(WebModelIds webModelIds) {
		super();
		this.webModelIds = webModelIds;
	}
	
	public WebModel createWebModel(SiteView homeSiteView){
		return new WebModel(homeSiteView);
	}
	
	public SiteView createSiteView(String name, Boolean homeSiteView){
		return new SiteView(this.getWebModelIds().getSiteViewId(),name,homeSiteView);
	}
	
	public Page createPage(String name, Boolean home, Boolean landmark){
		return new Page(this.getWebModelIds().getPageId(),name,home,landmark);
	}
	
	public DataUnit createDataUnit(String name, EntityDecorator entity){
		return new DataUnit(this.getWebModelIds().getDataUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}
	
	public EntryUnit createEntryUnit(String name){
		return new EntryUnit(this.getWebModelIds().getEntryUnitId(),name);
	}
	
	public EntryUnit createEntryUnit(String name, EntityDecorator entity){
		EntryUnit entryUnit = new EntryUnit(this.getWebModelIds().getEntryUnitId(),name, entity);
		entryUnit.createFields();
		return entryUnit;
	}
	
	public IndexUnit createIndexUnit(String name, EntityDecorator entity){
		return new IndexUnit(this.getWebModelIds().getIndexUnitId(),name, entity);
	}
	
	public MultiChoiceIndexUnit createMultiChoiceIndexUnit(String name, EntityDecorator entity){
		return new MultiChoiceIndexUnit(this.getWebModelIds().getMultiChoiceIndexUnitId(),name, entity);
	}
	
	public MultiEntryUnit createMultiEntryUnit(String name, EntityDecorator entity){
		MultiEntryUnit multiEntryUnit = new MultiEntryUnit(this.getWebModelIds().getMultiEntryUnitId(),name, entity);
		multiEntryUnit.createFields();
		return multiEntryUnit;
	}
	
	public SelectorUnit createSelectorUnit(String name, EntityDecorator entity){
		return new SelectorUnit(this.getWebModelIds().getSelectorUnitId(),name, entity);
	}
	
	public CreateUnit createCreateUnit(String name, EntityDecorator entity){
		return new CreateUnit(this.getWebModelIds().getCreateUnitId(),name, entity);
	}
	
	public DeleteUnit createDeleteUnit(String name, EntityDecorator entity){
		return new DeleteUnit(this.getWebModelIds().getDeleteUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}
	
	public ModifyUnit createModifyUnit(String name, EntityDecorator entity){
		return new ModifyUnit(this.getWebModelIds().getModifyUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}
	
	public ParameterCoupling createParameterCoupling(String name, Boolean coupling, Boolean passing, Parameter sourceParameter, Parameter targetParameter){
		return new ParameterCoupling(this.getWebModelIds().getParameterCouplingId(),name,coupling,passing,sourceParameter,targetParameter);
	}
	
	public Selector createSelector(AttributeDecorator key){
		Selector selector = new Selector(this.getWebModelIds().getSelectorId(), key);
		selector.addKeyCondition(this.createKeyCondition());
		return selector;
	}
	
	public KeyCondition createKeyCondition(){
		return new KeyCondition(this.getWebModelIds().getKeyConditionId(),this.getWebModelIds().getKeyConditionName());
	}
	
	public AutomaticLink createAutomaticLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
		AutomaticLink link = new AutomaticLink(this.getWebModelIds().getLinkId(),name,automaticCoupling,from,to);
		from.addLink(link);
		return link;
	}
	
	public NormalLink createNormalLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
		NormalLink link = new NormalLink(this.getWebModelIds().getLinkId(),name,automaticCoupling,from,to);
		from.addLink(link);
		return link;
	}
	
	public TransportLink createTransportLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
		TransportLink link = new TransportLink(this.getWebModelIds().getLinkId(),name,automaticCoupling,from,to);
		from.addLink(link);
		return link;
	}
	
	public OKLink createOKLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
		OKLink link = new OKLink(this.getWebModelIds().getLinkId(),name,automaticCoupling,from,to);
		from.addLink(link);
		return link;
	}
	
	public KOLink createKOLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
		KOLink link = new KOLink(this.getWebModelIds().getLinkId(),name,automaticCoupling,from,to);
		from.addLink(link);
		return link;
	}
	
	public NormalField createNormalField(String name, Type type){
		return new NormalField(this.getWebModelIds().getNormalFieldId(), name, type);
	}
	
	public NormalField createNormalField(AttributeDecorator attribute){
		return this.createNormalField(attribute.getName(), attribute.getAttributeType());
	}
	
	public SelectionField createSelectionField(String name, Type type){
		return new SelectionField(this.getWebModelIds().getSelectionFieldId(), name, type);
	}
	
	public AttributeParameter createAttributeParameter(AttributeDecorator attribute){
		return new AttributeParameter(attribute.getId(),attribute);
	}
	
	public CurrentOIDParameter createCurrentOIDParameter(AttributeDecorator attributeKey){
		return new CurrentOIDParameter(attributeKey.getId(),attributeKey);
	}
	
	public DefaultUnitParameter createDefaultUnitParameter(String name, String id){
		return new DefaultUnitParameter(name,id);
	}
	
	public KeyConditionParameter createKeyConditionParameter(KeyCondition keyCondition){
		return new KeyConditionParameter(keyCondition.getId(),keyCondition);
	}
	
	public RelationshipParameter createRelationshipParameter(RelationshipDecorator relationship){
		return new RelationshipParameter(relationship.getId(),relationship);
	}
	
	public NormalFieldParameter createNormalFieldParameter(NormalField normalField){
		return new NormalFieldParameter(normalField.getId(),normalField);
	}
	
	public SelectionFieldLabelParameter createSelectionFieldLabelParameter(String id, String name, SelectionField selectionField){
		return new SelectionFieldLabelParameter(id,name,selectionField);
	}
	
	public SelectionFieldOutputParameter createSelectionFieldOutputParameter(String id, String name, SelectionField selectionField){
		return new SelectionFieldOutputParameter(id,name,selectionField);
	}
	
	public SelectionFieldPreselectionParameter createSelectionFieldPreselectionParameter(String id, String name, SelectionField selectionField){
		return new SelectionFieldPreselectionParameter(id, name,selectionField);
	}
	
	public OutputSelectionFieldParameter createOutputSelectionFieldParameter(SelectionField selectionField){
		return new OutputSelectionFieldParameter(selectionField);
	}
	
	public WebModelIds getWebModelIds() {
		return webModelIds;
	}
	
}
