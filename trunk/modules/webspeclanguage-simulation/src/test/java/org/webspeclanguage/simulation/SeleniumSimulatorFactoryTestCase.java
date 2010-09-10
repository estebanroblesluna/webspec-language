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

import java.util.List;

import junit.framework.TestCase;

import org.selenium.ExtendedSelenium;
import org.selenium.SeleniumEvaluator;
import org.webspeclanguage.base.WebSpecFactory;
import org.webspeclanguage.webspec2simulation.SeleniumSimulatorFactory;
import org.webspeclanguage.webspec2simulation.SimulatorFactory;

/**
 * @author Esteban Robles Luna
 */
public class SeleniumSimulatorFactoryTestCase extends TestCase {

	private SimulatorFactory factory;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.factory = new SeleniumSimulatorFactory(new SeleniumEvaluator(new ExtendedSelenium(null)));
	}

	public void testFactory1() {
		List<Simulator> simulators = this.factory.getSimulators(WebSpecFactory.getAmazonExample(), "/");
		assertEquals(1, simulators.size());
	}
	
	public void testFactory2() {
		List<Simulator> simulators = this.factory.getSimulators(WebSpecFactory.getAmazonExample2(), "/");
		assertEquals(2, simulators.size());
	}
}