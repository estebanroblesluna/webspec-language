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

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public class AbsoluteLayoutInfoImpl implements AbsoluteLayoutInfo {

  private int x;
  private int y;
  private int width;
  private int height;
  private Widget control;

  public AbsoluteLayoutInfoImpl(int x, int y, int width, int height, Widget control) {
    super();
    this.setX(x);
    this.setY(y);
    this.setWidth(width);
    this.setHeight(height);
    this.setControl(control);
  }

  private void setX(int x) {
    this.x = x;
  }

  public int getX() {
    return x;
  }
  
  private void setY(int y) {
    this.y = y;
  }
  
  public int getY() {
    return y;
  }
  
  private void setWidth(int width) {
    this.width = width;
  }
  
  public int getWidth() {
    return width;
  }
  
  private void setHeight(int weight) {
    this.height = weight;
  }
  
  public int getHeight() {
    return height;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitAbsoluteLayoutInfo(this);
  }

  public Object copy() {
    return new AbsoluteLayoutInfoImpl(this.getX(), this.getY(), this.getWidth(), this.getHeight(), (Widget) this.getControl().copy());
  }

  private void setControl(Widget control) {
    this.control = control;
  }

  public Widget getControl() {
    return control;
  }

}
