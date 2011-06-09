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
import org.webspeclanguage.metamock.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.WidgetAnnotationVisitor;
import org.webspeclanguage.metamock.model.annotation.LayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;

public class CompositeWidgetAnnotationImpl extends WidgetAnnotationImpl implements CompositeWidgetAnnotation {

  private LayoutAnnotation layoutAnnotation;
  private TemplateInstantiationAnnotation templateInstantiationAnnotation;
  private RepetitionAnnotation repetitionAnnotation;

  public CompositeWidgetAnnotationImpl(Widget control, String id, TemplateInstantiationAnnotation templateInstantiationAnnotation,
          RepetitionAnnotation repetitionAnnotation, LayoutAnnotation layoutAnnotation) {
    super(control, id);
    this.setTemplateInstantiationAnnotation(templateInstantiationAnnotation);
    this.setLayoutAnnotation(layoutAnnotation);
    this.setRepetitionAnnotation(repetitionAnnotation);
  }

  private void setLayoutAnnotation(LayoutAnnotation layoutAnnotation) {
    this.layoutAnnotation = layoutAnnotation;
  }

  public final LayoutAnnotation getLayoutAnnotation() {
    return layoutAnnotation;
  }

  private void setTemplateInstantiationAnnotation(TemplateInstantiationAnnotation templateInstantiationAnnotation) {
    this.templateInstantiationAnnotation = templateInstantiationAnnotation;
  }

  public final TemplateInstantiationAnnotation getTemplateInstantiationAnnotation() {
    return templateInstantiationAnnotation;
  }

  private void setRepetitionAnnotation(RepetitionAnnotation repetitionAnnotation) {
    this.repetitionAnnotation = repetitionAnnotation;
  }

  public final RepetitionAnnotation getRepetitionAnnotation() {
    return repetitionAnnotation;
  }

  public final void visit(WidgetAnnotationVisitor cav) {
    cav.visitCompositeControlAnnotation(this);
  }

}
