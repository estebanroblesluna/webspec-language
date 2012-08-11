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
import java.util.Locale;
import java.util.Map;

import org.docx4j.model.structure.MarginsWellKnown;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.util.PropertyUtil;
import org.webspeclanguage.userstories.visitor.WordEnumerationCroppingVisitor;
import org.webspeclanguage.userstories.visitor.WordEnumerationExplanationVisitor;
import org.webspeclanguage.userstories.visitor.WordEnumerationMockupVisitor;

/**
 * Strategy implementation that generate content in sections.
 * 
 * @author cristian.cianfagna
 */
public class WordEnumerationUserStoryGeneratorStrategy extends AbstractWordUserStoryGenerator {

  private long subsectionFontSize;
  private WordEnumerationCroppingVisitor croppingUserStoryVisitor;
  private WordEnumerationExplanationVisitor explanationUserStoryVisitor;
  private WordEnumerationMockupVisitor mockupUserStoryVisitor;

  public WordEnumerationUserStoryGeneratorStrategy() {
    super();
    this.setSubsectionFontSize(Long.valueOf(PropertyUtil.getProperty("userstory.wns.page.fontsize.subsections")));
  }

  @Override
  protected void initialize(WordprocessingMLPackage wordprocessingMLPackage, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception {
    this.basicInitialize(wordprocessingMLPackage, croppingMap, diagramFile, locale);
    this.setCroppingUserStoryVisitor(new WordEnumerationCroppingVisitor(this.getWordprocessingMLPackage(), this.getWmlFactory(), this.getCroppingMap(), this
            .getDiagramFile(), this.getSubsectionFontSize(), this.getMessageSource(), this.getLocale()));
    this.setExplanationUserStoryVisitor(new WordEnumerationExplanationVisitor(this.getWordprocessingMLPackage(), this.getWmlFactory(), this.getMessageSource(),
            this.getLocale(), this.getSubsectionFontSize()));
    this.setMockupUserStoryVisitor(new WordEnumerationMockupVisitor(this.getWordprocessingMLPackage(), this.getWmlFactory(), this.getSubsectionFontSize(), this
            .getMessageSource(), this.getLocale()));
  }

  @Override
  protected void generateHeader(Path path) throws Exception {
    this.basicGenerateHeader(path);
    // Image belong to the scenario
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().getImage(this.getWordprocessingMLPackage(), this.getDiagramFile(), 0));
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP());
  }

  @Override
  protected void generateBody(PathItem pathItem) {
    pathItem.accept(this.getCroppingUserStoryVisitor());
    pathItem.accept(this.getExplanationUserStoryVisitor());
    pathItem.accept(this.getMockupUserStoryVisitor());
  }

  @Override
  protected void generateFooter() {
    this.basicGenerateFooter();
  }

  @Override
  protected boolean isLandscape() {
    return Boolean.valueOf(PropertyUtil.getProperty("userstory.wns.page.orientation.landscape"));
  }

  @Override
  protected PageSizePaper getPageSizePaper() {
    return PageSizePaper.valueOf(PropertyUtil.getProperty("userstory.wns.page.size"));
  }

  @Override
  protected MarginsWellKnown getMargin() {
    return MarginsWellKnown.valueOf(PropertyUtil.getProperty("userstory.wns.page.margins"));
  }

  @Override
  protected long getNumberingStrategyForHeader() {
    return 3;
  }

  @Override
  protected long getFontSizeForScenarioDescription() {
    return Long.valueOf(PropertyUtil.getProperty("userstory.wns.page.fontsize.scenario"));
  }

  private long getSubsectionFontSize() {
    return subsectionFontSize;
  }

  private void setSubsectionFontSize(long subsectionFontSize) {
    this.subsectionFontSize = subsectionFontSize;
  }

  private WordEnumerationCroppingVisitor getCroppingUserStoryVisitor() {
    return croppingUserStoryVisitor;
  }

  private void setCroppingUserStoryVisitor(WordEnumerationCroppingVisitor croppingUserStoryVisitor) {
    this.croppingUserStoryVisitor = croppingUserStoryVisitor;
  }

  private WordEnumerationExplanationVisitor getExplanationUserStoryVisitor() {
    return explanationUserStoryVisitor;
  }

  private void setExplanationUserStoryVisitor(WordEnumerationExplanationVisitor explanationUserStoryVisitor) {
    this.explanationUserStoryVisitor = explanationUserStoryVisitor;
  }

  private WordEnumerationMockupVisitor getMockupUserStoryVisitor() {
    return mockupUserStoryVisitor;
  }

  private void setMockupUserStoryVisitor(WordEnumerationMockupVisitor mockupUserStoryVisitor) {
    this.mockupUserStoryVisitor = mockupUserStoryVisitor;
  }

}
