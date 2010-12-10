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
package org.webspeclanguage.metamock.model.impl;

import org.webspeclanguage.metamock.model.NumericSpinner;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link NumericSpinner}
 * 
 * @author Jose Matias Rivero
 */
public class NumericSpinnerImpl extends SimpleControlImpl implements NumericSpinner {

  private Integer minValue;
  private Integer maxValue;

  public NumericSpinnerImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Integer minValue, Integer maxValue) {
    super(controlID, x, y, width, height);
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

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitNumericSpinner(this);
  }

  public Boolean equalInContent(UIControl control) {
    NumericSpinner other = (NumericSpinner) control;
    return super.equalInContent(other) && this.getMinValue().equals(other.getMinValue()) && this.getMaxValue().equals(other.getMaxValue());
  }

  public UIControl copyConcreteControl() {
    return new NumericSpinnerImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getMinValue(), this.getMaxValue());
  }

}
