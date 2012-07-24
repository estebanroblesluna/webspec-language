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
package org.webspeclanguage.webtest.assertion;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;

/**
 * An assertion of the page's title
 * 
 * @author Esteban Robles Luna
 */
public class WebAssertTitle implements WebAssertion {

  private Expression title;

  public WebAssertTitle(Expression title) {
    Validate.notNull(title);
    
    this.title = title;
  }

  public Object accept(WebTestItemVisitor webTestItemVisitor) {
    return webTestItemVisitor.visitWebAssertTitle(this);
  }

  public Expression getTitle() {
    return title;
  }
}