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
package org.webspeclanguage.userstories.dto;

/**
 * @author cristian.cianfagna
 */
public class ScenarioStep {

  private String interactionORnavigationCroppedImagePath;
  private String explanation;
  private String mockupImagePath;
  private String name;

  public ScenarioStep(String name, String interactionORnavigationCroppedImagePath, String explanation, String mockupImagePath) {
    this.setExplanation(explanation);
    this.setInteractionORnavigationCroppedImagePath(interactionORnavigationCroppedImagePath);
    this.setMockupImagePath(mockupImagePath);
    this.setName(name);
  }

  public String getInteractionORnavigationCroppedImagePath() {
    return interactionORnavigationCroppedImagePath;
  }

  public String getExplanation() {
    return explanation;
  }

  public String getMockupImagePath() {
    return mockupImagePath;
  }

  private void setInteractionORnavigationCroppedImagePath(String interactionORnavigationCroppedImagePath) {
    this.interactionORnavigationCroppedImagePath = interactionORnavigationCroppedImagePath;
  }

  private void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  private void setMockupImagePath(String mockupImagePath) {
    this.mockupImagePath = mockupImagePath;
  }

  public String getName() {
    return "".equals(this.getMockupImagePath()) ? "" : this.name;
  }

  private void setName(String name) {
    this.name = name;
  }

}
