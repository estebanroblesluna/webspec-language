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
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotationVisitor;

public class TemplateAnnotationImpl extends SuiAnnotationImpl implements TemplateAnnotation {

  private String customCompositeId;

  public TemplateAnnotationImpl(Widget widget, String customCompositeId) {
    super(widget);
    this.setCustomCompositeId(customCompositeId);
  }

  public void visit(WidgetAnnotationVisitor cav) {
    cav.visitTemplateAnnotation(this);
  }

  private void setCustomCompositeId(String customCompositeId) {
    this.customCompositeId = customCompositeId;
  }

  public String getTemplateId() {
    return customCompositeId;
  }

}
