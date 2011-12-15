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
package org.webspeclanguage.mockupdd.codegen.generator;

import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;

/**
 * @author Jose Matias Rivero
 */
public class Mockup<TMockupRepresentation> {

  private TMockupRepresentation representation;
  private MockupContainerInfo containerInfo;

  public Mockup(TMockupRepresentation representation, MockupContainerInfo containerInfo) {
    super();
    this.setRepresentation(representation);
    this.setContainerInfo(containerInfo);
  }

  private void setRepresentation(TMockupRepresentation representation) {
    this.representation = representation;
  }

  public final TMockupRepresentation getRepresentation() {
    return representation;
  }

  private void setContainerInfo(MockupContainerInfo containerInfo) {
    this.containerInfo = containerInfo;
  }

  public final MockupContainerInfo getContainerInfo() {
    return containerInfo;
  }

}
