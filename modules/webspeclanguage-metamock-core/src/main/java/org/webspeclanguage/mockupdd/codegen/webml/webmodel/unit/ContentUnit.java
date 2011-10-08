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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit;
import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

/**
 * @author Franco Giacosa
 */
public abstract class ContentUnit extends Unit {

	private List<AttributeDecorator> displayAttributes = new ArrayList<AttributeDecorator>();
	private Boolean displayAllAttributes = true;
	
	public ContentUnit(String id, String name, EntityDecorator entity) {
		super(id, name, entity);
		// TODO Auto-generated constructor stub
	}
	
	public List<AttributeDecorator> getDisplayAttributes() {
		return displayAttributes;
	}

	public void setDisplayAttributes(List<AttributeDecorator> displayAttributes) {
		this.displayAttributes = displayAttributes;
	}

	public Boolean getDisplayAllAttributes() {
		return displayAllAttributes;
	}

	public void setDisplayAllAttributes(Boolean displayAllAttributes) {
		this.displayAllAttributes = displayAllAttributes;
	}
}
