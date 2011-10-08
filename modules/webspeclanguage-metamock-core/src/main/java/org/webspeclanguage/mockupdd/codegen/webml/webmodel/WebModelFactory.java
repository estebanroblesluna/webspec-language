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
  WebModel createWebModel(SiteView homeSiteView);
  SiteView createSiteView(String name, Boolean homeSiteView);
  Page createPage(String name, Boolean home, Boolean landmark);
  DataUnit createDataUnit(String name, EntityDecorator entity);
  EntryUnit createEntryUnit(String name);
  EntryUnit createEntryUnit(String name, EntityDecorator entity);
  IndexUnit createIndexUnit(String name, EntityDecorator entity);
  MultiChoiceIndexUnit createMultiChoiceIndexUnit(String name, EntityDecorator entity);
  MultiEntryUnit createMultiEntryUnit(String name, EntityDecorator entity);
  SelectorUnit createSelectorUnit(String name, EntityDecorator entity);
  CreateUnit createCreateUnit(String name, EntityDecorator entity);
  DeleteUnit createDeleteUnit(String name, EntityDecorator entity);
  ModifyUnit createModifyUnit(String name, EntityDecorator entity);
  ParameterCoupling createParameterCoupling(String name, Boolean coupling, Boolean passing, Parameter sourceParameter, Parameter targetParameter);
  Selector createSelector(AttributeDecorator key);
  KeyCondition createKeyCondition();
  WebModelIds getWebModelIds();
  AutomaticLink createAutomaticLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  NormalLink createNormalLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  TransportLink createTransportLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  OKLink createOKLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  KOLink createKOLink(String name, Boolean automaticCoupling, LinkElement from, LinkElement to);
  NormalField createNormalField(String name, Type type);
  NormalField createNormalField(AttributeDecorator attribute);
  SelectionField createSelectionField(String name, Type type);
  AttributeParameter createAttributeParameter(AttributeDecorator attribute);
  CurrentOIDParameter createCurrentOIDParameter(AttributeDecorator attributeKey);
  DefaultUnitParameter createDefaultUnitParameter(String name, String id);
  KeyConditionParameter createKeyConditionParameter(KeyCondition keyCondition);
  RelationshipParameter createRelationshipParameter(RelationshipDecorator relationship);
  NormalFieldParameter createNormalFieldParameter(NormalField normalField);
  SelectionFieldLabelParameter createSelectionFieldLabelParameter(String id, String name, SelectionField selectionField);
  SelectionFieldOutputParameter createSelectionFieldOutputParameter(String id, String name, SelectionField selectionField);
  SelectionFieldPreselectionParameter createSelectionFieldPreselectionParameter(String id, String name, SelectionField selectionField);
  OutputSelectionFieldParameter createOutputSelectionFieldParameter(SelectionField selectionField);
  
}
