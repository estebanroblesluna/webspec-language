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
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.impl.Template;
import org.webspeclanguage.mockupdd.translator.annotation.AnnotationInterpreter;

/**
 * {@link AnnotationInterpreter} used in a first stage of annotation processing.
 * Takes into account template instantiation and repetition inferente 
 * 
 * @author Jose Matias Rivero
 */
public class DefaultAnnotationPreprocessor extends AbstractAnnotationInterpreter implements AnnotationInterpreter {

  private SuiFactory factory;
  private RepetitionDetector repetitionDetector;

  public DefaultAnnotationPreprocessor(SuiFactory factory, RepetitionDetector repetitionDetector) {
    super();
    this.setFactory(factory);
    this.setRepetitionDetector(repetitionDetector);
  }

  public void interpreteAnnotation(SuiAnnotation annotation, SuiModel model) {
    this.setCurrentModel(model);
    annotation.visit(this);
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  private SuiFactory getFactory() {
    return factory;
  }

  private void setRepetitionDetector(RepetitionDetector repetitionDetector) {
    this.repetitionDetector = repetitionDetector;
  }

  private RepetitionDetector getRepetitionDetector() {
    return repetitionDetector;
  }

  @Override
  public void visitTemplateAnnotation(TemplateAnnotation templateAnnotation) {
    this.getCurrentModel().addTemplate(new Template((CompositeWidget) templateAnnotation.getWidget(), templateAnnotation.getTemplateId(), this.getFactory()));
  }

  @Override
  public void visitRepetitionAnnotation(RepetitionAnnotation repetitionAnnotation) {
    CompositeWidget cc = (CompositeWidget) repetitionAnnotation.getWidget();
    Repetition r = this.getRepetitionDetector().detectRepetition(cc);
    if (r != null) {
      cc.removeAllChildren();
      cc.addChild(r);
    }
  }

  @Override
  public void visitCompositeWidgetAnnotation(CompositeWidgetAnnotation cca) {
    if (cca.getRepetitionAnnotation() != null) {
      cca.getRepetitionAnnotation().visit(this);
    }
  }

  @Override
  public void startingAnnotationInterpretationIn(SuiModel model) {

  }

}
