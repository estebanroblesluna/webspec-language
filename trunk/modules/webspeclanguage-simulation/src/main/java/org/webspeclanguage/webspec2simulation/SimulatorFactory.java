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
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.base.Diagram;
import org.webspeclanguage.simulation.Simulation;
import org.webspeclanguage.simulation.Simulator;

/**
 * An abstract class for obtaining simulators
 * 
 * @author Esteban Robles Luna
 */
public abstract class SimulatorFactory {

	public List<Simulator> getSimulators(Diagram diagram, String homePath) {
	  Validate.notNull(diagram);
	  Validate.notNull(homePath);
	  
		List<Simulator> simulators = new ArrayList<Simulator>();
		SimulationGenerationResult  result = this.generateSimulations(diagram, homePath);
		for (int i = 0; i < result.getSize(); i++) {
			Simulation simulation = result.getSimulation(i);
			simulators.add(this.createSimulatorFor(simulation));
		}
		return simulators;
	}
	
	protected abstract Simulator createSimulatorFor(Simulation simulation);

	private SimulationGenerationResult generateSimulations(Diagram diagram, String homePath) {
		WebSpec2WebSimulationTransformation transformation = new WebSpec2WebSimulationTransformation(homePath);
		return transformation.transform(diagram);
	}
}