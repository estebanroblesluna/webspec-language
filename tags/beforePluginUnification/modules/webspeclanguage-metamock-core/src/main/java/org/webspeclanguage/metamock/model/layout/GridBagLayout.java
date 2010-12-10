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
package org.webspeclanguage.metamock.model.layout;

import java.util.Collection;
import java.util.List;

import org.webspeclanguage.metamock.model.UIControl;

/**
 * Represets a layout similar to an HTML table. A GridBagLayout has a specific
 * number of rows and cells, and is composed by {@link GridBagLayoutCell}s
 * 
 * @author Jose Matias Rivero
 */
public interface GridBagLayout extends Layout {

  Integer getColumnCount();

  Integer getRowCount();

  Collection<UIControl> getRowContent(Integer i);

  Collection<UIControl> getColumnContent(Integer i);

  GridBagLayoutCell getCell(Integer rowIndex, Integer columnIndex);

  GridBagLayoutCell getControlCell(UIControl c);

  <TCell, TRow> List<TRow> visitByRows(GridBagLayoutVisitor<TCell, TRow> v);

  void add(GridBagLayoutCell cell) throws GridBagLayoutException;

}
