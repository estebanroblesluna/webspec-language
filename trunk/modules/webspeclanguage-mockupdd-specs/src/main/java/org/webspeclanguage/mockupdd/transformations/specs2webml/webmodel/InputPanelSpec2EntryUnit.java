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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.EntryUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;


/**
 * @author Franco Giacosa
 */
public class InputPanelSpec2EntryUnit {
  
  private InputPanelSpec inputPanelSpec;
  private EntryUnit entryUnit;
  private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;
  public InputPanelSpec2EntryUnit(InputPanelSpec inputPanelSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
    super();
    this.setInputPanelSpec(inputPanelSpec);
    this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
  }

  public void transform(){
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getInputPanelSpec().getClassSpec().getName());    
    this.setEntryUnit(webFactory.createEntryUnit(this.getInputPanelSpec().getWidget().getWidgetId(), entityDecorator));
  }

  public InputPanelSpec getInputPanelSpec() {
    return inputPanelSpec;
  }

  public void setInputPanelSpec(InputPanelSpec inputPanelSpec) {
    this.inputPanelSpec = inputPanelSpec;
  }
  
  public EntryUnit getEntryUnit() {
    return entryUnit;
  }
  
  public void setEntryUnit(EntryUnit entryUnit) {
    this.entryUnit = entryUnit;
  }

public void setHypertextSpec2WebMLWebModel(HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
	this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
}

public HypertextSpec2WebMLWebModel getHypertextSpec2WebMLWebModel() {
	return hypertextSpec2WebMLWebModel;
}
  
}
