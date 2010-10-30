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

import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.annotation.ControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.ControlAnnotationVisitor;

public class ControlAnnotationImpl implements ControlAnnotation {

  private UIControl control;
  private String id;

  public ControlAnnotationImpl(UIControl control, String id) {
    super();
    this.setControl(control);
    this.setId(id);
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final String getId() {
    return id;
  }

  private void setControl(UIControl control) {
    this.control = control;
  }

  public final UIControl getControl() {
    return control;
  }

  public void visit(ControlAnnotationVisitor cav) {
    cav.visitControlAnnotation(this);
  }

}
