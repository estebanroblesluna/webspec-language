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

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.annotation.CompositeControlAnnotation;
import org.webspeclanguage.metamock.model.annotation.MetaMockAnnotation;
import org.webspeclanguage.metamock.model.annotation.TemplateInstantiationAnnotation;

/**
 * Particular implementation of {@link AnnotationInterpreter} for {@link TemplateAnnotation}
 * 
 * @author Jose Matias Rivero
 */
public class TemplateAnnotationInterpreter extends AbstractAnnotationInterpreter {

	@Override
	public void visitTemplateInstantiation(TemplateInstantiationAnnotation ti) {
		this.getCurrentModel().getTemplate(ti.getTemplateId(), ti.getTemplateContainerId())
			.instantiateIn((CompositeControl) ti.getControl());
	}

	public void interpreteAnnotation(MetaMockAnnotation annotation,
			MetaMockModel model) {
		annotation.visit(this);		
	}
	
	@Override
	public void visitCompositeControlAnnotation(
			CompositeControlAnnotation cca) {
		if (cca.getTemplateInstantiationAnnotation() != null) {
			cca.getTemplateInstantiationAnnotation().visit(this);
		}
	}
}
