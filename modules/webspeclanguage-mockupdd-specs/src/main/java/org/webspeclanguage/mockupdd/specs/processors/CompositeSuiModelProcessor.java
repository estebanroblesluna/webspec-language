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

import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;

/**
 * @author José Matías Rivero
 */
public class CompositeSuiModelProcessor extends SuiModelProcessor {

  private List<SuiModelProcessor> processors;

  public CompositeSuiModelProcessor(List<SuiModelProcessor> processors) {
    super();
    this.processors = processors;
  }
  
  public CompositeSuiModelProcessor(SuiModelProcessor... processors) {
    super();
    this.processors = Arrays.asList(processors);
  }

  @Override
  public void process(SuiSpecsInferenceState specs) {
    for (SuiModelProcessor processor : this.processors) {
      processor.process(specs);
    }
  }

}