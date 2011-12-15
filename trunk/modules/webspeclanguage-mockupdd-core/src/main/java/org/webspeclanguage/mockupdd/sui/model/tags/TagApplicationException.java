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
package org.webspeclanguage.mockupdd.sui.model.tags;

import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * @author Jose Matias Rivero
 */
public class TagApplicationException extends Exception {

  private static final long serialVersionUID = 1L;
  private Widget widget;
  private Tag tag;
  private List<TagParameterValue> parameterValues;

  public TagApplicationException(Widget widget, Tag tag, List<TagParameterValue> parameterValues, String message) {
    super(message);
    this.setWidget(widget);
    this.setTag(tag);
    this.setParameterValues(parameterValues);
  }

  private void setWidget(Widget widget) {
    this.widget = widget;
  }

  public Widget getWidget() {
    return widget;
  }

  private void setTag(Tag tag) {
    this.tag = tag;
  }

  public Tag getTag() {
    return tag;
  }

  private void setParameterValues(List<TagParameterValue> parameterValues) {
    this.parameterValues = parameterValues;
  }

  public List<TagParameterValue> getParameterValues() {
    return parameterValues;
  }

}
