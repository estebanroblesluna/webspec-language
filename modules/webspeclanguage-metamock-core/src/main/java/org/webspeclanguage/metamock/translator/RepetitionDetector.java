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
package org.webspeclanguage.metamock.translator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockUtil;
import org.webspeclanguage.metamock.utils.Point;

/**
 * Provides component repetition detection in {@link CompositeControl}s
 * 
 * @author Jose Matias Rivero
 */
public class RepetitionDetector {

	private static final int DEFAULT_TOLERANCE = 10;
	private static final int DEFAULT_MIN_CONTROLS = 3;
	private MetaMockFactory factory;
	private Integer pixelTolerance;
	private Integer minControls;

	public RepetitionDetector(MetaMockFactory factory, Integer pixelTolerance, Integer minControls) {
		super();
		this.setFactory(factory);
		this.setPixelTolerance(pixelTolerance);
		this.setMinControls(minControls);
	}
	
	public RepetitionDetector(MetaMockFactory factory) {
		this(factory, DEFAULT_TOLERANCE, DEFAULT_MIN_CONTROLS);
	}

	private void setFactory(MetaMockFactory factory) {
		this.factory = factory;
	}

	private MetaMockFactory getFactory() {
		return factory;
	}

	private void setPixelTolerance(Integer pixelTolerance) {
		this.pixelTolerance = pixelTolerance;
	}

	public Integer getPixelTolerance() {
		return pixelTolerance;
	}

	private void setMinControls(Integer minControls) {
    this.minControls = minControls;
  }

  private Integer getMinControls() {
    return minControls;
  }

  public Repetition detectRepetition(CompositeControl cc) {
		List<UIControl> controlsToProcess = new ArrayList<UIControl>(cc.getControls());
		Collection<UIControl> repetitionControls = new ArrayList<UIControl>();
		List<Point> offsets = null;
		this.sortByTopLeftBorderProximity(controlsToProcess);
		if (controlsToProcess.size() == 0) {
			return null;
		}
		while (controlsToProcess.size() > 0) {
			UIControl c = controlsToProcess.get(0);
			Collection<UIControl> similarControls = 
				this.getSimilarControls(controlsToProcess, c);
			if (similarControls.size() == 0) {
				return null;
			}
			if (offsets == null) {
				offsets = this.getOffsets(c, similarControls);
				if (offsets.size() == 0 || offsets.size() < this.getMinControls() - 1) {
				  return null;
				}
			}
			else {
				if(!this.checkOffsets(offsets, c, similarControls, this.getPixelTolerance())) {
					return null;
				}
			}
			controlsToProcess.remove(c);
			controlsToProcess.removeAll(similarControls);
			repetitionControls.add(c);
		}
		offsets.add(new Point(0, 0)); // empty offset
		Repetition r = this.getFactory().createRepetition(cc.getControlId(), cc.getX(), 
				cc.getY(), cc.getWidth(), cc.getHeight(), repetitionControls, this.inferRows(offsets), 
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

	private void sortByTopLeftBorderProximity(List<UIControl> controlsToProcess) {
		Collections.sort(controlsToProcess, new Comparator<UIControl>() {

			public int compare(UIControl c1, UIControl c2) {
				return c1.getX() + c1.getY() - (c2.getX() + c2.getY());
			}
			
		});
		
	}

	private boolean checkOffsets(List<Point> offsets,
			UIControl referenceControl,
			Collection<UIControl> similarControls, Integer pixelTolerance) {
		for (UIControl c : similarControls) {
			Boolean offsetMatches = false;
			for (Point offset : offsets) {
			  Point offsetLocation = new Point(referenceControl.getX() + offset.getX(), referenceControl.getY() + offset.getY());
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

	private List<Point> getOffsets(UIControl c, Collection<UIControl> controls) {
		List<Point> offsets = new ArrayList<Point>();
		for (UIControl c2 : controls) {
			if (c2 != c) {
				offsets.add(new Point(c2.getX() - c.getX(), c2.getY() - c.getY()));
			}
		}
		return offsets;
	}

	@SuppressWarnings("unchecked")
  private Collection<UIControl> getSimilarControls(List<UIControl> controls, UIControl c) {
		Collection<UIControl> controlsOfTheSameType = 
			MetaMockUtil.filterControlsByType(controls, c.getClass());
		Collection<UIControl> similarControls = new ArrayList<UIControl>();
    for (UIControl cs : controlsOfTheSameType) {
      if (c != cs && MetaMockUtil.areGraphicallySimilar(c, cs, this.getPixelTolerance())) {
        similarControls.add(cs);
      }
    }
		return similarControls;
	}

}
