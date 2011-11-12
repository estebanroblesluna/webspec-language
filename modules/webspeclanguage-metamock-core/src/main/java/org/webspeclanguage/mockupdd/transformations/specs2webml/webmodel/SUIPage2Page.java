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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.sui.model.*;


/**
 * @author Franco Giacosa
 */
public class SUIPage2Page {
  
    private org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page webmlPage;
    private org.webspeclanguage.mockupdd.sui.model.Page suiPage;

    public SUIPage2Page(org.webspeclanguage.mockupdd.sui.model.Page suiPage){
      super();
      this.setSuiPage(suiPage);
    }
    
    public org.webspeclanguage.mockupdd.sui.model.Page getSuiPage() {
      return suiPage;
    }
    
    public void setSuiPage(org.webspeclanguage.mockupdd.sui.model.Page suiPage) {
      this.suiPage = suiPage;
    }
    
    public org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page getWebmlPage() {
      return webmlPage;
    }
    
    public void setWebmlPage(org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page webmlPage) {
      this.webmlPage = webmlPage;
    }
    
}
