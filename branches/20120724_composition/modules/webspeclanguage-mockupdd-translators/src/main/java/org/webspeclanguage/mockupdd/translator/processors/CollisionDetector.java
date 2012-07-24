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
import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.WidgetGroup;
import org.webspeclanguage.mockupdd.translator.CollidingWidgetsException;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class CollisionDetector implements MockupProcessor {

  public <TSource> void process(WidgetGroup widgetGroup, SuiModel model, MockupContainerInfo containerInfo, MockupProcessingEngine<TSource> engine)
          throws SuiTranslationException {
    for (Widget c : widgetGroup.getWidgets()) {
      for (Widget c2 : SuiUtil.getCollidingWidgets(widgetGroup.getWidgets(), c)) {
        if (this.widgetIsIncludedIn(c, c2)) {
          continue;
        }
        if (this.widgetIsIncludedIn(c2, c)) {
          continue;
        }
        if (this.oneOfWidgetsIsAnAnnotation(c, c2)) {
          continue;
        }
        throw new CollidingWidgetsException(model, c, c2);
      }
    }
  }

  private boolean widgetIsIncludedIn(Widget c, Widget c2) {
    if (!SuiUtil.widgetIsKindOf(c2, CompositeWidget.class)) {
      return false;
    }
    return SuiUtil.isIncludedIn(c, c2);
  }

  private boolean oneOfWidgetsIsAnAnnotation(Widget c, Widget c2) {
    return (SuiUtil.widgetIsKindOf(c, Annotation.class) && !SuiUtil.widgetIsKindOf(c2, Annotation.class))
            || (SuiUtil.widgetIsKindOf(c2, Annotation.class) && !SuiUtil.widgetIsKindOf(c, Annotation.class));
  }

}
