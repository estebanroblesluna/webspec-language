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
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.P;
import org.docx4j.wml.STVerticalJc;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.NamedObject;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public class WordTabularCroppingVisitor extends AbstractWordCroppingVisitor {
  
  private final static Logger LOGGER = Logger.getLogger(WordTabularCroppingVisitor.class);
  
  private int cellWidthTwips;

  public WordTabularCroppingVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, Map<String, CroppingInfo> croppingMap,
          int cellWidthTwips, File diagramFile, MessageSource messageSource, Locale locale) {
    super(wordprocessingMLPackage, wmlFactory, croppingMap, diagramFile, messageSource, locale);
    this.setCellWidthTwips(cellWidthTwips);
  }

  public Object visitInteraction(Interaction interaction) {
    return this.visit(interaction);
  }

  public Object visitNavigation(Navigation navigation) {
    return visit(navigation);
  }

  private Object visit(NamedObject namedObject) {
    CroppingInfo croppingInfo = getCroppingMap().get(namedObject.getName());
    return this.getWmlFactory().createTC(this.getP(croppingInfo), 
            JcEnumeration.CENTER, STVerticalJc.CENTER, this.getCellWidthTwips());
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return this.getWmlFactory().createTC(this.getWmlFactory().createP(), this.getCellWidthTwips());
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    CroppingInfo croppingInfo = this.getCroppingMap().get(richBehavior.getName());
    return this.getWmlFactory().createTC(this.getP(croppingInfo), this.getCellWidthTwips());
  }

  private P getP(CroppingInfo croppingInfo) {
    ByteArrayOutputStream byteArrayOutputStream = null;
    P p = null;
    try {
      byteArrayOutputStream = ImageCroppingUtil.cropImage(this.getDiagramFile(), croppingInfo);
      p = getWmlFactory().getImage(getWordprocessingMLPackage(), byteArrayOutputStream, this.getCellWidthTwips());
    } catch (Exception e) {
      LOGGER.error(e);
      return getWmlFactory().createP();
    }
    return p;
  }

  private int getCellWidthTwips() {
    return cellWidthTwips;
  }
  
  private void setCellWidthTwips(int cellWidthTwips) {
    this.cellWidthTwips = cellWidthTwips;
  }

}
