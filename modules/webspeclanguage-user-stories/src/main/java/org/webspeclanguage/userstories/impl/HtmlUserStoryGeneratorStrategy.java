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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.PathComputer;
import org.webspeclanguage.userstories.HtmlUserStoryGenerator;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.dto.Scenario;
import org.webspeclanguage.userstories.dto.ScenarioStep;
import org.webspeclanguage.userstories.response.FileResource;
import org.webspeclanguage.userstories.response.HtmlGenerationResponse;
import org.webspeclanguage.userstories.response.HtmlGenerationResponse.Builder;
import org.webspeclanguage.userstories.visitor.HtmlCroppingVisitor;
import org.webspeclanguage.userstories.visitor.HtmlExplanationVisitor;
import org.webspeclanguage.userstories.visitor.HtmlMockupVisitor;

/**
 * Html generator.
 * 
 * @author cristian.cianfagna
 */
public class HtmlUserStoryGeneratorStrategy implements HtmlUserStoryGenerator, HtmlStrategyConstants, MessageSourceAware {

  private StringTemplateGroup htmlTemplateGroup;
  private File diagramFile;
  private Map<String, CroppingInfo> croppingMap;
  private MessageSource messageSource;
  private Locale locale;

  public HtmlUserStoryGeneratorStrategy() {
    this.setHtmlTemplateGroup(new StringTemplateGroup("userStoryGroup"));
  }

  public UserStoryGenerationResponse generate(Diagram diagram, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale) throws Exception {
    this.initialize(croppingMap, diagramFile, locale);

    HtmlGenerationResponse.Builder htmlGenarationResponseBuilder = new HtmlGenerationResponse.Builder();
    htmlGenarationResponseBuilder.withScenariosDirectory(IMG_SCENARIOS_DIRECTORY).withIntearctionsDirectory(IMG_INTERACTIONS_DIRECTORY)
            .withNavigationsDirectory(IMG_NAVIGATIONS_DIRECTORY).withMockupsDirectory(IMG_MOCKUPS_DIRECTORY);

    StringTemplate pageTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "page");
    StringTemplate headTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "head");
    StringTemplate bodyTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "body");
    StringTemplate headerTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "header");
    StringTemplate mainContainerTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "mainContainer");
    StringTemplate footerTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "footer");
    headerTemplate.setAttribute("title", this.getMessage("userstory.title"));

    List<Scenario> scenarios = new ArrayList<Scenario>();
    Scenario scenario;
    ScenarioStep scenarioStep = null;

    HtmlCroppingVisitor croppingUserStoryVisitor = new HtmlCroppingVisitor(htmlGenarationResponseBuilder, this.getCroppingMap(),
            this.getDiagramFile(), this.getMessageSource(), this.getLocale());
    HtmlExplanationVisitor explanationUserStoryVisitor = 
      new HtmlExplanationVisitor(this.getHtmlTemplateGroup(), this.getMessageSource(), this.getLocale());
    StepNameVisitor stepNameVisitor = new StepNameVisitor();
    HtmlMockupVisitor mockupUserStoryVisitor = new HtmlMockupVisitor(htmlGenarationResponseBuilder,this.getMessageSource(),
            this.getLocale());

    List<Path> paths = PathComputer.computePaths(diagram);
    for (Path path : paths) {
      scenario = this.createScenario(path, this.getDiagramFile(), htmlGenarationResponseBuilder);
      for (PathItem pathItem : path.getItems()) {
        scenarioStep = new ScenarioStep((String) pathItem.accept(stepNameVisitor), (String) pathItem.accept(croppingUserStoryVisitor), (String) pathItem
                .accept(explanationUserStoryVisitor), (String) pathItem.accept(mockupUserStoryVisitor));
        scenario.addScenarioStep(scenarioStep);
      }
      scenarios.add(scenario);
    }

    pageTemplate.setAttribute("head", headTemplate);
    bodyTemplate.setAttribute("header", headerTemplate);
    bodyTemplate.setAttribute("mainContainer", mainContainerTemplate);
    bodyTemplate.setAttribute("footer", footerTemplate);
    mainContainerTemplate.setAttribute("scenarios", scenarios);
    mainContainerTemplate.setAttribute("stepByStep", this.getMessage("userstory.stepBYstep"));
    pageTemplate.setAttribute("body", bodyTemplate);
    htmlGenarationResponseBuilder.withHtml(pageTemplate.toString());
    this.addResources(htmlGenarationResponseBuilder);
    return htmlGenarationResponseBuilder.build();
  }

  private void addResources(Builder htmlGenarationResponseBuilder) {
    this.addJsResources(htmlGenarationResponseBuilder);
    this.addFancyZoomImageResources(htmlGenarationResponseBuilder);
  }

  private void addFancyZoomImageResources(Builder htmlGenarationResponseBuilder) {
    htmlGenarationResponseBuilder.withFancyZoomResourcesDirectory(RESOURCES_DIRECTORY);
    File[] files = new File(this.getClass().getResource("resources/blank.gif").getFile()).getParentFile().listFiles();
    for (int i = 0; i < files.length; i++) {
      htmlGenarationResponseBuilder.addFancyZoomImageFile(files[i]);
    }
  }

  private void addJsResources(Builder htmlGenarationResponseBuilder) {
    htmlGenarationResponseBuilder.withJavascriptDirectory(JS_DIRECTORY);
    htmlGenarationResponseBuilder.addJavascriptFile(new File(this.getClass().getResource("js/jquery.fancyzoom.js").getFile())).addJavascriptFile(
            new File(this.getClass().getResource("js/jquery.fancyzoom.min.js").getFile())).addJavascriptFile(
            new File(this.getClass().getResource("js/jquery.ifixpng.js").getFile())).addJavascriptFile(
            new File(this.getClass().getResource("js/jquery.js").getFile())).addJavascriptFile(
            new File(this.getClass().getResource("js/jquery.shadow.js").getFile()));
  }

  private Scenario createScenario(Path path, File diagramFile, Builder htmlGenarationResponseBuilder) throws IOException {
    Scenario scenario = new Scenario(path.getNameUsing("&rarr;"), IMG_SCENARIOS_DIRECTORY + "/" + diagramFile.getName());
    ByteArrayOutputStream scenarioByteArrayOutputStream = new ByteArrayOutputStream();
    ImageCroppingUtil.writeImage(ImageCroppingUtil.readImage(diagramFile), scenarioByteArrayOutputStream, "png");
    htmlGenarationResponseBuilder.addScenario(new FileResource("/" + diagramFile.getName(), scenarioByteArrayOutputStream));
    return scenario;
  }

  private void initialize(Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale) {
    this.setCroppingMap(croppingMap);
    this.setDiagramFile(diagramFile);
    this.setLocale(locale);
  }

  /**
   * Visitor in charges of getting each step name.
   * 
   * @author cristian.cianfagna
   */
  private class StepNameVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      return interaction.getName();
    }
    public Object visitNavigation(Navigation navigation) {
      return navigation.getName();
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return "";
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      return richBehavior.getName();
    }
  }

  protected String getMessage(String bundleKey) {
    return this.getMessageSource().getMessage(bundleKey, new Object[] {}, this.getLocale());
  }

  private StringTemplateGroup getHtmlTemplateGroup() {
    return htmlTemplateGroup;
  }

  private void setHtmlTemplateGroup(StringTemplateGroup htmlTemplateGroup) {
    this.htmlTemplateGroup = htmlTemplateGroup;
  }

  private Map<String, CroppingInfo> getCroppingMap() {
    return croppingMap;
  }

  private void setCroppingMap(Map<String, CroppingInfo> croppingMap) {
    this.croppingMap = croppingMap;
  }

  private File getDiagramFile() {
    return diagramFile;
  }

  private void setDiagramFile(File diagramFile) {
    this.diagramFile = diagramFile;
  }

  private MessageSource getMessageSource() {
    return messageSource;
  }

  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  private Locale getLocale() {
    return locale;
  }

  private void setLocale(Locale locale) {
    this.locale = locale;
  }
}
