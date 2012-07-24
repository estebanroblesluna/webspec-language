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
package org.webspeclanguage.mockupdd.sui.model.annotation.impl;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotationVisitor;

public class TemplateInstantiationAnnotationImpl extends SuiAnnotationImpl implements TemplateInstantiationAnnotation {

  private String templateId;
  private String templateContainerId;
  private String placeholderId;

  public TemplateInstantiationAnnotationImpl(Widget widget, String templateId, String templateContainerId, String placeholderId) {
    super(widget);
    this.setTemplateId(templateId);
    this.setTemplateContainerId(templateContainerId);
    this.setPlaceholderId(placeholderId);
  }

  private void setTemplateId(String parentId) {
    this.templateId = parentId;
  }

  public String getTemplateId() {
    return templateId;
  }

  private void setPlaceholderId(String parentPlaceholderId) {
    this.placeholderId = parentPlaceholderId;
  }

  public String getPlaceholderId() {
    return placeholderId;
  }

  private void setTemplateContainerId(String widgetPageId) {
    this.templateContainerId = widgetPageId;
  }

  public String getTemplateContainerId() {
    return templateContainerId;
  }

  public void visit(WidgetAnnotationVisitor cav) {
    cav.visitTemplateInstantiation(this);
  }

}
