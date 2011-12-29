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
    private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;

    public PanelClassMappingSpec2DataUnit(PanelClassMappingSpec panel, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel){
      super();
      this.setPanel(panel);
      this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
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
      EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getPanel().getClassSpec().getName());    
      this.setDataUnit(webFactory.createDataUnit(this.getPanel().getWidget().getWidgetId(), entityDecorator));
    }
    
    public DataUnit getDataUnit() {
      return dataUnit;
    }
    
    public void setDataUnit(DataUnit dataUnit) {
      this.dataUnit = dataUnit;
    }

	public void setHypertextSpec2WebMLWebModel(
			HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
		this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
	}

	public HypertextSpec2WebMLWebModel getHypertextSpec2WebMLWebModel() {
		return hypertextSpec2WebMLWebModel;
	}

}
