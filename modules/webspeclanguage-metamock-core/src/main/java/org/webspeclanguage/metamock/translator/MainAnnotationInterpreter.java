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
package org.webspeclanguage.metamock.translator;

import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.SuiFactory;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.WidgetAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.SuiAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.translator.annotation.AnnotationInterpreter;

/**
 * {@link AnnotationInterpreter} that executes annotations after structural ones were
 * applied
 * 
 * @author Jose Matias Rivero
 */
public class MainAnnotationInterpreter extends AbstractAnnotationInterpreter implements AnnotationInterpreter {

  private SuiFactory factory;
  private Widget currentControl;

  public MainAnnotationInterpreter(SuiFactory factory) {
    this.setFactory(factory);
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  private SuiFactory getFactory() {
    return factory;
  }

  private void setCurrentControl(Widget c) {
    this.currentControl = c;
  }

  private Widget getCurrentControl() {
    return currentControl;
  }

  @Override
  public void visitControlAnnotation(WidgetAnnotation ca) {
    this.setCurrentControl(ca.getControl());
    if (ca.getId() != null) {
      ca.getControl().setFriendlyId(ca.getId());
    }
  }

  @Override
  public void visitGridBagLayoutAnnotation(GridBagLayoutAnnotation gridBagLayoutAnnotation) {
    CompositeWidget c = (CompositeWidget) this.getCurrentControl();
    c.setLayout(this.getFactory().createGridBagLayout(c.getControls()));
  }

  @Override
  public void visitVerticalBoxLayoutAnnotation(VerticalBoxLayoutAnnotation verticalBoxLayoutAnnotation) {
    CompositeWidget c = (CompositeWidget) this.getCurrentControl();
    c.setLayout(this.getFactory().createVerticalBoxLayout(c.getControls()));
  }

  public void interpreteAnnotation(SuiAnnotation annotation, SuiModel model) {
    annotation.visit(this);
  }

  @Override
  public void visitCompositeControlAnnotation(CompositeWidgetAnnotation cca) {
    this.visitControlAnnotation(cca);
    if (cca.getLayoutAnnotation() != null) {
      cca.getLayoutAnnotation().visit(this);
    }
  }

}
