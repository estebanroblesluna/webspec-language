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

import java.util.ArrayList;
import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.WidgetGroup;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class LayoutInferrer implements MockupProcessor {

  private LayoutFactory defaultLayoutFactory;

  public LayoutInferrer(LayoutFactory defaultLayoutFactory) {
    super();
    this.setDefaultLayoutFactory(defaultLayoutFactory);
  }

  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    Collection<Widget> widgets = new ArrayList<Widget>();
    for (Page p : model.getPages()) {
      widgets.addAll(SuiUtil.getAllWidgetsRecursively(p));
      widgets.add(p);
    }
    widgets.addAll(model.getWidgetsOutsidePages());
    for (SuiModelElement c : SuiUtil.filterWidgetsByType(widgets, CompositeWidget.class)) {
      CompositeWidget cc = (CompositeWidget) c;
      if (cc.getLayout() == null) {
        cc.setLayout(this.getDefaultLayoutFactory().createLayout(cc.getWidgets()));
      }
    }
  }

  private void setDefaultLayoutFactory(LayoutFactory defaultLayoutFactory) {
    this.defaultLayoutFactory = defaultLayoutFactory;
  }

  private LayoutFactory getDefaultLayoutFactory() {
    return defaultLayoutFactory;
  }

}
