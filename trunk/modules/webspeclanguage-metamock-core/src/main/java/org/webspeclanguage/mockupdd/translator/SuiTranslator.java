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

import java.util.Collection;

import org.webspeclanguage.mockupdd.codegen.generator.Mockup;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * Represents a translator of a specific source mockup representation to a {@link SuiModel} instance
 * 
 * @author matias
 */
public abstract class SuiTranslator<TSource> {
	
	
	public SuiModel translateModelFrom(Collection<Mockup<TSource>> mockups) throws SuiTranslationException, MockupSourceParsingException {
		return 
			this.applyAnnotationsAndInferLayouts(
				this.getRawModel(mockups));
	}

	
	/**
	 * Returns a {@link SuiModel} parsed and partially post-processed. Neither annotations and layouts 
	 * arent't applied 
	 */
	public abstract SuiModel getRawModel(Collection<Mockup<TSource>> mockup)
			throws SuiTranslationException, MockupSourceParsingException;


	/**
	 * Returns the initial {@link SuiModel} in which annotations are interpreted and layouts are
	 * inferred
	 */
	public abstract SuiModel applyAnnotationsAndInferLayouts(SuiModel model);
	
}
