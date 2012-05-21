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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import org.webspeclanguage.mockupdd.specs.hypertext.HypertextSpecFactory;



/**
 * @author Franco Giacosa
 */
public class HypertextSpecFacade {
  
  private HypertextSpecFactory hypertextSpecFactory;
  private static HypertextSpecFacade hypertextSpecFacade;

  private HypertextSpecFacade() {
    hypertextSpecFactory = new HypertextSpecFactoryImpl();
  }
  public static HypertextSpecFacade getHypertextSpecFacade() {
    if(hypertextSpecFacade==null){  
      hypertextSpecFacade = new HypertextSpecFacade();
    }  
    return hypertextSpecFacade;
  }
  
  public HypertextSpecFactory getHypertextSpecFactory() {
    return hypertextSpecFactory;
  }


}