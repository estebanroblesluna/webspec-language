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
import java.util.List;

import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.SimpleTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author José Matías Rivero
 */
public class SaveActionSpecInferer extends SuiModelProcessor {

  SaveSpecParameterVisitor saveSpecParameterVisitor;

  @Override
  public void process(SuiSpecsInferenceState specs) {
    this.saveSpecParameterVisitor = new SaveSpecParameterVisitor(specs);
    Collection<TagApplication> saveTags = specs.getTagIndexer().getTagApplicationsFor(SuiDefaultConfig.getInstance().getTag("Data", "Save"));
    for (TagApplication saveTag : saveTags) {
      this.saveSpecParameterVisitor.setCurrentTagApplication(saveTag);
      this.processSaveTag(saveTag, specs);
    }
  }

  private void processSaveTag(TagApplication saveTag, SuiSpecsInferenceState specs) {
    saveTag.getParameterValues().get(0).getValue().accept(this.saveSpecParameterVisitor);
  }

  private class SaveSpecParameterVisitor implements TagParameterValueContentVisitor<Void> {

    private SuiSpecsInferenceState specs;
    private TagApplication currentTagApplication;

    public SaveSpecParameterVisitor(SuiSpecsInferenceState specs) {
      this.specs = specs;
    }

    @Override
    public Void visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      return createSaveSpecFromMapping(valueContent, 
              this.findSourceWidget(null, valueContent.getTextualRepresentation()));
    }

    @Override
    public Void visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      return createSaveSpecFromMapping(valueContent, 
              this.findSourceWidget(valueContent.getWidgetId(), valueContent.getTextualRepresentation()));
    }

    private Void createSaveSpecFromMapping(TagParameterValueContent valueContent, CompositeWidget sourceWidget) {
      ClassMappingSpec<CompositeWidget> cms = specs.getClassMappingSpecForWidget(sourceWidget);
      if (cms == null) {
        specs.addError(new SuiModelProcessingError(SaveActionSpecInferer.this, this.currentTagApplication.getWidget(), "Source widget \""
                + valueContent.getTextualRepresentation() + "\" not mapped"));
      }
      specs.addSaveActionSpec((TriggerWidget) this.currentTagApplication.getWidget(), SuiSpecsConfig.getInstance().getHypertextSpecFactory().createSaveActionSpec(cms));
      return null;
    }

    private CompositeWidget findSourceWidget(String widgetId, String valueRepresentation) {
      CompositeWidget sourceWidget = null;
      if (widgetId != null) {
        sourceWidget = (CompositeWidget) this.currentTagApplication.getWidget().getPage().getWidgetById(widgetId);
        if (sourceWidget == null) {
          this.specs.addError(new SuiModelProcessingError(SaveActionSpecInferer.this, this.currentTagApplication.getWidget(), "Widget \""
                  + widgetId + " not found"));
        }
      } else {
        List<Widget> parents = SuiUtil.getParents(this.currentTagApplication.getWidget());
        for (Widget parent : parents) {
          if (specs.getClassMappingSpecForWidget((CompositeWidget) parent) != null) {
            sourceWidget = (CompositeWidget) parent;
            break;
          }
        }
        if (sourceWidget == null) {
          specs.addError(new SuiModelProcessingError(SaveActionSpecInferer.this, this.currentTagApplication.getWidget(), "Cannot find a data context for \""
                  + valueRepresentation + "\" in widget hierarchy"));
        }
      }
      return sourceWidget;
    }

    @Override
    public Void visitDataPathNode(DataPathNode dataPathNode) {
      return null;
    }

    public void setCurrentTagApplication(TagApplication currentTagApplication) {
      this.currentTagApplication = currentTagApplication;
    }

  }

}
