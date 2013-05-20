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
package org.webspeclanguage.service;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.impl.HTMLUserStoryGenerationParameters;
import org.webspeclanguage.userstories.impl.WordUserStoryGenerationParameters;

public class UserStoryGenerationParametersFactory  {
	
	private Diagram diagram;
	private File diagramFile;
	private Map<String, CroppingInfo> croppingInfoMap;
	private Locale outputLocale;
	private String diagramImageServiceUrl;
	private String croppingServiceUrl;
	private String diagramId;
	private List<String> cssFilePaths;
	private List<String> jsFilePaths;
	private String imagesDirectory;

	public UserStoryGenerationParametersFactory(Diagram diagram, Map<String, CroppingInfo> croppingInfoMap,
			Locale outputLocale, File diagramFile, String diagramImageServiceUrl, String croppingServiceUrl,
			String diagramId, List<String> cssFilePaths, List<String> jsFilePaths, String imagesDirectory) {
		super();
		this.setDiagram(diagram);
		this.setCroppingInfoMap(croppingInfoMap);
		this.setOutputLocale(outputLocale);
		this.setDiagramFile(diagramFile);
		this.setDiagramImageServiceUrl(diagramImageServiceUrl);
		this.setCroppingServiceUrl(croppingServiceUrl);
		this.setDiagramId(diagramId);
		this.setCssFilePaths(cssFilePaths);
		this.setJsFilePaths(jsFilePaths);
		this.setImagesDirectory(imagesDirectory);
	}

	public UserStoryGenerationParameters build(UserStoryOutput userStoryOutput) {
		return userStoryOutput.getParametersForOutput(this);
	}

	public UserStoryGenerationParameters buildHTMLParameters() {
		return new HTMLUserStoryGenerationParameters(
				this.getDiagram(), this.getCroppingInfoMap(), this.getOutputLocale(), this.getDiagramImageServiceUrl(),
				this.getCroppingServiceUrl(), this.getDiagramId(), this.getCssFilePaths(), this.getJsFilePaths(),
				this.getImagesDirectory());
	}

	public UserStoryGenerationParameters buildWordParameters() {
		return new WordUserStoryGenerationParameters(this.getDiagram(), this.getCroppingInfoMap(),
				this.getDiagramFile(), this.getOutputLocale());
	}

	private Diagram getDiagram() {
		return diagram;
	}

	private void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	private Map<String, CroppingInfo> getCroppingInfoMap() {
		return croppingInfoMap;
	}

	private void setCroppingInfoMap(Map<String, CroppingInfo> croppingInfoMap) {
		this.croppingInfoMap = croppingInfoMap;
	}

	private Locale getOutputLocale() {
		return outputLocale;
	}

	private void setOutputLocale(Locale outputLocale) {
		this.outputLocale = outputLocale;
	}

	private String getDiagramImageServiceUrl() {
		return diagramImageServiceUrl;
	}

	private void setDiagramImageServiceUrl(String diagramImageServiceUrl) {
		this.diagramImageServiceUrl = diagramImageServiceUrl;
	}

	private String getCroppingServiceUrl() {
		return croppingServiceUrl;
	}

	private void setCroppingServiceUrl(String croppingServiceUrl) {
		this.croppingServiceUrl = croppingServiceUrl;
	}

	private String getDiagramId() {
		return diagramId;
	}

	private void setDiagramId(String diagramId) {
		this.diagramId = diagramId;
	}

	private List<String> getCssFilePaths() {
		return cssFilePaths;
	}

	private void setCssFilePaths(List<String> cssFilePaths) {
		this.cssFilePaths = cssFilePaths;
	}

	private List<String> getJsFilePaths() {
		return jsFilePaths;
	}

	private void setJsFilePaths(List<String> jsFilePaths) {
		this.jsFilePaths = jsFilePaths;
	}

	private String getImagesDirectory() {
		return imagesDirectory;
	}

	private void setImagesDirectory(String imagesDirectory) {
		this.imagesDirectory = imagesDirectory;
	}

	private File getDiagramFile() {
		return diagramFile;
	}

	private void setDiagramFile(File diagramFile) {
		this.diagramFile = diagramFile;
	}

}
