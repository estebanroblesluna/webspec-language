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

import org.webspeclanguage.mockupdd.mockups.MockupContainerInfo;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
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
public class WidgetRegistering implements MockupProcessor {

  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    for (Page p : model.getPages()) {
      for (Widget c : p.getWidgets()) {
        this.registerWidgetIn(c, model);
      }
    }
  }

  private void registerWidgetIn(Widget c, SuiModel model) {
    if (SuiUtil.widgetIsKindOf(c, CompositeWidget.class)) {
      this.registerCompositeWidgetIn((CompositeWidget) c, model);
    } else if (SuiUtil.widgetIsKindOf(c, SimpleWidget.class)) {
      this.registerSimpleWidgetIn((SimpleWidget) c, model);
    }
  }

  private void registerSimpleWidgetIn(SimpleWidget c, SuiModel model) {
    model.registerWidget(c);
  }

  private void registerCompositeWidgetIn(CompositeWidget c, SuiModel model) {
    for (Widget child : c.getWidgets()) {
      this.registerWidgetIn(child, model);
    }
  }

}
