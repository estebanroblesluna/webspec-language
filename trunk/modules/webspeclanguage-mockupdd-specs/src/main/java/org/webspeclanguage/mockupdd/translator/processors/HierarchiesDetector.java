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

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.WidgetGroup;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class HierarchiesDetector implements MockupProcessor {

  private Class< ? extends Widget>[] excludedWidgets;

  public HierarchiesDetector(Class< ? extends Widget>[] excludedWidgets) {
    super();
    this.setExcludedWidgets(excludedWidgets);
  }

  @SuppressWarnings("unchecked")
  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    Collection<Widget> compositeWidgets = SuiUtil.filterWidgetsByType(widgetGroup.getWidgets(), CompositeWidget.class);
    compositeWidgets = this.filterExcludedWidgets(compositeWidgets);
    for (Widget c : this.filterExcludedWidgets(widgetGroup.getWidgets())) {
      CompositeWidget parent = (CompositeWidget) SuiUtil.getParentWidgetFromCollection(c, compositeWidgets);
      if (parent != null) {
        parent.addChild(c);
      }
    }
  }

  private Collection<Widget> filterExcludedWidgets(Collection<Widget> widgets) {
    return SuiUtil.excludeWidgetTypes(widgets, this.getExcludedWidgets());
  }

  void setExcludedWidgets(Class< ? extends Widget>[] excludedWidgets) {
    this.excludedWidgets = excludedWidgets;
  }

  Class< ? extends Widget>[] getExcludedWidgets() {
    return excludedWidgets;
  }

}
