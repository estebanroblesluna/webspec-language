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
package org.webspeclanguage.expression.typechecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a result of a typechecking
 * 
 * @author Esteban Robles Luna
 */
public class TypecheckingResult {

  private List<Exception> exceptions;

  public TypecheckingResult() {
    this.exceptions = new ArrayList<Exception>();
  }

  public void addError(Exception exception) {
    this.exceptions.add(exception);
  }

  public List<Exception> getExceptions() {
    return Collections.unmodifiableList(exceptions);
  }
}
