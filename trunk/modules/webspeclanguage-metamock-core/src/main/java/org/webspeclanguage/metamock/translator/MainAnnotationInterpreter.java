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

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.annotation.CompositeControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.ControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.metamock.model.annotation.MetaMockAnnotation;
import org.webspeclanguage.metamock.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.metamock.translator.annotation.AnnotationInterpreter;

/**
 * {@link AnnotationInterpreter} that executes annotations after structural ones were
 * applied
 * 
 * @author Jose Matias Rivero
 */
public class MainAnnotationInterpreter extends AbstractAnnotationInterpreter implements AnnotationInterpreter {

  private MetaMockFactory factory;
  private UIControl currentControl;

  public MainAnnotationInterpreter(MetaMockFactory factory) {
    this.setFactory(factory);
  }

  private void setFactory(MetaMockFactory factory) {
    this.factory = factory;
  }

  private MetaMockFactory getFactory() {
    return factory;
  }

  private void setCurrentControl(UIControl c) {
    this.currentControl = c;
  }

  private UIControl getCurrentControl() {
    return currentControl;
  }

  @Override
  public void visitControlAnnotation(ControlAnnotation ca) {
    this.setCurrentControl(ca.getControl());
    if (ca.getId() != null) {
      ca.getControl().setFriendlyId(ca.getId());
    }
  }

  @Override
  public void visitGridBagLayoutAnnotation(GridBagLayoutAnnotation gridBagLayoutAnnotation) {
    CompositeControl c = (CompositeControl) this.getCurrentControl();
    c.setLayout(this.getFactory().createGridBagLayout(c.getControls()));
  }

  @Override
  public void visitVerticalBoxLayoutAnnotation(VerticalBoxLayoutAnnotation verticalBoxLayoutAnnotation) {
    CompositeControl c = (CompositeControl) this.getCurrentControl();
    c.setLayout(this.getFactory().createVerticalBoxLayout(c.getControls()));
  }

  public void interpreteAnnotation(MetaMockAnnotation annotation, MetaMockModel model) {
    annotation.visit(this);
  }

  @Override
  public void visitCompositeControlAnnotation(CompositeControlAnnotation cca) {
    this.visitControlAnnotation(cca);
    if (cca.getLayoutAnnotation() != null) {
      cca.getLayoutAnnotation().visit(this);
    }
  }

}
