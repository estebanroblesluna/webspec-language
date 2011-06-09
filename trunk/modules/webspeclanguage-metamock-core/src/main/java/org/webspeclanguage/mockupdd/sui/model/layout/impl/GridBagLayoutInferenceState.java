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
package org.webspeclanguage.mockupdd.sui.model.layout.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;

/**
 * Class used for {@link RecursiveGridBagLayoutFactory} to store the inference state
 * 
 * @author Jose Matias Rivero
 */
public class GridBagLayoutInferenceState {

	private Map<Widget, GridBagLayoutCell> cellsForWidgets;
	
	public GridBagLayoutInferenceState() {
		super();
		this.setCellsForWidgets(new HashMap<Widget, GridBagLayoutCell>());
	}

	private void setCellsForWidgets(Map<Widget, GridBagLayoutCell> cellsForWidgets) {
		this.cellsForWidgets = cellsForWidgets;
	}

	private Map<Widget, GridBagLayoutCell> getCellsForWidgets() {
		return cellsForWidgets;
	}

	public GridBagLayoutCell getCellForWidget(Widget c) {
		if (!this.getCellsForWidgets().containsKey(c)) {
			this.getCellsForWidgets().put(c, new GridBagLayoutCellImpl(1, 1, 1, 1, c));
		}
		return this.getCellsForWidgets().get(c);
	}

	public Collection<Widget> getWidgets() {
		return this.getCellsForWidgets().keySet();
	}
	
	
	
}
