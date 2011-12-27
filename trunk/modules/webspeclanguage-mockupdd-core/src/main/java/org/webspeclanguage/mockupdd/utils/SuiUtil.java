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
package org.webspeclanguage.mockupdd.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.Widget;


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
	 * Returns <code>true</code> if the widget <code>c1</code> is vertically
	 * included into the widget <code>c2</code>, <code>false</code> otherwise
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
   * Returns <code>true</code> if the widget <code>c1</code> is horizontally
   * included into the widget <code>c2</code>, or <code>false</code> otherwise
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
	 * Returns <code>true</code> if the widget <code>c1</code> is graphically included in
	 * widget <code>c2</code> or <code>false</code> otherwise
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
	 * Returns a subset of <code>widgets</code> containing only the widgets graphically
	 * in <code>c</code> 
	 * @param c
	 * @param ctrls
	 * @return 
	 */
	public static Collection<Widget> widgetsIncludedIn(Widget c, Collection<Widget> ctrls) {
		Set<Widget> included = new HashSet<Widget>();
		for (Widget ctrl : ctrls) {
			if (ctrl != c && SuiUtil.isIncludedIn(ctrl, c)) {
				included.add(ctrl);
			}
		}
		return included;
	}
	
	public static Collection<Widget> filterWidgetsByType(Collection<Widget> widgets, Class<? extends Widget>... types) {
		Set<Widget> included = new HashSet<Widget>();
		for (Widget c : widgets) {
			for (Class<? extends Widget> type : types) {
				if (SuiUtil.widgetIsKindOf(c, type)) {
					included.add((Widget)c);
					break;
				}
			}
		}
		return included;			
	}
	
	/**
	 * Returns <code>true</code> if the widget <code>c</code> is instance of <code>clazz</code> or
	 * instance of some <code>clazz</code>'s superclass 
	 */
	public static Boolean widgetIsKindOf(Widget c, Class<? extends Widget> clazz) {
		return clazz.isAssignableFrom(c.getClass());
	}
	
	/**
	 * Returns the parent widget found in <code>widgets</code> for the widget <code>c</code>, or 
	 * null if no one was found in the collection
	 */
	public static SuiModelElement getParentWidgetFromCollection(Widget c, Collection<Widget> widgets) {
		Integer parentCoef = Integer.MAX_VALUE;
		Integer coef;
		Widget parent = null;
		for (Widget c2 : widgets) {
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
	 * Calculates a coefficient that indicates how well a widget fits into another.
	 * The lesser the coefficient, the best the widget <code>c1</code> fits in <code>c2</code>
	 * @return the fit coefficient if the widget <code>c1</code> is graphically included
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
	 * Sorts <code>widgets</code> by x coord in ascending order.
	 */
	public static List<Widget> sortWidgetsByXCoord(Collection<Widget> widgets) {
		return SuiUtil.sortWidgets(widgets, new Comparator<Widget>() {
			public int compare(Widget c1, Widget c2) {
				return c1.getX() - c2.getX();
			}
		});
	}
	
  /**
   * Sorts <code>widgets</code> by y coord in ascending order.
   */
	public static List<Widget> sortWidgetsByYCoord(Collection<Widget> widgets) {
		return SuiUtil.sortWidgets(widgets, new Comparator<Widget>() {
			public int compare(Widget c1, Widget c2) {
				return c1.getY() - c2.getY();
			}
		});
	}

	/**
	 * Sorts <code>widgets</code> collection using the Comparator provided 
	 */
	public static List<Widget> sortWidgets(Collection<Widget> widgets, Comparator<Widget> comp) {
		if (widgets.size() == 0) {
			return new ArrayList<Widget>();
		}
		List<Widget> widgetList = new ArrayList<Widget>(widgets);
		Collections.sort(widgetList, comp);
		return widgetList;
	}

	/**
	 * Returns all the widgets included in <code>c</code> and its subwidgets
	 * in a recursive manner
	 */
	@SuppressWarnings("unchecked")
  public static Collection<Widget> getAllWidgetsRecursively(CompositeWidget c) {
		Collection<Widget> allCtrls = new HashSet<Widget>();
		// TODO an individual collection for widgets in CC shouldn't exists. The children widgets
		// should be kept in the layout and not replicated in CC. 
		for (Widget ctrl : c.getWidgets()) {
			allCtrls.add(ctrl);
		}
		for (Widget ctrl : 
				SuiUtil.filterWidgetsByType(c.getWidgets(), CompositeWidget.class)) {
			allCtrls.addAll(SuiUtil.getAllWidgetsRecursively((CompositeWidget) ctrl));
		}
		return allCtrls;
	}
	
	/**
   * The same that <code>getAllWidgetsRecursively</code>, but also returns the parent
   * widget
   */
	public static Collection<Widget> getAllWidgetsRecursivelyIncludingParent(CompositeWidget c) {
	  Collection<Widget> widgets = getAllWidgetsRecursively(c);
	  widgets.add(c);
	  return widgets;	
	}

	/**
	 * Returns all the widgets in <code>widgets</code> collection that are colliding
	 * with <code>c</code>
	 */
	public static Collection<Widget> getCollidingWidgets(Collection<Widget> widgets,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : widgets) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.widgetsAreColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}
	
	/**
   * Returns all the widgets in <code>widgets</code> collection that are colliding
   * with <code>c</code> taking into account the x axis
	 */
	public static Collection<Widget> getVerticallyCollidingWidgets(Collection<Widget> widgets,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : widgets) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.widgetsAreVerticallyColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}

  /**
   * Returns all <i>d</i> widgets in <code>widgets</code> collection for which a path 
   * c1..cn exists, where c is vertically colliding with c1, ci is vertically colliding with c(i + 1), 
   * 1 <= i <= n - 1, and cn is vertically colliding with d
   */
	public static Collection<Widget> getAllVerticallyCollidingWidgets(
			Collection<Widget> widgets,
			Widget c) {
		Collection<Widget> ctrlQueue = new HashSet<Widget>(
		  SuiUtil.getVerticallyCollidingWidgets(widgets, c));
		Collection<Widget> collidingWidgets = new HashSet<Widget>();
		while (ctrlQueue.iterator().hasNext()) {
		  Widget cc = ctrlQueue.iterator().next();
		  ctrlQueue.remove(cc);
			if (cc == c) {
				continue;
			}
			collidingWidgets.add(cc);
			Collection<Widget> recCtrls = new HashSet<Widget>(widgets);
			recCtrls.removeAll(collidingWidgets);
			ctrlQueue.addAll(SuiUtil.getVerticallyCollidingWidgets(recCtrls, cc));
		}
		return collidingWidgets;
	}
	
  /**
   * Returns all the widgets in <code>widgets</code> collection that are colliding
   * with <code>c</code> taking into account the y axis
   */
	public static Collection<Widget> getHorizontallyCollidingWidgets(Collection<Widget> widgets,
			Widget c) {
		Collection<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c2 : widgets) {
			if (c2 == c) {
				continue;
			}
			if (SuiUtil.widgetsAreHorizontallyColliding(c, c2)) {
				ctrls.add(c2);
			}
		}
		return ctrls;
	}
	
  /**
   * Returns all <i>d</i> widgets in <code>widgets</code> collection for which a path 
   * c1..cn exists, where c is horizontally colliding with c1, ci is horizontally colliding with c(i + 1), 
   * 1 <= i <= n - 1, and cn is horizontally colliding with d
   */
	public static Collection<Widget> getAllHorizontallyCollidingWidgets(Collection<Widget> widgets,
			Widget c) {
    Collection<Widget> ctrlQueue = new HashSet<Widget>(
      SuiUtil.getHorizontallyCollidingWidgets(widgets, c));
    Collection<Widget> collidingWidgets = new HashSet<Widget>();
    while (ctrlQueue.iterator().hasNext()) {
      Widget cc = ctrlQueue.iterator().next();
      ctrlQueue.remove(cc);
      if (cc == c) {
        continue;
      }
      collidingWidgets.add(cc);
      Collection<Widget> recCtrls = new HashSet<Widget>(widgets);
      recCtrls.removeAll(collidingWidgets);
      ctrlQueue.addAll(SuiUtil.getHorizontallyCollidingWidgets(recCtrls, cc));
    }
    return collidingWidgets;
	}

	
	/**
	 * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding, false
	 * otherwise
	 */
	private static Boolean widgetsAreColliding(Widget c, Widget c2) {
		return 
			SuiUtil.widgetsAreVerticallyColliding(c, c2) &&
			SuiUtil.widgetsAreHorizontallyColliding(c, c2);
	}
	
  /**
   * Returns <code>true</code> if <code>c</code> and <code>c2</code> are colliding taking into
   * account only the y axis, or <code>false</code> otherwise
   */
	private static Boolean widgetsAreHorizontallyColliding(Widget c, Widget c2) {
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
	private static Boolean widgetsAreVerticallyColliding(Widget c, Widget c2) {
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
	public static List<Widget> getParents(Widget c) {
		List<Widget> ctrls = new ArrayList<Widget>();
		Widget currWidget = c.getParent();
		while (currWidget != null) {
			ctrls.add(currWidget);
			currWidget = currWidget.getParent();
		}
		return ctrls;
	}

	/**
	 * Returns a collection of widgets formed by all the widgets in <code>widgets</code> 
	 * collection which type is not one of the types in <code>types</code> array 
	 */
	public static Collection<Widget> excludeWidgetTypes(Collection<Widget> widgets,
			Class<? extends Widget>... types) {
		List<Widget> ctrls = new ArrayList<Widget>();
		for (Widget c : widgets) {
			Boolean isInTypeList = false;
			for (Class<? extends Widget> clazz : types) {
				if (SuiUtil.widgetIsKindOf(c, clazz)) {
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
	 * Groups widgets that are mutually and vertically colliding and returns a list of these
	 * groups, sorted by y coord 
	 */
	public static List<WidgetList> getWidgetsGroupedInColumns(
			Collection<Widget> widgets) {
		Collection<Widget> processedWidgets = 
			new ArrayList<Widget>();
		List<Widget> orderedWidgets = SuiUtil.sortWidgetsByXCoord(widgets);
		List<WidgetList> widgetLists = new ArrayList<WidgetList>();
		for (Widget c : orderedWidgets) {
			if (processedWidgets.contains(c)) {
				continue;
			}
			Collection<Widget> collCtrls =
				SuiUtil.getAllVerticallyCollidingWidgets(widgets, c);
			collCtrls.add(c);
			widgetLists.add(
				new WidgetList(
					SuiUtil.sortWidgetsByXCoord(collCtrls)));
			processedWidgets.addAll(collCtrls);
		}
		return widgetLists;
	}

	 /**
   * Groups widgets that are mutually and horizontally colliding and returns a list of these
   * groups, sorted by x coord 
   */
	public static List<WidgetList> getWidgetsGroupedInRows(
			Collection<Widget> widgets) {
		Collection<Widget> processedWidgets = 
			new ArrayList<Widget>();
		List<Widget> orderedWidgets = SuiUtil.sortWidgetsByYCoord(widgets);
		List<WidgetList> widgetLists = new ArrayList<WidgetList>();
		for (Widget c : orderedWidgets) {
			if (processedWidgets.contains(c)) {
				continue;
			}
			Collection<Widget> collCtrls =
				SuiUtil.getAllHorizontallyCollidingWidgets(widgets, c);
			collCtrls.add(c);
			widgetLists.add(
				new WidgetList(
					SuiUtil.sortWidgetsByYCoord(collCtrls)));
			processedWidgets.addAll(collCtrls);
		}
		return widgetLists;
	}

	
	/**
	 * Returns <code>true</code> if <code>c1</code> and <code>c2</code>
	 * are widgets of the same type and approximately have the same sizes, 
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
   * Returns a rectangle surrounding all the widgets provided in the 
   * <code>widgets</code> collection. Some extra <code>marginSize</code> can be
   * provided to expand the returned rectangle from its original size.
   */
  public static Rectangle getSurroundingRectangle(Collection<Widget> widgets, Integer marginSize) {
    MaxHolder<Widget, Integer> rightBorder = new MaxHolder<Widget, Integer>();
    MaxHolder<Widget, Integer> bottomBorder = new MaxHolder<Widget, Integer>();
    MinHolder<Widget, Integer> leftBorder = new MinHolder<Widget, Integer>();
    MinHolder<Widget, Integer> topBorder = new MinHolder<Widget, Integer>();
    for (Widget c : widgets) {
      leftBorder.store(c, c.getX());
      topBorder.store(c, c.getY());
      rightBorder.store(c, c.getX() + c.getWidth());
      bottomBorder.store(c, c.getY() + c.getHeight());
    }
    return new Rectangle(leftBorder.getMinValue() - 1, rightBorder.getMaxValue() + 1, 
            topBorder.getMinValue() - 1, bottomBorder.getMaxValue() + 1);
  }

}
