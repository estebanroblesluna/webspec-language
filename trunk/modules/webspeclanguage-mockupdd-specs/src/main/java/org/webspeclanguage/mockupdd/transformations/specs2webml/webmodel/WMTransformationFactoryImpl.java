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

import java.util.ArrayList;
import java.util.Iterator;

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
public class WMTransformationFactoryImpl implements WMTransformationFactory {

  public NavigationSpec2NavigationPUnitToPUnit transformNavigationSpec2NavigationPUnitToPUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel){
	  
	  NavigationSpec2NavigationPUnitToPUnit navigationSpec2NavigationPUnitToPUnit = new NavigationSpec2NavigationPUnitToPUnit(navigationSpec,hypertextSpec2WebMLWebModel);
	  navigationSpec2NavigationPUnitToPUnit.transform();
	  return navigationSpec2NavigationPUnitToPUnit;
  }
  
  public NavigationSpec2IntraNavigationUnitToUnit transformNavigationSpec2IntraNavigationUnitToUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
	  
	  NavigationSpec2IntraNavigationUnitToUnit navigationSpec2IntraNavigationUnitToUnit = new NavigationSpec2IntraNavigationUnitToUnit(navigationSpec,hypertextSpec2WebMLWebModel);
	  navigationSpec2IntraNavigationUnitToUnit.transform();
	  return navigationSpec2IntraNavigationUnitToUnit;	  
  }
  public NavigationSpec2NavigationPageToPage transformNavigationSpec2NavigationPageToPage(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel){
	  
	  NavigationSpec2NavigationPageToPage navigationSpec2NavigationPageToPage = new NavigationSpec2NavigationPageToPage(navigationSpec,hypertextSpec2WebMLWebModel);
	  navigationSpec2NavigationPageToPage.transform();
	  return navigationSpec2NavigationPageToPage;	  
  }
	
  public RepetitionClassMappingSpec2IndexUnit transformRepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetitionClassMappingSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    
    RepetitionClassMappingSpec2IndexUnit suiRepetition2IndexUnit = new RepetitionClassMappingSpec2IndexUnit(repetitionClassMappingSpec,hypertextSpec2WebMLWebModel);
    suiRepetition2IndexUnit.transform();
    return suiRepetition2IndexUnit;   
  }

  public PanelClassMappingSpec2DataUnit transformPanelClassMappingSpec2DataUnit(PanelClassMappingSpec suiPanel, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {

    PanelClassMappingSpec2DataUnit suiPanel2DataUnit = new PanelClassMappingSpec2DataUnit(suiPanel,hypertextSpec2WebMLWebModel);
    suiPanel2DataUnit.transform();
    return suiPanel2DataUnit;
  }

  public SUIPage2Page transformSUIPage2Page(org.webspeclanguage.mockupdd.sui.model.Page suiPage, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    
    SUIPage2Page suiPage2Page = new SUIPage2Page(suiPage,hypertextSpec2WebMLWebModel);
    suiPage2Page.transform();
    return suiPage2Page;
  }

  public SaveActionSpec2CreateUnit transformSaveActionSpec2CreateUnit(SaveActionSpec saveActionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    
    SaveActionSpec2CreateUnit saveActionSpec2CreateUnit = new SaveActionSpec2CreateUnit(saveActionSpec,hypertextSpec2WebMLWebModel);
    saveActionSpec2CreateUnit.transform();
    return saveActionSpec2CreateUnit;
  }

  public DeleteActionSpec2DeleteUnit transformDeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {

    DeleteActionSpec2DeleteUnit deleteActionSpec2DeleteUnit = new DeleteActionSpec2DeleteUnit(deleteActionSpec,hypertextSpec2WebMLWebModel);
    deleteActionSpec2DeleteUnit.transform();
    return deleteActionSpec2DeleteUnit;
  }
  
  public InputPanelSpec2EntryUnit transformInputPanelSpec2EntryUnit(InputPanelSpec inputPanelSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    
    InputPanelSpec2EntryUnit suiInputPanel2EntryUnit = new InputPanelSpec2EntryUnit(inputPanelSpec,hypertextSpec2WebMLWebModel);
    suiInputPanel2EntryUnit.transform();
    return suiInputPanel2EntryUnit;
  }
  
  public SelectableRepetitionSpec2MultiChoiceIU transformSelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    
    SelectableRepetitionSpec2MultiChoiceIU selectableRepetitionSpec2MultiChoiceIU = new SelectableRepetitionSpec2MultiChoiceIU(selectableRepetitionSpec,hypertextSpec2WebMLWebModel);
    selectableRepetitionSpec2MultiChoiceIU.transform();
    this.getSelectableRepetitionSpec2MultiChoiceIUs().add(selectableRepetitionSpec2MultiChoiceIU);
    return selectableRepetitionSpec2MultiChoiceIU;
  }

}
