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
package org.webspeclanguage.userstories.impl;

import java.util.Locale;
import java.util.Map;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

public abstract class AbstractUserStoryGenerationParameters {

	private Diagram diagram;
	private Map<String, CroppingInfo> croppingInfoMap;
	private Locale outputLocale;
	
	public AbstractUserStoryGenerationParameters(Diagram diagram,
			Map<String, CroppingInfo> croppingInfoMap, Locale outputLocale) {
		super();
		this.setDiagram(diagram);
		this.setCroppingInfoMap(croppingInfoMap);
		this.setOutputLocale(outputLocale);
	}

	protected Diagram getDiagram() {
		return diagram;
	}

	private void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	protected Map<String, CroppingInfo> getCroppingInfoMap() {
		return croppingInfoMap;
	}

	private void setCroppingInfoMap(Map<String, CroppingInfo> croppingInfoMap) {
		this.croppingInfoMap = croppingInfoMap;
	}

	protected Locale getOutputLocale() {
		return outputLocale;
	}

	private void setOutputLocale(Locale outputLocale) {
		this.outputLocale = outputLocale;
	}

}
