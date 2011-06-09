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
package org.webspeclanguage.metamock.model;

/**
 * Abstract interface containing all the common features present in a widget
 * 
 * @author Jose Matias Rivero
 */
public abstract interface Widget extends SuiModelElement {

  String getControlId();

  String getFriendlyId();

  Integer getHeight();

  Integer getWidth();

  Integer getX();

  Integer getY();

  CompositeWidget getParent();

  void setParent(CompositeWidget c);

  Page getPage();

  void setControlId(String id);

  /**
   * checks if this and <code>control</code> have the same properties (e.g.,
   * label, inner composition, etc)
   * 
   * @param control
   * @return true if this is graphically equal to <code>control</code>
   */
  Boolean equalInContent(Widget control);

  /**
   * checks if this <code>control</code> is the same component, even in
   * different versions of the model
   * 
   * @param parent
   * @return true if this is the same control than <code>control</code>
   */
  Boolean isTheSameAs(CompositeWidget parent);

  void setFriendlyId(String id);

}
