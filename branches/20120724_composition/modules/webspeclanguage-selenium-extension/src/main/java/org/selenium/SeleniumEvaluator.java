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
package org.selenium;

import org.apache.commons.lang.Validate;

/**
 * An evaluator which can evaluate any function call to Selenium
 * 
 * @author Esteban Robles Luna
 */
public class SeleniumEvaluator {

  private ExtendedSelenium extendedSelenium;

  public SeleniumEvaluator(ExtendedSelenium extendedSelenium) {
    Validate.notNull(extendedSelenium);
    
    this.extendedSelenium = extendedSelenium;
  }

  public void evaluate(String functionName, String[] args) {
    this.getExtendedSelenium().execute(functionName, args);
  }

  private ExtendedSelenium getExtendedSelenium() {
    return extendedSelenium;
  }
}