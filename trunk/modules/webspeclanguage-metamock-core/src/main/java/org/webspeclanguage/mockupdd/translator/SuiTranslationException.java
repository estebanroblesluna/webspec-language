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

import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * Exception thrown when some anomalous event occurs during mockup translation
 * 
 * @author Jose Matias Rivero
 */
public class SuiTranslationException extends Exception {

	private static final long serialVersionUID = 1L;
  private SuiModel model;

	public SuiTranslationException(SuiModel model) {
		super();
		this.setModel(model);
	}

	private void setModel(SuiModel model) {
		this.model = model;
	}

	public SuiModel getModel() {
		return model;
	}

}
