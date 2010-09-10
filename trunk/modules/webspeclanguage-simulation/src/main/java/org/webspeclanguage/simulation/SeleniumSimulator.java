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

import org.apache.commons.lang.Validate;
import org.selenium.SeleniumEvaluator;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.base.WidgetPropertyReference;
import org.webspeclanguage.expression.base.WidgetReference;

/**
 * A simulator based on Selenium
 * 
 * @author Esteban Robles Luna
 */
public class SeleniumSimulator extends Simulator {

	private SeleniumEvaluator evaluator;
	
	public SeleniumSimulator(Simulation simulation, SeleniumEvaluator evaluator) {
		super(simulation);
		
		Validate.notNull(evaluator);

		this.evaluator = evaluator;
		this.setDelay(3000);
	}

	@Override
	protected void simulate(SimulationItem item) {
		item.accept(new SimulationItemVisitor() {

      public Object visitExecuteAction(ExecuteAction executeAction) {
        evaluate(executeAction.getActionName(), toStrings(executeAction.getArguments()));
        return null;
      }

      public Object visitOpenMockup(OpenMockup openMockup) {
        evaluate("open", openMockup.getLocation());
        evaluate("loadSimulation", "");
        return null;
      }

      public Object visitShowGeneralDescription(ShowGeneralDescription showDescription) {
        showDescription(showDescription.getDescription());
        return null;
      }

      public Object visitShowDescriptionAt(ShowDescriptionAt showDescriptionAt) {
        showDescriptionAt(showDescriptionAt.getDescription(), showDescriptionAt.getLocation());
        return null;
      }
		});
	}
	
  private String[] toStrings(List<Expression> arguments) {
    String[] args = new String[arguments.size()];
    for (int i = 0; i < arguments.size(); i++) {
      Expression argument = arguments.get(i);
      if (argument instanceof NumberConstant) {
        args[i] = ((NumberConstant) argument).getConstant().toString();
      } else if (argument instanceof StringConstant) {
        args[i] = ((StringConstant) argument).getConstant().toString();
      } else if (argument instanceof BooleanConstant) {
        args[i] = ((BooleanConstant) argument).getConstant().toString();
      } else if (argument instanceof WidgetReference) {
        args[i] = argument.toString();
      } else if (argument instanceof WidgetPropertyReference) {
        args[i] = argument.toString();
      } else {
        args[i] = argument.toString();
      }
    }
    return args;
  }
	
	private void showDescription(String description) {
		this.evaluate("showMessage", description, getDelayAsString());
	}

	private void showDescriptionAt(String description, String location) {
		this.evaluate("showMessageWithLocator", location, description);
	}

	private String getDelayAsString() {
		return Integer.valueOf(this.getDelay()).toString();
	}
	
	private void evaluate(String functionName, String... args) {
		this.getEvaluator().evaluate(functionName, args);
	}

	private SeleniumEvaluator getEvaluator() {
		return evaluator;
	}
}