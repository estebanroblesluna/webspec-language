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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.content.TagContentParsingException;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;

public class TagApplicationImpl implements TagApplication {

  private Widget widget;
  private Tag tag;
  private List<TagParameterValue> parameterValues;

  public TagApplicationImpl(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    super();
    this.validateParams(widget, tag, parameterValues);
    this.validateTagNotAppliedAlready(widget, tag, parameterValues);
    this.setWidget(widget);
    this.setTag(tag);
    this.setParameterValues(parameterValues);
  }

  public static TagApplicationImpl newFromTextualValues(Widget widget, Tag tag, List<String> parameterValues) throws TagApplicationException {
    List<TagParameterValue> values = new ArrayList<TagParameterValue>();
    if (tag.getParameters().size() == 0) {
      if (parameterValues.size() == 0) {
        return new TagApplicationImpl(widget, tag, Arrays.asList(new TagParameterValue[]{}));
      } else {
        throw new TagApplicationException(widget, tag, null, "The tag " + tag.getName() + " doesn't accept parameters");
      }
    }
    TagParameter currentParameter = null;
    for (int i = 0; i < parameterValues.size(); i++) {
      if (i < tag.getParameters().size()) {
        currentParameter = tag.getParameters().get(i);
      }
      try {
        values.add(new TagParameterValueImpl(currentParameter, 
                SuiDefaultConfig.getInstance().getTagParameterValueContentParser().parse(parameterValues.get(i))));
      } catch (TagContentParsingException e) {
        throw new TagApplicationException(widget, tag, null, "Tag parameter value parsing error :" + e.getMessage());
      }
    }
    return new TagApplicationImpl(widget, tag, values);
  }

  private void validateTagNotAppliedAlready(Widget w, Tag t, List<TagParameterValue> parameterValues2) throws TagApplicationException {
    for (TagApplication ta : w.getAppliedTags()) {
      if (ta.getTag().equals(t)) {
        throw new TagApplicationException(w, t, parameterValues, "The tag " + t.getName() + " has been already applied over that widget");
      }
    }
  }

  private void validateParams(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    this.checkApplicability(widget, tag, parameterValues);
    //this.checkParameterWellFormedness(widget, tag, parameterValues);
    this.checkParameterCount(widget, tag, parameterValues);
    this.checkParameters(widget, tag, parameterValues);
  }

  private void checkParameterWellFormedness(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    for (TagParameterValue value : parameterValues) {
      if (!value.getValue().getTextualRepresentation().matches("[a-zA-Z0-9]+(:[a-zA-Z0-9]+)?(\\.[a-zA-Z0-9]+)?")) {
        throw new TagApplicationException(widget, tag, parameterValues, "The tag " + tag.getName() + " has an invalid parameter value: \"" + value.getValue() + "\"");
      }
    }
  }

  private void checkParameters(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    int i = 0;
    TagParameter currentParameter = null;
    for (; i < parameterValues.size(); i++) {
      if (i < tag.getParameters().size()) {
        currentParameter = tag.getParameters().get(i);
      }
      if (!currentParameter.equals(parameterValues.get(i).getTagParameter())) {
        throw new TagApplicationException(widget, tag, parameterValues, "Parameter " + i + " does not match for tag " + tag.getName());
      }
    }
  }

  private void checkParameterCount(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    if (parameterValues.size() < tag.getParameters().size() && tag.isParameterStrict()) {
      throw new TagApplicationException(widget, tag, parameterValues, "Parameter provided are less than expected for tag " + tag.getName());
    }
  }

  private void checkApplicability(Widget widget, Tag tag, List<TagParameterValue> parameterValues) throws TagApplicationException {
    if (!this.tagIsApplicableOver(tag, widget.getClass())) {
      throw new TagApplicationException(widget, tag, parameterValues, "Tag " + tag.getName() + " not applicable for widget class " + widget.getClass());
    }
  }
  
  private boolean tagIsApplicableOver(Tag tag, Class<? extends Widget> widgetClassToCheck) {
    for (Class<? extends Widget> widgetClass : tag.applicableOver()) {
      if (widgetClass.isAssignableFrom(widgetClassToCheck)) {
        return true;
      }
    }
    return false;
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

  public void remove() {
    this.getWidget().removeTagApplication(this);
    this.setWidget(null);
  }

}
