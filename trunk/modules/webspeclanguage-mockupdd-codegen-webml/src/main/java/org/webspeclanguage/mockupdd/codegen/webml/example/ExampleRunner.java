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

package org.webspeclanguage.mockupdd.codegen.webml.example;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.xmlgeneration.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
/**
 * @author Franco Giacosa
 */
public class ExampleRunner {

  public static void main(String[] args) {
        
    WebMLDataModelExample dmexample = new WebMLDataModelExample();  
    DataModel dm = dmexample.generateDataModel();
    
    WebMLWebModelExample wmexample = new WebMLWebModelExample();  
    WebModel wm = wmexample.generateWebModel(dm);
    
    MainXmlGenerator mainXmlGenerator = new MainXmlGenerator("src/main/java/org/webspeclanguage/mockupdd/codegen/webml/xmlgeneration/webratioprojects");
    mainXmlGenerator.mapModels(dm, wm);
    
  }
}
