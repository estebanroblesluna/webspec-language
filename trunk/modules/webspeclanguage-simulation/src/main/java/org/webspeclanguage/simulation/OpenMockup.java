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

/**
 * A item that opens a mockup in the browser
 * 
 * @author Esteban Robles Luna
 */
public class OpenMockup implements SimulationItem {

  private String location;
  
  public OpenMockup(String location) {
    Validate.notNull(location);
    
    this.location = location;
  }
  
  public Object accept(SimulationItemVisitor visitor) {
    return visitor.visitOpenMockup(this);
  }

  public String getLocation() {
    return location;
  }
}