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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public class AbsoluteLayoutImpl implements AbsoluteLayout {

  public AbsoluteLayoutImpl() {
    super();
    this.setLayoutInfoByWidget(new HashMap<Widget, AbsoluteLayoutInfo>());
  }

  private Map<Widget, AbsoluteLayoutInfo> layoutInfoByWidget;

  private void setLayoutInfoByWidget(Map<Widget, AbsoluteLayoutInfo> layoutInfoByWidget) {
    this.layoutInfoByWidget = layoutInfoByWidget;
  }

  private Map<Widget, AbsoluteLayoutInfo> getLayoutInfoByWidget() {
    return layoutInfoByWidget;
  }

  public AbsoluteLayoutInfo getInfoForWidget(Widget c) {
    return this.getLayoutInfoByWidget().get(c);
  }

  public void addWidget(Widget c, AbsoluteLayoutInfo layoutInfo) {
    this.getLayoutInfoByWidget().put(c, layoutInfo);
  }

  public Collection<Widget> getWidgets() {
    return this.getLayoutInfoByWidget().keySet();
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitAbsoluteLayout(this);
  }

  public Object copy() {
    AbsoluteLayoutImpl layout = new AbsoluteLayoutImpl();
    for (Widget c : layout.getWidgets()) {
      AbsoluteLayoutInfo info = (AbsoluteLayoutInfo) this.getInfoForWidget(c).copy();
      layout.addWidget(info.getWidget(), info); 
    }
    return layout;
  }

  public Collection<AbsoluteLayoutInfo> getAllLayoutInfo() {
    return this.getLayoutInfoByWidget().values();
  }

  public void replaceWidget(Widget widgetToReplace, Widget replacingWidget) {
    AbsoluteLayoutInfo info = this.getLayoutInfoByWidget().get(widgetToReplace);
    this.getLayoutInfoByWidget().remove(widgetToReplace);
    this.getLayoutInfoByWidget().put(replacingWidget, info);
  }

}
