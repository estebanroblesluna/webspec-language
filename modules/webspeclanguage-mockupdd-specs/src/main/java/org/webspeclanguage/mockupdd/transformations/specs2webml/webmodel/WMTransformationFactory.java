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
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;


/**
 * @author Franco Giacosa
 */
public interface WMTransformationFactory {
   
  RepetitionClassMappingSpec2IndexUnit transformRepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetitionClassMappingSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  PanelClassMappingSpec2DataUnit transformPanelClassMappingSpec2DataUnit(PanelClassMappingSpec panelClassMappingSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  SUIPage2Page transformSUIPage2Page(org.webspeclanguage.mockupdd.sui.model.Page suiPage, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);  
  InputPanelSpec2EntryUnit transformInputPanelSpec2EntryUnit(InputPanelSpec inputPanelSpecsuiInputPanel, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  SaveActionSpec2CreateUnit transformSaveActionSpec2CreateUnit(SaveActionSpec saveActionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  DeleteActionSpec2DeleteUnit transformDeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  SelectableRepetitionSpec2MultiChoiceIU transformSelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  NavigationSpec2NavigationPageToPage transformNavigationSpec2NavigationPageToPage(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  NavigationSpec2IntraNavigationUnitToUnit  transformNavigationSpec2IntraNavigationUnitToUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);
  NavigationSpec2NavigationPUnitToPUnit transformNavigationSpec2NavigationPUnitToPUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel);

}
