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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.links;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;

/**
 * @author Franco Giacosa
 */
public abstract class ContextualLink extends Link {
	
	private Unit targetUnit;
	private Unit sourceUnit;
	
	public ContextualLink(String id, String name, Boolean automaticCoupling, Unit targetUnit,Unit sourceUnit) {
		super(id, name, automaticCoupling);
		this.targetUnit = targetUnit;
		this.sourceUnit = sourceUnit;
	}
	
	
	public Unit getTargetUnit() {
		return targetUnit;
	}
	public void setTargetUnit(Unit targetUnit) {
		this.targetUnit = targetUnit;
	}
	public Unit getSourceUnit() {
		return sourceUnit;
	}
	public void setSourceUnit(Unit sourceUnit) {
		this.sourceUnit = sourceUnit;
	}

}
