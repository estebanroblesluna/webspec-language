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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.ActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.utils.SuiCollectionUtils;
import org.webspeclanguage.mockupdd.utils.SuiCollectionUtils.Indexer;

/**
 * @author Jose Matias Rivero
 */
public class NavigationSpecInferer extends SuiModelProcessor {

  @Override
  public void process(SuiSpecsInferenceState specs) {
    Map<String, TagApplication> nodes = this.getIndexedNodes(specs);
    for (Page p : specs.getModel().getPages()) {
      Collection<TagApplication> links = getLinks(specs, p);
      for (TagApplication ta : links) {
        String linkId = ta.getParameterValues().get(0).getValue().getTextualRepresentation();
        TagApplication node = nodes.get(linkId);
        if (node == null) {
          specs.addError(new SuiModelProcessingError(this, ta.getWidget(), "Destination node \"" + linkId + "\" not found."));
          continue;
        }
        Page destPage = (Page) nodes.get(linkId).getWidget();
        List<ObjectTransferSpec> transferSpecs = this.getTransferSpecs(specs, ta.getWidget(), destPage);
        specs.addNavigationSpec(SuiSpecsConfig.getInstance()
                .getHypertextSpecFactory().createNavigationSpec(destPage, (TriggerWidget) ta.getWidget(), 
                        new ArrayList<ActionSpec>(), transferSpecs));
      }
    }
  }

  private List<ObjectTransferSpec> getTransferSpecs(SuiSpecsInferenceState specs, Widget w, Page destPage) {
    List<ObjectTransferSpec> transfers = new ArrayList<ObjectTransferSpec>();
    for (TagApplication ta : w.getAppliedTags()) {
      if (ta.getTag().equals(SuiDefaultConfig.getInstance().getTag("Nav", "Transfer"))) {
        
        Widget from = this.getWidget(specs, w.getPage(), ta.getParameterValues().get(0).getValue().getTextualRepresentation());
        Widget to = this.getWidget(specs, destPage, ta.getParameterValues().get(1).getValue().getTextualRepresentation());
        if (from != null && to != null) {
          transfers.add(
              SuiSpecsConfig.getInstance()
                  .getHypertextSpecFactory().createObjectTransferSpec(from, to));
        }
      }
    }
    return transfers;
  }

  private Widget getWidget(SuiSpecsInferenceState specs, Page page, String widgetId) {
    Widget w = page.getWidgetById(widgetId);
    if (w != null) {
      return w;
    } else {
      specs.addError(new SuiModelProcessingError(this, page, "Widget \"" + widgetId + 
              "\" not found in page \"" + page.getWidgetId() + "\""));
      return null;
    }
  }

  private Map<String, TagApplication> getIndexedNodes(SuiSpecsInferenceState specs) {
    return SuiCollectionUtils.index(
            specs.getTagIndexer().getTagApplicationsFor(
                    SuiDefaultConfig.getInstance().getTag("Nav", "Node")) 
                    
      , new Indexer<String, TagApplication>() {

        @Override
        public String getIndex(TagApplication object) {
          return object.getParameterValues().get(0).getValue().getTextualRepresentation();
        }
      
    });
  }
    
  private Collection<TagApplication> getLinks(SuiSpecsInferenceState specs, Page p) {
    return specs.getTagIndexer().getTagApplicationsFor(
            SuiDefaultConfig.getInstance().getTag("Nav", "Link"), p); 
  }

}
