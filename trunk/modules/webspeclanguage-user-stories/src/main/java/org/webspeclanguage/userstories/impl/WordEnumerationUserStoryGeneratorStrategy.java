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
import org.docx4j.wml.P;
import org.docx4j.wml.Tc;
import org.springframework.context.ApplicationContext;
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
 * Strategy implementation that generate content in sections.
 * 
 * @author cristian.cianfagna
 */
public class WordEnumerationUserStoryGeneratorStrategy extends AbstractWordUserStoryGenerator {

  private final static Logger LOGGER = Logger.getLogger(WordEnumerationUserStoryGeneratorStrategy.class);
  private final static long LEFT_PADDING_1800 = 1800;

  private long subsectionFontSize;
  private CroppingUserStoryVisitor croppingUserStoryVisitor;
  private ExplanationUserStoryVisitor explanationUserStoryVisitor;
  private MockupUserStoryVisitor mockupUserStoryVisitor;

  public WordEnumerationUserStoryGeneratorStrategy(ApplicationContext applicationContext) {
    super(applicationContext);
    this.setSubsectionFontSize(Long.valueOf(PropertyUtil.getProperty("userstory.wns.page.fontsize.subsections")));
  }

  @Override
  protected void initialize(WordprocessingMLPackage wordprocessingMLPackage, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale locale)
          throws Exception {
    this.basicInitialize(wordprocessingMLPackage, croppingMap, diagramFile, locale);
    this.setCroppingUserStoryVisitor(new CroppingUserStoryVisitor());
    this.setExplanationUserStoryVisitor(new ExplanationUserStoryVisitor());
    this.setMockupUserStoryVisitor(new MockupUserStoryVisitor());
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

  private Tc getInteractionExplanation(Interaction interaction) {
    String interactionDiagramExplanationText = this.getMessage("userstory.wen.section.interaction.diagram.explanation", new String[] { interaction.getName() });
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().createNumberingP(3, 2, interactionDiagramExplanationText, true, getSubsectionFontSize()));
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.interaction.where"));
    sb.append(" '");
    sb.append(interaction.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.interaction.ableto"));
    sb.append(" ");
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP(sb.toString(), LEFT_PADDING_1800));
    StringBuilder transitionSB;
    for (Transition transition : interaction.getForwardTransitions()) {
      transitionSB = new StringBuilder();
      transitionSB.append("'");
      transitionSB.append(transition.getName());
      transitionSB.append("'");
      this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, transitionSB.toString()));
    }
    return null;
  }
  private Tc getNavigationExplanation(Navigation navigation) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMessage("userstory.wts1.navigation.to"));
    sb.append(" '");
    sb.append(navigation.getName());
    sb.append("' ");
    sb.append(this.getMessage("userstory.wts1.navigation.actions"));
    sb.append(" ");
    String navigationDiagramExplanationText = this.getMessage("userstory.wen.section.navigation.diagram.explanation", new String[] { navigation.getName() });
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().createNumberingP(3, 2, navigationDiagramExplanationText, true, getSubsectionFontSize()));
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP(sb.toString(), LEFT_PADDING_1800));
    String actionString;
    for (Action action : navigation.getActions()) {
      actionString = (String) action.accept(new ActionVisitor() {

        public Object visitExpressionAction(ExpressionAction expressionAction) {
          return getActionExplanation(expressionAction);
        }
        public Object visitLetVariable(LetVariable letVariable) {
          return getActionExplanation(letVariable);
        }
      });
      this.getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(2, 3, actionString));
    }
    return null;
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
  public class CroppingUserStoryVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      String interactionText = getMessage("userstory.wen.section.interaction", new String[] { interaction.getName() });
      P p = getWmlFactory().createNumberingP(3, 1, interactionText, true, getSubsectionFontSize());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(p);

      String interactionDiagramText = getMessage("userstory.wen.section.interaction.diagram", new String[] { interaction.getName() });
      P pp = getWmlFactory().createNumberingP(3, 2, interactionDiagramText, true, getSubsectionFontSize());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(pp);

      CroppingInfo croppingInfo = getCroppingMap().get(interaction.getName());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getP(croppingInfo));

      return null;
    }
    public Object visitNavigation(Navigation navigation) {
      String navigationText = getMessage("userstory.wen.section.navigation", new String[] { navigation.getName() });
      P p = getWmlFactory().createNumberingP(3, 1, navigationText, true, getSubsectionFontSize());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(p);

      String navigationDiagramText = getMessage("userstory.wen.section.navigation.diagram", new String[] { navigation.getName() });
      P pp = getWmlFactory().createNumberingP(3, 2, navigationDiagramText, true, getSubsectionFontSize());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(pp);

      CroppingInfo croppingInfo = getCroppingMap().get(navigation.getName());
      getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getP(croppingInfo));

      return null;
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return getWmlFactory().createP();
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      CroppingInfo croppingInfo = getCroppingMap().get(richBehavior.getName());
      return this.getP(croppingInfo);
    }
    private P getP(CroppingInfo croppingInfo) {
      ByteArrayOutputStream byteArrayOutputStream = null;
      P p = null;
      try {
        byteArrayOutputStream = ImageCroppingUtil.cropImage(getDiagramFile(), croppingInfo);
        p = getWmlFactory().getImage(getWordprocessingMLPackage(), byteArrayOutputStream, LEFT_PADDING_1800);
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
  public class ExplanationUserStoryVisitor implements PathItemVisitor {

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
  public class MockupUserStoryVisitor implements PathItemVisitor {

    public Object visitInteraction(Interaction interaction) {
      String mockupFilePath = interaction.getMockupFile();
      if (StringUtils.isNotEmpty(mockupFilePath)) {
        File mockupFile = new File(mockupFilePath);
        if (mockupFile.exists()) {
          try {
            String mockText = getMessage("userstory.wen.section.interaction.mockup", new String[] { interaction.getName() });
            getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createNumberingP(3, 2, mockText, true, getSubsectionFontSize()));

            getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().getImage(getWordprocessingMLPackage(), mockupFile, LEFT_PADDING_1800));
            getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createP());
            return null;
          } catch (Exception e) {
            LOGGER.error(e);
            getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createP());
            return null;
          }
        }
      }
      getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createP());
      return null;
    }
    public Object visitNavigation(Navigation navigation) {
      getWordprocessingMLPackage().getMainDocumentPart().addObject(getWmlFactory().createP());
      return null;
    }
    public Object visitOperationReference(OperationReference operationReference) {
      return getWmlFactory().createP();
    }
    public Object visitRichBehavior(RichBehavior richBehavior) {
      return getWmlFactory().createP();
    }
  }

  private long getSubsectionFontSize() {
    return subsectionFontSize;
  }

  private void setSubsectionFontSize(long subsectionFontSize) {
    this.subsectionFontSize = subsectionFontSize;
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

  private MockupUserStoryVisitor getMockupUserStoryVisitor() {
    return mockupUserStoryVisitor;
  }

  private void setMockupUserStoryVisitor(MockupUserStoryVisitor mockupUserStoryVisitor) {
    this.mockupUserStoryVisitor = mockupUserStoryVisitor;
  }

}
