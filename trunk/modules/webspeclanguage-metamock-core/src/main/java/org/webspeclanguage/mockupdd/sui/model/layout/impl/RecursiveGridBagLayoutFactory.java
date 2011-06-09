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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.utils.SuiUtil;
import org.webspeclanguage.mockupdd.utils.WidgetList;

/**
 * A {@link LayoutFactory} that applies a recursive algorithm to infer a 
 * {@link GridBagLayout} for a collection of widgets
 * 
 * @author Jose Matias Rivero
 */
public class RecursiveGridBagLayoutFactory implements LayoutFactory {

	public GridBagLayout createLayout(Collection<Widget> widgets) {
		GridBagLayoutInferenceState state = new GridBagLayoutInferenceState();
		this.inferColumns(widgets, state, 0);
		this.inferRows(widgets, state, 0);
		return this.buildLayout(state);
	}

	private GridBagLayout buildLayout(GridBagLayoutInferenceState state) {
		GridBagLayout gbl = new GridBagLayoutImpl();
      for (Widget c : state.getWidgets()) {
      	gbl.addAddShift(state.getCellForWidget(c));
      }     
		return gbl;
	}

	private Integer inferColumns(Collection<Widget> widgets,
			GridBagLayoutInferenceState state,
			Integer parentCol) {
		List<WidgetList> cols = SuiUtil.getWidgetsGroupedInColumns(widgets);
		Integer accumColspan = 0;
		for (Integer childCol = 0; childCol < cols.size(); childCol++) {
			accumColspan +=
				this.inferColumnsRecursively(cols.get(childCol).getWidgets(), state, 
	        accumColspan + parentCol);
		}
		return accumColspan;
	}

	private Integer inferColumnsRecursively(Collection<Widget> widgets,
			GridBagLayoutInferenceState state, Integer col) {
		if (widgets.size() == 1) {
			GridBagLayoutCell cell = state.getCellForWidget(widgets.iterator().next());
			cell.setColumnSpan(1);
			cell.setColumn(col);
			return 1;
		} else {
			Widget widerWidget = null;
			Collection<Widget> maxCollidingWidgets = new ArrayList<Widget>();
			Integer minCollidingWidgetCount = Integer.MAX_VALUE;
			for (Widget c : widgets) {
				Collection<Widget> collidingWidgets = 
					SuiUtil.getVerticallyCollidingWidgets(widgets, c);
				if (collidingWidgets.size() > maxCollidingWidgets.size()) {
					maxCollidingWidgets = collidingWidgets;
					widerWidget = c;
				}
				if (minCollidingWidgetCount < collidingWidgets.size()) {
				  minCollidingWidgetCount = collidingWidgets.size();
				}
			}
			Collection<Widget> remainingWidgets = new ArrayList<Widget>(widgets);
      remainingWidgets.remove(widerWidget);
      Integer colspan = this.inferColumns(remainingWidgets, state, col);
      state.getCellForWidget(widerWidget).setColumnSpan(colspan);
			state.getCellForWidget(widerWidget).setColumn(
        maxCollidingWidgets.size() == minCollidingWidgetCount ? col + 1 : col);
			
			return colspan;
		}
	}
	
	private Integer inferRows(Collection<Widget> widgets,
			GridBagLayoutInferenceState state,
			Integer parentRow) {
		List<WidgetList> rows = SuiUtil.getWidgetsGroupedInRows(widgets);
		Integer accumRowspan = 0;
		for (Integer childRow = 0; childRow < rows.size(); childRow++) {
			accumRowspan +=
				this.inferRowsRecursively(rows.get(childRow).getWidgets(), state, 
	        accumRowspan + parentRow);
		}
		return accumRowspan;
	}

	private Integer inferRowsRecursively(Collection<Widget> widgets,
			GridBagLayoutInferenceState state, Integer row) {
		if (widgets.size() == 1) {
			GridBagLayoutCell cell = state.getCellForWidget(widgets.iterator().next());
			cell.setRowSpan(1);
			cell.setRow(row);
			return 1;
		} else {
			Widget widerWidget = null;
			Collection<Widget> maxCollidingWidgets = new ArrayList<Widget>();
			for (Widget c : widgets) {
				Collection<Widget> collidingWidgets = 
					SuiUtil.getHorizontallyCollidingWidgets(widgets, c);
				if (collidingWidgets.size() > maxCollidingWidgets.size()) {
					maxCollidingWidgets = collidingWidgets;
					widerWidget = c;
				}	
			}
			Collection<Widget> remainingWidgets = new ArrayList<Widget>(widgets);
			remainingWidgets.remove(widerWidget);
			Integer colspan = this.inferRows(remainingWidgets, state, row);
			state.getCellForWidget(widerWidget).setRowSpan(colspan);
			state.getCellForWidget(widerWidget).setRow(row);
			return colspan;
		}
	}

}
