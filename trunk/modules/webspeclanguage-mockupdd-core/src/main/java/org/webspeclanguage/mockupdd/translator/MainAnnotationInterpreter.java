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
package org.webspeclanguage.mockupdd.translator;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.translator.annotation.AnnotationInterpreter;

/**
 * {@link AnnotationInterpreter} that executes annotations after structural ones were
 * applied
 * 
 * @author Jose Matias Rivero
 */
public class MainAnnotationInterpreter extends AbstractAnnotationInterpreter implements AnnotationInterpreter {

  private SuiFactory factory;
  private Widget currentWidget;

  public MainAnnotationInterpreter(SuiFactory factory) {
    this.setFactory(factory);
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  private SuiFactory getFactory() {
    return factory;
  }

  private void setCurrentWidget(Widget c) {
    this.currentWidget = c;
  }

  private Widget getCurrentWidget() {
    return currentWidget;
  }

  @Override
  public void visitWidgetAnnotation(WidgetAnnotation ca) {
    this.setCurrentWidget(ca.getWidget());
    if (ca.getId() != null) {
      ca.getWidget().setFriendlyId(ca.getId());
    }
  }

  @Override
  public void visitGridBagLayoutAnnotation(GridBagLayoutAnnotation gridBagLayoutAnnotation) {
    CompositeWidget c = (CompositeWidget) this.getCurrentWidget();
    c.setLayout(this.getFactory().createGridBagLayout(c.getWidgets()));
  }

  @Override
  public void visitVerticalBoxLayoutAnnotation(VerticalBoxLayoutAnnotation verticalBoxLayoutAnnotation) {
    CompositeWidget c = (CompositeWidget) this.getCurrentWidget();
    c.setLayout(this.getFactory().createVerticalBoxLayout(c.getWidgets()));
  }

  public void interpreteAnnotation(SuiAnnotation annotation, SuiModel model) {
    annotation.visit(this);
  }

  @Override
  public void visitCompositeWidgetAnnotation(CompositeWidgetAnnotation cca) {
    this.visitWidgetAnnotation(cca);
    if (cca.getLayoutAnnotation() != null) {
      cca.getLayoutAnnotation().visit(this);
    }
  }

}
