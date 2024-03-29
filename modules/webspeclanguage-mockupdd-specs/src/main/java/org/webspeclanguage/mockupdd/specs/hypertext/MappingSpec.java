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

package org.webspeclanguage.mockupdd.specs.hypertext;

import org.webspeclanguage.mockupdd.sui.model.DataBoundWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * Represents an abstract data mapping to a underlying {@link Widget}
 * 
 * @author Jose Matias Rivero
 */
public interface MappingSpec<W extends Widget> {

  /**
   * @return The widget being mapped
   */
  W getWidget();
  
  
  /**
   * Sets the widget being mapped
   */
  void setWidget(W widget);

  /**
   * @return The Widget from which instance is taken (if defined)
   */
  DataBoundWidget getDataSource();

  void setDataSource(DataBoundWidget dataSource);

}