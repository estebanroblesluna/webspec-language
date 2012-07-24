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
package org.webspeclanguage.api;

import org.webspeclanguage.impl.widget.Widget;

/**
 * A widget provider can provide widgets from a widget path.
 * 
 * @author Esteban Robles Luna
 */
public interface WidgetProvider {

  /**
   * Returns the widget according to widgetPath or null if not found.
   * 
   * @param widgetPath a path separated by '.'. For example Home.login or simply login
   * @return the widget or null
   */
  Widget getWidget(String widgetPath);
}
