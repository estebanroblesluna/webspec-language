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

import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.WidgetGroup;
import org.webspeclanguage.mockupdd.utils.Rectangle;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class PageCreator implements MockupProcessor {

  @SuppressWarnings("unchecked")
  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    if (SuiUtil.filterWidgetsByType(widgetGroup.getWidgets(), Page.class).size() == 0) {
      Rectangle r = SuiUtil.getSurroundingRectangle(widgetGroup.getWidgets(), 1);
      Page p = engine.getFactory().createPage(containerInfo.getName(), r.getLeft(), r.getTop(), r.getWidth(), r.getHeight(),
              CodegenUtil.camelCaseToSpaces(containerInfo.getName()), containerInfo.getName());
      widgetGroup.addWidget(p);
    }
  }

}
