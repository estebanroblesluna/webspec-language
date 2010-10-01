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
package org.webspeclanguage.metamock.translator;

import org.webspeclanguage.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockUtil;


/**
 * Represents a template which content is expressed through an {@link CompositeControl}.
 * 
 * @author Jose Matias Rivero
 */
public class Template {

	private CompositeControl contents;
	private String templateId;
	private MetaMockFactory factory;
	
	public Template(CompositeControl contents, String templateId, MetaMockFactory factory) {
		super();
		this.setContents(contents);
		this.setTemplateId(templateId);
		this.setFactory(factory);
	}

	public void instantiateIn(CompositeControl cc) {
		CompositeControl newCc = (CompositeControl) this.getContents().copy();
		for (UIControl c : MetaMockUtil.getAllControlsRecursively(newCc)) {
			c.setFriendlyId(cc.getFriendlyId() + CodegenUtil.firstToUpper(c.getFriendlyId()));
		}
		cc.setLayout(newCc.getLayout());
		cc.setControls(newCc.getControls());
	}

	private void setContents(CompositeControl contents) {
		this.contents = contents;
	}

	public CompositeControl getContents() {
		return contents;
	}

	private void setFactory(MetaMockFactory factory) {
		this.factory = factory;
	}

	public MetaMockFactory getFactory() {
		return factory;
	}

	private void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return templateId;
	}


}
