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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.CreateUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFactory;


/**
 * @author Franco Giacosa
 */
public class SaveActionSpec2CreateUnit {
  
  public SaveActionSpec saveActionSpec;
  public CreateUnit createUnit;

  public SaveActionSpec2CreateUnit(SaveActionSpec saveActionSpec){
    super();
    this.setSaveActionSpec(saveActionSpec);
  }
  
  public void transform(){
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    DMTransformationFacade dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
    DMTransformationFactory dmTransformationFactory = dmTransformationFacade.getDMTransformationFactory();
    
    EntityDecorator entityDecorator = dmTransformationFactory.getEntity(this.getSaveActionSpec().getSpec().getClassSpec().getName());    
    this.setCreateUnit(webFactory.createCreateUnit(this.getSaveActionSpec().getSpec().getClassSpec().getName(), entityDecorator));    
  }

  public SaveActionSpec getSaveActionSpec() {
    return saveActionSpec;
  }
  
  public void setSaveActionSpec(SaveActionSpec saveActionSpec) {
    this.saveActionSpec = saveActionSpec;
  }
    
  public CreateUnit getCreateUnit() {
    return createUnit;
  }

  public void setCreateUnit(CreateUnit createUnit) {
    this.createUnit = createUnit;
  }
}
