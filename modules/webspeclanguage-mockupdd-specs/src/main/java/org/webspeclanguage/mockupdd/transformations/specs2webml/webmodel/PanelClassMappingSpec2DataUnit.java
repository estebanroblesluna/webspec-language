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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.DataUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;


/**
 * @author Franco Giacosa
 */
public class PanelClassMappingSpec2DataUnit implements Spec2ContentUnit{
  
    private DataUnit dataUnit; 
    private PanelClassMappingSpec panel;
    private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;

    public PanelClassMappingSpec2DataUnit(PanelClassMappingSpec panel, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel){
      super();
      this.setPanel(panel);
      this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
    }
    
    public PanelClassMappingSpec getSpec() {
      return panel;
    }

    public void setPanel(PanelClassMappingSpec panel) {
      this.panel = panel;
    }
    
    public void transform(){
      WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
      WebModelFactory webFactory = webModelFacade.getWebModelFactory();
      EntityDecorator entityDecorator = this.getHypertextSpec2WebMLWebModel().getDataSpecs2WebMLDataModel().getEntity(this.getSpec().getClassSpec().getName());    
      this.setDataUnit(webFactory.createDataUnit(this.getSpec().getWidget().getWidgetId(), entityDecorator));
      this.getHypertextSpec2WebMLWebModel().addContentUnitToPage(this.getSpec().getWidget(),this.getContentUnit());
    }
    
    public DataUnit getContentUnit() {
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
