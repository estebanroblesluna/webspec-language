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

import org.webspeclanguage.metamock.model.MetaMockModel;

/**
 * Represents a translator of a specific source mockup representation to a {@link MetaMockModel} instance
 * 
 * @author matias
 */
public abstract class MetaMockTranslator<TSource> {
	
	
	public MetaMockModel translateModelFrom(TSource source, MockupContainerInfo info) throws MetaMockTranslationException {
		return 
			this.applyAnnotationsAndInferLayouts(
				this.getRawModel(source, info));
	}

	
	/**
	 * Returns a {@link MetaMockModel} parsed and partially post-processed. Neither annotations and layouts 
	 * arent't applied 
	 */
	public abstract MetaMockModel getRawModel(TSource source, MockupContainerInfo info)
			throws MetaMockTranslationException;


	/**
	 * Returns the initial {@link MetaMockModel} in which annotations are interpreted and layouts are
	 * inferred
	 */
	public abstract MetaMockModel applyAnnotationsAndInferLayouts(MetaMockModel model);
	
}
