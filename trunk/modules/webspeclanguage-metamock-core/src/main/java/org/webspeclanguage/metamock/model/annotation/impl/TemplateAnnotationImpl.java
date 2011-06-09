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
package org.webspeclanguage.metamock.model.annotation.impl;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.annotation.WidgetAnnotationVisitor;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;

public class TemplateAnnotationImpl extends ControlAnnotationImpl implements TemplateAnnotation {

  private String customCompositeId;

  public TemplateAnnotationImpl(Widget control, String customCompositeId) {
    super(control);
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
