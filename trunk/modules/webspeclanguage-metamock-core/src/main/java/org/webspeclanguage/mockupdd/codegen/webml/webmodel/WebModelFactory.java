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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.AttributeDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.AttributeParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.CurrentOIDParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.DefaultUnitParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.KeyConditionParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.NormalFieldParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.OutputSelectionFieldParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.ParameterCoupling;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.RelationshipParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.SelectionFieldLabelParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.SelectionFieldOutputParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.SelectionFieldPreselectionParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.AutomaticLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.KOLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.OKLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.TransportLink;
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


/**
 * @author Franco Giacosa
 */
public interface WebModelFactory {
  public abstract WebModel createWebModel(SiteView homeSiteView);
  public abstract SiteView createSiteView(String name, Boolean homeSiteView);
  public abstract Page createPage(String name, Boolean home, Boolean landmark);
  public abstract DataUnit createDataUnit(String name, EntityDecorator entity);
  public abstract EntryUnit createEntryUnit(String name);
  public abstract EntryUnit createEntryUnit(String name, EntityDecorator entity);
  public abstract IndexUnit createIndexUnit(String name, EntityDecorator entity);
  public abstract MultiChoiceIndexUnit createMultiChoiceIndexUnit(String name, EntityDecorator entity);
  public abstract MultiEntryUnit createMultiEntryUnit(String name, EntityDecorator entity);
  public abstract SelectorUnit createSelectorUnit(String name, EntityDecorator entity);
  public abstract CreateUnit createCreateUnit(String name, EntityDecorator entity);
  public abstract DeleteUnit createDeleteUnit(String name, EntityDecorator entity);
  public abstract ModifyUnit createModifyUnit(String name, EntityDecorator entity);
  public abstract ParameterCoupling createParameterCoupling(String name, Boolean coupling, Boolean passing, Parameter sourceParameter, Parameter targetParameter);
  public abstract Selector createSelector(AttributeDecorator key);
  public abstract KeyCondition createKeyCondition();
  public abstract WebModelIds getWebModelIds();
  public abstract AutomaticLink createAutomaticLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  public abstract NormalLink createNormalLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  public abstract TransportLink createTransportLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  public abstract OKLink createOKLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  public abstract KOLink createKOLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  public abstract NormalField createNormalField(String name, Type type);
  public abstract NormalField createNormalField(AttributeDecorator attribute);
  public abstract SelectionField createSelectionField(String name, Type type);
  public abstract AttributeParameter createAttributeParameter(AttributeDecorator attribute);
  public abstract CurrentOIDParameter createCurrentOIDParameter(AttributeDecorator attributeKey);
  public abstract DefaultUnitParameter createDefaultUnitParameter(String name, String id);
  public abstract KeyConditionParameter createKeyConditionParameter(KeyCondition keyCondition);
  public abstract RelationshipParameter createRelationshipParameter(RelationshipDecorator relationship);
  public abstract NormalFieldParameter createNormalFieldParameter(NormalField normalField);
  public abstract SelectionFieldLabelParameter createSelectionFieldLabelParameter(String id, String name, SelectionField selectionField);
  public abstract SelectionFieldOutputParameter createSelectionFieldOutputParameter(String id, String name, SelectionField selectionField);
  public abstract SelectionFieldPreselectionParameter createSelectionFieldPreselectionParameter(String id, String name, SelectionField selectionField);
  public abstract OutputSelectionFieldParameter createOutputSelectionFieldParameter(SelectionField selectionField);
   
}
