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

import org.webspeclanguage.mockupdd.sui.model.annotation.CompositeWidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.GridBagLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.VerticalBoxLayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.WidgetAnnotationVisitor;

/**
 * A {@link WidgetAnnotationVisitor} that defines an empty implementation.
 * Useful to be inherited and overload only required methods
 *  
 * @author Jose Matias Rivero
 */
public class DefaultWidgetAnnotationVisitor implements
		WidgetAnnotationVisitor {

	public void visitCompositeWidgetAnnotation(
			CompositeWidgetAnnotation compositeWidgetAnnotation) {
	}

	public void visitWidgetAnnotation(WidgetAnnotation ca) {
	}

	public void visitGridBagLayoutAnnotation(
			GridBagLayoutAnnotation gridBagLayoutAnnotation) {

	}

	public void visitTemplateAnnotation(TemplateAnnotation templateAnnotation) {
	}

	public void visitTemplateInstantiation(TemplateInstantiationAnnotation pi) {
	}

	public void visitVerticalBoxLayoutAnnotation(
			VerticalBoxLayoutAnnotation verticalBoxLayoutAnnotation) {
	}

	public void visitRepetitionAnnotation(
			RepetitionAnnotation repetitionAnnotationImpl) {
	}

}
