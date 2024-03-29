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
package org.webspeclanguage.mockupdd.specs.processors;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * @author Jose Matias Rivero
 */
public abstract class SuiModelProcessor {

  public abstract void process(SuiSpecsInferenceState specs);
  
  public void process(SuiModel model) {
    this.process(new SuiSpecsInferenceState(model));
  }

}
