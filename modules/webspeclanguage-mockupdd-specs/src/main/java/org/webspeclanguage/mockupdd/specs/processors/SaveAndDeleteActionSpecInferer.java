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
 * @author Jose Matias Rivero
 */
public class SaveAndDeleteActionSpecInferer extends SuiModelProcessor {

  SaveAndDeleteSpecParameterVisitor specParameterVisitor;

  @Override
  public void process(SuiSpecsInferenceState specs) {
    this.specParameterVisitor = new SaveAndDeleteSpecParameterVisitor(specs);
    Collection<TagApplication> tags = specs.getTagIndexer().getTagApplicationsFor(SuiDefaultConfig.getInstance().getTag("Data", "Save"));
    tags.addAll(specs.getTagIndexer().getTagApplicationsFor(SuiDefaultConfig.getInstance().getTag("Data", "Delete")));
    for (TagApplication saveTag : tags) {
      this.specParameterVisitor.setCurrentTagApplication(saveTag);
      this.processTag(saveTag, specs);
    }
  }

  private void processTag(TagApplication saveTag, SuiSpecsInferenceState specs) {
    saveTag.getParameterValues().get(0).getValue().accept(this.specParameterVisitor);
  }

  private class SaveAndDeleteSpecParameterVisitor implements TagParameterValueContentVisitor<Void> {

    private SuiSpecsInferenceState specs;
    private TagApplication currentTagApplication;

    public SaveAndDeleteSpecParameterVisitor(SuiSpecsInferenceState specs) {
      this.specs = specs;
    }

    @Override
    public Void visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      return createSpecFromMapping(valueContent, 
              SpecInfererUtils.findSourceWidget(SaveAndDeleteActionSpecInferer.this, this.specs, this.currentTagApplication.getWidget(), 
                      null, valueContent.getTextualRepresentation()));
    }

    @Override
    public Void visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      return createSpecFromMapping(valueContent, 
              SpecInfererUtils.findSourceWidget(SaveAndDeleteActionSpecInferer.this, this.specs, this.currentTagApplication.getWidget(), 
                      valueContent.getWidgetId(), valueContent.getTextualRepresentation()));
    }

    private Void createSpecFromMapping(TagParameterValueContent valueContent, CompositeWidget sourceWidget) {
      ClassMappingSpec<CompositeWidget> cms = specs.getClassMappingSpecForWidget(sourceWidget);
      if (cms == null) {
        specs.addError(new SuiModelProcessingError(SaveAndDeleteActionSpecInferer.this, this.currentTagApplication.getWidget(), "Source widget \""
                + valueContent.getTextualRepresentation() + "\" not mapped"));
      }
      if (this.currentTagApplication.getTag().getName().equals("Save")) {
        specs.addSaveActionSpec((TriggerWidget) this.currentTagApplication.getWidget(), SuiSpecsConfig.getInstance().getHypertextSpecFactory().createSaveActionSpec(cms));
      } else if (this.currentTagApplication.getTag().getName().equals("Delete")) {
        specs.addDeleteActionSpec((TriggerWidget) this.currentTagApplication.getWidget(), SuiSpecsConfig.getInstance().getHypertextSpecFactory().createDeleteActionSpec(cms));
      
      }
      return null;
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
