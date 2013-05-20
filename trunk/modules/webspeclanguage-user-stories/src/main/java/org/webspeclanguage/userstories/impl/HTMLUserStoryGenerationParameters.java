package org.webspeclanguage.userstories.impl;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

public class HTMLUserStoryGenerationParameters extends AbstractUserStoryGenerationParameters
	implements UserStoryGenerationParameters {

	private String diagramImageServiceUrl;
	private String croppingServiceUrl;
	private String diagramId;
	private List<String> cssFilePaths;
	private List<String> jsFilePaths;
	private String imagesDirectory;

	public HTMLUserStoryGenerationParameters(Diagram diagram, Map<String, CroppingInfo> croppingInfoMap,
			Locale outputLocale, String diagramImageServiceUrl, String croppingServiceUrl,
			String diagramId, List<String> cssFilePaths, List<String> jsFilePaths, String imagesDirectory) {
		super(diagram, croppingInfoMap, outputLocale);
		this.setDiagramImageServiceUrl(diagramImageServiceUrl);
		this.setCroppingServiceUrl(croppingServiceUrl);
		this.setDiagramId(diagramId);
		this.setCssFilePaths(cssFilePaths);
		this.setJsFilePaths(jsFilePaths);
		this.setImagesDirectory(imagesDirectory);
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
		return new File("dummy.png");
	}

	@Override
	public Locale getOutputLocale() {
		return super.getOutputLocale();
	}

	@Override
	public String getDiagramImageServiceUrl() {
		return this.diagramImageServiceUrl;
	}

	@Override
	public String getCroppingServiceUrl() {
		return this.croppingServiceUrl;
	}

	@Override
	public String getDiagramId() {
		return this.diagramId;
	}

	@Override
	public List<String> getCssFilePaths() {
		return this.cssFilePaths;
	}

	@Override
	public List<String> getJsFilePaths() {
		return this.jsFilePaths;
	}

	@Override
	public String getImagesDirectory() {
		return this.imagesDirectory;
	}

	private void setDiagramImageServiceUrl(String diagramImageServiceUrl) {
		this.diagramImageServiceUrl = diagramImageServiceUrl;
	}

	private void setCroppingServiceUrl(String croppingServiceUrl) {
		this.croppingServiceUrl = croppingServiceUrl;
	}

	private void setDiagramId(String diagramId) {
		this.diagramId = diagramId;
	}

	private void setCssFilePaths(List<String> cssFilePaths) {
		this.cssFilePaths = cssFilePaths;
	}

	private void setJsFilePaths(List<String> jsFilePaths) {
		this.jsFilePaths = jsFilePaths;
	}

	private void setImagesDirectory(String imagesDirectory) {
		this.imagesDirectory = imagesDirectory;
	}

}
