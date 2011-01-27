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

import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * @author Jose Matias Rivero
 */
public class AbsoluteLayoutImpl implements AbsoluteLayout {

  public AbsoluteLayoutImpl() {
    super();
    this.setLayoutInfoByControl(new HashMap<UIControl, AbsoluteLayoutInfo>());
  }

  private Map<UIControl, AbsoluteLayoutInfo> layoutInfoByControl;

  private void setLayoutInfoByControl(Map<UIControl, AbsoluteLayoutInfo> layoutInfoByControl) {
    this.layoutInfoByControl = layoutInfoByControl;
  }

  private Map<UIControl, AbsoluteLayoutInfo> getLayoutInfoByControl() {
    return layoutInfoByControl;
  }

  public AbsoluteLayoutInfo getInfoForControl(UIControl c) {
    return this.getLayoutInfoByControl().get(c);
  }

  public void addControl(UIControl c, AbsoluteLayoutInfo layoutInfo) {
    this.getLayoutInfoByControl().put(c, layoutInfo);
  }

  public Collection<UIControl> getControls() {
    return this.getLayoutInfoByControl().keySet();
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitAbsoluteLayout(this);
  }

  public Object copy() {
    AbsoluteLayoutImpl layout = new AbsoluteLayoutImpl();
    for (UIControl c : layout.getControls()) {
      AbsoluteLayoutInfo info = (AbsoluteLayoutInfo) this.getInfoForControl(c).copy();
      layout.addControl(info.getControl(), info); 
    }
    return layout;
  }

  public Collection<AbsoluteLayoutInfo> getAllLayoutInfo() {
    return this.getLayoutInfoByControl().values();
  }

}
