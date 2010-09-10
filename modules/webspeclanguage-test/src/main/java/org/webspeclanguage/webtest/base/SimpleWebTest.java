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
import java.util.Collections;
import java.util.List;

/**
 * Represents a simple test that contains a list of test items
 * 
 * @author Esteban Robles Luna
 */
public class SimpleWebTest extends WebTest {

  private List<WebTestItem> items;

  public SimpleWebTest(String name) {
    super(name);
    
    this.items = new ArrayList<WebTestItem>();
  }

  @Override
  public Object accept(WebTestVisitor visitor) {
    return visitor.visitSimpleWebTest(this);
  }

  public void addItem(WebTestItem item) {
    this.items.add(item);
  }

  public List<WebTestItem> getItems() {
    return Collections.unmodifiableList(this.items);
  }

  public int getLength() {
    return this.getItems().size();
  }

  public WebTestItem getItem(int i) {
    return this.getItems().get(i);
  }
}
