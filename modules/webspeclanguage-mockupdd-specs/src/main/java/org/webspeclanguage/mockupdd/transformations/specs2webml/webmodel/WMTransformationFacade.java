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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;

/**
 * @author Franco Giacosa
 */
public final class WMTransformationFacade {

	private WMTransformationFactory wmTransformationFactory;
	private static WMTransformationFacade wmTransformationFacade;
	private List<HypertextSpec2WebMLWebModel> hypertextSpecsTransformations = new ArrayList<HypertextSpec2WebMLWebModel>();

	private WMTransformationFacade() {
		wmTransformationFactory = new WMTransformationFactoryImpl();
	}

	public static WMTransformationFacade getWMTransformationFacade() {
		if (wmTransformationFacade == null) {
			wmTransformationFacade = new WMTransformationFacade();
		}
		return wmTransformationFacade;
	}

	public WMTransformationFactory getWMTransformationFactory() {
		return wmTransformationFactory;
	}

	public HypertextSpec2WebMLWebModel transformHypertext(
			SuiSpecsInferenceState suiSpecsInferenceState,DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel = new HypertextSpec2WebMLWebModel(
				suiSpecsInferenceState,dataSpecs2WebMLDataModel);
		hypertextSpec2WebMLWebModel.transform();
		this.getHypertextSpecsTransformations()
				.add(hypertextSpec2WebMLWebModel);
		return hypertextSpec2WebMLWebModel;
	}

	public void setHypertextSpecsTransformations(
			List<HypertextSpec2WebMLWebModel> hypertextSpecsTransformations) {
		this.hypertextSpecsTransformations = hypertextSpecsTransformations;
	}

	public List<HypertextSpec2WebMLWebModel> getHypertextSpecsTransformations() {
		return hypertextSpecsTransformations;
	}

}
