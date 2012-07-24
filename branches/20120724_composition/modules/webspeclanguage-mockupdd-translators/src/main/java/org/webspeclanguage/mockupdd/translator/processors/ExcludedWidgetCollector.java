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
package org.webspeclanguage.mockupdd.translator.processors;

import java.util.Collection;

import org.webspeclanguage.mockupdd.mockups.MockupContainerInfo;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.WidgetGroup;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class ExcludedWidgetCollector implements MockupProcessor {

  private Class< ? extends Widget>[] excludedWidgets;

  public ExcludedWidgetCollector(Class< ? extends Widget>[] excludedWidgets) {
    super();
    this.setExcludedWidgets(excludedWidgets);
  }

  @SuppressWarnings("unchecked")
  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    Collection<Widget> excludedWidgets = this.filterNonHierarchicalWidgets(widgetGroup.getWidgets());
    for (Widget c : SuiUtil.filterWidgetsByType(widgetGroup.getWidgets(), CompositeWidget.class)) {
      if (c.getPage() == null) {
        excludedWidgets.add(c);
      }
    }
    for (Widget c : excludedWidgets) {
      model.addWidgetOutsidePages(c);
    }
  }

  private Collection<Widget> filterNonHierarchicalWidgets(Collection<Widget> widgets) {
    return SuiUtil.filterWidgetsByType(widgets, this.getExcludedWidgets());
  }

  private void setExcludedWidgets(Class< ? extends Widget>[] excludedWidgets) {
    this.excludedWidgets = excludedWidgets;
  }

  private Class< ? extends Widget>[] getExcludedWidgets() {
    return excludedWidgets;
  }

}
