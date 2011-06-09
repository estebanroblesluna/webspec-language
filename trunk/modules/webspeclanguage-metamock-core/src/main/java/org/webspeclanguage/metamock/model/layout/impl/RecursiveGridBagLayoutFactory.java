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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.LayoutFactory;
import org.webspeclanguage.metamock.utils.ControlList;
import org.webspeclanguage.metamock.utils.SuiUtil;

/**
 * A {@link LayoutFactory} that applies a recursive algorithm to infer a 
 * {@link GridBagLayout} for a collection of controls
 * 
 * @author Jose Matias Rivero
 */
public class RecursiveGridBagLayoutFactory implements LayoutFactory {

	public GridBagLayout createLayout(Collection<Widget> controls) {
		GridBagLayoutInferenceState state = new GridBagLayoutInferenceState();
		this.inferColumns(controls, state, 0);
		this.inferRows(controls, state, 0);
		return this.buildLayout(state);
	}

	private GridBagLayout buildLayout(GridBagLayoutInferenceState state) {
		GridBagLayout gbl = new GridBagLayoutImpl();
      for (Widget c : state.getControls()) {
      	gbl.addAddShift(state.getCellForControl(c));
      }     
		return gbl;
	}

	private Integer inferColumns(Collection<Widget> controls,
			GridBagLayoutInferenceState state,
			Integer parentCol) {
		List<ControlList> cols = SuiUtil.getControlsGroupedInColumns(controls);
		Integer accumColspan = 0;
		for (Integer childCol = 0; childCol < cols.size(); childCol++) {
			accumColspan +=
				this.inferColumnsRecursively(cols.get(childCol).getControls(), state, 
	        accumColspan + parentCol);
		}
		return accumColspan;
	}

	private Integer inferColumnsRecursively(Collection<Widget> controls,
			GridBagLayoutInferenceState state, Integer col) {
		if (controls.size() == 1) {
			GridBagLayoutCell cell = state.getCellForControl(controls.iterator().next());
			cell.setColumnSpan(1);
			cell.setColumn(col);
			return 1;
		} else {
			Widget widerControl = null;
			Collection<Widget> maxCollidingControls = new ArrayList<Widget>();
			Integer minCollidingControlCount = Integer.MAX_VALUE;
			for (Widget c : controls) {
				Collection<Widget> collidingControls = 
					SuiUtil.getVerticallyCollidingControls(controls, c);
				if (collidingControls.size() > maxCollidingControls.size()) {
					maxCollidingControls = collidingControls;
					widerControl = c;
				}
				if (minCollidingControlCount < collidingControls.size()) {
				  minCollidingControlCount = collidingControls.size();
				}
			}
			Collection<Widget> remainingControls = new ArrayList<Widget>(controls);
      remainingControls.remove(widerControl);
      Integer colspan = this.inferColumns(remainingControls, state, col);
      state.getCellForControl(widerControl).setColumnSpan(colspan);
			state.getCellForControl(widerControl).setColumn(
        maxCollidingControls.size() == minCollidingControlCount ? col + 1 : col);
			
			return colspan;
		}
	}
	
	private Integer inferRows(Collection<Widget> controls,
			GridBagLayoutInferenceState state,
			Integer parentRow) {
		List<ControlList> rows = SuiUtil.getControlsGroupedInRows(controls);
		Integer accumRowspan = 0;
		for (Integer childRow = 0; childRow < rows.size(); childRow++) {
			accumRowspan +=
				this.inferRowsRecursively(rows.get(childRow).getControls(), state, 
	        accumRowspan + parentRow);
		}
		return accumRowspan;
	}

	private Integer inferRowsRecursively(Collection<Widget> controls,
			GridBagLayoutInferenceState state, Integer row) {
		if (controls.size() == 1) {
			GridBagLayoutCell cell = state.getCellForControl(controls.iterator().next());
			cell.setRowSpan(1);
			cell.setRow(row);
			return 1;
		} else {
			Widget widerControl = null;
			Collection<Widget> maxCollidingControls = new ArrayList<Widget>();
			for (Widget c : controls) {
				Collection<Widget> collidingControls = 
					SuiUtil.getHorizontallyCollidingControls(controls, c);
				if (collidingControls.size() > maxCollidingControls.size()) {
					maxCollidingControls = collidingControls;
					widerControl = c;
				}	
			}
			Collection<Widget> remainingControls = new ArrayList<Widget>(controls);
			remainingControls.remove(widerControl);
			Integer colspan = this.inferRows(remainingControls, state, row);
			state.getCellForControl(widerControl).setRowSpan(colspan);
			state.getCellForControl(widerControl).setRow(row);
			return colspan;
		}
	}

}
