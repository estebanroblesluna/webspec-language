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
package org.webspeclanguage.metamock.utils;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;

/**
 * Abstracts user positions and length gathering from the orientation (vertical
 * or horizontal)
 * 
 * Useful when some actions must be applied in both vertical and horizontal
 * orientations in order to avoid repeated code
 * 
 * @author Jose Matias Rivero
 */
public interface UIControlOrientationStrategy {

  int getPosition(Widget control);

  int getLength(Widget control);
  
  void setPosition(GridBagLayoutCell cell, int position);
  
  void setSpan(GridBagLayoutCell cell, int span);
  
}
