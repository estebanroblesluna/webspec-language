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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;


/**
 * @author Franco Giacosa
 */
public final class DMTransformationFacade {

  private DMTransformationFactory dmTransformationFactory;
  private List<DataSpecs2WebMLDataModel> dataSpecsTransformations = new ArrayList<DataSpecs2WebMLDataModel>();
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
  
  public DataSpecs2WebMLDataModel transformData(SuiSpecsInferenceState suiSpecsInferenceState){
	  DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel = new DataSpecs2WebMLDataModel(suiSpecsInferenceState);
	  dataSpecs2WebMLDataModel.transform();
	  return dataSpecs2WebMLDataModel;
  }
  public void setDataSpecsTransformations(List<DataSpecs2WebMLDataModel> dataSpecsTransformations) {
	this.dataSpecsTransformations = dataSpecsTransformations;
  }
  public List<DataSpecs2WebMLDataModel> getDataSpecsTransformations() {
	return dataSpecsTransformations;
  }
 
 
}
