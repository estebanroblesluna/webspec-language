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
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.XmlUtils;
import org.docx4j.model.structure.MarginsWellKnown;
import org.docx4j.model.structure.PageDimensions;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.Numbering;
import org.docx4j.wml.P;
import org.docx4j.wml.SectPr;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.PathComputer;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.UserStoryGenerator;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.factory.WmlFactory;
import org.webspeclanguage.userstories.response.WordUserStoryGenerationResponse;

/**
 * Abstract class that holds common instance variables and define the algorithm
 * to generate the document's structure.
 * 
 * @author cristian.cianfagna
 */
public abstract class AbstractWordUserStoryGeneratorStrategy implements UserStoryGenerator, ApplicationContextAware {

  private final static Logger LOGGER = Logger.getLogger(AbstractWordUserStoryGeneratorStrategy.class);

  private WordprocessingMLPackage wordprocessingMLPackage;
  private WmlFactory wmlFactory;
  private Map<String, CroppingInfo> croppingMap;
  private File diagramFile;
  private Locale locale;
  private MessageSource messageSource;

  public AbstractWordUserStoryGeneratorStrategy() {
    this.setWmlFactory(WmlFactory.getInstance());
    try {
      this.setWordprocessingMLPackage(WordprocessingMLPackage.createPackage());
    } catch (InvalidFormatException e) {
      LOGGER.error("It is imposible to create the word processing package", e);
      throw new RuntimeException(e);
    }
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    try {
      InputStream numberingInputStream = applicationContext.getResource("classpath:/conf/numbering.xml").getInputStream();
      NumberingDefinitionsPart numberingDefinitionsPart = new NumberingDefinitionsPart();
      numberingDefinitionsPart.setJaxbElement((Numbering) XmlUtils.unmarshal(numberingInputStream));
      this.getWordprocessingMLPackage().getMainDocumentPart().addTargetPart(numberingDefinitionsPart);
    } catch (Exception e) {
      LOGGER.error(e);
    }
    this.setMessageSource(applicationContext);
  }

	@Override
	public UserStoryGenerationResponse generate(
			UserStoryGenerationParameters userStoryGenerationParameters)
			throws Exception {
		return this.generate(userStoryGenerationParameters.getDiagram(), userStoryGenerationParameters.getCroppingInfoMap(),
				userStoryGenerationParameters.getDiagramFile(), userStoryGenerationParameters.getOutputLocale());
	}
  
  private UserStoryGenerationResponse generate(Diagram diagram, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception {
    List<Path> paths = PathComputer.computePaths(diagram);
    for(Path path : paths) {
      this.initialize(this.getWordprocessingMLPackage(), croppingMap, diagramFile, locale);
      this.generateHeader(path);
      for (PathItem pathItem : path.getItems()) {
        this.generateBody(pathItem);
      }
      this.generateFooter();
    }
    return new WordUserStoryGenerationResponse(this.getWordprocessingMLPackage()); 
  }

  // template method
  protected abstract void initialize(WordprocessingMLPackage wordprocessingMLPackage, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception;
  protected abstract void generateHeader(Path path) throws Exception;
  protected abstract void generateBody(PathItem pathItem);
  protected abstract void generateFooter();

  // Initialize
  protected abstract boolean isLandscape();
  protected abstract PageSizePaper getPageSizePaper();
  protected abstract MarginsWellKnown getMargin();

  // Header
  protected abstract long getNumberingStrategyForHeader();
  protected abstract long getFontSizeForScenarioDescription();

  protected void basicInitialize(WordprocessingMLPackage wordprocessingMLPackage, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception {
    this.setCroppingMap(croppingMap);
    this.setDiagramFile(diagramFile);
    this.setLocale(locale);

    boolean isLandscape = this.isLandscape();
    PageSizePaper pageSizePaper = this.getPageSizePaper();
    PageDimensions pageDimensions = new PageDimensions();
    pageDimensions.setPgSize(pageSizePaper, isLandscape);
    pageDimensions.setMargins(this.getMargin());
    SectPr sectPr = this.getWordprocessingMLPackage().getMainDocumentPart().getJaxbElement().getBody().getSectPr();
    sectPr.setPgSz(pageDimensions.getPgSz());
    sectPr.setPgMar(pageDimensions.getPgMar());
  }

  protected void basicGenerateHeader(Path path) {
    // Header of each scenario
    P scenarioParagraph = this.getWmlFactory().createNumberingP(this.getNumberingStrategyForHeader(), 0, path.getNameUsing("-->\n"), true,
            this.getFontSizeForScenarioDescription());
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(scenarioParagraph);
  }

  protected void basicGenerateFooter() {
    // Adding a new page with a paragraph
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().getNewPage());
  }

  protected String getMessage(String bundleKey) {
    return this.getMessageSource().getMessage(bundleKey, new Object[] {}, this.getLocale());
  }

  protected WordprocessingMLPackage getWordprocessingMLPackage() {
    return wordprocessingMLPackage;
  }

  protected WmlFactory getWmlFactory() {
    return wmlFactory;
  }

  protected Map<String, CroppingInfo> getCroppingMap() {
    return croppingMap;
  }

  protected File getDiagramFile() {
    return diagramFile;
  }

  protected Locale getLocale() {
    return locale;
  }

  protected MessageSource getMessageSource() {
    return messageSource;
  }

  private void setWordprocessingMLPackage(WordprocessingMLPackage wordprocessingMLPackage) {
    this.wordprocessingMLPackage = wordprocessingMLPackage;
  }

  private void setWmlFactory(WmlFactory wmlFactory) {
    this.wmlFactory = wmlFactory;
  }

  private void setCroppingMap(Map<String, CroppingInfo> croppingMap) {
    this.croppingMap = croppingMap;
  }

  private void setDiagramFile(File diagramFile) {
    this.diagramFile = diagramFile;
  }

  private void setLocale(Locale locale) {
    this.locale = locale;
  }

  private void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

}
