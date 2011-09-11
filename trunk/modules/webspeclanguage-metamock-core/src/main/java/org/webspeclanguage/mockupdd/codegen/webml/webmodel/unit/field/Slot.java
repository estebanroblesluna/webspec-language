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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;

/**
 * @author Franco Giacosa
 */
public class Slot {

	
	private String id;
	private String name;
	private Boolean label;
	private Boolean output;
	
	public Slot(String id, String name, Boolean label, Boolean output) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
		this.output = output;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getLabel() {
		return label;
	}
	public void setLabel(Boolean label) {
		this.label = label;
	}
	public Boolean getOutput() {
		return output;
	}
	public void setOutput(Boolean output) {
		this.output = output;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
  public void accept(WebModelVisitor visitor){
    visitor.visit(this);
  }

}
