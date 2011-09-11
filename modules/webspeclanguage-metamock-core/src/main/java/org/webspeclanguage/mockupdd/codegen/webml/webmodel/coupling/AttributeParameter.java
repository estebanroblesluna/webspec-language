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
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;

/**
 * @author Franco Giacosa
 */
public class AttributeParameter extends Parameter {

	public AttributeDecorator attribute;

	public AttributeParameter(String id, AttributeDecorator attribute) {
		super(id,attribute.getName());
		this.attribute = attribute;
	}
	public AttributeDecorator getAttribute() {
		return attribute;
	}
	public void setAttribute(AttributeDecorator attribute) {
		this.attribute = attribute;
	}
	public void accept(WebModelVisitor visitor) {
	  visitor.visit(this);
	}  
}
