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

import java.io.File;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public class WordEnumerationMockupVisitor extends AbstractWordMockupVisitor {

  private final static Logger LOGGER = Logger.getLogger(WordEnumerationMockupVisitor.class);
  private final static long LEFT_PADDING_1800 = 1800;
  private final static long WIDTH_IN_TWIPS_4000 = 4000;

  private long subsectionFontSize;

  public WordEnumerationMockupVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, long subsectionFontSize, 
          MessageSource messageSource, Locale locale) {
    super(wordprocessingMLPackage, wmlFactory, messageSource, locale);
    this.setSubsectionFontSize(subsectionFontSize);
  }

  public Object visitInteraction(Interaction interaction) {
    String mockupFilePath = interaction.getMockupFile();
    if (StringUtils.isNotEmpty(mockupFilePath)) {
      File mockupFile = new File(mockupFilePath);
      if (mockupFile.exists()) {
        try {
          String mockText = this.getMessage("userstory.wen.section.interaction.mockup", new String[] { interaction.getName() });
          this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
                  this.getWmlFactory().createNumberingP(3, 2, mockText, true, this.getSubsectionFontSize()));

          this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
                  this.getWmlFactory().getImage(getWordprocessingMLPackage(), 
                		  mockupFile, LEFT_PADDING_1800, WIDTH_IN_TWIPS_4000));
          this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
                  this.getWmlFactory().createP());
          return null;
        } catch (Exception e) {
          LOGGER.error(e);
          this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP());
          return null;
        }
      }
    }
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(this.getWmlFactory().createP());
    return null;
  }

  public Object visitNavigation(Navigation navigation) {
    this.getWordprocessingMLPackage().getMainDocumentPart().addObject(
            this.getWmlFactory().createP());
    return null;
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return this.getWmlFactory().createP();
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return this.getWmlFactory().createP();
  }
  
  private long getSubsectionFontSize() {
    return subsectionFontSize;
  }

  private void setSubsectionFontSize(long subsectionFontSize) {
    this.subsectionFontSize = subsectionFontSize;
  }

}
