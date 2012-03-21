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
package org.webspeclanguage.mockupdd.sui.model.impl.tags.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;

/**
 * @author Jose Matias Rivero
 */
public class DataPathTagParameterValueContentImpl implements DataPathTagParameterValueContent {

  private String widgetId;
  private DataPathNode rootNode;

  public DataPathTagParameterValueContentImpl(String widgetId, DataPathNode rootNode) {
    super();
    this.setWidgetId(widgetId);
    this.setRootNode(rootNode);
  }

  @Override
  public String getWidgetId() {
    return widgetId;
  }

  private void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  @Override
  public String getTextualRepresentation() {
    String rep = this.getWidgetId() == null ? "" : this.getWidgetId() + ": ";
    DataPathNode node = this.getRootNode();
    if (node != null) {
      rep += node.getTextualRepresentation();
    }
    node = node.getNextNode();
    while (node != null) {
      rep += " -> " + node.getTextualRepresentation();
    }
    return rep;
  }

  @Override
  public <T> T accept(TagParameterValueContentVisitor<T> visitor) {
    return visitor.visitDataPathTagParameterValueContent(this);
  }

  @Override
  public DataPathNode getRootNode() {
    return rootNode;
  }

  private void setRootNode(DataPathNode rootNode) {
    this.rootNode = rootNode;
  }

}
