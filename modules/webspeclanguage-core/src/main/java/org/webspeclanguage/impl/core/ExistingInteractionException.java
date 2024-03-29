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
package org.webspeclanguage.impl.core;

/**
 * An exception thrown when we try to add an interaction that is already
 * present in the diagram
 * 
 * @author Esteban Robles Luna
 */
public class ExistingInteractionException extends RuntimeException {

  private static final long serialVersionUID = -5039946734643911167L;

  private String name;

  public ExistingInteractionException(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
