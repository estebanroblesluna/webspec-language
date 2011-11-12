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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.specs.hypertext.*;



/**
 * @author Franco Giacosa
 */
public class SUIPanel2DataUnit {
  
    private DataUnit dataUnit;
    
    private ClassMappingSpec panel;

    public SUIPanel2DataUnit(ClassMappingSpec panel){
      super();
      this.setPanel(panel);
    }
    
    public ClassMappingSpec getPanel() {
      return panel;
    }

    public void setPanel(ClassMappingSpec panel) {
      this.panel = panel;
    }
    
    public void transform(){
      
      WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
      WebModelFactory webFactory = webModelFacade.getWebModelFactory();
      
     // this.setDataUnit(webFactory.)
      
    }
    
    public DataUnit getDataUnit() {
      return dataUnit;
    }
    
    public void setDataUnit(DataUnit dataUnit) {
      this.dataUnit = dataUnit;
    }

}
