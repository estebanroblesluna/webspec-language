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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DeleteUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFacade;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DMTransformationFactory;


/**
 * @author Franco Giacosa
 */
public class DeleteActionSpec2DeleteUnit {

  public DeleteActionSpec deleteActionSpec;
  public DeleteUnit deleteUnit;

  public DeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec) {
    super();
    this.deleteActionSpec = deleteActionSpec;
  }

  public void transform(){
    
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    DMTransformationFacade dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
    DMTransformationFactory dmTransformationFactory = dmTransformationFacade.getDMTransformationFactory();
    
    EntityDecorator entityDecorator = dmTransformationFactory.getEntity(this.getDeleteActionSpec().getSpec().getClassSpec().getName());    
    this.setDeleteUnit(webFactory.createDeleteUnit(this.getDeleteActionSpec().getSpec().getClassSpec().getName(), entityDecorator));  
  }

  public DeleteActionSpec getDeleteActionSpec() {
    return deleteActionSpec;
  }
  
  public void setDeleteActionSpec(DeleteActionSpec deleteActionSpec) {
    this.deleteActionSpec = deleteActionSpec;
  }
  
  public DeleteUnit getDeleteUnit() {
    return deleteUnit;
  }

  public void setDeleteUnit(DeleteUnit deleteUnit) {
    this.deleteUnit = deleteUnit;
  }
}
