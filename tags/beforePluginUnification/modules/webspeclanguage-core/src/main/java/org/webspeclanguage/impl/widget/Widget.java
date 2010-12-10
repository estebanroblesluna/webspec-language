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
package org.webspeclanguage.impl.widget;

/**
 * An abstract class for widgets
 * 
 * @author Esteban Robles Luna
 */
public abstract class Widget {

  private String location;
  private String id;
  private Container container;
  private String name;

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }

  public String getPreferedLocation() {
    if (this.getId() != null) {
      return "id=" + this.getId();
    } else {
      if (this.getLocation() == null) {
        return "id=" + this.getName();
      } else {
        return this.getLocation();
      }
    }
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
    if (this.name == null) {
      this.name = this.id;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
