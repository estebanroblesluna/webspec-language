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
	public DataUnit createDataUnit(String name, EntityDecorator entity){
		return new DataUnit(this.getWebModelSingleton().getDataUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}
	public EntryUnit createEntryUnit(String name){
    return new EntryUnit(this.getWebModelSingleton().getEntryUnitId(),name);
  }
	public EntryUnit createEntryUnit(String name, EntityDecorator entity){
		return new EntryUnit(this.getWebModelSingleton().getEntryUnitId(),name, entity);
	}
	public IndexUnit createIndexUnit(String name, EntityDecorator entity){
		return new IndexUnit(this.getWebModelSingleton().getIndexUnitId(),name, entity);
	}
	public MultiChoiceIndexUnit createMultiChoiceIndexUnit(String name, EntityDecorator entity){
		return new MultiChoiceIndexUnit(this.getWebModelSingleton().getMultiChoiceIndexUnitId(),name, entity);
	}
	public MultiEntryUnit createMultiEntryUnit(String name, EntityDecorator entity){
		return new MultiEntryUnit(this.getWebModelSingleton().getMultiEntryUnitId(),name, entity);
	}
	public SelectorUnit createSelectorUnit(String name, EntityDecorator entity){
		return new SelectorUnit(this.getWebModelSingleton().getSelectorUnitId(),name, entity);
	}
	public CreateUnit createCreateUnit(String name, EntityDecorator entity){
		return new CreateUnit(this.getWebModelSingleton().getCreateUnitId(),name, entity);
	}
	public DeleteUnit createDeleteUnit(String name, EntityDecorator entity){
		return new DeleteUnit(this.getWebModelSingleton().getDeleteUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}
	public ModifyUnit createaModifyUnit(String name, EntityDecorator entity){
		return new ModifyUnit(this.getWebModelSingleton().getModifyUnitId(),name, entity, this.createSelector(entity.getEntityKey()));
	}	
	public ParameterCoupling createParameterCoupling(String name, Boolean coupling, Boolean passing, Parameter sourceParameter, Parameter targetParameter){
		return new ParameterCoupling(this.getWebModelSingleton().getParameterCouplingId(),name,coupling,passing,sourceParameter,targetParameter);
	}
	public Selector createSelector(AttributeDecorator key){
	  Selector selector = new Selector(this.getWebModelSingleton().getSelectorId(), key);
	  selector.addKeyCondition(this.createKeyCondition());
	  return selector;
	}
	public KeyCondition createKeyCondition(){
	  return new KeyCondition(this.getWebModelSingleton().getKeyConditionId(),this.getWebModelSingleton().getKeyConditionName());
	}
	public WebModelSingleton getWebModelSingleton() {
		return webModelSingleton;
	}
	public AutomaticLink createAutomaticLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
    return new AutomaticLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,from,to);
  }
  public NormalLink createNormalLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
    return new NormalLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,from,to);
  }
  public TransportLink createTransportLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
    return new TransportLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,from,to);
  }
  public OKLink createOKLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
    return new OKLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,from,to);
  }
  public KOLink createKOLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to){
    return new KOLink(this.getWebModelSingleton().getLinkId(),name,automaticCoupling,from,to);
  }
  public NormalField createNormalField(String name, Type type){
    return new NormalField(this.getWebModelSingleton().getNormalFieldId(), name, type);
  }
  public NormalField createNormalField(AttributeDecorator attribute){
    return this.createNormalField(attribute.getName(), attribute.getAttributeType());
  }
  public SelectionField createSelectionField(String name, Type type){
    return new SelectionField(this.getWebModelSingleton().getSelectionFieldId(), name, type);
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
}
