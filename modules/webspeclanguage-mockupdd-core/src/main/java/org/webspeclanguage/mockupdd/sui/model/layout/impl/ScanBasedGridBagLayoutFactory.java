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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutException;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.translator.logger.SuiLogging;
import org.webspeclanguage.mockupdd.utils.HorizontalOrientationStrategy;
import org.webspeclanguage.mockupdd.utils.VerticalOrientationStrategy;
import org.webspeclanguage.mockupdd.utils.WidgetOrientationStrategy;

/**
 * @author Jose Matias Rivero
 */
public class ScanBasedGridBagLayoutFactory implements LayoutFactory {

  private WidgetMilestoneComparator comparator;

  private void setComparator(WidgetMilestoneComparator comparator) {
    this.comparator = comparator;
  }

  private WidgetMilestoneComparator getComparator() {
    return comparator;
  }

  public ScanBasedGridBagLayoutFactory() {
    this.setComparator(new WidgetMilestoneComparator());
  }

  public GridBagLayout createLayout(Collection<Widget> widgets) {
    GridBagLayoutInferenceState state = new GridBagLayoutInferenceState();
    GridBagLayout gbl = new GridBagLayoutImpl();
    this.inferPositions(widgets, state, HorizontalOrientationStrategy.getInstance());
    this.inferPositions(widgets, state, VerticalOrientationStrategy.getInstance());
    try {
      this.buildGridBagLayout(gbl, state);
    } catch (GridBagLayoutException e) {
      SuiLogging.getDefaultLogger().logGridBagLayoutException(e);
    }
    return gbl;
  }
  
  private void buildGridBagLayout(GridBagLayout gbl, GridBagLayoutInferenceState state) throws GridBagLayoutException {
    for (Widget c : state.getWidgets()) {
      gbl.add(state.getCellForWidget(c));
    }
  }

  private void inferPositions(Collection<Widget> widgets, GridBagLayoutInferenceState state, WidgetOrientationStrategy strategy) {
    List<WidgetMilestone> milestones = this.createMilestones(widgets, strategy);
    int currentPosition = 1;
    List<Widget> currentWidgets = new ArrayList<Widget>();
    Map<Widget, Integer> widgetsSpan = new HashMap<Widget, Integer>();
    boolean lastWasWidgetEnd = false;
    Collections.sort(milestones, this.getComparator());
    for (WidgetMilestone m : milestones) {
      if (m.getType().equals(WidgetMilestone.MilestoneType.WidgetStart)) {
        currentWidgets.add(m.getWidget());
        widgetsSpan.put(m.getWidget(), 0);
        strategy.setPosition(state.getCellForWidget(m.getWidget()), currentPosition);
        if (lastWasWidgetEnd) {
          for (Widget c : currentWidgets) {
            widgetsSpan.put(c, widgetsSpan.get(c) + 1);
          }
        }
        lastWasWidgetEnd = false;
      } else if (m.getType().equals(WidgetMilestone.MilestoneType.WidgetEnd)) {
        if (!lastWasWidgetEnd) {
          currentPosition++;
        }
        currentWidgets.remove(m.getWidget());
        strategy.setSpan(state.getCellForWidget(m.getWidget()), this.normalizeSpan(widgetsSpan.get(m.getWidget())));
        lastWasWidgetEnd = true;
      }
    }
  }
  
  private int normalizeSpan(int span) {
    if (span == 0) {
      return 1;
    } else {
      return span; 
    }
  }

  private List<WidgetMilestone> createMilestones(Collection<Widget> widgets, WidgetOrientationStrategy strategy) {
    List<WidgetMilestone> milestones = new ArrayList<WidgetMilestone>();
    for (Widget c : widgets) {
      milestones.add(new WidgetMilestone(strategy.getPosition(c), c, WidgetMilestone.MilestoneType.WidgetStart));
      milestones.add(new WidgetMilestone(strategy.getPosition(c) + strategy.getLength(c), c, WidgetMilestone.MilestoneType.WidgetEnd));
    }
    return milestones;
  }

  private class WidgetMilestoneComparator implements Comparator<WidgetMilestone> {

    public int compare(WidgetMilestone m1, WidgetMilestone m2) {
      return m1.getPosition() - m2.getPosition();
    }

  }

}
