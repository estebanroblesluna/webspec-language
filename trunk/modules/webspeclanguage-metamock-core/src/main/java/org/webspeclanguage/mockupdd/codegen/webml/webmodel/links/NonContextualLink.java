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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;

/**
 * @author Franco Giacosa
 */
public class NonContextualLink extends Link {

	private Page targetPage;
	private Page sourcePage;
	
	public NonContextualLink(String id, String name, Boolean automaticCoupling, Page targetPage,	Page sourcePage) {
		super(id, name, automaticCoupling);
		this.targetPage = targetPage;
		this.sourcePage = sourcePage;
	}
	public Page getTargetPage() {
		return targetPage;
	}
	public void setTargetPage(Page targetPage) {
		this.targetPage = targetPage;
	}
	public Page getSourcePage() {
		return sourcePage;
	}
	public void setSourcePage(Page sourcePage) {
		this.sourcePage = sourcePage;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
}
