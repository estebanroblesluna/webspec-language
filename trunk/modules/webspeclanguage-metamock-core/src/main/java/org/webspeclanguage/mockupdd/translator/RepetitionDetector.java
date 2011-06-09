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
package org.webspeclanguage.mockupdd.translator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.Point;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * Provides component repetition detection in {@link CompositeWidget}s
 * 
 * @author Jose Matias Rivero
 */
public class RepetitionDetector {

	private static final int DEFAULT_TOLERANCE = 10;
	private static final int DEFAULT_MIN_CONTROLS = 3;
	private SuiFactory factory;
	private Integer pixelTolerance;
	private Integer minWidgets;

	public RepetitionDetector(SuiFactory factory, Integer pixelTolerance, Integer minWidgets) {
		super();
		this.setFactory(factory);
		this.setPixelTolerance(pixelTolerance);
		this.setMinWidgets(minWidgets);
	}
	
	public RepetitionDetector(SuiFactory factory) {
		this(factory, DEFAULT_TOLERANCE, DEFAULT_MIN_CONTROLS);
	}

	private void setFactory(SuiFactory factory) {
		this.factory = factory;
	}

	private SuiFactory getFactory() {
		return factory;
	}

	private void setPixelTolerance(Integer pixelTolerance) {
		this.pixelTolerance = pixelTolerance;
	}

	public Integer getPixelTolerance() {
		return pixelTolerance;
	}

	private void setMinWidgets(Integer minWidgets) {
    this.minWidgets = minWidgets;
  }

  private Integer getMinWidgets() {
    return minWidgets;
  }

  public Repetition detectRepetition(CompositeWidget cc) {
		List<Widget> widgetsToProcess = new ArrayList<Widget>(cc.getWidgets());
		Collection<Widget> repetitionWidgets = new ArrayList<Widget>();
		List<Point> offsets = null;
		this.sortByTopLeftBorderProximity(widgetsToProcess);
		if (widgetsToProcess.size() == 0) {
			return null;
		}
		while (widgetsToProcess.size() > 0) {
			Widget c = widgetsToProcess.get(0);
			Collection<Widget> similarWidgets = 
				this.getSimilarWidgets(widgetsToProcess, c);
			if (similarWidgets.size() == 0) {
				return null;
			}
			if (offsets == null) {
				offsets = this.getOffsets(c, similarWidgets);
				if (offsets.size() == 0 || offsets.size() < this.getMinWidgets() - 1) {
				  return null;
				}
			}
			else {
				if(!this.checkOffsets(offsets, c, similarWidgets, this.getPixelTolerance())) {
					return null;
				}
			}
			widgetsToProcess.remove(c);
			widgetsToProcess.removeAll(similarWidgets);
			repetitionWidgets.add(c);
		}
		offsets.add(new Point(0, 0)); // empty offset
		Repetition r = this.getFactory().createRepetition(cc.getWidgetId(), cc.getX(), 
				cc.getY(), cc.getWidth(), cc.getHeight(), repetitionWidgets, this.inferRows(offsets), 
				this.inferColumns(offsets), cc.getContainerId());
		return r;
	}

	private Integer inferColumns(List<Point> offsets) {
		List<Point> sortedOffsets = new ArrayList<Point>(offsets);
		Integer columns = 1;
		Collections.sort(sortedOffsets, new Comparator<Point>() {

			public int compare(Point p1, Point p2) {
				return p1.getX() - p2.getX();
			}
			
		});
		for (Integer i = 1; i < sortedOffsets.size(); i++) {
			if (sortedOffsets.get(i).getX() - sortedOffsets.get(i - 1).getX() > this.getPixelTolerance()) {
				columns++;
			}
		}
		return columns;		
	}

	private Integer inferRows(List<Point> offsets) {
		List<Point> sortedOffsets = new ArrayList<Point>(offsets);
		Integer rows = 1;
		Collections.sort(sortedOffsets, new Comparator<Point>() {

			public int compare(Point p1, Point p2) {
				return p1.getY() - p2.getY();
			}
			
		});
		for (Integer i = 1; i < sortedOffsets.size(); i++) {
			if (sortedOffsets.get(i).getY() - sortedOffsets.get(i - 1).getY() > this.getPixelTolerance()) {
				rows++;
			}
		}
		return rows;
	}

	private void sortByTopLeftBorderProximity(List<Widget> widgetsToProcess) {
		Collections.sort(widgetsToProcess, new Comparator<Widget>() {

			public int compare(Widget c1, Widget c2) {
				return c1.getX() + c1.getY() - (c2.getX() + c2.getY());
			}
			
		});
		
	}

	private boolean checkOffsets(List<Point> offsets,
			Widget referenceWidget,
			Collection<Widget> similarWidgets, Integer pixelTolerance) {
		for (Widget c : similarWidgets) {
			Boolean offsetMatches = false;
			for (Point offset : offsets) {
			  Point offsetLocation = new Point(referenceWidget.getX() + offset.getX(), referenceWidget.getY() + offset.getY());
				if (offsetLocation.isNear(new Point(c.getX(), c.getY()), pixelTolerance)) {
					offsetMatches = true;
					break;
				}
			}
			if (!offsetMatches) {
				return false;	
			}
		}
		return true;
	}

	private List<Point> getOffsets(Widget c, Collection<Widget> widgets) {
		List<Point> offsets = new ArrayList<Point>();
		for (Widget c2 : widgets) {
			if (c2 != c) {
				offsets.add(new Point(c2.getX() - c.getX(), c2.getY() - c.getY()));
			}
		}
		return offsets;
	}

	@SuppressWarnings("unchecked")
  private Collection<Widget> getSimilarWidgets(List<Widget> widgets, Widget c) {
		Collection<Widget> widgetsOfTheSameType = 
			SuiUtil.filterWidgetsByType(widgets, c.getClass());
		Collection<Widget> similarWidgets = new ArrayList<Widget>();
    for (Widget cs : widgetsOfTheSameType) {
      if (c != cs && SuiUtil.areGraphicallySimilar(c, cs, this.getPixelTolerance())) {
        similarWidgets.add(cs);
      }
    }
		return similarWidgets;
	}

}
