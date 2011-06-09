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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutException;
import org.webspeclanguage.metamock.model.layout.LayoutFactory;
import org.webspeclanguage.metamock.translator.logger.SuiLogging;
import org.webspeclanguage.metamock.utils.HorizontalOrientationStrategy;
import org.webspeclanguage.metamock.utils.UIControlOrientationStrategy;
import org.webspeclanguage.metamock.utils.VerticalOrientationStrategy;

public class ScanBasedGridBagLayoutFactory implements LayoutFactory {

  private UIControlMilestoneComparator comparator;

  private void setComparator(UIControlMilestoneComparator comparator) {
    this.comparator = comparator;
  }

  private UIControlMilestoneComparator getComparator() {
    return comparator;
  }

  public ScanBasedGridBagLayoutFactory() {
    this.setComparator(new UIControlMilestoneComparator());
  }

  public GridBagLayout createLayout(Collection<Widget> controls) {
    GridBagLayoutInferenceState state = new GridBagLayoutInferenceState();
    GridBagLayout gbl = new GridBagLayoutImpl();
    this.inferPositions(controls, state, HorizontalOrientationStrategy.getInstance());
    this.inferPositions(controls, state, VerticalOrientationStrategy.getInstance());
    try {
      this.buildGridBagLayout(gbl, state);
    } catch (GridBagLayoutException e) {
      SuiLogging.getDefaultLogger().logGridBagLayoutException(e);
    }
    return gbl;
  }
  
  private void buildGridBagLayout(GridBagLayout gbl, GridBagLayoutInferenceState state) throws GridBagLayoutException {
    for (Widget c : state.getControls()) {
      gbl.add(state.getCellForControl(c));
    }
  }

  private void inferPositions(Collection<Widget> controls, GridBagLayoutInferenceState state, UIControlOrientationStrategy strategy) {
    List<WidgetMilestone> milestones = this.createMilestones(controls, strategy);
    int currentPosition = 1;
    List<Widget> currentControls = new ArrayList<Widget>();
    Map<Widget, Integer> controlsSpan = new HashMap<Widget, Integer>();
    boolean lastWasControlEnd = false;
    Collections.sort(milestones, this.getComparator());
    for (WidgetMilestone m : milestones) {
      if (m.getType().equals(WidgetMilestone.MilestoneType.WidgetStart)) {
        currentControls.add(m.getControl());
        controlsSpan.put(m.getControl(), 0);
        strategy.setPosition(state.getCellForControl(m.getControl()), currentPosition);
        if (lastWasControlEnd) {
          for (Widget c : currentControls) {
            controlsSpan.put(c, controlsSpan.get(c) + 1);
          }
        }
        lastWasControlEnd = false;
      } else if (m.getType().equals(WidgetMilestone.MilestoneType.WidgetEnd)) {
        if (!lastWasControlEnd) {
          currentPosition++;
        }
        currentControls.remove(m.getControl());
        strategy.setSpan(state.getCellForControl(m.getControl()), this.normalizeSpan(controlsSpan.get(m.getControl())));
        lastWasControlEnd = true;
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

  private List<WidgetMilestone> createMilestones(Collection<Widget> controls, UIControlOrientationStrategy strategy) {
    List<WidgetMilestone> milestones = new ArrayList<WidgetMilestone>();
    for (Widget c : controls) {
      milestones.add(new WidgetMilestone(strategy.getPosition(c), c, WidgetMilestone.MilestoneType.WidgetStart));
      milestones.add(new WidgetMilestone(strategy.getPosition(c) + strategy.getLength(c), c, WidgetMilestone.MilestoneType.WidgetEnd));
    }
    return milestones;
  }

  private class UIControlMilestoneComparator implements Comparator<WidgetMilestone> {

    public int compare(WidgetMilestone m1, WidgetMilestone m2) {
      return m1.getPosition() - m2.getPosition();
    }

  }

}
