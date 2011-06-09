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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public class AbsoluteLayoutImpl implements AbsoluteLayout {

  public AbsoluteLayoutImpl() {
    super();
    this.setLayoutInfoByControl(new HashMap<Widget, AbsoluteLayoutInfo>());
  }

  private Map<Widget, AbsoluteLayoutInfo> layoutInfoByControl;

  private void setLayoutInfoByControl(Map<Widget, AbsoluteLayoutInfo> layoutInfoByControl) {
    this.layoutInfoByControl = layoutInfoByControl;
  }

  private Map<Widget, AbsoluteLayoutInfo> getLayoutInfoByControl() {
    return layoutInfoByControl;
  }

  public AbsoluteLayoutInfo getInfoForControl(Widget c) {
    return this.getLayoutInfoByControl().get(c);
  }

  public void addControl(Widget c, AbsoluteLayoutInfo layoutInfo) {
    this.getLayoutInfoByControl().put(c, layoutInfo);
  }

  public Collection<Widget> getControls() {
    return this.getLayoutInfoByControl().keySet();
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitAbsoluteLayout(this);
  }

  public Object copy() {
    AbsoluteLayoutImpl layout = new AbsoluteLayoutImpl();
    for (Widget c : layout.getControls()) {
      AbsoluteLayoutInfo info = (AbsoluteLayoutInfo) this.getInfoForControl(c).copy();
      layout.addControl(info.getControl(), info); 
    }
    return layout;
  }

  public Collection<AbsoluteLayoutInfo> getAllLayoutInfo() {
    return this.getLayoutInfoByControl().values();
  }

  public void replaceControl(Widget controlToReplace, Widget replacingControl) {
    AbsoluteLayoutInfo info = this.getLayoutInfoByControl().get(controlToReplace);
    this.getLayoutInfoByControl().remove(controlToReplace);
    this.getLayoutInfoByControl().put(replacingControl, info);
  }

}
