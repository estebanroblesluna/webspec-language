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
package org.webspeclanguage.impl.exception;

/**
 * A base class for Exceptions
 * 
 * @author Esteban Robles Luna
 */
public class WebspecException extends RuntimeException {

  private static final long serialVersionUID = -3993904983196702319L;

  public WebspecException(String message) {
    super(message);
  }

  public WebspecException(Exception e) {
    super(e);
  }
}
