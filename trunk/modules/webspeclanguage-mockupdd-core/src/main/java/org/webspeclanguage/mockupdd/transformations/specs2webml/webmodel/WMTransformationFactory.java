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

package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;

import java.util.*;


/**
 * @author Franco Giacosa
 */
public interface WMTransformationFactory {
  
  final java.util.List<SelectableRepetitionSpec2MultiChoiceIU> selectableRepetitionSpec2MultiChoiceIUs = new ArrayList<SelectableRepetitionSpec2MultiChoiceIU>();
  final java.util.List<RepetitionClassMappingSpec2IndexUnit> repetitionClassMappingSpec2IndexUnits = new ArrayList<RepetitionClassMappingSpec2IndexUnit>();
  final java.util.List<PanelClassMappingSpec2DataUnit> panelClassMapping2DataUnits = new ArrayList<PanelClassMappingSpec2DataUnit>();
  final java.util.List<SUIPage2Page> suiPage2Pages = new ArrayList<SUIPage2Page>();
  final java.util.List<InputPanelSpec2EntryUnit> inputPanelSpec2EntryUnits = new ArrayList<InputPanelSpec2EntryUnit>();
  final java.util.List<SaveActionSpec2CreateUnit> saveActionSpec2CreateUnits = new ArrayList<SaveActionSpec2CreateUnit>();
  final java.util.List<DeleteActionSpec2DeleteUnit> deleteActionSpec2DeleteUnits = new ArrayList<DeleteActionSpec2DeleteUnit>();
  
  
  RepetitionClassMappingSpec2IndexUnit transformRepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetitionClassMappingSpec);
  PanelClassMappingSpec2DataUnit transformPanelClassMappingSpec2DataUnit(PanelClassMappingSpec panelClassMappingSpec);
  SUIPage2Page transformSUIPage2Page(org.webspeclanguage.mockupdd.sui.model.Page suiPage);
  
  InputPanelSpec2EntryUnit transformInputPanelSpec2EntryUnit(InputPanelSpec inputPanelSpecsuiInputPanel);
  SaveActionSpec2CreateUnit transformSaveActionSpec2CreateUnit(SaveActionSpec saveActionSpec);
  DeleteActionSpec2DeleteUnit transformDeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec);
  SelectableRepetitionSpec2MultiChoiceIU transformSelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec);
  
}
