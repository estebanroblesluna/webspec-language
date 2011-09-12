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

package org.webspeclanguage.mockupdd.codegen.webml.webmodel;


/**
 * @author Franco Giacosa
 */
public class WebModelFacade {

  private WebModelFactory webModelFactory;
  private static WebModelFacade webModelFacade;

  private WebModelFacade() {
    WebModelIds webModelIds = new WebModelIds();
    webModelFactory = new WebModelFactoryImpl(webModelIds);
  }
  public static WebModelFacade getWebModelFacade() {
    if(webModelFacade==null){  
       webModelFacade = new WebModelFacade();
    }  
    return webModelFacade;
  }
  
  public WebModelFactory getWebModelFactory() {
    return webModelFactory;
  }
  
 


  
 
}
