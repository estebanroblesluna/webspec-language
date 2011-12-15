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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.specs.hypertext.*;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.*;


/**
 * @author Franco Giacosa
 */
public class PanelClassMappingSpec2DataUnit {
  
    private DataUnit dataUnit;
    
    private PanelClassMappingSpec panel;

    public PanelClassMappingSpec2DataUnit(PanelClassMappingSpec panel){
      super();
      this.setPanel(panel);
    }
    
    public PanelClassMappingSpec getPanel() {
      return panel;
    }

    public void setPanel(PanelClassMappingSpec panel) {
      this.panel = panel;
    }
    
    public void transform(){
      WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
      WebModelFactory webFactory = webModelFacade.getWebModelFactory();
      DMTransformationFacade dmTransformationFacade = DMTransformationFacade.getDMTransformationFacade();
      DMTransformationFactory dmTransformationFactory = dmTransformationFacade.getDMTransformationFactory();
      
      EntityDecorator entityDecorator = dmTransformationFactory.getEntity(this.getPanel().getClassSpec().getName());    
      this.setDataUnit(webFactory.createDataUnit(this.getPanel().getWidget().getWidgetId(), entityDecorator));
    }
    
    public DataUnit getDataUnit() {
      return dataUnit;
    }
    
    public void setDataUnit(DataUnit dataUnit) {
      this.dataUnit = dataUnit;
    }

}
