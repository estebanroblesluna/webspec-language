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

import org.apache.commons.lang.Validate;
import org.selenium.SeleniumEvaluator;
import org.webspeclanguage.simulation.SeleniumSimulator;
import org.webspeclanguage.simulation.Simulation;
import org.webspeclanguage.simulation.Simulator;

/**
 * A factory for obtaining {@link SeleniumSimulator}
 * 
 * @author Esteban Robles Luna
 */
public class SeleniumSimulatorFactory extends SimulatorFactory {

	private SeleniumEvaluator evaluator;
	
	public SeleniumSimulatorFactory(SeleniumEvaluator evaluator) {
	  Validate.notNull(evaluator);
	  
	  this.evaluator = evaluator;
	}
	
	@Override
	protected Simulator createSimulatorFor(Simulation simulation) {
		return new SeleniumSimulator(simulation, this.getEvaluator());
	}

	private SeleniumEvaluator getEvaluator() {
		return evaluator;
	}
}