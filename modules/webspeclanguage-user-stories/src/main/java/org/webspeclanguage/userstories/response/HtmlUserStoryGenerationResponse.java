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
package org.webspeclanguage.userstories.response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;

/**
 * @author cristian.cianfagna
 */
public class HtmlUserStoryGenerationResponse implements UserStoryGenerationResponse {

  private String scenariosDirectory;
  private List<FileResource> scenarios;
  private String intearctionsDirectory;
  private List<FileResource> interactions;
  private String navigationsDirectory;
  private List<FileResource> navigations;
  private String mockupsDirectory;
  private List<FileResource> mockups;
  private String fancyZoomResourcesDirectory;
  private List<File> fancyZoomImageFiles;
  private String javascriptsDirectory;
  private List<File> javascriptFiles;
  private String html;

  private HtmlUserStoryGenerationResponse() {
    this.setInteractions(new ArrayList<FileResource>());
    this.setMockups(new ArrayList<FileResource>());
    this.setNavigations(new ArrayList<FileResource>());
    this.setScenarios(new ArrayList<FileResource>());
    this.setFancyZoomImageFiles(new ArrayList<File>());
    this.setJavascriptFiles(new ArrayList<File>());
  }

  public void generateResourcesIn(String fullDirectory, String fileNameWithoutExtension) throws FileNotFoundException, IOException {
    Validate.notNull(this.getScenariosDirectory(), "Scenarios directory is null");
    Validate.notNull(this.getIntearctionsDirectory(), "Interactions directory is null");
    Validate.notNull(this.getNavigationsDirectory(), "Navigations directory is null");
    Validate.notNull(this.getMockupsDirectory(), "Mockups directory is null");
    Validate.notNull(this.getFancyZoomResourcesDirectory(), "Ressources directory is null");
    Validate.notNull(this.getJavascriptsDirectory(), "Javascripts directory is null");
    this.createDirectory(fullDirectory + this.getScenariosDirectory());
    this.createResources(fullDirectory + this.getScenariosDirectory(), this.getScenarios());
    this.createDirectory(fullDirectory + this.getIntearctionsDirectory());
    this.createResources(fullDirectory + this.getIntearctionsDirectory(), this.getInteractions());
    this.createDirectory(fullDirectory + this.getNavigationsDirectory());
    this.createResources(fullDirectory + this.getNavigationsDirectory(), this.getNavigations());
    this.createDirectory(fullDirectory + this.getMockupsDirectory());
    this.createResources(fullDirectory + this.getMockupsDirectory(), this.getMockups());
    this.createDirectory(fullDirectory + this.getFancyZoomResourcesDirectory());
    this.createFileResources(fullDirectory + this.getFancyZoomResourcesDirectory(), this.getFancyZoomImageFiles());
    this.createFileResources(fullDirectory + this.getJavascriptsDirectory(), this.getJavascriptFiles());

    StringBuilder sb = new StringBuilder(fullDirectory).append("/").
    	append(fileNameWithoutExtension).append(".html");
    FileOutputStream fos = new FileOutputStream(sb.toString());
    OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("UTF8"));
    BufferedWriter bw = new BufferedWriter(osw);
    bw.write(this.getHtml());
    bw.close();
  }

  private void createFileResources(String directory, List<File> fancyZoomImageFiles) throws IOException {
    File destination = new File(directory);
    for (File file : fancyZoomImageFiles) {
      FileUtils.copyFileToDirectory(file, destination);
    }
  }

  private void createResources(String directory, List<FileResource> resources) throws FileNotFoundException, IOException {
    for (FileResource fileResource : resources) {
      fileResource.getOutputStream().writeTo(new FileOutputStream(new File(directory + fileResource.getFileName())));
    }
  }

  private void createDirectory(String directory) {
    new File(directory).mkdirs();
  }

  private String getScenariosDirectory() {
    return scenariosDirectory;
  }

  private void setScenariosDirectory(String scenariosDirectory) {
    this.scenariosDirectory = scenariosDirectory;
  }

  private List<FileResource> getScenarios() {
    return scenarios;
  }

  private void setScenarios(List<FileResource> scenarios) {
    this.scenarios = scenarios;
  }

  private String getIntearctionsDirectory() {
    return intearctionsDirectory;
  }

  private void setIntearctionsDirectory(String intearctionsDirectory) {
    this.intearctionsDirectory = intearctionsDirectory;
  }

  private List<FileResource> getInteractions() {
    return interactions;
  }

  private void setInteractions(List<FileResource> interactions) {
    this.interactions = interactions;
  }

  private String getNavigationsDirectory() {
    return navigationsDirectory;
  }

  private void setNavigationsDirectory(String navigationsDirectory) {
    this.navigationsDirectory = navigationsDirectory;
  }

  private List<FileResource> getNavigations() {
    return navigations;
  }

  private void setNavigations(List<FileResource> navigations) {
    this.navigations = navigations;
  }

  private String getMockupsDirectory() {
    return mockupsDirectory;
  }

  private void setMockupsDirectory(String mockupsDirectory) {
    this.mockupsDirectory = mockupsDirectory;
  }

  private List<FileResource> getMockups() {
    return mockups;
  }

  private void setMockups(List<FileResource> mockups) {
    this.mockups = mockups;
  }

  private String getHtml() {
    return html;
  }

  private void setHtml(String html) {
    this.html = html;
  }

  private List<File> getFancyZoomImageFiles() {
    return fancyZoomImageFiles;
  }

  private void setFancyZoomImageFiles(List<File> fancyZoomImageFiles) {
    this.fancyZoomImageFiles = fancyZoomImageFiles;
  }

  private String getFancyZoomResourcesDirectory() {
    return fancyZoomResourcesDirectory;
  }

  private void setFancyZoomResourcesDirectory(String fancyZoomResourcesDirectory) {
    this.fancyZoomResourcesDirectory = fancyZoomResourcesDirectory;
  }

  private String getJavascriptsDirectory() {
    return javascriptsDirectory;
  }

  private void setJavascriptsDirectory(String javascriptsDirectory) {
    this.javascriptsDirectory = javascriptsDirectory;
  }

  private List<File> getJavascriptFiles() {
    return javascriptFiles;
  }

  private void setJavascriptFiles(List<File> javascriptFiles) {
    this.javascriptFiles = javascriptFiles;
  }

  public static class Builder {

    private HtmlUserStoryGenerationResponse htmlGenarationResponse;

    public Builder() {
      this.setHtmlGenarationResponse(new HtmlUserStoryGenerationResponse());
    }

    public HtmlUserStoryGenerationResponse build() {
      return this.getHtmlGenarationResponse();
    }

    public Builder withScenariosDirectory(String scenariosDirectory) {
      this.getHtmlGenarationResponse().setScenariosDirectory(scenariosDirectory);
      return this;
    }

    public Builder addScenario(FileResource scenario) {
      this.getHtmlGenarationResponse().getScenarios().add(scenario);
      return this;
    }

    public Builder withIntearctionsDirectory(String intearctionsDirectory) {
      this.getHtmlGenarationResponse().setIntearctionsDirectory(intearctionsDirectory);
      return this;
    }

    public Builder addInteraction(FileResource interaction) {
      this.getHtmlGenarationResponse().getInteractions().add(interaction);
      return this;
    }

    public Builder withNavigationsDirectory(String navigationsDirectory) {
      this.getHtmlGenarationResponse().setNavigationsDirectory(navigationsDirectory);
      return this;
    }

    public Builder addNavigation(FileResource navigation) {
      this.getHtmlGenarationResponse().getNavigations().add(navigation);
      return this;
    }

    public Builder withMockupsDirectory(String mockupsDirectory) {
      this.getHtmlGenarationResponse().setMockupsDirectory(mockupsDirectory);
      return this;
    }

    public Builder addMockup(FileResource mockup) {
      this.getHtmlGenarationResponse().getMockups().add(mockup);
      return this;
    }

    public Builder withHtml(String html) {
      this.getHtmlGenarationResponse().setHtml(html);
      return this;
    }

    public Builder addFancyZoomImageFile(File resource) {
      this.getHtmlGenarationResponse().getFancyZoomImageFiles().add(resource);
      return this;
    }

    public Builder withFancyZoomResourcesDirectory(String fancyZoomResourcesDirectory) {
      this.getHtmlGenarationResponse().setFancyZoomResourcesDirectory(fancyZoomResourcesDirectory);
      return this;
    }

    public Builder addJavascriptFile(File resource) {
      this.getHtmlGenarationResponse().getJavascriptFiles().add(resource);
      return this;
    }

    public Builder withJavascriptDirectory(String javascriptsDirectory) {
      this.getHtmlGenarationResponse().setJavascriptsDirectory(javascriptsDirectory);
      return this;
    }

    private HtmlUserStoryGenerationResponse getHtmlGenarationResponse() {
      return htmlGenarationResponse;
    }

    private void setHtmlGenarationResponse(HtmlUserStoryGenerationResponse htmlGenarationResponse) {
      this.htmlGenarationResponse = htmlGenarationResponse;
    }

  }
}
