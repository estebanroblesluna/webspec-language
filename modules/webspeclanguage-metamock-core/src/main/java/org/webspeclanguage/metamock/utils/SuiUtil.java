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
package org.webspeclanguage.metamock.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.SuiModelElement;
import org.webspeclanguage.metamock.model.Widget;


/**
 * Provides some useful methods to check properties or features in a
 * MockSpec model or its components, avoiding pollution of language interfaces and 
 * 
 * @author Jose Matias Rivero
 */
public final class SuiUtil {

	private SuiUtil() {
		
	}
	
	public static Boolean isComposite(SuiModelElement c) {
		return c instanceof CompositeWidget;
	}
	
	/**
	 * Returns <code>true</code> if the control <code>c1</code> is vertically
	 * included into the control <code>c2</code>, <code>false</code> otherwise
	 */
	public static Boolean isVerticallyIncludedIn(Widget c1, Widget c2) {
		if (c1.equals(c2)) {
			return false;
		}
		return
			c1.getX() > c2.getX() && c1.getX() < c2.getX() + c2.getWidth() &&
			c1.getX() + c1.getWidth() < c2.getX() + c2.getWidth();
	}
	
  /**
   * Returns <code>true</code> if the control <code>c1</code> is horizontally
   * included into the control <code>c2</code>, or <code>false</code> otherwise
   */
	public static Boolean isHorizontallyIncludedIn(Widget c1, Widget c2) {
		if (c1.equals(c2)) {
			return false;
		}
		return
			c1.getY() > c2.getY() && c1.getY() < c2.getY() + c2.getHeight() &&
			c1.getY() + c1.getHeight() < c2.getY() + c2.getHeight();
	}
	
	/**
	 * Returns <code>true</code> if the control <code>c1</code> is graphically included in
	 * control <code>c2</code> or <code>false</code> otherwise
	 */
	public static Boolean isIncludedIn(Widget c1, Widget c2) {
		if (c1.equals(c2)) {
			return false;
		}
		return
			SuiUtil.isVerticallyIncludedIn(c1, c2) &&
			SuiUtil.isHorizontallyIncludedIn(c1, c2);
	}
	
	/**
	 * Returns a subset of <code>controls</code> containing only the controls graphically
	 * in <code>c</code> 
	 * @param c
	 * @param ctrls
	 * @return 
	 */
	public static Collection<Widget> controlsIncludedIn(Widget c, Collection<Widget> ctrls) {
		Set<Widget> included = new HashSet<Widget>();
		for (Widget ctrl : ctrls) {
			if (ctrl != c && SuiUtil.isIncludedIn(ctrl, c)) {
				included.add(ctrl);
			}
		}
		return included;
	}
	
	public static Collection<Widget> filterControlsByType(Collection<Widget> controls, Class<? extends Widget>... types) {
		Set<Widget> included = new HashSet<Widget>();
		for (Widget c : controls) {
			for (Class<? extends Widget> type : types) {
				if (SuiUtil.controlIsKindOf(c, type)) {
					included.add((Widget)c);
					break;
				}
			}
		}
		return included;			
	}
	
	/**
	 * Returns <code>true</code> if the control <code>c</code> is instance of <code>clazz</code> or
	 * instance of some <code>clazz</code>'s superclass 
	 */
	public static Boolean controlIsKindOf(Widget c, Class<? extends Widget> clazz) {
		return clazz.isAssignableFrom(c.getClass());
	}
	
	/**
	 * Returns the parent control found in <code>controls</code> for the control <code>c</code>, or 
	 * null if no one was found in the collection
	 */
	public static SuiModelElement getParentControlFromCollection(Widget c, Collection<Widget> controls) {
		Integer parentCoef = Integer.MAX_VALUE;
		Integer coef;
		Widget parent = null;
		for (Widget c2 : controls) {
			if (SuiUtil.isIncludedIn(c, c2)) {
				coef = SuiUtil.getFitCoefficient(c, c2);
				if (parent == null || coef < parentCoef) {
					parent = c2;
					parentCoef = coef;
				}
			}	
		}
		return parent;	
	}
	
	/**
	 * Calculates a coefficient that indicates how well a control fits into another.
	 * The lesser the coefficient, the best the control <code>c1</code> fits in <code>c2</code>
	 * @return the fit coefficient if the control <code>c1</code> is graphically included
	 * 		in <code>c2</code>, null otherwise
	 */
	private static Integer getFitCoefficient(Widget c1, Widget c2) {
		if (!SuiUtil.isIncludedIn(c1, c2)) {
			return null;
		}
		return 
			c1.getX() - c2.getX() +
			(c2.getX() + c2.getWidth()) - (c1.getX() + c1.getWidth()) +
			(c2.getY() + c2.getHeight()) - (c1.getY() + c1.getHeight());
	}
	
	/**
	 * Sorts <code>controls</code> by x coord in ascending order.
	 */
	public static List<Widget> sortControlsByXCoord(Collection<Widget> controls) {
		return SuiUtil.sortControls(controls, new Comparator<Widget>() {
			public int compare(Widget c1, Widget c2) {
				return c1.getX() - c2.getX();
			}
		});
	}
	
  /**
   * Sorts <code>controls</code> by y coord in ascending order.
   */
	public static List<Widget> sortControlsByYCoord(Collection<Widget> controls) {
		return SuiUtil.sortControls(controls, new Comparator<Widget>() {
			public int compare(Widget c1, Widget c2) {
				return c1.getY() - c2.getY();
			}
		});
	}

	/**
	 * Sorts <code>controls</code> collection using the Comparator provided 
	 */
	public static List<Widget> sortControls(Collection<Widget> controls, Comparator<Widget> comp) {
		if (controls.size() == 0) {
			return new ArrayList<Widget>();
		}
		List<Widget> controlList = new ArrayList<Widget>(controls);
		Collections.sort(controlList, comp);
		return controlList;
	}

	/**
	 * Returns all the controls included in <code>c</code> and its subcontrols
	 * in a recursive manner
	 */
	@SuppressWarnings("unchecked")
  public static Collection<Widget> getAllControlsRecursively(CompositeWidget c) {
		Collection<Widget> allCtrls = new ArrayList<Widget>();
		Collection<Widget> ctrls = new ArrayList<Widget>();
		// TODO an individual collection for controls in CC shouldn't exists. The children controls
		// should be kept in the layout and not replicated in CC. 
		if (c.getLayout() != null) {
			ctrls = c.getLayout().getControls();
		} else {
			ctrls = c.getControls();
		}
		for (Widget ctrl : ctrls) {
			allCtrls.add(ctrl);
		}
		for (Widget ctrl : 
				SuiUtil.filterControlsByType(ctrls, CompositeWidget.class)) {
			allCtrls.addAll(SuiUtil.getAllControlsRecursively((CompositeWidget) ctrl));
		}
		return allCtrls;
	}

	/**
	 * Returns all the controls in <code>controls</code> collection that are colliding
	 * with <code>c</code>
	 */
	public static Collection<Widget> getCollidingControls(Collection<Widget> controls,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.controlsAreColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}
	
	/**
   * Returns all the controls in <code>controls</code> collection that are colliding
   * with <code>c</code> taking into account the x axis
	 */
	public static Collection<Widget> getVerticallyCollidingControls(Collection<Widget> controls,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.controlsAreVerticallyColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}

  /**
   * Returns all <i>d</i> controls in <code>controls</code> collection for which a path 
   * c1..cn exists, where c is vertically colliding with c1, ci is vertically colliding with c(i + 1), 
   * 1 <= i <= n - 1, and cn is vertically colliding with d
   */
	public static Collection<Widget> getAllVerticallyCollidingControls(
			Collection<Widget> controls,
			Widget c) {
		Collection<Widget> ctrlQueue = new HashSet<Widget>(
		  SuiUtil.getVerticallyCollidingControls(controls, c));
		Collection<Widget> collidingControls = new HashSet<Widget>();
		while (ctrlQueue.iterator().hasNext()) {
		  Widget cc = ctrlQueue.iterator().next();
		  ctrlQueue.remove(cc);
			if (cc == c) {
				continue;
			}
			collidingControls.add(cc);
			Collection<Widget> recCtrls = new HashSet<Widget>(controls);
			recCtrls.removeAll(collidingControls);
			ctrlQueue.addAll(SuiUtil.getVerticallyCollidingControls(recCtrls, cc));
		}
		return collidingControls;
	}
	
  /**
   * Returns all the controls in <code>controls</code> collection that are colliding
   * with <code>c</code> taking into account the y axis
   */
	public static Collection<Widget> getHorizontallyCollidingControls(Collection<Widget> controls,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.controlsAreHorizontallyColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}
	
  /**
   * Returns all <i>d</i> controls in <code>controls</code> collection for which a path 
   * c1..cn exists, where c is horizontally colliding with c1, ci is horizontally colliding with c(i + 1), 
   * 1 <= i <= n - 1, and cn is horizontally colliding with d
   */
	public static Collection<Widget> getAllHorizontallyCollidingControls(Collection<Widget> controls,
			Widget c) {
    Collection<Widget> ctrlQueue = new HashSet<Widget>(
      SuiUtil.getHorizontallyCollidingControls(controls, c));
    Collection<Widget> collidingControls = new HashSet<Widget>();
    while (ctrlQueue.iterator().hasNext()) {
      Widget cc = ctrlQueue.iterator().next();
      ctrlQueue.remove(cc);
      if (cc == c) {
        continue;
      }
      collidingControls.add(cc);
      Collection<Widget> recCtrls = new HashSet<Widget>(controls);
      recCtrls.removeAll(collidingControls);
      ctrlQueue.addAll(SuiUtil.getHorizontallyCollidingControls(recCtrls, cc));
    }
    return collidingControls;
	}

	
	/**
	 * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding, false
	 * otherwise
	 */
	private static Boolean controlsAreColliding(Widget c, Widget c2) {
		return 
			SuiUtil.controlsAreVerticallyColliding(c, c2) &&
			SuiUtil.controlsAreHorizontallyColliding(c, c2);
	}
	
  /**
   * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding taking into
   * account only the y axis, or <code>false</code> otherwise
   */
	private static Boolean controlsAreHorizontallyColliding(Widget c, Widget c2) {
		Integer c1TopBorder = c.getY();
		Integer c1BottomBorder = c.getY() + c.getHeight();
		Integer c2TopBorder = c2.getY();
		Integer c2BottomBorder = c2.getY() + c2.getHeight();
		return (
			(c1TopBorder >= c2TopBorder && c1TopBorder <= c2BottomBorder) ||
			(c1BottomBorder >= c2TopBorder && c1BottomBorder <= c2BottomBorder) ||
			(c2BottomBorder >= c1BottomBorder && c2TopBorder <= c1TopBorder) ||
			(c1BottomBorder >= c2BottomBorder && c1TopBorder <= c2TopBorder)
		); 
	}
	
  /**
   * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding taking into
   * account only the x axis, or <code>false</code> otherwise
   */
	private static Boolean controlsAreVerticallyColliding(Widget c, Widget c2) {
		Integer c1LeftBorder = c.getX();
		Integer c1RightBorder = c.getX() + c.getWidth();
		Integer c2LeftBorder = c2.getX();
		Integer c2RightBorder = c2.getX() + c2.getWidth();
		return (
			(c1LeftBorder >= c2LeftBorder && c1LeftBorder <= c2RightBorder) ||
			(c1RightBorder >= c2LeftBorder && c1RightBorder <= c2RightBorder) ||
			(c2RightBorder >= c1RightBorder && c2LeftBorder <= c1LeftBorder) ||
			(c1RightBorder >= c2RightBorder && c1LeftBorder <= c2LeftBorder)
		); 
	}
	
	
	/**
	 * Returns a collection of all the parents of <code>c</code>
	 */
	public static Collection<Widget> getParents(Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		Widget currControl = c.getParent();
		while (currControl != null) {
			ctrls.add(currControl);
			currControl = currControl.getParent();
		}
		return ctrls;
	}

	/**
	 * Returns a collection of controls formed by all the controls in <code>controls</code> 
	 * collection which type is not one of the types in <code>types</code> array 
	 */
	public static Collection<Widget> excludeControlTypes(Collection<Widget> controls,
			Class<? extends Widget>... types) {
		List<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c : controls) {
			Boolean isInTypeList = false;
			for (Class<? extends Widget> clazz : types) {
				if (SuiUtil.controlIsKindOf(c, clazz)) {
					isInTypeList = true;
				}
			}
			if (!isInTypeList) {
				ctrls.add(c);
			}
		}
		return ctrls;		
	}
	
	/**
	 * Groups controls that are mutually and vertically colliding and returns a list of these
	 * groups, sorted by y coord 
	 */
	public static List<ControlList> getControlsGroupedInColumns(
			Collection<Widget> controls) {
		Collection<Widget> processedControls = 
			new ArrayList<Widget>();
		List<Widget> orderedControls = SuiUtil.sortControlsByXCoord(controls);
		List<ControlList> controlLists = new ArrayList<ControlList>();
		for (Widget c : orderedControls) {
			if (processedControls.contains(c)) {
				continue;
			}
			Collection<Widget> collCtrls =
				SuiUtil.getAllVerticallyCollidingControls(controls, c);
			collCtrls.add(c);
			controlLists.add(
				new ControlList(
					SuiUtil.sortControlsByXCoord(collCtrls)));
			processedControls.addAll(collCtrls);
		}
		return controlLists;
	}

	 /**
   * Groups controls that are mutually and horizontally colliding and returns a list of these
   * groups, sorted by x coord 
   */
	public static List<ControlList> getControlsGroupedInRows(
			Collection<Widget> controls) {
		Collection<Widget> processedControls = 
			new ArrayList<Widget>();
		List<Widget> orderedControls = SuiUtil.sortControlsByYCoord(controls);
		List<ControlList> controlLists = new ArrayList<ControlList>();
		for (Widget c : orderedControls) {
			if (processedControls.contains(c)) {
				continue;
			}
			Collection<Widget> collCtrls =
				SuiUtil.getAllHorizontallyCollidingControls(controls, c);
			collCtrls.add(c);
			controlLists.add(
				new ControlList(
					SuiUtil.sortControlsByYCoord(collCtrls)));
			processedControls.addAll(collCtrls);
		}
		return controlLists;
	}

	
	/**
	 * Returns <code>true</code> if <code>c1</code> and <code>c2</code>
	 * are controls of the same type and approximately have the same sizes, 
	 * allowing a difference of <code>maxDifference</code> pixels at most
	 */
	public static Boolean areGraphicallySimilar(Widget c1, Widget c2,
			Integer maxDifference) {
		return
			c1.getClass().equals(c2.getClass()) &&
			Math.abs(c1.getWidth() - c2.getWidth()) <= maxDifference &&
			Math.abs(c1.getHeight() - c2.getHeight()) <= maxDifference;
	}

	
  /**
   * Returns a rectangle surrounding all the controls provided in the 
   * <code>controls</code> collection. Some extra <code>marginSize</code> can be
   * provided to expand the returned rectangle from its original size.
   */
  public static Rectangle getSurroundingRectangle(Collection<Widget> controls, Integer marginSize) {
    MaxHolder<Widget, Integer> rightBorder = new MaxHolder<Widget, Integer>();
    MaxHolder<Widget, Integer> bottomBorder = new MaxHolder<Widget, Integer>();
    MinHolder<Widget, Integer> leftBorder = new MinHolder<Widget, Integer>();
    MinHolder<Widget, Integer> topBorder = new MinHolder<Widget, Integer>();
    for (Widget c : controls) {
      leftBorder.store(c, c.getX());
      topBorder.store(c, c.getY());
      rightBorder.store(c, c.getX() + c.getWidth());
      bottomBorder.store(c, c.getY() + c.getHeight());
    }
    return new Rectangle(leftBorder.getMinValue() - 1, rightBorder.getMaxValue() + 1, 
            topBorder.getMinValue() - 1, bottomBorder.getMaxValue() + 1);
  }

}
