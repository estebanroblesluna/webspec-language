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
package org.webspeclanguage.mockupdd.sui.model.layout;

import java.util.List;

import org.webspeclanguage.mockupdd.utils.WidgetList;

/**
 * Defines a layout composed by vertical columns of widgets. Order of widgets
 * between columns are not specified
 * 
 * @author Jose Matias Rivero
 */
public interface VerticalBoxLayout extends Layout {

  WidgetList getWidgetsAt(Integer i);

  <T> List<T> visitWidgets(GroupedLinearLayoutVisitor<T> v);

}
