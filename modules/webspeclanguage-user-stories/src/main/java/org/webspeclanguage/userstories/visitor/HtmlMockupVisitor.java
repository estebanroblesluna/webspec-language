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

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.impl.HtmlStrategyConstants;

/**
 * @author cristian.cianfagna
 */

public class HtmlMockupVisitor extends AbstractVisitor implements HtmlStrategyConstants {

  public HtmlMockupVisitor(MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
  }

  public Object visitInteraction(Interaction interaction) {
    return interaction.getMockupFile() != null ? interaction.getMockupFile() : "";
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

}
