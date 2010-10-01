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

import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.Widget;

/**
 * An interaction represents a point where the user can interact
 * with the application using its interface objects (widgets)
 * 
 * @author Esteban Robles Luna
 */
public interface Interaction extends NamedObject, TransitionSource, TransitionTarget, PathItem {

  /**
   * Obtains the widget whose name is widgetName or null if not found
   * 
   * @param widgetName the widget name
   * @return the widget or null
   */
  Widget getWidget(String widgetName);

  /**
   * Sets the diagram to be the container of this interaction
   * 
   * @param diagram the diagram
   */
  void setDiagram(Diagram diagram);

  /**
   * @return the expression that represents the title of this interaction
   */
  Expression getTitle();

  /**
   * @return the invariant that needs to be satisfied on this interaction
   */
  Expression getInvariant();

  /**
   * @return the root {@link Widget}
   */
  Container getRoot();

  /**
   * @return the location of this interaction
   */
  String getLocation();

  /**
   * @return the location of the mockup associated with this interaction
   */
  String getMockupFile();
}
