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
 * Exception thrown when some anomalous event occurs during mockup translation
 * 
 * @author Jose Matias Rivero
 */
public class MetaMockTranslationException extends Exception {

	private static final long serialVersionUID = 1L;
  private MetaMockModel model;

	public MetaMockTranslationException(MetaMockModel model) {
		super();
		this.setModel(model);
	}

	private void setModel(MetaMockModel model) {
		this.model = model;
	}

	public MetaMockModel getModel() {
		return model;
	}

}
