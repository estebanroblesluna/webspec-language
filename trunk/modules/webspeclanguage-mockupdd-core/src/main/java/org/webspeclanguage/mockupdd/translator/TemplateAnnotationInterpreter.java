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

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;

/**
 * Particular implementation of {@link AnnotationInterpreter} for {@link TemplateAnnotation}
 * 
 * @author Jose Matias Rivero
 */
public class TemplateAnnotationInterpreter extends AbstractAnnotationInterpreter {

	@Override
	public void visitTemplateInstantiation(TemplateInstantiationAnnotation ti) {
		this.getCurrentModel().getTemplate(ti.getTemplateId(), ti.getTemplateContainerId())
			.instantiateIn((CompositeWidget) ti.getWidget());
	}

	public void interpreteAnnotation(SuiAnnotation annotation,
			SuiModel model) {
		annotation.visit(this);		
	}
	
	@Override
	public void visitCompositeWidgetAnnotation(
			CompositeWidgetAnnotation cca) {
		if (cca.getTemplateInstantiationAnnotation() != null) {
			cca.getTemplateInstantiationAnnotation().visit(this);
		}
	}
}
