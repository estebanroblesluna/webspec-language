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

package org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.SelectionField;


/**
 * @author Franco Giacosa
 */
public class OutputSelectionFieldParameter extends Parameter {

  private SelectionField selectionField;
  public OutputSelectionFieldParameter(SelectionField selectionField) {
    super(selectionField.getId(),selectionField.getName());
    this.selectionField = selectionField;
  }

  @Override
  public void accept(WebModelVisitor visitor) {
    visitor.visit(this);
  }

  
  public SelectionField getSelectionField() {
    return selectionField;
  }

  
  public void setSelectionField(SelectionField selectionField) {
    this.selectionField = selectionField;
  }

}
