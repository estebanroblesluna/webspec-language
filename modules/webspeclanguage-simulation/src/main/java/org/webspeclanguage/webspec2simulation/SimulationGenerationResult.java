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
package org.webspeclanguage.webspec2simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.base.WebSpecPath;
import org.webspeclanguage.simulation.Simulation;

/**
 * A class that represents the result of computing simulations
 * over a diagram
 * 
 * @author Esteban Robles Luna
 */
public class SimulationGenerationResult {

  private String name;
  private Map<WebSpecPath, Simulation> generatedSimulations;
  private List<Simulation> simulations;
  
  public SimulationGenerationResult(String name) {
    Validate.notNull(name);
    
    this.name = name;
    this.simulations = new ArrayList<Simulation>();
    this.generatedSimulations = new HashMap<WebSpecPath, Simulation>();
  }

  public void addSimulation(WebSpecPath path, Simulation result) {
    Validate.notNull(path);
    Validate.notNull(result);
    
    this.getGeneratedSimulations().put(path, result);
    this.getSimulations().add(result);
  }

  public int getSize() {
    return this.getSimulations().size();
  }

  public Simulation getSimulation(int i) {
    return this.getSimulations().get(i);
  }

  public String getName() {
    return name;
  }

  private Map<WebSpecPath, Simulation> getGeneratedSimulations() {
    return generatedSimulations;
  }

  private List<Simulation> getSimulations() {
    return simulations;
  }
}
