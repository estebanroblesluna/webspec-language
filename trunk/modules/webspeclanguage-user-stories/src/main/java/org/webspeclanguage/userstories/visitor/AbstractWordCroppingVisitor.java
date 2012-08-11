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
import java.util.Map;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.context.MessageSource;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.factory.WmlFactory;

/**
 * @author cristian.cianfagna
 */
public abstract class AbstractWordCroppingVisitor extends AbstractCroppingVisitor {

  protected final static long LEFT_PADDING_1800 = 1800;

  private WordprocessingMLPackage wordprocessingMLPackage;
  private WmlFactory wmlFactory;

  public AbstractWordCroppingVisitor(WordprocessingMLPackage wordprocessingMLPackage, WmlFactory wmlFactory, Map<String, CroppingInfo> croppingMap, 
          File diagramFile, MessageSource messageSource, Locale locale) {
    super(croppingMap, diagramFile, messageSource, locale);
    this.setWordprocessingMLPackage(wordprocessingMLPackage);
    this.setWmlFactory(wmlFactory);
  }
  
  protected WordprocessingMLPackage getWordprocessingMLPackage() {
    return wordprocessingMLPackage;
  }
  
  private void setWordprocessingMLPackage(WordprocessingMLPackage wordprocessingMLPackage) {
    this.wordprocessingMLPackage = wordprocessingMLPackage;
  }
  
  protected WmlFactory getWmlFactory() {
    return wmlFactory;
  }

  private void setWmlFactory(WmlFactory wmlFactory) {
    this.wmlFactory = wmlFactory;
  }

}
