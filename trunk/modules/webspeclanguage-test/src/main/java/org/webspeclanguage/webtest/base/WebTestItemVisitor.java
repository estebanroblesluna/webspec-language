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
package org.webspeclanguage.webtest.base;

import org.webspeclanguage.webtest.action.WebClick;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebType;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertExpression;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;

/**
 * A visitor of test items
 * 
 * @author Esteban Robles Luna
 */
public interface WebTestItemVisitor {

  /**
   * Visits a {@link WebOpenUrl}
   * 
   * @param webOpenUrl the item to visit
   * @return the result of visiting the item
   */
  Object visitWebOpenUrl(WebOpenUrl webOpenUrl);

  /**
   * Visits a {@link WebCreateVariableFromExpression}
   * 
   * @param webCreateVariableFromExpression the item to visit
   * @return the result of visiting the item
   */
  Object visitWebCreateVariableFromExpression(WebCreateVariableFromExpression webCreateVariableFromExpression);

  /**
   * Visits a {@link WebAssertTitle}
   * 
   * @param assertTitle the item to visit
   * @return the result of visiting the item
   */
  Object visitWebAssertTitle(WebAssertTitle assertTitle);

  /**
   * Visits a {@link WebAssertExpression}
   * 
   * @param webAssertExpression the item to visit
   * @return the result of visiting the item
   */
  Object visitWebAssertExpression(WebAssertExpression webAssertExpression);

  /**
   * Visits a {@link WebExpression}
   * 
   * @param webExpression the item to visit
   * @return the result of visiting the item
   */
  Object visitWebExpression(WebExpression webExpression);

  /**
   * Visits a {@link WebWaitPageToLoad}
   * 
   * @param webWaitPageToLoad the item to visit
   * @return the result of visiting the item
   */
  Object visitWebWaitPageToLoad(WebWaitPageToLoad webWaitPageToLoad);

  /**
   * Visits a {@link WebClick}
   * 
   * @param webClick the item to visit
   * @return the result of visiting the item
   */
  Object visitWebClick(WebClick webClick);

  /**
   * Visits a {@link WebType}
   * 
   * @param webType the item to visit
   * @return the result of visiting the item
   */
  Object visitWebType(WebType webType);
}