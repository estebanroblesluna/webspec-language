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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.impl.HtmlStrategyConstants;
import org.webspeclanguage.userstories.response.FileResource;
import org.webspeclanguage.userstories.response.HtmlUserStoryGenerationResponse.Builder;

/**
 * @author cristian.cianfagna
 */

public class HtmlMockupVisitor extends AbstractVisitor implements HtmlStrategyConstants {

  private final static Logger LOGGER = Logger.getLogger(HtmlMockupVisitor.class);

  private Builder htmlGenarationResponseBuilder;

  public HtmlMockupVisitor(Builder htmlGenarationResponseBuilder, MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
    this.setHtmlGenarationResponseBuilder(htmlGenarationResponseBuilder);
  }

  public Object visitInteraction(Interaction interaction) {
    String mockupFilePath = interaction.getMockupFile();
    if (StringUtils.isNotEmpty(mockupFilePath)) {
      File mockupFile = new File(mockupFilePath);
      if (mockupFile.exists()) {
        try {
          ByteArrayOutputStream mockupByteArrayOutputStream = new ByteArrayOutputStream();
          ImageCroppingUtil.writeImage(ImageCroppingUtil.readImage(mockupFile), mockupByteArrayOutputStream, "png");
          FileResource fileResource = new FileResource("/" + interaction.getName() + ".png", mockupByteArrayOutputStream);
          this.getHtmlGenarationResponseBuilder().addMockup(fileResource);
          return IMG_MOCKUPS_DIRECTORY + fileResource.getFileName();
        } catch (Exception e) {
          LOGGER.error(e);
          return "";
        }
      }
    }
    return "";
  }

  public Object visitNavigation(Navigation navigation) {
    return "";
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return "";
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return "";
  }

  private Builder getHtmlGenarationResponseBuilder() {
    return htmlGenarationResponseBuilder;
  }
  
  private void setHtmlGenarationResponseBuilder(Builder htmlGenarationResponseBuilder) {
    this.htmlGenarationResponseBuilder = htmlGenarationResponseBuilder;
  }

}
