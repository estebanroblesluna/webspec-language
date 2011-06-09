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

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * Default implementation of {@link Annotation}
 * 
 * @author Jose Matias Rivero
 */
public class AnnotationImpl extends WidgetImpl implements Annotation {

  private Widget targetElement;
  private String content;

  public AnnotationImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Widget targetElement, String content) {
    super(controlID, x, y, width, height);
    this.setTargetElement(targetElement);
    this.setContent(content);
  }

  public final Widget getTargetElement() {
    return this.targetElement;
  }

  public final void setTargetElement(Widget targetElement) {
    this.targetElement = targetElement;
  }

  private void setContent(String content) {
    this.content = content;
  }

  public final String getContent() {
    return content;
  }

  public final <T> T accept(SuiVisitor<T> v) {
    return v.visitAnnotation(this);
  }

  public final Widget copyConcreteControl() {
    return new AnnotationImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getTargetElement(), this.getContent());
  }

}
