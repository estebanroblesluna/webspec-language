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
package org.webspeclanguage.mockupdd.sui.model.impl.tags;

import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;

public class TagApplicationImpl implements TagApplication {

  private Widget widget;
  private Tag tag;
  private List<TagParameterValue> parameterValues;

  public TagApplicationImpl(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    super();
    this.validateParams(widget, tag, parameterValues);
    this.setWidget(widget);
    this.setTag(tag);
    this.setParameterValues(parameterValues);
  }

  private void validateParams(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    if (!tag.applicableOver().contains(widget.getClass())) {
      throw new TagApplicationException(widget, tag, parameterValues, "Tag " + tag.getName() + " not applicable for widget class " + widget.getClass());
    }
    if (parameterValues.size() != tag.getParameters().size()) {
      throw new TagApplicationException(widget, tag, parameterValues, "Parameter names provided don't matches for tag " + tag.getName());
    }
    int i = 0;
    while (i < parameterValues.size()) {
      if (tag.getParameters().get(i).equals(parameterValues.get(i).getTagParameter())) {
        throw new TagApplicationException(widget, tag, parameterValues, "Parameter " + i + " don't matches for tag " + tag.getName());
      }
    }
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
