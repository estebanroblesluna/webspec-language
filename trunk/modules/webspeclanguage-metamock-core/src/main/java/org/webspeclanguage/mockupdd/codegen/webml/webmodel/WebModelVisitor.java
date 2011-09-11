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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public interface WebModelVisitor {

	void visit(WebModel webModel);
	void visit(Locale locale);
	void visit(PatternConfigurationBoolean patternConfiguration);
	void visit(PatternConfigurationDate patternConfiguration);
	void visit(PatternConfigurationDecimal patternConfiguration);
	void visit(PatternConfigurationFloat patternConfiguration);
	void visit(PatternConfigurationInteger patternConfiguration);
	void visit(PatternConfigurationTime patternConfiguration);
	void visit(PatternConfigurationTimeStamp patternConfiguration);
	void visit(SiteView siteView);
	void visit(Page page);
	void visit(CreateUnit createUnit);
	void visit(DataUnit dataUnit);
	void visit(DeleteUnit deleteUnit);
	void visit(EntryUnit entryUnit);
	void visit(IndexUnit indexUnit);
	void visit(ModifyUnit modifyUnit);
	void visit(MultiChoiceIndexUnit multiChoiceIndexUnit);
	void visit(MultiEntryUnit mutiEntryUnit);
	void visit(SelectorUnit selectorUnit);
	void visit(KeyCondition keyCondition);
	void visit(AutomaticLink automaticLink);
	void visit(KOLink koLink);
	void visit(OKLink okLink);
	void visit(NormalLink normalLink);
	void visit(SelectionField selectionField);
	void visit(TransportLink transportLink);
	void visit(ParameterCoupling parameterCoupling);
	void visit(NormalField normalField);
	void visit(SelectionFieldLabelParameter selectionField);
  void visit(SelectionFieldOutputParameter selectionField);
	void visit(SelectionFieldPreselectionParameter selectionField);
	void visit(Slot slot);
	void visit(Selector selector);
	void visit(AttributeParameter attributeParameter);
  void visit(CurrentOIDParameter currentOIDParameter);
  void visit(KeyConditionParameter keyConditionParameter);
  void visit(NormalFieldParameter normalFieldParameter);
  void visit(RelationshipParameter relationshipParameter);
  void visit(DefaultUnitParameter defaultUnitParameter);
  void visit(OutputSelectionFieldParameter outputSelectionFieldParameter);

}
