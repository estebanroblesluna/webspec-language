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
package org.webspeclanguage.metamock.model.layout.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;

/**
 * Class used for {@link RecursiveGridBagLayoutFactory} to store the inference state
 * 
 * @author Jose Matias Rivero
 */
public class RecursiveGridBagLayoutInferenceState {

	private Map<UIControl, GridBagLayoutCell> cellsForControls;
	
	public RecursiveGridBagLayoutInferenceState() {
		super();
		this.setCellsForControls(new HashMap<UIControl, GridBagLayoutCell>());
	}

	private void setCellsForControls(Map<UIControl, GridBagLayoutCell> cellsForControls) {
		this.cellsForControls = cellsForControls;
	}

	private Map<UIControl, GridBagLayoutCell> getCellsForControls() {
		return cellsForControls;
	}

	public GridBagLayoutCell getCellForControl(UIControl c) {
		if (!this.getCellsForControls().containsKey(c)) {
			this.getCellsForControls().put(c, new GridBagLayoutCellImpl(1, 1, 1, 1, c));
		}
		return this.getCellsForControls().get(c);
	}

	public Collection<UIControl> getControls() {
		return this.getCellsForControls().keySet();
	}
	
	
	
}