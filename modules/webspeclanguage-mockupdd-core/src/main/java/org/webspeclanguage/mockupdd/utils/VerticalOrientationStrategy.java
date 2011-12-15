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
package org.webspeclanguage.mockupdd.utils;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;


public class VerticalOrientationStrategy implements WidgetOrientationStrategy {

  private static VerticalOrientationStrategy instance;
  public static VerticalOrientationStrategy getInstance() {
    if (instance == null) {
      instance = new VerticalOrientationStrategy();
    }
    return instance;
  }
  
  private VerticalOrientationStrategy() {
  }
  
  public int getPosition(Widget widget) {
    return widget.getX();
  }

  public int getLength(Widget widget) {
    return widget.getWidth();
  }
  
  public void setPosition(GridBagLayoutCell cell, int position) {
    cell.setColumn(position);
  }

  public void setSpan(GridBagLayoutCell cell, int span) {
    cell.setColumnSpan(span);
  }

}
