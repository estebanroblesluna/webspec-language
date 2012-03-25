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

package org.webspeclanguage.mockupdd.specs.processors;

import java.util.List;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author José Matías Rivero
 */
public class SpecInfererUtils {

  public static CompositeWidget findSourceWidget(
          SuiModelProcessor processor, SuiSpecsInferenceState specs, Widget widget, 
          String widgetId, String valueRepresentation) {
    CompositeWidget sourceWidget = null;
    if (widgetId != null) {
      sourceWidget = (CompositeWidget) widget.getPage().getWidgetById(widgetId);
      if (sourceWidget == null) {
        specs.addError(new SuiModelProcessingError(processor, widget, "Widget \""
                + widgetId + " not found"));
      }
    } else {
      List<Widget> parents = SuiUtil.getParents(widget);
      for (Widget parent : parents) {
        if (specs.getClassMappingSpecForWidget((CompositeWidget) parent) != null) {
          sourceWidget = (CompositeWidget) parent;
          break;
        }
      }
      if (sourceWidget == null) {
        specs.addError(new SuiModelProcessingError(processor, widget, "Cannot find a data context for \""
                + valueRepresentation + "\" in widget hierarchy"));
      }
    }
    return sourceWidget;
  }
  
}
