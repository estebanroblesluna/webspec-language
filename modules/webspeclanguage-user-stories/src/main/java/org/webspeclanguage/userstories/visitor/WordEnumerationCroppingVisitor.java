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
package org.webspeclanguage.userstories.visitor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public class WordEnumerationCroppingVisitor extends AbstractWordCroppingVisitor {

  private final static Logger LOGGER = Logger.getLogger(WordEnumerationCroppingVisitor.class);
  private final static long WIDTH_IN_TWIPS_4000 = 4000;

  private long subsectionFontSize;

  public WordEnumerationCroppingVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, 
          Map<String, CroppingInfo> croppingMap, File diagramFile, long subsectionFontSize, MessageSource messageSource, Locale locale) {
    super(wordprocessingMLPackage, wmlFactory, croppingMap, diagramFile, messageSource, locale);
    this.setSubsectionFontSize(subsectionFontSize);
  }

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
      byteArrayOutputStream = ImageCroppingUtil.cropImage(this.getDiagramFile(), croppingInfo);
      p = getWmlFactory().getImage(getWordprocessingMLPackage(), byteArrayOutputStream, LEFT_PADDING_1800, WIDTH_IN_TWIPS_4000);
    } catch (Exception e) {
      LOGGER.error(e);
      return getWmlFactory().createP();
    }
    return p;
  }
  
  private long getSubsectionFontSize() {
    return subsectionFontSize;
  }
  
  private void setSubsectionFontSize(long subsectionFontSize) {
    this.subsectionFontSize = subsectionFontSize;
  }
}
