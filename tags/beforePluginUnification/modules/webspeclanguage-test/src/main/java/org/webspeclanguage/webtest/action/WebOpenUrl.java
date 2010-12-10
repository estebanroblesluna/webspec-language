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
package org.webspeclanguage.webtest.action;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;

/**
 * An action that opens a url in the browser
 * 
 * @author Esteban Robles Luna
 */
public class WebOpenUrl implements WebAction {

  private String url;

  public WebOpenUrl(String url) {
    Validate.notNull(url);
    
    this.url = url;
  }

  public Object accept(WebTestItemVisitor webTestItemVisitor) {
    return webTestItemVisitor.visitWebOpenUrl(this);
  }

  public String getUrl() {
    return url;
  }
}