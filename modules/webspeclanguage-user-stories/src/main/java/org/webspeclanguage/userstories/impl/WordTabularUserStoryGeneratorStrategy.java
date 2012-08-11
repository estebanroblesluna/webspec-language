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
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.P;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.util.PropertyUtil;
import org.webspeclanguage.userstories.visitor.WordTabularCroppingVisitor;
import org.webspeclanguage.userstories.visitor.WordTabularExplanationVisitor;
import org.webspeclanguage.userstories.visitor.WordTabularMockupVisitor;

/**
 * Strategy implementation that generate content in tables.
 * 
 * @author cristian.cianfagna
 */
public class WordTabularUserStoryGeneratorStrategy extends AbstractWordUserStoryGenerator {

  private final static int COLUMNS = 3;

  private int cellWidthTwips;
  private Tbl table;
  private WordTabularCroppingVisitor croppingUserStoryVisitor;
  private WordTabularExplanationVisitor explanationUserStoryVisitor;
  private WordTabularMockupVisitor mockupUserStoryVisitor;

  public WordTabularUserStoryGeneratorStrategy() {
    super();
  }

  @Override
  protected void initialize(WordprocessingMLPackage wordprocessingMLPackage, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception {
    this.basicInitialize(wordprocessingMLPackage, croppingMap, diagramFile, locale);
    // twips for cells
    int writableWidthTwips = wordprocessingMLPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
    this.setCellWidthTwips(new Double(Math.floor((writableWidthTwips / COLUMNS))).intValue());
    this.setCroppingUserStoryVisitor(new WordTabularCroppingVisitor(this.getWordprocessingMLPackage(),
            this.getWmlFactory(), this.getCroppingMap(), this.getCellWidthTwips(), this.getDiagramFile(), this.getMessageSource(),
            this.getLocale()));
    this.setExplanationUserStoryVisitor(
            new WordTabularExplanationVisitor(this.getWmlFactory(), this.getMessageSource(), this.getLocale(), this.getCellWidthTwips()));
    this.setMockupUserStoryVisitor(new WordTabularMockupVisitor(this.getWordprocessingMLPackage(), this.getWmlFactory(), this.getCellWidthTwips(),
            this.getMessageSource(), this.getLocale()));
  }

  @Override
  protected void generateHeader(Path path) throws Exception {
    this.basicGenerateHeader(path);
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP());

    // Image belong to the scenario
    P pimage = this.getWmlFactory().getImage(this.getWordprocessingMLPackage(), this.getDiagramFile(), JcEnumeration.CENTER);
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(pimage);

    // The table starts at the beginning of a new page
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().getNewPage());

    // New table
    Tbl table = this.getWmlFactory().createTbl();

    // Table header
    Tr headerTr = this.getWmlFactory().createTr();
    headerTr.getContent().add(
            this.getWmlFactory().createTC(this.getMessage("userstory.wts1.table.column1"), JcEnumeration.CENTER, STVerticalJc.CENTER, this.getCellWidthTwips(),
                    true, Long.valueOf(PropertyUtil.getProperty("userstory.wts.page.table.fontsize.header"))));
    headerTr.getContent().add(
            this.getWmlFactory().createTC(this.getMessage("userstory.wts1.table.column2"), JcEnumeration.CENTER, STVerticalJc.CENTER, this.getCellWidthTwips(),
                    true, Long.valueOf(PropertyUtil.getProperty("userstory.wts.page.table.fontsize.header"))));
    headerTr.getContent().add(
            this.getWmlFactory().createTC(this.getMessage("userstory.wts1.table.column3"), JcEnumeration.CENTER, STVerticalJc.CENTER, this.getCellWidthTwips(),
                    true, Long.valueOf(PropertyUtil.getProperty("userstory.wts.page.table.fontsize.header"))));
    table.getContent().add(headerTr);
    this.setTable(table);
  }

  @Override
  protected void generateBody(PathItem pathItem) {
    // Adding rows
    Tr row = this.getWmlFactory().createTr();
    row.getContent().add((Tc) pathItem.accept(this.getCroppingUserStoryVisitor()));
    row.getContent().add((Tc) pathItem.accept(this.getExplanationUserStoryVisitor()));
    row.getContent().add((Tc) pathItem.accept(this.getMockupUserStoryVisitor()));
    this.getTable().getContent().add(row);
  }

  @Override
  protected void generateFooter() {
    // Adding the table
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getTable());
    this.basicGenerateFooter();
  }

  @Override
  protected boolean isLandscape() {
    return Boolean.valueOf(PropertyUtil.getProperty("userstory.wts.page.orientation.landscape"));
  }

  @Override
  protected PageSizePaper getPageSizePaper() {
    return PageSizePaper.valueOf(PropertyUtil.getProperty("userstory.wts.page.size"));
  }

  @Override
  protected MarginsWellKnown getMargin() {
    return MarginsWellKnown.valueOf(PropertyUtil.getProperty("userstory.wts.page.margins"));
  }

  @Override
  protected long getNumberingStrategyForHeader() {
    return 1;
  }

  @Override
  protected long getFontSizeForScenarioDescription() {
    return Long.valueOf(PropertyUtil.getProperty("userstory.wts.page.fontsize.scenario"));
  }

  protected int getCellWidthTwips() {
    return cellWidthTwips;
  }

  private void setCellWidthTwips(int cellWidthTwips) {
    this.cellWidthTwips = cellWidthTwips;
  }

  private Tbl getTable() {
    return table;
  }

  private void setTable(Tbl table) {
    this.table = table;
  }

  private WordTabularMockupVisitor getMockupUserStoryVisitor() {
    return mockupUserStoryVisitor;
  }

  private void setMockupUserStoryVisitor(WordTabularMockupVisitor mockupUserStoryVisitor) {
    this.mockupUserStoryVisitor = mockupUserStoryVisitor;
  }

  private WordTabularCroppingVisitor getCroppingUserStoryVisitor() {
    return croppingUserStoryVisitor;
  }

  private void setCroppingUserStoryVisitor(WordTabularCroppingVisitor croppingUserStoryVisitor) {
    this.croppingUserStoryVisitor = croppingUserStoryVisitor;
  }

  private WordTabularExplanationVisitor getExplanationUserStoryVisitor() {
    return explanationUserStoryVisitor;
  }

  private void setExplanationUserStoryVisitor(WordTabularExplanationVisitor explanationUserStoryVisitor) {
    this.explanationUserStoryVisitor = explanationUserStoryVisitor;
  }

}
