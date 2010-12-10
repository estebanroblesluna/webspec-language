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
package org.webspeclanguage.simulation;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.exception.WebspecException;

/**
 * A simulator is responsible of executing a {@link Simulation}
 * 
 * @author Esteban Robles Luna
 */
public abstract class Simulator {

	private Simulation simulation;
	private int simulationIndex;
	private int delay;
	private boolean running;
	
	protected Simulator(Simulation simulation) {
	  Validate.notNull(simulation);
	  
		this.simulationIndex = 0;
		this.simulation = simulation;
		this.running = false;
    this.delay = 1000;
	}

	public void simulate() {
		if (!this.isRunning()) {
			this.reset();
			this.running = true;
			while (this.getSimulationIndex() < this.getSimulation().getItems().size() && this.isRunning()) {
				this.step();
				this.sleep();
				this.simulationIndex++;
			}
		}
	}
	
	public void stop() {
		this.running = false;
	}
	
	protected abstract void simulate(SimulationItem item);
	
	private void sleep() {
		try {
			Thread.sleep(this.getDelay());
		} catch (InterruptedException e) {
			throw new WebspecException(e);
		}
	}

	private void step() {
	  SimulationItem item = this.getSimulation().getItems().get(this.getSimulationIndex());
		this.simulate(item);
	}

	private boolean isRunning() {
		return this.running;
	}

	private void reset() {
		this.simulationIndex = 0;
	}

	protected int getSimulationIndex() {
		return simulationIndex;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	private Simulation getSimulation() {
		return simulation;
	}
}
