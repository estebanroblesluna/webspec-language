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
package org.webspeclanguage.mockupdd.translator.logger;

import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutException;
import org.webspeclanguage.mockupdd.translator.MockupSourceParsingException;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;

/**
 * @author Jose Matias Rivero
 */
public interface MockupProcessingLogger {

  void logTranslationException(SuiTranslationException exception);

  void logGridBagLayoutException(GridBagLayoutException e);

  void logSourceParsingException(MockupSourceParsingException e);

}
