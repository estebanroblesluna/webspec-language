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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.springframework.context.MessageSource;
import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;

/**
 * @author cristian.cianfagna
 */
public class HtmlExplanationVisitor extends AbstractExplanationVisitor {

  private static final String TEMPLATES = "org/webspeclanguage/userstories/impl/";
  StringTemplateGroup htmlTemplateGroup;

  public HtmlExplanationVisitor(StringTemplateGroup htmlTemplateGroup, MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
    this.setHtmlTemplateGroup(htmlTemplateGroup);
  }

  public Object visitInteraction(Interaction interaction) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getExplanationHeader(interaction));
    StringTemplate htmlListTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "htmlList");
    List<String> listItems = new ArrayList<String>();
    for (Transition transition : interaction.getForwardTransitions()) {
      listItems.add(transition.getName());
    }
    htmlListTemplate.setAttribute("listItems", listItems);
    sb.append(htmlListTemplate.toString());
    return sb.toString();
  }

  public Object visitNavigation(Navigation navigation) {
    return this.visitTransition(navigation);
  }

  public Object visitOperationReference(OperationReference operationReference) {
    return "";
  }

  public Object visitRichBehavior(RichBehavior richBehavior) {
    return this.visitTransition(richBehavior);
  }

  private Object visitTransition(Transition transition) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getExplanationHeader(transition));
    String actionString;
    StringTemplate htmlListTemplate = this.getHtmlTemplateGroup().getInstanceOf(TEMPLATES + "htmlList");
    List<String> listItems = new ArrayList<String>();
    for (Action action : transition.getActions()) {
      actionString = (String) action.accept(this.getActionVisitor());
      listItems.add(actionString);
    }
    htmlListTemplate.setAttribute("listItems", listItems);
    sb.append(htmlListTemplate.toString());
    return sb.toString();
  }

  private StringTemplateGroup getHtmlTemplateGroup() {
    return htmlTemplateGroup;
  }

  private void setHtmlTemplateGroup(StringTemplateGroup htmlTemplateGroup) {
    this.htmlTemplateGroup = htmlTemplateGroup;
  }

}
