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
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.docx4j.model.structure.MarginsWellKnown;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.P;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;
import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.action.ActionVisitor;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.util.PropertyUtil;
import org.webspeclanguage.userstories.visitor.WordUserStoryExpressionVisitor;

/**
 * Strategy implementation that generate content in tables.
 * 
 * @author cristian.cianfagna
 */
public class WordTabularUserStoryGeneratorStrategy extends AbstractWordUserStoryGenerator {

  private final static Logger LOGGER = Logger.getLogger(WordTabularUserStoryGeneratorStrategy.class);
  private final static int COLUMNS = 3;

  private int cellWidthTwips;
  private Tbl table;
  private CroppingUserStoryVisitor croppingUserStoryVisitor;
  private ExplanationUserStoryVisitor explanationUserStoryVisitor;
  private MockupUserStoryVisitor mockupUserStoryVisitor;

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
    this.setCroppingUserStoryVisitor(new CroppingUserStoryVisitor());
    this.setExplanationUserStoryVisitor(new ExplanationUserStoryVisitor());
    this.setMockupUserStoryVisitor(new MockupUserStoryVisitor());
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

  private Tc getInteractionExplanation(Interaction interaction) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.interaction.where"));
    sb.append(" '");
    sb.append(interaction.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.interaction.ableto"));
    sb.append(" ");
    StringBuilder transitionSB;
    Tc tc = this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
    tc.getContent().add(this.getWmlFactory().createP(sb.toString()));
    for (Transition transition : interaction.getForwardTransitions()) {
      transitionSB = new StringBuilder();
      transitionSB.append("'");
      transitionSB.append(transition.getName());
      transitionSB.append("'");
      tc.getContent().add(getWmlFactory().createNumberingP(2, transitionSB.toString()));
    }
    return tc;
  }

  private Tc getNavigationExplanation(Navigation navigation) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.navigation.to"));
    sb.append(" '");
    sb.append(navigation.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.navigation.actions"));
    sb.append(" ");
    String actionString;
    Tc tc = this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
    tc.getContent().add(this.getWmlFactory().createP(sb.toString()));
    for (Action action : navigation.getActions()) {
      actionString = (String) action.accept(new ActionVisitor() {

        public Object visitExpressionAction(ExpressionAction expressionAction) {
          return getActionExplanation(expressionAction);
        }
        public Object visitLetVariable(LetVariable letVariable) {
          return getActionExplanation(letVariable);
        }
      });
      tc.getContent().add(getWmlFactory().createNumberingP(2, actionString));
    }
    return tc;
  }

  private String getActionExplanation(ExpressionAction expressionAction) {
    return (String) expressionAction.getExpression().accept(new WordUserStoryExpressionVisitor(this.getMessageSource(), this.getLocale()));
  }

  private String getActionExplanation(LetVariable letVariable) {
    StringBuilder sb = new StringBuilder();
    sb.append("userstory.wts1.navigation.action.letvariable");
    sb.append(" '");
    sb.append(letVariable.getVariableName());
    sb.append("' ");
    sb.append("userstory.wts1.navigation.action.letvariable.type");
    sb.append(" ");
    sb.append(letVariable.getType().name());
    return sb.toString();
  }

  /**
   * Visitor in charges of getting each step image from diagramImage.
   * 
   * @author cristian.cianfagna
   */
  private class CroppingUserStoryVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      CroppingInfo croppingInfo = getCroppingMap().get(interaction.getName());
      return getWmlFactory().createTC(this.getP(croppingInfo), JcEnumeration.CENTER, STVerticalJc.CENTER, getCellWidthTwips());
    }
    public Object visitNavigation(Navigation navigation) {
      CroppingInfo croppingInfo = getCroppingMap().get(navigation.getName());
      return getWmlFactory().createTC(this.getP(croppingInfo), JcEnumeration.CENTER, STVerticalJc.CENTER, getCellWidthTwips());
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      CroppingInfo croppingInfo = getCroppingMap().get(richBehavior.getName());
      return getWmlFactory().createTC(this.getP(croppingInfo), getCellWidthTwips());
    }
    private P getP(CroppingInfo croppingInfo) {
      ByteArrayOutputStream byteArrayOutputStream = null;
      P p = null;
      try {
        byteArrayOutputStream = ImageCroppingUtil.cropImage(getDiagramFile(), croppingInfo);
        p = getWmlFactory().getImage(getWordprocessingMLPackage(), byteArrayOutputStream);
      } catch (Exception e) {
        LOGGER.error(e);
        return getWmlFactory().createP();
      }
      return p;
    }
  }

  /**
   * Visitor in charges of getting each step explanation from diagram.
   * 
   * @author cristian.cianfagna
   */
  private class ExplanationUserStoryVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      return getInteractionExplanation(interaction);
    }
    public Object visitNavigation(Navigation navigation) {
      return getNavigationExplanation(navigation);
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return getWmlFactory().createP();
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      return getWmlFactory().createP();
    }
  }

  /**
   * Visitor in charges of getting mockup image from interaction.
   * 
   * @author cristian.cianfagna
   */
  private class MockupUserStoryVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      String mockupFilePath = interaction.getMockupFile();
      if (StringUtils.isNotEmpty(mockupFilePath)) {
        File mockupFile = new File(mockupFilePath);
        if (mockupFile.exists()) {
          try {
            return getWmlFactory().createTC(getWmlFactory().getImage(getWordprocessingMLPackage(), mockupFile), JcEnumeration.CENTER, STVerticalJc.CENTER,
                    getCellWidthTwips());
          } catch (Exception e) {
            LOGGER.error(e);
            return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
          }
        }
      }
      return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
    }
    public Object visitNavigation(Navigation navigation) {
      return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      return getWmlFactory().createTC(getWmlFactory().createP(), getCellWidthTwips());
    }
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

  private MockupUserStoryVisitor getMockupUserStoryVisitor() {
    return mockupUserStoryVisitor;
  }

  private void setMockupUserStoryVisitor(MockupUserStoryVisitor mockupUserStoryVisitor) {
    this.mockupUserStoryVisitor = mockupUserStoryVisitor;
  }

  private CroppingUserStoryVisitor getCroppingUserStoryVisitor() {
    return croppingUserStoryVisitor;
  }

  private void setCroppingUserStoryVisitor(CroppingUserStoryVisitor croppingUserStoryVisitor) {
    this.croppingUserStoryVisitor = croppingUserStoryVisitor;
  }

  private ExplanationUserStoryVisitor getExplanationUserStoryVisitor() {
    return explanationUserStoryVisitor;
  }

  private void setExplanationUserStoryVisitor(ExplanationUserStoryVisitor explanationUserStoryVisitor) {
    this.explanationUserStoryVisitor = explanationUserStoryVisitor;
  }

}
