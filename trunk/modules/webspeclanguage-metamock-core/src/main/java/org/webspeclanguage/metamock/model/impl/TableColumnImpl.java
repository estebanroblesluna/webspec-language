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
package org.webspeclanguage.metamock.model.impl;

import org.webspeclanguage.metamock.model.TableColumn;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link TableColumn}
 * 
 * @author Jose Matias Rivero
 */
public class TableColumnImpl implements TableColumn {

  private String label;

  public TableColumnImpl(String label) {
    super();
    this.setLabel(label);
  }

  public String getLabel() {
    return this.label;
  }

  private void setLabel(String label) {
    this.label = label;
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitTableColumn(this);
  }

  public boolean equals(Object obj) {
    TableColumn other = (TableColumn) obj;
    return this.getLabel().equals(other.getLabel());
  }

  public int hashCode() {
    return this.getLabel().hashCode();
  }

  public Object copy() {
    return new TableColumnImpl(this.getLabel());
  }

}
