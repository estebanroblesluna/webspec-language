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
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.NamedObject;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.cropping.ImageCroppingUtil;
import org.webspeclanguage.userstories.impl.HtmlStrategyConstants;
import org.webspeclanguage.userstories.response.FileResource;
import org.webspeclanguage.userstories.response.HtmlUserStoryGenerationResponse.Builder;

/**
 * @author cristian.cianfagna
 */
public class HtmlCroppingVisitor extends AbstractCroppingVisitor implements HtmlStrategyConstants {
  
  private final static Logger LOGGER = Logger.getLogger(HtmlCroppingVisitor.class);

  private Builder htmlGenarationResponseBuilder;

  public HtmlCroppingVisitor(Builder htmlGenarationResponseBuilder, Map<String, CroppingInfo> croppingMap, File diagramFile,
          MessageSource messageSource, Locale locale) {
    super(croppingMap, diagramFile, messageSource, locale);
    this.setHtmlGenarationResponseBuilder(htmlGenarationResponseBuilder);
  }

  public Object visitInteraction(Interaction interaction) {
    FileResource fileResource = this.processImage(interaction.getName());
    this.getHtmlGenarationResponseBuilder().addInteraction(fileResource);
    return IMG_INTERACTIONS_DIRECTORY + "/" + fileResource.getFileName();
  }

  public Object visitNavigation(Navigation navigation) {
    return this.visit(navigation);
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return "";
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return this.visit(richBehavior);
  }

  private Object visit(NamedObject namedObject) {
    FileResource fileResource = this.processImage(namedObject.getName());
    this.getHtmlGenarationResponseBuilder().addNavigation(fileResource);
    return IMG_NAVIGATIONS_DIRECTORY + fileResource.getFileName();
  }

  private FileResource processImage(String name) {
    CroppingInfo croppingInfo = getCroppingMap().get(name);
    try {
      ByteArrayOutputStream scenarioStepByteArrayOutputStream = ImageCroppingUtil.cropImage(this.getDiagramFile(), croppingInfo);
      return new FileResource("/" + name + ".png", scenarioStepByteArrayOutputStream);
    } catch (Exception e) {
      LOGGER.error(e);
      throw new RuntimeException(e);
    }
  }

  private Builder getHtmlGenarationResponseBuilder() {
    return htmlGenarationResponseBuilder;
  }
  
  private void setHtmlGenarationResponseBuilder(Builder htmlGenarationResponseBuilder) {
    this.htmlGenarationResponseBuilder = htmlGenarationResponseBuilder;
  }

}
