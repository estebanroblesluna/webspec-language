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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.IndexUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;


/**
 * @author Franco Giacosa
 */
public class RepetitionClassMappingSpec2IndexUnit implements Spec2ContentUnit {
  
  private RepetitionClassMappingSpec repetition;
  private IndexUnit indexUnit;
  private HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel;
  public RepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetition, HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel){
    super();
    this.setRepetition(repetition);
    this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
  }

  public void transform(){
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getSpec().getClassSpec().getName());    
    this.setIndexUnit(webFactory.createIndexUnit(this.getSpec().getWidget().getWidgetId(), entityDecorator));
    this.getHypertextSpec2WebMLWebModel().addContentUnitToPage(this.getSpec().getWidget(),this.getContentUnit());
  }
  
  public RepetitionClassMappingSpec getSpec() {
    return repetition;
  }
  
  public void setRepetition(RepetitionClassMappingSpec repetition) {
    this.repetition = repetition;
  }
  
  public IndexUnit getContentUnit() {
    return indexUnit;
  }
  
  public void setIndexUnit(IndexUnit indexUnit) {
    this.indexUnit = indexUnit;
  }

public void setHypertextSpec2WebMLWebModel(HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel) {
	this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
}

public HypertextSpecs2WebMLWebModel getHypertextSpec2WebMLWebModel() {
	return hypertextSpec2WebMLWebModel;
}

}
