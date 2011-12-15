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
package org.webspeclanguage.mockupdd.translator;

import org.webspeclanguage.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiUtil;


/**
 * Represents a template which content is expressed through an {@link CompositeWidget}.
 * 
 * @author Jose Matias Rivero
 */
public class Template {

	private CompositeWidget contents;
	private String templateId;
	private SuiFactory factory;
	
	public Template(CompositeWidget contents, String templateId, SuiFactory factory) {
		super();
		this.setContents(contents);
		this.setTemplateId(templateId);
		this.setFactory(factory);
	}

	public void instantiateIn(CompositeWidget cc) {
		CompositeWidget newCc = (CompositeWidget) this.getContents().copy();
		for (Widget c : SuiUtil.getAllWidgetsRecursively(newCc)) {
			c.setFriendlyId(cc.getFriendlyId() + CodegenUtil.firstToUpper(c.getFriendlyId()));
		}
		cc.setLayout(newCc.getLayout());
		cc.setWidgets(newCc.getWidgets());
	}

	private void setContents(CompositeWidget contents) {
		this.contents = contents;
	}

	public CompositeWidget getContents() {
		return contents;
	}

	private void setFactory(SuiFactory factory) {
		this.factory = factory;
	}

	public SuiFactory getFactory() {
		return factory;
	}

	private void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return templateId;
	}


}
