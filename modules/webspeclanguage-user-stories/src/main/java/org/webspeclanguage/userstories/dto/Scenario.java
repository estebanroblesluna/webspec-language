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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cristian.cianfagna
 */
public class Scenario {

  private String id;
  private String description;
  private String diagramFilePath;
  private List<ScenarioStep> scenarioSteps;

  public Scenario(String id, String description, String diagramFilePath) {
    this.setDescription(description);
    this.setDiagramFilePath(diagramFilePath);
    this.setScenarioSteps(new ArrayList<ScenarioStep>());
    this.setId(id);
  }

  public void addScenarioStep(ScenarioStep scenarioStep) {
    this.scenarioSteps.add(scenarioStep);
  }

  public String getDescription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }

  public String getDiagramFilePath() {
    return diagramFilePath;
  }

  private void setDiagramFilePath(String diagramFilePath) {
    this.diagramFilePath = diagramFilePath;
  }
  
  public List<ScenarioStep> getScenarioSteps() {
    return Collections.unmodifiableList(this.scenarioSteps);
  }
  
  private void setScenarioSteps(List<ScenarioStep> scenarioSteps) {
    this.scenarioSteps = scenarioSteps;
  }

  public String getId() {
	return id;
  }

  private void setId(String id) {
	this.id = id;
  }

}
