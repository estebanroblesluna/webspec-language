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
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotationVisitor;

public class WidgetAnnotationImpl implements WidgetAnnotation {

  private Widget widget;
  private String id;

  public WidgetAnnotationImpl(Widget widget, String id) {
    super();
    this.setWidget(widget);
    this.setId(id);
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final String getId() {
    return id;
  }

  private void setWidget(Widget widget) {
    this.widget = widget;
  }

  public final Widget getWidget() {
    return widget;
  }

  public void visit(WidgetAnnotationVisitor cav) {
    cav.visitWidgetAnnotation(this);
  }

}
