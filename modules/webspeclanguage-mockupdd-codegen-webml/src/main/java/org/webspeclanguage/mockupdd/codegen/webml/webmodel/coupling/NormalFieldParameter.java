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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;

/**
 * @author Franco Giacosa
 */
public class NormalFieldParameter extends Parameter {

	private NormalField field;
	
	public NormalFieldParameter(String id, NormalField field) {
		super(id,field.getName());
		this.field = field;
		// TODO Auto-generated constructor stub
	}
	public Field getField() {
		return field;
	}
	public void setField(NormalField field) {
		this.field = field;
	}
	public void accept(WebModelVisitor visitor) {
    visitor.visit(this);
  }
}
