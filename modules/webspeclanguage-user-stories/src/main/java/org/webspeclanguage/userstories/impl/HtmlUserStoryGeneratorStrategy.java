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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.lang.StringUtils;
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
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.UserStoryGenerator;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.dto.Scenario;
import org.webspeclanguage.userstories.dto.ScenarioStep;
import org.webspeclanguage.userstories.response.HtmlUserStoryGenerationResponse;
import org.webspeclanguage.userstories.visitor.HtmlCroppingVisitor;
import org.webspeclanguage.userstories.visitor.HtmlExplanationVisitor;
import org.webspeclanguage.userstories.visitor.HtmlMockupVisitor;

/**
 * Html generator.
 * 
 * @author cristian.cianfagna
 */
public class HtmlUserStoryGeneratorStrategy implements UserStoryGenerator, HtmlStrategyConstants, MessageSourceAware {

  private StringTemplateGroup htmlTemplateGroup;
  private Map<String, CroppingInfo> croppingMap;
  private MessageSource messageSource;
  private Locale locale;

  public HtmlUserStoryGeneratorStrategy() {
	StringTemplateGroup st = new StringTemplateGroup("userStoryGroup");
	st.setFileCharEncoding("UTF-8");
    this.setHtmlTemplateGroup(st);
  }

	@Override
	public UserStoryGenerationResponse generate(
			UserStoryGenerationParameters userStoryGenerationParameters)
			throws Exception {
		return this.generate(userStoryGenerationParameters.getDiagramImageServiceUrl(),
				userStoryGenerationParameters.getCroppingServiceUrl(), userStoryGenerationParameters.getDiagramId(),
				userStoryGenerationParameters.getDiagram(), userStoryGenerationParameters.getCroppingInfoMap(),
				userStoryGenerationParameters.getCssFilePaths(), userStoryGenerationParameters.getJsFilePaths(), 
				userStoryGenerationParameters.getImagesDirectory(), userStoryGenerationParameters.getOutputLocale());
	}

  private UserStoryGenerationResponse generate(String diagramImageServiceUrl, String croppingServiceUrl, String diagramId, Diagram diagram,
  		Map<String, CroppingInfo> croppingMap,
  		List<String> cssFiles, List<String> jsFiles, String imageDirectory,
  		Locale locale) throws Exception {
	  this.initialize(croppingMap, locale);

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

	  HtmlCroppingVisitor croppingUserStoryVisitor1 = new HtmlCroppingVisitor(croppingServiceUrl, diagramId, this.getCroppingMap());
	  HtmlExplanationVisitor explanationUserStoryVisitor = 
			  new HtmlExplanationVisitor(this.getHtmlTemplateGroup(), this.getMessageSource(), this.getLocale());
	  StepNameVisitor stepNameVisitor = new StepNameVisitor();
	  HtmlMockupVisitor mockupUserStoryVisitor = new HtmlMockupVisitor(this.getMessageSource(),
        this.getLocale());

	  List<Path> paths = PathComputer.computePaths(diagram);
	  for (Path path : paths) {
	  	// THIS IS AN EXAMPLE http://localhost:8080/service/projects/diagram/{diagramId}/image
		  scenario = new Scenario(path.getName() + UUID.randomUUID().toString(), path.getNameUsing("&rarr;"), 
					StringUtils.replace(diagramImageServiceUrl, 
							DIAGRAM_PLACEHOLDER, diagramId));

		  for (PathItem pathItem : path.getItems()) {
			  scenarioStep = new ScenarioStep(path.getName() + pathItem.hashCode() + UUID.randomUUID().toString(),
			  		path.getName() + pathItem.hashCode() + UUID.randomUUID().toString(),
			  		(String) pathItem.accept(stepNameVisitor), (String) pathItem.accept(croppingUserStoryVisitor1), (String) pathItem
					  .accept(explanationUserStoryVisitor), (String) pathItem.accept(mockupUserStoryVisitor));
			  scenario.addScenarioStep(scenarioStep);
		  }
		  scenarios.add(scenario);
	  }

	  headTemplate.setAttribute("cssFiles", cssFiles);
	  headTemplate.setAttribute("jsFiles", jsFiles);
	  headTemplate.setAttribute("imageDirectory", imageDirectory);
	  pageTemplate.setAttribute("head", headTemplate);
	  bodyTemplate.setAttribute("header", headerTemplate);
	  bodyTemplate.setAttribute("mainContainer", mainContainerTemplate);
	  bodyTemplate.setAttribute("footer", footerTemplate);
	  mainContainerTemplate.setAttribute("scenarios", scenarios);
	  mainContainerTemplate.setAttribute("stepByStep", this.getMessage("userstory.stepBYstep"));
	  pageTemplate.setAttribute("body", bodyTemplate);
	  
	  return new HtmlUserStoryGenerationResponse(pageTemplate.toString());
  }

  private void initialize(Map<String, CroppingInfo> croppingMap, Locale locale) {
    this.setCroppingMap(croppingMap);
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
