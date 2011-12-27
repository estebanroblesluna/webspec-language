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

import java.util.Collection;
import java.util.Map;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author José Matías Rivero
 */
public class WidgetIdInferer implements SuiModelProcessor {

  @Override
  public void process(SuiSpecsInferenceState specs) {
    for (Page p : specs.getModel().getPages()) {
      this.inferWidgetIdInPage(p);
    }
  }

  @SuppressWarnings("rawtypes")
  private void inferWidgetIdInPage(Page p) {
//    Map dataTaggedWidgets = JXPathUtil.getValues(p, "//widgets/appliedTags/tag[name='Data']", "../..", "../parameterValues[1]/value/textualRepresentation");
//    Widget pivotWidget = this.getPivotWidget(dataTaggedWidgets);
  }

  private Widget getPivotWidget(Map dataTaggedWidgets) {
    Collection<Widget> repetitions = 
            SuiUtil.filterWidgetsByType(dataTaggedWidgets.keySet(), Repetition.class);
    Widget pivotWidget;
    if (!repetitions.isEmpty()) {
      return repetitions.iterator().next();
    } else {
      return (Widget) dataTaggedWidgets.keySet().iterator().next();
    }
  }
}
