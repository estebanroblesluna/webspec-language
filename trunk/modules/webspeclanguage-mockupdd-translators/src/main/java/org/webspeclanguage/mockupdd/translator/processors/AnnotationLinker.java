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

import java.util.Comparator;

import org.webspeclanguage.mockupdd.mockups.MockupContainerInfo;
import org.webspeclanguage.mockupdd.sui.model.Annotation;
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
public class AnnotationLinker implements MockupProcessor {

  @SuppressWarnings("unchecked")
  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    for (Widget c : SuiUtil.filterWidgetsByType(widgetGroup.getWidgets(), Annotation.class)) {
      Annotation a = (Annotation) c;
      a.setTargetElement(
      // chooses the inner-depth widget
      SuiUtil.sortWidgets(SuiUtil.getCollidingWidgets(widgetGroup.getWidgets(), a), new Comparator<Widget>() {
        public int compare(Widget c1, Widget c2) {
          return SuiUtil.getParents(c2).size() - SuiUtil.getParents(c1).size();
        }
      }).get(0));
    }
  }

}
