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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

public class WordUserStoryGenerationParameters extends AbstractUserStoryGenerationParameters 
	implements UserStoryGenerationParameters {

	private File diagramFile;

	public WordUserStoryGenerationParameters(Diagram diagram, Map<String, CroppingInfo> croppingInfoMap,
			File diagramFile, Locale outputLocale) {
		super(diagram, croppingInfoMap, outputLocale);
		this.setDiagramFile(diagramFile);
	}

	@Override
	public Diagram getDiagram() {
		return super.getDiagram();
	}

	@Override
	public Map<String, CroppingInfo> getCroppingInfoMap() {
		return super.getCroppingInfoMap();
	}

	@Override
	public File getDiagramFile() {
		return this.diagramFile;
	}

	@Override
	public Locale getOutputLocale() {
		return super.getOutputLocale();
	}

	@Override
	public String getDiagramImageServiceUrl() {
		return "";
	}

	@Override
	public String getCroppingServiceUrl() {
		return "";
	}

	@Override
	public String getDiagramId() {
		return "";
	}

	@Override
	public List<String> getCssFilePaths() {
		return Arrays.asList();
	}

	@Override
	public List<String> getJsFilePaths() {
		return Arrays.asList();
	}

	@Override
	public String getImagesDirectory() {
		return "";
	}

	private void setDiagramFile(File diagramFile) {
		this.diagramFile = diagramFile;
	}

}
