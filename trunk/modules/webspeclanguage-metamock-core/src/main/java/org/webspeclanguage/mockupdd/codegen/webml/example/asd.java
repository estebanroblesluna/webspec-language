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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public class asd {

  public static void main(String[] args) {
      WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
      
      WebModelFacade webModelFacade2 = WebModelFacade.getWebModelFacade();
      if(webModelFacade.equals(webModelFacade2))
        System.out.print(webModelFacade2.getClass().toString());
      
      WebModelFactory factory = webModelFacade2.getWebModelFactory();
      
      if(factory != null){
        System.out.print(webModelFacade2.getClass().toString());

      }
     
  }
}
