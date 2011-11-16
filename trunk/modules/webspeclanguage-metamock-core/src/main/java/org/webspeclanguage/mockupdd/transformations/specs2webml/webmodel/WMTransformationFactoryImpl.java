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

import org.webspeclanguage.mockupdd.specs.hypertext.*;


/**
 * @author Franco Giacosa
 */
public class WMTransformationFactoryImpl implements WMTransformationFactory {



  public RepetitionClassMappingSpec2IndexUnit transformRepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetitionClassMappingSpec) {
    RepetitionClassMappingSpec2IndexUnit suiRepetition2IndexUnit = new RepetitionClassMappingSpec2IndexUnit(repetitionClassMappingSpec);
    suiRepetition2IndexUnit.transform();
    WMTransformationFactory.repetitionClassMappingSpec2IndexUnits.add(suiRepetition2IndexUnit);
    return suiRepetition2IndexUnit;
      
  }

  public PanelClassMappingSpec2DataUnit transformPanelClassMappingSpec2DataUnit(PanelClassMappingSpec suiPanel) {
    PanelClassMappingSpec2DataUnit suiPanel2DataUnit = new PanelClassMappingSpec2DataUnit(suiPanel);
    suiPanel2DataUnit.transform();
    WMTransformationFactory.panelClassMapping2DataUnits.add(suiPanel2DataUnit);
    return suiPanel2DataUnit;
  }

  public SUIPage2Page transformSUIPage2Page(org.webspeclanguage.mockupdd.sui.model.Page suiPage) {
    
    SUIPage2Page suiPage2Page = new SUIPage2Page(suiPage);
    suiPage2Page.transform();
    WMTransformationFactory.suiPage2Pages.add(suiPage2Page);
    return suiPage2Page;
    
  }


  public SaveActionSpec2CreateUnit transformSaveActionSpec2CreateUnit(SaveActionSpec saveActionSpec) {
    
    SaveActionSpec2CreateUnit saveActionSpec2CreateUnit = new SaveActionSpec2CreateUnit(saveActionSpec);
    saveActionSpec2CreateUnit.transform();
    WMTransformationFactory.saveActionSpec2CreateUnits.add(saveActionSpec2CreateUnit);
    return saveActionSpec2CreateUnit;
    
  }

  public DeleteActionSpec2DeleteUnit transformDeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec) {

    DeleteActionSpec2DeleteUnit deleteActionSpec2DeleteUnit = new DeleteActionSpec2DeleteUnit(deleteActionSpec);
    deleteActionSpec2DeleteUnit.transform();
    WMTransformationFactory.deleteActionSpec2DeleteUnits.add(deleteActionSpec2DeleteUnit);
    return deleteActionSpec2DeleteUnit;
    
  }
  
  public InputPanelSpec2EntryUnit transformInputPanelSpec2EntryUnit(InputPanelSpec inputPanelSpec) {
    
    InputPanelSpec2EntryUnit suiInputPanel2EntryUnit = new InputPanelSpec2EntryUnit(inputPanelSpec);
    suiInputPanel2EntryUnit.transform();
    WMTransformationFactory.inputPanelSpec2EntryUnits.add(suiInputPanel2EntryUnit);
    return suiInputPanel2EntryUnit;
    
  }
  
  public SelectableRepetitionSpec2MultiChoiceIU transformSelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec) {
    SelectableRepetitionSpec2MultiChoiceIU selectableRepetitionSpec2MultiChoiceIU = new SelectableRepetitionSpec2MultiChoiceIU(selectableRepetitionSpec);
    selectableRepetitionSpec2MultiChoiceIU.transform();
    WMTransformationFactory.selectableRepetitionSpec2MultiChoiceIUs.add(selectableRepetitionSpec2MultiChoiceIU);
    return selectableRepetitionSpec2MultiChoiceIU;
  }

}
