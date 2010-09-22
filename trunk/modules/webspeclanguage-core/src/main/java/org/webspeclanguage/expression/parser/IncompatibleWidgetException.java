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
package org.webspeclanguage.expression.parser;

/**
 * An exception that is thrown when a widget is incompatible with its
 * expected type during parsing
 * 
 * @author Esteban Robles Luna
 */
public class IncompatibleWidgetException extends RuntimeException {

  private static final long serialVersionUID = -2851906589002923351L;

  private String interactionName;
  private String widgetName;

  public IncompatibleWidgetException(String interactionName, String widgetName) {
    this.interactionName = interactionName;
    this.widgetName = widgetName;
  }

  public String getInteractionName() {
    return interactionName;
  }

  public String getWidgetName() {
    return widgetName;
  }
}
