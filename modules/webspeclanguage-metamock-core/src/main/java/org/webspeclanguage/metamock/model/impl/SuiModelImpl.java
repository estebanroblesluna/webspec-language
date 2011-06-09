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
package org.webspeclanguage.metamock.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.metamock.model.SuiModelElement;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.translator.Template;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * Default implementation of {@link SuiModel}
 * 
 * @author Jose Matias Rivero
 */
public class SuiModelImpl implements SuiModel {

  private Map<String, Widget> controlsById;
  private Map<String, Page> pagesById;
  private Collection<Widget> controlsOutsidePages;
  private Map<String, Template> templatesById;

  SuiModelImpl(Collection<Page> pages) {
    super();
    this.setPagesById(new HashMap<String, Page>());
    this.setPages(pages);
    this.setControlsById(new HashMap<String, Widget>());
    this.setControlsOutsidePages(new ArrayList<Widget>());
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

  private void setControlsById(Map<String, Widget> controlsById) {
    this.controlsById = controlsById;
  }

  private Map<String, Widget> getControlsById() {
    return controlsById;
  }

  private void setControlsOutsidePages(Collection<Widget> controlsOutsideAnyPage) {
    this.controlsOutsidePages = controlsOutsideAnyPage;
  }

  public Collection<Widget> getControlsOutsidePages() {
    return controlsOutsidePages;
  }

  public SuiModelElement getControlById(String id) {
    return this.getControlsById().get(id);
  }

  public void registerControl(Widget c) {
    this.getControlsById().put(c.getControlId(), c);
  }

  public <T> T visit(SuiVisitor<T> metaMockVisitor) {
    return metaMockVisitor.visitModel(this);
  }

  public void addControlOutsidePages(Widget c) {
    this.getControlsOutsidePages().add(c);
  }

  public Page getPageById(String pageId) {
    return this.getPagesById().get(pageId);
  }

  public void addPage(Page p) {
    this.getPagesById().put(p.getControlId(), p);
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
