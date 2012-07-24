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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * An abstract class for tests
 * 
 * @author Esteban Robles Luna
 */
public abstract class WebTest {

  private String name;
  private List<WebTestItem> setUpItems;

  protected WebTest(String name) {
    this.name = name;
    this.setUpItems = new ArrayList<WebTestItem>();
  }

  public abstract Object accept(WebTestVisitor visitor);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    Validate.notNull(name);
    
    this.name = name;
  }

  public void addSetUpItem(WebTestItem item) {
    this.getSetUpItems().add(item);
  }

  public List<WebTestItem> getSetUpItems() {
    return setUpItems;
  }
}