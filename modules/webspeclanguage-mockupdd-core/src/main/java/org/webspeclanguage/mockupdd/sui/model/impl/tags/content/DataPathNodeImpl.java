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

import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;

/**
 * @author José Matías Rivero
 */
public class DataPathNodeImpl implements DataPathNode {

  private String accessorName;
  private String className;
  private DataPathNode nextNode;

  public DataPathNodeImpl(String accessorName, String className) {
    super();
    this.setAccessorName(accessorName);
    this.setClassName(className);
  }
  
  public DataPathNodeImpl(String accessorName, String className, DataPathNode nextNode) {
    super();
    this.setAccessorName(accessorName);
    this.setClassName(className);
    this.setNextNode(nextNode);
  }

  public DataPathNodeImpl(String className) {
    this(null, className);
  }

  @Override
  public String getAccessorName() {
    return accessorName;
  }

  private void setAccessorName(String accessorName) {
    this.accessorName = accessorName;
  }

  @Override
  public String getClassName() {
    return className;
  }

  private void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String getTextualRepresentation() {
    return this.className + (this.getAccessorName() != null ? "." + this.getAccessorName() : "");
  }
  
  @Override
  public <T> T accept(TagParameterValueContentVisitor<T> visitor) {
    return visitor.visitDataPathNode(this);
  }

  @Override
  public DataPathNode getNextNode() {
    return nextNode;
  }

  @Override
  public void setNextNode(DataPathNode nextNode) {
    this.nextNode = nextNode;
  }

  @Override
  public int size() {
    if (this.getNextNode() != null) {
      return 1 + this.getNextNode().size();
    }
    return 1;
  }

  @Override
  public DataPathNode lastNode() {
    if (this.getNextNode() == null) {
      return this;
    } else {
      return this.getNextNode().lastNode();
    }
      
  }

}
