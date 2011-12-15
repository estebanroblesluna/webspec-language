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
package org.webspeclanguage.mockupdd.sui.model.impl;

import org.webspeclanguage.mockupdd.sui.model.NumericSpinner;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link NumericSpinner}
 * 
 * @author Jose Matias Rivero
 */
public class NumericSpinnerImpl extends SimpleWidgetImpl implements NumericSpinner {

  private Integer minValue;
  private Integer maxValue;

  public NumericSpinnerImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue) {
    super(widgetID, x, y, width, height);
    this.setMinValue(minValue);
    this.setMaxValue(maxValue);
  }

  public Integer getMaxValue() {
    return maxValue;
  }

  public Integer getMinValue() {
    return minValue;
  }

  private void setMinValue(Integer minValue) {
    this.minValue = minValue;
  }

  private void setMaxValue(Integer maxValue) {
    this.maxValue = maxValue;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitNumericSpinner(this);
  }

  public Boolean equalInContent(Widget widget) {
    NumericSpinner other = (NumericSpinner) widget;
    return super.equalInContent(other) && this.getMinValue().equals(other.getMinValue()) && this.getMaxValue().equals(other.getMaxValue());
  }

  public Widget copyConcreteWidget() {
    return new NumericSpinnerImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getMinValue(), this.getMaxValue());
  }

}
