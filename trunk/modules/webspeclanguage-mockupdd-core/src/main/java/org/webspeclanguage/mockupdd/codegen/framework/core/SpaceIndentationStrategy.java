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
package org.webspeclanguage.mockupdd.codegen.framework.core;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Jose Matias Rivero
 */
public class SpaceIndentationStrategy implements IndentationStrategy {

  private char[] spaces;

  public SpaceIndentationStrategy(Integer spaces) {
    super();
    this.setSpaces(spaces);
  }

  private void setSpaces(Integer spaces) {
    char[] spacesArray = new char[spaces];
    for (int i = 0; i < spacesArray.length; i++) {
      spacesArray[i] = ' ';
    }
    this.spaces = spacesArray;
  }

  private char[] getSpaces() {
    return spaces;
  }

  public void writeIndentation(OutputStreamWriter outputStreamWriter) {
    try {
      outputStreamWriter.write(this.getSpaces());
    } catch (IOException e) {
    }
  }

}
