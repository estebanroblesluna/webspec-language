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

import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;


/**
 * @author José Matías Rivero
 */
public class SuiModelProcessingError {

  private SuiModelProcessor sourceProcessor;
  private SuiModelElement sourceElement;
  private String description;
  
  public SuiModelProcessingError(SuiModelProcessor sourceProcessor, SuiModelElement sourceElement, String description) {
    this.setDescription(description);
    this.setSourceProcessor(sourceProcessor);
    this.setSourceElement(sourceElement);
  }

  public SuiModelProcessor getSourceProcessor() {
    return sourceProcessor;
  }

  private void setSourceProcessor(SuiModelProcessor sourceProcessor) {
    this.sourceProcessor = sourceProcessor;
  }

  public SuiModelElement getSourceElement() {
    return sourceElement;
  }

  private void setSourceElement(SuiModelElement sourceElement) {
    this.sourceElement = sourceElement;
  }

  public String getDescription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }

}
