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
package org.webspeclanguage.mockupdd.translator.annotation;

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;

/**
 * Allows chaining of {@link AnnotationInterpreter}s
 * 
 * @author Jose Matias Rivero
 */
public class AnnotationProcessor {

	private AnnotationInterpreter interpreter;
	private AnnotationProcessor nextProcessor;
	
	public AnnotationProcessor(AnnotationInterpreter interpreter,
			AnnotationProcessor nextProcessor) {
		super();
		this.setInterpreter(interpreter);
		this.setNextProcessor(nextProcessor);
	}
	
	public AnnotationProcessor(AnnotationInterpreter interpreter) {
		this(interpreter, null);
	}

	public static AnnotationProcessor createChainedProcessors(AnnotationInterpreter... interpreters) {
		AnnotationProcessor previousProcessor = null;
		AnnotationProcessor firstProcessor = null;
		for (AnnotationInterpreter i : interpreters) {
			AnnotationProcessor newProcessor = new AnnotationProcessor(i);
			if (previousProcessor != null) {
				previousProcessor.setNextProcessor(newProcessor);
			} else {
				firstProcessor = newProcessor;
			}
			previousProcessor = newProcessor;
		}
		return firstProcessor; 
	}

	private void setInterpreter(AnnotationInterpreter interpreter) {
		this.interpreter = interpreter;
	}

	private AnnotationInterpreter getInterpreter() {
		return interpreter;
	}

	private void setNextProcessor(AnnotationProcessor nextProcessor) {
		this.nextProcessor = nextProcessor;
	}

	private AnnotationProcessor getNextProcessor() {
		return nextProcessor;
	}

	public final void processAnnotations(Collection<SuiAnnotation> annotations, SuiModel model) {
		this.getInterpreter().startingAnnotationInterpretationIn(model);
		for (SuiAnnotation a : annotations) {
			this.getInterpreter().interpreteAnnotation(a, model);
		}
		if (this.getNextProcessor() != null) {
			this.getNextProcessor().processAnnotations(annotations, model);
		}
	}
	
}
