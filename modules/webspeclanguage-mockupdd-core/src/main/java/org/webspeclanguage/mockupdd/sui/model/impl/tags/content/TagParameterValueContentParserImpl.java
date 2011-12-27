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
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.FunctorException;
import org.apache.commons.collections.Transformer;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentParser;

/**
 * @author José Matías Rivero
 */
public class TagParameterValueContentParserImpl implements TagParameterValueContentParser {

  private SuiFactory suiFactory;

  public TagParameterValueContentParserImpl(SuiFactory suiFactory) {
    super();
    this.suiFactory = suiFactory;
  }

  public TagParameterValueContent parse(String representation) throws TagContentParsingException {
    String widgetId = null;
    String remaining = representation;
    String[] parts = remaining.split(":");
    if (parts.length == 2) {
      widgetId = this.checkIdent(parts[0]);
      remaining = parts[1];
    } else if (parts.length > 2) {
      throw new TagContentParsingException("The character \":\" appears more than one time in tag expression");
    }
    DataPathNode rootNode = this.parseDataPath(remaining);
    if (rootNode.size() == 1 && widgetId == null && rootNode.getAccessorName() == null) {
      return this.suiFactory.createSimpleTagParameterValueContent(rootNode.getClassName());
    } else {
      return this.suiFactory.createDataPathTagParameterValueContent(widgetId, rootNode);
    }
  }

  @SuppressWarnings("rawtypes")
  private DataPathNode parseDataPath(String string) throws TagContentParsingException {
    String[] nodes = string.split("->");
    DataPathNode firstNode = null;
    DataPathNode lastNode = null;
    for (String node : nodes) {
      DataPathNode freshNode = parseNodeContent(node);
      if (firstNode == null) {
        firstNode = freshNode;
      }
      if (lastNode != null) {
        lastNode.setNextNode(freshNode);
      }
      lastNode = freshNode;
    }
    return this.validateDataPath(firstNode);
  }

  private DataPathNode parseNodeContent(String node) throws TagContentParsingException {
    String[] parts = node.split("\\.");
    if (parts.length == 1) {
      return suiFactory.createDataPathNode(checkIdent(parts[0]));
    } else if (parts.length == 2) {
      return suiFactory.createDataPathNode(checkIdent(parts[0]), checkIdent(parts[1]));
    } else {
      throw new TagContentParsingException("Only one \".\" character can be used: \"" + node + "\"");
    }
  }

  private DataPathNode validateDataPath(DataPathNode rootNode) throws TagContentParsingException {
    DataPathNode currentNode = rootNode;
    while (currentNode != null) {
      DataPathNode node = ((DataPathNode)currentNode);
      if (node.getAccessorName() == null && node.getNextNode() != null) {
        throw new TagContentParsingException("Accessor can be ommited only on the last node in the chain");
      }
      currentNode = currentNode.getNextNode();
    }
    return rootNode;
  }

  private String checkIdent(String string) throws TagContentParsingException {
    String res = string.trim();
    if (res.matches("[a-zA-Z]([0-9a-zA-Z])*")) {
      return res;
    }
    throw new TagContentParsingException("Wrong identifier: \"" + string + "\"");
  }

}
