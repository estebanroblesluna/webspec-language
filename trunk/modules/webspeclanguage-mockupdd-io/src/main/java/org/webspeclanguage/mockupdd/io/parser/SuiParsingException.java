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
package org.webspeclanguage.mockupdd.io.parser;

/**
 * @author Jose Matias Rivero
 */
public class SuiParsingException extends Exception {

  private static final long serialVersionUID = 1L;
  
  private Object objectBeingParsed;

  public SuiParsingException(String message, Object objectBeingParsed) {
    super(message);
    this.setObjectBeingParsed(objectBeingParsed);
  }

  private void setObjectBeingParsed(Object objectBeingParsed) {
    this.objectBeingParsed = objectBeingParsed;
  }

  public Object getObjectBeingParsed() {
    return objectBeingParsed;
  }
  
}
