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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.MultiChoiceIndexUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;


/**
 * @author Franco Giacosa
 */
public class SelectableRepetitionSpec2MultiChoiceIU {

  private SelectableRepetitionSpec selectableRepetitionSpec;
  private MultiChoiceIndexUnit multiChoiceIndexUnit;
  private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;
  
  public SelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    super();
    this.setSelectableRepetitionSpec(selectableRepetitionSpec);
    this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
  }
  
  public MultiChoiceIndexUnit getMultiChoiceIndexUnit() {
    return multiChoiceIndexUnit;
  }
  
  public void setMultiChoiceIndexUnit(MultiChoiceIndexUnit multiChoiceIndexUnit) {
    this.multiChoiceIndexUnit = multiChoiceIndexUnit;
  }
  
  public SelectableRepetitionSpec getSelectableRepetitionSpec() {
    return selectableRepetitionSpec;
  }
  
  public void setSelectableRepetitionSpec(SelectableRepetitionSpec selectableRepetitionSpec) {
    this.selectableRepetitionSpec = selectableRepetitionSpec;
  }

  public void transform(){      
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getSelectableRepetitionSpec().getClassSpec().getName());    
    this.setMultiChoiceIndexUnit(webFactory.createMultiChoiceIndexUnit(this.getSelectableRepetitionSpec().getWidget().getWidgetId(), entityDecorator));    
  }

public void setHypertextSpec2WebMLWebModel(HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
	this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
}

public HypertextSpec2WebMLWebModel getHypertextSpec2WebMLWebModel() {
	return hypertextSpec2WebMLWebModel;
}
 


}
