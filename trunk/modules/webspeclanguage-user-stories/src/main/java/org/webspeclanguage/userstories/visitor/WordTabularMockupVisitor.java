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
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.STVerticalJc;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public class WordTabularMockupVisitor extends AbstractWordMockupVisitor {

  private final static Logger LOGGER = Logger.getLogger(WordTabularMockupVisitor.class);

  private int cellWidthTwips;

  public WordTabularMockupVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, int cellWidthTwips,
          MessageSource messageSource, Locale locale) {
    super(wordprocessingMLPackage, wmlFactory, messageSource, locale);
    this.setCellWidthTwips(cellWidthTwips);
  }

  public Object visitInteraction(Interaction interaction) {
    String mockupFilePath = interaction.getMockupFile();
    if (StringUtils.isNotEmpty(mockupFilePath)) {
      File mockupFile = new File(mockupFilePath);
      if (mockupFile.exists()) {
        try {
          return this.getWmlFactory().createTC(
                  this.getWmlFactory().getImage(this.getWordprocessingMLPackage(), mockupFile, 0, this.getCellWidthTwips()), 
                  JcEnumeration.CENTER, STVerticalJc.CENTER,
                  getCellWidthTwips());
        } catch (Exception e) {
          LOGGER.error(e);
          return this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
        }
      }
    }
    return this.getWmlFactory().createTC(getWmlFactory().createP(), this.getCellWidthTwips());
  }

  public Object visitNavigation(Navigation navigation) {
    return this.getWmlFactory().createTC(getWmlFactory().createP(), this.getCellWidthTwips());
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
  }
  
  private int getCellWidthTwips() {
    return cellWidthTwips;
  }
  
  private void setCellWidthTwips(int cellWidthTwips) {
    this.cellWidthTwips = cellWidthTwips;
  }
}
