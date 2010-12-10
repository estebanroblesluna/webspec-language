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

import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.translator.annotation.AnnotationInterpreter;

/**
 * Interpretation strategy for {@link ControlAnnotation}s
 * 
 * @author Jose Matias Rivero
 */
public abstract class AbstractAnnotationInterpreter extends DefaultControlAnnotationVisitor implements AnnotationInterpreter {

  private MetaMockModel currentModel;

  public AbstractAnnotationInterpreter() {
    super();
  }

  protected final void setCurrentModel(MetaMockModel currentModel) {
    this.currentModel = currentModel;
  }

  public final MetaMockModel getCurrentModel() {
    return currentModel;
  }

  public void startingAnnotationInterpretationIn(MetaMockModel model) {
    this.setCurrentModel(model);
  }

}
