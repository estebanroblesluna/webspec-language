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
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFactory;


/**
 * @author Franco Giacosa
 */
public class RepetitionClassMappingSpec2IndexUnit {
  
  private RepetitionClassMappingSpec repetition;
  private IndexUnit indexUnit;
  private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;
  public RepetitionClassMappingSpec2IndexUnit(RepetitionClassMappingSpec repetition, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel){
    super();
    this.setRepetition(repetition);
    this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
  }

  public void transform(){
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    DMTransformationFacade dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
    DMTransformationFactory dmTransformationFactory = dmTransformationFacade.getDMTransformationFactory();
    
    EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getRepetition().getClassSpec().getName());    
    this.setIndexUnit(webFactory.createIndexUnit(this.getRepetition().getWidget().getWidgetId(), entityDecorator));    
  }
  
  public RepetitionClassMappingSpec getRepetition() {
    return repetition;
  }
  
  public void setRepetition(RepetitionClassMappingSpec repetition) {
    this.repetition = repetition;
  }
  
  public IndexUnit getIndexUnit() {
    return indexUnit;
  }
  
  public void setIndexUnit(IndexUnit indexUnit) {
    this.indexUnit = indexUnit;
  }

public void setHypertextSpec2WebMLWebModel(HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
	this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
}

public HypertextSpec2WebMLWebModel getHypertextSpec2WebMLWebModel() {
	return hypertextSpec2WebMLWebModel;
}

}
