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
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFactory;


/**
 * @author Franco Giacosa
 */
public class SelectableRepetitionSpec2MultiChoiceIU {

  public SelectableRepetitionSpec selectableRepetitionSpec;
  public MultiChoiceIndexUnit multiChoiceIndexUnit;
  
  public SelectableRepetitionSpec2MultiChoiceIU(SelectableRepetitionSpec selectableRepetitionSpec) {
    super();
    this.setSelectableRepetitionSpec(selectableRepetitionSpec);
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
    DMTransformationFacade dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
    DMTransformationFactory dmTransformationFactory = dmTransformationFacade.getDMTransformationFactory();
      
    EntityDecorator entityDecorator = dmTransformationFactory.getEntity(this.getSelectableRepetitionSpec().getClassSpec().getName());    
    this.setMultiChoiceIndexUnit(webFactory.createMultiChoiceIndexUnit(this.getSelectableRepetitionSpec().getWidget().getWidgetId(), entityDecorator));    
  }
 


}
