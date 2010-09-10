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
package org.webspeclanguage.base;

/**
 * An exception thrown when the interaction we are trying to add is invalid
 * because it doesn't have a location.
 * 
 * @author Esteban Robles Luna
 */
public class InvalidStartingInteractionException extends RuntimeException {

  private WebSpecInteraction startingInteraction;

  private static final long serialVersionUID = -2059856228247237272L;

  public InvalidStartingInteractionException(WebSpecInteraction startingInteraction) {
    this.startingInteraction = startingInteraction;
  }

  public WebSpecInteraction getStartingInteraction() {
    return startingInteraction;
  }
}