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

import java.util.List;

/**
 * An operation represents a sequence of actions perform over
 * a sequence of {@link Interaction}s
 * 
 * @author Esteban Robles Luna
 */
public interface Operation extends NamedObject, WidgetProvider {

  /**
   * @return the list of items that this operation contains
   */
  List<PathItem> getItems();
}
