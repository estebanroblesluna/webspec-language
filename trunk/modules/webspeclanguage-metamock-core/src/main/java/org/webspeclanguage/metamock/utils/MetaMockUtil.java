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

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockElement;
import org.webspeclanguage.metamock.model.UIControl;


/**
 * Provides some useful methods to check properties or features in a
 * MockSpec model or its components, avoiding pollution of language interfaces and 
 * 
 * @author Jose Matias Rivero
 */
public final class MetaMockUtil {

	private MetaMockUtil() {
		
	}
	
	public static Boolean isComposite(MetaMockElement c) {
		return c instanceof CompositeControl;
	}
	
	/**
	 * Returns <code>true</code> if the control <code>c1</code> is vertically
	 * included into the control <code>c2</code>, <code>false</code> otherwise
	 */
	public static Boolean isVerticallyIncludedIn(UIControl c1, UIControl c2) {
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
	public static Boolean isHorizontallyIncludedIn(UIControl c1, UIControl c2) {
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
	public static Boolean isIncludedIn(UIControl c1, UIControl c2) {
		if (c1.equals(c2)) {
			return false;
		}
		return
			MetaMockUtil.isVerticallyIncludedIn(c1, c2) &&
			MetaMockUtil.isHorizontallyIncludedIn(c1, c2);
	}
	
	/**
	 * Returns a subset of <code>controls</code> containing only the controls graphically
	 * in <code>c</code> 
	 * @param c
	 * @param ctrls
	 * @return 
	 */
	public static Collection<UIControl> controlsIncludedIn(UIControl c, Collection<UIControl> ctrls) {
		Set<UIControl> included = new HashSet<UIControl>();
		for (UIControl ctrl : ctrls) {
			if (ctrl != c && MetaMockUtil.isIncludedIn(ctrl, c)) {
				included.add(ctrl);
			}
		}
		return included;
	}
	
	public static Collection<UIControl> filterControlsByType(Collection<UIControl> controls, Class<? extends UIControl>... types) {
		Set<UIControl> included = new HashSet<UIControl>();
		for (UIControl c : controls) {
			for (Class<? extends UIControl> type : types) {
				if (MetaMockUtil.controlIsKindOf(c, type)) {
					included.add((UIControl)c);
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
	public static Boolean controlIsKindOf(UIControl c, Class<? extends UIControl> clazz) {
		return clazz.isAssignableFrom(c.getClass());
	}
	
	/**
	 * Returns the parent control found in <code>controls</code> for the control <code>c</code>, or 
	 * null if no one was found in the collection
	 */
	public static MetaMockElement getParentControlFromCollection(UIControl c, Collection<UIControl> controls) {
		Integer parentCoef = Integer.MAX_VALUE;
		Integer coef;
		UIControl parent = null;
		for (UIControl c2 : controls) {
			if (MetaMockUtil.isIncludedIn(c, c2)) {
				coef = MetaMockUtil.getFitCoefficient(c, c2);
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
	private static Integer getFitCoefficient(UIControl c1, UIControl c2) {
		if (!MetaMockUtil.isIncludedIn(c1, c2)) {
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
	public static List<UIControl> sortControlsByXCoord(Collection<UIControl> controls) {
		return MetaMockUtil.sortControls(controls, new Comparator<UIControl>() {
			public int compare(UIControl c1, UIControl c2) {
				return c1.getX() - c2.getX();
			}
		});
	}
	
  /**
   * Sorts <code>controls</code> by y coord in ascending order.
   */
	public static List<UIControl> sortControlsByYCoord(Collection<UIControl> controls) {
		return MetaMockUtil.sortControls(controls, new Comparator<UIControl>() {
			public int compare(UIControl c1, UIControl c2) {
				return c1.getY() - c2.getY();
			}
		});
	}

	/**
	 * Sorts <code>controls</code> collection using the Comparator provided 
	 */
	public static List<UIControl> sortControls(Collection<UIControl> controls, Comparator<UIControl> comp) {
		if (controls.size() == 0) {
			return new ArrayList<UIControl>();
		}
		List<UIControl> controlList = new ArrayList<UIControl>(controls);
		Collections.sort(controlList, comp);
		return controlList;
	}

	/**
	 * Returns all the controls included in <code>c</code> and its subcontrols
	 * in a recursive manner
	 */
	@SuppressWarnings("unchecked")
  public static Collection<UIControl> getAllControlsRecursively(CompositeControl c) {
		Collection<UIControl> allCtrls = new ArrayList<UIControl>();
		Collection<UIControl> ctrls = new ArrayList<UIControl>();
		// TODO an individual collection for controls in CC shouldn't exists. The children controls
		// should be kept in the layout and not replicated in CC. 
		if (c.getLayout() != null) {
			ctrls = c.getLayout().getControls();
		} else {
			ctrls = c.getControls();
		}
		for (UIControl ctrl : ctrls) {
			allCtrls.add(ctrl);
		}
		for (UIControl ctrl : 
				MetaMockUtil.filterControlsByType(ctrls, CompositeControl.class)) {
			allCtrls.addAll(MetaMockUtil.getAllControlsRecursively((CompositeControl) ctrl));
		}
		return allCtrls;
	}

	/**
	 * Returns all the controls in <code>controls</code> collection that are colliding
	 * with <code>c</code>
	 */
	public static Collection<UIControl> getCollidingControls(Collection<UIControl> controls,
			UIControl c) {
		Collection<UIControl> ctrls = new ArrayList<UIControl>();
		for (UIControl c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (MetaMockUtil.controlsAreColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}
	
	/**
   * Returns all the controls in <code>controls</code> collection that are colliding
   * with <code>c</code> taking into account the x axis
	 */
	public static Collection<UIControl> getVerticallyCollidingControls(Collection<UIControl> controls,
			UIControl c) {
		Collection<UIControl> ctrls = new ArrayList<UIControl>();
		for (UIControl c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (MetaMockUtil.controlsAreVerticallyColliding(c, c2)) {
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
	public static Collection<UIControl> getAllVerticallyCollidingControls(
			Collection<UIControl> controls,
			UIControl c) {
		Collection<UIControl> ctrlQueue = new HashSet<UIControl>(
		  MetaMockUtil.getVerticallyCollidingControls(controls, c));
		Collection<UIControl> collidingControls = new HashSet<UIControl>();
		while (ctrlQueue.iterator().hasNext()) {
		  UIControl cc = ctrlQueue.iterator().next();
		  ctrlQueue.remove(cc);
			if (cc == c) {
				continue;
			}
			collidingControls.add(cc);
			Collection<UIControl> recCtrls = new HashSet<UIControl>(controls);
			recCtrls.removeAll(collidingControls);
			ctrlQueue.addAll(MetaMockUtil.getVerticallyCollidingControls(recCtrls, cc));
		}
		return collidingControls;
	}
	
  /**
   * Returns all the controls in <code>controls</code> collection that are colliding
   * with <code>c</code> taking into account the y axis
   */
	public static Collection<UIControl> getHorizontallyCollidingControls(Collection<UIControl> controls,
			UIControl c) {
		Collection<UIControl> ctrls = new ArrayList<UIControl>();
		for (UIControl c2 : controls) {
			if (c2 == c) {
				continue;
			}
			if (MetaMockUtil.controlsAreHorizontallyColliding(c, c2)) {
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
	public static Collection<UIControl> getAllHorizontallyCollidingControls(Collection<UIControl> controls,
			UIControl c) {
    Collection<UIControl> ctrlQueue = new HashSet<UIControl>(
      MetaMockUtil.getHorizontallyCollidingControls(controls, c));
    Collection<UIControl> collidingControls = new HashSet<UIControl>();
    while (ctrlQueue.iterator().hasNext()) {
      UIControl cc = ctrlQueue.iterator().next();
      ctrlQueue.remove(cc);
      if (cc == c) {
        continue;
      }
      collidingControls.add(cc);
      Collection<UIControl> recCtrls = new HashSet<UIControl>(controls);
      recCtrls.removeAll(collidingControls);
      ctrlQueue.addAll(MetaMockUtil.getHorizontallyCollidingControls(recCtrls, cc));
    }
    return collidingControls;
	}

	
	/**
	 * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding, false
	 * otherwise
	 */
	private static Boolean controlsAreColliding(UIControl c, UIControl c2) {
		return 
			MetaMockUtil.controlsAreVerticallyColliding(c, c2) &&
			MetaMockUtil.controlsAreHorizontallyColliding(c, c2);
	}
	
  /**
   * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding taking into
   * account only the y axis, or <code>false</code> otherwise
   */
	private static Boolean controlsAreHorizontallyColliding(UIControl c, UIControl c2) {
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
	private static Boolean controlsAreVerticallyColliding(UIControl c, UIControl c2) {
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
	public static Collection<UIControl> getParents(UIControl c) {
		Collection<UIControl> ctrls = new ArrayList<UIControl>();
		UIControl currControl = c.getParent();
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
	public static Collection<UIControl> excludeControlTypes(Collection<UIControl> controls,
			Class<? extends UIControl>... types) {
		List<UIControl> ctrls = new ArrayList<UIControl>();
		for (UIControl c : controls) {
			Boolean isInTypeList = false;
			for (Class<? extends UIControl> clazz : types) {
				if (MetaMockUtil.controlIsKindOf(c, clazz)) {
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
			Collection<UIControl> controls) {
		Collection<UIControl> processedControls = 
			new ArrayList<UIControl>();
		List<UIControl> orderedControls = MetaMockUtil.sortControlsByXCoord(controls);
		List<ControlList> controlLists = new ArrayList<ControlList>();
		for (UIControl c : orderedControls) {
			if (processedControls.contains(c)) {
				continue;
			}
			Collection<UIControl> collCtrls =
				MetaMockUtil.getAllVerticallyCollidingControls(controls, c);
			collCtrls.add(c);
			controlLists.add(
				new ControlList(
					MetaMockUtil.sortControlsByXCoord(collCtrls)));
			processedControls.addAll(collCtrls);
		}
		return controlLists;
	}

	 /**
   * Groups controls that are mutually and horizontally colliding and returns a list of these
   * groups, sorted by x coord 
   */
	public static List<ControlList> getControlsGroupedInRows(
			Collection<UIControl> controls) {
		Collection<UIControl> processedControls = 
			new ArrayList<UIControl>();
		List<UIControl> orderedControls = MetaMockUtil.sortControlsByYCoord(controls);
		List<ControlList> controlLists = new ArrayList<ControlList>();
		for (UIControl c : orderedControls) {
			if (processedControls.contains(c)) {
				continue;
			}
			Collection<UIControl> collCtrls =
				MetaMockUtil.getAllHorizontallyCollidingControls(controls, c);
			collCtrls.add(c);
			controlLists.add(
				new ControlList(
					MetaMockUtil.sortControlsByYCoord(collCtrls)));
			processedControls.addAll(collCtrls);
		}
		return controlLists;
	}

	
	/**
	 * Returns <code>true</code> if <code>c1</code> and <code>c2</code>
	 * are controls of the same type and approximately have the same sizes, 
	 * allowing a difference of <code>maxDifference</code> pixels at most
	 */
	public static Boolean areGraphicallySimilar(UIControl c1, UIControl c2,
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
  public static Rectangle getSurroundingRectangle(Collection<UIControl> controls, Integer marginSize) {
    MaxHolder<UIControl, Integer> rightBorder = new MaxHolder<UIControl, Integer>();
    MaxHolder<UIControl, Integer> bottomBorder = new MaxHolder<UIControl, Integer>();
    MinHolder<UIControl, Integer> leftBorder = new MinHolder<UIControl, Integer>();
    MinHolder<UIControl, Integer> topBorder = new MinHolder<UIControl, Integer>();
    for (UIControl c : controls) {
      leftBorder.store(c, c.getX());
      topBorder.store(c, c.getY());
      rightBorder.store(c, c.getX() + c.getWidth());
      bottomBorder.store(c, c.getY() + c.getHeight());
    }
    return new Rectangle(leftBorder.getMinValue() - 1, rightBorder.getMaxValue() + 1, 
            topBorder.getMinValue() - 1, bottomBorder.getMaxValue() + 1);
  }

}
