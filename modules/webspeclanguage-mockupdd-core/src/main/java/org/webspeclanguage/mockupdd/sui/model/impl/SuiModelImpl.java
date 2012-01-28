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
package org.webspeclanguage.mockupdd.sui.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link SuiModel}
 * 
 * @author Jose Matias Rivero
 */
public class SuiModelImpl implements SuiModel {

  private Map<String, Widget> widgetsById;
  private Map<String, Page> pagesById;
  private Collection<Widget> widgetsOutsidePages;
  private Map<String, Template> templatesById;

  SuiModelImpl(Collection<Page> pages) {
    super();
    this.setPagesById(new HashMap<String, Page>());
    this.setPages(pages);
    this.setWidgetsById(new HashMap<String, Widget>());
    this.setWidgetsOutsidePages(new ArrayList<Widget>());
    this.setTemplatesById(new HashMap<String, Template>());
  }

  private final void setPages(Collection<Page> pages) {
    this.getPagesById().clear();
    for (Page p : pages) {
      this.addPage(p);
    }
  }

  public Collection<Page> getPages() {
    return this.getPagesById().values();
  }

  private void setWidgetsById(Map<String, Widget> widgetsById) {
    this.widgetsById = widgetsById;
  }

  private Map<String, Widget> getWidgetsById() {
    return widgetsById;
  }

  private void setWidgetsOutsidePages(Collection<Widget> widgetsOutsideAnyPage) {
    this.widgetsOutsidePages = widgetsOutsideAnyPage;
  }

  public Collection<Widget> getWidgetsOutsidePages() {
    return widgetsOutsidePages;
  }

  public SuiModelElement getWidgetById(String id) {
    return this.getWidgetsById().get(id);
  }

  public void registerWidget(Widget c) {
    this.getWidgetsById().put(c.getWidgetId(), c);
  }

  public <T> T visit(SuiVisitor<T> metaMockVisitor) {
    return metaMockVisitor.visitModel(this);
  }

  public void addWidgetOutsidePages(Widget c) {
    this.getWidgetsOutsidePages().add(c);
  }

  public Page getPageById(String pageId) {
    return this.getPagesById().get(pageId);
  }

  public void addPage(Page p) {
    this.getPagesById().put(p.getWidgetId(), p);
  }

  private void setPagesById(Map<String, Page> pagesById) {
    this.pagesById = pagesById;
  }

  private Map<String, Page> getPagesById() {
    return pagesById;
  }

  private void setTemplatesById(Map<String, Template> templatesById) {
    this.templatesById = templatesById;
  }

  private Map<String, Template> getTemplatesById() {
    return templatesById;
  }

  public void addTemplate(Template template) {
    this.getTemplatesById().put(template.getTemplateId() + "-" + template.getContents().getContainerId(), template);

  }

  public Template getTemplate(String templateId, String containerId) {
    return this.getTemplatesById().get(templateId + "-" + containerId);
  }

}
