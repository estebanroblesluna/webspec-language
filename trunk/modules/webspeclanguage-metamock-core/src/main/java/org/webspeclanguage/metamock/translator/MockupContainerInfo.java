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
package org.webspeclanguage.metamock.translator;

/**
 * Represents additional info associated with mockup containers (e.g., Files).
 * This info is needed to give some context or details of containment to high
 * level models elements like Pages
 * 
 * @author matias
 * 
 */
public class MockupContainerInfo {

  private String name;
  private String sourceFilePath;

  public MockupContainerInfo(String name) {
    super();
    this.setName(name);
  }

  public MockupContainerInfo(String name, String sourceFilename) {
    super();
    this.setName(name);
    this.setSourceFilePath(sourceFilename);
  }

  public final void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private void setSourceFilePath(String filename) {
    this.sourceFilePath = filename;
  }

  public String getSourceFilePath() {
    return sourceFilePath;
  }

}
