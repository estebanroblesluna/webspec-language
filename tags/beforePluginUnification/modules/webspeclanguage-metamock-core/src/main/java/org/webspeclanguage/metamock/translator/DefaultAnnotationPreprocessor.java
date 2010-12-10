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
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.annotation.CompositeControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.MetaMockAnnotation;
import org.webspeclanguage.metamock.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateAnnotation;
import org.webspeclanguage.metamock.translator.annotation.AnnotationInterpreter;


/**
 * {@link AnnotationInterpreter} used in a first stage of annotation processing.
 * Takes into account template instantiation and repetition inferente 
 * 
 * @author Jose Matias Rivero
 */
public class DefaultAnnotationPreprocessor extends AbstractAnnotationInterpreter implements AnnotationInterpreter {

  private MetaMockFactory factory;
  private RepetitionDetector repetitionDetector;

  public DefaultAnnotationPreprocessor(MetaMockFactory factory, RepetitionDetector repetitionDetector) {
    super();
    this.setFactory(factory);
    this.setRepetitionDetector(repetitionDetector);
  }

  public void interpreteAnnotation(MetaMockAnnotation annotation, MetaMockModel model) {
    this.setCurrentModel(model);
    annotation.visit(this);
  }

  private void setFactory(MetaMockFactory factory) {
    this.factory = factory;
  }

  private MetaMockFactory getFactory() {
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
    this.getCurrentModel().addTemplate(new Template((CompositeControl) templateAnnotation.getControl(), templateAnnotation.getTemplateId(), this.getFactory()));
  }

  @Override
  public void visitRepetitionAnnotation(RepetitionAnnotation repetitionAnnotation) {
    CompositeControl cc = (CompositeControl) repetitionAnnotation.getControl();
    Repetition r = this.getRepetitionDetector().detectRepetition(cc);
    if (r != null) {
      cc.removeAllChildren();
      cc.addChild(r);
    }
  }

  @Override
  public void visitCompositeControlAnnotation(CompositeControlAnnotation cca) {
    if (cca.getRepetitionAnnotation() != null) {
      cca.getRepetitionAnnotation().visit(this);
    }
  }

  @Override
  public void startingAnnotationInterpretationIn(MetaMockModel model) {

  }

}
