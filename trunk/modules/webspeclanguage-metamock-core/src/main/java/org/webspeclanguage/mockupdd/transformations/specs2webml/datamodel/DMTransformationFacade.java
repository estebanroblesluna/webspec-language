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

package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;


/**
 * @author Franco Giacosa
 */
public final class DMTransformationFacade {

  private DMTransformationFactory dmTransformationFactory;
  private static DMTransformationFacade dmTransformationFacade;

  private DMTransformationFacade() {
    dmTransformationFactory = new DMTransformationFactoryImpl();
  }
  public static DMTransformationFacade getDMTransformationFacade() {
    if(dmTransformationFacade==null){  
      dmTransformationFacade = new DMTransformationFacade();
    }  
    return dmTransformationFacade;
  }
  
  public DMTransformationFactory getDMTransformationFactory() {
    return dmTransformationFactory;
  }
  
 
}
